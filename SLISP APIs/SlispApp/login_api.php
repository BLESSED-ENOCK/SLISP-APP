<?php
error_reporting(1);
//ini_set('display_errors', 1);

//Initiallise server connection variables.
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "slisp_app";
// Create database connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
 die("Connection failed: " . mysqli_connect_error()); // Output connection failure message.
}
?>