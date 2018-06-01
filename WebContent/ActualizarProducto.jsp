<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.styleTabla{
		border-radius:9px;
		width:500px;
		height: 400px;
		border: 3px solid black;
		position: relative;
		margin-left: 400px;
		margin-top: 10px;
		background-color: graytext;
		float: left;
	}
	
	
	.styleImag{
	position: relative;
	margin-left: 750px;
	margin-top: -210px;
	float: left;
	}
	.styleBody{
	background-color: gray;
	}
	
	.styleTitulo{
	background-color: orange;
	}
	
	.StyleTextCabecera{
		align="center";
	}

</style>
</head>
<body class="styleBody">
	<div class="styleTitulo">
		<h1 align="center" >Actualizar Registro</h1>
		<h3 align="center">Codigo del articulo : <%=request.getAttribute("CodArt") %></h3>
	</div>
	
	<form  name="form1" method="get" action="ControladorProductos"><!-- action=ControladorProductos se refiere a ControladorProductos.java  -->
	
	<input type="hidden" name="instruccion" value="BDActualizado">
    <!--enviar el codigoArticulo del producto a actualizar -->
	
	<input type="hidden" name="txtcArt" value="">
	
		<table class="styleTabla" >
			
			<tr>
				<td>seccion</td>
				<td><label for="txtSeccion"></label> <input type="text"
					name="txtSeccion" id="txtSeccion" value="${productoActualizar.seccionArt}" /></td>
			</tr>
			<tr>
				<td>numArt</td>
				<td><label for="txtNomArt"></label> <input type="text"
					name="txtNomArt" id="txtnomArt" value="${productoActualizar.nomArt}"/></td>
			</tr>
			<tr>
				<td>fecha</td>
				<td><label for="txtFecha"></label> <input type="date"
					name="txtFecha" id="txtFecha" value="${productoActualizar.fechaArt}"/></td>
			</tr>
			<tr>
				<td>precio</td>
				<td><label for="txtPrecio">
				</label> <input type="text" name="txtPrecio" value="${productoActualizar.precioArt}"/></td>
			</tr>
			<tr>
				<td>importado</td>
				<td><label for="Importado"></label> 
				<select name="txtImportado" ">
				<option value="" selected>"${productoActualizar.importArt}"</option>
				<option value="Si"> Si </option>
				<option value="No"> No </option>
				</select></td>
			</tr>
			<tr>
				<td>Pais</td>
				<td><label for="txtPais"></label> <input type="text"
					name="txtPais" id="txtPais" value="${productoActualizar.paisArt}"/></td>
			</tr>
			
			
			<tr>
				<td align="center"><input type="submit" name="enviar" id="enviar" value="Enviar" /></td>
				<td align="center"><input type="reset" name="Restablecer" id="Restablecer" value="Restablecer" /></td>
			</tr>
			
			
		
		</table>
		
			<div class="styleImag" ><img  padi alt="" src="update.png" ></div>
		
</body>
</html>