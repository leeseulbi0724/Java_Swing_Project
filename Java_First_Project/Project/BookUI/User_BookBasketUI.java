package BookUI;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BookSystem.BookSystem;
import BookVO.BookVO;
import BookVO.MemberVO;
import Commons.Commons;

public class User_BookBasketUI extends JDialog implements ActionListener{

	//Filed
	
	LoginUI login;
	JTextField bsBookName_tf;
	JTextField bsBookPrice_tf;
	JButton buyCancel_btn, buyIt_btn;
	BookVO book;
	JComboBox count_box;
	String name;
	ArrayList<MemberVO> list;
	
	BookSystem system;
	
	//Constructor
	public User_BookBasketUI(Window parent, BookVO book, String name, BookSystem system) {
		super(parent,"장바구니",ModalityType.APPLICATION_MODAL);
		this.system= system;
		this.name = name;
		this.book = book;
		init();		
		
	}
	
	
	
	//Method
	public void init() {
		
		JPanel basketPanel = new JPanel();
		setBounds(100, 100, 530, 560);
		getContentPane().setLayout(null);
		basketPanel.setBackground(Color.WHITE);
		basketPanel.setBounds(0, 0, 530, 540);
		getContentPane().add(basketPanel);
		basketPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(44, 113, 428, 2);
		basketPanel.add(separator_1);
		
		String[] count = new String[] {"1","2","3","4","5"};
		count_box = new JComboBox(count);
		count_box.setBounds(347, 152, 125, 35);
		basketPanel.add(count_box);
		
		bsBookName_tf = new JTextField();
		bsBookName_tf.setBounds(44, 125, 269, 35);
		bsBookName_tf.setText(book.getBookname());
		basketPanel.add(bsBookName_tf);
		bsBookName_tf.setColumns(10);
		
		bsBookPrice_tf = new JTextField();
		bsBookPrice_tf.setBounds(44, 170, 269, 35);
		bsBookPrice_tf.setText(String.valueOf(book.getPrice())+"원");
		basketPanel.add(bsBookPrice_tf);
		bsBookPrice_tf.setColumns(10);		
		
		buyIt_btn = new JButton("장바구니담기");
		buyIt_btn.setBounds(44, 449, 195, 53);
		buyIt_btn.setForeground(Color.WHITE);
		buyIt_btn.setBackground(Color.PINK);
		basketPanel.add(buyIt_btn);
		
		buyCancel_btn = new JButton("취소");
		buyCancel_btn.setBounds(277, 449, 195, 53);
		buyCancel_btn.setForeground(Color.WHITE);
		buyCancel_btn.setBackground(Color.PINK);
		basketPanel.add(buyCancel_btn);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("images/cart.png"));
		lblNewLabel.setBounds(126, 10, 249, 53);
		
		JLabel name_label = new JLabel("   장바구니");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		name_label.setBounds(126, 55, 249, 53);		
		
		JLabel logo_label = new JLabel();
		logo_label.setHorizontalAlignment(SwingConstants.CENTER);
		logo_label.setIcon(new ImageIcon("images/Backlogo.png"));
		logo_label.setBounds(126, 240, 249, 200);	
		
		basketPanel.add(lblNewLabel);
		basketPanel.add(name_label);
		basketPanel.add(logo_label);
		
		/** 폰트설정 **/
		count_box.setFont(Commons.getFont());
		bsBookName_tf.setFont(Commons.getFont());
		bsBookPrice_tf.setFont(Commons.getFont());
		buyIt_btn.setFont(Commons.getFont());
		buyCancel_btn.setFont(Commons.getFont());
		lblNewLabel.setFont(Commons.getFont());
		name_label.setFont(Commons.getFont(14,"BOLD"));
		
		/** 버튼 이벤트 **/
		buyCancel_btn.addActionListener(this);
		buyIt_btn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(buyCancel_btn)) {
			dispose();
		} else if (obj.equals(buyIt_btn)) {
			Basket_proc();
		}		
	}
	
	public void Basket_proc() {
		BookVO vo = new BookVO();
		vo.setBookname(bsBookName_tf.getText());
		vo.setPrice((book.getPrice()));
		vo.setCount(Integer.parseInt(count_box.getSelectedItem().toString()));	
		boolean result = system.Basket_check(vo, name);
		if (system.User_Basket(vo, name,result)) {
			JOptionPane.showMessageDialog(null, Commons.getMsg("선택하신 도서를 장바구니에 담았습니다."));
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, Commons.getMsg("장바구니에 담지 못했습니다. 다시 시도해주세요."));
		}				
	}
}
