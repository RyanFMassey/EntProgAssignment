<fieldset>
	<legend>Insert Course</legend>
	<form action="#">
		<label for="insertID">Course ID:</label>
		<input type="text" id="insertID" name="insertID">
		
		</p>
		
		<label for="insertName">Course Name:</label>
		<input type="text" id="insertName" name="insertName">
		
		</p>
		
		<label for="courseCredits">Credits:</label>
		<input type="text" id="courseCredits" name="courseCredits">
					
		</p>
					
		<label for="courseDuration">Duration:</label>
		<input type="text" id="courseDuration" name="courseDuration">
		
		</p>
				
		<label for="courseTutor">Course Tutor:</label>
		<input type="text" id="courseTutor" name="courseTutor">
				
		</p>		
					
		<input type="button" value="Add Course" onclick='insertCourse("#result")'/>
		<p/>
		<div id="result" align="center"></div>
	</form>
</fieldset>