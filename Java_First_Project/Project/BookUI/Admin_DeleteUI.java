package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import BookUI.User_MyPage_MyUI.ButtonEditor;
import BookUI.User_MyPage_MyUI.ButtonRenderer;
import BookVO.BookVO;
import Commons.Commons;

public class Admin_DeleteUI implements ActionListener{

	Admin_MainUI main;
	JFrame frame;
	JPanel bottom_panel;
	JButton btn_search;
	JTextField search_tf;
	ArrayList<Object> delete_list = new ArrayList<Object>();

	public Admin_DeleteUI(Admin_MainUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}
	public void init() {	
		main.switching(Admin_MainUI.Delete);
		bottom_panel = new JPanel();
		bottom_panel.setBackground(new Color(176, 196, 222));
		bottom_panel.setBounds(192, 439, 535, 41);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel bottom_label = new JLabel("삭제할 도서를 검색해주세요");
		bottom_label.setHorizontalAlignment(SwingConstants.CENTER);
		bottom_panel.add(bottom_label, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox();
		bottom_panel.add(comboBox, BorderLayout.WEST);
		comboBox.addItem("도서명");		comboBox.addItem("저자");
		
		JLabel name_label = new JLabel("회 원 삭 제");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		main.content_panel.add(name_label, BorderLayout.NORTH);
		name_label.setBackground(SystemColor.activeCaption);
		
		btn_search = new JButton("검색");
		bottom_panel.add(btn_search, BorderLayout.EAST);
		
		search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		
		main.content_panel.add(BorderLayout.CENTER, main.scrollPane);
		main.content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		btn_search.addActionListener(this);
		
		
		/** 폰트 설정 (쓰실때 주석처리하시거나 본인 폰트로 설정하시면 됩니다! )**/
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());
		
		
	}
	
	
	/** 검색 버튼 클릭 시 이벤트 **/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_search)) {
				data_search();		
			}
	}
	
	/** 도서 검색 **/
	public void data_search() {
		main.model.setNumRows(0);
		main.model.addColumn("삭제");
		Object row[] = new Object[7];
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		list = main.system.Admin_Search(search_tf.getText());
		for (BookVO book : list) {
			row[0] = book.getBno();
			row[1] = book.getBookname();
			row[2] = book.getAuthor();
			row[3] = book.getPblsh();
			row[4] = book.getPrice();
			row[5] = book.getPblshdate();
			delete_list.add(row);
			main.model.addRow(row);			
		}
		
        main.book_table.getColumn("삭제").setCellRenderer(new ButtonRenderer());
        main.book_table.getColumn("삭제").setCellEditor(new ButtonEditor(new JTextField()));
		
	}
	
	
	/** 삭제 버튼 이벤트 처리 **/
			class ButtonRenderer extends JButton implements TableCellRenderer {
				 public ButtonRenderer() {
				  setOpaque(true);
				 }		 
				 @Override
				 public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
					int col) {
					 	setText((obj == null) ? "" : obj.toString());
					  	return this;
					 }
				}
				class ButtonEditor extends DefaultCellEditor { 
				 JButton btn_delete;
				 String lbl;
				 Boolean clicked;

				 public ButtonEditor(JTextField txt) {
					  super(txt);
					  btn_delete = new JButton();
					  btn_delete.setOpaque(true);
					  btn_delete.addActionListener(new ActionListener() {
						  @Override
						  public void actionPerformed(ActionEvent e) {
						   fireEditingStopped();
						  }
					  });
				 }
				 @Override
				 public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
					  lbl = (obj == null) ? "" : obj.toString();
					  btn_delete.setText(lbl);
					  clicked = true;
					  return btn_delete;
				 }
				 @Override
				 public Object getCellEditorValue() {
				  if (clicked) {
					 int confirm = JOptionPane.showConfirmDialog(btn_delete, Commons.getMsg("정말로 삭제하시겠습니까?"));
					if (confirm == 0) {						
						if (main.system.Admin_Delete(search_tf.getText())) {
							JOptionPane.showMessageDialog(null, Commons.getMsg("삭제가 완료되었습니다."));
							delete_list.remove(main.book_table.getSelectedRow());
							main.model.removeRow(main.book_table.getSelectedRow());		
							search_tf.setText("");
						};						
					}
				  }
				  clicked = false;
				  return new String(lbl);
				 }
				 @Override
				 public boolean stopCellEditing() {
					  clicked = false;
					  return super.stopCellEditing();
				 }
				 @Override
				 public void fireEditingStopped() {
					 super.fireEditingStopped();
				 }

			}
		
	

}
