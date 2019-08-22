
<?PHP
include_once("login_api.php");

    // Intiallizing the the Sign data to be captured.

     $English_Name= $_POST['English_Name']; 
     $Luganda_Name= $_POST['Luganda_Name']; 
     $Description = $_POST['Description']; 
     $image = $_POST['Sign'];
     $User_id = $_POST['User_id']; 
  
    $id =$English_Name; // Intiallizing the Sign Image with and English Name
  
    $path = "uploads/$id.jpeg"; //Intiallization the path for the sign Image.
    
    $actualpath =$path; // Re-intillizing the Sign Image with another value.
    
    $sql = "INSERT INTO new_sign (English_Name,Luganda_Name,Sign,Description,User_id) VALUES ('$English_Name','$Luganda_Name','$actualpath','$Description',$User_id)"; // Capture the sign data to the new_sign table.
    
    
    if(mysqli_query($conn,$sql)){ //Open the database connection and execute query.

     file_put_contents($path,base64_decode($image)); //Convert the image data into into a string.
   
    }
    
    mysqli_close($conn); // Then close the database connection.
  
?>

