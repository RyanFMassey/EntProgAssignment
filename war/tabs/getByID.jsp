<fieldset>
	<legend>Get Course By ID</legend>
	<form action="#">
		<label for="courseID">Course ID:</label>
		<input type="text" id="courseID" name="courseID">
		<input type="button" value="Find Course" onclick='getByID("#idTable", "courseID")'/>
		<input type="button" value="Clear" onclick='clearDiv("#idTable")'/>
		<p/>
		<div id="idTable" align="center"></div>
	</form>
</fieldset>