package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Commons.Commons;

public class Admin_MemberViewsUI {
	JFrame frame;
	Admin_MainUI main;
	
	JTextField member_tf;
	JButton btn_member;
	
	public Admin_MemberViewsUI(Admin_MainUI main) {
		this.main = main;
		this.frame = main.frame;
		init();
	}
	
	public void init() {
		main.switching(Admin_MainUI.User_select);
		
		JPanel content_panel = new JPanel(new BorderLayout());
		content_panel.setBackground(SystemColor.activeCaption);
		content_panel.setBounds(192, 98, 535, 331);
		content_panel.setLayout(new BorderLayout(0, 0));
		
		String[] colNames = {"아이디","패스워드","이름","생년월일","HP","주소"};
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		
		JScrollPane scrollPane = new JScrollPane();
		content_panel.add(scrollPane, BorderLayout.CENTER);
		
		JTable member_table = new JTable(model);
		JScrollPane member_pane = new JScrollPane(member_table);
		scrollPane.setViewportView(member_pane);	
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setBackground(new Color(176, 196, 222));
		bottom_panel.setBounds(192, 439, 535, 41);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel name_label = new JLabel("회 원 조 회");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		content_panel.add(name_label, BorderLayout.NORTH);
		name_label.setBackground(SystemColor.activeCaption);
		
		JLabel bottom_label = new JLabel("삭제할 회원을 검색해주세요");
		bottom_label.setHorizontalAlignment(SwingConstants.CENTER);
		bottom_panel.add(bottom_label, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox();
		bottom_panel.add(comboBox, BorderLayout.WEST);
		comboBox.addItem("아이디");		comboBox.addItem("이름");
		
		JButton btn_search = new JButton("검색");
		bottom_panel.add(btn_search, BorderLayout.EAST);
		
		JTextField search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		
		content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		main.content_panel.add(content_panel);
		
		/** 폰트 설정 **/
		member_table.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		
		
	}



}
