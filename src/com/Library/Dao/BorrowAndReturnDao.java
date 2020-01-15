package com.Library.Dao;

import java.util.ArrayList;

import com.Library.DataBase.DataBase;

/**
 * 对借还书的的Data操作
 * @author Codebells
 *
 */
public class BorrowAndReturnDao {
	private UserDao user=new UserDao();
	private BookDao book=new BookDao();
	//先判断这个用户借了那些书--返回书籍的名称 进行判断是否相等如果相等，进行删除操作，把书籍的号start设置为1；
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
