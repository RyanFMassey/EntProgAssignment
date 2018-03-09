package ocm.epmmu;

import java.io.BufferedReader;
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
public class insertCoursesServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get and set the variables
		String courseID = request.getParameter("id");
		String courseName = request.getParameter("name");
		String courseTutor = request.getParameter("tutor");
		String courseDuration = request.getParameter("duration");
		int courseCredits = -1;
		
		PrintWriter out1 = response.getWriter();
		
		try {
			
			//Get within the try as the string may not be able to be parsed
			courseCredits = (Integer.parseInt(request.getParameter("credits")));
			
			
			if ((courseID != null) && (courseName != null) && (courseTutor != null) && (courseDuration != null) && (courseCredits != -1)) { //if all of the variables are valid
				try {
					CourseDAO.INSTANCE.addCourse(courseID, courseName, courseCredits, courseDuration, courseTutor); //try to add the course to the data store
					out1.println("Course added successfully"); //print to the user
				    out1.close();//close the writer
				} catch (Exception e) {
				    out1.println("One or more fields have errors"); //print to the user if the addCourse failed
				    out1.close(); //close the writer
				}
			} else {
			    out1.println("One or more fields are null"); //print to the user if one or more of the fields are null
			    out1.close();
			}
		} catch (Exception e) {
			 out1.println("Please enter a valid value for credits"); //print to the user if the credits value could not be parsed
			 out1.close();
		}
		
		
	}
}
