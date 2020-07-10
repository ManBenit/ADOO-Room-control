<?php
	include "./Funciones_BDD.php";

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		$resultado= $funcbdd->obtenerUsuarios();

		$lista="";

		for($i=0; $i<sizeof($resultado); $i++)
			$lista= $lista.$resultado[$i]["IdUsuario"]."-".$resultado[$i]["NomCompleto"]."@";
		
		$listaToCharArray= str_split($lista);
		$listaToCharArray[sizeof($listaToCharArray)-1]= null;


		print(implode($listaToCharArray));
	}

?>