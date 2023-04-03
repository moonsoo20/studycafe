
public class PersonBean {
	private int seat;
	private String name;
	private String pass;
	private String time;
	
	
	public PersonBean() {

	}
	public PersonBean(int seat, String name, String pass, String time) {
		super();
		this.seat = seat;
		this.name = name;
		this.pass = pass;
		this.time = time;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
