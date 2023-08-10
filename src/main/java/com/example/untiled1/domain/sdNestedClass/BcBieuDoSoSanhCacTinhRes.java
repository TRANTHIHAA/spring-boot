package com.example.untiled1.domain.sdNestedClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BcBieuDoSoSanhCacTinhRes {
    List<String> lstLegend;
    List<String> lstQuarter;
    List<DataBieuDo> dataBieuDo;
    List<QuyDto> quarterData;
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class QuyDto {
        private String tenQuy;
        private List<Long> data = new ArrayList<>();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class DataBieuDo {
        private String tenTinh;

        private String loaiBaoCao;

        // loai Bao cao viet tat Tuần(value: WW), Tháng(value: MM), Quý (value: Q)
        private String loaiBc;

        private Long tong;
    }
}
