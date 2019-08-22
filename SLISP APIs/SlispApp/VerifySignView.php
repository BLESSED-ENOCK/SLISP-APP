    <?php
                  
include_once("login_api.php");
          
$query=mysqli_query($conn, "SELECT Sign_Id, English_Name, Luganda_Name, Sign FROM new_sign");//Open database connection and retrieval all the signs to be verified.

if ($query) { // Checking whether there is any sign record.
  while ($row=mysqli_fetch_array($query)) { // If retieve all the sign records found.
   
    $flag[]= $row; // Intiallizing the array for storing records.

    $check=$row['Sign_Id']; //Read every record by sign Id.
  }
  print(json_encode($flag)); // Out put the results in form of of JSON Object format.
if($check==NULL) //Cheking the returned status.
           {            
                      $r[$num_rows]="Record is not available"; //Intialize the Output message.
                      print(json_encode($r)); // If the desired record was not found, then give the output "Record is not available".
             }
            else
             {

                $r[$num_rows]="success"; // If there is a desired out put, then give the ouput
          
              } 
}
mysqli_close($conn); // Close the connection.
    
    ?> 