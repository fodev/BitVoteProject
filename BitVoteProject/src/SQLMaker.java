import java.io.File;
import java.util.Scanner;

public class SQLMaker {

	//bad code
	public static void main(String[] args) throws Exception{
		String fileName="C:\\zzz\\averageStore.txt";//������ �ȱ����ٴ� ����
		Scanner scanner=new Scanner(new File(fileName));

		while(true){
			try{
				String line=scanner.nextLine();
				System.out.println("str.append(\""+line+"\");");
			}catch(Exception e){
				break;
			}
		}

	}
}
