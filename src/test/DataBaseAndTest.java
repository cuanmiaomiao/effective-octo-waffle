package test;

import java.sql.*;
import java.util.ArrayList;




import org.junit.*;
import org.junit.Test;




public class DataBaseAndTest {

	   static final String DB_URL = "jdbc:mysql://localhost:3306/mytestdb?serverTimezone=GMT&useSSL=false";
	   static final String USER = "root";
	   static final String PASS = "123456";

	   	
		  @After
		    public void testAfter(){

			   System.out.println("Goodbye!");
			  
		    }
		  @BeforeClass
		    public static void tesBefore(){

			   System.out.println("hello!");
			  
		    }
	@Ignore  
	@Test
	  public static void  insertTest(ArrayList<String> sql){
		  
		  Connection conn = null;
		  Statement stmt = null;
		   
	      try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	      try {

			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	      for (int i = 0; i < sql.size(); i++) {
		      try {
					stmt.executeUpdate(sql.get(i));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	      try {
			stmt.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  @Ignore
	  public void buildtest() {

		   Connection conn = null;
		   Statement stmt = null;
		   try{

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating database...");
		      stmt = conn.createStatement();

		      String sql = "CREATE DATABASE jdbc_db";
		      stmt.executeUpdate(sql);
		      System.out.println("Database created successfully...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	}
	  
	@Ignore
	public void testRead() {
		   Connection conn = null;
		   Statement stmt = null;
		   try{

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM huizhi";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         
		     	String TrxId = rs.getString("TrxId");
		    	String TrxFinishTm = rs.getString("TrxFinishTm");
		    	float TrxAmt = rs.getFloat("TrxAmt");
		    	String SysRtnCd = rs.getString("SysRtnCd");
		    	String BizStsCd = rs.getString("BizStsCd");
		         //Display values
		    	
		         System.out.println(TrxId);
		         System.out.println(TrxFinishTm);
		         System.out.println(TrxAmt);
		         System.out.println(SysRtnCd);
		         System.out.println(BizStsCd);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	}
	
	@Ignore
	public static void testdelete(String tableName){

		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);

		      stmt = conn.createStatement();
		      String sql = "DELETE FROM "+tableName+";";
		      stmt.executeUpdate(sql);
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}


}
