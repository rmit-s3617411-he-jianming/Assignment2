package MiniNet;
/**  
 * @version V1.6  
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/
import java.util.*;
public class Control implements Print{
	Scanner in = new Scanner(System.in);

	HashMap children;

	Control(){
		children = new HashMap();//initialize a Children array
	}

	/*detect the person whether the age under 16 */
	public boolean detectAge(String name1,String name2) {//for detect the children weather conform the situation
		boolean a=true;//if a = true the system will judge the person is over 16 age
		Children per1 = per1 = (Children) children.get(name1);
		Children per2 = per2 =(Children) children.get(name2);
		if(2<per1.getAge()&&per1.getAge()<16&&2<per2.getAge()&&per2.getAge()<16
				&&per1.getFather().getName()!=per2.getFather().getName()
				&&(per1.getAge()-per2.getAge()<3||per2.getAge()-per1.getAge()<3)){
			a =true;
		}else if(per1.getAge()>16&&per2.getAge()>16){
			a=true;
		}else {
			System.out.println("Maybe there are something worng :1.they are come from same family or 2.one children over 16 and one children under 16"
					+ " 3.The age difference between these two young friends cannot be more than 3 years");
			a=false;
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

	//1.Add a Adult or Children into network
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
	public void createChildren(String name,Children per) {//for create a new person
		if(children.containsKey(name))  
		{
			System.out.println("The name already exist");
		}	    
		else
		{
			children.put(name, per);
			System.out.println("Add Successfully");	    
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
			System.out.println("choose what kind of information you want to update 1.name 2.age 3.status 4.image");
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
			}
		}    
		else{
			System.out.println("The name is not exist");
			return;
		}
	}

	//4.Delect the selected person
	public void delect(String name) {//delect the person 
		if(children.containsKey(name))  //for judge the name whether exist.prevent a system have two same name
		{
			children.remove(name);
		}	    
		else if(children.containsKey(name)){
			children.remove(name);
		}
		else{
			System.out.println("The name is not exist");
			return;
		}
	}

	//5.Connect two person in a meaningful way
	public void connect(String name1,String name2) {//connect two person in a meaningful way
		this.check(name1);
		this.check(name2);
		Children per1 = (Children) children.get(name1);	    
		Children per2 = (Children) children.get(name2);
		System.out.println("please type what kind of realtionship you want to add 1.friend 2.parent");
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
			}
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
			System.out.println(children1);
		}
	}
	
		//9.According to the selected name,display the list of friends
		public void display(String name) {
			Children aa = (Children) children.get(name);
			aa.getFriend();
		}
}
