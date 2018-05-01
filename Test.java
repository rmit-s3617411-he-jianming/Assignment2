package MiniNet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Test {
    /**
     * @param args
     */
    public static void main(String[] args){
        try {
                //����HSQLDB���ݿ�JDBC����
                Class.forName("org.hsqldb.jdbcDriver");
                //���ڴ��н�����ʱ���ݿ�score���û���Ϊsa������Ϊ��
                @SuppressWarnings("unused")
                Connection connect = DriverManager.getConnection("jdbc:hsqldb:file:db/file;shutdown=true", "sa", "");
                System.out.println("Link is OK��");
                
                Statement state = connect.createStatement(); 
                state.executeUpdate("drop table children if exists");
                state.executeUpdate("create table children (Name VARCHAR(20), Image VARCHAR(20),Status VARCHAR(20),Age VARCHAR(20),Gender VARCHAR(20),states VARCHAR(20))");
                System.out.println("Create is  OK��");
                
                state.executeUpdate("Insert into children (Name, Image,Status,Age,Gender,states) Values('sd', '������','sd', '������','sd', '������')");
                state.executeUpdate("Insert into children (Name, Image,Status,Age,Gender,states) Values('sd', '���»�','sd', '���»�','sd', '���»�')");
                System.out.println("Insert is OK��");
                
                PreparedStatement pstmt2   =   connect.prepareStatement("select * from children");   
                ResultSet rs = pstmt2.executeQuery();   
                while(rs.next()){  
                    String x;
                    x = rs.getString(1) + "   " + rs.getString(2)+ "   " + rs.getString(3)+ "   " + rs.getString(4)+ "   " + rs.getString(5)+ "   " + rs.getString(6);                  
                    System.out.println(x);                      
                }   
                System.out.println("Select is OK��");
                pstmt2.close();
                rs.close();
                
                state.close();
                connect.close();
                
            } catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                  e.printStackTrace();
            }              
    }
}