package com.Library.Service;


import com.Library.Dao.BorrowAndReturnDao;


public class BorrowAndReturnService {
	private static BorrowAndReturnDao book=new BorrowAndReturnDao();
	/**
	 * ɾ�����Բ���
	 * @param BookON ���뿼�Կγ̺�
	 * @param UserID ���뿼�Կγ���
	 * @return ����ɹ�����true,���򷵻�false
	 */
	public static boolean Bookreturn(String BookON,String UserID){
		return book.examdelete(UserID, BookON);
	}
	/**
	 * �������
	 * @param BookON ���뿼�Կγ̺�
	 * @param UserID ���뿼�Կγ���
	 * @return ����ɹ�����true,���򷵻�false
	 */
	public static boolean BookBorrow(String userid ,String mainteacher,String futeacher){
		return book.BookBorrow( userid , mainteacher, futeacher);
	}
}
