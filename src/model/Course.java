package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//Main course object
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String courseID;
	private String courseName;
	private int courseCredits;
	private String courseDuration;
	private String courseTutor;
	
	//course constructor
	public Course(String ID, String name, int credits, String duration, String tutor) {
		setCourseID(ID);
		setCourseName(name);
		setCourseCredits(credits);
		setCourseDuration(duration);
		setCourseTutor(tutor);
	}

	//Course getters and setters
	public String getCourseID() {
		return courseID;
	}


	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public int getCourseCredits() {
		return courseCredits;
	}


	public void setCourseCredits(int courseCredits) {
		this.courseCredits = courseCredits;
	}


	public String getCourseDuration() {
		return courseDuration;
	}


	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}


	public String getCourseTutor() {
		return courseTutor;
	}


	public void setCourseTutor(String courseTutor) {
		this.courseTutor = courseTutor;
	}

	//own toString method
	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", courseName=" + courseName + ", courseCredits=" + courseCredits
				+ ", courseDuration=" + courseDuration + ", courseTutor=" + courseTutor + "]";
	}
	
	
	
}
