package com.example.untiled1.domain.importExcel;

import com.example.untiled1.domain.exportExcel.KeySearchListObj;
import com.example.untiled1.domain.exportExcel.ReportRepository;
import com.example.untiled1.domain.exportExcel.ReportRequestInfo;
import com.example.untiled1.global.storage.Storage;
import com.example.untiled1.global.utils.ReportUtil;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.untiled1.global.constants.ConstanExcel.RegexCell.REGEX_CELL_NUMBER;
import static com.example.untiled1.global.constants.ConstanExcel.RegexCell.checkRegexCell;
import static com.example.untiled1.global.constants.ConstanExcel.setErrorMessage;

@Service
@Slf4j
@ComponentScan
public class importExcelService {

    @Autowired
    private Storage storage;
    @Autowired
    ReportRepository reportRepo;
    @Value("${spring.datasource.url}")
    String oracleUrl;

    @Value("${spring.datasource.password}")
    String oraclePassword;

    @Value("${spring.datasource.username}")
    String oracleUsername;

    public void importExcels(List<MultipartFile> partFiles, KeySearchListObj objInput) throws SQLException {
        OracleConnection connection = null;
        try {
            OracleDataSource odcDataSource = new OracleDataSource();
            odcDataSource.setURL(oracleUrl);
            odcDataSource.setUser(oracleUsername);
            odcDataSource.setPassword(oraclePassword);
            connection = (OracleConnection) odcDataSource.getConnection();
        } catch (Exception e) {
            System.out.println("DB connection Exception");
            e.printStackTrace();
        }
        for (MultipartFile partFile : partFiles) {
            final List<Struct> arrayStruct = new ArrayList<Struct>();
            final Object[] item = new Object[5];
            ReportRequestInfo reportInfo = null;
            int n = 0;
            try {
                InputStream inputStream = partFile.getInputStream();
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                String nameFile = partFile.getOriginalFilename().split("_")[1];
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    XSSFSheet sheet = workbook.getSheetAt(i);
                    String sheetName = workbook.getSheetName(i);
//                    objInput.setTenDonViBC(sheetName);
//                    String[] results = sheetName.split("_");
//                    if (results.length != 2) {
//                        String message = " Tên báo cáo " + sheetName + " ko đúng định dạng;     ";
//                        setErrorMessage(message);
//                        log.error(message);
//                        break;
//                    }
//                    String nameCapHC = results[0];
//                    String nameDvCA = results[1];
                    objInput.setReportCode(nameFile);
                    reportInfo = reportRepo.saveReportRequestTest(
                            objInput, "strToken");
                    if (reportInfo == null) {
                        String message = " Tên báo cáo file " + nameFile + " ko đúng;     ";
                        setErrorMessage(message);
                        log.error(message);
                        break;
                    }

                    if (reportInfo.getMaDvCa() == null || Objects.equals(reportInfo.getMaDvCa(), "")) {
                        String message = "tên đơn vị báo cáo  " +nameFile+ " Sheet "+ sheetName + " ko đúng định dạng;     ";
                        setErrorMessage(message);
                        log.error(message);
                        break;
                    }

                    for (int j = reportInfo.getMinRow(); j <= reportInfo.getMaxRow(); j++) {
                        if (reportInfo.getDeleteRow() != null && reportInfo.getDeleteRow().contains(("_" + j + "_"))) {
                            continue;
                        }
                        for (int k = reportInfo.getMinCell(); k <= reportInfo.getMaxCell(); k++) {
                            if (reportInfo.getDeleteCol() != null && reportInfo.getDeleteCol().contains(("_" + k + "_"))) {
                                continue;
                            }
                            if (isCell(nameFile, sheetName, sheet, k, j, reportInfo.getMaxCell(), reportInfo.getMinCell())) {
                                item[0] = getCellValue(sheet, k, j);

//                                item[0] = file.getOriginalFilename();
//                                item[1] = getCellValue(sheet, k, j);
                                item[1] = k;
                                item[2] = j;
                                item[3] = reportInfo.getMaDvCa();
                                item[4] = objInput.getTenTaiKhoan();
//                                item[4] = objInput.getIdCauHinhBaoCao();
                                arrayStruct.add(connection.createStruct(reportInfo.getDataType(), item));
                            } else n++;
                        }
                    }
                }
                if (arrayStruct.size() != 0 && n == 0) {
                    try {
//                        lưu file đính kèm vào storage
                        storage.uploadToPrivateBucket(partFile.getOriginalFilename().replace(".xlsx", "")+"_"+objInput.getTenFileDinhKem()+".xlsx", partFile.getInputStream());
                        objInput.setTenFileDinhKem(partFile.getOriginalFilename().replace(".xlsx", "")+"_"+objInput.getTenFileDinhKem()+".xlsx");
//                        lưu từng ô trong excel vào db theo tọa độ
                        final Array arrayStructs = connection.createOracleArray(reportInfo.getDataTypes(),
                                arrayStruct.toArray());
                        final String sqlQuery = "{ " + "call " + reportInfo.getImportExcelPr() + "(?,?)" + " }";
                        final CallableStatement stmt = connection.prepareCall(sqlQuery);
                        stmt.setString(1, ReportUtil.convertObject2Json(objInput));
                        stmt.setArray(2, arrayStructs);
                        stmt.executeUpdate();
                        if (stmt != null) {
                            stmt.close();
                        }
                    } catch (
                            SQLException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                log.error(e.getMessage());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private  Object getCellValue(XSSFSheet sheet , int cellNum, int rowNum) {
        XSSFRow row = sheet.getRow(rowNum);

        CellType cellType = row.getCell(cellNum).getCellTypeEnum();
        Object cellValue = null;

        switch (cellType) {
            case BOOLEAN:
                cellValue = row.getCell(cellNum).getBooleanCellValue();
                break;
            case NUMERIC:
                cellValue = row.getCell(cellNum).getNumericCellValue();
                break;
            case STRING:
                cellValue = row.getCell(cellNum).getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }


        return cellValue;
    }

    private boolean isCell(String fileName,String sheetName ,XSSFSheet sheet , int cellNum, int rowNum, int maxCol, int minCol) {
        try {
            //nếu cell = null thì sẽ bắn lỗi NullPointerException
            getCellValue( sheet , cellNum,  rowNum).toString();
            if (cellNum != minCol ) {
                return checkRegexCell(fileName,sheetName,REGEX_CELL_NUMBER,
                        getCellValue(sheet , cellNum,  rowNum).toString(), cellNum, rowNum);
            }
            return true;
        } catch (NullPointerException e) {
            String message = "File " + fileName + " Sheet " + sheetName + " Row " + (rowNum +1) + " column " + (cellNum +1) + " is empty;     ";
            setErrorMessage(message);
            log.error(message);
            return false;
        }

    }


    public ResponseEntity<byte[]> downloadFile(KeySearchListObj objInput) throws IOException {
//        if (objInput.getTenFileDinhKem() == null) {
//            ReportRequestInfo reportInfo = reportRepo.getCauHinhBC(objInput);
//            if (reportInfo != null) {
//                InputStream is = storage.getPrivateObject(reportInfo.getTenBC()+".xlsx");
//                return   ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +"BC_" +reportInfo.getTenBC()+"_" + reportInfo.getChuKy()+"_"+ reportInfo.getNamBC()+".xlsx"+ "\"")
//                        .body(IOUtils.toByteArray(is));
//            }
//            throw new ApplicationException("upload file không thành công");
//
//        }else {
//            String as = objInput.getTenFileDinhKem().split("_")[objInput.getTenFileDinhKem().split("_").length-1];
////            objInput.getTenFileDinhKem().replace(as,"");
//            InputStream is = storage.getPrivateObject(objInput.getTenFileDinhKem());
//            return   ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +objInput.getTenFileDinhKem().replace(as,"")+".xlsx"+ "\"")
//                    .body(IOUtils.toByteArray(is));
//        }
        return null;

    }

    public ResponseEntity<byte[]> downloadFileff( String path) throws IOException {
//        ReportRequestInfo reportInfo = reportRepo.getCauHinhBC(objInput);
//        InputStream is = storage.getPrivateObject(path);
//        return   ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +path+ "\"")
//                .body(IOUtils.toByteArray(is));
        return null;
    }


}
