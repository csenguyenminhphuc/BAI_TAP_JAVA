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
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
	
	
	
	
	public class JDatePickerGUI extends JFrame implements ActionListener, MouseListener{
	
		private DefaultTableModel model;
		private JTable table;
		private JDatePickerMethod method;
		private JComboBox cboPhai;
		
		public JDatePickerGUI() {
			setTitle("JDatePicker");
			setSize(700, 600);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			method = new JDatePickerMethod();
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
			// TODO Auto-generated method stub
			int row = table.getSelectedRow();
	        int col = table.getSelectedColumn();
	        if (col == 4) { // cột ngày sinh
	            String currentDate = (String) table.getValueAt(row, col);

	            // Dùng model và datePanel thay vì datePicker để không có nút "..."
	            UtilDateModel model = new UtilDateModel();

	            if (currentDate != null && !currentDate.isEmpty()) {
	                try {
	                    LocalDate d = LocalDate.parse(currentDate);
	                    model.setDate(d.getYear(), d.getMonthValue() -1, d.getDayOfMonth());
	                    model.setSelected(true);
	                } catch (Exception ex) {
	                    // ignore parse lỗi
	                }
	            }

	            JDatePanel datePanel = new JDatePanel(model);

	            JPopupMenu popup = new JPopupMenu();
	            popup.setLayout(new BorderLayout());
	            popup.add(datePanel, BorderLayout.CENTER);

	            datePanel.addActionListener(evt -> {
	                Date selected = (Date) datePanel.getModel().getValue();
	                if (selected != null) {
	                    LocalDate ld = selected.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	                    table.setValueAt(ld.toString(), row, col);
	                    popup.setVisible(false);
	                }
	            });

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
