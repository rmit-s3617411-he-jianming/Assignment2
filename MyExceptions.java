package MiniNet;

import javax.swing.JOptionPane;

public class MyExceptions extends Exception {
	public MyExceptions() {
		super();
	}

	public MyExceptions(String msg) {
		super(msg);
		System.err.println("Error message is: " + msg);
	}

	public MyExceptions(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MyExceptions(Throwable cause) {
		super(cause);
	}

	/*when trying to make friend with a young child.*/
	public void TooYoungException() throws MyExceptions {
		throw new MyExceptions("Adult can not makes friend with a young child.");
	}

	/*when trying to connect two children with an age gap larger than 3.*/
	public void NotToBeFriendsException() throws MyExceptions{
		throw new MyExceptions("two children with an age gap larger than 3");
	}

	/*when a child or young child has no parent or has only one parent, e.g.
adding a child to one adult, or to two adults who are not a couple. That also happens when trying
to delete an adult who has at least one dependent. (In this world a couple that have at least one kid
become inseparable and immortal.)*/
	public void NoParentException() throws MyExceptions {
		throw new MyExceptions("child has no parent or has only one parent");
	}//this exception can not happen.In assignment1, It status one each children must connect two adult who are couple

	/*when trying to make two adults a couple and at least one of them is already connected with another adult as a couple.*/
	public boolean NoAvailableException(Children per1,Children per2) throws MyExceptions {
		if(per1.getCouple()!=null||per2.getCouple()!=null) {
			throw new MyExceptions("one of them is already connected with another adult as a couple");
		}else {
			per1.setCouple(per2);
			per2.setCouple(per1);
			return true;
		}
	}

	/*when trying to make a couple when at least one member is not an adult.*/
	public boolean NotToBeCoupledException(Children per1,Children per2) throws MyExceptions {
		if(per1.getAge()<16||per2.getAge()<16) {
			throw new MyExceptions("at least one member is not a adult");
		}else {
			per1.setCouple(per2);
			per2.setCouple(per1);
			return true;
		}	    	
	}

	/*when trying to enter a person whose age is negative or over 150. (You can treat age as integer)*/
	public void NoSuchAgeException() throws MyExceptions {
		throw new MyExceptions("type a right age(not positive and not under 150)");
	}

	/*when trying to connect a child in a colleague relation.*/
	public boolean NotToBeColleaguesException(Children per1,Children per2) throws MyExceptions {
		if(per1.getAge()<16||per2.getAge()<16) {
			throw new MyExceptions("the child is under 16, can not child in a colleague relation");
		}else {
			per1.colleague.put(per2.getName(), per2);
			per2.colleague.put(per1.getName(),per1);
			return true;
		}	
	}

	/*when trying to make a young child in a classmate relation*/
	public boolean NotToBeClassmatesException(Children per1,Children per2) throws MyExceptions {
		if(per1.getAge()<6||per2.getAge()<6) {
			throw new MyExceptions("the child is under 16, can not child in a classmate relation");
		}else if((per1.getAge()<16&&per1.getAge()<16)||(per1.getAge()>16&&per2.getAge()>16)) {	
			return true;
		}else {
			return false;
		}
	}
}
