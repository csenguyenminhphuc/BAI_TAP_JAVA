package BaiTapJTabbedPane;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
import java.io.Serializable;
import java.util.Objects;

public class NhanVienEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String maNV;
	private String hoNV;
	private String tenNV;
	private int tuoi;
	private Boolean phai;
	private PhongBanEntity phongBan;
	private Double tienLuong;
	
	public NhanVienEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVienEntity(String maNV, String hoNV, String tenNV, int tuoi, Boolean phai, PhongBanEntity phongBan,
			Double tienLuong) {
		super();
		this.maNV = maNV;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.tuoi = tuoi;
		this.phai = phai;
		this.phongBan = phongBan;
		this.tienLuong = tienLuong;
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

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public Boolean getPhai() {
		return phai;
	}

	public void setPhai(Boolean phai) {
		this.phai = phai;
	}

	public PhongBanEntity getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBanEntity phongBan) {
		this.phongBan = phongBan;
	}
	
	public Double getTienLuong() {
		return tienLuong;
	}
	
	public void setTienLuong(Double tienLuong) {
		this.tienLuong = tienLuong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(hoNV, maNV, phai, phongBan, tenNV, tuoi, tienLuong);
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
		return Objects.equals(hoNV, other.hoNV) && Objects.equals(maNV, other.maNV) && Objects.equals(phai, other.phai)
				&& Objects.equals(phongBan, other.phongBan) && Objects.equals(tenNV, other.tenNV) && tuoi == other.tuoi && Objects.equals(tienLuong, other.tienLuong);
	}

	@Override
	public String toString() {
		return "NhanVienEntity [maNV=" + maNV + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", tuoi=" + tuoi + ", phai="
				+ phai + ", phongBan=" + phongBan + " , tienLuong=" + tienLuong + "]";
	}
	
	
}
