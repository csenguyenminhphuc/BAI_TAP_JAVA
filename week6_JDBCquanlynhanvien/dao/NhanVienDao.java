package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVienEntity;

public class NhanVienDao {
	
	private Connection con;
	
	public NhanVienDao() {
		super();
		this.con = ConnectDB.getConnection();
	}
	
	public ArrayList<NhanVienEntity> getDanhSachNhanVienDB() {
		ArrayList<NhanVienEntity> list = new ArrayList<NhanVienEntity>();
		String sql = "SELECT * FROM NhanVien";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String maNV = rs.getString("MaNhanVien");
				String hoNV = rs.getString("Ho");
				String tenNV = rs.getString("TenNhanVien");
				String tuoiNV = rs.getString("Tuoi");
				String phaiNV = rs.getString("Phai");
				String phongBanNV = rs.getString("PhongBan");
				Double tienLuongNV = rs.getDouble("TienLuong");
				NhanVienEntity nv = new NhanVienEntity(maNV, hoNV, tenNV, phaiNV, Integer.parseInt(tuoiNV), phongBanNV, tienLuongNV);
				list.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// Thêm nhân viên vào database
	public Boolean addNhanVienDB(NhanVienEntity nv) {
		String sql = "INSERT INTO NhanVien (MaNhanVien, Ho, TenNhanVien, Phai, Tuoi, PhongBan, TienLuong) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nv.getMaNV());
			ps.setString(2, nv.getHoNV());
			ps.setString(3, nv.getTenNV());
			ps.setString(4, nv.getPhaiNV());
			ps.setInt(5, nv.getTuoiNV());
			ps.setString(6, nv.getPhongBanNV());
			ps.setDouble(7, nv.getTienLuongNV());
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	// Xóa nhân viên khỏi database
	public Boolean deleteNhanVienDB(String maNV) {
		String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maNV);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
