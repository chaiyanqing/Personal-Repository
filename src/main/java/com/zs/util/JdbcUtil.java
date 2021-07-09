package com.zs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
public class JdbcUtil {
	static Properties properties = PropertiesUtil.getProperties("jdbc.properties");
	private static String url =properties.getProperty("url"); 
    private static String user = properties.getProperty("username");
    private static String password =properties.getProperty("password");
    public static void execute(List<String> sqls){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);  
            conn.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            System.out.println("开始：" + startTime); 
            for (String sql : sqls) {
            	System.out.println(sql);
            	pstm = conn.prepareStatement(sql);
            	pstm.addBatch();
            	pstm.executeBatch();
			}
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime)); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static  List<Map<String,String>> ResultList(String sql){
    	List<Map<String,String>> listMap=new ArrayList();
    	Connection conn = null;
        Statement statement =null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);  
            statement = conn.createStatement();
            //执行查询，得到ResultSet
            rs = statement.executeQuery(sql);
            while (rs.next()) {
            	Map<String,String> s=new HashMap<String,String>();
            	s.put("id", String.valueOf(rs.getString(1)));
            	listMap.add(s);
			}
			 rs.close();
             return listMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(statement!=null){
                try {
                	statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(rs!=null){
            	try {
					 rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    } 

}
