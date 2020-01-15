package Test;



import java.sql.SQLException;

import com.Library.Service.*;

public class test {

	public static void main(String[] arge) throws SQLException{		
		SentMail mail=new SentMail("419683141@qq.com");
		System.out.println(mail.Sent());
	}

}
