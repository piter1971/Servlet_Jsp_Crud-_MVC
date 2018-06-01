<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.tablaReg {
	border: 3px solid black;
	position: absolute;
	float: left;
	margin-left: 400px;
	margin-top: 160px
}

.fondoProyecto {
	background-color: #D6D6FF;
}

.cabecera {
	background-color: #808080;
}

.filas {
	text-align: center;
	background-color: #BEBEBE;
}

.titulo {
	align-content: center;
}

#contenedorCabecera {
	position: absolute;
	margin-left: 0px;
	width: 100%;
	height: 150px;
	background-color: red;
}

.btnLista {
	position: absolute;
	margin-left: 100px;
	margin-top: 50px;
}

.titulo {
	position: absolute;
	margin-left: 300px;
	margin-top: 0px;
}
</style>
</head>
<body class="fondoProyecto">
	<form name="porFecha" method="get" action="ControladorProductos">
		<div id="contenedorCabecera">
			<div class="btnLista">
				<input type="submit" name="instruccionn" value="Ir a lista completa">
			</div>
			<div class="titulo">
				<h1>
					Mostrando todo los articulos con fecha entre :
					<%=request.getAttribute("f1")%>
					y
					<%=request.getAttribute("f2")%></h1>
			</div>

		</div>

		<table class="tablaReg">
			<tr>
				<th class="cabecera">Codigo articulo</th>
				<th class="cabecera">Seccion</th>
				<th class="cabecera">Nombre arcticulo</th>
				<th class="cabecera">Precio</th>
				<th class="cabecera">Fecha</th>
				<th class="cabecera">Importado</th>
				<th class="cabecera">Pais de origen</th>
			</tr>
			<c:forEach var="tempProd" items="${recibeListaFecha}">

				<tr>
					<td class="filas">${tempProd.cArt}</td>
					<td class="filas">${tempProd.seccionArt}</td>
					<td class="filas">${tempProd.nomArt}</td>
					<td class="filas">${tempProd. precioArt}</td>
					<td class="filas">${tempProd.fechaArt}</td>
					<td class="filas">${tempProd.importArt}</td>
					<td class="filas">${tempProd.paisArt}</td>
				</tr>
			</c:forEach>
		</table>
	</form>

</body>

</html>