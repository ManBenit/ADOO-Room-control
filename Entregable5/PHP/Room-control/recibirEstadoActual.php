<?php
	include "./Funciones_BDD.php";

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		$resultado= $funcbdd->obtenerEstadoActual();

		$lista="";

        $listaRes= 
        $resultado[0]["Temperatura"]."@".
        $resultado[0]["Humedad"]."@".
        $resultado[0]["Luz"]."@".
        $resultado[0]["Presencia"]."@".
        $resultado[0]["Humo"]."@".
        $resultado[0]["Sonido"]."@".
        $resultado[0]["Ventilador"];
		


		print($listaRes);
	}

?>