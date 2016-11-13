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
			con = DriverManager.getConnection("jdbc:sqlite:C:/workspace_JavaEE/chat/USERS.db");
			
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
				sql = "CREATE TABLE `"+ tableName +"` (`USERID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
						+ "`FIRSTNAME` CHAR(50) NOT NULL,"
						+ "`LASTNAME` CHAR(50) NOT NULL,"
						+ "`USERNAME` CHAR(50) NOT NULL,"
						+ "`EMAIL` CHAR(50) NOT NULL,"
						+ "`PASSWORD` CHAR(50) NOT NULL,"
						+ "`IMG` BLOB NOT NULL,"
						+ "`AGE` INT NOT NULL)";
				
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
	
	public static void insertToUsersTable(String firstname, String lastname, String username, String email, String password, int age) {
		Connection con = null;
		Statement st = null;
		String sql = null;
		
		try {
		    con = Config.getConnection();
		    con.setAutoCommit(false);
		    System.out.println("Opened database successfully");
			
		    st = con.createStatement();
			
			sql = "INSERT INTO `USERS` VALUES (null,'"+firstname+"','"+lastname+"','"+username+"','"+email+"','"+password+"','anonymous_profile.png',"+age+")";
			
			st.executeUpdate(sql);
			con.commit();
			Config.closeConnection(con, st);
			System.out.println("Records created successfully...");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	public static boolean checkValidationLogin(String username, String password){
		
		Connection con = null;
		Statement st = null;
		String sql = null;
		String dbUser, dbPass;
		
		boolean valid = false;
		
		try {
		    con = Config.getConnection();
		    
		    st = con.createStatement();
			
			sql = "select username, password from users";
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				dbUser = rs.getString("username");
				dbPass = rs.getString("password");
				
				if(dbUser.equalsIgnoreCase(username) && dbPass.equalsIgnoreCase(password)){
					valid = true;
				}
			}
			
			rs.close();
			Config.closeConnection(con, st);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return valid;
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

	public static String getMyInfo(String param, String username){
		Connection con = null;
		Statement st = null;
		String sql = null;
		
		try {
		    con = Config.getConnection();
		    
		    st = con.createStatement();
			
			sql = "select `" + param + "` from users where username = '" + username + "'";
			
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				param = rs.getString(param);
			}
			
			rs.close();
			Config.closeConnection(con, st);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return param;
	}
	
	
	
	/*public static void main(String[] args) {
		
		Config.createTable("USERS");
		//Config.insertToUsersTable("toot", "lior@avdiel.com", "123", 123);
	}
	*/
}