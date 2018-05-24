<?php
class DB{
  private $dbHost="localhost";
  private $dbUsername="vdpetrov";
  private $dbPassword="Vesko1993";
  private $dbName="vdpetrov";
  public $db_con;
  public function __construct(){
	  
    if(!isset($this->db)){
      try{
        $pdo=new PDO("mysql:host=".$this->dbHost.";dbname=".$this->dbName, $this->dbUsername, $this->dbPassword);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $this->db_con=$pdo;
      }catch(PDOEXCEPTION $e){
        die("Failed to connect with mysql :".$e->getMessage());
      
      }
    }
  }
  public function userLogin($email, $password)
  {
    $stmt=$this->db_con->prepare("SELECT * FROM users WHERE dEmail=:dEmail AND dPassword=:dPassword");
	$stmt->bindParam(":dEmail", $email);
	$stmt->bindParam(":dPassword", $password);
    $stmt->execute();
    $row=$stmt->fetch(PDO::FETCH_ASSOC);
    if($stmt->rowCount()){
      return true;
    }else{
      return false;
    }
  }
  public function registerNewUser($dFname, $dLname, $dEmail, $dPassword)
  {
    $sql="INSERT into users(dFname, dLname, dEmail, dPassword) VALUES (:dFname, :dLname, :dEmail, :dPassword)";
    $stmt=$this->db_con->prepare($sql);
    $stmt->bindParam(":dFname", $dFname);
    $stmt->bindParam(":dLname", $dLname);
    $stmt->bindParam(":dEmail", $dEmail);
    $stmt->bindParam(":dPassword", $dPassword);
    $stmt->execute();
    $newId=$this->db_con->lastInsertId();
    return $newId;
  }
  
  public function catalog($category)
  {
	$query="SELECT * from `products` WHERE category = :category;";
	$stmt=$this->db_con->prepare($query, array(PDO::ATTR_CURSOR => PDO::CURSOR_SCROLL));
	$stmt->bindParam(":category", $category); 
	$stmt->execute();
	return $stmt;
  }
}
?>