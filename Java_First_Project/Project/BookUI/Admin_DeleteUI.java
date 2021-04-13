package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Commons.Commons;

public class Admin_DeleteUI {

	Admin_MainUI main;
	JFrame frame;
	JPanel bottom_panel;

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
		
		JButton btn_search = new JButton("검색");
		bottom_panel.add(btn_search, BorderLayout.EAST);
		
		JTextField search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		
		main.content_panel.add(BorderLayout.CENTER, main.scrollPane);
		main.content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		
		/** 폰트 설정 (쓰실때 주석처리하시거나 본인 폰트로 설정하시면 됩니다! )**/
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont());
		
		
	}

}
