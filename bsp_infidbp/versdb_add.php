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

$name=$_POST["name"];
$gruendungsdatum=$_POST["gruendungsdatum"];
$vorname=$_POST["vorname"];
$nachname=$_POST["nachname"];
$geburtsdatum=$_POST["geburtsdatum"];
$vid2=$_POST["vid2"];
$kid2=$_POST["kid2"];
$gegenstand=$_POST["gegenstand"];

//Insert versicherung
if($name != "" AND $gruendungsdatum != ""){

$sql = "SELECT MAX(VID) FROM versicherung";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $vid = $row["MAX(VID)"] + 1;
    }
} else {
    echo "0 results";
}

//execute a DB query
$sql = "INSERT INTO versicherung (VID, Gruendungsdatum, Name) VALUES ($vid, '$gruendungsdatum', '$name')";
if ($conn->query($sql) === TRUE) {
    echo "New insurance created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}

//insert Kunde
if($vorname != "" AND $nachname != "" AND $geburtsdatum != ""){

$sql = "SELECT MAX(SvNr) FROM kunde";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $vid = $row["MAX(SvNr)"] + 1;
    }
} else {
    echo "0 results";
}

//execute a DB query
$sql = "INSERT INTO kunde (SvNr, Geburtsdatum, Vorname, Nachname) VALUES ($vid, '$geburtsdatum', '$vorname', '$nachname')";
if ($conn->query($sql) === TRUE) {
    echo "New customer created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}

//Insert Kondition
if($gegenstand != "" AND $vid2 != "" AND $kid2 != ""){

//execute a DB query
$sql = "INSERT INTO kondition (KID, Gegenstand, VID) VALUES ($kid2, '$gegenstand', '$vid2')";
if ($conn->query($sql) === TRUE) {
    echo "New condition created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
}

$conn->close();

?>
</body>
</html>