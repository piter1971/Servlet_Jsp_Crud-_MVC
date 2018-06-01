<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar todo los productos</title>

<style type="text/css">
.filas {
	text-align: center;
	background-color: #BEBEBE;
}
.cabecera{
background-color: #808080;
}

.tablaReg {
	border: 3px solid black;
	position: absolute;
	float: left;
	margin-left: 220px;
	margin-top: 160px
}

.fondoProyecto {
	background-color: #D6D6FF;
}

#btnInsert {
	padding-top: 150px;
	padding-left: 20px;
}

#contenedorLat {
	position: absolute;
	margin-top: 150px;
	margin-left: 0px;
	height: 500px;
	width: 150px;
	background-color: red;
	float: left;
}

#contenedorCabecera {
	position: absolute;
	margin-left: 0px;
	width: 100%;
	height: 150px;
	background-color: red;
}



.Btnfechas {
    position:absolute;
	margin-top: 50px;
	margin-left: 20px;
}

.fechas {
	Position: absolute;
	padding-top: 30px;
	margin-left: 130px;
}

.BtnPrecios {
    position:absolute;
	margin-top: 50px;
	margin-left: 300px;
}

.Precios {
	Position: absolute;
	padding-top: 30px;
	margin-left: 420px;
}

.BtnSeccion{
	Position: absolute;
	padding-top: 50px;
	margin-left: 600px;
}

.BtnPais{
	Position: absolute;
	padding-top: 50px;
	margin-left: 870px;
}

.BtnImport{
	Position: absolute;
	padding-top: 50px;
	margin-left: 1100px;
}

</style>
</head>


<body class="fondoProyecto">

	<form name="form1" method="get" action="ControladorProductos">


		<div id="contenedorCabecera">

			<div class="Btnfechas">
				<input type="submit" name="instruccion" value="verPorFechas" />
			</div>
			<div class="fechas">
				<input type="date" name="fecha1" value=""  /> <br> hasta <br>
				<input type="date" name="fecha2" value=""  />
				<br>
				${errorFechas }<br>
			</div>			
			
			<div class="BtnPrecios">
				<input type="submit" name="instruccion" value="verPorPrecios" />
			</div>
			<div class="Precios">
				<input type="text" name="p1" value=" " />
				 <br> hasta <br>
				<input type="text" name="p2" value=" " />
				<br>
				${errorPrecio}<br>
			</div>
			
			<div class="BtnSeccion">
				<input type="submit" name="instruccion" value="verPorSeccion" />
				&nbsp
				<select name="Seccion">
					<c:forEach var="tempListaSecc" items="${listaSecc}">				
						<option value="${tempListaSecc}">${tempListaSecc}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="BtnPais">
				<input type="submit" name="instruccion" value="verPorPais" />
				&nbsp
				<select name="pais">
					<c:forEach var="tempListaPais" items="${RecibeListaPais}">
						<option value="${tempListaPais}">${tempListaPais}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="BtnImport">
				<input type="submit" name="instruccion" value="verPorImportado" />
				&nbsp
				<select name="importado">
					<option value="Si">Si</option>
					<option value="No">No</option>
				</select>
			</div>



		</div>

		<div id="contenedorLat">
			<div id="btnInsert">
				<input type="button" value="Insertar registro"
					onclick="window.location.href='InsertarProd.jsp'" />
			</div>
		</div>

		<table class="tablaReg">
			<tr>
				<th class="cabecera">Eliminar Producto</th>
				<th class="cabecera">Actualizar Producto</th>
				<th class="cabecera">Codigo articulo</th>
				<th class="cabecera">Seccion</th>
				<th class="cabecera">Nombre arcticulo</th>
				<th class="cabecera">Precio</th>
				<th class="cabecera">Fecha</th>
				<th class="cabecera">Importado</th>
				<th class="cabecera">Pais de origen</th>
			</tr>
			<c:forEach var="tempProd" items="${LISTAPRODUCTOS }">

				<!--Link para cada producto con su campo clave -->
				<!--ControladorProductos se refiere a ControladorProducto.java -->
				<c:url var="linkTemp" value="ControladorProductos">
					<!--<input type="hidden" name="instruccion" value="InsertarBD">de InsertarProd.jsp-->
					<!-- num 1 nos va a permitir que cuando sea llamado de ControladorProductos.java
		           linea: "String elComando = request.getParameter("instruccion");"
		           segun su valor "value"  llamara un metodo o otro" con el switch -->
					<!--1-->
					<c:param name="instruccion" value="ActualizarBDListaProd"></c:param>
					<!--num 2 nos permite inviar junto al link de cada linea de la tabla el codArt del articulo para actualizar-->
					<!--2-->
					<c:param name="CodArt" value="${tempProd.cArt}"></c:param>

				</c:url>

				<c:url var="linkEliminar" value="ControladorProductos">
					<c:param name="instruccion" value="Eliminar"></c:param>
					<c:param name="CodArt" value="${tempProd.cArt}"></c:param>
				</c:url>
				<tr>
					<!-- LinkTemp nombre de la variable del url -->
					<td class="filas"><a href="${linkEliminar}">Eliminar</a></td>
					<td class="filas"><a href="${linkTemp}">Actualizar</a></td>
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