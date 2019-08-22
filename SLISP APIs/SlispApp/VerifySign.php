    <?php
          
        include_once("login_api.php");

        $Sign_Id= $_POST['Sign_Id'];  // Intializing the unique sign to be verivified by its Sign Id.
        
        //Queries 1 and 2 Move a sign record from newsign table to old sign table.
        //Open database connection and execute the first query.
        $query1= mysqli_query($conn, "INSERT INTO old_sign (English_Name,Luganda_Name,Sign,Description) SELECT English_Name,Luganda_Name,Sign,Description FROM new_sign WHERE Sign_Id = '$Sign_Id'"); // copying data from newsign table to old signtable.

        //Open database connection and execute the second query.
        $query2=mysqli_query($conn, "DELETE FROM new_sign WHERE Sign_Id = '$Sign_Id' ");// Delete the copied sign from the newsign table.

        mysqli_close($conn); // Close the database connection.
     

    ?> 