package project;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ClientFrame extends JFrame {

	private CallBackClientService callbackService;

	// 탭
	private JTabbedPane tabPane;

	// 메인 패널
	private JPanel mainPanel;

	// 로그인 창
	private LoginPanel logPanel;

	// 대기실 창
	private WaitingRoomPanel waitingRoom;

	// 메세지 창
	private MessagePanel messagePanel;

	public ClientFrame(CallBackClientService callbackService) {
		this.callbackService = callbackService;
		InitData();
		setInitLayout();
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

	public LoginPanel getLogPanel() {
		return logPanel;
	}

	public void InitData() {
		logPanel = new LoginPanel(callbackService);
		mainPanel = new JPanel();
		messagePanel = new MessagePanel(callbackService);
		waitingRoom = new WaitingRoomPanel(callbackService);
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

		tabPane.addTab("대화상대", null, waitingRoom, null);

		setVisible(true);

	}
}
