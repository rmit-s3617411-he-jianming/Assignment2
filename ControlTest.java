package MiniNet;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class ControlTest {
	Control control;
	Children per1 = new Children("a", "b", "c", "d", 17,"e");
	Children per2 = new Children("bb", "cc", "dd", "ee", 12,"ff");
	Children per3 = new Children("ccc", "ddd", "eee", "fff", 20,"ggg");
	Children per4 = new Children("ddd", "ddd", "eee", "fff", 20,"ggg");
	HashMap children =new HashMap();
	
	@Test
	public void testCreateAdult() throws Exception {
	control = new Control();
	control.createAdult("a", per1);
	}
	
	@Test
	public void testDetectAge() throws Exception {
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.detectAge("a", "ccc");
	}

	@Test
	public void testCheck() throws Exception{
		control = new Control();
		children.put("a", per1);
		control.check("a");
	}

	@Test
	public void testShowString() throws Exception{
		control = new Control();
		children.put("a", per1);
		control.show();
	}

	@Test
	public void testUpdateProfile() throws Exception{
		control = new Control();
		children.put("ccc", per3);
		control.updateProfile("ccc",  "dddd", "eee", "fff", 20,"ggg");
	}

	@Test
	public void testDelect() throws Exception{
		control = new Control();
		children.put("ccc", per3);
		control.delect("ccc");
	}

	@Test
	public void testConnectCouple() throws Exception{
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.connectCouple("ccc", "a");
	}

	@Test
	public void testConnectColleague() throws Exception{
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.connectColleague("a", "ccc");
	}

	@Test
	public void testConnectClassmate()throws Exception {
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.connectClassmate("a", "ccc");
	}

	@Test
	public void testConnectFriend() throws Exception{
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.connectFriend("a", "ccc");
	}

	@Test
	public void testShowRelationship() throws Exception{
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.connectFriend("a", "ccc");
		control.showRelationship("a");
	}

	@Test
	public void testShowConnectionChain() throws Exception {
		control = new Control();
		control.createAdult("a", per1);
		children.put("a", per1);
		control.createAdult("ccc", per3);
		children.put("ccc", per3);
		control.createAdult("ddd", per4);
		children.put("ddd", per4);
		control.connectFriend("a", "ccc");
		control.connectClassmate("ccc", "ddd");
		control.showConnectionChain("a", "ddd");
	}

}
