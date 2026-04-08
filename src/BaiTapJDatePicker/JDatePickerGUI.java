package BaiTapJDatePicker;
/**
 * Nguyễn Minh Phúc - 22637001
 * Bài Tập Quản lý nhân viên
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.UtilDateModel;


	
public class JDatePickerGUI extends JFrame implements ActionListener, MouseListener{

	private DefaultTableModel model;
	private JTable table;
	private JCalendarMethod method;
	private JComboBox cboPhai;
	
	public JDatePickerGUI() {
		setTitle("JDatePicker");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		method = new JCalendarMethod();
		createGUI();
		loadDataToTable();
	}
	
	public void loadDataToTable() {
		model.setRowCount(0);
		ArrayList<JDatePickerEntity> list = method.getDanhSachNhanVien();
		for (JDatePickerEntity nv : list) {
			Object[] row = {
				nv.getMaSoNv(),
				nv.getHoNV(),
				nv.getTenNV(),
				nv.getGioiTinhNV() ? "Nam" : "Nữ",
				nv.getNgaySinhNV(),
				String.format("%.0f", nv.getTienLuongNV())
			};
			model.addRow(row);
		}
	}
	
	public void createGUI() {
		setLayout(new BorderLayout());
		
		// Panel Center Table
		JPanel panelCenter = new JPanel(new BorderLayout());
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Mã số");
		model.addColumn("Họ");
		model.addColumn("Tên Nhân Viên");
		model.addColumn("Phái");
		model.addColumn("Ngày Sinh");
		model.addColumn("Tiền Lương");
		
		table.setRowHeight(30);
		cboPhai = new JComboBox<>(new String[] {"Nam", "Nữ"});
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cboPhai));
		table.addMouseListener(this);
		// set cột ngày sinh nội dung nằm giữa
		table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {{
	         setHorizontalAlignment(JLabel.CENTER);
	     }});
		// set cột tiền lương nội dung nằm phải hiển thị thêm dấu $ ở cuối
		table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
		    {
		        setHorizontalAlignment(JLabel.RIGHT);
		    }

		    @Override
		    protected void setValue(Object value) {
		        setText(value == null ? "" : value.toString() + " $");
		    }
		});
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelCenter.add(scrollPane, BorderLayout.CENTER);
		add(panelCenter, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Lấy vị trí hàng và cột đang được chọn trong JTable
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();

		// Kiểm tra nếu click vào cột thứ 4 (cột ngày sinh)
		if (col == 4) { 
		    // Lấy giá trị hiện tại trong ô (dạng String)
		    String currentDate = (String) table.getValueAt(row, col);

		    // Tạo model cho date picker
		    UtilDateModel model = new UtilDateModel();

		    // Nếu ô đã có ngày -> parse và set vào model
		    if (currentDate != null && !currentDate.isEmpty()) {
		        try {
		            // Chuyển String sang LocalDate (format yyyy-MM-dd)
		            LocalDate d = LocalDate.parse(currentDate);

		            // Set ngày vào model (lưu ý tháng bắt đầu từ 0)
		            model.setDate(d.getYear(), d.getMonthValue() - 1, d.getDayOfMonth());

		            // Đánh dấu là đã chọn ngày
		            model.setSelected(true);
		        } catch (Exception ex) {
		            // Nếu parse lỗi thì bỏ qua (không crash chương trình)
		        }
		    }

		    // Tạo panel chọn ngày (date picker UI)
		    JDatePanel datePanel = new JDatePanel(model);

		    // Tạo popup để hiển thị date picker
		    JPopupMenu popup = new JPopupMenu();
		    popup.setLayout(new BorderLayout());

		    // Thêm datePanel vào popup
		    popup.add(datePanel, BorderLayout.CENTER);

		    // Bắt sự kiện khi người dùng chọn ngày
		    datePanel.addActionListener(evt -> {
		        // Lấy ngày được chọn từ model
		        Date selected = (Date) datePanel.getModel().getValue();

		        if (selected != null) {
		            // Convert từ Date sang LocalDate
		            LocalDate ld = selected.toInstant()
		                                   .atZone(ZoneId.systemDefault())
		                                   .toLocalDate();

		            // Set lại giá trị vào JTable (dạng String yyyy-MM-dd)
		            table.setValueAt(ld.toString(), row, col);

		            // Đóng popup sau khi chọn xong
		            popup.setVisible(false);
		        }
		    });

		    // Hiển thị popup tại vị trí click chuột
		    popup.show(table, e.getX(), e.getY());
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