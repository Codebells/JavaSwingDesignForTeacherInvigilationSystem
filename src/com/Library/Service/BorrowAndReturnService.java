package com.Library.Service;


import com.Library.Dao.BorrowAndReturnDao;


public class BorrowAndReturnService {
	private static BorrowAndReturnDao book=new BorrowAndReturnDao();
	/**
	 * 删除考试操作
	 * @param BookON 键入考试课程号
	 * @param UserID 键入考试课程名
	 * @return 还书成功返回true,否则返回false
	 */
	public static boolean Bookreturn(String BookON,String UserID){
		return book.examdelete(UserID, BookON);
	}
	/**
	 * 借书操作
	 * @param BookON 键入考试课程号
	 * @param UserID 键入考试课程名
	 * @return 借书成功返回true,否则返回false
	 */
	public static boolean BookBorrow(String userid ,String mainteacher,String futeacher){
		return book.BookBorrow( userid , mainteacher, futeacher);
	}
}
