package com.Library.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * JDBC工具类
 * @author Codebells
 *
 */
public class JdbcUtil {
	
	public static Connection getMysql(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //加载数据库驱动
			return  DriverManager.getConnection("jdbc:mysql://localhost:3306/designmode","root","");//建立数据库的链接
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void close(Connection con,PreparedStatement ps,ResultSet rs){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Connection con,PreparedStatement ps){
		
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	

}
