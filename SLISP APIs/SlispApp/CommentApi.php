
<?PHP
include_once("login_api.php");

  //Initializing Comment Values to Capture. 
    $Content = $_POST['Content'];
    $Post_Id = $_POST['Post_Id'];
    $User_id = $_POST['User_id'];



   date_default_timezone_set('Africa/Nairobi'); //Choosing time Zone.
   $Time = date("Y-m-d H:i:s A"); //Initializing automatic Comment Time in Date-Time format.
    
    $sql = "INSERT INTO comments (Content,CommentTime,Post_Id,User_id) VALUES ('$Content','$Time','$Post_Id','$User_id')"; //Inserting data in the Comments table
    
   $result = mysqli_query($conn,$sql); //Open The Database Connection and execute the query

    mysqli_close($conn); // Close the database connection.
  
?>

