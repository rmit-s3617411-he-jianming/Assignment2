package MiniNet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**  
 * @version V1.6  
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/

public class Control extends JFrame {
	Scanner in = new Scanner(System.in);
	FileWriter fw = null;
	MyExceptions exceptions;
	HashMap children;
	public static HashMap Children;
	public static String childrenOutput="Name, Image, Status, Age, Gender, States\n";
	public static ArrayList<String> relationship = new ArrayList<String>();

	Control() throws Exception{
		children = new HashMap();//initialize a Children array
		exceptions = new MyExceptions();
		File SystemInformation = new File("D:/IT/eclipse/HolidayPrac/src/MiniNet/DB/SystemInformation.txt");
	}


	//detect the person age
	public boolean detectAge(String name1,String name2) throws MyExceptions  {
		boolean a=true;//if a = true the system will judge the person is over 16 age
		Children per1 =(Children) children.get(name1);
		Children per2 =(Children) children.get(name2);
		if(2<per1.getAge()&&per1.getAge()<16&&2<per2.getAge()&&per2.getAge()<16
				&&per1.getFather().getName()!=per2.getFather().getName()
				&&(per1.getAge()-per2.getAge()<3||per2.getAge()-per1.getAge()<3)){
			a =true;
		}else if(per1.getAge()>16&&per2.getAge()>16){
			a=true;
		}else if(per2.getAge()<6||per1.getAge()<6){
			try {
				exceptions.TooYoungException();
			}catch(MyExceptions e) {
				e.printStackTrace();
				a=false;
				return false;
			}
		}else if(per1.getAge()-per2.getAge()>3||per2.getAge()-per1.getAge()>3||per1.getAge()>16
				&&per2.getAge()<16||per1.getAge()<16&&per2.getAge()>16) {
			try {
				exceptions.NotToBeFriendsException();
			}catch(MyExceptions e) {
				e.printStackTrace();
				a=false;
				return false;
			}
		}
		return a;
	}

	public void check(String name) {
		if(!children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			System.out.println("The name is not exist");
			return;
		}
	}

	//1.Add a Adult  into network
	public void createAdult(String name,Children per) throws Exception {
		if(children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			JOptionPane.showMessageDialog(this,"The name already exist");
		}	    
		else
		{
			children.put(name, per);
		}
	}

	//2.Display the profile of the selected person
	public void show(String name) throws Exception {//show the profile about your selected person.
		if(children.containsKey(name)) {
			Children per = (Children) children.get(name);
			System.out.println(per);
			this.childrenOutput+= per.toString();
			return;
		}
		else  {
			JOptionPane.showMessageDialog(this,"the person is no exist");
			//if the person cannot be fond,end of the loop
			return ;
		}
	}


	//3.Update the profile information of the selected person
	public void updateProfile(String name) {//update the profile information about your selected person.
		Children per;
		if(children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			per = (Children) children.get(name);
			System.out.println("choose what kind of information you want to update 1.name 2.age 3.status 4.image 5.States");
			String no = in.next();
			if(no.equals("1")) {
				System.out.println("Please type the new name");
				String newname = in.next();
				per.setName(newname);
				children.remove(name);
				children.put(newname, per);
				System.out.println("upadated successful");
				return;
			}else if(no.equals("2")) {
				System.out.println("Please type the new age");
				per.setAge(in.nextInt());
				System.out.println("upadated successful");
				return;
			}else if(no.equals("3")){
				System.out.println("Please type the new status");
				per.setStatus(in.next());
				System.out.println("upadated successful");
				return;
			}else if(no.equals("4")){
				System.out.println("Please type the new image");
				per.setStatus(in.next());
				System.out.println("upadated successful");
				return;
			}else if(no.equals("5")){
				System.out.println("Please type the new states");
				per.setStates(in.next());
				System.out.println("upadated successful");
				return;
			}
		}    
		else{
			System.out.println("The name is not exist");
			return;
		}
	}

	//4.Delete the selected person
	public void delect(String name) {//delete the person 
		Children child = (Children) children.get(name);
		if(children.containsKey(name)){  //for judge the name whether exist.prevent a system have two same name
			if(child.getFather()!=null) {
				child.getFather().setChildren(null);
			}if(child.getMother()!=null) {
				child.getMother().setChildren(null);
			}
			children.remove(name);
			JOptionPane.showMessageDialog(this,"Remove successfully");
		}else{
			JOptionPane.showMessageDialog(this,"The name already exist");
			return;
		}
	}


	//5.1 connect couple
	public void connectCouple(String name1,String name2) throws Exception, MyExceptions {
		this.check(name1);
		this.check(name2);
		Children per1 = (Children) children.get(name1);	    
		Children per2 = (Children) children.get(name2);
		if(this.detectAge(name1,name2)) {
			try {
				if(exceptions.NoAvailableException(per1,per2)&&exceptions.NotToBeCoupledException(per1, per2)) {
					per1.setCouple(per2);
					per2.setCouple(per1);
					JOptionPane.showMessageDialog(this,"built relationship successfully");
					System.out.println(per1.getCouple().getName());
					return;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"connect unsuccessfully");
			}
		}
	}

	//5.2 connect colleague
	public void connectColleague(String name1,String name2) throws Exception, MyExceptions {
		this.check(name1);
		this.check(name2);
		Children per1 = (Children) children.get(name1);	    
		Children per2 = (Children) children.get(name2);
		if(this.detectAge(name1,name2)) {
			try {
				if(per1.getAge()>16&&per2.getAge()>16||exceptions.NotToBeColleaguesException(per1, per2)) {
					per1.colleague.put(name2,per2);
					per2.colleague.put(name1, per1);
					JOptionPane.showMessageDialog(this,"built relationship successfully");
					return;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"connect unsuccessfully");
			}
		}
	}

	//5.3 connect classmate
	public void connectClassmate(String name1,String name2) throws Exception, MyExceptions {
		this.check(name1);
		this.check(name2);
		Children per1 = (Children) children.get(name1);	    
		Children per2 = (Children) children.get(name2);
		if(this.detectAge(name1,name2)) {
			try {
				if(exceptions.NotToBeClassmatesException(per1, per2)) {
					per1.classmate.put(name2, per2);
					per2.classmate.put(name1, per1);
					JOptionPane.showMessageDialog(this,"built relationship successfully");
					return;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"connect unsuccessfully");
			}
		}
	}

	//5.4 connect friend
	public void connectFriend(String name1,String name2) throws MyExceptions {
		this.check(name1);
		this.check(name2);
		Children per1 = (Children) children.get(name1);	    
		Children per2 = (Children) children.get(name2);
		if(this.detectAge(name1,name2)) {
			per1.friend.put(per2.getName(),per2);//put person2(name2/per2) into person1's(name/per1) friends' list 
			per2.friend.put(per1.getName(),per1);//put person1(name/per1) into person2's(name/per2) friends' list 
			JOptionPane.showMessageDialog(this,"built relationship successfully");
			return;
		}else {
			JOptionPane.showMessageDialog(this,"connect unsuccessfully");
		}
	}

	//6.Find out whether a person is a direct friend of another person
	public void findOut(String name1,String name2) {//for find out what the relationship between two person
		this.check(name1);
		this.check(name2);
		Children per1 = null;
		Children per2 = null;
		if(children.containsKey(name1)||children.containsKey(name2))  //for judge the name whether exist.prevent a system have two same name
		{
			per1 = (Children) children.get(name1);
			per2 = (Children) children.get(name2);
		}	    
		else if(children.containsKey(name1)||children.containsKey(name1)){
			per1 =(Children) children.get(name1);
			per2 =(Children) children.get(name2);
		}
		if((per1.friend.containsKey(per2.getName())||per2.friend.containsKey(per1.getName()))){
			System.out.println("They are friend");
		}else {
			System.out.println("They are not friend");
		}
	}

	//7.Find out the name(s) of a person¡¯s child(ren) or the names of the parent
	public void findParent(String name) {
		Children per1 = null;
		if(children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			per1 = (Children) children.get(name);
		}	    
		else if(children.containsKey(name)){
			per1 =(Children) children.get(name);			
		}		
		System.out.println("please choose:according to the name, findout 1.the names of a person's children 2.the name of the parents");
		String no =in.next();
		if(no.equals("1")) {//find out the input person's children
			if(per1.getChildren()==null) {
				System.out.println("this person has not children");
				return;
			}else {
				System.out.println(per1.getName()+"'s children is "+per1.getChildren().getName());
				return;
			}
		}else if(no.equals("2")) {//find out the input person's parent
			if(per1.getMother()==null&&per1.getFather()==null) {
				System.out.println("this person's father name is not be registed");
				System.out.println("this person's mother name is not be registed");
				return;
			}else {
				System.out.println("this person's father name "+per1.getFather().getName());
				System.out.println("this person's mother name "+per1.getMother().getName());
			}

		}
	}

	//8.Show all people profile
	public void print() {
		Iterator it = children.keySet().iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			Children children1 = (Children) children.get(key);
			childrenOutput+= children1.toString()+"\n";
			System.out.println(childrenOutput);
		}
	}

	//9.According to the selected name,display the list of friends
	public void display(String name) {
		Children aa = (Children) children.get(name);
		Iterator it = aa.friend.keySet().iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			Children children1 = (Children) children.get(key);
			childrenOutput+= aa.getName()+","+children1.getName()+",friend\n";
			System.out.println(childrenOutput);
		}
	}

	//10.According to the selected name,display the couple
	public void showCouple(String name) {
		Children aa = (Children) children.get(name);
		childrenOutput+= aa.getName()+","+aa.getCouple()+",couple\n";
		System.out.println(childrenOutput);
	}

	//11.According to the selected name,display the colleague
	public void showColleague(String name) {
		Children aa = (Children) children.get(name);
		Iterator it = aa.colleague.keySet().iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			Children children1 = (Children) children.get(key);
			childrenOutput+= children1.toString()+"\n";
			System.out.println(childrenOutput);
		}
	}

	//12.According to the selected name,display the classmate
	public void showClassmate(String name) {
		Children aa = (Children) children.get(name);
		Iterator it = aa.classmate.keySet().iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			Children children1 = (Children) children.get(key);
			childrenOutput+= children1.toString()+"\n";
			System.out.println(childrenOutput);
		}
	}

	//12.According to the selected name,display the parents
	public void showParents(String name) {
		Children aa = (Children) children.get(name);
		childrenOutput+= aa.getFather().toString()+"\n"+aa.getMother().toString();
		System.out.println(childrenOutput);
	}

	//13.According to the selected name,display the classmate
	public void showRelationship(String name) {
		int i =0;
		Children aa = (Children) children.get(name);
		Iterator classmate = aa.classmate.keySet().iterator();
		while(classmate.hasNext()){
			String key =classmate.next().toString();
			Children children1 = (Children) children.get(key);
			String a= name+","+children1.getName()+",classmate";
			relationship.add(a);
		}
		Iterator colleague = aa.colleague.keySet().iterator();
		while(colleague.hasNext()){
			String key =colleague.next().toString();
			Children children1 = (Children) children.get(key);
			String a= name+","+children1.getName()+",colleague";
			relationship.add(a);
		}
		Iterator friend = aa.friend.keySet().iterator();
		while(friend.hasNext()){
			String key =colleague.next().toString();
			Children children1 = (Children) children.get(key);
			String a= name+","+children1.getName()+",friend";
			relationship.add(a);
		}
		if(aa.getFather()!=null&&aa.getMother()!=null) {
			String a= name+","+aa.getFather().getName()+",parent";
			relationship.add(a);
			String b= name+","+aa.getMother().getName()+",parent";
			relationship.add(b);
		}
		if(aa.getCouple()!=null) {
			String a= name+","+aa.getCouple().getName()+",couple";
			relationship.add(a);
		}
		if(aa.getChildren()!=null) {
			String a= name+","+aa.getChildren().getName()+",parent";
			relationship.add(a);
		}
		Collections.sort(relationship);
		for(int j=0;j<relationship.size();j++) {
			childrenOutput+= relationship.get(j)+"\n";
		}
	}
}
