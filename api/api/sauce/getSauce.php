<?php
  require_once("../../inc/db.php");

  mt_srand(date("Ymd"));

  $sql = "SELECT `Name`, `Image`, `Description` FROM sauces";
  $result = $con->query($sql);
  $data = [];
  if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $data[] = $row;
    }
  }

  print_r(json_encode($data[mt_rand(0, $result->num_rows-1)]));
?>
