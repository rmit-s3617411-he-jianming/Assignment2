package MiniNet;
/**  
 * @version V1.6 
 * @author jammy  
 * data 2018Äê3ÔÂ22ÈÕ 22:44:04
 **/
public class Adult extends Person {//adult
	private Adult couple;//each parent has a couple
	private Children Children;//each parent has a child at least

	
	public void setCouple(Adult couple) {
		this.couple=couple;
	}
	public Adult getCouple() {
		return this.couple;
	}

	public void setChildren(Children children) {
		this.Children=children;
	}
	public Children getChildren() {
		return this.Children;
	}
}
