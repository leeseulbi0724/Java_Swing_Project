package BookUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import BookDAO.MemberDAO;
import BookSystem.BookSystem;
import BookVO.MemberVO;
import Commons.Commons;



public class Admin_MemberViewsUI implements ActionListener {
	JFrame frame;
	Admin_MainUI main;
	LoginUI ui;
	ArrayList<MemberVO> list;
	ArrayList<MemberVO> slist;
	JButton btn_search;
	JPanel bottom_panel;
	JComboBox comboBox;
	JTextField search_tf;
	JButton btn_member;
	DefaultTableModel model;
	JScrollPane scrollPane, member_pane, search_pane;
	JTable member_table;
	Object row[];
	BookSystem system = new BookSystem();
	MemberDAO mdao = new MemberDAO();

	
	static final int LIST = 1;
	static final int SEARCH = 2;
	
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
		btn_search.addActionListener(this);
		
		search_tf = new JTextField();
		bottom_panel.add(search_tf, BorderLayout.CENTER);
		search_tf.setColumns(10);
		
		main.content_panel.add(BorderLayout.SOUTH, bottom_panel);
		
		search_tf.requestFocus();
		
		/** 폰트 설정 **/
		name_label.setFont(Commons.getFont());
		bottom_label.setFont(Commons.getFont());
		comboBox.setFont(Commons.getFont());
		btn_search.setFont(Commons.getFont());
		search_tf.setFont(Commons.getFont());

//		scrollPane = new JScrollPane();
//		main.content_panel.add(BorderLayout.CENTER, scrollPane);
		member_data();
		
		
	}
	
	public void member_data() {
		
		list = mdao.getResultSelect();
		String [] colNames = new String [] {"번호","아이디","패스워드","이름","생년월일","HP","주소","삭제"};
		Object[][] rowDatas = new Object[list.size()][colNames.length];
		 for (int i = 0; i < list.size(); i++) {
	            rowDatas[i] = new Object[] {
	                list.get(i).getRno(),
	                list.get(i).getId(),
	                list.get(i).getPass(),
	                list.get(i).getName(),
	                list.get(i).getBirthday(),
	                list.get(i).getHp(),
	                list.get(i).getAddr(),
	                " "
	            };
	        }
		 
		 member_table = new JTable();
		 member_table.setModel(new DefaultTableModel(rowDatas,colNames) {
	            boolean[] columnEditables = new boolean[] {
	                false, false, false, true, false
	            };
	        });
		
		 member_table.getColumnModel().getColumn(7).setCellRenderer(new TableUpdateCell("삭제", this, mdao,Admin_MemberViewsUI.LIST ));
	     member_table.getColumnModel().getColumn(7).setCellEditor(new TableUpdateCell("삭제", this, mdao,Admin_MemberViewsUI.LIST ));
		
	     member_table.getColumnModel().getColumn(0).setResizable(false);
	     member_table.getColumnModel().getColumn(0).setPreferredWidth(50);
	     member_table.getColumnModel().getColumn(1).setResizable(false);
	     member_table.getColumnModel().getColumn(1).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(2).setResizable(false);
	     member_table.getColumnModel().getColumn(2).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(3).setResizable(false);
	     member_table.getColumnModel().getColumn(3).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(4).setResizable(false);
	     member_table.getColumnModel().getColumn(4).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(5).setResizable(false);
	     member_table.getColumnModel().getColumn(5).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(6).setResizable(false);
	     member_table.getColumnModel().getColumn(6).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(7).setResizable(false);
	     member_table.getColumnModel().getColumn(7).setPreferredWidth(140);
	    
	     
		member_pane = new JScrollPane(member_table);
		member_pane.setEnabled(false);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(member_pane);
		main.content_panel.add(scrollPane, BorderLayout.CENTER);
		main.content_panel.setVisible(true);
	    
		/** 테이블 색 설정 **/
		JTableHeader head = member_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		member_table.setFont(Commons.getFont());

		}
	
	//검색
    public void searchList() {
    	main.content_panel.removeAll();
    	
        slist = mdao.search(comboBox.getSelectedItem().toString(),search_tf.getText());
        String [] colNames = new String [] {"번호","아이디","패스워드","이름","생년월일","HP","주소","삭제"};
        Object[][] rowDatas = new Object[slist.size()][colNames.length];
       
        for (int i = 0; i < slist.size(); i++) {
            rowDatas[i] = new Object[] {
            		list.get(i).getRno(),
  	                list.get(i).getId(),
  	                list.get(i).getPass(),
  	                list.get(i).getName(),
  	                list.get(i).getBirthday(),
  	                list.get(i).getHp(),
  	                list.get(i).getAddr(),
  	                " "
            };
        }
        member_table = new JTable();
        member_table.setModel(new DefaultTableModel(rowDatas,colNames) {
            boolean[] columnEditables = new boolean[] {
                false, false, false, true, false
            };
        });
        
        //JTable에  삭제 버튼 생성 및 이벤트 처리를 위한 데이터 전달
        member_table.getColumnModel().getColumn(7).setCellRenderer(new TableUpdateCell("삭제", this, mdao,Admin_MemberViewsUI.SEARCH ));
	    member_table.getColumnModel().getColumn(7).setCellEditor(new TableUpdateCell("삭제", this, mdao,Admin_MemberViewsUI.SEARCH ));
		
	     member_table.getColumnModel().getColumn(0).setResizable(false);
	     member_table.getColumnModel().getColumn(0).setPreferredWidth(50);
	     member_table.getColumnModel().getColumn(1).setResizable(false);
	     member_table.getColumnModel().getColumn(1).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(2).setResizable(false);
	     member_table.getColumnModel().getColumn(2).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(3).setResizable(false);
	     member_table.getColumnModel().getColumn(3).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(4).setResizable(false);
	     member_table.getColumnModel().getColumn(4).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(5).setResizable(false);
	     member_table.getColumnModel().getColumn(5).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(6).setResizable(false);
	     member_table.getColumnModel().getColumn(6).setPreferredWidth(140);
	     member_table.getColumnModel().getColumn(7).setResizable(false);
	     member_table.getColumnModel().getColumn(7).setPreferredWidth(140);	    
	    
		JScrollPane search_pane = new JScrollPane(member_table);
		search_pane.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(search_pane);
		main.content_panel.add(scrollPane, BorderLayout.CENTER);
		main.content_panel.setVisible(true);
	    
		/** 테이블 색 설정 **/
		JTableHeader head = member_table.getTableHeader();
		head.setBackground(new Color(173, 216, 230));
		head.setForeground(new Color(255,255,255));		
		member_table.setFont(Commons.getFont());
		
		System.out.println("확인완료");
    }    
  //검색 이벤트 처리
    @Override
    public void actionPerformed(ActionEvent e) {
    	Object obj = e.getSource();
    	
    	if(obj == btn_search) {
    		if(search_tf.getText().equals("")) {
//    			init();
    			member_data();
    		}else {
    			searchList();
    		}
    	}
    }
	
	//JTable 수정, 삭제 버튼 생성 및 추가 클래스
	class TableUpdateCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	    JButton jb;
	    MemberDAO mdao;

	    public TableUpdateCell(String name, Admin_MemberViewsUI mlist, MemberDAO mdao, int option) {
	    	this.mdao = mdao;
	        jb = new JButton(name);
	        jb.setFont(Commons.getFont());
	        
	        jb.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String name = e.getActionCommand();            
	            	int result = 0;
	            	
	            	if(name.equals("삭제")){
	            		int confirm=JOptionPane.showConfirmDialog(null, Commons.getMsg("정말로 삭제하시겠습니까?"));
		            	if(confirm==0) {	            		
		            		if(option == Admin_MemberViewsUI.LIST) {
		            			result = mdao.delete(mlist.list.get(mlist.member_table.getSelectedRow()).getId());
		            			
		            		}
		            		
		            		if(result !=0)	mlist.init();
		            	}
	            	}
	            	
	            }
	        });
	    }
	    

	    @Override
	    public Object getCellEditorValue() {
	        return null;
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        return jb;
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
	            int column) {
	        return jb;
	    }
	}//TableUpdateCell class



}
