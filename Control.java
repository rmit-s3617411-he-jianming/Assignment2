package MiniNet;
/**  
 * @version V1.6  
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/
import java.util.*;
public class Control implements Print{
	Scanner in = new Scanner(System.in);

	MyExceptions exceptions;
	HashMap children;

	Control(){
		children = new HashMap();//initialize a Children array
		exceptions = new MyExceptions();
	}


	//detect the person age
	public boolean detectAge(String name1,String name2) throws MyExceptions  {
		boolean a=true;//if a = true the system will judge the person is over 16 age
		Children per1 = per1 = (Children) children.get(name1);
		Children per2 = per2 =(Children) children.get(name2);
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
	public void createAdult(String name,Children per) {
		if(children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			System.out.println("The name already exist");
		}	    
		else
		{
			children.put(name, per);
			System.out.println("Add Successfully");	    
		}
	}

	//2.Add a children into network
	public void createChildren(int age) {
		Children mother = null;
		Children father = null;
		System.out.println("You are under 16, so you have to provide your parent's information");
		System.out.println("please type your name");
		String cname = in.next();
		System.out.println("please type your status");
		String cstatus = in.next();
		System.out.println("please type your image");
		String cimage = in.next();
		System.out.println("please type choose your mother's gender:1.F 2.M");
		String cgender = null;
		if(in.next().equals("1")) {
			cgender ="F";
		}else if(in.next().equals("2")) {
			cgender ="M";
		}
		System.out.println("please type your states");
		String cstates =in.next();
		Children per = new Children(cname,age,cstatus,cimage,cgender,cstates);
		this.createAdult(cname, per);
		System.out.println("please type your mother's name");
		String mname=in.next();
		if(children.containsKey(mname)) {
			mother =(Children) children.get(mname);
			System.out.println("Your mother has created a account, now connect your's relationship");
			this.createAdult(mname, per);//create a mother account
			per.setMother(mother);
			mother.setChildren(per);
		}else {
			System.out.println("please type your mother's age");
			int mage =in.nextInt();
			System.out.println("please type your mother's status");
			String mstatus =in.next();
			System.out.println("please type your mother's image");
			String mimage =in.next();
			System.out.println("please type choose your mother's gender:1.F 2.M");
			String mgender = null;
			if(in.next().equals("1")) {
				mgender ="F";
			}else if(in.next().equals("2")) {
				mgender ="M";
			}
			System.out.println("please type your states");
			String mstates =in.next();
			mother = new Children(cname,age,cstatus,mimage,mgender,mstates);
			this.createAdult(mname, mother);
			per.setMother(mother);
			mother.setChildren(per);
		}
		System.out.println("please type your father's name");
		String fname =in.next();
		if(children.containsKey(fname)) {
			father =(Children) children.get(fname);
			System.out.println("Your mother has created a account, now connect your's relationship");
			per.setMother(father);
			father.setChildren(per);
		}else {
			System.out.println("please type your father's age");
			int fage = in.nextInt();
			System.out.println("please type your father's status");
			String fstatus = in.next();
			System.out.println("please type your father's image");
			String fimage = in.next();
			System.out.println("please type choose your father's gender:1.F 2.M");
			String fgender = null;
			if(in.next().equals("1")) {
				fgender ="F";
			}else if(in.next().equals("2")) {
				fgender ="M";
			}
			System.out.println("please type your states");
			String fstates =in.next();
			father = new Children(cname,age,cstatus,fimage,fgender,fstates);
			this.createAdult(fname, father);
			per.setFather(per);
			father.setChildren(per);
			father.setCouple(mother);
			mother.setCouple(father);
		}
	}

	//2.Display the profile of the selected person
	public void show(String name) {//show the profile about your selected person.
		if(children.containsKey(name)) {
			Children per = (Children) children.get(name);
			System.out.println(msg);
			System.out.println(per);
			return;
		}
		else  {
			System.out.println("the person is no exist");//if the person cannot be fond,end of the loop
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

	//4.Delect the selected person
	public void delect(String name) {//delect the person 
		Children child = (Children) children.get(name);
		if(children.containsKey(name)){  //for judge the name whether exist.prevent a system have two same name
			if(child.getFather()!=null) {
				child.getFather().setChildren(null);
			}if(child.getMother()!=null) {
				child.getMother().setChildren(null);
			}
			children.remove(name);
		}else{
			System.out.println("The name is not exist");
			return;
		}
	}

	//5.Connect two person in a meaningful way
	public void connect(String name1,String name2) throws MyExceptions  {//connect two person in a meaningful way
		this.check(name1);
		this.check(name2);
		Children per1 = (Children) children.get(name1);	    
		Children per2 = (Children) children.get(name2);
		System.out.println("please type what kind of realtionship you want to add 1.friend 2.parent 3.Colleague 4.Classmate 5.couple");
		String relationship=in.next();
		if(relationship.equals("1")) {//make friends
			if(this.detectAge(name1,name2)) {
				per1.friend.put(per2.getName(),per2);//put person2(name2/per2) into person1's(name/per1) friends' list 
				per2.friend.put(per1.getName(),per1);//put person1(name/per1) into person2's(name/per2) friends' list 
				System.out.println("built relationship successful");
				return;
			}else {
				System.out.println("they cannot be friend, update unsuccessful");
			}
		}else if(relationship.equals("2")) {//set the parent
			System.out.println("please choose what the older people is?:"+"1."+name2+" is father "+"2."+name2+" is mother");
			String no =in.next();
			if(per1.getAge()>per2.getAge()||no.equals("1")) {//choose the second person is a father
				per2.setFather(per1);
			}else if (per1.getAge()>per2.getAge()||no.equals("2")) {//choose the second person is a mother
				per2.setMother(per1);
			}else if(per1.getAge()<per2.getAge()||no.equals("1")) {//choose the second person is a father
				per1.setFather(per2);
			}else if(per1.getAge()<per2.getAge()||no.equals("2")) {//choose the second person is a father
				per1.setFather(per2);
			}else if(relationship.equals("3")) {
				try {
					if(per1.getAge()>16&&per2.getAge()>16||exceptions.NotToBeColleaguesException(per1, per2)) {
						per1.colleague.put(name2,per2);
						per2.colleague.put(name1, per1);
						System.out.println("built relationship successful");
						return;}
				}catch(Exception e) {
					System.out.println("they cannot be friend, update unsuccessful");
				}
			}else if(relationship.equals("4")) {
				try {
					if(exceptions.NotToBeClassmatesException(per1, per2)) {
						per1.classmate.put(name2, per2);
						per2.classmate.put(name1, per1);
						System.out.println("built relationship successful");
						return;
					}
				}catch(Exception e) {
					System.out.println("they cannot be friend, update unsuccessful");
				}
			}else if(relationship.equals("5")) {
				try {
					if(exceptions.NoAvailableException(per1,per2)&&exceptions.NotToBeCoupledException(per1, per2)) {
						System.out.println("built relationship successful");
						return;
					}
				}catch(Exception e) {
					System.out.println("they cannot be friend, update unsuccessful");
				}
			}}}

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
		System.out.println(msg);
		Iterator it = children.keySet().iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			Children children1 = (Children) children.get(key);
			System.out.println(children1);
		}
	}

	//9.According to the selected name,display the list of friends
	public void display(String name) {
		Children aa = (Children) children.get(name);
		aa.getFriend();
	}
}
