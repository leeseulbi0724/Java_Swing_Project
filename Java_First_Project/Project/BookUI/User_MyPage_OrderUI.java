package BookUI;

public class User_MyPage_OrderUI {
User_MyPageUI main;
	
	public User_MyPage_OrderUI(User_MyPageUI main) {
		this.main = main;
		init();
	}
	
	public void init() {
		main.switching(User_MyPageUI.Order);
	}
}
