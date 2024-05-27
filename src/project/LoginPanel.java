package project;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
	
	// 백그라운드 패널
	private JPanel backgroundPanel;

	public LoginPanel() {
		InitData();
		setInitData();
	}

	public void InitData() {
		backgroundPanel = new JPanel();
	}

	public void setInitData() {
		
		setSize(400, 500);
	}

}
