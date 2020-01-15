package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.DataBase.DataBase;

/**
 * 对管理员用户进行操作的类！
 * 功能有对管理员用户的添加、删除、更新,账号查找
 * @author Codebells
 *
 */
public class AdminDao {
	/**
	 * 添加一个新管理员到数据库中
	 * @param id	账户
	 * @param pwd	密码
	 * @return	返回boolean 是否添加成功
	 */
	public  boolean NewAdmin(String id,String pwd){
		String sql="insert into bmsadmin values ('"+id+"','"+pwd+"')";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)
		return true;
		else
			return false;
	}
	
	/**
	 * 修改管理员的密码操作的方法
	 * @param id 提供要修改的管理员账户号码
	 * @param pwd 新的密码
	 * @return 返回Boolean 是否修改成功
	 */
	public  boolean UpdataAdmin(String id,String pwd){	
		String sql="update bmsadmin set pwd='"+pwd+"' where id='"+id+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	/**
	 * 删除管理员的操作 提供账户号码
	 * @param id	账号
	 * @return	返回Boolean 是否修改成功
	 */
	public  boolean deleteAdmin(String id){
		String sql="delete from bmsadmin where id='"+id+"'";
		int i=DataBase.sendUpdateSQL(sql);
		if(i!=0)		
		return true;
		else
			return false;
	}
	
	/**
	 * 管理员进行登录操作的验证方法
	 * @param numer 登录的账号
	 * @param pwd	登录的密码
	 * @return	返回是否登录成功 boolean
	 */
	public  boolean Login(String id,String pwd){
	
		ResultSet rs=DataBase.sendQuerySQL("select pwd from bmsadmin where id='"+id+"'");
		try {
			rs.next();
			if(pwd.equals(rs.getString("pwd")))
				return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
		
	}
}
