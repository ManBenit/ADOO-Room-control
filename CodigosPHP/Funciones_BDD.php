<?php
	include "./Conexion.php";

	class Funciones_BDD{
		
		///////////////////SELECT FROM
		public function obtenerUsuarios(){
			$query= "SELECT * FROM seleccionarUsuario";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function obtenerIntensidadReciente(){
			$query= "SELECT IntenCaracter FROM LED WHERE IdLed=(SELECT MAX(IdLed) FROM LED)";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function obtenerEdoRecVentilador(){
			$query= "SELECT Usuario FROM Ventilador WHERE IdVent=(SELECT MAX(IdVent) FROM Ventilador)";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function obtenerEstadoActual(){
			$query= "SELECT * FROM estadoActual";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function select_ColumnaDeTabla($columna, $tabla){
			$query= "SELECT $columna FROM $tabla";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}





		
		public function select_ColumnaDeTablaEspecificado($columna, $tabla, $colReferencia, $valorReferencia){
			$query= "SELECT $columna FROM $tabla WHERE $colReferencia='$valorReferencia'";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}
		
		public function select_ColumnaDeTablaConEspecificacionMAX($columna, $tabla, $colReferencia, $colValorMaximo, $tablaDeColValorMaximo){
			$query= "SELECT $columna FROM $tabla WHERE $colReferencia=(SELECT MAX($colValorMaximo) FROM $tablaDeColValorMaximo)";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}
		
		//****
		public function select_ColumnaDeTablaConDobleEspecificacion($columna, $tabla, $colReferencia1, $valorReferencia1, $colReferencia2, $valorReferencia2){
			$query= "SELECT $columna FROM $tabla WHERE $colReferencia1='$valorReferencia1' AND $colReferencia2='$valorReferencia2'";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}
		//****

		public function select_ColumnaDeTablaOrdenado($columna, $tabla, $refOrden, $tipoOrden){ //$tipoOrden: ASC o DESC
			$query= "SELECT $columna FROM $tabla ORDER BY $refOrden $tipoOrden";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function select_ColumnaDeTablaEspecificadoOrdenado($columna, $tabla, $colReferencia, $valorReferencia, $refOrden, $tipoOrden){ //$tipoOrden: ASC o DESC
			$query= "SELECT $columna FROM $tabla WHERE $colReferencia='$valorReferencia' ORDER BY $refOrden $tipoOrden";
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}
		
		/*public function select_QueryVariable($query){
			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute();				
				return $comando->fetchAll(PDO::FETCH_ASSOC);
			}catch(PDOException $ex){
				return -1;
			}
		}*/
		
		//////////////////////////////




		///////////////////INSERT INTO
		public function nuevoUsuario($idUsuario, $nombre, $apaterno, $amaterno, $contra, $fechaRegistro, $admin){
			$query= "INSERT INTO Usuario VALUES('$nombre', '$apaterno', '$amaterno', '$contra', '$idUsuario', '$fechaRegistro', '$admin')";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($idUsuario, $nombre, $apaterno, $amaterno, $contra, $fechaRegistro, $admin));
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function insertarLuz($id, $intensidad, $caracter, $fecha, $hora){
			if($caracter=='-')
				$query= "INSERT INTO LED VALUES('$id', null, '$caracter', '$fecha', '$hora', '1')";
			else
				$query= "INSERT INTO LED VALUES('$id', '$intensidad', '$caracter', '$fecha', '$hora', '1')";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($id, $intensidad, $caracter, $fecha, $hora));
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function insertarSenalVent($id, $senal, $fecha, $hora){
			$ultimasSenales= $this->select_ColumnaDeTablaConEspecificacionMAX("Temperatura, Humedad, Humo", "Ventilador", "IdVent", "IdVent", "Ventilador");
			$query= "INSERT INTO Ventilador VALUES(
				'$id',
				'$ultimasSenales[0][0]',
				'$ultimasSenales[0][1]',
				'$ultimasSenales[0][3]',
				'$senal',
				'$fecha',
				'$hora',
				'1'
				)";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($senal));
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function insert_Generico($tabla, $valoresConFormatoSQL){
			$query= "INSERT INTO $tabla VALUES $valoresConFormatoSQL"; //Se debe poner los valores con paréntesis y comillas desde donde se llame esta función
			//$query= "INSERT INTO lugares VALUES(null, '11', 'elpito')";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($tabla, $valoresConFormatoSQL));
			}catch(PDOException $ex){
				return -1;
			}
		}

		//////////////////////////////


		
		
		///////////////////DELETE FROM
		public function eliminarUsuario($idUsuario){
			$query= "DELETE FROM Usuario WHERE IdUsuario='$idUsuario'";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($idUsuario));
			}catch(PDOException $ex){
				return -1;
			}
		}

		public function delete_Generico($tabla, $referencia, $valor){
			$query= "DELETE FROM $tabla WHERE $referencia='$valor'";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($idUsuario));
			}catch(PDOException $ex){
				return -1;
			}
		}
		//////////////////////////////


		///////////////////UPDATE
		public function enviarSenalVent($senal){
			$query= "UPDATE Ventilador SET Usuario='$senal' WHERE IdVent=(SELECT MAX(IdVent) FROM Ventilador)";
			try{
				$comando= Conexion::getDB()->prepare($query);
				return $comando->execute(array($senal));
			}catch(PDOException $ex){
				return -1;
			}
		}





		public function update_informacionDeOptometrista($tellocal, $telmovil, $direccion, $fechanac, $correo, $idOptometrista){

			$query= "UPDATE Optometrista SET tellocal='$tellocal', telmovil='$telmovil', direccion='$direccion', fechanac='$fechanac', correo='$correo' WHERE idOptometrista='$idOptometrista'";

			try{
				$comando= Conexion::getDB()->prepare($query);
				$comando->execute(array($tellocal, $telmovil, $direccion, $fechanac, $correo, $idOptometrista));
				return $comando->rowCount();
			}catch(PDOException $ex){
				return -1;
			}
		}
		//////////////////////////////
		
		
	}//clase Funciones
?>
