import com.bit.ui.ChatServer;

public class Main {
	public static void main(String[] args) {
//		try{
//			new LoginUI().startUI();
//			new VoteUI().startUI();
//		}catch(Exception e){
//			System.out.println(e.getMe-ssage());
//		}
		
		try {
			ChatServer.getInstance().openServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
