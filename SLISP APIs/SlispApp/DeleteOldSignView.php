    <?php
                  
include_once("login_api.php");
          
$query=mysqli_query($conn, "SELECT Sign_Id, English_Name, Luganda_Name, Sign FROM old_sign"); // Open the database connection and Retrieve all the verified sign data from the old_sign table.

if ($query) { // Checking whether there is any desired record.

  while ($row=mysqli_fetch_array($query)) { // Get the record(s) found.
   
    $flag[]= $row; // Store the output in an array.

    $check=$row['Sign_Id']; // Reading the table by sign Id.
  }
  print(json_encode($flag)); // Output results in JSON object format.

if($check==NULL) // Check output status
           {            
                      $r[$num_rows]="Record is not available"; //intiallising output message.
                      print(json_encode($r)); //Give output if nothing is returned. 
             }
            else
             {

                $r[$num_rows]="success"; // If something is returned then give message "Success".
          
              } 
}

mysqli_close($conn); // Close the database connection.

    ?> 