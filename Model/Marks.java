package Model;
////Using Encapsulation to get the data of the marks

public class Marks {
	private int id;
	private String studentName;
	private String level;
	private String course;
	private String marks;
	public Marks(int id, String studentName, String course, String level, String marks) {
		this.id = id;
		this.studentName = studentName;
		this.course = course;
		this.level = level;
		this.marks = marks;
	}
	public int getId() {
		return id;
	}
	public String getstudentName() {
		return studentName;
	}
	public String getLevel() {
		return level;
	}
	public String getMarks() {
		return marks;
	}
	public String course() {
		return course;
	}	
}