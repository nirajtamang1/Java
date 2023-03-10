package Model;
//Using Encapsulation to get the data from the course

public class Course{
	  private String id;
      private String course;
      private String years;
      private String isActive;
      
      public Course(String id, String course, String years, String isActive) {
    	  this.id = id;
    	  this.course= course;
    	  this.years = years;
    	  this.isActive = isActive;  
      }
	public String getId() {
		return id;
	}

	public String getName() {
		return course;
	}

	public String getYears() {
		return years;
	}

	public String getisActive() {
		return isActive;
	}
    
}