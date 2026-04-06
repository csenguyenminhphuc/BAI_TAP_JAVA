package BaiTapJTabbedPane;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
public class NhanVienMethod {
	private ArrayList<NhanVienEntity> listNhanVien;
	private HashMap<String, NhanVienEntity> mapNhanVien;
	private NhanVienDao nhanVienDao;
	
	public NhanVienMethod() {
		super();
		this.listNhanVien = new ArrayList<NhanVienEntity>();
		this.mapNhanVien = new HashMap<String, NhanVienEntity>();
		this.nhanVienDao = new NhanVienDao();
		loadDataFromDatabase();
	}
	
	public void loadDataFromDatabase() {
		ArrayList<NhanVienEntity> list = nhanVienDao.getDanhSachNhanVien();
		for (NhanVienEntity nv : list) {
			listNhanVien.add(nv);
			mapNhanVien.put(nv.getMaNV(), nv);
		}
	}
	
	public Boolean addNhanVien(NhanVienEntity nv) {
		if (mapNhanVien.containsKey(nv.getMaNV())) {
			return false;
		}
		listNhanVien.add(nv);
		mapNhanVien.put(nv.getMaNV(), nv);
		return true;
	}
	
	public Boolean deleteNhanVien(String maNV) {
		if (!mapNhanVien.containsKey(maNV)) {
			return false;
		}
		NhanVienEntity nv = mapNhanVien.get(maNV);
		listNhanVien.remove(nv);
		mapNhanVien.remove(maNV);
		return true;
	}
	
	public ArrayList<NhanVienEntity> getListNhanVien() {
		return listNhanVien;
	}
	
	
}
