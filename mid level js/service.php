<?php

$json = '{"classes": {
	"CSE": [
		{"course": "142", "title": "Java I"},
		{"course": "143", "title": "Java II"},
		{"course": "154", "title": "Web Programming"},
		{"course": "373", "title": "Data Structures and Algorithms"} 
	],
	"INFO": [
		{"course": "101", "title": "Social Networking Technologies"},
		{"course": "200", "title": "Intellectual Foundations of Informatics"},
		{"course": "340", "title": "Database Management Systems"},
		{"course": "341", "title": "Computer Networks and Distributed Applications"} 	
	]
}}';

header("Content-type: application/json");
print json_encode($json);


?>