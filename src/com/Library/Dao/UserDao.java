package com.Library.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.Library.DataBase.DataBase;

/**
 * ����ͨ�û����еĲ��� ���û��ģ���ӣ����£�ɾ��������
 * 
 * @author Codebells
 *
 */
public class UserDao {

	/**
	 * ���һ���µ���ͨ�û������ݿ���
	 * 
	 * @param id
	 *            ѧ��
	 * @param name
	 *            ����
	 * @param sex
	 *            �Ա�
	 * @param numer
	 *            �˺�
	 * @param pwd
	 *            ����
	 * @return ����boolean �Ƿ���ӳɹ�
	 */
	public boolean NewUser(String id, String name, String sex, String numer, String pwd) {
		String sql = "insert into bmsuser values('" + id + "','" + name + "','" + sex + "' ,0)";
		String sql2 = "insert into bmsuser_numer values('" + id + "','" + numer + "','" + pwd + "',10)";
		System.out.println(sql);
		int i = DataBase.sendUpdateSQL(sql);
		if (i != 0) { // ���ѧ�ű���ӳɹ� ������˺ű�����
			System.out.println("OK1!!");
			int j = DataBase.sendUpdateSQL(sql2);
			if (j != 0)
				return true;
			else { // ������ʧ�ܻع���֮ǰ��ԭʼ����
				System.out.println("Failed2!!");
				DataBase.sendUpdateSQL("delete from bmsuser where id='" + id + "'"); // ���ݻع�ɾ������
				return false;
			}

		} else
			return false;
	}

	/**
	 * �޸����ݿ�����ͨ�û����Ա��������� �����޸ĵ�����null
	 * 
	 * @param id
	 *            �޸��˵�ѧ��
	 * @param sex
	 *            �µ��Ա�
	 * @param name
	 *            �µ�����
	 * @return ����boolean �Ƿ��޸ĳɹ�
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
	 * �޸����ݿ�����ͨ�û����������
	 * 
	 * @param id
	 *            �޸��˵�ѧ��
	 * @param pwd
	 *            ������
	 * @return ����boolean �Ƿ��޸ĳɹ�
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
	 * ɾ����ͨ�û�����Ϣ����
	 * 
	 * @param id
	 *            �ṩɾ���û���ѧ��
	 * @return ����boolean �Ƿ�ɾ���ɹ�
	 */
	public boolean DeleteUser(String id) {
		int i = DataBase.sendUpdateSQL("delete from bmsuser where id='" + id + "'");
		if (i != 0) {
			return true;
		} else
			return false;
	}

	/**
	 * ����ͨ�û��Ĳ��Ҳ���,��ѧ�Ž��в���
	 * 
	 * @param id
	 *            ����Ҫ���ҵ�ѧ��
	 * @return ����һ����6���������ַ���.˳���±�ֱ���(ѧ��0������1���Ա�2���˺�3������4��ͼ�����������5),���û���ҵ��򷵻ص���һ��ȫ��Ϊ��(null)�����顣
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
	 * �û����е�¼��������֤����
	 * 
	 * @param numer
	 *            ��¼���˺�
	 * @param pwd
	 *            ��¼������
	 * @return �����Ƿ��¼�ɹ���ѧ��IDѧ��
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
	 * �鿴һ���û��࿼�Ŀ���
	 * 
	 * @param userid
	 *            �û���id
	 * @return ����һ��ArrayList��������¼����û����м࿼��cno
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
	 * �ṩһ���û�ѧ�ŷ��� ���ĵ�����ͼ����ϸ��Ϣ
	 * 
	 * @param userid
	 *            ѧ��
	 * @return ����һ���ַ������鳤��Ϊ6�� �±�ֱ����(���0������1������2��������3������4������ʱ��5)�������쳣���ؿ�
	 */
	@SuppressWarnings("finally")
	public String[][] PrintTable(String userid) {
		ArrayList<String> booksid = UserBorrowedBooks(userid);

		if (booksid != null) { // ���ж��Ƿ�Ϊ��
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
