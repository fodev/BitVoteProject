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
			System.out.println("로그인 완료");
			
			VoteDao votedao = new VoteDao();
			List<Vote> list=votedao.getVoteList(user);
			
			System.out.println("어떤 문항에 대해서 투표하시겠습니까?");
		
			for (int i =0;i<list.size();i++) {
				System.out.println(i + " : " + list.get(i));
			}
			
			int selectIdx= Integer.parseInt(sc.nextLine());
			
			Vote selectVote = list.get(selectIdx);
			List<VoteChoice> choiceList = votedao.getChoiceList(selectVote);
			
			for (VoteChoice voteChoice : choiceList) {
				System.out.println(voteChoice);
			}
			
			System.out.println("몇번에 투표를 하시겠습니까?");
			selectIdx= Integer.parseInt(sc.nextLine());
			VoteChoice choiceVote= choiceList.get(selectIdx-1);
			
			votedao.excuteVote(user, selectVote, choiceVote);
			System.out.println("투표가 완료되었습니다.");
		}catch(Exception e){
			System.out.println(e.getMessage());
			//e.printStackTrace();						
		}
	}

}
