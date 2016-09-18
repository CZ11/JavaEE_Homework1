import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket socket; // ʵ����һ������socket
	private BufferedReader br;
	private PrintWriter pw;

	public ServerThread(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			System.out.println("�ͻ���:" + socket.getInetAddress() + ":"
					+ socket.getLocalPort());

			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			
			String linestr = null;
			String change = "";

			while ((linestr = br.readLine()) != null) {

				System.out.println("������Ϣ��" + linestr);

				// ��linestr����󣬸�ֵ��change
				for (int i = linestr.length() - 1; i > -1; i--) {
					change = change + String.valueOf(linestr.charAt(i));
				}

				System.out.println("����Ϣ����" + change);

				pw.println("����������Ϣ���������" + change);// ���͸��ͻ���

				change = "";// ��change��ԭ

			}
		} catch (Exception e) {
			System.out.println("���ӶϿ�����");
		} 
	}
}
