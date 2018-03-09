package ocm.epmmu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.CourseDAO;

@SuppressWarnings("serial")
public class CoursesServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get the parameters form the URL and store them in variables
		String getBy = request.getParameter("getBy");
		String courseName = request.getParameter("name");
		String courseID = request.getParameter("id");
		String format = request.getParameter("format");
		String tutor = request.getParameter("tutor");
		
		//Create a a new, empty array list, used to store courses
		List<Course> courses = new ArrayList();
		
		//Create a new string, used to store the output from some jsp
		String outputPage = null;
		
		System.out.println("name: " + courseName);
		System.out.println("id: " + courseID);
		System.out.println("format: " + format);
		
		if (getBy != null) {	//if the getBy parameter has been set
			if (getBy.equals("all")) { //and it equals "all"
				courses = CourseDAO.INSTANCE.getAllCourses();  //create an instance of the DAO and run the method getAllCourses and store it in the course variable
				request.setAttribute("courses", courses);		//set the courses attribute to the courses variable
				request.setAttribute("count", courses.size());	//set the count attribute to the size of the courses list
			} else if (getBy.equals("id")) {	//if the getBy parameter equals id
				if(courseID != null) {		//and the id parameter is set
					courses = CourseDAO.INSTANCE.getCourseByID(courseID);  //create an instance of the DAO and run the getByID method, passing in the id variable as an argument
					request.setAttribute("courses", courses);  //set the courses attribute to the courses variable
					request.setAttribute("count", courses.size());  //set the count attribute to the size of the courses list
				}
			} else if (getBy.equals("tutor")) {  //if the getBy parameter equals tutor
				if (tutor != null) {	//and the tutor parameter is set
					courses = CourseDAO.INSTANCE.getCourseByTutor(tutor); //get the courses by tutor
					request.setAttribute("courses", courses);		//and put them in the courses attribute
					request.setAttribute("count", courses.size());
				}				
			} else if (getBy.equals("name")) {	//if the getBy parameter equals name
				if (courseName != null) {		//and the name attribute is set
					courses = CourseDAO.INSTANCE.getCourseByName(courseName);	//get the courses by name
					request.setAttribute("courses", courses);		//and store the results in the courses attribute
					request.setAttribute("count", courses.size());
				}
			} else if (getBy.equals("fill")) {  //used to fill the data store for testing purposes
				CourseDAO.INSTANCE.insertData();
			}
		}
		
		if (courses != null) {  //if there are some results form the above getBy methods
			if (format != null) {	//and a format has been specified
				if (format.equals("xml")) {		//if the format is xml
					outputPage = "/WEB-INF/formats/courses-xml.jsp"; //outpage equals the result of the courses-xml.jsp file
					RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage); //the outpage is then output to the user using a dispatcher
					dispatcher.include(request, response);
					
				} else if (format.equals("json")) { //if the format is json
					outputPage = "/WEB-INF/formats/courses-json.jsp"; //then output page equals the result of the courses-json.jsp file
					RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage); //and the results are output to the user using a dispatcher
					dispatcher.include(request, response);
				    
				} else { //if the format parameter is set but does not equal xml or json then the results are printed as a list
					PrintWriter out1 = response.getWriter();
				    out1.println(getCoursesAsString(courses));
				    out1.close();
				}
			} else { //if the format has not been set then the results are printed as a list
				PrintWriter out1 = response.getWriter();
			    out1.println(getCoursesAsString(courses)); //outputs the result of the getCoursesAsStrings method with the courses list passed into it
			    out1.close(); //closes the writer
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getCoursesAsString(List<Course> courses){
		String coursesString = ""; //create an empty string
		for (int i = 0; i < courses.size(); i++) { //for each of the courses within the course list
			coursesString = coursesString + "ID: " + courses.get(i).getCourseID() + "\nName: " + courses.get(i).getCourseName() + "\nCredits: " + courses.get(i).getCourseCredits() + "\nTutor: " + courses.get(i).getCourseTutor() + "\nDuration: " + courses.get(i).getCourseDuration() + "\n\n"; //take the variables and them to the output string in a list format, using \n to seperate them
		}
		return coursesString;
	}
}
