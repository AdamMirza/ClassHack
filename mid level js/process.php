<?php
	$dictionary = file_get_contents("sample.txt");

	$parts = split("\n", $dictionary);
	for ($i = 0; $i < count($parts); $i++) { 
		$components = split("::", $parts[$i]);
		list($department, $course_num, $title) = $components;
		// do something with $department, $course_num, $title
	}

?>

