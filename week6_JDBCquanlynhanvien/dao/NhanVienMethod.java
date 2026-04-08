package dao;

import java.util.ArrayList;
import java.util.HashMap;

import entity.NhanVienEntity;

public class NhanVienMethod {
	private NhanVienDao nhanVienDao;
	private ArrayList<NhanVienEntity> listNhanVien;
	private HashMap<String, NhanVienEntity> mapNhanVien;
	
	public NhanVienMethod() {
		super();
		this.nhanVienDao = new NhanVienDao();
		this.listNhanVien = new ArrayList<NhanVienEntity>();
		this.mapNhanVien = new HashMap<String, NhanVienEntity>();
		loadDataFromDatabase();
	}
	// load dữ liệu từ database vào list và map
	public void loadDataFromDatabase() {
		ArrayList<NhanVienEntity> list = nhanVienDao.getDanhSachNhanVienDB();
		for (NhanVienEntity nv : list) {
			listNhanVien.add(nv);
			mapNhanVien.put(nv.getMaNV(), nv);
		}
	}
	// thêm nhân viên vào list, map và database
	public Boolean addNhanVien(NhanVienEntity nv) {
		if (mapNhanVien.containsKey(nv.getMaNV())) {
			return false;
		}
		listNhanVien.add(nv);
		mapNhanVien.put(nv.getMaNV(), nv);
		return true;
	}
	// xóa nhân viên khỏi list, map và database
	public Boolean deleteNhanVien(String maNV) {
		if (!mapNhanVien.containsKey(maNV)) {
			return false;
		}
		NhanVienEntity nv = mapNhanVien.get(maNV);
		listNhanVien.remove(nv);
		mapNhanVien.remove(maNV);
		return true;
	}
	
	// lấy danh sách nhân viên
	public ArrayList<NhanVienEntity> getListNhanVien() {
		return listNhanVien;
	}
	
	// tìm nhân viên theo mã
	public NhanVienEntity findNhanVien(String maNV) {
		return mapNhanVien.get(maNV);
	}
	
}
