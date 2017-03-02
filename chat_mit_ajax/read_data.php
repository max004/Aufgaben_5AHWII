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

//read
$sql = "SELECT * FROM chat ORDER BY id DESC";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "<b>id: </b>" . $row["id"] . "<br/>" . " <b>Eintrag: </b>" . $row["eintrag"]. "<br/>" . "==========================" . "<br/>";
    }	
} 

$conn->close();

?>
</body>
</html>