<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<courses>
	<headings>
	    <heading>ID</heading>
	    <heading>Name</heading>
	    <heading>Credits</heading>
	    <heading>Duration</heading>
	    <heading>Tutor</heading>
  	</headings>
 
 	<c:forEach items="${courses}" var="course">
	   <course>
	    <id>${course.courseID}</id>
	    <name>${course.courseName}</name>
	    <credits>${course.courseCredits}</credits>
	    <duration>${course.courseDuration}</duration>
	    <tutor>${course.courseTutor}</tutor>
	  </course>
	</c:forEach>
	
</courses>