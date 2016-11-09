package com.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Config {

	
	public static Connection getConnection(){
		
		Connection con = null; 
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:/Users/DMJ834/workspace/chat/USERS.db");
			
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		System.out.println("Open database success...");
		return con;
	}
	
	public static Statement getStatement(){
		
		Statement st = null; 
		
		try {
			st = Config.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return st;
	}

	
	public static void createTable(String tableName){
		
		String sql = null;
		Connection con = null;
		Statement st = null;
		
		try {
			con = Config.getConnection();
			st = Config.getStatement();
			
			org.sqlite.jdbc4.JDBC4DatabaseMetaData dbm = (org.sqlite.jdbc4.JDBC4DatabaseMetaData) con.getMetaData();
			ResultSet tableExists = dbm.getTables(null, null, "USERS", null);
			
			if (!tableExists.next()){
				sql = "CREATE TABLE `"+ tableName +"` (`USERID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`USERNAME` CHAR(50) NOT NULL,`EMAIL` CHAR(50) NOT NULL,`PASSWORD` CHAR(50) NOT NULL,`AGE`	INT NOT NULL)";
				
				st.executeUpdate(sql);
				
				System.out.println("Create table successfully");
			}else{
				System.out.println("Table already exists...");
			}
			
			Config.closeConnection(con, st);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void insertToUsersTable(String username, String email, String password, int age) {
		Connection con = null;
		Statement st = null;
		String sql = null;
		
		try {
		    con = Config.getConnection();
		    con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
			
		    st = con.createStatement();
			
			sql = "INSERT INTO `USERS` VALUES (null,'"+username+"','"+email+"','"+password+"',"+age+")";
			
			st.executeUpdate(sql);
			con.commit();
			Config.closeConnection(con, st);
			System.out.println("Records created successfully...");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	public static void closeConnection(Connection con, Statement st){
		
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*public static void main(String[] args) {
		
		Config.createTable("USERS");
		Config.insertToUsersTable("toot", "lior@avdiel.com", "123", 123);
	}
	*/
}