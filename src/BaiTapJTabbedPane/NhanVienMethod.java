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
	
	public NhanVienMethod() {
		super();
		this.listNhanVien = new ArrayList<NhanVienEntity>();
		this.mapNhanVien = new HashMap<String, NhanVienEntity>();
	}
	
	
	// Thêm nhân viên mới vào danh sách
	public Boolean addNhanVien(NhanVienEntity nv) {
		if (mapNhanVien.containsKey(nv.getMaNV())) {
			return false;
		}
		listNhanVien.add(nv);
		mapNhanVien.put(nv.getMaNV(), nv);
		return true;
	}
	
	// Xóa nhân viên khỏi danh sách
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
	
	// Load dữ liệu từ file
	public Boolean loadDataFromFile(String path) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<NhanVienEntity> loadedList = (ArrayList<NhanVienEntity>) FileLoadAndSave.loadFile(path);
			if (loadedList != null) {
				this.listNhanVien = loadedList;
				this.mapNhanVien.clear();
				for (NhanVienEntity nv : loadedList) {
					mapNhanVien.put(nv.getMaNV(), nv);
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Save dữ liệu vào file
	public Boolean saveDataToFile(String path) {
		try {
			return FileLoadAndSave.saveFile(listNhanVien, path);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
