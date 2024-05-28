package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class MessagePanel extends JPanel {

	// 백그라운드 이미지 컴포넌트
	private Image backgroundImage;
	private JPanel backgroundPanel;

	// 패널
	private JPanel mainPanel;
	private JPanel bottomPanel;

	// 스크롤
	private ScrollPane scrollPane;

	// 텍스트 컴포넌트
	private JTextArea mainMessageBox;
	private JTextField writeMessageBox;

	// 전송 버튼
	private JButton sendMessageBtn;

	public MessagePanel() {
		initData();
		setInitLayout();
	}

	public void initData() {
		backgroundImage = new ImageIcon("img/loginBack.jpg").getImage();
		backgroundPanel = new JPanel();

		mainPanel = new JPanel();
		bottomPanel = new JPanel();

		scrollPane = new ScrollPane();

		mainMessageBox = new JTextArea();
		writeMessageBox = new JTextField(17);
		sendMessageBtn = new JButton("전송");

	}

	public void setInitLayout() {
		setSize(getWidth(), getHeight());
		setLayout(null);

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

//		mainMessageBox.setEnabled(false);
		mainPanel.setBounds(40, 20, 300, 350);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(scrollPane);
		scrollPane.setBounds(45, 15, 280, 310);
		scrollPane.add(mainMessageBox);
		add(mainPanel);

		sendMessageBtn.setBackground(Color.WHITE);
//		sendMessageBtn.setEnabled(false);
		bottomPanel.setBounds(43, 380, 294, 35);
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.add(writeMessageBox);
		bottomPanel.add(sendMessageBtn);
		add(bottomPanel);

	}

	public void initListner() {
		sendMessageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	private void sendMessage() {
		if (!writeMessageBox.getText().equals(null)) {
			String message = writeMessageBox.getText();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
}
