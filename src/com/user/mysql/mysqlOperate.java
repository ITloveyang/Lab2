package com.user.mysql;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.user.userbook.BOOK;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
public class mysqlOperate {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn/app_lxyproject2?characterEncoding=utf8&useSSL=true";
    static final String USER = "lkwyxy12j5";
    static final String PASS = "xi4wyyklh500j2xxim1xwhii21z5z054k0wwz44x";
    public List<BOOK> userQuery(String authorNAME)
	{
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;  
        List<BOOK> bookList=new LinkedList<BOOK>();
        try{
            // 注册 JDBC 驱动
        	System.out.println("注册 JDBC 驱动");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动注册成功");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT AuthorID, Name, Age,Country FROM author where Name=?";
            pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, authorNAME);
            
            ResultSet rs = pstmt.executeQuery();
            List<String> AuthorIDList=new ArrayList<String>();
            // 展开结果集数据库 	
            while(rs.next()){
                // 通过字段检索
            	AuthorIDList.add(rs.getString("AuthorID"));
            }
            for(int i=0;i<AuthorIDList.size();i++){
            	sql = "SELECT ISBN, Title, AuthorID,Publisher,PublishData,Price FROM book where AuthorID=?";
            	pstmt = conn.prepareStatement(sql);  
                pstmt.setString(1, AuthorIDList.get(i));
                
                rs = pstmt.executeQuery();
                while(rs.next()){
                	
                	BOOK b=new BOOK();
                    b.ISBN  = rs.getString("ISBN");
                    b.TITLE = rs.getString("Title");
                    b.AUTHORID = rs.getString("AuthorID");
                    b.PUBLISHER  = rs.getString("Publisher");
                    b.PUBLISHDATA = rs.getString("PublishData");
                    b.PRICE = rs.getString("Price");
                    bookList.add(b);
                    
                }
            }
            // 完成后关闭
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
		return bookList;
	}
	public boolean userDelete(String ISBN){
		Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;  
        @SuppressWarnings("unused")
		List<BOOK> bookList=new LinkedList<BOOK>();
        try{
            // 注册 JDBC 驱动
        	System.out.println("注册 JDBC 驱动");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动注册成功");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM book WHERE ISBN = ?";

            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, ISBN);
            
            int i = pstmt.executeUpdate();
            System.out.println("删除的BOOK: " + i);
            if(i==0)
            {
            	System.out.println("该书已经删除!");
            	return false;
            }
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
		return true;
	}
	public Map<String,String> showDetail(String ISBN){
		Map<String,String> detailMap=new HashMap<String,String>();
		Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;  
        try{
            // 注册 JDBC 驱动
        	System.out.println("注册 JDBC 驱动");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动注册成功");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            String AuthorID;
           
            sql = "SELECT ISBN, Title, AuthorID,Publisher,PublishData,Price FROM book where ISBN=?";

            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, ISBN);
            
            ResultSet rs = pstmt.executeQuery();
         // 展开结果集数据库 	
            while(rs.next()){
                // 通过字段检索
            	detailMap.put("ISBN", rs.getString("ISBN"));
            	detailMap.put("题目", rs.getString("Title"));
            	detailMap.put("作者ID", rs.getString("AuthorID"));
            	detailMap.put("出版社", rs.getString("Publisher"));
            	detailMap.put("出版日期", rs.getString("PublishData"));
            	detailMap.put("价格", rs.getString("Price"));
            }
            AuthorID=detailMap.get("作者ID");
            sql="SELECT AuthorID, Name, Age,Country FROM author where AuthorID=?";
            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, AuthorID);
           
            System.out.println(pstmt.toString());
            ResultSet rs2 = pstmt.executeQuery();
            while(rs2.next()){
                // 通过字段检索
            	detailMap.put("作者名字", rs2.getString("Name"));
            	detailMap.put("年龄", rs2.getString("Age"));
            	detailMap.put("国家", rs2.getString("Country"));
            }
            
            // 完成后关闭
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
		return detailMap;
	}
	public Map<String,String> updateDetail(String ISBN,String autherID,String publishdate,String publisher,String price){
		Map<String,String> detailMap=new HashMap<String,String>();
		Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;  
        PreparedStatement pstmt1 = null;
        
        try{
            // 注册 JDBC 驱动
        	System.out.println("注册 JDBC 驱动");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动注册成功");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            String AuthorID;
            sql = "update book set Price=?,  AuthorID=?, Publisher=?, PublishData=?  where ISBN=?";
            pstmt1 = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt1.setString(1, price);
            pstmt1.setString(2, autherID);
            pstmt1.setString(3, publisher);
            pstmt1.setString(4, publishdate);
            pstmt1.setString(5, ISBN);
            pstmt1.executeUpdate();
            pstmt1.close();
            sql = "SELECT ISBN, Title, AuthorID,Publisher,PublishData,Price FROM book where ISBN=?";

            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, ISBN);
            
            ResultSet rs = pstmt.executeQuery();
         // 展开结果集数据库 	
            while(rs.next()){
                // 通过字段检索
            	detailMap.put("ISBN", rs.getString("ISBN"));
            	detailMap.put("题目", rs.getString("Title"));
            	detailMap.put("作者ID", rs.getString("AuthorID"));
            	detailMap.put("出版社", rs.getString("Publisher"));
            	detailMap.put("出版日期", rs.getString("PublishData"));
            	detailMap.put("价格", rs.getString("Price"));
            }
            AuthorID=detailMap.get("作者ID");
            sql="SELECT AuthorID, Name, Age,Country FROM author where AuthorID=?";
            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, AuthorID);
           
            System.out.println(pstmt.toString());
            ResultSet rs2 = pstmt.executeQuery();
            while(rs2.next()){
                // 通过字段检索
            	detailMap.put("作者名字", rs2.getString("Name"));
            	detailMap.put("年龄", rs2.getString("Age"));
            	detailMap.put("国家", rs2.getString("Country"));
            }
            
            // 完成后关闭
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
		return detailMap;
	}
	public Map<String,String> insertbook(String ISBN,String authorID,String publishdate,String publisher,String price,String Title,String authorNAME,String Country,String Age)
	{
		Map<String,String> detailMap=new HashMap<String,String>();
		Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;  
        PreparedStatement pstmt1 = null;
       
        try{
            // 注册 JDBC 驱动
        	System.out.println("注册 JDBC 驱动");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动注册成功");
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into book (ISBN, Title, AuthorID,Publisher,PublishData,Price)  values(?,?,?,?,?,?)";
            pstmt1 = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt1.setString(1, ISBN);
            pstmt1.setString(2, Title);
            pstmt1.setString(3, authorID);
            pstmt1.setString(4, publisher);
            pstmt1.setString(5, publishdate);
            pstmt1.setString(6, price);
            int row=pstmt1.executeUpdate();
            if(row>0){
            	System.out.println("成功添加了"+row+"条数据");
            }
            pstmt1.close();
            sql = "SELECT AuthorID,Name,Country,Age FROM author where AuthorID=?";


            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, authorID);
            
            ResultSet rs = pstmt.executeQuery();
         // 展开结果集数据库 	
            if(!rs.next())
            {
            	sql = "insert into author ( AuthorID,Name,Country,Age)  values(?,?,?,?)";
            	 pstmt1 = (PreparedStatement)conn.prepareStatement(sql);  
                 pstmt1.setString(1, authorID);
                 pstmt1.setString(2, authorNAME);
                 pstmt1.setString(3, Country);
                 pstmt1.setString(4, Age);
                  row=pstmt1.executeUpdate();
                 if(row>0){
                 	System.out.println("成功添加了"+row+"条数据");
                 }
                 
                 pstmt1.close();
            }
           
            
            // 完成后关闭
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
		return detailMap;
	}
}
