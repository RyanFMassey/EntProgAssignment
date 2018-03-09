<fieldset>
	<legend>Get Course By Name</legend>
	<form action="#">
		<label for="courseName">Course Name:</label>
		<input type="text" id="courseName" name="courseName">
		<input type="button" value="Find Course(s)" onclick='getByName("#courseTable", "courseName")'/>
		<input type="button" value="Clear" onclick='clearDiv("#courseTable")'/>
		<p/>
		<div id="courseTable" align="center"></div>
	</form>
</fieldset>