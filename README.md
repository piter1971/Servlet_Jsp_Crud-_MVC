Este proyecto es una aplicacion Web hecho en Java Se y con eclipse oxygen .
Se ha usado Tomcat version 8.5 .
Metodo de conexion :  Pool mantiene abierto varias conexiones a la base de datos para varios usuarios
Base de datos  phpMyadmin.
  - Tabla : producto
                    campos:CodigoArticulo  Varchar
                           Seccion         Varchar
                           NombreArticulo  Varchar
                           Precio          Double
                           Fecha           Date
                           Importado       Varchar
                           Pais            Varchar
                           Foto            Blob
                           
   -Tabla : usuario
                  campos : contra
                           nombre
                        
Esta aplicacion se inicia con la pagina Login.jsp y despues de un login correcto permitira : 
  - introducir un registro en la base de datos              
  - Eliminar un registro.
  - actualizar un registro
  - ver productos  entre una fecha y otra.
  - ver productos entre una precio y otra.
  - ver producto por pais elegido.
  - ver producto por seccion elegido.
  - ver producto por importado o no importado.
  
  
