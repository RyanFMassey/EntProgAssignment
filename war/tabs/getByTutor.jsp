<fieldset>
	<legend>Get Course By Tutor</legend>
	<form action="#">
		<label for="tutorName">Tutor Name:</label>
		<input type="text" id="tutorName" name="tutorName">
		<input type="button" value="Find Course(s)" onclick='getByTutor("#tutorTable", "tutorName")'/>
		<input type="button" value="Clear" onclick='clearDiv("#tutorTable")'/>
		<p/>
		<div id="tutorTable" align="center"></div>
	</form>
</fieldset>