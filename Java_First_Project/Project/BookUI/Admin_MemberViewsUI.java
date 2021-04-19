package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.table.JTableHeader;

import BookSystem.BookSystem;
import BookVO.MemberVO;
import Commons.Commons;

public class Admin_MemberViewsUI {
	JFrame frame;
	Admin_MainUI main;
	LoginUI ui;
	ArrayList<MemberVO> list;
	JButton btn_search;
	JPanel bottom_panel;
	JComboBox comboBox;
	JTextField search_tf;
	JButton btn_member;
	DefaultTableModel model;
	JScrollPane scrollPane, member_pane;
	JTable member_table;
	Object row[];
	BookSystem system = new BookSystem();
	
	public Admin_MemberViewsUI(Admin_MainUI main) {
		
		this.main = main;
		this.frame = main.frame;
		init();
		
	}
	
	
	public void init() {
		main.switching(Admin_MainUI.User_select);
		
		bottom_panel = new JPanel();
		bottom_panel.setBackground(new Color(176, 196, 222));
		bottom_panel.setBounds(192, 439, 535, 41);
		bottom_panel.setLayout(new BorderLayout(0, 0));
		
		JLabel name_label = new JLabel("회 원 조 회");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		main.content_panel.add(name_label, BorderLayout.NORTH);
		name_label.setBackground(SystemColor.activeCaption);
		
		JLabel bottom_label = new JLabel("삭제할 회원을 검색해주세요");
		bottom_label.setHorizontalAlignment(SwingConstants.CENTER);
		bottom_panel.add(bottom_label, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		bottom_panel.add(comboBox, BorderLayout.WEST);
		comboBox.addItem("아이디");		comboBox.addItem("이름");
		
		btn_search = new JButton("검색");
		bottom_panel.add(btn_search, BorderLayout.EAST);
		
		search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		
		Object[] header = {"아이디","패스워드","이름","생년월일","HP","주소"};
		model = new DefaultTableModel(header, 0);
		member_table = new JTable(model);
		JScrollPane member_pane = new JScrollPane(member_table);
		member_pane.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(member_pane);	
		
		
		main.content_panel.add(scrollPane, BorderLayout.CENTER);
		main.content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		/** 테이블 색 설정 **/
		JTableHeader head = member_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		
		search_tf.requestFocus();
		
		/** 폰트 설정 **/
		name_label.setFont(Commons.getFont());
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		member_table.setFont(Commons.getFont());
	
		member_data();
		
	}
	
	public void member_data() {
		model.setNumRows(0);
		member_table.removeAll();
		row = new Object[6];
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list = system.Admin_MemberSelect();
		
		for(MemberVO member : list) {
			row[0] = member.getId();
			row[1] = member.getPass();
			row[2] = member.getName();
			row[3] = member.getBirthday();
			row[4] = member.getHp();
			row[5] = member.getAddr();
			model.addRow(row);
		}
		model.fireTableDataChanged();
		
	}
	
}
