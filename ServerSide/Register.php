<?php
include "db.php";
$db= new db();
if(isset($_POST['registerNewUser']) && $_POST['registerNewUser']=="true"){
  if(!empty($_POST['dFname']) && !empty($_POST['dLname']) && !empty($_POST['dEmail']) && !empty($_POST['dPassword'])){
    $result=$db->registerNewUser($_POST['dFname'], $_POST['dLname'], $_POST['dEmail'], $_POST['dPassword']);
    if($result>0){
      echo "success";
    }else{
      echo "error";
    }
  }
}
?>