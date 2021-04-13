package Commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JTextField;

public class Commons {
		//Field
		//Constructor
		//Method
	/** 각자 본인 폰트로 바꾸셔서 쓰시면 됩니다 **/
	/** 폰트 **/
	public static Font getFont() {
		Font font = new Font("LG PC", Font.PLAIN, 13);
		return font;
	}
	public static Font getFont(int num) {
		Font font = new Font("LG PC", Font.PLAIN, num);
		return font;
	}	
	
	/** 라벨 **/
	public static Label getMsg(String msg) {
			Font font = new Font("LG PC", Font.PLAIN, 10);
			Label label = new Label(msg);
			label.setFont(font);
			return label;
	}
	
	
	
	
}
