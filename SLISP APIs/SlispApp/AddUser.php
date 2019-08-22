
<?PHP
include_once("login_api.php"); 

//Checking whether there is data in the input fields.

if(isset($_POST['Username']) && !empty(isset($_POST['Username'])) 
&& isset($_POST['Password']) && !empty(isset($_POST['Password']))){ 
  
  //Intializing the Admin data variables to be captured

  $username = $_POST['Username']; 
  $phone_number= $_POST['Phone_number']; 
  $password = $_POST['Password']; 
  $Role = "Admin";

  $sql = "INSERT INTO users VALUES (NULL, '$username', '$phone_number', '$password','$Role')"; //Capture the new  Admin data into the users table.

  if ($conn->query($sql) === TRUE) { // Open database connection and execute query.

    $last_id = mysqli_insert_id($conn); //Check for the last Insert Id to ensure that data was inserted.
    echo "$last_id"; // And Output results.

  } else { 
    echo "ErrorInsert"; // Give a message if the insertaion failed.
    
    echo "Error: " . $sql . "<br>" . $conn->error; // Give a message if the insertaion failed.
    
  }
}
?>