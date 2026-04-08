package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDao;
import dao.NhanVienMethod;
import entity.NhanVienEntity;

public class NhanVienGUI extends JFrame implements ActionListener, MouseListener {
	
	
	private JTextField txtMaNV;
	private JTextField txtHoNV;
	private JTextField txtTenNV;
	private JTextField txtTuoiNV;
	private JTextField txtTienLuongNV;
	private JTextField txtPhongBanNV;
	private JComboBox<String> cboPhongBanNV;
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private JTextField txtTimKiem;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnLocTheoPhongBan;
	private NhanVienMethod methodNhanVien;
	private NhanVienDao nhanVienDao;
	private ButtonGroup phaiGroup;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;



	public NhanVienGUI() {
		setTitle("Quản Lý Nhân Viên - 22637001 - Nguyễn Minh Phúc");
		setSize(1000, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		modelNhanVien = new DefaultTableModel();
		
		createGUI();
		methodNhanVien = new NhanVienMethod();
		nhanVienDao = new NhanVienDao();
		
		loadDataToTable();
		txtMaNV.setText("NV004");
		txtHoNV.setText("Nguyễn");
		txtTenNV.setText("Minh Phúc");
		txtTuoiNV.setText("22");
		rdoNam.setSelected(true);
		cboPhongBanNV.setSelectedIndex(1);
		txtTienLuongNV.setText("15000000");

	}

	
	public void loadDataToTable() {
		// TODO Auto-generated method stub
		modelNhanVien.setRowCount(0);
		for (NhanVienEntity nv : methodNhanVien.getListNhanVien()) {
			Object[] rowData = {
					nv.getMaNV(),
					nv.getHoNV(),
					nv.getTenNV(),
					nv.getTuoiNV(),
					nv.getPhaiNV(),
					nv.getPhongBanNV(),
					nv.getTienLuongNV()
			};
			modelNhanVien.addRow(rowData);
		}
	}
	public void createGUI() {
		// TODO Auto-generated method stub
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelSouth = new JPanel();
		setLayout(new BorderLayout());
		add(panelNorth, BorderLayout.NORTH);
		add(panelCenter, BorderLayout.CENTER);
		add(panelSouth,	 BorderLayout.SOUTH);
		
		// Panel North
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
		JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
		JLabel lblTitle = new JLabel();
		lblTitle.setText("THÔNG TIN NHÂN VIÊN");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		panelTitle.add(lblTitle);
		panelNorth.add(panelTitle);
		
		JPanel panelMaNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtMaNV = new JTextField();
		txtMaNV.setPreferredSize(new Dimension(850, 35));
		panelMaNV.add(lblMaNV);
		panelMaNV.add(txtMaNV);
		panelNorth.add(panelMaNV);
		
		JPanel panelHoTen = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblHoTen = new JLabel("Họ:                 ");
		lblHoTen.setFont(new Font("Arial", Font.BOLD, 13));
		txtHoNV = new JTextField();
		txtHoNV.setPreferredSize(new Dimension(400, 35));
		JLabel lblTen = new JLabel("Tên:  ");
		txtTenNV = new JTextField();
		txtTenNV.setPreferredSize(new Dimension(400, 35));
		panelHoTen.add(lblHoTen);
		panelHoTen.add(txtHoNV);
		panelHoTen.add(lblTen);
		panelHoTen.add(txtTenNV);
		panelNorth.add(panelHoTen);
		
		JPanel panelTuoiNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblTuoiNV = new JLabel("Tuổi:              ");
		lblTuoiNV.setFont(new Font("Arial", Font.BOLD, 13));
		txtTuoiNV = new JTextField();
		txtTuoiNV.setPreferredSize(new Dimension(650, 35));
		JLabel lblPhai = new JLabel("         Phái: ");
		lblPhai.setFont(new Font("Arial", Font.BOLD, 13));
		phaiGroup = new ButtonGroup();
		rdoNam = new JRadioButton("Nam");
		rdoNu = new JRadioButton("Nữ");
		phaiGroup.add(rdoNam);
		phaiGroup.add(rdoNu);
		panelTuoiNV.add(lblTuoiNV);
		panelTuoiNV.add(txtTuoiNV);
		panelTuoiNV.add(lblPhai);
		panelTuoiNV.add(rdoNam);
		panelTuoiNV.add(rdoNu);
		panelNorth.add(panelTuoiNV);
		
		JPanel panelPhongBanTienLuongNV = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel lblTienLuong = new JLabel("Tiền lương:   ");
		lblTienLuong.setFont(new Font("Arial", Font.BOLD, 13));
		txtTienLuongNV = new JTextField();
		txtTienLuongNV.setPreferredSize(new Dimension(600, 35));
		JLabel lblPhongBan = new JLabel("         Phòng ban: ");
		lblPhongBan.setFont(new Font("Arial", Font.BOLD, 13));
		cboPhongBanNV = new JComboBox<>();
		cboPhongBanNV.setPreferredSize(new Dimension(120, 35));
		cboPhongBanNV.addItem("Tất cả");
		cboPhongBanNV.addItem("Phòng nhân sự");
		cboPhongBanNV.addItem("Phòng kỹ thuật");
		cboPhongBanNV.addItem("Phòng tổ chức");
		cboPhongBanNV.addItem("Phòng tài vụ");
		panelPhongBanTienLuongNV.add(lblTienLuong);
		panelPhongBanTienLuongNV.add(txtTienLuongNV);
		panelPhongBanTienLuongNV.add(lblPhongBan);
		panelPhongBanTienLuongNV.add(cboPhongBanNV);
		panelNorth.add(panelPhongBanTienLuongNV);
		
		// panel Center
		
		panelCenter.setLayout(new BorderLayout());
		
		tableNhanVien = new JTable(modelNhanVien);
		modelNhanVien.addColumn("Mã nhân viên");
		modelNhanVien.addColumn("Họ");
		modelNhanVien.addColumn("Tên");
		modelNhanVien.addColumn("Tuổi");	
		modelNhanVien.addColumn("Phái");
		modelNhanVien.addColumn("Phòng ban");
		modelNhanVien.addColumn("Tiền lương");
		tableNhanVien.setRowHeight(30);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// Cách ngắn nhất và tốt nhất (áp dụng cho tất cả cột)
		tableNhanVien.setDefaultRenderer(Object.class, centerRenderer);
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
		panelCenter.add(scrollPane, BorderLayout.CENTER);
		
		
		// panel South
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
		JLabel lblTimKiem = new JLabel("Nhập mã nhân viên:");
		lblTimKiem.setFont(new Font("Arial", Font.BOLD, 13));
		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(150, 35));
		btnTim = new JButton("Tìm");
		btnTim.setPreferredSize(new Dimension(70, 35));
		btnThem	= new JButton("Thêm");
		btnThem.setPreferredSize(new Dimension(70, 35));
		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setPreferredSize(new Dimension(100, 35));
		btnXoa = new JButton("Xóa");
		btnXoa.setPreferredSize(new Dimension(70, 35));
		btnLocTheoPhongBan = new JButton("Lọc theo phòng ban");
		btnLocTheoPhongBan.setPreferredSize(new Dimension(150, 35));
		
		panelSouth.add(lblTimKiem);
		panelSouth.add(txtTimKiem);
		panelSouth.add(btnTim);
		panelSouth.add(new JLabel("     "));
		panelSouth.add(btnThem);
		panelSouth.add(btnXoaTrang);
		panelSouth.add(btnXoa);
		panelSouth.add(btnLocTheoPhongBan);
		
		
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLocTheoPhongBan.addActionListener(this);
		
		tableNhanVien.addMouseListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == btnTim) {
			// Xử lý sự kiện tìm kiếm nhân viên
			searchNhanVien();
		} else if (source == btnThem) {
			// Xử lý sự kiện thêm nhân viên
			addNhanVien();
		} else if (source == btnXoaTrang) {
			// Xử lý sự kiện xóa trắng các trường nhập liệu
			clearInputFields();
		} else if (source == btnXoa) {
			// Xử lý sự kiện xóa nhân viên\
			deleteNhanVien();
		} else if (source == btnLocTheoPhongBan) {
			// Xử lý sự kiện lọc nhân viên theo phòng ban
			filterByPhongBan();
		}
	}
	
	private void clearInputFields() {
		txtMaNV.setText("");
		txtHoNV.setText("");
		txtTenNV.setText("");
		txtTuoiNV.setText("");
		txtTienLuongNV.setText("");
		cboPhongBanNV.setSelectedIndex(0);
	}
	
	private Boolean validateInput() {
		String maNV = txtMaNV.getText().trim();
		String hoNV = txtHoNV.getText().trim();
		String tenNV = txtTenNV.getText().trim();
		String tuoiStr = txtTuoiNV.getText().trim();
		String tienLuongStr = txtTienLuongNV.getText().trim();
		
		if (maNV.isEmpty() || hoNV.isEmpty() || tenNV.isEmpty() || tuoiStr.isEmpty() || tienLuongStr.isEmpty()) {
			return false;
		}
		
		try {
			int tuoi = Integer.parseInt(tuoiStr);
			double tienLuong = Double.parseDouble(tienLuongStr);
			if (tuoi <= 0 || tienLuong < 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	// Tìm kiếm
	private void searchNhanVien() {
		String maNV = txtTimKiem.getText().trim();
		if (maNV.isEmpty()) {
			loadDataToTable();
			return;
		}
		NhanVienEntity nv = methodNhanVien.findNhanVien(maNV);
		if (nv != null) {
			modelNhanVien.setRowCount(0);
			Object[] rowData = {
					nv.getMaNV(),
					nv.getHoNV(),
					nv.getTenNV(),
					nv.getTuoiNV(),
					nv.getPhaiNV(),
					nv.getPhongBanNV(),
					nv.getTienLuongNV()
			};
			modelNhanVien.addRow(rowData);
		} else {
			modelNhanVien.setRowCount(0);
		}
	}
	
	private void addNhanVien() {
		if (!validateInput()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ và đúng định dạng thông tin nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String maNV = txtMaNV.getText().trim();
		String hoNV = txtHoNV.getText().trim();
		String tenNV = txtTenNV.getText().trim();
		int tuoi = Integer.parseInt(txtTuoiNV.getText().trim());
		String phai = rdoNam.isSelected() ? "Nam" : "Nữ";
		String phongBan = cboPhongBanNV.getSelectedItem().toString();
		double tienLuong = Double.parseDouble(txtTienLuongNV.getText().trim());
		
		NhanVienEntity nv = new NhanVienEntity(maNV, hoNV, tenNV, phai, tuoi, phongBan, tienLuong);
		
		if (methodNhanVien.addNhanVien(nv) && nhanVienDao.addNhanVienDB(nv)) {
			loadDataToTable();
			clearInputFields();
			JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
			
		} else if (phongBan == "Tất cả") {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng ban!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Xóa nhân viên khi click vào nút xóa sau khi đã chọn nhân viên trên bảng
	private void deleteNhanVien() {
		int selectedRow = tableNhanVien.getSelectedRow();
		if (selectedRow >= 0) {
			String maNV = modelNhanVien.getValueAt(selectedRow, 0).toString();
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				if (methodNhanVien.deleteNhanVien(maNV) && nhanVienDao.deleteNhanVienDB(maNV)) {
					loadDataToTable();
					clearInputFields();
				} else {
					JOptionPane.showMessageDialog(this, "Không thể xóa nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// load dữ liệu khi click vào bảng
		int selectedRow = tableNhanVien.getSelectedRow();
		if (selectedRow >= 0) {
			txtMaNV.setText(modelNhanVien.getValueAt(selectedRow, 0).toString());
			txtHoNV.setText(modelNhanVien.getValueAt(selectedRow, 1).toString());
			txtTenNV.setText(modelNhanVien.getValueAt(selectedRow, 2).toString());
			txtTuoiNV.setText(modelNhanVien.getValueAt(selectedRow, 3).toString());
			String phai = modelNhanVien.getValueAt(selectedRow, 4).toString();
			if (phai.equals("Nam")) {
				rdoNam.setSelected(true);
			} else {
				rdoNu.setSelected(true);
			}
			cboPhongBanNV.setSelectedItem(modelNhanVien.getValueAt(selectedRow, 5).toString());
			txtTienLuongNV.setText(modelNhanVien.getValueAt(selectedRow, 6).toString());
		}
		
	}
	
	// lọc theo phòng ban khi click vào nút lọc
	private void filterByPhongBan() {
		String selectedPhongBan = cboPhongBanNV.getSelectedItem().toString();
		if (selectedPhongBan.equals("Tất cả")) {
			loadDataToTable();
			return;
		}
		modelNhanVien.setRowCount(0);
		for (NhanVienEntity nv : methodNhanVien.getListNhanVien()) {
			if (nv.getPhongBanNV().equals(selectedPhongBan)) {
				Object[] rowData = {
						nv.getMaNV(),
						nv.getHoNV(),
						nv.getTenNV(),
						nv.getTuoiNV(),
						nv.getPhaiNV(),
						nv.getPhongBanNV(),
						nv.getTienLuongNV()
				};
				modelNhanVien.addRow(rowData);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}