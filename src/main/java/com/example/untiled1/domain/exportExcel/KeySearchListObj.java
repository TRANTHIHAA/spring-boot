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

    // 1. Đơn vị CSGT (Mã báo cáo)
    private String reportCode;

    // 1. Đơn vị CSGT (Loại file)
    private String fileType;

    // 1. Đơn vị CSGT (Chuỗi tham số)
    private String strParam;

    // 1. Đơn vị CSGT (Mã đơn vị CSGT được chọn)
    // @ApiModelProperty(value = "Đơn vị Cảnh sát giao thông")
    private String hoTen;

    // 1. Đơn vị CSGT (Mã đơn vị trả giấy tờ)
    private String phongBan;

    private String tenTaiKhoan;

    // 2. Từ ngày (thời gian lập báo cáo từ)
    private String tuNgay;

    // 3. Đến ngày (thời gian lập báo cáo đến)
    private String denNgay;
}
