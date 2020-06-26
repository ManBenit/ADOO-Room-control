<?php
	include "./Funciones_BDD.php";

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		//Recepción de información por método GET
		$ids= $_GET["ids"];
		$usuarios= explode("-", $ids);

		for($i=0; $i<sizeof($usuarios); $i++){
			$bdnCorrecto2= $funcbdd->delete_Generico("Controla", "IdUsuario", $usuarios[$i]);
			$bdnCorrecto= $funcbdd->eliminarUsuario($usuarios[$i]);	
		}

		if($bdnCorrecto && $bdnCorrecto2)
			print("1");
	}

?>