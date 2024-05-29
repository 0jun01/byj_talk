package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Client implements CallBackClientService, ProtocolImpl {

	// 프레임 창
	private ClientFrame clientFrame;

	// client의 화면 부분의 컴포넌트를 멤버변수로 가져와 담을 변수
	private JTextArea mainMessageBox;
	private JButton sendMessageBtn;

	// 소켓 장치
	private Socket socket;

	// 입출력 장치
	private BufferedReader reader;
	private BufferedWriter writer;

	// 연결 주소
	private String ip;
	private int port;

	// 유저 정보
	private String id;
	private String name;

	// 토크나이저 사용 변수
	private String protocol;
	private String from;
	private String message;

	// 완
	public Client() {
		clientFrame = new ClientFrame(this);
		mainMessageBox = clientFrame.getMessagePanel().getMainMessageBox();
		sendMessageBtn = clientFrame.getMessagePanel().getSendMessageBtn();
	}

	// connect버튼을 눌렀을때 실행된다.
	// 완
	@Override
	public void clickConnectServerBtn(String ip, int port, String id) {
		this.ip = ip;
		this.port = port;
		this.id = id;
		try {
			connectNetwork();
			connectIO();

			writer.write(id.trim() + "\n");
			writer.flush();
			clientFrame.setTitle("[Thunder Talk " + id + "님 ]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 소켓 장치 연결
	// 완
	private void connectNetwork() {
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 입출력 장치 연결!
	// 완
	private void connectIO() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			readThread();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 서버측으로부터 온 데이터 읽기
	private void readThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						String msg = reader.readLine();
						mainMessageBox.append(msg + "\n");
						// checkProtocol(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	// 완
	private void writer(String str) {
		try {
			writer.write(str + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void checkProtocol(String msg) {
//		StringTokenizer tokenizer = new StringTokenizer(msg, "/");
//
//		protocol = tokenizer.nextToken();
//		from = tokenizer.nextToken();
//
//		if (protocol.equals("Chatting")) {
//			message = tokenizer.nextToken();
//			chatting();
//		}
//
//	}

	@Override
	public void chatting() {

	}

	// 완
	@Override
	public void clickSendMessageBtn(String messageText) {
		writer(id + " : " + messageText);
	}

	@Override
	public void newUser() {
		writer(id + "님이 접속함\n");

	}

	public static void main(String[] args) {
		new Client();
	}

}