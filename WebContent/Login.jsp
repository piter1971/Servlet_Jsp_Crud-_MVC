<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<style type="text/css">
			.cuerpoLogin{
			width:500px;
			height: 200px;
			
			border: 3px solid black;
			position: relative;
			margin-left: 30%;
			margin-top: 50px;
			background-color: red;
			}
			
			.bodyStyle{
				background-color: gray;
			}
			
			.imag{
			align="left";
			width="50px";
			height="50px";
			}
			
			.styleTexto{
			text-align: center;
			}
	
	</style>
</head>
<body  class="bodyStyle">
	<div class="styleTexto">
		<h1 >Login</h1>
		${errorLogin}<br>
	</div>
	<form action="ControladorProductos" method="get">
	<input type="hidden" name="instruccion" value="loginBD">
	
		<table class="cuerpoLogin">
			<tr>
				<td align="center">Usuario  </td>
				<td><input type="text" name="usuario" ></td>
				<td class="imag"><img alt="" src="user.png"  > </td>
			</tr>
			<tr>
				<td align="center">contraseña  </td>
				<td><input type="password" name="contra">  </td>
				<td class="imag"><img alt="" src="pass.png" > </td>
			</tr>
			<tr>
				<td align="center"><input type="submit" name="enviarLogin" value="Login"> </td>
				<td align="center"><input type="reset" name="cancelar"  value="cancelar">  </td>
				
			</tr>		
			
		</table>	
		
	</form>
	
</body>
</html>