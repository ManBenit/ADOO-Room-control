<?php
	include "./Funciones_BDD.php";


	if($_SERVER["REQUEST_METHOD"]=="GET"){
		date_default_timezone_set("America/Mexico_City");
		$hoy= date("Y-m-d");
		$hora= date("H:i:s");

		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		//Recepción de información por método GET
		$vent= $_GET["vent"];

		$ventRec= $funcbdd->obtenerEdoRecVentilador();
		$sens= $funcbdd->select_ColumnaDeTabla("IdVent", "Ventilador");
		
		if($vent==$ventRec[0]["Usuario"])
			print("1");
		else{
			$bdnCorrecto= $funcbdd->insertarSenalVent(consecutivo($sens, "IdVent"), $vent, $hoy, $hora);
			if($bdnCorrecto)
				print("1");
		}
		
	}


	function consecutivo($arregloBDD, $PK){
		$consecutivo=0;
		$cualFalta=0;
		$numeroAsignado=0;
		$n=1;
		
		for($i=0; $i<sizeof($arregloBDD); $i++){
			if(($n+$i)==$arregloBDD[$i][$PK])
				$consecutivo=0;
			else{
				$consecutivo=1;
				$cualFalta= ($n+$i);
				$i= sizeof($arregloBDD)-1;
			}
		}
		
		if($consecutivo==1)
			$numeroAsignado= $cualFalta;
		else
			$numeroAsignado= ($n+$i);
		
		return $numeroAsignado;
	}

?>


