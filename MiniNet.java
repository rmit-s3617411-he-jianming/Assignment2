package MiniNet;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class MiniNet extends JFrame implements ActionListener {
	JPanel mb1;
	JLabel bq11; 
	JTextField wbk11;
	JButton an11,an12,an13,an14,an15,an16,an17,an18,an19;

	JPanel mb2;
	JLabel bq21;
	JTextField wbk21;
	JButton an21,an22,an23,an24,an25;

	JPanel mb3; 
	JTextArea wbk31;
	JScrollPane gd31;

	JPanel mb4;
	JLabel bq41,bq42,bq43,bq44,bq45,bq46;
	JTextField wbk41,wbk42,wbk43,wbk44,wbk45,wbk46;

	JPanel mb41;
	JButton an411,an412,an413,an414;


	JPanel mb5;//for storing mb1&&mb2
	JPanel mb6;//for storing mb4&&mb41

	FileReader wjl=null;   
	BufferedReader hcl=null;
	Control control=null;
	DataBase d =null;
	static Children [] a= new Children[3];//for store new person

	public static void main(String[] args) throws Exception {
		MiniNet m= new MiniNet();
	}

	public MiniNet() throws Exception
	{
		control= new Control();

		mb1=new JPanel();
		bq11=new JLabel("Name1:");
		wbk11=new JTextField(10);
		an11=new JButton("Search");
		an11.addActionListener(this);
		an11.setActionCommand("Search");
		an12=new JButton("ShowAll");
		an12.addActionListener(this);
		an12.setActionCommand("ShowAll");
		an13=new JButton("ShowCouple");
		an13.addActionListener(this);
		an13.setActionCommand("ShowCouple");
		an14=new JButton("ShowColleague");
		an14.addActionListener(this);
		an14.setActionCommand("ShowColleague");
		an15=new JButton("ShowClassmate");
		an15.addActionListener(this);
		an15.setActionCommand("ShowClassmate");
		an16=new JButton("ShowFriends");
		an16.addActionListener(this);
		an16.setActionCommand("showFriends");
		an17=new JButton("ShowParent");
		an17.addActionListener(this);
		an17.setActionCommand("showParent");
		an18=new JButton("ShowRelationship");
		an18.addActionListener(this);
		an18.setActionCommand("ShowRelationship");
		an19=new JButton("ShowConnectionChain");
		an19.addActionListener(this);
		an19.setActionCommand("ShowConnectionChain");
		mb1.add(bq11); mb1.add(wbk11); mb1.add(an11); mb1.add(an11);mb1.add(an12);
		//		mb1.add(an13); mb1.add(an14);mb1.add(an15);mb1.add(an16); mb1.add(an17);
		mb1.add(an18);mb1.add(an19);

		mb2=new JPanel();
		bq21 =new JLabel("Name2:");
		wbk21 = new JTextField(10);
		an21=new JButton("Delete");
		an21.addActionListener(this);
		an21.setActionCommand("Delete");
		an22=new JButton("ConnectCouple");
		an22.addActionListener(this);
		an22.setActionCommand("ConnectCouple");
		an23=new JButton("ConnectColleague");
		an23.addActionListener(this);
		an23.setActionCommand("ConnectColleague");
		an24=new JButton("ConnectClassmate");
		an24.addActionListener(this);
		an24.setActionCommand("ConnectClassmate");
		an25=new JButton("ConnectFriend");
		an25.addActionListener(this);
		an25.setActionCommand("ConnectFriend");
		mb2.add(bq21); mb2.add(wbk21);
		mb2.add(an21); mb2.add(an22); mb2.add(an23); mb2.add(an24); mb2.add(an25);

		mb3 = new JPanel();
		wbk31=new JTextArea();
		wbk31.setEditable(false);
		gd31 = new JScrollPane(wbk31);

		d= new DataBase();

		String name1="Alex Smith";
		String name2="Ben Turner";
		String name3="Hannah White";
		String name4="Zoe Foster";
		String name5="Mark Turner";
		Children per1 = new Children("Alex Smith","¡°¡±","¡°student at RMIT¡±","M",21,"WA");
		Children per2 = new Children("Ben Turner","¡°BenPhoto.jpg¡±","¡°manager at Coles¡±","M",35,"VIC");
		Children per3 = new Children("Hannah White","¡°Hannah.png¡±","¡°student at PLC¡±","F",14,"VIC");
		Children per4 = new Children("Zoe Foster","¡°¡±","¡°Founder of ZFX¡±","F",28,"VIC");		
		Children per5 = new Children("Mark Turner","¡°Mark.jpeg¡±","¡°¡±","M",2,"VIC");
		control.createAdult(name1, per1);
		control.createAdult(name2, per2);
		control.createAdult(name3, per3);
		control.createAdult(name4, per4);
		control.createAdult(name5, per5);

		FileReader wjl=null;    BufferedReader hcl=null;//show the data
		try
		{
			wjl=new FileReader("D:/IT/eclipse/HolidayPrac/src/MiniNet/people.txt");
			hcl=new BufferedReader(wjl);
			String s="",zfc="";
			while((s=hcl.readLine())!=null)
			{
				zfc+=(s+"\n");
			}    	
			wbk31.setText(zfc);
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

		mb4 = new JPanel();
		bq41 = new JLabel("Name");
		wbk41 = new JTextField(10);
		bq42 = new JLabel("Image");
		wbk42 = new JTextField(10);
		bq43 = new JLabel("Status");
		wbk43 = new JTextField(10);
		bq44 = new JLabel("Age");
		wbk44 = new JTextField(10);
		bq45 = new JLabel("Gender");
		wbk45 = new JTextField(10);
		bq46 = new JLabel("States");
		wbk46 = new JTextField(10);
		mb4.add(bq41); mb4.add(wbk41); mb4.add(bq42); mb4.add(wbk42); mb4.add(bq43); mb4.add(wbk43); 
		mb4.add(bq44); mb4.add(wbk44); mb4.add(bq45); mb4.add(wbk45); mb4.add(bq46); mb4.add(wbk46);  

		mb41 = new JPanel();
		an411 = new JButton("Add");
		an411.addActionListener(this);
		an411.setActionCommand("Add");
		an412 = new JButton("Modify");
		an412.addActionListener(this);
		an412.setActionCommand("Modify");
		an413 = new JButton("AddFather");
		an413.addActionListener(this);
		an413.setActionCommand("AddFather");
		an414 = new JButton("AddMother");
		an414.addActionListener(this);
		an414.setActionCommand("AddMother");
		mb41.add(an411); mb41.add(an412); mb41.add(an413); mb41.add(an414);

		mb5 = new JPanel();
		mb5.setLayout(new GridLayout(2,1));
		mb5.add(mb1);
		mb5.add(mb2);

		mb6 = new JPanel();
		mb6.setLayout(new GridLayout(2,1));
		mb6.add(mb4,"North");
		mb6.add(mb41,"South");

		this.add(gd31);
		this.add(mb5,"North");
		this.add(mb6,"South");

		this.setTitle("MiniNet");
		this.setSize(1000,700);
		this.setLocation(200,90);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Search")){	
			String name =wbk11.getText();
			try {
				control.childrenOutput="Name, Image, Status, Age, Gender, States\n";
				control.show(name);
				wbk31.setText(control.childrenOutput);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if(e.getActionCommand().equals("ShowAll")){
			control.childrenOutput="Name, Image, Status, Gender, Age, States\n";;
			control.print();
			wbk31.setText(control.childrenOutput);
		}
		else if(e.getActionCommand().equals("ShowRelationship")){
			String name =wbk11.getText();
			control.childrenOutput="Name1, Name2, Relationship\n";;
			control.showRelationship(name);
			wbk31.setText(control.childrenOutput);
		}
		else if(e.getActionCommand().equals("Delect")){
			String name = wbk21.getText();
			control.delect(name);
		}
		else if(e.getActionCommand().equals("ConnectCouple")){
			String name1 = wbk11.getText();
			String name2 = wbk21.getText();
			try {
				control.connectCouple(name1, name2);
			} catch (MyExceptions e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("ConnectColleague")){
			String name1 = wbk11.getText();
			String name2 = wbk21.getText();
			try {
				control.connectColleague(name1, name2);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("ConnectClassmate")){
			String name1 = wbk11.getText();
			String name2 = wbk21.getText();
			try {
				control.connectClassmate(name1, name2);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("ConnectFriend")){
			String name1 = wbk11.getText();
			String name2 = wbk21.getText();
			try {
				control.connectFriend(name1, name2);
			} catch (MyExceptions e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("Add")){

			String name = wbk41.getText();
			String image = wbk42.getText();
			String status = wbk43.getText();
			String age1 = wbk44.getText();
			int age = 0;
			try {
				age = Integer.parseInt(age1);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			String gender = wbk45.getText();
			String states = wbk46.getText();
			Children per = new Children(name,status,image,gender,age,states);

			try {
				if(age>16&&age<150){
					control.createAdult(name, per);
					Children children = (Children) control.children.get(name);
					JOptionPane.showMessageDialog(this,"Add successfully");
				}else if(age<16) {
					JOptionPane.showMessageDialog(this,"You are under 16, so you have to provide your parent's information");
					JOptionPane.showMessageDialog(this,"Now,type your fother information and click 'Add' button");
					control.createAdult(name, per);
					a[0]=per;
				}else if(age<0||age>150) {
					JOptionPane.showMessageDialog(this,"please type age from 0 to 150");
				}}catch(Exception e1) {
					e1.printStackTrace();
				}
		}
		else if(e.getActionCommand().equals("Modify")){

		}
		else if(e.getActionCommand().equals("AddFather")){
			String name = wbk41.getText();
			String image = wbk42.getText();
			String status = wbk43.getText();
			String age1 = wbk44.getText();
			int age = 0;
			try {
				age = Integer.parseInt(age1);
				if(age<16||age<0||age>150) {
					JOptionPane.showMessageDialog(this,"please type a right age");
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			String gender = wbk45.getText();
			String states = wbk46.getText();
			Children per = new Children(name,status,image,gender,age,states);
			try {
				control.createAdult(name, per);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			a[1]=per;
			JOptionPane.showMessageDialog(this,"Now,type your mather information and click 'Add' button");
		}
		else if(e.getActionCommand().equals("AddMother")){
			String name = wbk41.getText();
			String image = wbk42.getText();
			String status = wbk43.getText();
			String age1 = wbk44.getText();
			int age = 0;
			try {
				age = Integer.parseInt(age1);
				if(age<16||age<0||age>150) {
					JOptionPane.showMessageDialog(this,"please type a right age");
				}
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
			String gender = wbk45.getText();
			String states = wbk46.getText();
			Children per = new Children(name,status,image,gender,age,states);
			try {
				control.createAdult(name, per);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			a[2]=per;
			a[0].setMother(this.a[1]);
			a[0].setFather(this.a[2]);
			a[1].setCouple(this.a[2]);
			a[1].setChildren(this.a[0]);
			a[2].setCouple(this.a[1]);
			a[2].setChildren(this.a[0]);
			JOptionPane.showMessageDialog(this,"Add successfully");
		}

	}
}