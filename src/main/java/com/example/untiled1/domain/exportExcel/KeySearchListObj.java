package com.example.untiled1.domain.exportExcel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.Lob;
import java.io.Serializable;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class KeySearchListObj  implements Serializable {

    // 1. Đơn vị CSGT (Loại file)
    private Long idCauHinhBaoCao;
    private String tenDonViBC;
    private String tenFileDinhKem;




    // 1. Đơn vị CSGT (Mã báo cáo)
    private String reportCode;

    // 1. Đơn vị CSGT (Loại file)
    private String fileType;

    // 1. Đơn vị CSGT (Chuỗi tham số)
    private String strParam;

    // 1. Đơn vị CSGT (Mã đơn vị CSGT được chọn)
    // @ApiModelProperty(value = "Đơn vị Cảnh sát giao thông")
    private String donVi;

    // 1. Đơn vị CSGT (Mã đơn vị trả giấy tờ)
    private String donViTraGiayTo;

    // 2. Từ ngày (thời gian lập báo cáo từ)
    private String tuNgay;

    // 3. Đến ngày (thời gian lập báo cáo đến)
    private String denNgay;

    // 4. Tên phương tiện (Tên loại phương tiện)
    private String phuongTien;

    // 5. Biển số (mã biển số)
    private String bienSo;

    // 6. TT-NĐ(Thông tư, nghị định) (Số thông tư,  nghị định)
    private String soThongTu;

    // 7. Nhóm hành vi vi phạm (Giá trị nhóm hành vi vi phạm)
    private String nhomHanhVi;

    // 8. Hành vi vi phạm (trả danh sách tìm kiếm theo các mã hành vi vi phạm trong bảng VBPL)
    private String hanhVi;

    // 9. Tên người vi phạm (Giá trị tên người vi phạm)
    private String nguoiViPham;

    // 11. Địa điểm vi phạm (Danh sách địa điểm vi phạm)
    private String diaDiem;

    // 12. Loại báo cáo (Loại báo cáo 1 trong 3: Tất cả, các đơn vị CSGT có dữ liệu và ko dữ liệu)
    private String loai;

    // 14. Trạng thái (Trạng thái biên bản quyết định)
    private String trangThai;

    // 15. Tuyến đường (Danh sách tuyến đường được chọn)
    private String tuyenDuong;

    // 16. Địa bàn vi phạm (Danh sách địa bàn vi phạm)
    private String diaBan;

    // 17. Tên người dùng (Tên người dùng đăng nhập trong hệ thống)
    private String username;

    // 18. Số (Mã của Tang vật, giấy tờ)
    private String tangVat;

    // 20. Tạm giữ từ ngày (Thời gian tạm giữ từ ngày)
    private String ngayTamGiuTu;

    // 21. Tạm giữ đến ngày (Thời gian tạm giữ đến ngày)
    private String ngayTamGiuDen;

    // 22. Tước từ ngày (Thời gian tước giấy tờ từ ngày)
    private String ngayTuocGiayToTu;

    // 23. Tước đến ngày (Thời gian tước giấy tờ đến ngày)
    private String ngayTuocGiayToDen;

    // 24. Đơn vị trả giấy tờ (Đơn vị CSGT thực hiện trả giấy tờ)
    // 25. Nghề nghiệp người vi phạm (Nghề nghiệp của người vi phạm)
    private String ngheNghiep;

    // 26. Khoảng thời gian vi phạm từ (Thời gian vi phạm từ ngày)
    private String ngayViPhamTu;

    // 27. Khoảng thời gian vi phạm đến (Thời gian vi phạm đến ngày)
    private String ngayViPhamDen;

    // 28. Độ tuổi người vi phạm từ (Độ tuổi vi phạm từ tuổi)
    private String doTuoiTu;

    // 29. Độ tuổi người vi phạm đến (Độ tuổi vi phạm đến tuổi)
    private String doTuoiDen;

    // 30. Loại tang vật, phương tiện, giấy tờ (Chọn 1 trong 3: tất cả, phương tiện, giấy tờ)
    // 31. Lĩnh vực (chọn 1 hoặc nhiều giá trị Lĩnh vực CSGT: 981, 982, 983)
    private String linhVuc;

    // Hình thức sử phạt
    private String hinhThuc;

    // Hình thức sử phạt
    private String hinhThucXpbs;
    private String loaiDoiSoat;
    private String loaiDongBo;

    // Từ ngày Xử Ly
    private String tuNgayXuLy;

    // Đến ngày Xử Lý
    private String denNgayXuLy;

    // Màu biển
    private String mauBien;

    // Số căn cước công dân
    private String soCccd;

    // Số chứng nhận đăng ký hoặc mã doanh nghiệp
    private String soCndkMaDn;

    // Số CMT/CCCD/Hộ chiếu
    private String soDinhDanh;

    //Số GCN/MÃ số DN
    private String maSoDN;

    // Loại quyết định
    private String loaiQuyetDinh;

    // 32. Đơn vị công an báo cáo
    private String donViCA;

}
