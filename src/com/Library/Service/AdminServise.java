package com.Library.Service;

import com.Library.Dao.*;

public class AdminServise {
	private static AdminDao admin=new AdminDao();
	private static UserDao user=new UserDao();
	private static String ID;
	private static String PWD;
	/**
	 * 添加一个新的管理员账户
	 * @param number
	 * @param password
	 */
	public static boolean addNewAdmin(String number,String password){
		return admin.NewAdmin(number, password);
	}
	
	/**
	 * 修改管理员的密码操作的方法
	 * @param id 提供要修改的管理员账户号码
	 * @param pwd 新的密码
	 * @return 返回Boolean 是否修改成功
	 */
	public static boolean UpdataAdmin(String id,String pwd){	
		return admin.UpdataAdmin(id, pwd);
		
	}
	/**
	 * 删除管理员的操作,按照工号;
	 */
	public static boolean deleteAdmin(String ID){
		return admin.deleteAdmin(ID);
	}
	
	
	/**
	 * 管理员权限 删除普通用户对象
	 * @param ID 提供删除对象的职工号
	 * @return	返回是否删除成功
	 */
	public static boolean AdminDeleteByUser(String ID){
		return user.DeleteUser(ID);
	}
	
	
	/**
	 * 更新普通用户的信息
	 * @param FindID 需要更新的用户ID职工号
	 * @param UserName	新的用户姓名
	 * @return 返回是否更新成功
	 */
public static boolean updataUserName(String FindID,String UserName){
		return user.UpdataUser(FindID, null, UserName);
	}
	
	public static boolean updataUserSex(String FindID,String UserSex){
		return user.UpdataUser(FindID, UserSex, null);
	}
	
	public static boolean updataUserPassword(String FindID,String UserPassword){
		return user.UpdataUser(FindID, UserPassword);
	}

	
	/**
	 * 判断管理员用户输入的账号密码和数据库是否匹配
	 * @param numer 键入账号名
	 * @param password	键入面膜
	 * @return	返回boolean类型
	 */
	public static boolean AdminCheck(String numer,String password){
		boolean is= admin.Login(numer, password);
		if(is){
			ID=numer;
			PWD=password;
		}
		return is;
}

	public static String getID() {
		return ID;
	}

	public static String getPWD() {
		return PWD;
	}		
}
