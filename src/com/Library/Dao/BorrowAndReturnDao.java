package com.Library.Dao;

import java.util.ArrayList;

import com.Library.DataBase.DataBase;

/**
 * �Խ軹��ĵ�Data����
 * @author Codebells
 *
 */
public class BorrowAndReturnDao {
	private UserDao user=new UserDao();
	private BookDao book=new BookDao();
	//���ж�����û�������Щ��--�����鼮������ �����ж��Ƿ���������ȣ�����ɾ�����������鼮�ĺ�start����Ϊ1��
	public  boolean examdelete(String userid,String bookid){
		ArrayList<String> booksid=user.UserBorrowedBooks(userid);
		if(booksid!=null){
		for(Object i:booksid){
			if(i.equals(bookid)){
				DataBase.sendUpdateSQL("delete from BorrowAndReturn where cno='"+bookid+"'");
				DataBase.sendUpdateSQL("update bmsuser_numer set booksize=booksize-1 where userid='"+userid+"'");
				return true;
			}
		}
		}
		return false;
	}
	
	public  boolean BookBorrow(String userid ,String mainteacher,String futeacher){
		String Result[]=book.FindsBookByID(mainteacher);
		if(mainteacher.equals(Result[0]) && "1".equals(Result[5])){
			int i=DataBase.sendUpdateSQL("insert into BorrowAndReturn(cno,mainteacher,fu_teacher) values('"+userid+"','"+mainteacher+"','"+futeacher+"')");
			if(i!=0){
				DataBase.sendUpdateSQL("update bmsuser set state=1 where Uname='"+mainteacher+"'");
				DataBase.sendUpdateSQL("update bmsuser_numer set booksize=booksize+1 where userid='"+userid+"'");
				return true;
			}
				else
					return false;
		}
		else
			return false;
	}
}
