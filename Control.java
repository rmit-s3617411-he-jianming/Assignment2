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
	public void updateProfile(String name,String image,String status,String gender,int age,String states) {//update the profile information about your selected person.
		Children per;
		if(children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			per = (Children) children.get(name);
			if(!image.equals("")) {
				per.setImage(image);
			}if(!status.equals("")) {
				per.setStatus(status);
			}if(!gender.equals("")) {
				per.setGender(gender);
			}if(age!=0) {
				per.setAge(age);
			}if(!states.equals("")) {
				per.setStates(states);
			}
			JOptionPane.showMessageDialog(this,"update successfully");
			return;
		}else{
		JOptionPane.showMessageDialog(this,"The name is not exist");
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
				per1.relationshipChain.put(per2.getName(), per2);
				per2.relationshipChain.put(per1.getName(), per1);
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
				per1.relationshipChain.put(per2.getName(), per2);
				per2.relationshipChain.put(per1.getName(), per1);
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
				per1.relationshipChain.put(per2.getName(), per2);
				per2.relationshipChain.put(per1.getName(), per1);
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
		per1.relationshipChain.put(per2.getName(), per2);
		per2.relationshipChain.put(per1.getName(), per1);
		JOptionPane.showMessageDialog(this,"built relationship successfully");
		return;
	}else {
		JOptionPane.showMessageDialog(this,"connect unsuccessfully");
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

//13.According to the selected name,display all relationship
public void showRelationship(String name) {
	int i =0;
	Children aa = (Children) children.get(name);
	Iterator classmate = aa.classmate.keySet().iterator();
	while(classmate.hasNext()){
		String key =classmate.next().toString();
		Children children1 = (Children) children.get(key);
		String a= name+","+children1.getName()+",classmate";
		relationship.remove(a);
		relationship.add(a);
	}
	Iterator colleague = aa.colleague.keySet().iterator();
	while(colleague.hasNext()){
		String key =colleague.next().toString();
		Children children1 = (Children) children.get(key);
		String a= name+","+children1.getName()+",colleague";
		relationship.remove(a);
		relationship.add(a);
	}
	Iterator friend = aa.friend.keySet().iterator();
	while(friend.hasNext()){
		String key =friend.next().toString();
		Children children1 = (Children) children.get(key);
		String a= name+","+children1.getName()+",friend";
		relationship.remove(a);
		relationship.add(a);
	}
	if(aa.getFather()!=null&&aa.getMother()!=null) {
		String a= name+","+aa.getFather().getName()+",parent";
		relationship.add(a);
		String b= name+","+aa.getMother().getName()+",parent";
		relationship.remove(a);
		relationship.add(b);
	}
	if(aa.getCouple()!=null) {
		String a= name+","+aa.getCouple().getName()+",couple";
		relationship.remove(a);
		relationship.add(a);
	}
	if(aa.getChildren()!=null) {
		String a= name+","+aa.getChildren().getName()+",parent";
		relationship.remove(a);
		relationship.add(a);
	}
	Collections.sort(relationship);
	for(int j=0;j<relationship.size();j++) {
		childrenOutput+= relationship.get(j)+"\n";
	}
}

//13.According to the selected name1,name2 display the connection chain
public void showConnectionChain(String name1,String name2) {
	int i =0;
	Children c1 = (Children) children.get(name1);
	Iterator relationshipchain1 = c1.relationshipChain.keySet().iterator();
	while(relationshipchain1.hasNext()) {
		String key = relationshipchain1.next().toString();
		Children children1 = (Children) children.get(key);
		if(children1.relationshipChain.containsKey(name2)) {
			childrenOutput+= name1+"->"+key+"->"+name2;	
		}
	}
}
}
