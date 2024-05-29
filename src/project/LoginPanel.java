package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class LoginPanel extends JPanel {
	private CallBackClientService callbackService;
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
	private JButton cancelbtn;

	public LoginPanel(CallBackClientService callbackService) {
		this.callbackService = callbackService;
		InitData();
		setInitData();
		btnListner();
	}

	public JButton getConnectBtn() {
		return connectBtn;
	}

	public JButton getCancelbtn() {
		return cancelbtn;
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
		connectBtn = new JButton("확인");
		cancelbtn = new JButton("취소");
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

		// 로그인 버튼 컴포넌트
		connectBtn.setBackground(Color.WHITE);
		cancelbtn.setBackground(Color.WHITE);

		connectBtn.setBounds(new Rectangle(45, 270, 100, 30));
		cancelbtn.setBounds(new Rectangle(45, 300, 100, 30));
		connectBtn.setFont(new java.awt.Font("SansSerif", 0, 12));
		cancelbtn.setFont(new java.awt.Font("SansSerif", 0, 12));

		midPanel.add(connectBtn);
		midPanel.add(cancelbtn);

		// 테스트 코드
		inputIp.setText("127.0.0.1");
		inputPort.setText("10000");
	}

	private void btnListner() {
		connectBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickConnectBtn();
			}
		});
	}

	private void clickConnectBtn() {
		System.out.println("버튼 클릭후 아이디 전송");
		if ((!inputIp.getText().equals(null)) && (!inputPort.getText().equals(null))
				&& (!inputId.getText().equals(null))) {

			String ip = inputIp.getText();
			String stringPort = inputPort.getText();
			int port = Integer.parseInt(stringPort);
			String id = inputId.getText();
			JOptionPane.showMessageDialog(null, id + "님 하이요", "알림", JOptionPane.INFORMATION_MESSAGE);
			callbackService.clickConnectServerBtn(ip, port, id);
		} else {
			JOptionPane.showMessageDialog(null, "님아 아이디 입력하세요", "알림", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
