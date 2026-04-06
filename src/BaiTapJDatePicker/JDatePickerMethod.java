package BaiTapJDatePicker;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
import java.util.ArrayList;
import java.util.HashMap;

public class JDatePickerMethod {
	private ArrayList<JDatePickerEntity> listNV;
	private HashMap<String, JDatePickerEntity> mapNV;
	private JDatePickerDao dao;
	
	public JDatePickerMethod() {
		super();
		this.listNV = new ArrayList<>();
		this.mapNV = new HashMap<>();
		this.dao = new JDatePickerDao();
		loadDataFromDatabase();
	}
	
	// Load dữ liệu từ database
	public void loadDataFromDatabase() {
		ArrayList<JDatePickerEntity> list = dao.getDanhSachNhanVien();
		for (JDatePickerEntity nv : list) {
			listNV.add(nv);
			mapNV.put(nv.getMaSoNv(), nv);
		}
	}
	
	// Thêm nhân viên vào list, map và database
	public boolean themNhanVien(JDatePickerEntity nv) {
		if (nv == null || mapNV.containsKey(nv.getMaSoNv())) 
			return false;
		if (dao.themNhanVien(nv)) {
			listNV.add(nv);
			mapNV.put(nv.getMaSoNv(), nv);
			return true;
		}
		return false;
	}
	
	// Xóa nhân viên khỏi list, map và database
	public boolean xoaNhanVien(String maSoNv) {
		JDatePickerEntity nv = mapNV.get(maSoNv);
		if (nv == null) 
			return false;
		if (dao.xoaNhanVien(maSoNv)) {
			listNV.remove(nv);
			mapNV.remove(maSoNv);
			return true;
		}
		return false;
	}
	
	// Cập nhật nhân viên
	public boolean capNhatNhanVien(JDatePickerEntity nv) {
		if (nv == null || !mapNV.containsKey(nv.getMaSoNv()))
			return false;
		if (dao.capNhatNhanVien(nv)) {
			mapNV.put(nv.getMaSoNv(), nv);
			for (int i = 0; i < listNV.size(); i++) {
				if (listNV.get(i).getMaSoNv().equals(nv.getMaSoNv())) {
					listNV.set(i, nv);
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	// Get Danh sách nhân viên
	public ArrayList<JDatePickerEntity> getDanhSachNhanVien() {
		return new ArrayList<>(listNV);
	}
	
	// Get nhân viên theo mã
	public JDatePickerEntity getNhanVien(String maSoNv) {
		return mapNV.get(maSoNv);
	}
}
