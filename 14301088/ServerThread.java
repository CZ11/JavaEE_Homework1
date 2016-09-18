import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket socket; // 实例化一个服务socket
	private BufferedReader br;
	private PrintWriter pw;

	public ServerThread(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			System.out.println("客户端:" + socket.getInetAddress() + ":"
					+ socket.getLocalPort());

			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			
			String linestr = null;
			String change = "";

			while ((linestr = br.readLine()) != null) {

				System.out.println("接受信息：" + linestr);

				// 将linestr逆序后，赋值给change
				for (int i = linestr.length() - 1; i > -1; i--) {
					change = change + String.valueOf(linestr.charAt(i));
				}

				System.out.println("将信息逆序：" + change);

				pw.println("服务器将信息逆序输出：" + change);// 发送给客户端

				change = "";// 将change还原

			}
		} catch (Exception e) {
			System.out.println("连接断开：（");
		} 
	}
}
