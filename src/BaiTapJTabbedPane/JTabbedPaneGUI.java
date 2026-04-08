package BaiTapJTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JTabbedPaneGUI extends JFrame implements ActionListener {
	private DefaultTableModel model;
	private JTable tableNhanVien;
	private JTextField txtMaNV;
	private JTextField txtHoNV;
	private JTextField txtTenNV;
	private JTextField txtTuoiNV;
	private JTextField txtTienLuongNV;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnLuu;
	private JComboBox cboPhaiNV;
	private NhanVienMethod NhanVienMethod;
	private final String  filePath = "data/DanhSachNhanVien.dat";
	private JComboBox cboPhongBan;
	private JComboBox cboPhongBanNV;
	
	public JTabbedPaneGUI() {
		setTitle("JTabbedPane");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// Khởi tạo model trước
		model = new DefaultTableModel();
		
		// Tạo GUI 
		createGUI();
		
		// Sau đó khởi tạo NhanVienMethod và load dữ liệu
		NhanVienMethod = new NhanVienMethod();
		
		// Kiểm tra và load dữ liệu từ file
		java.io.File file = new java.io.File(filePath);
		if (file.exists()) {
			// File tồn tại, load dữ liệu từ file
			if (NhanVienMethod.loadDataFromFile(filePath)) {
				JOptionPane.showMessageDialog(this, "Đã tải dữ liệu từ file:\n" + filePath, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Lỗi khi tải file dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// File không tồn tại
			JOptionPane.showMessageDialog(this, "Không tìm thấy file dữ liệu:\n" + filePath + "\n\nChương trình sẽ chạy bình thường.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		}
		
		// Load dữ liệu từ file lên bảng
		loadDataToTable();
		
		setVisible(true);
		
		txtMaNV.setText("NV004");
		txtHoNV.setText("Nguyễn Minh");
		txtTenNV.setText("Phúc");
		txtTuoiNV.setText("23");
		txtTienLuongNV.setText("15000000");
		cboPhaiNV.setSelectedItem("Nam");
		cboPhongBanNV.setSelectedIndex(1); // Chọn "Phòng tổ chức"
	}

	public void loadDataToTable() {
		// Xóa hết dữ liệu cũ trong bảng
		model.setRowCount(0);
		ArrayList<NhanVienEntity> list = NhanVienMethod.getListNhanVien();
		for (NhanVienEntity nv : list) {
			// Xử lý hiển thị giới tính
			String gioiTinh = nv.getPhai() ? "Nam" : "Nữ";
			model.addRow(new Object[] {
				nv.getMaNV(),
				nv.getHoNV(),
				nv.getTenNV(),
				gioiTinh,
				nv.getTuoi(),
				nv.getTienLuong() != null ? String.format("%.0f", nv.getTienLuong()) : "0"
			});
		}
		// Reset ComboBox Phòng Ban về "Tất cả"
		if (cboPhongBan != null) {
			cboPhongBan.setSelectedIndex(0);
		}
	}
	public void createGUI() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panelDanhSachNhanVien = new JPanel();
		JPanel panelThemMoiNhanVien = new JPanel();
		tabbedPane.addTab("Danh Sách Nhân Viên", panelDanhSachNhanVien);
		tabbedPane.addTab("Thêm Mới Nhân Viên", panelThemMoiNhanVien);
		tabbedPane.addTab("Thêm Mới Vua Tao", new JPanel()); // Tab trống để tạo khoảng cách
		add(tabbedPane);
		
		// Tab 1: Danh Sách Nhân Viên
		panelDanhSachNhanVien.setLayout(new BorderLayout());
		JPanel panelPhongBan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblPhongBan = new JLabel("Phòng Ban:                   ");
		lblPhongBan.setFont(new Font("Arial", Font.BOLD, 12));
		panelPhongBan.add(lblPhongBan);
		// 
		cboPhongBan = new JComboBox<>();
		cboPhongBan.setPreferredSize(new Dimension(180, 30));
		cboPhongBan.addItem("0.Tất cả");
		int index = 1;
		for (PhongBanEntity pb : PhongBanEntity.values()) {
			String item = index + "." + pb.getTenPhong();   // hoặc pb.toString()
		    cboPhongBan.addItem(item);
		    index++;
		}
		
		cboPhongBan.addActionListener(this);
		panelPhongBan.add(cboPhongBan);
		
		// Khởi tạo model với các cột
		model.addColumn("Mã");
		model.addColumn("Họ");
		model.addColumn("Tên");
		model.addColumn("Phái");
		model.addColumn("Tuổi");
		model.addColumn("Tiền Lương");
		
		tableNhanVien = new JTable(model);
		tableNhanVien.setRowHeight(30);
		tableNhanVien.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
		    {
		        setHorizontalAlignment(JLabel.RIGHT);
		    }

		    @Override
		    protected void setValue(Object value) {
		        setText(value == null ? "" : value.toString() + " $");
		    }
		});
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
		panelDanhSachNhanVien.add(scrollPane, BorderLayout.CENTER);
		panelDanhSachNhanVien.add(panelPhongBan, BorderLayout.NORTH);
		
		// Tab 2: Thêm Mới Nhân Viên
		panelThemMoiNhanVien.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		panelThemMoiNhanVien.add(new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0))); // panel trống để tạo khoảng cách
		JPanel panelMaNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		JLabel lblMaNV = new JLabel("Mã Nhân Viên: ");
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtMaNV = new JTextField();
		txtMaNV.setPreferredSize(new Dimension(550, 35));
		panelMaNV.add(lblMaNV);
		panelMaNV.add(txtMaNV);
		panelThemMoiNhanVien.add(panelMaNV);
		
		JPanel panelHoNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		JLabel lblHoNV = new JLabel("Họ:                   ");
		lblHoNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtHoNV = new JTextField();
		txtHoNV.setPreferredSize(new Dimension(550, 35));
		panelHoNV.add(lblHoNV);
		panelHoNV.add(txtHoNV);
		panelThemMoiNhanVien.add(panelHoNV);
		
		JPanel panelTenNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		JLabel lblTenNV = new JLabel("Tên nhân viên: ");
		lblTenNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtTenNV = new JTextField();
		txtTenNV.setPreferredSize(new Dimension(550, 35));
		panelTenNV.add(lblTenNV);
		panelTenNV.add(txtTenNV);
		panelThemMoiNhanVien.add(panelTenNV);
		
		JPanel panelTuoiNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		JLabel lblTuoiNV = new JLabel("Tuổi:                ");
		lblTuoiNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtTuoiNV = new JTextField();
		txtTuoiNV.setPreferredSize(new Dimension(550, 35));
		panelTuoiNV.add(lblTuoiNV);
		panelTuoiNV.add(txtTuoiNV);
		panelThemMoiNhanVien.add(panelTuoiNV);
		
		JPanel panelPhai_PhongBanNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		JLabel lblPhaiNV = new JLabel("Phái:                ");
		lblPhaiNV.setFont(new Font("Arial", Font.BOLD, 13));
		cboPhaiNV = new JComboBox<>();
		cboPhaiNV.setPreferredSize(new Dimension(205, 35));
		cboPhaiNV.addItem("Nam");
		cboPhaiNV.addItem("Nữ");
		JLabel lblPhongBanNV = new JLabel("Phòng Ban:");
		lblPhongBanNV.setFont(new Font("Arial", Font.BOLD, 13));
		cboPhongBanNV = new JComboBox<>();
		cboPhongBanNV.setPreferredSize(new Dimension(250, 35));
		for (PhongBanEntity pb : PhongBanEntity.values()) {
		    cboPhongBanNV.addItem(pb);
		}
		panelPhai_PhongBanNV.add(lblPhaiNV);
		panelPhai_PhongBanNV.add(cboPhaiNV);
		panelPhai_PhongBanNV.add(lblPhongBanNV);
		panelPhai_PhongBanNV.add(cboPhongBanNV);
		panelThemMoiNhanVien.add(panelPhai_PhongBanNV);
		
		JPanel panelTienLuongNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		JLabel lblTienLuongNV = new JLabel("Tiền Lương:     ");
		lblTienLuongNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtTienLuongNV = new JTextField();
		txtTienLuongNV.setPreferredSize(new Dimension(550, 35));
		panelTienLuongNV.add(lblTienLuongNV);
		panelTienLuongNV.add(txtTienLuongNV);
		panelThemMoiNhanVien.add(panelTienLuongNV);
		
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xóa Trắng");
		btnLuu = new JButton("Lưu");
		btnThem.setPreferredSize(new Dimension(80, 35));
		btnXoaTrang.setPreferredSize(new Dimension(100, 35));
		btnLuu.setPreferredSize(new Dimension(70, 35));
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		panelButton.add(btnThem);
		panelButton.add(btnXoaTrang);
		panelButton.add(btnLuu);
		panelThemMoiNhanVien.add(panelButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		// Xử lý sự kiện ComboBox Phòng Ban
		if (source == cboPhongBan) {
			locDuLieuTheoPhongBan();
		}
		// Xử lý nút "Thêm"
		else if (source == btnThem) {
			Them();
		}
		// Xử lý nút "Xóa Trắng"
		else if (source == btnXoaTrang) {
			XoaTrang();
		}
		// Xử lý nút "Lưu"
		else if (source == btnLuu) {
			Luu();
		}
	}
	
	// Phương thức lọc dữ liệu theo phòng ban
	private void locDuLieuTheoPhongBan() {
		String phongBanChon = (String) cboPhongBan.getSelectedItem();
		
		// Xóa hết dữ liệu cũ trong bảng
		model.setRowCount(0);
		
		ArrayList<NhanVienEntity> list = NhanVienMethod.getListNhanVien();
		
		// Nếu chọn "Tất cả" thì hiển thị toàn bộ
		if (phongBanChon.equals("0.Tất cả")) {
			for (NhanVienEntity nv : list) {
				String gioiTinh = nv.getPhai() ? "Nam" : "Nữ";
				model.addRow(new Object[] {
					nv.getMaNV(),
					nv.getHoNV(),
					nv.getTenNV(),
					gioiTinh,
					nv.getTuoi(),
					nv.getTienLuong() != null ? String.format("%.0f", nv.getTienLuong()) : "0"
				});
			}
		} else {
			// Lọc theo phòng ban được chọn
			// Loại bỏ số và dấu chấm từ phòng ban được chọn
			String tenPhongBan = phongBanChon.substring(2); // Bỏ "X." ở đầu
			
			for (NhanVienEntity nv : list) {
				// Kiểm tra nếu phòng ban của nhân viên trùng với phòng ban được chọn
				if (nv.getPhongBan() != null && nv.getPhongBan().equals(tenPhongBan)) {
					String gioiTinh = nv.getPhai() ? "Nam" : "Nữ";
					model.addRow(new Object[] {
						nv.getMaNV(),
						nv.getHoNV(),
						nv.getTenNV(),
						gioiTinh,
						nv.getTuoi(),
						nv.getTienLuong() != null ? String.format("%.0f", nv.getTienLuong()) : "0"
					});
				}
			}
		}
	}
	
	// Xử lý sự kiện nút "Thêm"
	private void Them() {
		String maNV = txtMaNV.getText().trim();
		String hoNV = txtHoNV.getText().trim();
		String tenNV = txtTenNV.getText().trim();
		String tuoiStr = txtTuoiNV.getText().trim();
		String tienLuongStr = txtTienLuongNV.getText().trim();
		
		// Kiểm tra dữ liệu nhập
		if (maNV.isEmpty() || hoNV.isEmpty() || tenNV.isEmpty() || tuoiStr.isEmpty() || tienLuongStr.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			int tuoi = Integer.parseInt(tuoiStr);
			double tienLuong = Double.parseDouble(tienLuongStr);
			
			if (tuoi < 18 || tuoi > 65) {
				JOptionPane.showMessageDialog(this, "Tuổi phải từ 18 đến 65!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (tienLuong < 0) {
				JOptionPane.showMessageDialog(this, "Tiền lương không được âm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// Tạo object NhanVienEntity
			NhanVienEntity nv = new NhanVienEntity();
			nv.setMaNV(maNV);
			nv.setHoNV(hoNV);
			nv.setTenNV(tenNV);
			nv.setTuoi(tuoi);
			nv.setPhai(cboPhaiNV.getSelectedItem().toString().equals("Nam"));
			nv.setPhongBan((PhongBanEntity) cboPhongBanNV.getSelectedItem());
			nv.setTienLuong(tienLuong);
			
			// Thêm vào danh sách
			if (NhanVienMethod.addNhanVien(nv)) {
				JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				loadDataToTable();
				XoaTrang();
			}
			else {
				JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Tuổi và tiền lương phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// Xử lý sự kiện nút "Xóa Trắng"
	private void XoaTrang() {
		txtMaNV.setText("");
		txtHoNV.setText("");
		txtTenNV.setText("");
		txtTuoiNV.setText("");
		txtTienLuongNV.setText("");
		cboPhaiNV.setSelectedIndex(0);
		cboPhongBanNV.setSelectedIndex(0);
		txtMaNV.requestFocus();
	}
	
	// Xử lý sự kiện nút "Lưu" vào file
	private void Luu() {
		ArrayList<NhanVienEntity> list = NhanVienMethod.getListNhanVien();
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Danh sách nhân viên trống, không có gì để lưu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (FileLoadAndSave.saveFile(list, filePath)) {
			JOptionPane.showMessageDialog(this, "Lưu danh sách nhân viên thành công vào file:\n" + filePath, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Lỗi khi lưu file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

}
