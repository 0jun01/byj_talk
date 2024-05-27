package project;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Server {
	private static final int PORT = 10000;
	ServerFrame serverFrame;

	// 서버 소켓 장치
	ServerSocket serverSocket;
	Socket socket;

	// 파일 저장을 위한 장치
	private FileWriter fileWriter;

	public Server() {
		serverFrame = new ServerFrame(this);
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
			fileWriter.write(alert);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server();
	}

}
