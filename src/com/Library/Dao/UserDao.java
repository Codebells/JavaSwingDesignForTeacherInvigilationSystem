package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.Library.DataBase.DataBase;

/**
 * 对普通用户进行的操作 对用户的，添加，更新，删除，查找
 * 
 * @author Codebells
 *
 */
public class UserDao {

	/**
	 * 添加一个新的普通用户到数据库中
	 * 
	 * @param id
	 *            学号
	 * @param name
	 *            姓名
	 * @param sex
	 *            性别
	 * @param numer
	 *            账号
	 * @param pwd
	 *            密码
	 * @return 返回boolean 是否添加成功
	 */
	public boolean NewUser(String id, String name, String sex, String numer, String pwd) {
		String sql = "insert into bmsuser values('" + id + "','" + name + "','" + sex + "' ,0)";
		String sql2 = "insert into bmsuser_numer values('" + id + "','" + numer + "','" + pwd + "',10)";
		System.out.println(sql);
		int i = DataBase.sendUpdateSQL(sql);
		if (i != 0) { // 如果学号表添加成功 则添加账号表数据
			System.out.println("OK1!!");
			int j = DataBase.sendUpdateSQL(sql2);
			if (j != 0)
				return true;
			else { // 如果添加失败回滚最之前的原始数据
				System.out.println("Failed2!!");
				DataBase.sendUpdateSQL("delete from bmsuser where id='" + id + "'"); // 数据回滚删除操作
				return false;
			}

		} else
			return false;
	}

	/**
	 * 修改数据库中普通用户的性别姓名操作 不做修改的输入null
	 * 
	 * @param id
	 *            修改人的学号
	 * @param sex
	 *            新的性别
	 * @param name
	 *            新的姓名
	 * @return 返回boolean 是否修改成功
	 */
	public boolean UpdataUser(String id, String sex, String name) {
		String sql;
		if (name == null && sex != null)
			sql = "update bmsuser set sex='" + sex + "' where id='" + id + "'";
		else if (sex == null && name != null)
			sql = "update bmsuser set Uname='" + name + "' where id='" + id + "'";
		else
			sql = "update bmsuser set Uname='" + name + "',sex='" + sex + "' where id='" + id + "'";
		System.out.println("sql : "+sql);
		int i = DataBase.sendUpdateSQL(sql);
		if (i != 0)
			return true;
		else
			return false;
	}

	/**
	 * 修改数据库中普通用户的密码操作
	 * 
	 * @param id
	 *            修改人的学号
	 * @param pwd
	 *            新密码
	 * @return 返回boolean 是否修改成功
	 */
	public boolean UpdataUser(String id, String pwd) {
		String sql = "update bmsuser_numer set pwd='" + pwd + "' where userid='" + id + "'";
		int i = DataBase.sendUpdateSQL(sql);
		if (i != 0)
			return true;
		else
			return false;
	}

	/**
	 * 删除普通用户的信息操作
	 * 
	 * @param id
	 *            提供删除用户的学号
	 * @return 返回boolean 是否删除成功
	 */
	public boolean DeleteUser(String id) {
		int i = DataBase.sendUpdateSQL("delete from bmsuser where id='" + id + "'");
		if (i != 0) {
			return true;
		} else
			return false;
	}

	/**
	 * 对普通用户的查找操作,对学号进行查找
	 * 
	 * @param id
	 *            键入要查找的学号
	 * @return 返回一个带6个参数的字符串.顺序下表分别是(学号0、姓名1、性别2、账号3、密码4、图书的最大借书量5),如果没有找到则返回的是一个全部为空(null)的数组。
	 */
	@SuppressWarnings("finally")
	public String[] FindsUserByID(String id) {
		String Result[] = new String[6];
		ResultSet rs1;
		ResultSet rs;
		String sql = "select * from bmsuser where id='" + id + "'";
		rs = DataBase.sendQuerySQL(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					Result[0] = rs.getString("id");
					Result[1] = rs.getString("Uname");
					Result[2] = rs.getString("sex");
				}
			} catch (SQLException e) {
				return Result = null;
			} finally {
				rs1 = DataBase.sendQuerySQL("select * from bmsuser_numer where userid='" + id + "'");
				try {
					while (rs1.next()) {
						Result[3] = rs1.getString("numer");
						Result[4] = rs1.getString("pwd");
						Result[5] = String.valueOf(rs1.getInt("booksize"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return Result = null;
				} finally {
					return Result;
				}
			}
		} else
			return null;

	}
	public  String get_email(String user) {
		System.out.println("name = ");
		ResultSet rs = DataBase.sendQuerySQL("select mail from usermail where uname='" + user + "'");
		try {
			System.out.println(rs.getString("mail"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (rs != null) {
			try {
				return rs.getString("mail");
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 用户进行登录操作的验证方法
	 * 
	 * @param numer
	 *            登录的账号
	 * @param pwd
	 *            登录的密码
	 * @return 返回是否登录成功的学生ID学号
	 */
	public String Login(String numer, String pwd) {

		ResultSet rs = DataBase.sendQuerySQL("select pwd,userid from bmsuser_numer where numer='" + numer + "'");
		if (rs != null) {
			try {
				while (rs.next()) {
					if (pwd.equals(rs.getString("pwd")))
						return rs.getString("userid");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return null;

	}

	/**
	 * 查看一个用户监考的考试
	 * 
	 * @param userid
	 *            用户的id
	 * @return 返回一个ArrayList容器，记录这个用户所有监考的cno
	 */
	@SuppressWarnings("finally")
	public ArrayList<String> UserBorrowedBooks(String userid) {
		ArrayList<String> booksid = new ArrayList<String>();
		System.out.println("userid= "+userid);
		ResultSet rs = DataBase.sendQuerySQL(
				"select cno from bmsbook where mainteacher='" + userid + "' or fu_teacher='" + userid + "'");
		if (rs != null) {
			try {
				while (rs.next()) {
					System.out.println("res cno = "+rs.getString("cno"));
					booksid.add(rs.getString("cno"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				return booksid;
			}
		} else
			return null;
	}

	/**
	 * 提供一个用户学号返回 借阅的所有图书详细信息
	 * 
	 * @param userid
	 *            学号
	 * @return 返回一个字符串数组长度为6的 下标分别代表(书号0，书名1，作者2，出版社3，类型4，借书时间5)，遇到异常返回空
	 */
	@SuppressWarnings("finally")
	public String[][] PrintTable(String userid) {
		ArrayList<String> booksid = UserBorrowedBooks(userid);

		if (booksid != null) { // 先判断是否为空
			String Result[][] = new String[booksid.size()][8];
			String bid;
			for (int i = 0; i < booksid.size(); i++) {
				bid = booksid.get(i);
				System.out.println("cno = " + bid);
				ResultSet rs = DataBase.sendQuerySQL(
						"select exam_pici,exam_date,begin_time,end_time,classroom,cno,cname,mainteacher from bmsbook where cno='"
								+ bid + "'");
				if (rs != null) {
					try {
						while (rs.next()) {
							Result[i][0] = rs.getString("exam_pici");
							System.out.println(Result[i][0]);
							Result[i][1] = rs.getString("exam_date");
							System.out.println(Result[i][1]);
							Result[i][2] = rs.getString("begin_time");
							System.out.println(Result[i][2]);
							Result[i][3] = rs.getString("end_time");
							System.out.println(Result[i][3]);
							Result[i][4] = rs.getString("classroom");
							System.out.println(Result[i][4]);
							Result[i][5] = rs.getString("cno");
							System.out.println(Result[i][5]);
							Result[i][6] = rs.getString("cname");
							System.out.println(Result[i][6]);
							Result[i][7] = rs.getString("mainteacher");
							System.out.println(Result[i][7]);
						}
					} catch (SQLException e) {
						return Result = null;
					}
				}
			}
			return Result;

		} else
			return null;
	}

}
