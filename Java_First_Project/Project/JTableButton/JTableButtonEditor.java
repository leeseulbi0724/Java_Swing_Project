package JTableButton;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import BookUI.User_MyPage_MyUI;


public class JTableButtonEditor implements TableCellEditor {
	
	JButton btn;
	Boolean clicked;
	
	public JTableButtonEditor(User_MyPage_MyUI main) {
		this.btn = main.btn_delete;
		btn.setOpaque(true);
	}
	
    @Override
    public void addCellEditorListener(CellEditorListener arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void cancelCellEditing() {
        // TODO Auto-generated method stub
    }
    @Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public boolean isCellEditable(EventObject arg0) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void removeCellEditorListener(CellEditorListener arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public boolean shouldSelectCell(EventObject arg0) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean stopCellEditing() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public Component getTableCellEditorComponent(JTable arg0, Object arg1, 
    		boolean arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
    	clicked = true;
        return null;
    }
}
