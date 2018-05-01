package MiniNet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.*;

class DataBase extends AbstractTableModel 
{
	Vector ziduan,jilu;
	PreparedStatement ps=null;
    Connection ct=null;
    ResultSet rs=null;
    
	public int getRowCount()
	{
		return this.jilu.size();
	}
    public int getColumnCount()
    {
		return this.ziduan.size();
	}
	public Object getValueAt(int hang, int lie)
	{
		return ((Vector)this.jilu.get(hang)).get(lie);
	}
	
	public DataBase()
	{
		this.sqlyj("select * from children");
	}
	public DataBase(String ss)
	{
		this.sqlyj(ss);
	}
	public String getColumnName(int e)
	{
		return (String)this.ziduan.get(e);
	}
	public void sqlyj(String sql)
	{
		ziduan=new Vector();
		ziduan.add("Name");
		ziduan.add("Image");
		ziduan.add("Status");
		ziduan.add("Age");
		ziduan.add("Gender");
		ziduan.add("States");		
		
        jilu=new Vector();
		
		try {
	    	  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  ct=DriverManager.getConnection("jdbc:hsqldb:file:db/file;shutdown=true", "sa", "");
			  ps=ct.prepareStatement(sql);	
			  rs=ps.executeQuery();
			  
			  while(rs.next())
			  {
				  Vector hang=new Vector();
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));
					hang.add(rs.getString(3));
					hang.add(rs.getInt(4));
					hang.add(rs.getString(5));
					hang.add(rs.getString(6));
					jilu.add(hang);
			  }
		} catch (Exception e){}
	    finally
	    {
	    	try {
	    		if(rs!=null)
				{
					rs.close();
				}
	    		if(ps!=null)
				{
					ps.close();
				}
				if(ct!=null)
				{
					ct.close();
				}
				
			} catch (Exception e){}
	    }
	}
}

