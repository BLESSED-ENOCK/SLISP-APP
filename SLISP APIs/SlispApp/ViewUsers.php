<?php
                  
include_once("login_api.php");
          
$query=mysqli_query($conn, "SELECT User_id, Username, Phone_number, Role FROM users");//Open database connection and retrieval all the User records.

if ($query) { // Checking whether there is any User record.

  while ($row=mysqli_fetch_array($query)) { // If retieve all the User records found.
   
    $flag[]= $row; // Intiallizing the array for storing records.

    $check=$row['User_id']; //Read every record by User id
  }
  print(json_encode($flag));// Out put the results in form of of JSON Object format.

if($check==NULL) //Cheking the returned status.
           {            
                      $r[$num_rows]="Record is not available"; //Intialize the Output message.
                      print(json_encode($r));   // If the desired record was not found, then give the output "Record is not available".
             }
            else
             {

                $r[$num_rows]="success"; // If there is a desired out put, then give the ouput
          
              } 
}
mysqli_close($conn); // Close the connection.
 
?> 