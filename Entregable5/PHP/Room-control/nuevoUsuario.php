<?php
	include "./Funciones_BDD.php";

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		//Recepción de información por método GET
		$idUsuario= $_GET["idUs"];
		$nombre= $_GET["nom"];
		$apaterno= $_GET["apa"];
		$amaterno= $_GET["ama"];
		$contra= $_GET["contra"];
		$fecha= $_GET["fecha"];
		$admin= $_GET["admin"];

		$verificarCantidad= $funcbdd->obtenerUsuarios();
		if(sizeof($verificarCantidad)>=5)
			print("Imposible registrar, ya existen 5 usuarios.");
		else{
			//Ejecución del query
			$bdnCorrecto= $funcbdd->nuevoUsuario($idUsuario, $nombre, $apaterno, $amaterno, $contra, $fecha, $admin);
			$bdnCorrecto2= $funcbdd->insert_Generico("Controla", "('$idUsuario', '1')"); //Va a controlar el lugar 1, porque solo hay un lugar

			//Debe regresarse un mensaje para completar la conexión, de lo contrario el query no se ejecuta correctamente
			if($bdnCorrecto && $bdnCorrecto2)
				print("1");
		}
	}

?>