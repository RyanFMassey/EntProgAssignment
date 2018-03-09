<fieldset>
	<legend>Get All Courses</legend>
	<form action="#">
		<label for="return-type">Return:</label>
		<select id="return-type">
			<option value="XMLtable">Table from XML</option>
			<option value="JSONtable">Table from JSON</option>
			<option value="list">String</option>
		</select>
				
		<input type="button" value="Show Courses" onclick='getAll("#allTable", "return-type")'/>
		<input type="button" value="Clear" onclick='clearDiv("#allTable")'/>
		<p/>
		<div id="allTable" align="center"></div>
	</form>
</fieldset>