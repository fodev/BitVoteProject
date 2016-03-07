import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.bit.dao.UserDao;
import com.bit.dao.VoteDao;
import com.bit.domain.Member;
import com.bit.domain.Vote;
import com.bit.domain.VoteChoice;

public class LoginTest {
	Member user;
	@Before
	public void setUp() throws Exception {
		user = new Member("u01","1234");
		
	}

	@Test
	public void test() {
		UserDao userdao= new UserDao();
		Scanner sc = new Scanner(System.in);
		try {
			userdao.loginUser(user);
			System.out.println("�α��� �Ϸ�");
			
			VoteDao votedao = new VoteDao();
			List<Vote> list=votedao.getVoteList(user);
			
			System.out.println("� ���׿� ���ؼ� ��ǥ�Ͻðڽ��ϱ�?");
		
			for (int i =0;i<list.size();i++) {
				System.out.println(i + " : " + list.get(i));
			}
			
			int selectIdx= Integer.parseInt(sc.nextLine());
			
			Vote selectVote = list.get(selectIdx);
			List<VoteChoice> choiceList = votedao.getChoiceList(selectVote);
			
			for (VoteChoice voteChoice : choiceList) {
				System.out.println(voteChoice);
			}
			
			System.out.println("����� ��ǥ�� �Ͻðڽ��ϱ�?");
			selectIdx= Integer.parseInt(sc.nextLine());
			VoteChoice choiceVote= choiceList.get(selectIdx-1);
			
			votedao.excuteVote(user, selectVote, choiceVote);
			System.out.println("��ǥ�� �Ϸ�Ǿ����ϴ�.");
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();						
		}
	}

}
