package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class WaitingRoomPanel extends JPanel {

	// 백그라운드 이미지 컴포넌트
	private Image backgroundImage;
	private JPanel backgroundPanel;

	// 패널
	private JPanel userListPanel;

	// 유저
	public JList<String> userList;

	public Vector<String> userIdVector = new Vector<>();

	private CallBackClientService callBackClientService;

	public WaitingRoomPanel(CallBackClientService callBackClientService) {
		this.callBackClientService = callBackClientService;
		initData();
		setinitLayout();
	}

	public void initData() {
		backgroundImage = new ImageIcon("img/loginBack.jpg").getImage();
		backgroundPanel = new JPanel();

		userListPanel = new JPanel();

		userList = new JList<>();

	}

	public void setinitLayout() {
		setSize(getWidth(), getHeight());
		setLayout(null);

		userListPanel.setBounds(130, 30, 120, 400);
		userListPanel.setBackground(Color.WHITE);
		userListPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3), "user List"));
//		userIdVector.add("영준아 쏴라");
		userList.setListData(userIdVector);
		userListPanel.add(userList);
		add(userListPanel);

		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
