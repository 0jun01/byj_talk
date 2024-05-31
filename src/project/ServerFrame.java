package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerFrame extends JFrame {

	private Server mContext;

	private ScrollPane scrollPane;

	// 백그라운드 패널
	private JPanel backgroundPanel;

	// 메인보드
	private JPanel mainPanel;
	private JTextArea mainBoard;

	// 포트 패널
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;
	private JButton connectBtn;
	private ImageIcon btnIcon;
	private ImageIcon btnIcon2;

	public ServerFrame(Server mContext) {
		this.mContext = mContext;
		InitData();
		setInitLayout();
		btnListener();
	}

	public JButton getConnectBtn() {
		return connectBtn;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JTextField getInputPort() {
		return inputPort;
	}

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	public void InitData() {
		// 백그라운드 패널
		backgroundPanel = new BackgroundPanel();

		// 메인 패널
		mainPanel = new JPanel();
		mainBoard = new JTextArea();

		scrollPane = new ScrollPane();

		// 포트 패널
		portPanel = new JPanel();
		portLabel = new JLabel("PORT NUMBER");
		inputPort = new JTextField(10);
		btnIcon = new ImageIcon("img/button.png");
		btnIcon2 = new ImageIcon("img/button2.png");
		connectBtn = new JButton(btnIcon);

		// 임시포트
		inputPort.setText("10000");
	}

	public void setInitLayout() {
		setTitle("[ Thunder Talk ]");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		// 백그라운드 패널
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);
		
		// 버튼
		connectBtn.setRolloverIcon(btnIcon2);
		connectBtn.setBorderPainted(false);
		connectBtn.setPreferredSize(new Dimension(54,23));

		// 포트패널 컴포넌트
		portLabel.setForeground(Color.white);
		portPanel.setBounds(50, 40, 300, 50);
		portPanel.setBackground(new Color(0, 0, 0, 0));

		portPanel.add(portLabel);
		portPanel.add(inputPort);
		portPanel.add(connectBtn);
		backgroundPanel.add(portPanel);

		// 메인패널 컴포넌트
		mainPanel.setBounds(40, 100, 320, 350);
		mainPanel.setBackground(Color.WHITE);

		mainPanel.add(scrollPane);
		scrollPane.setBounds(45, 50, 320, 340);
		scrollPane.add(mainBoard);
		backgroundPanel.add(mainPanel);
		mainPanel.setVisible(false);

		setVisible(true);
	}

	private void btnListener() {
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mContext.startServer();
			}
		});
	}

	public void autoScroll() {
		int len = inputPort.getDocument().getLength();
		inputPort.setCaretPosition(len);
	}

	private class BackgroundPanel extends JPanel {
		private JPanel backgroundPanel;
		private Image backgroundImage;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon("img/enterChat.png").getImage();
			backgroundPanel = new JPanel();
			add(backgroundPanel);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
	}

}
