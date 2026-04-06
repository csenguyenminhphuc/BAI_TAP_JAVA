package BaiTapJTabbedPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BaiTapJDatePicker.JDatePickerEntity;

public class NhanVienDao {
	private Connection con;
	
	public NhanVienDao() {
		super();
		this.con = ConnectDB.getConnection();
		if (this.con == null) {
			System.out.println("Lỗi: Không thể kết nối tới database!");
		}
	}
	
	// Lấy danh sách tất cả nhân viên từ database
	public ArrayList<NhanVienEntity> getDanhSachNhanVien() {
		ArrayList<NhanVienEntity> list = new ArrayList<>();
		
		// Kiểm tra kết nối
		if (con == null) {
			con = ConnectDB.getConnection();
			if (con == null) {
				System.out.println("Lỗi: Không thể lấy kết nối database!");
				return list;
			}
		}
		
		String sql = "SELECT * FROM NhanVien";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhanVienEntity nv = new NhanVienEntity();
				nv.setMaNV(rs.getString("MaNhanVien"));
				nv.setHoNV(rs.getString("Ho"));
				nv.setTenNV(rs.getString("TenNhanVien"));
				nv.setTuoi(rs.getInt("Tuoi"));
				nv.setPhai(rs.getBoolean("Phai"));
				nv.setPhongBan(rs.getString("PhongBan"));
				// Lấy tienLuong nếu có cột này trong database
				try {
					nv.setTienLuong(rs.getDouble("TienLuong"));
				} catch (SQLException e) {
					// Nếu không có cột TienLuong thì set mặc định
					nv.setTienLuong(0.0);
				}
				list.add(nv);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Lỗi khi lấy danh sách nhân viên từ database:");
			e.printStackTrace();
		}
		return list;
	}
	
	// Thêm nhân viên vào database
	public boolean themNhanVien(NhanVienEntity nv) {
		// Kiểm tra kết nối
		if (con == null) {
			con = ConnectDB.getConnection();
			if (con == null) {
				System.out.println("Lỗi: Không thể lấy kết nối database!");
				return false;
			}
		}
		
		String sql = "INSERT INTO NhanVien (MaNhanVien, Ho, TenNhanVien, Tuoi, Phai, PhongBan, TienLuong) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			String phai = nv.getPhai().equals("Nam") ? "Nam" : "Nữ"; // Chuyển đổi giá trị phái thành "Nam" hoặc "Nữ"
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getMaNV());
			ps.setString(2, nv.getHoNV());
			ps.setString(3, nv.getTenNV());
			ps.setInt(4, nv.getTuoi());
			ps.setString(5, phai);
			ps.setString(6, nv.getPhongBan());
			ps.setDouble(7, nv.getTienLuong());
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Lỗi khi thêm nhân viên vào database:");
			e.printStackTrace();
		}
		return false;
	}
	
	// Xóa nhân viên khỏi database
	public boolean xoaNhanVien(String maNhanVien) {
		// Kiểm tra kết nối
		if (con == null) {
			con = ConnectDB.getConnection();
			if (con == null) {
				System.out.println("Lỗi: Không thể lấy kết nối database!");
				return false;
			}
		}
		
		String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Lỗi khi xóa nhân viên khỏi database:");
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
