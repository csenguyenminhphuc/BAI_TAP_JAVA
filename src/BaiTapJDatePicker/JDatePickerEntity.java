package BaiTapJDatePicker;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
import java.util.Objects;

public class JDatePickerEntity {
	private String maSoNv;
	private String hoNV;
	private String tenNV;
	private Boolean gioiTinhNV;
	private String ngaySinhNV;
	private Double tienLuongNV;
	
	public JDatePickerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JDatePickerEntity(String maSoNv, String hoNV, String tenNV, Boolean gioiTinhNV, String ngaySinhNV,
			Double tienLuongNV) {
		super();
		this.maSoNv = maSoNv;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.gioiTinhNV = gioiTinhNV;
		this.ngaySinhNV = ngaySinhNV;
		this.tienLuongNV = tienLuongNV;
	}

	public String getMaSoNv() {
		return maSoNv;
	}

	public void setMaSoNv(String maSoNv) {
		this.maSoNv = maSoNv;
	}

	public String getHoNV() {
		return hoNV;
	}

	public void setHoNV(String hoNV) {
		this.hoNV = hoNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public Boolean getGioiTinhNV() {
		return gioiTinhNV;
	}

	public void setGioiTinhNV(Boolean gioiTinhNV) {
		this.gioiTinhNV = gioiTinhNV;
	}

	public String getNgaySinhNV() {
		return ngaySinhNV;
	}

	public void setNgaySinhNV(String ngaySinhNV) {
		this.ngaySinhNV = ngaySinhNV;
	}

	public Double getTienLuongNV() {
		return tienLuongNV;
	}

	public void setTienLuongNV(Double tienLuongNV) {
		this.tienLuongNV = tienLuongNV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gioiTinhNV, hoNV, maSoNv, ngaySinhNV, tenNV, tienLuongNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JDatePickerEntity other = (JDatePickerEntity) obj;
		return Objects.equals(gioiTinhNV, other.gioiTinhNV) && Objects.equals(hoNV, other.hoNV)
				&& Objects.equals(maSoNv, other.maSoNv) && Objects.equals(ngaySinhNV, other.ngaySinhNV)
				&& Objects.equals(tenNV, other.tenNV) && Objects.equals(tienLuongNV, other.tienLuongNV);
	}

	@Override
	public String toString() {
		return "JDatePickerEntity [maSoNv=" + maSoNv + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", gioiTinhNV="
				+ gioiTinhNV + ", ngaySinhNV=" + ngaySinhNV + ", tienLuongNV=" + tienLuongNV + "]";
	}
	
	
}
