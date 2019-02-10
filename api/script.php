<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

  require_once("inc/db.php");

  $file = fopen("data.csv","r");

  while(!feof($file)) {
    $data = fgetcsv($file);
    $title = addslashes($data[0]);
    $cusine = addslashes($data[1]);
    $time = addslashes($data[2]);
    $isveg = addslashes($data[3]);
    $meat = addslashes($data[4]);
    $isspicy = addslashes($data[5]);
    $veg = addslashes($data[6]);
    $img = addslashes($data[7]);
    $ingred = addslashes($data[8]);
    $direction = addslashes($data[9]);

    $sql = "INSERT INTO foods (Name, Cuisine, isVegetarian, isSpicy, Meats, Vegetables, Time, Ingredients, Directions, Image) VALUES ('$title', '$cusine', $isveg, '$isspicy', '$meat', '$veg', '$time', '$ingred', '$direction', '$img')";

    if ($con->query($sql) === TRUE) {
    } else {
      echo "$sql ";
    }
  }

  fclose($file);

 ?>
