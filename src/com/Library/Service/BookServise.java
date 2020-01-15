package com.Library.Service;

import java.sql.ResultSet;
import com.Library.Dao.BookDao;

public class BookServise {
	private static BookDao book=new BookDao();
	
	/**
	 * 新增一场考试
	 * @param exam_pici 考试批次
	 * @param exam_date	考试日期
	 * @param begin_time 开始时间
	 * @param end_time	结束时间
	 * @param classroom	考试教室
	 * @param cno	课程号
	 * @param cname	课程名
	 * @param mainteacher	主考老师
	 * @param fu_teacher	副考老师
	 * @return	返回是否添加成功 boolean
	 */
	public static boolean NewBook(String exam_pici,String exam_date,String begin_time,String end_time,String classroom,String cno,String cname,String mainteacher,String fu_teacher){
		return book.NewBook( exam_pici, exam_date, begin_time, end_time, classroom, cno, cname, mainteacher, fu_teacher);
	}
	
	
	//通过查找书号返回一个对象，对这个对象进行更新操作；
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
	 * 删除考试的操作
	 * @param FindBookON 提供需要删除的考试课程号
	 * @return 返回是否删除成功
	 */
	public static boolean deleteBook(String FindBookON){
		return book.deleteBook(FindBookON);
	}
	
	/**
	 * 返回查找的考试信息
	 * @param bookON 提供需要查找的考试课程号
	 * @return 找到返回true，没找到返回false
	 */
	public static boolean findBookByON(String bookON){
		 if(book.FindsBookByID(bookON)!=null)
			 return true;
		 else
			 return false;
	}
	/**
	 * 按照关键字查找考试详细信息 返回的是一个结果集
	 * @param 参数 提供参数
	 * @return 返回 Result 结果集
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
