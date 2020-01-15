package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.DataBase.DataBase;

/**
 * �Կ��ԵĲ���
 * ��ӣ�ɾ�������£�����
 * @author Codebells
 *
 */
public class BookDao {
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
	public  boolean NewBook(String exam_pici,String exam_date,String begin_time,String end_time,String classroom,String cno,String cname,String mainteacher,String fu_teacher){
		String sql="insert into  bmsbook(exam_pici,exam_date,begin_time,end_time,classroom,cno,cname,mainteacher,fu_teacher) values(' "+exam_pici+"','"+exam_date+"','"+begin_time+"','"+end_time+"','"+classroom+"','"+cno+"','"+cname+"','"+mainteacher+"','"+fu_teacher+  "')";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)
			return true;
			else
				return false;
	}
	
	/**
	 * ɾ�����ԵĲ��� �ṩ�γ̺�
	 * @param cno	�γ̺�
	 * @return	����Boolean �Ƿ��޸ĳɹ�
	 */
	public  boolean deleteBook(String cno){
		String sql="delete from bmsbook where cno='"+cno+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	//���²���***************************
	public  boolean UpdataBookID(String cno,String newid){
		int i=DataBase.sendUpdateSQL("update bmsbook set cno='"+newid+"' where bookid='"+cno+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookexam_pici(String bookid,String newName){
		int i=DataBase.sendUpdateSQL("update bmsbook set exam_pici='"+newName+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookexam_date(String bookid,String newauthor){
		int i=DataBase.sendUpdateSQL("update bmsbook set exam_date='"+newauthor+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookbegin_time(String bookid,String newpress){
		int i=DataBase.sendUpdateSQL("update bmsbook set begin_time='"+newpress+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookend_time(String bookid,String newpress){
		int i=DataBase.sendUpdateSQL("update bmsbook set end_time='"+newpress+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookclassroom(String bookid,String newtype){
		int i=DataBase.sendUpdateSQL("update bmsbook set classroom='"+newtype+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookcname(String bookid,String newpress){
		int i=DataBase.sendUpdateSQL("update bmsbook set cname='"+newpress+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookmainteacher(String bookid,String newtype){
		int i=DataBase.sendUpdateSQL("update bmsbook set mainteacher='"+newtype+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	public  boolean UpdataBookfu_teacher(String bookid,String newpress){
		int i=DataBase.sendUpdateSQL("update bmsbook set fu_teacher='"+newpress+"' where bookid='"+bookid+"'");
		if(i!=0)		
		return true;
		else
			return false;
	}
	//���²�����β***************************
	
	
	/**
	 * ���ҿ�����Ϣ��ͨ��cno
	 * @param cno �γ̺�
	 * @return	����һ����9���������ַ�������.���û���ҵ��򷵻ص���һ��ȫ��Ϊ��(null)�����顣
	 * @param exam_pici ��������
	 * @param exam_date	��������
	 * @param begin_time ��ʼʱ��
	 * @param end_time	����ʱ��
	 * @param classroom	���Խ���
	 * @param cno	�γ̺�
	 * @param cname	�γ���
	 * @param mainteacher	������ʦ
	 * @param fu_teacher	������ʦ
	 */
	@SuppressWarnings("finally")
	public  String[] FindsBookByID(String bookid){
		String sql="select * from bmsbook where cno='"+bookid+"'";
		ResultSet rs=DataBase.sendQuerySQL(sql);
		if(rs!=null){
			String Result[]=new String[9];
			try {
				while(rs.next()){
					Result[0]=rs.getString("exam_pici");
					Result[1]=rs.getString("exam_date");
					Result[2]=rs.getString("begin_time");
					Result[3]=rs.getString("end_time");
					Result[4]=rs.getString("classroom");
					Result[5]=rs.getString("cno");
					Result[6]=rs.getString("cname");
					Result[7]=rs.getString("mainteacher");
					Result[8]=rs.getString("fu_teacher");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				return Result;
			}
		}else
			return null;
	}
	public ResultSet FindsBookexam_pici(String type){
		return  DataBase.sendQuerySQL("select *from bmsbook where exam_pici like '"+type+"%'");
	}
	public ResultSet FindsBookclassroom(String name){
		return DataBase.sendQuerySQL("select *from bmsbook where classroom like '"+name+"%'");
	}
	public ResultSet FindsBookID(String id){
		return  DataBase.sendQuerySQL("select *from bmsbook where cno like '"+id+"%'");
	}
	public ResultSet FindsBookDate(String id){
		return  DataBase.sendQuerySQL("select *from bmsbook where exam_date like '"+id+"%'");
	}
	public ResultSet FindsBookcname(String author){
		return  DataBase.sendQuerySQL("select *from bmsbook where cname like '"+author+"%'");
	}
	public ResultSet FindsBookmainteacher(String type){
		return  DataBase.sendQuerySQL("select *from bmsbook where mainteacher like '"+type+"%'");
	}
	public ResultSet FindsBookfu_teacher(String type){
		return  DataBase.sendQuerySQL("select *from bmsbook where fu_teacher like '"+type+"%'");
	}
	public ResultSet FindsBookAll(){
		return  DataBase.sendQuerySQL("select * from bmsbook");
	}
}
