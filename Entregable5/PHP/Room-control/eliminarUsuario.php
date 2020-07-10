<?php
	include "./Funciones_BDD.php";

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		//Recepción de información por método GET
		$ids= $_GET["ids"];
		$usuarios= explode("-", $ids);

		$bndFinal=true;
		for($i=0; $i<sizeof($usuarios); $i++){
			$bdnCorrecto= $funcbdd->delete_Generico("Controla", "IdUsuario", $usuarios[$i]);
			$bdnCorrecto2= $funcbdd->eliminarUsuario($usuarios[$i]);	
			if(!$bdnCorrecto || !$bdnCorrecto2){
				$bndFinal=false;
				break;
			}
		}

		if($bndFinal)
			print("1");
	}

?>