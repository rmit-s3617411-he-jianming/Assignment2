package MiniNet;

import java.util.HashMap;
import java.util.Iterator;

/**  
 * @version V1.6 
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/
abstract public class Person {//every person have name, age, status, friend, image, father and mother attributes
	private String name;
	private int age;
	private String status;
	HashMap friend;//built a friend array for storing friends' name
	private String image;
	private Children father;
	private Children mother;
	private String gender;
	HashMap colleague;
	HashMap classmate;
	HashMap relationshipChain;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	private String states;

	Person(){
		friend = new HashMap();
		colleague = new HashMap();
		classmate = new HashMap();
		relationshipChain = new HashMap();
	}

	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}

	public void setAge(int age) {
		this.age=age;
	}
	public int getAge() {
		return this.age;
	}

	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return this.status;
	}

	public void setFriend(String name,Children per) {
		friend.put(name, friend);
	}

	public void getFriend() {//type the ith friend you want to show
		Iterator it = friend.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next().toString();
			Children children = (Children) friend.get(key);
			System.out.println(children);
		}
		return ;
	}

	public void setImage(String image) {
		this.image=image;
	}
	public String getImage() {
		return this.image;
	}

	public void setFather(Children father) {
		this.father=father;
	}
	public Adult getFather() {
		return this.father;
	}

	public void setMother(Children mother) {
		this.mother=mother;
	}
	public Adult getMother() {
		return this.mother;
	}

	public String toString() {//override the toString method for output person basic information
		String msg=this.name+", "+this.image+", "+this.status+", "+this.gender+", "+this.age+", "+this.states;
		return msg;
	}
}
