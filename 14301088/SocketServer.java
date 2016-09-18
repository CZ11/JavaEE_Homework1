import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {

	private static ServerSocket serverSocket; // 实例化一个服务socket
	private static Socket socket;
	private static int count = 1; // 客户端数量计算

	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer();

		Thread t = new Thread(server);
		t.start();
	}

	public SocketServer() throws IOException {
		try {
			serverSocket = new ServerSocket(3333);
			System.out.println("服务器启动。。。");
		} catch (IOException e) {
			System.out.println("服务器绑定端口号失败！");
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("客户端数量：" + count);
				count++;

				// 当有客户端连接时，为每个客户端开辟独立线程执行
				Thread t = new Thread(new ServerThread(socket));
				t.start();
			} catch (Exception e) {
				System.out.println("连接断开：（");
			}
		}

	}

}
