package entity;

import java.util.Objects;

public class NhanVienEntity {
	private String	maNV;
	private String	hoNV;
	private String	tenNV;
	private String	phaiNV;
	private int tuoiNV;
	private String phongBanNV;
	private Double tienLuongNV;
	
	public NhanVienEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NhanVienEntity(String maNV, String hoNV, String tenNV, String phaiNV, int tuoiNV, String phongBanNV,
			Double tienLuongNV) {
		super();
		this.maNV = maNV;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.phaiNV = phaiNV;
		this.tuoiNV = tuoiNV;
		this.phongBanNV = phongBanNV;
		this.tienLuongNV = tienLuongNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
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

	public String getPhaiNV() {
		return phaiNV;
	}

	public void setPhaiNV(String phaiNV) {
		this.phaiNV = phaiNV;
	}

	public int getTuoiNV() {
		return tuoiNV;
	}

	public void setTuoiNV(int tuoiNV) {
		this.tuoiNV = tuoiNV;
	}

	public String getPhongBanNV() {
		return phongBanNV;
	}

	public void setPhongBanNV(String phongBanNV) {
		this.phongBanNV = phongBanNV;
	}

	public Double getTienLuongNV() {
		return tienLuongNV;
	}

	public void setTienLuongNV(Double tienLuongNV) {
		this.tienLuongNV = tienLuongNV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hoNV, maNV, phaiNV, phongBanNV, tenNV, tienLuongNV, tuoiNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVienEntity other = (NhanVienEntity) obj;
		return Objects.equals(hoNV, other.hoNV) && Objects.equals(maNV, other.maNV)
				&& Objects.equals(phaiNV, other.phaiNV) && Objects.equals(phongBanNV, other.phongBanNV)
				&& Objects.equals(tenNV, other.tenNV) && Objects.equals(tienLuongNV, other.tienLuongNV)
				&& tuoiNV == other.tuoiNV;
	}

	@Override
	public String toString() {
		return "NhanVienEntity [maNV=" + maNV + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", phaiNV=" + phaiNV
				+ ", tuoiNV=" + tuoiNV + ", phongBanNV=" + phongBanNV + ", tienLuongNV=" + tienLuongNV + "]";
	}
	
	
}
