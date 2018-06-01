package Servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ModeloProducto {
	private DataSource dataSource;
	
	public ModeloProducto(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	
	public List<Producto> getProductos() throws Exception{
		List<Producto> listaProd = new ArrayList<>();
		Connection miCon = null;
		Statement miStat=null;
		ResultSet miRes = null;
		try {
		//---------establecer conexion----------
		miCon=dataSource.getConnection();
		
		//-----------crear sentencia sql--------
		String instruccionQueri = "SELECT * FROM productos";		
		miStat=miCon.createStatement();
		
		//----------ejecutar sql---------------
		miRes=miStat.executeQuery(instruccionQueri);
		
		 //----------recorrer el resultset para obtener todo los datos-------
		while (miRes.next()) {
			String codArt = miRes.getString(1);
			String secArt = miRes.getString(2);
			String nomArt = miRes.getString(3);
			double precArt = miRes.getDouble(4);
			Date fechaArt = miRes.getDate(5);
			String importArt = miRes.getString(6);
			String paisArt = miRes.getString(7);
			
			Producto temProd = new Producto(codArt, secArt, nomArt,precArt, fechaArt, importArt, paisArt);
			listaProd.add(temProd);			
		}
		
		}finally {
			miStat.close();
			miCon.close();
		} 
		return listaProd;
	}
	
	public List<String> obtenerProdSeccion() throws Exception{
		List<String> listaProd = new ArrayList<>();
		Connection miCon = null;
		Statement miStat=null;
		ResultSet miRes = null;
		try {
		//---------establecer conexion----------
		miCon=dataSource.getConnection();
		
		//-----------crear sentencia sql--------
		String instruccionQueri = "SELECT DISTINCT Seccion FROM productos";		
		miStat=miCon.createStatement();
		
		//----------ejecutar sql---------------
		miRes=miStat.executeQuery(instruccionQueri);
		
		 //----------recorrer el resultset para obtener todo los datos-------
		while (miRes.next()) {
			
			String secArt = miRes.getString(1);
			
			
			//Producto temProd = new Producto( secArt);
			
			listaProd.add(secArt);	
		
		}
		
		}finally {
			miStat.close();
			miCon.close();
		} 
		return listaProd;
	}

	public void agregarNuevoProd(Producto prod) throws Exception{
		
		// Obtener instruccion
		Connection miCon = null;
		PreparedStatement miPrepStat = null;
		
		try {
			miCon = dataSource.getConnection();

			// Crear instruccion SQL y crear la consulta preparada
			String SqlInsert = "INSERT INTO productos(CodigoArticulo, Seccion, NombreArticulo,Precio, Fecha, Importado, Pais) VALUES (?,?,?,?,?,?,?)";

			// establecer parametros del producto
			miPrepStat = miCon.prepareStatement(SqlInsert);
			miPrepStat.setString(1, prod.getcArt());
			miPrepStat.setString(2, prod.getSeccionArt());
			miPrepStat.setString(3, prod.getNomArt());
			miPrepStat.setDouble(4, prod.getPrecioArt());

			/*Es necesario convertir la fecha que nos da prod.getFecha ya que es del tipo java.util y hay que pasar lo a java.sql*/
			
			java.util.Date utilDate = prod.getFechaArt();
			java.sql.Date fechaConvertida=null;
			if (utilDate !=null) {
				fechaConvertida = new java.sql.Date(utilDate.getTime());
			}
			
			miPrepStat.setDate(5, fechaConvertida);
			miPrepStat.setString(6, prod.getImportArt());
			miPrepStat.setString(7, prod.getPaisArt());

			// ejecutar la instruccion sql			
			miPrepStat.execute();
			
			} catch (Exception e) {
			System.out.println("Eror en la inserccion");
			e.printStackTrace();
		}finally {
			miPrepStat.close();
			miCon.close();
		}
	}

	public Producto getCodArtProducto(String codArticulo) throws Exception {
		
		Producto miProd = null;
		Connection miCon = null;
		PreparedStatement miPrepStat= null;
		ResultSet miRes =null;
		String cArticulo = codArticulo;
		//ESTABLECER LA CONEXION CON LA BASE DE DATOS
		try {
			miCon=dataSource.getConnection();
		
		
		//CREAR LA INSTRUCCION PARA QUE BUSQUE EL ARTICULO
		String Instruccion_sql= "SELECT * FROM productos WHERE CodigoArticulo = ?";
		
		//CREAR CONSULTA PREPARADA
		miPrepStat=miCon.prepareStatement(Instruccion_sql);
		
		//ESTABLECER LO PARAMETROS
		miPrepStat.setString(1, cArticulo);
		
		//EJECUTAR LA CONSULTA
		miRes=miPrepStat.executeQuery();
		
		//OBTENER LOS DATOS DE LA RESPUESTA
		if (miRes.next()) {
			String CodArt = miRes.getString(1);
			String secArt = miRes.getString(2);
			String nomArt = miRes.getString(3);
			double precArt = miRes.getDouble(4);
			Date fechaArt = miRes.getDate(5);
			String importArt = miRes.getString(6);
			String paisArt = miRes.getString(7);
			
			miProd = new Producto( CodArt, secArt, nomArt,precArt, fechaArt, importArt, paisArt);
			
		}else {
			throw new Exception("No se ha encontrado el codigo "+ cArticulo );
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			miPrepStat.close();
			miCon.close();
		}
		return miProd;
	}

	public  void actualizarProd(Producto prodActualizado) throws Exception{
		Connection miCon = null;
		PreparedStatement miPrepStat= null;
		ResultSet miRes =null;
		try {
		//Establecer Conexion 
		miCon = dataSource.getConnection();
		//miCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/curso_sql", "root", "");;
		// Crear instruccion SQL y crear la consulta preparada
		String SqlUpdate = "UPDATE productos SET Seccion=?, NombreArticulo=?, Precio=? , Fecha=? ,Importado=?,Pais=? WHERE CodigoArticulo=? ";
		
		//Crear la consulat preparada 
		miPrepStat=miCon.prepareStatement(SqlUpdate);
		
		// establecer los parametros 
		miPrepStat.setString(1,prodActualizado.getSeccionArt() );
		miPrepStat.setString(2,prodActualizado.getNomArt() );
		miPrepStat.setDouble(3,prodActualizado.getPrecioArt() );
		java.util.Date utilDate = prodActualizado.getFechaArt();
		java.sql.Date fechaConvertida = new java.sql.Date(utilDate.getTime());
		miPrepStat.setDate(4, fechaConvertida);
		miPrepStat.setString(5,prodActualizado.getImportArt() );
		miPrepStat.setString(6,prodActualizado.getPaisArt() );
		miPrepStat.setString(7,prodActualizado.getcArt() );
		//System.out.println("Atributos actualizados en ActualizarProd "+ prodActualizado.toString());
		//ejecutar la intruccion sql
		miPrepStat.execute();
		System.out.println("Actualizado con exito");
		}finally {
			miPrepStat.close();
			miCon.close();
		}
	}

	public void eliminarProducto(String codArticulo) throws SQLException {
		Connection miCon = null;
		PreparedStatement miPrepStat= null;
		ResultSet miRes =null;
		try {
		miCon = dataSource.getConnection();
		//miCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/curso_sql", "root", "");;
		// Crear instruccion SQL y crear la consulta preparada
		String SqlDelete = "DELETE  FROM productos WHERE CodigoArticulo=? ";
		
		//Crear la consulat preparada 
		miPrepStat=miCon.prepareStatement(SqlDelete);        
		
		// establecer los parametros 
		miPrepStat.setString(1,codArticulo );
		
		
		miPrepStat.execute();
		System.out.println("Eliminado  con exito");
		}finally {
			miPrepStat.close();
			miCon.close();
		}
		
	}

	public boolean obtenerUsuarioContra(String user, String pass) throws SQLException {
		
		Connection miCon =null;
		boolean nom = false;
		boolean con = false;
		boolean acceso = false;
		PreparedStatement miPrepStat=null;
		ResultSet miRes = null;
		try {
		miCon=dataSource.getConnection();
		String selectUsuario = "SELECT nombre FROM usuario WHERE nombre = ? ";
		miPrepStat=miCon.prepareStatement(selectUsuario);
		
		miPrepStat.setString(1, user);
		miRes=miPrepStat.executeQuery();
		
		while (miRes.next()) {
			
			String nombre = miRes.getString(1);
			if (nombre.equals(user)) {
				nom = true;
			}
		}
		
		String selectContra = "SELECT contra FROM usuario WHERE nombre = ? ";
		miPrepStat=miCon.prepareStatement(selectContra);
		
		miPrepStat.setString(1, user);
		miRes=miPrepStat.executeQuery();
		
		while (miRes.next()) {
			
			String contra = miRes.getString(1);
			if (contra.equals(pass)) {
				con = true;
			}
		}
		
		if (con && nom) {
			acceso=true;
			
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			miCon.close();
			miPrepStat.close();
			miRes.close();
		}
		return acceso;		
	}

	public List<Producto> verFechas(String f1, String f2) throws Exception {
		List< Producto> listaFecha=new ArrayList<>();
		Connection miCon = null;
		PreparedStatement miPrepStat=null;
		ResultSet miRes = null;
		
		try {
		//---------establecer conexion----------
		miCon=dataSource.getConnection();
		
		//-----------crear sentencia sql--------
		String instruccionQueri = "select * from productos WHERE Fecha BETWEEN ? and ?";		
		miPrepStat=miCon.prepareStatement(instruccionQueri);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date cDate = df.parse(f1);
		java.sql.Date fechaConvertida = new java.sql.Date(cDate.getTime());
		miPrepStat.setDate(1, fechaConvertida);
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		Date cDate1 = df.parse(f2);
		java.sql.Date fechaConvertida1 = new java.sql.Date(cDate1.getTime());
		miPrepStat.setDate(2, fechaConvertida1);
		
		
		miRes= miPrepStat.executeQuery();
		
		while (miRes.next()) {
			String CodArt = miRes.getString(1);
			String secArt = miRes.getString(2);
			String nomArt = miRes.getString(3);
			double precArt = miRes.getDouble(4);
			Date fechaArt = miRes.getDate(5);
			String importArt = miRes.getString(6);
			String paisArt = miRes.getString(7);
			
			Producto  miProd = new Producto( CodArt, secArt, nomArt,precArt, fechaArt, importArt, paisArt);
			
			listaFecha.add(miProd);
			
		}
		
		}catch (Exception e) {
			System.out.println("Error en fechas");
			e.printStackTrace();
		}finally {
			miCon.close();
			miPrepStat.close();
			miRes.close();
		}
		return listaFecha;
		
	}
	 
	 public List<String>  obtenerProdPais() throws Exception{
			List<String> listaPais = new ArrayList<>();
			Connection miCon = null;
			Statement miStat=null;
			ResultSet miRes = null;
			try {
			//---------establecer conexion----------
			miCon=dataSource.getConnection();
			
			//-----------crear sentencia sql--------
			String instruccionQueri = "SELECT DISTINCT Pais FROM productos";		
			miStat=miCon.createStatement();
			
			//----------ejecutar sql---------------
			miRes=miStat.executeQuery(instruccionQueri);
			
			 //----------recorrer el resultset para obtener todo los datos-------
			while (miRes.next()) {
				
				String secArt = miRes.getString(1);
				
				
				//Producto temProd = new Producto( secArt);
				
				listaPais.add(secArt);	
			
			}
			
			}finally {
				miStat.close();
				miCon.close();
			} 
			return listaPais;
		}

	public List<Producto> verListaPrecio(String p1, String p2) throws Exception {
		Connection miCon =null;
		PreparedStatement miPrepStat=null;
		ResultSet miRes=null;
		Producto miProdPrecios=null;
		List <Producto> listaProdPrecio = new ArrayList<>();
		
		Double precio1 = Double.parseDouble(p1);
		Double precio2 = Double.parseDouble(p2);
		try {
			miCon = dataSource.getConnection();
			
			String selectPrecios = "SELECT * FROM productos WHERE Precio>? and Precio<?";
			miPrepStat=miCon.prepareStatement(selectPrecios);
			
			miPrepStat.setDouble(1, precio1);
			miPrepStat.setDouble(2, precio2);
			
			
			
			miRes=miPrepStat.executeQuery();
			
			while (miRes.next()) {
			String codArt=	miRes.getString(1);
			String 	seccArt =miRes.getString(2);
			String nomArt=	miRes.getString(3);
			Double precioArt=	miRes.getDouble(4);
			Date fechaArt=	miRes.getDate(5);
			String importArt =	miRes.getString(6);
			String paisArt=	miRes.getString(7);
				
				miProdPrecios= new Producto(codArt,seccArt,nomArt,precioArt,fechaArt,importArt,paisArt);
				
				listaProdPrecio.add(miProdPrecios);
			}
			
		} catch (Exception e) {
			System.out.println("Error en listar por precios");
			e.printStackTrace();
			System.out.println("*****************");
			e.getLocalizedMessage();
		}finally {
			miCon.close();
			miPrepStat.close();
			miRes.close();
		}
		
		
		return listaProdPrecio;
	}

	public List<Producto> verPorListaPorSeccion(String seccion) throws Exception {
		Connection miCon=null;
		PreparedStatement miPrepStat=null;
		ResultSet miRes=null;
		List<Producto> listaPorSeccion = new ArrayList<>();
		try {
			miCon=dataSource.getConnection();
			
			String selectSeccion = "SELECT * FROM productos where Seccion = ? ";
			miPrepStat=miCon.prepareStatement(selectSeccion);
			
			miPrepStat.setString(1, seccion);			
			miRes=miPrepStat.executeQuery();
			
			while (miRes.next()) {
				String codArt= miRes.getString(1);
				String seccArt= miRes.getString(2);
				String nomArt= miRes.getString(3);
				Double precioArt= miRes.getDouble(4);
				Date fechaArt= miRes.getDate(5);
				String importArt= miRes.getString(6);
				String paisArt= miRes.getString(7);
				
				Producto miProdSecciones=new Producto(codArt, seccArt, nomArt, precioArt, fechaArt, importArt,paisArt);
				listaPorSeccion.add(miProdSecciones);
				
			}
			
		}catch (Exception e) {
			System.out.println("ERROR en seccion");
			e.printStackTrace();
		}finally {
			miCon.close();
			miPrepStat.close();
			miRes.close();
		}
		return listaPorSeccion;
	}

	public List<Producto> listaProdPais(String pais) throws Exception {
		List<Producto> listaProdPais=new ArrayList<>();
		Connection miCon=null;
		PreparedStatement miPrepStat=null;
		ResultSet miRes = null;
		try {
		miCon=dataSource.getConnection();
		
		String selectPosPais = "SELECT * FROM productos WHERE Pais = ? ";
		miPrepStat=miCon.prepareStatement(selectPosPais);
		
		miPrepStat.setString(1, pais);
		miRes = miPrepStat.executeQuery();
		
		while (miRes.next()) {
			String codArt = miRes.getString(1);
			String seccArt = miRes.getString(2);
			String nomArt = miRes.getString(3);
			Double precioArt = miRes.getDouble(4);
			Date fechaArt = miRes.getDate(5);
			String importArt = miRes.getString(6);
			String paisArt = miRes.getString(7);
			
			Producto miProdPais = new Producto(codArt, seccArt, nomArt, precioArt,fechaArt, importArt,paisArt);
			
			listaProdPais.add(miProdPais);
			
		}
		}catch (Exception e) {
			System.out.println("ERROR select por pais");
			e.printStackTrace();
		}finally {
			miCon.close();
			miPrepStat.close();
			miRes.close();
		}
		return listaProdPais;
	}

	public List<Producto> listarPorImportado(String importado) throws Exception {
		List<Producto> listaProdImportado = new ArrayList<>();
		
		Connection miCon=null;
		PreparedStatement miPrepstat = null;
		ResultSet miRes = null;
		try {
		miCon=dataSource.getConnection();
		
		String selectPorImport = "SELECT * FROM productos   WHERE Importado = ?";
		
		miPrepstat=miCon.prepareStatement(selectPorImport);
		
		miPrepstat.setString(1, importado);
		miRes=miPrepstat.executeQuery();
		
		while (miRes.next()) {
			
			String codArt = miRes.getString(1);
			String seccArt = miRes.getString(2);
			String nomArt = miRes.getString(3);
			Double precioArt = miRes.getDouble(4);
			Date fechaArt = miRes.getDate(5);
			String importArt = miRes.getString(6);
			String paisArt = miRes.getString(7);
			
			Producto miProdImport = new Producto(codArt, seccArt, nomArt, precioArt, fechaArt, importArt, paisArt);
			
			listaProdImportado.add(miProdImport);			
		}		
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			miCon.close();
			miPrepstat.close();
			miRes.close();
		}
		return listaProdImportado;
	}
	
	public boolean ControlCodArt(String CodArt) throws SQLException {
		Boolean CodArtExiste=false;
		Connection miCon = null;
		PreparedStatement miPrepStat = null;
		ResultSet miRes = null;
		int contRes=0;
		try {
			miCon=dataSource.getConnection();
			
			String SelectCodArt="SELECT CodigoArticulo FROM productos WHERE CodigoArticulo = ? ";
			miPrepStat=miCon.prepareStatement(SelectCodArt);
			
			miPrepStat.setString(1, CodArt);
			miRes = miPrepStat.executeQuery();
			 
			while (miRes.next()) {
				contRes++;
			}
			if (contRes>0) {
				CodArtExiste=true;
			}
			
		}catch (Exception e) {
			e.getMessage();
			System.out.println("Error select cod art");
			e.printStackTrace();
		}finally {
			miCon.close();
			miPrepStat.close();
			miRes.close();
		}
		
		return CodArtExiste;
		
		
	}

	
	
}
	

		
	


