package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
/**
 * This class is used to access the data store used within my webservice. It includes methods
 * to insert objects, remove objects and query the data store to find certain objects.
 * 
 * @author Ryan Fan-Massey 14047957
 *
 */
//The data accessor object, used to access the data store
public enum CourseDAO {
	
	INSTANCE;
	
	/**
	 * This method is used to return a list of all of the courses within the data store
	 * @return A list containing all of the courses
	 */
	public List<Course> getAllCourses() { //returns a list of courses
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Course m"); //creates a query that selects all the course objects
		List<Course> allCourses = q.getResultList(); //stores the results in the list
		return allCourses; //and returns the list of courses
	}
	
	/**
	 * This method is used to return a list of courses, although it is very likely that it only contains a single course,
	 * based off of an argument, id, passed into the method. 
	 * @param id a String used to identify which course(s) to retrieve
	 * @return a list containing all of the courses with the passed id
	 */
	public List<Course> getCourseByID(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Course m where m.courseID = :id"); //query used to get the courses where the courseID variable is equal to an id
		q.setParameter("id", id); //which is set here using the passed id variable
		List<Course> courses = q.getResultList(); //the results are stored in this list
		return courses;	//and then returned
	}
	
	
	/**
	 * This method is used to get the courses containing a specified tutor. The method takes a string variable and queries
	 * the data store and return all of the courses that start with this string, and is case sensitive.
	 * @param tutor a String used to query the data store and return the courses with a tutor variable that begins with this string
	 * @return returns a list of courses
	 */
	public List<Course> getCourseByTutor(String tutor) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Course m where m.courseTutor like :tutor"); //using like allows for substring searches, but only if it begins with it
		q.setParameter("tutor", tutor + "%");
		List<Course> courses = q.getResultList(); //put the results is the list of courses
		return courses;	//return the list
	}
	
	/**
	 * This method is used to get the courses with a specified name. The method takes a string variable and queries
	 * the data store and return all of the courses that start with this string, and is case sensitive.
	 * @param name a String used to query the data store and return the courses with a tutor variable that begins with this string
	 * @return returns a list of courses
	 */
	public List<Course> getCourseByName(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m from Course m where m.courseName like :name"); //using like allows for substring searches, but only if it begins with it
		q.setParameter("name", name + "%"); //sets the parameter :name to the passed variable, name
		List<Course> courses = q.getResultList();	//put the results is the list of courses
		return courses; //returns the results
	}
	
	/**
	 * This method is used to add a course to the data store. This version of addCourse takes all of the necessary parameters needed to create a course, creates
	 * a course object using the parameters and adds it to the data store.
	 * @param id the id of the course being added
	 * @param name the name of the course being added
	 * @param credits the credits of the course being added
	 * @param duration the duration of the course being added
	 * @param tutor the tutor of the course being added
	 */
	public void addCourse(String id, String name, int credits, String duration, String tutor){
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Course c = new Course(id, name, credits, duration, tutor); //creates a course object using the passed variables
			em.persist(c); //adds it to the data store
			em.close(); //closes the entity manager
		}
	}
	
	/**
	 * This method is used to add a course to the data store. This version of addCourse
	 * simple takes a course parameter and adds it to the data store.
	 * @param course a course object containing the course that is going to be added
	 */
	public void addCourse(Course course){
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			em.persist(course); //adds the course to the data store
			em.close(); //closes the entity manager
		}
	}
	
	
	/**
	 * This method is used to remove a certain course from the data store, by its id
	 * @param id a String variable used to identify which course is being removed
	 */
	public void removeCourse(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Course course = em.find(Course.class, id); //find the course with the given id
			em.remove(course); //and remove it
		} finally {
			em.close(); //close the entity manager
		}
	}
	
	/**
	 * This method is used to add some data to the data store for testing purposes
	 */
	public void insertData(){
		addCourse("134573",		"Web and Mobile Development", 	150, "200", "John Smith");
		addCourse("201",		"English Literature", 			90, "100", "Hannah Taylor");
		addCourse("4126571", 	"Programming", 					150, "180", "Josh Brooke");
		addCourse("441211", 	"Computer System Fundamentals", 90, "100", "Sharon Peach");
		addCourse("741", 		"Statistics", 					60, "70", "Louise Brown");
		addCourse("741123", 	"English Literature", 			180, "180", "Hannah Taylor");
		addCourse("14856", 		"Advanced Mathematics", 		180, "180", "Louise Brown");
		addCourse("14855", 		"Information Technology", 		90, "100", "Joshua Evans");
	}
	 
}
