<html>
<body>
<?php

$servername = "localhost";
$username = "root";
$password = "";
$database = "chatroomdb";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) { // -> ist wie der . bei java (z.B.: this.name)
    die("Connection failed: " . $conn->connect_error);
} 

$eintrag=$_POST["eintrag"];

//Insert Eintrag
if($eintrag != ""){

//execute a DB query
$sql = "INSERT INTO chat (id, eintrag) VALUES (null, '$eintrag')";
if ($conn->query($sql) === TRUE) {

} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

}

$conn->close();

?>
</body>
</html>