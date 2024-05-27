package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LoginPanel extends JPanel {

	// 백그라운드 패널
	private Image backgroundImage;
	private JPanel backgroundPanel;

	// 미드 컴포넌트
	private JPanel midPanel;

	// ip 컴포넌트
	private JPanel ipPanel;
	private JLabel ipLabel;
	private JTextField inputIp;

	// port 컴포넌트
	private JPanel portPanel;
	private JLabel portLabel;
	private JTextField inputPort;

	// id 컴포넌트
	private JPanel idPanel;
	private JLabel idLabel;
	private JTextField inputId;

	// 로그인 버튼
	private JButton connectBtn;

	public LoginPanel() {
		InitData();
		setInitData();
	}

	public void InitData() {
		// 백그라운드 이미지 컴포넌트
		backgroundImage = new ImageIcon("img/loginBack.jpg").getImage();
		backgroundPanel = new JPanel();

		// 미드 컴포넌트
		midPanel = new JPanel();

		// ip 컴포넌트
		ipPanel = new JPanel();
		ipLabel = new JLabel("HOST IP");
		inputIp = new JTextField(10);

		// Port 컴포넌트
		portPanel = new JPanel();
		portLabel = new JLabel("PORT NUMBER");
		inputPort = new JTextField(10);

		// id 컴포넌트
		idPanel = new JPanel();
		idLabel = new JLabel("ID");
		inputId = new JTextField(10);

		// 로그인 버튼
		connectBtn = new JButton("Connect");
	}

	public void setInitData() {
		setSize(getWidth(), getHeight());
		setLayout(null);

		// 백그라운드 이미지 패널
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		// 미드 컴포넌트
		midPanel.setBounds(100, 60, 190, 380);
		midPanel.setLayout(null);
		midPanel.setBackground(Color.WHITE);
		midPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
		add(midPanel);

		// IP 컴포넌트
		ipPanel.setBounds(35, 60, 120, 100);
		ipPanel.setBackground(new Color(0, 0, 0, 0));
		ipPanel.add(ipLabel);
		ipPanel.add(inputIp);
		midPanel.add(ipPanel);

		// PORT 컴포넌트
		portPanel.setBounds(35, 140, 120, 100);
		portPanel.setBackground(new Color(0, 0, 0, 0));
		portPanel.add(portLabel);
		portPanel.add(inputPort);
		midPanel.add(portPanel);

		// id 컴포넌트
		idPanel.setBounds(35, 220, 120, 100);
		idPanel.setBackground(new Color(0, 0, 0, 0));
		idPanel.add(idLabel);
		idPanel.add(inputId);
		midPanel.add(idPanel);

		// LoginBtn 컴포넌트
		connectBtn.setBackground(Color.WHITE);
		connectBtn.setBounds(50, 300, 90, 20);
		midPanel.add(connectBtn);

		// 테스트 코드
		inputIp.setText("127.0.0.1");
		inputPort.setText("10000");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
