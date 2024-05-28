package project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ClientFrame extends JFrame {

	Client mContext;

	// 탭
	private JTabbedPane tabPane;

	// 메인 패널
	private JPanel mainPanel;

	// 로그인 창
	private LoginPanel logPanel;
	// 대기실 창

	// 메세지 창
	private MessagePanel messagePanel;

	public ClientFrame(Client mContext) {
		this.mContext = mContext;
		InitData();
		setInitLayout();
	}

	public void InitData() {
		logPanel = new LoginPanel(mContext);
		mainPanel = new JPanel();
		messagePanel = new MessagePanel();
		tabPane = new JTabbedPane(JTabbedPane.TOP);
	}

	public void setInitLayout() {
		setTitle("[ Thunder Talk ]");
		setSize(400, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabPane.setBounds(0, 0, getWidth(), getHeight());
		mainPanel.add(tabPane);

		mainPanel.setLayout(null);
		setContentPane(mainPanel);

		tabPane.addTab("로그인", null, logPanel, null);

		tabPane.addTab("채팅", null, messagePanel, null);

		setVisible(true);

	}
}
