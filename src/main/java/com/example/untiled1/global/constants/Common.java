package com.example.untiled1.global.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;

public class Common {
    private Common() {
    }

    public static final String PATH_TO_DTO = "etc.bca.c08.xlvp.domain.bbqd.dto.noidung";
    public static final String PATH_TO_QD_DTO = ".quyetdinh.";
    public static final String PATH_TO_BB_DTO = ".bienban.";

    @Getter
    public enum GenderEnum {
        OTHER(0L, "Khác"),
        MALE(1L, "Nam"),
        FEMALE(2L, "Nữ");

        private final Long value;
        private final String name;

        GenderEnum(Long value, String name) {
            this.value = value;
            this.name = name;
        }

        public static GenderEnum fromValue(Long value) {
            for (GenderEnum b : GenderEnum.values()) {
                if (Objects.equals(b.value, value)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LinhVucGiaoThongEnum {
        DUONG_BO(981, "Đường bộ"),
        DUONG_SAT(982, "Đường sắt"),
        DUONG_THUY(983, "Đường thủy");

        private final int value;
        private final String name;

        LinhVucGiaoThongEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LinhVucGiaoThongEnum fromValue(int value) {
            for (LinhVucGiaoThongEnum b : LinhVucGiaoThongEnum.values()) {
                if (b.value == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiBienBanQuyetDinhEnum {
        BIEN_BAN(1, "Biên bản"),
        QUYET_DINH(2, "Quyết định");

        private final int value;
        private final String name;

        LoaiBienBanQuyetDinhEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LoaiBienBanQuyetDinhEnum fromValue(int value) {
            for (LoaiBienBanQuyetDinhEnum b : LoaiBienBanQuyetDinhEnum.values()) {
                if (b.value == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum NguonBienBanQuyetDinhEnum {
        BBQD(1, "Biên bản/Quyết định"),
        VU_VIEC(2, "Vụ Việc"),
        TAO_BU(3, "CBCS/UBND");

        private final int value;
        private final String name;

        NguonBienBanQuyetDinhEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static NguonBienBanQuyetDinhEnum fromValue(String name) {
            for (NguonBienBanQuyetDinhEnum b : NguonBienBanQuyetDinhEnum.values()) {
                if (b.name.equalsIgnoreCase(name)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiNgaySinhEnum {
        DATE("dd/MM/yyyy"),
        MONTH("MM/yyyy"),
        YEAR("yyyy");

        private final String format;

        LoaiNgaySinhEnum(String format) {
            this.format = format;
        }

        public static LoaiNgaySinhEnum of(String loai) {
            try {
                return LoaiNgaySinhEnum.valueOf(loai);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    @Getter
    public enum TrangThaiBieuMauEnum {

        DA_LUU("01", "Đã lưu"),
        DA_IN("02", "Đã in"),
        DA_RA_BB_QD("03", "Đã ra BB/QĐ"),
        LUU_LICH_SU("04", "Lưu lịch sử "),
        CHO_PHE_DUYET("05", "Chờ phê duyệt"),
        DA_PHE_DUYET("06", "Đã phê duyệt"),
        DA_GUI_SMS("07", "Đã gửi SMS"),
        DA_THANH_TOAN("08", "Đã thanh toán trực tiếp"),
        DA_KET_THUC("09", "Đã kết thúc"),
        DA_CHAM_DUT("10", "Đã chấm dứt"),
        DA_THANH_TOAN_DVC("11", "Đã thanh toán qua dịch vụ công"),
        DA_THANH_TOAN_BUU_DIEN("12", "Đã thanh toán qua bưu điện"),

        DA_DONG_DAU("13", "Đã đóng dấu"),
        CHO_GO_PHE_DUYET("14", "Chờ gỡ phê duyệt");

        private final String value;
        private final String name;

        TrangThaiBieuMauEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static TrangThaiBieuMauEnum fromValue(String text) {
            for (TrangThaiBieuMauEnum b : TrangThaiBieuMauEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum TrangThaiGiaoQuyenEnum {

        DA_LUU("01", "Đã lưu"),
        DA_IN("02", "Đã in"),
        DA_PHE_DUYET("03", "Đã phê duyệt"),
        LUU_LICH_SU("04", "Lưu lịch sử"),
        DA_CHAM_DUT("05", "Đã chấm dứt"),
        DA_KET_THUC("06", "Đã kết thúc");

        private final String value;
        private final String name;

        TrangThaiGiaoQuyenEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static TrangThaiGiaoQuyenEnum fromValue(String text) {
            for (TrangThaiGiaoQuyenEnum b : TrangThaiGiaoQuyenEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    public static class Separator {
        private Separator() {
        }

        public static final String VERTICAL_BAR = "|";
        public static final String COMMA = ",";
    }

    @Getter
    public enum TrangThaiVuViecEnum {

        DA_LUU("01", "Đã lưu"),
        DA_KHOI_TAO("02", "Đã khởi tạo"),
        LUU_LICH_SU("03", "Lưu lịch sử "),
        DA_PHE_DUYET("04", "Đã phê duyệt");

        private final String value;
        private final String name;

        TrangThaiVuViecEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static TrangThaiVuViecEnum fromValue(String text) {
            for (TrangThaiVuViecEnum b : TrangThaiVuViecEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiHinhThucXuPhatChinhEnum {
        PHAT_TIEN("PHAT_TIEN", "Phạt tiền"),
        CANH_CAO("CANH_CAO", "Cảnh cáo"),
        PHAT_TIEN_TRE_NHO("PHAT_TIEN_TRE_NHO", "Phạt tiền 1/2 đối với người từ 16 đến dưới 18 tuổi");

        private final String value;
        private final String name;

        LoaiHinhThucXuPhatChinhEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

    }

    @Getter
    public enum NhomGiayToEnum {
        PHUONG_TIEN("PHUONG_TIEN", "Phương tiện", "TangVatPhuongTien", "HtxpPhuongTien"),
        GIAY_TO("GIAY_TO", "Giấy tờ", "TangVatGiayTo", "HtxpGiayTo"),
        GIAY_PHEP_LAI_XE("GIAY_PHEP_LAI_XE", "Giấy phép lái xe", "TangVatGPLX", "HtxpGiayPhepLaiXe"),
        KHAC("KHAC", "Khác", "TangVatKhac", "HtxpKhac");

        private final String value;
        private final String name;
        private final String commonClass;
        private final String hinhThucXuPhatClass;

        NhomGiayToEnum(String value, String name, String commonClass, String xuPhatClass) {
            this.value = value;
            this.name = name;
            this.commonClass = commonClass;
            this.hinhThucXuPhatClass = xuPhatClass;
        }

        public static NhomGiayToEnum fromValue(String text) {
            for (NhomGiayToEnum b : NhomGiayToEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }

    }

    @Getter
    public enum LoaiHinhThucSuaDoiEnum {
        SUA_DOI_BO_SUNG,
        THAY_THE
    }

    @Getter
    public enum LoaiHinhTdQlEnum {
        TUYEN_DUONG(1, "Tuyến đường"),
        QUOC_LO(2, "Quốc lộ");

        private final Integer value;
        private final String name;

        LoaiHinhTdQlEnum(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LoaiHinhTdQlEnum fromValue(Integer value) {
            for (LoaiHinhTdQlEnum b : LoaiHinhTdQlEnum.values()) {
                if (value.equals(b.getValue())) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiNoiGiuEnum {
        CUA_DON_VI(1, "Nơi tạm giữ của đơn vị"),
        DI_THUE(2, "Nơi tạm giữ đi thuê");

        private final Integer value;
        private final String name;

        LoaiNoiGiuEnum(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LoaiNoiGiuEnum fromValue(Integer value) {
            for (LoaiNoiGiuEnum b : LoaiNoiGiuEnum.values()) {
                if (value.equals(b.getValue())) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiDoiTuongViPhamEnum {

        CA_NHAN(0, "Cá nhân"),
        TO_CHUC(1, "Tổ chức");

        private final int value;
        private final String name;

        LoaiDoiTuongViPhamEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LoaiDoiTuongViPhamEnum fromValue(int value) {
            for (LoaiDoiTuongViPhamEnum b : LoaiDoiTuongViPhamEnum.values()) {
                if (b.getValue() == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiDoiTuongChungKienEnum {

        CA_NHAN(0, "Cá nhân", "CaNhanChungKien"),
        DAI_DIEN_CQ(1, "Đại diện chính quyền", "DaiDienCQChungKien"),
        TO_CHUC(2, "Tổ chức", "DaiDienCQChungKien");
        private final int value;
        private final String name;
        private final String thongTinClass;

        LoaiDoiTuongChungKienEnum(int value, String name, String thongTinClass) {
            this.value = value;
            this.name = name;
            this.thongTinClass = thongTinClass;
        }

        public static LoaiDoiTuongChungKienEnum fromValue(int value) {
            for (LoaiDoiTuongChungKienEnum b : LoaiDoiTuongChungKienEnum.values()) {
                if (b.getValue() == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiNoiDungEnum {
        THAY_DOI("THAY_DOI", "Sửa đổi, bổ sung"),
        HUY_BO("HUY_BO", "Hủy bỏ");

        private final String value;
        private final String name;

        LoaiNoiDungEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LoaiNoiDungEnum fromValue(String value) {
            for (LoaiNoiDungEnum loai : LoaiNoiDungEnum.values()) {
                if (loai.getValue().equalsIgnoreCase(value)) {
                    return loai;
                }
            }
            return null;
        }
    }

    @Getter
    public enum CanCuEnum {
        TTND_NHAP_TAY(1, "Căn cứ thông tư nghị định nhập tay", "ttndNhapTay"),
        BO_SUNG(2, "Căn cứ bổ sung nhập tay", "boSung"),
        BBQD(3, "Căn cứ theo các BBQĐ khác", "bbqd"),
        QD_GIAO_QUYEN(4, "Căn cứ QĐ giao quyền", "qdGiaoQuyen"),
        MA_CAN_CU(5, "Căn cứ chọn từ danh mục căn cứ", "maCanCu"),
        MA_TTND(6, "Căn cứ thông tư nghị định chọn từ dropdown list", "maTTND");

        private final int value;
        private final String name;
        private final String fieldName;

        CanCuEnum(int value, String name, String fieldName) {
            this.value = value;
            this.name = name;
            this.fieldName = fieldName;
        }

        public static CanCuEnum fromValue(int value) {
            for (CanCuEnum b : CanCuEnum.values()) {
                if (b.getValue() == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum BaseExistedEnum {
        MA_VU_VIEC("VU_VIEC", "MA"),
        MA_TANG_VAT("DANH_MUC_TANG_VAT_PHUONG_TIEN_GIAY_TO", "MA"),
        MA_MAU_BB_QD("DANH_MUC_MAU_BIEN_BAN_QUYET_DINH", "MA"),
        MA_NGHE_NGHIEP("DANH_MUC_NGHE_NGHIEP", "MA"),
        MA_CAN_CU("CAN_CU", "MA_CAN_CU"),
        MA_TTND("THONG_TU_NGHI_DINH", "MA"),
        HANH_VI_VI_PHAM("DANH_MUC_HANH_VI_VI_PHAM", "MA"),
        BB_QD("BIEN_BAN_QUYET_DINH", "MA"),
        QD_GIAO_QUYEN("GIAO_QUYEN", "MA"),
        MA_VBPL("DANH_MUC_VAN_BAN_PHAP_LUAT", "MA"),
        NHOM_HANH_VI_VI_PHAM("DANH_MUC_NHOM_HANH_VI_VI_PHAM", "MA"),
        HINH_THUC_XU_PHAT("HINH_THUC_XU_PHAT", "MA"),
        DIEM_TAM_GIU("NOI_TAM_GIU", "MA"),
        KY_HIEU_TTND("THONG_TU_NGHI_DINH", "KY_HIEU"),
        THONG_BAO("THONG_BAO", "MA"),
        NOI_CAP_GIAY_TO("NOI_CAP_GIAY_TO", "MA"),
        DM_TANG_VAT_PT_GT("DANH_MUC_TANG_VAT_PHUONG_TIEN_GIAY_TO", "MA");

        private final String tableName;
        private final String colName;

        BaseExistedEnum(String tableName, String colName) {
            this.tableName = tableName;
            this.colName = colName;
        }
    }

    @Getter
    public enum LyDoEnum {
        XAC_MINH(0, "Xác minh được tình tiết làm căn cứ quyết định xử phạt"),
        HANH_VI(1, "Hành vi vi phạm không còn gây nguy hiểm cho xã hội"),
        QUYET_DINH(2, "Quyết định xử phạt được thi hành");

        private final int value;
        private final String name;

        LyDoEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static LyDoEnum fromValue(int value) {
            for (LyDoEnum b : LyDoEnum.values()) {
                if (b.getValue() == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum DonViThoiGianEnum {
        NGAY("NGAY", "Ngày"),
        THANG("THANG", "Tháng"),
        NAM("NAM", "Năm");

        private final String value;
        private final String name;

        DonViThoiGianEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static DonViThoiGianEnum fromValue(String text) {
            for (DonViThoiGianEnum b : DonViThoiGianEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LyDoTamGiuEnum {
        XAC_MINH("XAC_MINH", "Để xác minh tình tiết mà nếu không tạm giữ thì không có căn cứ ra quyết định xử phạt"),
        NGAN_CHAN("NGAN_CHAN", "Để ngăn chặn hành vi vi phạm hành chính mà nếu không tạm giữ thì sẽ gây hậu quả nghiêm trọng cho xã hội"),
        DAM_BAO("DAM_BAO", "Để đảm bảo thi hành quyết định xử phạt");

        private final String value;
        private final String name;

        LyDoTamGiuEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum LoaiHtxpEnum {
        CHINH("CHINH", "Hình thức xử phạt chính"),
        BO_SUNG("BO_SUNG", "Hình thức xử phạt bổ sung");

        private final String value;
        private final String name;

        LoaiHtxpEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum LoaiHtxpBoSungEnum {
        TUOC("TUOC", "Tước"),
        DINH_CHI("DINH_CHI", "Đình chỉ"),
        TICH_THU("TICH_THU", "Tịch thu");

        private final String value;
        private final String name;

        LoaiHtxpBoSungEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum LoaiThongBaoEnum {
        TUOC("tuoc", "Thông báo tước", "TbTuoc"),
        XU_PHAT_VPHC("xu-phat-vphc", "Thông báo xử phạt VPHC", "TbXuPhatVphc");

        private final String value;
        private final String name;
        private final String classSuffix;

        LoaiThongBaoEnum(String value, String name, String classSuffix) {
            this.value = value;
            this.name = name;
            this.classSuffix = classSuffix;
        }

        public static LoaiThongBaoEnum fromValue(String text) {
            for (LoaiThongBaoEnum b : LoaiThongBaoEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum HinhThucGiaiTrinhEnum {
        TRUC_TIEP("TRUC_TIEP", "Giải trình trực tiếp", 2),
        VAN_BAN("VAN_BAN", "Giải trình bằng văn bản", 5);

        private final String value;
        private final String name;
        private final int thoiHan;

        HinhThucGiaiTrinhEnum(String value, String name, int thoiHan) {
            this.value = value;
            this.name = name;
            this.thoiHan = thoiHan;
        }
    }

    @Getter
    public enum QuyetDinhGiaoQuyenEnum {
        QD34A("118-2022-nd-cp-qd34a", "QuyetDinh34aDto"),
        QD34B("118-2022-nd-cp-qd34b", "QuyetDinh34bDto"),
        QD35("118-2022-nd-cp-qd35", "QuyetDinh35Dto"),
        QD36("118-2022-nd-cp-qd36", "QuyetDinh36Dto"),
        QD37("118-2022-nd-cp-qd37", "QuyetDinh37Dto");
        private final String value;
        private final String name;

        QuyetDinhGiaoQuyenEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static QuyetDinhGiaoQuyenEnum fromValue(String text) {
            for (QuyetDinhGiaoQuyenEnum b : QuyetDinhGiaoQuyenEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum TrangThaiTangVatEnum {
        TAM_GIU("TAM_GIU", "Đang tạm giữ", true),
        TUOC("TUOC", "Đang tước", true),
        DINH_CHI("DINH_CHI", "Đình chỉ", true),
        TICH_THU("TICH_THU", "Đang tịch thu", true),
        DA_TRA("DA_TRA", "Đã trả", false),
        HET_HAN("HET_HAN", "Hết hạn", true),
        TON_DONG("TON_DONG", "Tồn đọng", true);;

        private final String value;
        private final String name;
        private final Boolean isChuaTra;

        TrangThaiTangVatEnum(String value, String name, Boolean isChuaTra) {
            this.value = value;
            this.name = name;
            this.isChuaTra = isChuaTra;
        }

        public static TrangThaiTangVatEnum fromValue(String value) {
            for (TrangThaiTangVatEnum b : TrangThaiTangVatEnum.values()) {
                if (b.value.equalsIgnoreCase(value)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum DonViCsgtEnum {
        CAP_CUC(1, "Cục"),
        CAP_TINH_THANH_PHO(1, "Tỉnh/ Thành phố"),
        CAP_QUAN_HUYEN(2, "Quận/ Huyện"),
        CAP_PHONG(2, "Phòng"),
        CAP_DOI(3, "Đội"),
        CAP_XA(3, "Xã/ Phường");

        private final int value;
        private final String name;

        DonViCsgtEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

    }

    @Getter
    public enum NguonTaoVuViecEnum {
        PHAT_NGUOI,
        MOBILE_APP;
    }

    @Getter
    public enum CapHanhChinhEnum {

        CAP_TINH_THANH_PHO(1, "Tỉnh/ Thành phố"),
        CAP_QUAN_HUYEN_THI_XA(2, "Quận/ Huyện/ Thị xã"),
        CAP_XA_PHUONG_THI_TRAN(3, "Xã/ Phường/ Thị trấn");

        private final int value;
        private final String name;

        CapHanhChinhEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static CapHanhChinhEnum fromValue(int value) {
            for (CapHanhChinhEnum b : CapHanhChinhEnum.values()) {
                if (b.getValue() == value) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum TrangThaiThanhToanEnum {
        CHUA_THANH_TOAN("01", "Chưa thanh toán"),
        DA_THANH_TOAN("02", "Đã thanh toán");

        private final String value;
        private final String name;

        TrangThaiThanhToanEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum HinhThucNopPhatEnum {
        DVC("DVC", "Nộp phạt qua dịch vụ công"),
        TRUC_TIEP("TRUC_TIEP", "Nộp phạt trực tiếp"),
        BUU_DIEN("BUU_DIEN", "Nộp phạt qua bưu điện");

        private final String value;
        private final String name;

        HinhThucNopPhatEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

    }

    @Getter
    public enum LoaiKham {
        PHUONG_TIEN("PHUONG_TIEN", "Phương tiện vận tải"),
        DO_VAT("DO_VAT", "Đồ vật");

        private final String value;
        private final String name;

        LoaiKham(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum LoaiThongTinXacMinhEnum {
        TIEN_LUONG("ThuNhap", 1, "tiền lương, thu nhập"),
        THU_NHAP("ThuNhap", 2, "tiền lương, thu nhập"),
        TAI_KHOAN("TaiKhoan", 3, "tài khoản"),
        TAI_SAN("TaiSan", 4, "tài sản"),
        TIEN_BEN_THU_3("TienBenThu3", 5, "tiền, tài sản"),
        TAI_SAN_BEN_THU_3("TaiSanBenThu3", 5, "tiền, tài sản");

        private final String commonClass;
        private final int value;
        private final String name;

        LoaiThongTinXacMinhEnum(String commonClass, int value, String name) {
            this.commonClass = commonClass;
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum LoaiKhauTruEnum {
        TIEN_LUONG(1, "một phần lương"),
        THU_NHAP(2, "một phần thu nhập"),
        TAI_KHOAN(3, "tiền từ tài khoản");

        private final int value;
        private final String name;

        LoaiKhauTruEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum DoiTuongGiamHoEnum {
        CHA("CHA", "Cha"),
        ME("ME", "Mẹ"),
        NGUOI_GIAM_HO("NGUOI_GIAM_HO", "Người giám hộ");

        private final String value;
        private final String name;

        DoiTuongGiamHoEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static DoiTuongGiamHoEnum fromValue(@NotEmpty String value) {
            for (DoiTuongGiamHoEnum b : DoiTuongGiamHoEnum.values()) {
                if (b.getValue().equals(value)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum DonViThoiGianGioPhutEnum {
        GIO("GIO", "Giờ"),
        PHUT("PHUT", "Phút");

        private final String value;
        private final String name;

        DonViThoiGianGioPhutEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static DonViThoiGianGioPhutEnum fromValue(@NotEmpty String value) {
            for (DonViThoiGianGioPhutEnum b : DonViThoiGianGioPhutEnum.values()) {
                if (b.getValue().equals(value)) {
                    return b;
                }
            }
            return null;
        }

    }

    @Getter
    public enum LoaiKySoQuyetDinh {
        KY_SO_HANG_LOAT("01", "Ký số hàng loạt"),
        KY_SO_HSM("02", "Ký số hsm");

        private final String value;
        private final String name;

        LoaiKySoQuyetDinh(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum StatusEnum {
        INACTIVE(0, "Không kích hoạt"),
        ACTIVE(1, "Kích hoạt"),
        SUCCESS(2, "Thành công"),
        FAILED(3, "Thất bại");

        private final Integer value;
        private final String name;

        StatusEnum(Integer value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    // ThangNM
    @AllArgsConstructor
    @Getter
    public enum TrangThaiChuyenPhatEnum {

        CHUA_CHUYEN_PHAT("0", "Chưa chuyển phát"), //
        DA_CHUYEN_PHAT("1", "Đã chuyển phát"), //
        //
        ;

        private final String value;
        private final String name;

        public static TrangThaiChuyenPhatEnum fromValue(String text) {
            for (TrangThaiChuyenPhatEnum b : TrangThaiChuyenPhatEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    // Loại miễn giảm tiền phạt
    @AllArgsConstructor
    @Getter
    public enum LoaiMienGiamEnum {

        GIAM("GIAM", "Giảm tiền phạt vi phạm hành chính", "QĐ-GTP", "giảm"),
        MIEN_PHAN_CON_LAI("MIEN_PHAN_CON_LAI", "Miễn phần còn lại tiền phạt vi phạm hành chính", "QĐ-MTP", "miễn phần còn lại"),
        MIEN_TOAN_BO("MIEN_TOAN_BO", "Miễn toàn bộ tiền phạt vi phạm hành chính", "QĐ-MTP", "miễn toàn bộ");

        private final String value;
        private final String name;
        private final String kyHieuQd;
        private final String thucHien;

        public static LoaiMienGiamEnum fromValue(String text) {
            for (LoaiMienGiamEnum b : LoaiMienGiamEnum.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Getter
    public enum LoaiDanhMucEnum {
        CAN_CU("Danh mục căn cứ", "DongBoCanCu"),
        HANH_VI_VI_PHAM("Danh mục hành vi vi phạm", "DongBoHanhViViPham"),
        HINH_THUC_XU_PHAT("Danh mục hình thức xử phạt", "DongBoHinhThucXuPhat"),
        LOAI_GIAY_TO("Danh mục loại giấy tờ", "DongBoLoaiGiayTo"),
        LY_DO_TANG_GIAN("Danh mục lý do tăng giảm", "DongBoLyDoTangGiam"),
        MAU_BIEN_BAN_QUYET_DINH("Danh mục mẫu biên bản quyết định", "DongBoMauBBQD"),
        NGHE_NGHIEP("Danh mục nghề nghiệp", "DongBoNgheNghiep"),
        NHOM_HANH_VI_VI_PHAM("Danh mục nhóm hành vi vi phạm", "DongBoNhomHanhViViPham"),
        NOI_CAP_GIAY_TO("Danh mục nơi cấp giấy tờ", "DongBoNoiCapGiayTo"),
        NOI_TAM_GIU("Danh mục nơi tạm giữ", "DongBoNoiTamGiu"),
        THONG_TU_NGHI_DINH("Danh mục thông tư nghị định", "DongBoThongTuNghiDinh"),
        TUYEN_DUONG_QUOC_LO("Danh mục tuyến đường quốc lộ", "DongBoTuyenDuongQuocLo"),
        VAN_BAN_PHAP_LUAT("Danh mục văn bản pháp luật", "DongBoVanBanPhapLuat");

        private final String value;
        private final String name;

        LoaiDanhMucEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    @Getter
    public enum ActionEnum {
        CREATE("Tạo mới"),
        UPDATE("Sửa"),
        DELETE("Xóa");

        private final String name;

        ActionEnum(String name) {
            this.name = name;
        }
    }

    @Getter
    public enum NguonDongBo {
        DVC("dvc", "dịch vụ công");

        private final String path;
        private final String name;

        NguonDongBo(String path, String name) {
            this.path = path;
            this.name = name;
        }
    }

    @Getter
    public enum LoaiBbQdNoiDungChiTiet {
        HVVP("HVVP", "hành vi vi phạm"),
        HPBS("HPBS", "hinh phạt bổ sung");

        private final String value;
        private final String name;

        LoaiBbQdNoiDungChiTiet(String name, String value) {
            this.name = name;
            this.value = value;
        }

    }

    @Getter
    public enum NguonBbQdEnum {
        TAO_BU,
        MOBILE_APP;
    }

    @Getter
    public enum PhuongThucTraGiayTo {
        CHUA_DANG_KY("TRUC_TIEP", "Trả giấy tờ tại đơn vị"), //
        DA_DANG_KY("BUU_DIEN", "Chuyển phát nhanh");

        private final String value;
        private final String name;

        PhuongThucTraGiayTo(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public static PhuongThucTraGiayTo of(String value) {
            try {
                return PhuongThucTraGiayTo.valueOf(value);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum LoaiThaoTacEnum {
        CREATE("Thêm mới"),
        DELETE("Xóa"),
        UPDATE("Chỉnh sửa"),
        UPDATE_STATUS("Chuyển trạng thái");

        private final String name;

        public static String getName(String loai) {
            for (LoaiThaoTacEnum b : LoaiThaoTacEnum.values()) {
                if (b.toString().equalsIgnoreCase(loai)) {
                    return b.getName();
                }
            }
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum SignatureTypeEnum {
        VAN_THU("Chứ ký của văn thư"),
        LANH_DAO("Chứ ký của lãnh đạo");

        private final String name;
    }

    @Getter
    @AllArgsConstructor
    public enum LoaiGoPheDuyetEnum {
        DE_XUAT("Đề xuất"),
        XAC_NHAN("Xác nhận");

        private final String name;
    }
}

