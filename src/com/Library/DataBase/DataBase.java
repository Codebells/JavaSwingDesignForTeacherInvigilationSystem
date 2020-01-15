package com.Library.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DataBase {
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	/**
	 * 进行查询(select)操作的方法
	 * @param sql 发送的SQL语句
	 * @return 返回ResultSet结果集
	 */
	@SuppressWarnings("finally")
	public static ResultSet sendQuerySQL(String sql){
		try {
			con=JdbcUtil.getMysql();
			
			ps=con.prepareStatement(sql);	
			 
			 rs=ps.executeQuery();
	
		} catch (Exception e) {
			return null;
		}finally {
			return rs;
		}
	}
	
	/**
	 * 执行（insert/update/delete）插入、更新、删除。操作使用的方法
	 * @param sql 发送的SQL语句
	 * @return	返回受影响的行
	 */
	@SuppressWarnings("finally")
	public static int sendUpdateSQL(String sql){
			int i=0;
		
			con=JdbcUtil.getMysql();
			
			try {
				ps=con.prepareStatement(sql);
				 i=ps.executeUpdate();
			} catch (Exception e) {
				
				return i;
			}finally {
				return i;
			}
			 
			
	
		
	}
	
	public static void Colse(){
		JdbcUtil.close(con, ps, rs);
	}
}
