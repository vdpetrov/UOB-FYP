<?php
include "db.php";
$db= new DB();
$category = $_GET['category'];
$products = $db->catalog($category);
$result = $products->fetchAll(PDO::FETCH_ASSOC);
$json = json_encode($result);

echo $json;
?>