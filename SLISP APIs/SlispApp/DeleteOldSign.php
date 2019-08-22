    <?php
                  
include_once("login_api.php");

//Initiallise the unique sign to be deleted by its Sign_Id
 $Sign_Id= $_POST['Sign_Id']; 

$query=mysqli_query($conn, "DELETE FROM old_sign WHERE Sign_Id = '$Sign_Id' ");// Open database connection and Execute query.


mysqli_close($conn); //Close database connection.

    
    ?> 