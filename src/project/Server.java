package project;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server {

	private static final int PORT = 10000;

	private ServerFrame serverFrame;
	private JTextArea mainBoard;

	// 서버 소켓 장치
	private ServerSocket serverSocket;
	private Socket socket;

	// 파일 저장을 위한 장치
	private FileWriter fileWriter;

	public Server() {
		serverFrame = new ServerFrame(this);
		mainBoard = serverFrame.getMainBoard();
	}

	public void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			serverViewer("[알림] 서버 시작\n");
			serverFrame.getConnectBtn().setEnabled(false);
			serverFrame.getMainPanel().setVisible(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다", "알림", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void serverViewer(String alert) {
		try {
			fileWriter = new FileWriter("Thunder_talk.txt", true);
			mainBoard.append(alert);
			fileWriter.write(alert);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void autoScroll() {
		int len = mainBoard.getDocument().getLength();
		System.out.println(len);
		mainBoard.setCaretPosition(len);
	}

	public static void main(String[] args) {
		new Server();
	}

}
