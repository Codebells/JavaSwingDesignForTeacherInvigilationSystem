package com.Library.Service;

import java.sql.ResultSet;
import com.Library.Dao.BookDao;

public class BookServise {
	private static BookDao book=new BookDao();
	
	/**
	 * ����һ������
	 * @param exam_pici ��������
	 * @param exam_date	��������
	 * @param begin_time ��ʼʱ��
	 * @param end_time	����ʱ��
	 * @param classroom	���Խ���
	 * @param cno	�γ̺�
	 * @param cname	�γ���
	 * @param mainteacher	������ʦ
	 * @param fu_teacher	������ʦ
	 * @return	�����Ƿ���ӳɹ� boolean
	 */
	public static boolean NewBook(String exam_pici,String exam_date,String begin_time,String end_time,String classroom,String cno,String cname,String mainteacher,String fu_teacher){
		return book.NewBook( exam_pici, exam_date, begin_time, end_time, classroom, cno, cname, mainteacher, fu_teacher);
	}
	
	
	//ͨ��������ŷ���һ�����󣬶����������и��²�����
	public static boolean updataBookexam_pici(String FindNo,String bookname){
		return book.UpdataBookexam_pici(FindNo, bookname);
	}
	public static boolean updataBookON(String FindNo,String bookON){
		return book.UpdataBookID(FindNo, bookON);
	}
	public static boolean updataBookexam_date(String FindNo,String bookPress){
		return book.UpdataBookexam_date(FindNo, bookPress);
	}
	public static boolean updataBookbegin_time(String FindNo,String bookAuthor){
		return book.UpdataBookbegin_time(FindNo, bookAuthor);
	}
	public static boolean updataBookend_time(String FindNo,String bookType){
		return book.UpdataBookend_time(FindNo, bookType);
	}
	public static boolean updataBookclassroom(String FindNo,String bookAuthor){
		return book.UpdataBookclassroom(FindNo, bookAuthor);
	}
	public static boolean updataBookcname(String FindNo,String bookType){
		return book.UpdataBookcname(FindNo, bookType);
	}
	public static boolean updataBookmainteacher(String FindNo,String bookAuthor){
		return book.UpdataBookmainteacher(FindNo, bookAuthor);
	}
	public static boolean updataBookfu_teacher(String FindNo,String bookType){
		return book.UpdataBookfu_teacher(FindNo, bookType);
	}
	
	/**
	 * ɾ�����ԵĲ���
	 * @param FindBookON �ṩ��Ҫɾ���Ŀ��Կγ̺�
	 * @return �����Ƿ�ɾ���ɹ�
	 */
	public static boolean deleteBook(String FindBookON){
		return book.deleteBook(FindBookON);
	}
	
	/**
	 * ���ز��ҵĿ�����Ϣ
	 * @param bookON �ṩ��Ҫ���ҵĿ��Կγ̺�
	 * @return �ҵ�����true��û�ҵ�����false
	 */
	public static boolean findBookByON(String bookON){
		 if(book.FindsBookByID(bookON)!=null)
			 return true;
		 else
			 return false;
	}
	/**
	 * ���չؼ��ֲ��ҿ�����ϸ��Ϣ ���ص���һ�������
	 * @param ���� �ṩ����
	 * @return ���� Result �����
	 */
	public static ResultSet FindsBookexam_pici(String name){
		return book.FindsBookexam_pici(name);
	}
	public static ResultSet FindsBookID(String id){
		return book.FindsBookID(id);
	}
	public static ResultSet FindsBookDate(String id){
		return book.FindsBookDate(id);
	}
	public static ResultSet FindsBookclassroom(String author){
		return book.FindsBookclassroom(author);
	}
	public static ResultSet FindsBookcname(String type){
		return book.FindsBookcname(type);
	}
	public static ResultSet FindsBookmainteacher(String author){
		return book.FindsBookmainteacher(author);
	}
	public static ResultSet FindsBookfu_teacher(String type){
		return book.FindsBookfu_teacher(type);
	}
	public static ResultSet FindsBookAll(){
		return book.FindsBookAll();
	}
}
