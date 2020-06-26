<?php
	class Conexion{
		//Se usará PDO porque se necesita comunicar los datos de la app al php, y para ello se requiere el PDO	
		private $_connection;

		private static $dsn= "mysql:dbname=room_control;host=localhost";
		private static $usuario= "cjmebm98sh";
		private static $contra= "cejotashuriken";

		public static function getDB(){
			try{
				$_connection= new PDO(self::$dsn, self::$usuario, self::$contra, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES latin1"));
			}catch(PDOException $ex){
				echo "Error de conexión ".$ex->getMessage();
				$_connection= null;
			}
		
			return $_connection;
		}
	}
?>
