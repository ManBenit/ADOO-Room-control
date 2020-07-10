<?php
	include "./Funciones_BDD.php";

	if($_SERVER["REQUEST_METHOD"]=="GET"){
		$funcbdd= new Funciones_BDD();
		$tablas= ["TempHum", "LED", "SensorPIR", "SensorHumo", "SensorSonido", "Ventilador"];
		$cadenaEnvio="";
		
		for($t=0; $t<sizeof($tablas); $t++){
			//Obtención del nombre de las columnas
			$cols= $funcbdd->obtenerNomColumnas($tablas[$t]);
			for($i=0; $i<sizeof($cols); $i++)
				$cadenaEnvio.= $cols[$i]["COLUMN_NAME"]."#";
			$cadenaEnvio= borrarSobrante($cadenaEnvio);

			$cadenaEnvio.="*";//Columnas

			//Obtención de los valores de cada columna
			$valores= $funcbdd->select_columnaDeTabla("*", $tablas[$t]);
			for($i=0; $i<sizeof($valores); $i++){
				for($o=0; $o<sizeof($cols); $o++)
					$cadenaEnvio.= $valores[$i][$cols[$o]["COLUMN_NAME"]]."#";
				$cadenaEnvio= borrarSobrante($cadenaEnvio);
				$cadenaEnvio.= "/";
			}
			$cadenaEnvio= borrarSobrante($cadenaEnvio);
			$cadenaEnvio.= "@";
		}
		$cadenaEnvio= borrarSobrante($cadenaEnvio);

		print($cadenaEnvio);
	}

	//Esta función eliminará el caracter sobrante en la última posición de la cadena
	function borrarSobrante($cadena){
		$charArray= str_split($cadena);
		$charArray[sizeof($charArray)-1]= null;
		return implode($charArray);
	}

?>