    <?php
                  
include_once("login_api.php");

$User_id= $_POST['User_id']; //Initializing the User_id whose data is to be deleted.

$query=mysqli_query($conn, "DELETE FROM users WHERE User_id = '$User_id' "); // We delete record of the the selected User_id from users table.

mysqli_close($conn); // We close the Database Connection.

    
    ?> 