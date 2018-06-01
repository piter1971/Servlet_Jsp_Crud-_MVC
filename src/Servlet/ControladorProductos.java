package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  ModeloProducto  modeloProd ;
    @Resource(name="jdbc/productos")
    private DataSource dataS;
    String codArtActualizar;
    
  

	public ControladorProductos() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ordenar = request.getParameter("ordenar");
			
		
		try {
			modeloProd = new ModeloProducto(dataS);
			}catch (Exception e) {
				throw new ServletException();
			}
		//Leer el parametro que le llegue del formulario insertarProd.jsp
		//<input type="hidden" name="instruccion" value="InsertarBD"> en InsertarProd.jsp
		
		String elComando = request.getParameter("instruccion");
		
		// si no se envie el parametro, listar productos
		
		
		//redirgir el flujo de ejecucio al metodo adecuado
		/*switch el valor de elcomando puede ser  "listar" del if en caso de null o "InsertarBD" de value="InsertarBD" que viene de InsertarProd.jsp 
		 * ActualizarBD que viene de listarProductos*/
		switch (elComando) {
		case "listar":
			obtenerProductos(request, response);
			//listarPorsecciones(request, response);
			break;
		case "InsertarBD":
			agregarProductos(request, response);
			break;
		case "Ir a lista completa":
			obtenerProductos(request, response);
			break;
		case "loginBD":
			try {
				controlAcceso(request, response);
			} catch (SQLException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
			break;
		case "verPorPais":
			try {
				listaPorPais(request, response);
			} catch (Exception e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			break;
		case "verPorSeccion":
			
			try {
				verPorsecciones(request, response);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
			
		case "verPorImportado":			
			try {
				verPorImportacion(request, response);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
			case "verPorPrecios":
			
			try {
				listarPorPrecios(request, response);
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
		case "verPorFechas":
			try {
				verOrdenadoPorFechas(request, response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "Eliminar":
			try {
				EliminarProductos(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;	
		case "ActualizarBDListaProd":
			try {
				ActualizarProductos_ListaProd(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			break;
		case "BDActualizado":
			try {
				BDActualizado_ActualizarProducto(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;	
		default:
			obtenerProductos(request, response);
			break;
		}		
		}
	
	
	
	private void verPorImportacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String importado = request.getParameter("importado");
		List<Producto> listaPorImport;
		listaPorImport=modeloProd.listarPorImportado(importado);
		request.setAttribute("recibeListaImport", listaPorImport);
		request.setAttribute("import", importado);
		RequestDispatcher rDis =request.getRequestDispatcher("/VerPorImportado.jsp");
		rDis.forward(request, response);
	}

	private void verPorsecciones(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List <Producto> listaPorSeccion;
		String seccion=request.getParameter("Seccion");
		
		listaPorSeccion=modeloProd.verPorListaPorSeccion(seccion);
		
		request.setAttribute("enviarListaPorSeccion", listaPorSeccion);
		request.setAttribute("opcionElegida", seccion);
		RequestDispatcher rDisp = request.getRequestDispatcher("/ListadoPorSeccion.jsp");
		rDisp.forward(request, response);
		
	}

	private void listarPorPrecios(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String p1=request.getParameter("p1");
		String p2=request.getParameter("p2");
		
			List <Producto> listaPrecio;
			
			listaPrecio= modeloProd.verListaPrecio( p1 , p2);
			
			request.setAttribute("recibeListaPrecio", listaPrecio);
			request.setAttribute("precio1", p1);
			request.setAttribute("precio2", p2);
			
			RequestDispatcher rDisp = request.getRequestDispatcher("/ListadoPorPrecios.jsp");
			rDisp.forward(request, response);
		
		
	}

	private void listaPorPais(HttpServletRequest request, HttpServletResponse response) throws   Exception {
		String pais = request.getParameter("pais");
		List <Producto> listaPais;
		listaPais = modeloProd.listaProdPais(pais);		
		request.setAttribute("RecibeListaPais", listaPais);
		request.setAttribute("pais", pais);
		RequestDispatcher miDispatcher = request.getRequestDispatcher("/VerPorPais.jsp");
		miDispatcher.forward(request, response);
	}

	private void listarPorsecciones(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List <String> listaSecc;
			listaSecc=modeloProd.obtenerProdSeccion();
			request.setAttribute("listaSecc",listaSecc );		
			RequestDispatcher miDispatcher = request.getRequestDispatcher("/VerPorSeccion.jsp");
			miDispatcher.forward(request, response);			
	}

	private void verOrdenadoPorFechas(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		List<Producto> listafechas;
		String f1=request.getParameter("fecha1");
		String f2=request.getParameter("fecha2");
		
		if (f1.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$") && f2.matches("^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")) {
			listafechas=modeloProd.verFechas(f1,f2);
			request.setAttribute("recibeListaFecha",listafechas );
			request.setAttribute("f1", f1);
			request.setAttribute("f2", f2);
			
			RequestDispatcher rdisp = request.getRequestDispatcher("/VerPorFechas.jsp");
			rdisp.forward(request, response);	
		}else {
			String errorFechas = "Hay un error en unos de las dos fechas";
			request.setAttribute("errorFechas", errorFechas);
			obtenerProductos( request, response);
			RequestDispatcher rdisp = request.getRequestDispatcher("/ListaProductos.jsp");
			rdisp.forward(request, response);	
			
		}
		
		obtenerProductos( request, response);
	}

	private void controlAcceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String user = request.getParameter("usuario");
		String pass = request.getParameter("contra");
		
		if (modeloProd.obtenerUsuarioContra(user ,  pass)) {
			obtenerProductos(request, response);
		}else {
			String error=" Error en el login";
			request.setAttribute("errorLogin", error);
			RequestDispatcher rdisp = request.getRequestDispatcher("/Login.jsp");
			rdisp.forward(request, response);
			}		 
	}

	private void EliminarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String  CodArticulo = request.getParameter("CodArt");
		modeloProd.eliminarProducto(CodArticulo);
		
		obtenerProductos(request, response);
		
		
	}

	private void BDActualizado_ActualizarProducto(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//Leer los datos que le vienen de ActualizarProducto.jsp
		String codArt=codArtActualizar;
		String seccion=request.getParameter("txtSeccion");
		String nomArt=request.getParameter("txtNomArt");
		String fecha=request.getParameter("txtFecha");
		Date fecha1 =null;
		try {
			fecha1= new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e) {
			System.out.println("Error inseccion");
			e.printStackTrace();
		}
		Double precio=Double.parseDouble(request.getParameter("txtPrecio"));		
		String importado=request.getParameter("txtImportado");
		String paisOrigen=request.getParameter("txtPais");	
		
		
		//Crear un objeto de tipo Producto con la info del formulario ActualizarProducto.jsp
		Producto prodActualizado = new Producto(codArt, seccion, nomArt,precio, fecha1, importado, paisOrigen);
		//Actualizar la BD con la info del objeto
		
		 modeloProd.actualizarProd(prodActualizado );
		 
		// Volver al listado con la info listado
		 obtenerProductos(request, response);
	}

	private void ActualizarProductos_ListaProd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
			//Leer el codigo del articulo que viene del listaProductos.jsp
			
		String  CodArticulo = request.getParameter("CodArt");
		codArtActualizar=CodArticulo;
		
			//Enviar codigo articulo a ModeloProducto.java y igualar instancia Producto con el return de getCodArtProducto que tambien devuelve una instancia
		Producto elProd = modeloProd.getCodArtProducto(CodArticulo);
				
			//colocar atributo correspondiente a CodArt
		request.setAttribute("productoActualizar", elProd);
		request.setAttribute("CodArt", CodArticulo);
		
			//Enviar producto al fromulario de actualizar.jsp
		RequestDispatcher rDisp= request.getRequestDispatcher("/ActualizarProducto.jsp");
		rDisp.forward(request, response);
		
	}

	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		
		//leer la informacion del productos que le llegue del formulario InsertProd.jsp
		String codArt=request.getParameter("txtcArt");
		String seccion=request.getParameter("txtSeccion");
		String nomArt=request.getParameter("txtNomArt");
		String fecha=request.getParameter("txtFecha");
		Producto prod=null;
		Date fecha1 =null;		
				if (!fecha.equals("")) {
				try {
					fecha1= new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else {
					
				}			
		String precio1=request.getParameter("txtPrecio");
		Double	precio=null;
		if (!precio1.equals("")) {
		 precio=Double.parseDouble(precio1);	
		
		}else {
			precio=0.0;
		}
			
		String importado=request.getParameter("txtImportado");
		String paisOrigen=request.getParameter("txtPais");
		
		//Crear el objeto Producto
		 prod = new Producto(codArt, seccion, nomArt,precio, fecha1, importado, paisOrigen);
		
		
		//Enviar el objeto al modelo y depues el insertar el objeto producto en la BD
		 try {
			 if (!modeloProd.ControlCodArt( codArt)) {
				 modeloProd.agregarNuevoProd(prod);
				 obtenerProductos(request, response);
			}else {
				String errorCodArt = "Este codigo de articulo ya existe";
				request.setAttribute("errorCodArt", errorCodArt );
				RequestDispatcher rDis=request.getRequestDispatcher("/InsertarProd.jsp");
				rDis.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Volver al listado  de productos
		
	}

	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
				//--Lista de productos desde el modelo--
				List <Producto> productoList;
				List <String> listaSecc;
				List <String> listaPais;
				
				
				try {
					productoList= modeloProd.getProductos();
					listaSecc=modeloProd.obtenerProdSeccion();
					listaPais = modeloProd.obtenerProdPais();
				//--------agregar lista de productos al request
					request.setAttribute("LISTAPRODUCTOS", productoList);
					request.setAttribute("listaSecc",listaSecc );
					request.setAttribute("RecibeListaPais", listaPais);
				//--  enviar esta request a la pagina JSP-----
					RequestDispatcher miDispatcher = request.getRequestDispatcher("/ListaProductos.jsp");
					miDispatcher.forward(request, response);
					
				}catch (Exception e) {
					e.printStackTrace();
				}		
	}
	
	
	
}
