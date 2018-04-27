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
	private int age;
	private String status;
	private String image;

	Children(String name,int age,String status,String image){
//		this.name=name;
//		this.age=age;
//		this.status=status;
//		this.image=image;
		this.setName(name);
		this.setAge(age);
		this.setStatus(status);
		this.setImage(image);
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

	public void setChildrenProfile(String name, int age, String status, String image,Children father,Children mother) {//set child attributes
		super.setName(name);
		super.setAge(age);
		super.setStatus(status);
		super.setImage(image);
		super.setFather(father);
		super.setMother(mother);
	}

}
