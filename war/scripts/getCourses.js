/*
 * To Do
 * 
 * 
 *
 * -RESTful implementation
 * -WSDL
 * -MAYBE JQuery UI
 * -Add loading GIF
 */

function getAll(resultRegion, type){
	console.log(resultRegion + ", " + getValue(type));
	if (getValue(type) === "XMLtable") {
		getAllByXML(resultRegion);
	} else if (getValue(type) === "JSONtable") {
		getAllByJSON(resultRegion);
	} else if (getValue(type) === "list") {
		getAllString(resultRegion);
	}
}

function getByID(resultRegion, id) {
	$(resultRegion).html(""); //Removes previous results
	var urlString = "courses?getBy=id&format=json&id=" + getValue(id);
	$.ajax({
		type: "GET",
		url: urlString,
		success: function(data) {
			showJSONTable(data, resultRegion);
		}
	});
}

function getByTutor(resultRegion, tutor) {
	$(resultRegion).html(""); //Removes previous results
	var urlString = "courses?getBy=tutor&format=json&tutor=" + getValue(tutor);
	$.ajax({
		type: "GET",
		url: urlString,
		success: function(data) {
			showJSONTable(data, resultRegion);
		}
	});
}

function getByName(resultRegion, name) {
	$(resultRegion).html(""); //Removes previous results
	var urlString = "courses?getBy=name&format=json&name=" + getValue(name);
	$.ajax({
		type: "GET",
		url: urlString,
		success: function(data) {
			showJSONTable(data, resultRegion);
		}
	});
}

function getAllByXML(resultRegion) {
	$(resultRegion).html(""); //Removes previous results
	$.ajax({
		type: "GET",
		url: "courses?getBy=all&format=xml",
		dataType: "xml",
		success: function(data) {
			showXMLTable(data, resultRegion);
		}
	});
}

function getAllByJSON(resultRegion) {
	$(resultRegion).html(""); //Removes previous results
	$.ajax({
		type: "GET",
		url: "courses?getBy=all&format=json",
		success: function(data) {
			showJSONTable(data, resultRegion);
		}
	});
}

function getAllString(resultRegion){
	$(resultRegion).html(""); //Removes previous results
	$.ajax({
		type: "GET",
		url: "courses?getBy=all",
		success: function(data) {
			showAsString(data, resultRegion);
		}
	});
}

function showAsString(data, resultRegion) {
	console.log(data);
	var strings = data.split("\n");
	for (var i = 0; i < strings.length; i++) {
		$(resultRegion).append(strings[i] + "<br>");
	}
	
}

function showXMLTable(data, resultRegion) {
	console.log(data);
	var headings = [];
	var row = [];
	var rows = [];
	var i = 0;
	$(data).find("headings").each(function(){
		$(this).children().each(function(){
			var heading = $(this).text();
			headings.push(heading);
		})
	})
	
	$(data).find("course").each(function(){
		$(this).children().each(function(){
			var value = $(this).text();
			row.push(value);
		})
		
		rows.push(row);
		row = [];
		//$(resultRegion).append($(this).find("id").text() + "<br />");
	})
	
	var table = getTable(headings, rows);
	console.log(table);
	$(resultRegion).append(table);
}


function showJSONTable(data, resultRegion) {
			console.log(data);
			var json = eval("(" + data + ")");
		    var table = getTable(json.headings, json.courses);
			console.log(table);
			$(resultRegion).append(table);
}



function getTable(headings, rows) {
	  var table = "<table border='1' class='ajaxTable'>\n" +
	              getTableHeadings(headings) +
	              getTableBody(rows) +
	              "</table>";
	  return(table);
}

function getTableHeadings(headings) {
	  var firstRow = "  <tr>";
	  for(var i=0; i<headings.length; i++) {
	    firstRow += "<th>" + headings[i] + "</th>";
	  }
	  firstRow += "</tr>\n";
	  return(firstRow);
}

function getTableBody(rows) {
	var body = "";
	for(var i=0; i<rows.length; i++) {
		body += "  <tr>";
		var row = rows[i];
		for(var j=0; j<row.length; j++) {
			body += "<td>" + row[j] + "</td>";
		}
		body += "</tr>\n";
	}
	return(body);
}


function clearDiv(resultRegion) {
	$(resultRegion).empty();
}


function getValue(id) {
	  return(escape(document.getElementById(id).value));
}


function insertCourse(resultRegion) {
	console.log("1");
	var jsonForm = $("#insert").serializeArray();
	$.ajax({
		type: "POST",
		url: "insertCourses",
		data: jsonForm,
		dataType: "text",
		async: false,
		success: function(data) {
			console.log("2");
			$(resultRegion).html("");
			$(resultRegion).append(data);
		}
	});	
}

