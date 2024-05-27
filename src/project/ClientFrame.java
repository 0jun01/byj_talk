package project;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientFrame extends JFrame {
	private JPanel mainPanel;

	// 로그인 창
	private LoginPanel logPanel;
	// 대기실 창

	// 메세지 창

	public ClientFrame() {
		InitData();
		setInitLayout();
	}

	public void InitData() {
		logPanel = new LoginPanel();
		mainPanel = new JPanel();
	}

	public void setInitLayout() {
		setTitle("[ Thunder Talk ]");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.setLayout(null);
		setContentPane(logPanel);

		setVisible(true);

	}
}
