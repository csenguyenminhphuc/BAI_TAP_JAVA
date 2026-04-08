package BaiTapJTabbedPane;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */

public enum PhongBanEntity {
    PHONG_TO_CHUC("Phòng tổ chức"),
    PHONG_KY_THUAT("Phòng kỹ thuật"),
    PHONG_NHAN_SU("Phòng nhân sự"),
    PHONG_TAI_VU("Phòng tài vụ"),
	PHONG_KINH_DOANH("Phòng kinh doanh");

    private final String tenPhong;

    PhongBanEntity(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    @Override
    public String toString() {
        return tenPhong;
    }
}