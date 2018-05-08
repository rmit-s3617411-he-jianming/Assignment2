package MiniNet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class xxxx {

}if(e.getActionCommand().equals("chaxun"))
{	
	FileReader wjl=null;    BufferedReader hcl=null;
	try
	{
		String name = wbk1.getText();
		control.show(name);
		wjl=new FileReader("D:/IT/eclipse/HolidayPrac/src/MiniNet/DB/People.txt");
		hcl=new BufferedReader(wjl);
		String s="",zfc="";
		String[] separateData;
		ArrayList<String[]> allData= new ArrayList();
		while((s=hcl.readLine())!=null)
		{
			separateData = s.split(",");
			allData.add(separateData);
			zfc+=(s+"\n");
		}    	
		wbk2.setText(zfc);
		String nameFromFile = allData.get(1)[1];
		nameDisplay.setText(nameFromFile);
		System.out.print(nameFromFile);
	}
	catch(Exception aa){}
	finally
	{
		try 
		{
			wjl.close();
			hcl.close();
		} 
		catch (Exception e1) {}
	}
}
