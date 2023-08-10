package com.example.untiled1.domain.sdNestedClass;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BcBieuDoSoSanhCacTinhResService {

    public BcBieuDoSoSanhCacTinhRes searchBcBieuDo(String objInput) {
        BcBieuDoSoSanhCacTinhRes response = new BcBieuDoSoSanhCacTinhRes();
//        List<BcBieuDoSoSanhCacTinhRes.DataBieuDo> dataBieuDo = reportRepo.bcBieuDoSoSanhCacTinh(objInput);
        List<BcBieuDoSoSanhCacTinhRes.DataBieuDo> dataBieuDo = new ArrayList<>();

        // Set array tên các tỉnh
        Set<String> arrTenTinh = dataBieuDo.stream()
                .map(BcBieuDoSoSanhCacTinhRes.DataBieuDo::getTenTinh)
                .collect(Collectors.toSet());
        response.setLstLegend(new ArrayList<>(arrTenTinh));

        // set data biểu đồ theo loại Tuần/ Tháng
        response.setDataBieuDo(dataBieuDo);

        // Nếu loạiBc là Quý thì set data biểu đồ theo Quý
//        if (objInput.getLoaiBaoCao().equals("Q") && !CheckEx.getInstance().checkJavaUtilListIsEmpty(response.getDataBieuDo())) {

            List<String> lstLegend = Lists.newArrayList("Quý 1", "Quý 2", "Quý 3", "Quý 4");
            response.setLstQuarter(lstLegend);

            List<BcBieuDoSoSanhCacTinhRes.QuyDto> lstSeries = new ArrayList<>();

            Map<String, List<BcBieuDoSoSanhCacTinhRes.DataBieuDo>> quarter = dataBieuDo.stream()
                    .collect(Collectors.groupingBy(BcBieuDoSoSanhCacTinhRes.DataBieuDo::getLoaiBaoCao));
            for (String l : lstLegend) {
                List<Long> lstData = new ArrayList<>();
                if (quarter.containsKey(l)) {
                    Map<String, List<BcBieuDoSoSanhCacTinhRes.DataBieuDo>> tenTinhGroup = quarter.get(l).stream()
                            .collect(Collectors.groupingBy(BcBieuDoSoSanhCacTinhRes.DataBieuDo::getTenTinh));
                    for (var t : arrTenTinh) {
                        if (tenTinhGroup.containsKey(t)) {
                            lstData.add(tenTinhGroup.get(t).get(0).getTong());
                        } else {
                            lstData.add(0L);
                        }
                    }
                } else {
                    for (var ignored : arrTenTinh) {
                        lstData.add(0L);
                    }
                }
                lstSeries.add(new BcBieuDoSoSanhCacTinhRes.QuyDto(l, lstData));
            }
            // Set biểu đồ quý
            response.setQuarterData(lstSeries);
//        }

        return response;
    }
}
