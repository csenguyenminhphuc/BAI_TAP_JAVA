package BaiTapJDatePicker;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDatePickerDao {
	private Connection con;
	
	public JDatePickerDao() {
		this.con = ConnectDB.getConnection();
	}
	
	// Lấy danh sách tất cả nhân viên từ database
	public ArrayList<JDatePickerEntity> getDanhSachNhanVien() {
		ArrayList<JDatePickerEntity> list = new ArrayList<>();
		String sql = "SELECT * FROM NhanVien";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JDatePickerEntity nv = new JDatePickerEntity(
					rs.getString("MaSo"),
					rs.getString("Ho"),
					rs.getString("TenNhanVien"),
					rs.getBoolean("Phai"),
					rs.getString("NgaySinh"),
					rs.getDouble("TienLuong")
				);
				list.add(nv);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// Thêm nhân viên vào database
	public boolean themNhanVien(JDatePickerEntity nv) {
		String sql = "INSERT INTO NhanVien (MaSo, Ho, TenNhanVien, Phai, NgaySinh, TienLuong) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getMaSoNv());
			ps.setString(2, nv.getHoNV());
			ps.setString(3, nv.getTenNV());
			ps.setBoolean(4, nv.getGioiTinhNV());
			ps.setString(5, nv.getNgaySinhNV());
			ps.setDouble(6, nv.getTienLuongNV());
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Xóa nhân viên khỏi database
	public boolean xoaNhanVien(String maSoNV) {
		String sql = "DELETE FROM NhanVien WHERE MaSoNV = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maSoNV);
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Cập nhật nhân viên
	public boolean capNhatNhanVien(JDatePickerEntity nv) {
		String sql = "UPDATE NhanVien SET Ho = ?, TenNhanVien = ?, Phai = ?, NgaySinh = ?, TienLuong = ? WHERE Ma = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getHoNV());
			ps.setString(2, nv.getTenNV());
			ps.setBoolean(3, nv.getGioiTinhNV());
			ps.setString(4, nv.getNgaySinhNV());
			ps.setDouble(5, nv.getTienLuongNV());
			ps.setString(6, nv.getMaSoNv());
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
