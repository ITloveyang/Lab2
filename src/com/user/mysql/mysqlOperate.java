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
            // ע�� JDBC ����
        	System.out.println("ע�� JDBC ����");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����ע��ɹ�");
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement��...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT AuthorID, Name, Age,Country FROM author where Name=?";
            pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, authorNAME);
            
            ResultSet rs = pstmt.executeQuery();
            List<String> AuthorIDList=new ArrayList<String>();
            // չ����������ݿ� 	
            while(rs.next()){
                // ͨ���ֶμ���
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
            // ��ɺ�ر�
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
            // ע�� JDBC ����
        	System.out.println("ע�� JDBC ����");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����ע��ɹ�");
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement��...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM book WHERE ISBN = ?";

            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, ISBN);
            
            int i = pstmt.executeUpdate();
            System.out.println("ɾ����BOOK: " + i);
            if(i==0)
            {
            	System.out.println("�����Ѿ�ɾ��!");
            	return false;
            }
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
            // ע�� JDBC ����
        	System.out.println("ע�� JDBC ����");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����ע��ɹ�");
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement��...");
            stmt = conn.createStatement();
            String sql;
            String AuthorID;
           
            sql = "SELECT ISBN, Title, AuthorID,Publisher,PublishData,Price FROM book where ISBN=?";

            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, ISBN);
            
            ResultSet rs = pstmt.executeQuery();
         // չ����������ݿ� 	
            while(rs.next()){
                // ͨ���ֶμ���
            	detailMap.put("ISBN", rs.getString("ISBN"));
            	detailMap.put("��Ŀ", rs.getString("Title"));
            	detailMap.put("����ID", rs.getString("AuthorID"));
            	detailMap.put("������", rs.getString("Publisher"));
            	detailMap.put("��������", rs.getString("PublishData"));
            	detailMap.put("�۸�", rs.getString("Price"));
            }
            AuthorID=detailMap.get("����ID");
            sql="SELECT AuthorID, Name, Age,Country FROM author where AuthorID=?";
            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, AuthorID);
           
            System.out.println(pstmt.toString());
            ResultSet rs2 = pstmt.executeQuery();
            while(rs2.next()){
                // ͨ���ֶμ���
            	detailMap.put("��������", rs2.getString("Name"));
            	detailMap.put("����", rs2.getString("Age"));
            	detailMap.put("����", rs2.getString("Country"));
            }
            
            // ��ɺ�ر�
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
            // ע�� JDBC ����
        	System.out.println("ע�� JDBC ����");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����ע��ɹ�");
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement��...");
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
         // չ����������ݿ� 	
            while(rs.next()){
                // ͨ���ֶμ���
            	detailMap.put("ISBN", rs.getString("ISBN"));
            	detailMap.put("��Ŀ", rs.getString("Title"));
            	detailMap.put("����ID", rs.getString("AuthorID"));
            	detailMap.put("������", rs.getString("Publisher"));
            	detailMap.put("��������", rs.getString("PublishData"));
            	detailMap.put("�۸�", rs.getString("Price"));
            }
            AuthorID=detailMap.get("����ID");
            sql="SELECT AuthorID, Name, Age,Country FROM author where AuthorID=?";
            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, AuthorID);
           
            System.out.println(pstmt.toString());
            ResultSet rs2 = pstmt.executeQuery();
            while(rs2.next()){
                // ͨ���ֶμ���
            	detailMap.put("��������", rs2.getString("Name"));
            	detailMap.put("����", rs2.getString("Age"));
            	detailMap.put("����", rs2.getString("Country"));
            }
            
            // ��ɺ�ر�
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
            // ע�� JDBC ����
        	System.out.println("ע�� JDBC ����");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("����ע��ɹ�");
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // ִ�в�ѯ
            System.out.println(" ʵ����Statement��...");
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
            	System.out.println("�ɹ������"+row+"������");
            }
            pstmt1.close();
            sql = "SELECT AuthorID,Name,Country,Age FROM author where AuthorID=?";


            pstmt = (PreparedStatement)conn.prepareStatement(sql);  
            pstmt.setString(1, authorID);
            
            ResultSet rs = pstmt.executeQuery();
         // չ����������ݿ� 	
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
                 	System.out.println("�ɹ������"+row+"������");
                 }
                 
                 pstmt1.close();
            }
           
            
            // ��ɺ�ر�
            rs.close();
            pstmt.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
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
