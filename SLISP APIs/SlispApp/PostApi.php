
<?PHP
include_once("login_api.php");
  // Initailise the Values to be captured in the post table.
    $Content = $_POST['Content'];
    $User_id = $_POST['User_id'];
   
   date_default_timezone_set('Africa/Nairobi'); //Set the Time zone to be African. 
   $Time = date("Y-m-d H:i:s A"); // Initialise and capture the Post Time automatically in Date-Time format.
    
    // insertion to post table
   $sql = "INSERT INTO post (Content,PostTime,User_id) VALUES ('$Content','$Time' ,$User_id)";

   $result = mysqli_query($conn,$sql); // We open database connection and Execute the first Query.

   $Post_Id = mysqli_insert_id( $conn ); //- Retrieving the last insert Post_Id

// insertion to comments table
 $Comment2= "You may please make a comment"; // Intialise a default Value to be captured as a comment.

$sql2 = "INSERT INTO comments (Content,CommentTime,Post_Id, User_id) VALUES ('$Comment2','$Time','$Post_Id','$User_id')"; // Capture a default comment data in the comments table.

$result2 = mysqli_query($conn,$sql2); // We open database connection and Execute the second Query.

    mysqli_close($conn); // Close the database connection.
  
?>

