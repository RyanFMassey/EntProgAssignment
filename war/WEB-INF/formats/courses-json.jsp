<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>{headings: ["ID", "Name", "Credits", "Duration", "Tutor"],
 courses: [
	<c:forEach items="${courses}" var="course" varStatus="loop">["${course.courseID}", "${course.courseName}", "${course.courseCredits}", "${course.courseDuration}", "${course.courseTutor}"]<c:if test="${!loop.last}">,</c:if>
	</c:forEach>]
}
