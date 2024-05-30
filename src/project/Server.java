package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server {

	private static final int PORT = 10000;

	// 접속된 유저 벡터
	private static Vector<ClientHandler> connecteduser = new Vector<>();
	private static ArrayList<String> nickNameList = new ArrayList<>();

	private ServerFrame serverFrame;
	private JTextArea mainBoard;

	// 서버 소켓 장치
	private ServerSocket serverSocket;
	private Socket socket;

	// 토큰나이저 사용변수
	private String message;
	private String protocol;
	private String from;

	// 파일 저장을 위한 장치
	private FileWriter fileWriter;

	public Server() {
		serverFrame = new ServerFrame(this);
		mainBoard = serverFrame.getMainBoard();
	}

	public void startServer() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("서버 시작");
			serverViewer("[알림] 서버 시작\n");
			connectClient();
			serverFrame.getConnectBtn().setEnabled(false);
			serverFrame.getMainPanel().setVisible(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다", "알림", JOptionPane.ERROR_MESSAGE);
			System.out.println("사용중인데요");
		}
	}

	public void stopServer() {
		try {
			serverSocket.close();
			serverViewer("서버종료\n");
			serverFrame.getConnectBtn().setEnabled(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connectClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						socket = serverSocket.accept();
						System.out.println("ip, port, id 전송 완료");
						serverViewer("[알림] 사용자 접속 대기중 \n");
						ClientHandler user = new ClientHandler(socket);
//						connecteduser.add(user);
						user.start();

					} catch (IOException e) {
						// 서버 중지
						System.out.println("오류");
						serverViewer("[에러] 접속 에러!!!!! \n");
					}
				}
			}
		}).start();
	}

	private void broadcastMessage(String message) {
		for (int i = 0; i < connecteduser.size(); i++) {
			ClientHandler user = connecteduser.elementAt(i);
			user.writer(message);
		}
	}

	private void serverViewer(String alert) {
		try {
			fileWriter = new FileWriter("Thunder_talk.txt", true);
			mainBoard.append(alert);
			fileWriter.write(alert);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeClient(ClientHandler client, String nickName) {
		connecteduser.removeElement(client);
		nickNameList.remove(nickName);

	}
//	public void autoScroll() {
//		boolean flag;
//		while (true) {
//			int len = mainBoard.getDocument().getLength();
//			int prelen = len;
//			mainBoard.setCaretPosition(len);
//			if (len == prelen) {
//				flag = false;
//			}
//		}
//	}

	// 모든 클라이언트에게 메세지 보내기 - 브로드캐스트

	private class ClientHandler extends Thread implements ProtocolImpl {
		// 소켓 장치
		private Socket socket;

		// 입출력 장치
		private BufferedReader in;
		private BufferedWriter out;

		// 유저 정보
		String id;

		public ClientHandler(Socket socket) {
			this.socket = socket;
			connecIO();
		}

		private void connecIO() {
			try {
				// 입출력 장치
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				sendInFormation();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void sendInFormation() {
			try {
				id = in.readLine();
				serverViewer("[접속] " + id + "님\n");

				newUser();

				connectedUser();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {

				while (true) {
					String str = in.readLine();
					mainBoard.append(str + "\n");
					checkProtocol(str);
				}
			} catch (IOException e) {
				serverViewer(id + "님이 연결을 끊음\n");
				e.printStackTrace();
			}
		}

		private void checkProtocol(String str) {
			StringTokenizer tokenizer = new StringTokenizer(str, "/");

			protocol = tokenizer.nextToken();
			from = tokenizer.nextToken();
			if (protocol.equals("Chatting")) {
				message = tokenizer.nextToken();
				chatting();
			}
		}

		private void writer(String str) {
			try {
				out.write(str + "\n");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void chatting() {
			serverViewer(id + " : " + message + "\n");
			broadcastMessage("Chatting/" + id + "/" + message);
//			try {
//				out.write(message);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		}

		@Override
		public void newUser() {
			connecteduser.add(this);
			broadcastMessage("NewUser/" + id);
		}

		@Override
		public void connectedUser() {
			for (int i = 0; i < connecteduser.size(); i++) {
				ClientHandler user = connecteduser.elementAt(i);
				writer("ConnectedUser/" + user.id);
			}
		}
	}

	public static void main(String[] args) {
		new Server();
	}

}
