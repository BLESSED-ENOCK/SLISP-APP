<?php
include_once("login_api.php");

$sql = "SELECT Post_Id, Content,PostTime, Username FROM post JOIN users ON post.User_id = users.User_id "; // Retrieving all posts from the posts table.

$result = $conn->query($sql); // Open database connection and Execute query.

if ($result->num_rows >0) { // Checking whether there is any post record in the post table.
 
 
 while($row[] = $result->fetch_assoc()) { // If there any, Then retrieve and store in an array.
 
 $tem = $row; // Intialializing the value of the fetched records.
 
 $json = json_encode($tem); // Store the record in the JSON format.
 
 
 }
 
} else { // If there is no record, the give a message of "No Results Found."
 echo "No Results Found.";
}
 echo $json; // Output the JSON results

$conn->close();//Close the database connection.
?>