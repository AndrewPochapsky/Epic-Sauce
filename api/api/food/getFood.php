<?php
  require_once("../../inc/db.php");

  $data = json_decode($_GET["data"]);

  $sql = "SELECT * FROM foods";

  if(isset($data->isVegetarian)) {
    $sql .= " WHERE isVegetarian = '$data->isVegetarian'";
  }

  if(isset($data->isSpicy)) {
    $sql .= " AND isSpicy = $data->isSpicy";
  }

  if(isset($data->time)) {
    $sql .= " AND Time = '$data->time'";
  }

  if(isset($data->cuisine)) {
    $sql .= " AND Cuisine = '$data->cuisine'";
  }

  $result = $con->query($sql);
  $rows = [];
  if ($result->num_rows > 0) {
      // output data of each row
      while($row = $result->fetch_assoc()) {
          $rows[] = $row;
          $rows[sizeof($rows)-1]["Ingredients"] = json_decode($rows[sizeof($rows)-1]["Ingredients"]);
          $rows[sizeof($rows)-1]["Directions"] = json_decode($rows[sizeof($rows)-1]["Directions"]);
      }
  }
  $out = $rows;
  $meatsearch = [];
  if(isset($data->meat)) {
    foreach($out as $i) {
      $meatarray = json_decode($i["Meats"]);
      foreach($meatarray as $meatindex) {
        if($meatindex == $data->meat) {
          $meatsearch[] = $i;
        }
      }
    }
    $out = $meatsearch;
  }

  $vegsearch = [];
  if(isset($data->vegetables)) {
    foreach($out as $i) {
      $vegarray = json_decode($i["Vegetables"]);
      foreach($vegarray as $vegindex) {
        if($vegindex == $data->vegetables) {
          $vegsearch[] = $i;
        }
      }
    }
    $out = $vegsearch;
  }

  $values = [];
  $values_final = [];
  if(isset($_GET["nextValue"])) {
    $next = $_GET["nextValue"];
    if($next != "Meats" && $next != "Vegetables") {
      foreach($out as $o) {
        $values[$o[$next]] = $o[$next];
      }
    } else {
      $otherv = [];
      foreach($out as $o) {
          $otherv[] = $o[$next];
      }
      foreach($otherv as $o) {
        $json = json_decode($o);
        foreach($json as $oo) {
          $values[$oo] = $oo;
        }
      }
    }
  }

  foreach($values as $v) {
    $values_final[] = $v;
  }

  $outputarray = [];

  if(isset($_GET["showOutput"])) {
    if($_GET["showOutput"] == "true") {
      $outputarray[] = $out;
    }
  }

  function utf8ize($d) {
    if (is_array($d)) {
        foreach ($d as $k => $v) {
            $d[$k] = utf8ize($v);
        }
    } else if (is_string ($d)) {
        return utf8_encode($d);
    }
    return $d;
}
  $outputarray[] = $values_final;

  echo json_encode(utf8ize($outputarray));
 ?>
