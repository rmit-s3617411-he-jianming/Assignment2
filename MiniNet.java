package MiniNet;
/**  
 * @version: V1.6 
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/
import java.util.*;

import javax.swing.JOptionPane;

import javafx.scene.chart.PieChart.Data;
public class MiniNet {
	HashMap children;

	MiniNet() throws Exception{
		children = new HashMap();//initialize a Children array
	}

	public static void main(String[] args) throws Exception  {
		Scanner in = new Scanner(System.in);
		Control admin = new Control();//initialize a control object
		DriverClass menu = new DriverClass();//initialize a menu object 
		MyExceptions exception = new MyExceptions();
		while(true) {
			menu.menu();//invoke menu method in DriverClass class
			String number = in.next();
			//for giving the tips to user what they can do
			if(number.equals("1")) {
				System.out.println("----Now add a person into network----");
				System.out.println("Please type the age of person you want to add");
				int age = in.nextInt();
				if(age<16) {
					
				}else if(age<0||age>150) {
					try {
						exception.NoSuchAgeException();
					}catch(Exception e) {
						System.err.println("please type age from 0 to 150");
					}
				}
				else {
					System.out.println("Please type the name of person you want to add");
					String name = in.next();
					System.out.println("Please type the status of person you want to add");
					String status=in.next();
					System.out.println("Please type the image name of the person you want to add,if you donot want to add,please type null");
					String image = in.next();
					System.out.println("please type choose your gender:1.F 2.M");
					String gender = null;
					if(in.next().equals("1")) {
						gender ="F";
					}else if(in.next().equals("2")) {
						gender ="M";
					}
					System.out.println("please type your states");
					String states =in.next();
					Children per = new Children(name,age,status,image,gender,states);
					System.out.println(per);
					admin.createAdult(name, per);
				}
			}else if(number.equals("2")) {
				System.out.println("----Now display the profile of the selected person----");
				System.out.println("Please type the name of person you want to select");
				String name = in.next();
				admin.show(name);
			}else if(number.equals("3")) {
				System.out.println("----Now update the profile infromation of the selected person----");
				System.out.println("Please type the name of person you want to upadate");
				String name = in.next();
				admin.updateProfile(name);
			}else if(number.equals("4")) {
				System.out.println("----Now delect the selected person----");
				System.out.println("please type the name of person you want to delect");
				String name = in.next();
				admin.delect(name);
			}else if(number.equals("5")) {
				System.out.println("----Now connect two person in a meaningful way----");
				System.out.println("please type the name of the first person you want to connect");
				String name = in.next();
				System.out.println("please type the name of the second person you want to connect");
				String name2 = in.next();
				admin.connect(name, name2);
			}else if(number.equals("6")) {
				System.out.println("----Now find out whether a person is a direct friend of another person----");
				System.out.println("please type the name of the first person you want to find out");
				String name1 =in.next();
				System.out.println("please type the name of the second person you want to find out");
				String name2 =in.next();
				admin.findOut(name1, name2);
			}else if(number.equals("7")) {
				System.out.println("----Now find out the name(s) of a person¡¯s child(ren) or the names of the parents----");
				System.out.println("please type the name of person you want to find out");
				String name = in.next();
				admin.findParent(name);
			}else if(number.equals("8")) {
				System.out.println("----Now show all the person profile----");
				admin.print();
			}else if(number.equals("9")) {
				System.out.println("----Now according to the selected name,display the list of friedns----");
				System.out.println("----Please type the name of person you want to display his/her friends' list----");
				String name = in.next();
				System.out.println(name+"'s list of friends");
				admin.display(name);
			}else if(number.equals("0")) {
				System.out.println("end of program");
				System.exit(0);
			}else {
				System.out.println("please type right number");
			}
		}
	}
}
