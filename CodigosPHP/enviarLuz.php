<?php
	include "./Funciones_BDD.php";


	if($_SERVER["REQUEST_METHOD"]=="GET"){
		date_default_timezone_set("America/Mexico_City");
		$hoy= date("Y-m-d");
		$hora= date("H:i:s");

		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		//Recepción de información por método GET
		$luz= $_GET["luz"];

		$intRec= $funcbdd->obtenerIntensidadReciente();
		$ints= $funcbdd->select_ColumnaDeTabla("IdLed", "LED");
		if($luz=='-'){
			if($intRec[0]["IntenCaracter"]!=$luz){
				$bdnCorrecto= $funcbdd->insertarLuz(consecutivo($ints, "IdLed"), $luz, '-', $hoy, $hora);
				if($bdnCorrecto)
					print("1");
			}
			else
				print("1");
		}
		else{
			if($intRec[0]["IntenCaracter"]!=caracterLuz($luz)){
				$bdnCorrecto= $funcbdd->insertarLuz(consecutivo($ints, "IdLed"), $luz, caracterLuz($luz), $hoy, $hora); //Por ahora solo está el led de Id=1
				if($bdnCorrecto)
					print("1");
			}
			else
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

	function caracterLuz($luz){
		switch($luz){
			case 0:
				$retLuz='z';
			  	break;
			
			case 10:
				$retLuz='d';
				break;
			
			case 20:
				$retLuz='v';
				break;
			
			case 30:
				$retLuz='t';
				break;
			
			case 40:
				$retLuz='c';
				break;
			
			case 50:
				$retLuz='s';
				break;
			
			case 60:
				$retLuz='x';
				break;
			
			case 70:
				$retLuz='r';
				break;
			
			case 80:
				$retLuz='o';
				break;
			
			case 90:
				$retLuz='n';
				break;
			
			case 100:
				$retLuz='a';
				break;
		
			default:
				$retLuz=$retLuz;
				break;
		}
		  
		return $retLuz;
	}

?>


