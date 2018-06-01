<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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
		background-color: #1E90FF;
		float: left;
	}
	
	.styleImag{
	position: relative;
	margin-left: 750px;
	margin-top: -210px;
	float: left;
	}
	
	.styleBody{
	background-color: #87CEFA;
	}
	
	.styleTitulo{
	background-color: #808080;
	}
	
	.StyleTextCabecera{
		align="center";
	}
	
	.btnLista{
	position: absolute;
	margin-top: 450px;
	margin-left: 500px
	}
	.error{
		color: red;
		align="center";
	}

</style>


</head>
<body class="styleBody">
	<div class="styleTitulo">
	<h1 align="center">Insertar Registro</h1>
	</div>
	<div class="error">
	
	<c:if test = "${errorCodArt == null}">
       ${errorCodArt}
      </c:if>
	<p> ${errorCodArt} </p>
	</div>
	<form  name="form1" method="get" action="ControladorProductos"><!-- action=ControladorProductos se refiere a ControladorProductos.java  -->
	
	
	
		<table class="styleTabla">
			<tr>
				<td>cArt</td>
				<td><label for="txtcArt"></label>
				 <input type="text"	name="txtcArt" id="txtcArt" required/></td>
			</tr>
			<tr>
				<td>seccion</td>
				<td><label for="txtSeccion"></label> <input type="text"
					name="txtSeccion" id="txtSeccion" /></td>
			</tr>
			<tr>
				<td>numArt</td>
				<td><label for="txtNomArt"></label> <input type="text"
					name="txtNomArt" id="txtnumArt" /></td>
			</tr>
			<tr>
				<td>fecha</td>
				<td><label for="txtFecha"></label> <input type="date"
					name="txtFecha" id="txtFecha" /></td>
			</tr>
			<tr>
				<td>precio</td>
				<td><label for="txtPrecio"></label> <input type="text" name="txtPrecio"/></td>
			</tr>
			<tr>
				<td>importado</td>
				<td><label for="Importado"></label> 
				<select name="txtImportado">
				<option value="Si"> Si </option>
				<option value="No"> No </option>
				</select></td>
			</tr>
			<tr>
				<td>Pais</td>
				<td><label for="txtPais"></label> <input type="text"
					name="txtPais" id="txtPais" /></td>
			</tr>
			
			
			<tr>
			<div class="btnLista">
				<input type="submit" name="instruccionn" value="Ir a lista completa">
				&nbsp;
				<input type="submit" name="instruccion" value="InsertarBD" />
				&nbsp;
				<input type="reset" name="Restablecer"  value="Restablecer" />
			    
			 </div>
			</tr>

		</table>
		
		<div class="styleImag" ><img  padi alt="" src="insert.png" ></div>
	</form>

</body>
</html>