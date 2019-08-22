
<?PHP

    include_once("login_api.php");

     $SignName= $_POST['txtSignName']; // Initiallising the sign name to be retrieved.

$query=mysqli_query($conn, "SELECT * FROM old_sign WHERE English_Name LIKE '%".$SignName."%' OR Luganda_Name LIKE '%".$SignName."%'"); // Open the database connection and Retrieve all the sign data related to the entered sign name from the old_sign table.

if ($query) {// Checking whether there is any desired record.

  while ($row=mysqli_fetch_array($query)) { // Get the record(s) found.
   
    $flag[]= $row;// Store the output in an array.

    $check=$row['Sign_Id']; // Reading the table by sign Id.
  }
  print(json_encode($flag)); // Output results in JSON object format.

if($check==NULL) // Check output status
           {            
                      $r[$num_rows]="Record is not available"; //Give output if nothing is returned.
                 
             }
            else
             {

                $r[$num_rows]="success"; // If something is returned then give message "Success".
                
              } 

}
mysqli_close($conn); // Close the database connection.

?>

