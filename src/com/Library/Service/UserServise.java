package com.Library.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.Dao.UserDao;
import com.Library.DataBase.DataBase;



public class UserServise {
	private static UserDao user=new UserDao();
	/**
	 * 此处为用户登陆成功获取用户的所有详细信息
	 */
	private static String name;
	private static String id;
	private static String sex;
	private static String numer;
	private static String password;
	private static String MaxBook;
	public static int stat=0;
	/**
	 * 添加一个新的普通用户账户
	 * @param uname 用户姓名
	 * @param uid	用户职工号
	 * @param sex 性别
	 * @param unumer	用户账号
	 * @param upassword	用户密码
	 */
	public static boolean addNewUser(String uname,String uid, String sex, String unumer, String upassword){
		System.out.println("New "+sex);
		return user.NewUser(uid, uname, sex, unumer, upassword);
	}
	
	
	/**
	 * 更新普通用户的信息
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
	 * 删除的职工信息
	 * @param FindID 填写需要删除的职工号
	 * @return 返回是否删除成功
	 */
	public static boolean deleteUser(String FindID){
		return user.DeleteUser(FindID);
	}
	
	/**
	 * 查找的职工ID 对象
	 * @param ID 职工号
	 * @return  有这个职工号返回 true 不存在返回false
	 */
	public static boolean findUserByID(String ID){
		return false;
	}
	/**
	 * 查找职工的账号是否存在
	 * @param numer 职工名
	 * @return 存在返回false, 不存在返回true
	 */
	public static boolean findUserByNumer(String numer){
		return false;
	}
	
	/**
	 * 返回一个用户对象的所有数据信息
	 * @param ID  所要返回的职工ID职工号
	 * @return	返回字符串
	 */
	
	public static String toStringUser() {
		return "UserServise [name=" + name + ", id=" + id + ", sex=" + sex + ", numer=" + numer + ", password="
				+ password + ", MaxBook=" + MaxBook + "]";
	}


	/**
	 * 获取登录成功的职工全部信息
	 * @param id
	 */
	public static void ExtractUser(String ID){
		String extract[]=user.FindsUserByID(ID);
		id=extract[0];
		name=extract[1];
		sex=extract[2];
		numer=extract[3];
		password=extract[4];
		MaxBook=extract[5];
	}
	
	public static String QueryUser(String ID){
		String extract[]=user.FindsUserByID(ID);
		return "职工号:"+extract[0]+"\n"+"姓名："+extract[1]+"\n"+"性别："+extract[2]+"\n"+"账号："+extract[3]+"\n"+"密码："+extract[4]+"\n"
		;
	}
	public static String QueryUserName(String ID){
		String extract[]=user.FindsUserByID(ID);
		return extract[1];
	}
	/**
	 * 判断普通用户输入的账号密码和数据库是否匹配
	 * @param numer 键入账号名
	 * @param password	键入密码
	 * @return	返回boolean类型
	 */
	public static boolean UserCheck(String numer,String password){
		String is= user.Login(numer, password);
		if(is!=null){
			ExtractUser(is);
			return true;
		}
		return false;
	}
	public static String UserMail(String name){
		String sql="select mail from usermail where uname='" + name + "'";
		System.out.println("sql = "+sql);
		ResultSet rs = DataBase.sendQuerySQL(sql);
//		ResultSet rs = DataBase.sendQuerySQL("select mail from usermail");
		String is=null;
		try {
			rs.next();
			is = rs.getString("mail");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("is = "+name+is);
		if(is!=null){
			return is;
		}
		return null;
	}
	/**
	 * 返回这个职工的所有监考的信息
	 * @param ID name
	 * @return
	 */
	public static String[][] BookDataByUserID(String ID){
		return user.PrintTable(ID);
	}

	/**
	 * 获取信息get方法组
	 * @return
	 */
	public static String getName() {
		return name;
	}
	public static int getState() {
		return stat;
	}

	public static String getId() {
		return id;
	}


	public static String getSex() {
		return sex;
	}


	public static String getNumer() {
		return numer;
	}


	public static String getPassword() {
		return password;
	}


	public static String getMaxBook() {
		return MaxBook;
	}
	
	
}
