<?PHP 
    include_once("login_api.php"); 

    $userdata=array(); // Initiallize array for storing User data.
    
    if( isset($_POST['txtUsername']) && isset($_POST['txtPassword']) ) { // Check whether there is input data in the text fields.

        //Intiallize user login variables.
        $username = $_POST['txtUsername'];
        $password = $_POST['txtPassword'];
  
        $query = "SELECT Username, Password, Role, User_id FROM users ". 
        " WHERE Username = '$username' AND Password = '$password'"; // Retrive user login details from users table.
        
        $result = mysqli_query($conn, $query); // Open database connection and execute query.
        
        if($result->num_rows > 0){ // Check if there is any record returned.

         	$result = mysqli_fetch_array($result); // Get the values returned.

        	$role = trim($result['Role']); //Pick User role from the returned results.

          $userdata[role]= trim($result['Role']); // Store user role in userdata array.

          $userdata[id]=trim($result['User_id']); // Store user id in userdata array.
        	
          if ($role == 'Local') { // Check if the user's role is Local.
        	
        		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){

            
               echo json_encode($userdata); // If Local, Store both Role and User_id in JSON format.
				 exit;
            	} 
        	}
        	if ($role == 'Admin') { // Check if the user's role is Admin.
        		
        		if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
              
               echo json_encode($userdata); // If Admin, Store both Role and User_id in JSON format.
				 exit;
   		    	}
          
            echo json_encode($userdata); // Give JSON output.
          	} 

          	else{ 
            	echo "Login Failed <br/>"; // If the record is not found then give a message, Login Failed.
          	} 
       	} 
   	}

?>

<html>
<head><title>Login|KosalGeek</title></head>
    <body>
        <h1>Login Example|<a href=”http://www.kosalgeek.com”>KosalGeek</a></h1>
        <form action="<?PHP $_PHP_SELF ?>" method="post">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" name="btnSubmit" value="Login"/>
        </form>
    </body>
</html>