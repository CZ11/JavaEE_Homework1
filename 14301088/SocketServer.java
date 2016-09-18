import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {

	private static ServerSocket serverSocket; // ʵ����һ������socket
	private static Socket socket;
	private static int count = 1; // �ͻ�����������

	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer();

		Thread t = new Thread(server);
		t.start();
	}

	public SocketServer() throws IOException {
		try {
			serverSocket = new ServerSocket(3333);
			System.out.println("����������������");
		} catch (IOException e) {
			System.out.println("�������󶨶˿ں�ʧ�ܣ�");
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("�ͻ���������" + count);
				count++;

				// ���пͻ�������ʱ��Ϊÿ���ͻ��˿��ٶ����߳�ִ��
				Thread t = new Thread(new ServerThread(socket));
				t.start();
			} catch (Exception e) {
				System.out.println("���ӶϿ�����");
			}
		}

	}

}
