<?php
	include "./Funciones_BDD.php";
	
	if($_SERVER["REQUEST_METHOD"]=="GET"){
		//Objeto de ejecución de las funciones de base de datos
		$funcbdd= new Funciones_BDD();

		//Recepción de información por método GET
		$idUsuario= $_GET["idUs"];
		$contra= $_GET["contra"];
		

		$credenciales= $funcbdd->select_ColumnaDeTabla("IdUsuario", "Usuario");
		//Verificar si el usuario existe
		for($i=0; $i<sizeof($credenciales); $i++){
			if($credenciales[$i]["IdUsuario"]==$idUsuario){
				$usuarioExiste= true;
				break;
			}
		}
		
		if($usuarioExiste){
			$consultaContra= $funcbdd->select_ColumnaDeTablaEspecificado("Contrasena, Admin, Nombre, Apaterno, Amaterno", "Usuario", "IdUsuario", $idUsuario);
			if($consultaContra[0]["Contrasena"]==$contra)
				print($consultaContra[0]["Admin"]."@".$consultaContra[0]["Nombre"]." ".$consultaContra[0]["Apaterno"]." ".$consultaContra[0]["Amaterno"]);
			else
				print("Contraseña incorrecta");
		}
		else 
			print("El usuario no está registrado.");

	}

?>