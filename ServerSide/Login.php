<?php
include "db.php";
$db= new DB();
if(isset($_POST['loginuser']) && $_POST['loginuser']=="true"){
  if(!empty($_POST['dEmail']) && !empty($_POST['dPassword'])){
    if($db->userLogin($_POST['dEmail'], $_POST['dPassword'])){
      echo "success";
    }else{
      echo "error";
    }
  }
}else{
	echo "error";
}
?>