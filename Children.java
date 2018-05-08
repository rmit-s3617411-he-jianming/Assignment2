package MiniNet;
/**  
 * @version V1.6   
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/
public class Children extends Adult {
	private Children father;//each child has father
	private Children mother;//each child has mother
	private String name;
	private int		 age;
	private String status;
	private String image;
	private String gender;
	private String states;
	
	Children(String name,String status,String image,String gender,int age,String states){
		this.setName(name);
		this.setAge(age);
		this.setStatus(status);
		this.setImage(image);
		this.setGender(gender);
		this.setStates(states);
	}
	
	public void setFather(Children father) {
		this.father=father;
	}
	public Adult getFather() {
		return  this.father;
	}

	public void setMother(Children mother) {
		this.mother=mother;
	}
	public Adult getMother() {
		return this.mother;
	}

}
