    <?php 


include_once("login_api.php");

$PostId = $_POST['Post_Id']; // Intializing the unique post whose comments are to be retrived from the comments table.


$sql = "SELECT Content, CommentTime, Username FROM comments JOIN users ON comments.User_id = users.User_id WHERE Post_Id =$PostId";//Retriving data from comments and users tables for a specific post.



$result = $conn->query($sql); //Open database connection and execute retrieval query.

if ($result->num_rows >0) { // Checking whether there is a desired record in the comments table.
 
 
 while($row[] = $result->fetch_assoc()) { // If found then get data and put it an array.
 
 $tem = $row; // Intializing the retrived data.
 
 $json = json_encode($tem); // Store the Retrieved data in JSON format.
 
 
 }
 
} else {
 echo "No Results Found."; // if there is no desired record, then outpot message "No Results Found."
}
 echo $json; //Output the JSON data

$conn->close(); // Then Close the database connection.  
?>