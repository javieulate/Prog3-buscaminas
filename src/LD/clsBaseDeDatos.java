package LD;

import COMUN.clsEmailNoValido;
import LN.clsGestor;
import LN.clsUsuario;
import LN.clsUsuarioRepetido;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class clsBaseDeDatos 
{
	// ------------------------------------
			// VALIDO PARA CUALQUIER BASE DE DATOS
			// ------------------------------------
			
			private static Connection connection = null; //Gestiona la conexión 
			private static Statement statement = null; //Gestiona las instrucciones de la base

			/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
			 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
			 * @param nombreBD	Nombre de fichero de la base de datos
			 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
			 */
			public static Connection initBD( String nombreBD ) {
				try {
				    Class.forName("org.sqlite.JDBC");
				    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
					statement = connection.createStatement();
					statement.setQueryTimeout(30);  // poner timeout 30 msg
				    return connection;
				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog( null, "Error de conexión!! No se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
					System.out.println( "Error de conexión!! No se ha podido conectar con " + nombreBD );
					return null;
				}
			}
			
			/** Cierra la conexión con la Base de Datos
			 */
			public static void close() {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
			 * @return	Conexión con la BD, null si no se ha establecido correctamente.
			 */
			public static Connection getConnection() {
				return connection;
			}
			
			/** Devuelve una sentencia para trabajar con la BD,
			 * si la conexión si ha sido establecida previamente (#initBD()).
			 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
			 */
			public static Statement getStatement() {
				return statement;
			}

			// ------------------------------------
			// PARTICULAR DEL CATALOGO MULTIMEDIA
			// ------------------------------------
			
			/** Crea una tabla de catálogo multimedia en una base de datos, si no existía ya.
			 * Debe haberse inicializado la conexión correctamente.
			 */
			public static void crearTablaBD() {
				if (statement==null) return;
				try {
					statement.executeUpdate("create table if not exists fichero_usuarios " +
						"(nombre string, apellido string, mail string" +
						", nomUsuario string, contrasena string)");
				} catch (SQLException e) {
					// Si hay excepción es que la tabla ya existía (lo cual es correcto)
				 e.printStackTrace();  
				}
			}
			
			//A PARTIR DE AQUÍ LOS MÉTODOS DE LA BASE DE DATOS
			
			/** Añade un fichero multimedia a la tabla FICHERO_MULTIMEDIA de BD, 
			 * que debe estar abierta y tener el formato y los nombres de campos apropiados:
			 * (fichero string, error boolean, titulo string, cantante string, comentarios string)
			 * Usa la sentencia INSERT de SQL.
			 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
			 * @return	true si la inserción es correcta, false en caso contrario
			 * @throws clsUsuarioRepetido 
			 * @throws clsEmailNoValido 
			 */
			public static boolean anyadirFilaATabla( Statement st, clsUsuario a ) throws clsEmailNoValido, clsUsuarioRepetido
			{
				// Adicional uno
				if (chequearYaEnTabla(st,a)) {  // Si está ya en la tabla
					//return modificarFilaEnTabla(st,a);
					throw new clsUsuarioRepetido();
				}
				// Inserción normal
				try {
					clsGestor.validarEmail(a.getMail());
				
				try {
					String sentSQL = "insert into fichero_usuarios values(" +
							"'" + a.getNombre() + "', " +
							"'" + a.getApellido() + "', " +
							"'" + a.getMail() + "', " +
							"'" + a.getNomUsuario() + "', " +
							"'" + a.getContrasena() + "')";
					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
					int val = st.executeUpdate( sentSQL );
					if (val!=1) return false;  // Se tiene que añadir 1 - error si no
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				} 
				catch (clsEmailNoValido e1) {
					// TODO Auto-generated catch block
					throw new clsEmailNoValido();
				}
			}
			
			/** Comprueba si un fichero multimedia ya está en la tabla FICHERO_MULTIMEDIA de BD,
			 * considerando la trayectoria completa del disco como información clave.
			 * @param st	Sentencia ya abierta de base de datos
			 * @return	true si el fichero multimedia ya está en la tabla, false en caso contrario
			 */
			public static boolean chequearYaEnTabla( Statement st, clsUsuario a ) {
				try {
					String sentSQL = "select * from fichero_usuarios " +
							"where (nomUsuario = '" + a.getNomUsuario() + "' or mail = '" + a.getMail() + "' or contrasena = '" + a.getContrasena() + "'  )";
					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
					ResultSet rs = st.executeQuery( sentSQL );
					if (rs.next()) {  // Normalmente se recorre con un while, pero aquí solo hay que ver si ya existe
						rs.close();
						return true;
					}
					return false;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			
			/** Modifica los datos de un fichero multimedia en la tabla FICHERO_MULTIMEDIA de BD, 
			 * que debe estar abierta y tener el formato y los nombres de campos apropiados:
			 * (fichero string, error boolean, titulo string, cantante string, comentarios string)
			 * Usa la sentencia UPDATE de SQL.
			 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
			 * @return	true si la modificación es correcta, false en caso contrario
			 */
			public boolean modificarFilaEnTabla( Statement st, clsUsuario a ) {
				try {
					String sentSQL = "update fichero_usuarios set " +
							"nombre = '" + a.getNombre() + "', " +
							"apellido = '" + a.getApellido() + "', " +
							"mail = '" + a.getMail() + "', " +
							"contrasena = '" + a.getContrasena() + "' " +
							"where (nomUsuario = '" + a.getNomUsuario() + "')";
					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
					int val = st.executeUpdate( sentSQL );
					if (val!=1) return false;  // Se tiene que modificar 1, error si no
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
			
			/** Carga un fichero multimedia de la tabla FICHERO_MULTIMEDIA de BD,
			 * buscando la trayectoria completa del disco como información clave.
			 * @param st	Sentencia ya abierta de base de datos
			 */
//			public void cargarDeTabla( Statement st, String nomUsuario, String mail ) {
//				try {
//					String sentSQL = "select * from fichero_usuarios " +
//							"where (nomUsuario = '" + nomUsuario + "' and mail = '" + mail + "' )";
//					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
//					ResultSet rs = st.executeQuery( sentSQL );
//					if (rs.next()) {  // Normalmente se recorre con un while, pero aquí solo hay que ver si ya existe
//						nombre = rs.getString( "nombre" );
//						apellido = rs.getString( "apellido" );
//						//mail = rs.getString( "mail" );
//						contrasena = rs.getString( "contrasena" );
//						rs.close();
//					}
//					// else No hay ninguno en la tabla
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}

			public static clsUsuario cargarDeTabla2( Statement st, String nomUsuario, String mail, String contrasena ) {
				try {
					String sentSQL = "select * from fichero_multimedia " +
							"where (nomUsuario = '" + nomUsuario + "' and mail = '" + mail + "' and mail = '" + contrasena + "' )";
					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
					ResultSet rs = st.executeQuery( sentSQL );
					if (rs.next()) {  // Normalmente se recorre con un while, pero aquí solo hay que ver si ya existe
						clsUsuario u = new clsUsuario();
						u.nombre = rs.getString( "nombre" );
						u.apellido = rs.getString( "apellido" );
						u.contrasena= rs.getString( "contrasena" );
						u.mail = rs.getString( "mail" );
						u.nomUsuario = rs.getString("nomUsuario");
						rs.close();
						return u;
					}
					// else No hay ninguno en la tabla
					return null;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;  // Error
				}
			}
			
			
			/** Crea ficheros multimedia, cargándolos de la tabla FICHERO_MULTIMEDIA de BD,
			 * considerando la expresión where que se indica (formato de sql)
			 * @param st	Sentencia ya abierta de base de datos
			 * @param exprWhere	Expresión where, "" si se quieren cargar todos
			 * @return	lista de nuevos objetos cargados de la tabla, vacía si no hay ninguno que cumple las condiciones, null si hay error
			 */
			public static ArrayList<clsUsuario> cargarVariosDeTabla( Statement st, String exprWhere ) {
				try {
					ArrayList<clsUsuario> lista = new ArrayList<>();
					String sentSQL = "select * from fichero_usuarios" +
							((exprWhere==null||exprWhere.equals(""))?"":(" where " + exprWhere));
					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
					ResultSet rs = st.executeQuery( sentSQL );
					while (rs.next()) { 
						clsUsuario u = new clsUsuario();
						u.nombre = rs.getString( "nombre" );
						u.apellido = rs.getString( "apellido" );
						u.contrasena= rs.getString( "contrasena" );
						u.mail = rs.getString( "mail" );
						u.nomUsuario = rs.getString("nomUsuario");
						rs.close();
						lista.add( u );
					}
					return lista;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;  // Error
				}
			}
			
			public static ArrayList<clsUsuario> cargarVariosDeTabla2( Statement st) {
				try {
					ArrayList<clsUsuario> lista = new ArrayList<>();
					String sentSQL = "select * from fichero_usuarios";
					System.out.println( sentSQL );  // (Quitar) para ver lo que se hace
					ResultSet rs = st.executeQuery( sentSQL );
					int counter = 0;
					while (rs.next()) { 
						System.out.println("cargarVAriosDeTabla2: " + counter);
						clsUsuario u = new clsUsuario();
						u.nombre = rs.getString( "nombre" );
						u.apellido = rs.getString( "apellido" );
						u.contrasena= rs.getString( "contrasena" );
						u.mail = rs.getString( "mail" );
						u.nomUsuario = rs.getString("nomUsuario");
						
						lista.add( u );
						counter++;
					}
					rs.close();
					return lista;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;  // Error
				}
			}
			

}
