<?php
$servername = "localhost";
$username = "root";
$password = "Heya1234";
$dbname = "main";

$con = new mysqli($servername, $username, $password, $dbname);

if ($con->connect_error) {

}
?>
