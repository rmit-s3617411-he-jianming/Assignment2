package MiniNet;

import javax.swing.JOptionPane;

public class MyExceptions extends Exception{
	     public MyExceptions() {
	        super();
	    }
	    
	    public MyExceptions(String msg) {
	        super(msg);
	        System.out.println("Error message is: " + msg);
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
	    
/*when trying to make an adult and a child friend or connect two children with an age gap larger than 3.*/
	    public void NotToBeFriendsException() throws MyExceptions{
	    	throw new MyExceptions("two children with an age gap larger than 3");
	    }
	    
/*when a child or young child has no parent or has only one parent, e.g.
adding a child to one adult, or to two adults who are not a couple. That also happens when trying
to delete an adult who has at least one dependent. (In this world a couple that have at least one kid
become inseparable and immortal.)*/
	    public void NoParentException() throws MyExceptions {
	    	throw new MyExceptions("child has no parent or has only one parent");
	    }
	    
/*when trying to make two adults a couple and at least one of them is already connected with another adult as a couple.*/
	    public void NoAvailableException() throws MyExceptions {
	    	throw new MyExceptions("one of them is already connected with another adult as a couple");
	    }
	    
/*when trying to make a couple when at least one member is not an adult.*/
	    public void NotToBeCoupledException() throws MyExceptions {
	    	throw new MyExceptions("at least one member is not a adult");
	    }
	    
/*when trying to enter a person whose age is negative or over 150. (You can treat age as integer)*/
	    public void NoSuchAgeException() throws MyExceptions {
	    	throw new MyExceptions("type a right age(positive and under 150)");
	    }
	    
/*when trying to connect a child in a colleague relation.*/
	    public void NotToBeColleaguesException() throws MyExceptions {
	    	throw new MyExceptions("the child is under 16, can not child in a colleague relation");
	    }
	    
/*when trying to make a young child in a classmate relation*/
	    public void NotToBeClassmatesException() throws MyExceptions {
	    	throw new MyExceptions("the child is under 16, can not child in a classmate relation");
	    }
	    
}
