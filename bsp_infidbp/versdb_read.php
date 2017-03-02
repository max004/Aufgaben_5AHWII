<html>
<body>
<?php

$servername = "localhost";
$username = "root";
$password = "";
$database = "versicherungsdb";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) { // -> ist wie der . bei java (z.B.: this.name)
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully to database: " . $database . "<br/><br/>";
 
//execute a DB query
$sql = "SELECT * FROM kunde";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "<b>SvNr: </b>" . $row["SvNr"] . "<br/>" . " <b>Geburtsdatum: </b>" . $row["Geburtsdatum"]. "<br/>" . " <b>Vorname: </b>" . $row["Vorname"]. "<br/>" . " <b>Nachname: </b>" . $row["Nachname"] . "<br>" . "==========================" . "<br/>";
    }
} 
$conn->close();

?>
</body>
</html>