package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * @author Mentxaka
 *
 */
public class Api {

	static Connection conexion;

	public static boolean abrirConexion( String nombreBD ) {
		try {
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			Servidor.logger.log( Level.INFO, " ---> Conexión con BD establecida <---" );
			return true;
		}catch(Exception e){
			e.printStackTrace();
			Servidor.logger.log( Level.SEVERE, " Conexión con BD FALLIDA " );
			return false;
		}
	}

	public static void cerrarConexion() {
		try {
			conexion.close();
			Servidor.logger.log( Level.INFO, " ---> Conexión con BD cerrada correctamente <---" );
		} catch (SQLException e) {
			Servidor.logger.log( Level.SEVERE, " Cierre de conexión con BD FALLIDA " );
			e.printStackTrace();
		}
	}



	/**
	 * @param id	CLAVE DEL OBJETO 
	 * @param tipo	TIPO DEL OBJETO, USUARIO O actorActriz 
	 * @return
	 */
	public static ArrayList<Video> getVideoCollection(String id,String tipo) {
		ArrayList<Video> array = new ArrayList<>();
		try {
			Statement stmt = conexion.createStatement();
			ResultSet rs = null; 
			if(tipo.equalsIgnoreCase("USUARIO") && checkObjeto(id ,"USUARIO")){
				rs = stmt.executeQuery("SELECT * FROM usuariove WHERE usuario_id='" + id + "';");
			}else if(tipo.equalsIgnoreCase("actorActriz") && checkObjeto(id ,"actorActriz")){
				rs = stmt.executeQuery("SELECT * FROM aparece WHERE actor_id='" + id + "';");
			}
			while(rs.next()) {
				Video v = (Video) getObjectFromDB( rs.getString("VIDEO_ID"), "VIDEO");
				array.add(v);
			}
		} catch (SQLException e) {
			Servidor.logger.log( Level.SEVERE, " ERROR al crear coleccion de videos " );
			e.printStackTrace();

		}

		return array;
	}

	/**
	 * @param text coge el nombre del objeto que si existe en la base de datos
	 * @return convierte ese objeto cogido de la base de datos en un objeto de tipo objeto.
	 */
	public static Object getObjectFromDB(String id, String tabla) {
		if(tabla.equalsIgnoreCase("ACTORACTRIZ")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE ID='"+id+"';");
					Actor a = new Actor(
							rs.getString("ID"), 
							rs.getString("NOMBRE"), 
							rs.getString("APELLIDO"), 
							rs.getString("DESCRIPCION"), 
							getVideoCollection(id, "actorActriz"));
					Servidor.logger.log( Level.INFO, " EL USUARIO "+id+" ES DEVUELTO" );
					return a;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
				}
			}else {
				Servidor.logger.log( Level.INFO, " EL ACTOR "+id+" NO EXISTE " );
			}
		}
		if(tabla.equalsIgnoreCase("CONTENIDO")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE ID='"+id+"';");

					Contenido c = new Contenido(
							rs.getString("ID"), 
							rs.getString("TITULO"),  
							rs.getString("DIRECTOR"),  
							rs.getString("VALORACION"), 
							rs.getString("SINOPSIS"), 
							rs.getString("FECHA"), 
							rs.getString("PATH"), 
							(Tipo) getObjectFromDB(rs.getString("tipo_id"), "TIPO"), 
							(Genero) getObjectFromDB(rs.getString("gen_id"), "GENERO"));

					Servidor.logger.log( Level.INFO, " EL CONTENIDO "+id+" ES DEVUELTO" );
					return c;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
					e.printStackTrace();
				}
			}else {
				Servidor.logger.log( Level.INFO, " EL CONTENIDO "+id+" NO EXISTE " );
			}
		}
		if(tabla.equalsIgnoreCase("GENERO")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE ID='"+id+"';");
					Genero g = new Genero(
							rs.getString("ID"), 
							rs.getString("NOMBRE"));

					Servidor.logger.log( Level.INFO, " EL GENERO "+id+" ES DEVUELTO" );
					return g;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
				}
			}else {
				Servidor.logger.log( Level.INFO, " EL GENERO "+id+" NO EXISTE " );
			}
		}
		if(tabla.equalsIgnoreCase("TEMPORADA")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE ID='"+id+"';");

					Temporada temp = new Temporada(
							rs.getString("ID"), 
							rs.getString("NUMERO"),
							(Contenido) getObjectFromDB(rs.getString("contenido_id"), "CONTENIDO") );

					Servidor.logger.log( Level.INFO, " LA TEMPORADA "+id+" ES DEVUELTA" );
					return temp;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
				}
			}else {
				Servidor.logger.log( Level.INFO, " LA TEMPORADA "+id+" NO EXISTE " );
			}
		}
		if(tabla.equalsIgnoreCase("TIPO")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE ID='"+id+"';");

					Tipo t = new Tipo(
							rs.getString("ID"), 
							rs.getString("NOMBRE"));

					Servidor.logger.log( Level.INFO, " EL TIPO "+id+" ES DEVUELTO" );
					return t;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
				}
			}else {
				Servidor.logger.log( Level.INFO, " EL TIPO "+id+" NO EXISTE " );
			}
		}
		if(tabla.equalsIgnoreCase("USUARIO")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE id='"+id+"';");

					Usuario u = new Usuario(
							rs.getString("ID"), 
							rs.getString("NOMBRE"), 
							rs.getString("APELLIDO"), 
							rs.getString("NCUENTA"), 
							rs.getString("PATHFOTO"),  
							rs.getString("TELEFONO"), 
							rs.getString("EMAIL"),
							rs.getString("CONTRASENYA"), 
							rs.getString("FECHANACIMIENTO"), 
							getVideoCollection(id,"usuario"));

					Servidor.logger.log( Level.INFO, " EL USUARIO "+id+" ES DEVUELTO" );
					return u;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
				}
			}else {
				Servidor.logger.log( Level.INFO, " EL USUARIO "+id+" NO EXISTE " );
			}
		}
		if(tabla.equalsIgnoreCase("VIDEO")) {
			if (checkObjeto(id,tabla)){
				try {
					Statement stmt = conexion.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE ID='"+id+"';");

					Video v ;
					if(rs.getString("temporada_id").isEmpty()) {
						v = new Video(
								rs.getString("ID"), 
								rs.getString("TITULO"), 
								rs.getString("PATH"), 
								rs.getString("DURACION"),
								(Contenido) getObjectFromDB(rs.getString("contenido_id"), "CONTENIDO"));

					}else {
						v = new Video(
								rs.getString("ID"), 
								rs.getString("TITULO"), 
								rs.getString("PATH"), 
								rs.getString("DURACION"),
								(Temporada) getObjectFromDB(rs.getString("temporada_id"), "TEMPORADA"));

					}
					Servidor.logger.log( Level.INFO, " EL VIDEO "+id+" ES DEVUELTO" );
					return v;
				} catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL LEER DEL DATABASE " );
				}
			}else {
				Servidor.logger.log( Level.INFO, " EL VIDEO "+id+" NO EXISTE " );
			}
		}
		return tabla;
	}

	/** 
	 * @param objeto objeto que se quiere meter a la bd
	 * @param tabla	tabla en la que se quiere meter el objeto
	 */
	public static void addObjetoToDb(Object objeto) {
		if (objeto instanceof Actor) {
			Actor actor = (Actor) objeto;
			if(!checkObjeto(actor.getId()+"","actorActriz")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando actorActriz... " );

					String inserta = "INSERT INTO actorActriz VALUES('"+actor.getId() + "','" +	actor.getNombre() + "','" + actor.getApellido() + "','" + actor.getDescripcion() + "');";
					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " actorActriz registrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR ACTOR ");
				}
			}
		}
		if (objeto instanceof Contenido) {
			Contenido contenido = (Contenido) objeto;
			if(!checkObjeto(contenido.getId()+"","CONTENIDO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando contenido... " );

					String inserta = "INSERT INTO contenido VALUES('"+contenido.getId() + "','" +	contenido.getTitulo()+ "','" +contenido.getDirector()+ "','" +contenido.getValoracion()+ "','" +contenido.getSinopsis()+ "','" +contenido.getFecha()+ "','" +contenido.getPathFoto()+"','" + contenido.getGenero().getId() +"','" + contenido.getTipo().getId() + "');";
					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " Tipo registrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR CONTENIDO ");
				}
			}
		}
		if (objeto instanceof Genero) {
			Genero genero = (Genero) objeto;
			if(!checkObjeto(genero.getId()+"","GENERO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando genero... " );

					String inserta = "INSERT INTO genero VALUES('"+genero.getId() + "','" +	genero.getNombre()+ "');";
					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " Genero registrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR GENERO ");
				}
			}
		}
		if (objeto instanceof Temporada) {
			Temporada temporada = (Temporada) objeto;
			if(!checkObjeto(temporada.getId()+"","TEMPORADA")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando temporada... " );

					String inserta = "INSERT INTO temporada VALUES('"+temporada.getId() + "','" +	temporada.getNumero() + "','" + temporada.getContenido().getId()+ "');";
					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " Temporada registrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR GENERO ");
				}
			}
		}
		if (objeto instanceof Tipo) {
			Tipo tipo = (Tipo) objeto;
			if(!checkObjeto(tipo.getId()+"","TIPO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando tipo... " );

					String inserta = "INSERT INTO tipo VALUES('"+tipo.getId() + "','" +tipo.getNombre()+ "');";

					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " Tipo registrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR TIPO ");
				}
			}
		}
		if (objeto instanceof Usuario) {
			Usuario user = (Usuario) objeto;
			if(!checkObjeto(user.getNick(),"USUARIO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando usuario... " );

					String inserta = "INSERT INTO usuario VALUES('"+user.getNick()+ "','" +user.getNombre()+ "','" 
							+user.getApellido()+  "'," +user.getNumCuenta()+  ",'" +user.getPathFoto()+  "','" +user.getEmail()+ 
							"'," +user.getNumMovil()+ ",'" +user.getContrasenya()+ "','" +user.getFechaNacimiento()+ "');";

					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " Usuario registrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR USUARIO ");
					e.printStackTrace();
				}
			}
		}
		if (objeto instanceof Video) {
			Video video = (Video) objeto;
			if(!checkObjeto(video.getId()+"","VIDEO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Registrando video... " );
					String inserta = null;

					if(video.getTemporada() == null) {
						inserta = "INSERT INTO video VALUES('"+video.getId() + "','" +	video.getNombre()+"','" +video.getPath() + "','" + video.getValoracion() + "','" + "" + "','" + video.getContenido().getId() + "');";
					}
					if(video.getContenido() == null) {
						inserta = "INSERT INTO video VALUES('"+video.getId() + "','" +	video.getNombre()+"','" +video.getPath() + "','" + video.getValoracion() + "','" + video.getTemporada().getId() + "','" + "" + "');";
					}


					stmt.executeUpdate( inserta );

					Servidor.logger.log( Level.INFO, " Video registrado! " );

				}catch (Exception e) {
					e.printStackTrace();
					Servidor.logger.log( Level.SEVERE, " ERROR AL INSERTAR VIDEO ");
				}
			}
		}
	}

	/**
	 * @param objeto objeto que se desea eliminar
	 */
	public static void deleteObjetoFromDb(Object objeto) {
		if (objeto instanceof Actor) {
			Actor actor = (Actor) objeto;
			if(checkObjeto(actor.getId()+"","actorActriz")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando actorActriz... " );

					String sentencia = "DELETE FROM actorActriz WHERE ID='"+actor.getId()+"';";
					stmt.executeUpdate( sentencia );
					Servidor.logger.log( Level.INFO, " actorActriz borrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR ACTOR ");
				}
			}
		}
		if (objeto instanceof Contenido) {
			Contenido contenido = (Contenido) objeto;
			if(checkObjeto(contenido.getId()+"","CONTENIDO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando contenido... " );

					String sentencia = "DELETE FROM contenido WHERE ID='"+contenido.getId()+ "';";
					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Tipo borrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR CONTENIDO ");
				}
			}
		}
		if (objeto instanceof Genero) {
			Genero genero = (Genero) objeto;
			if(checkObjeto(genero.getId()+"","GENERO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando genero... " );

					String sentencia = "DELETE FROM genero WHERE ID='"+genero.getId()+"';";
					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Genero borrado! " );

				}catch (Exception e) {
					e.printStackTrace();
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR GENERO ");
				}
			}
		}
		if (objeto instanceof Temporada) {
			Temporada temporada = (Temporada) objeto;
			if(checkObjeto(temporada.getId()+"","TEMPORADA")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando temporada... " );

					String sentencia = "DELETE FROM temporada WHERE ID='"+temporada.getId()+ "';";
					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Temporada borrada! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR TEMPORADA ");
				}
			}
		}
		if (objeto instanceof Tipo) {
			Tipo tipo = (Tipo) objeto;
			if(checkObjeto(tipo.getId()+"","TIPO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando tipo... " );

					String sentencia = "DELETE FROM tipo WHERE ID='"+tipo.getId()+ "';";

					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Tipo borrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR TIPO ");
				}
			}
		}
		if (objeto instanceof Usuario) {
			Usuario user = (Usuario) objeto;
			if(checkObjeto(user.getNick(),"USUARIO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando usuario... " );

					String sentencia = "DELETE FROM usuario WHERE ID='"+user.getNick()+ "';";

					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Usuario borrado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR USUARIO ");
					e.printStackTrace();
				}
			}
		}
		if (objeto instanceof Video) {
			Video video = (Video) objeto;
			if(checkObjeto(video.getId()+"","VIDEO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Borrando video... " );
					String sentencia = null;

					sentencia = "DELETE FROM video WHERE ID='"+video.getId()+ "';";

					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Video borrado! " );

				}catch (Exception e) {
					e.printStackTrace();
					Servidor.logger.log( Level.SEVERE, " ERROR AL BORRAR VIDEO ");
				}
			}
		}
	}
	/**
	 * @param objeto
	 */
	public static void modifyObjetoFromDb(Object objeto, String columna, String valor) {
		if (objeto instanceof Actor) {
			Actor actor = (Actor) objeto;
			if(checkObjeto(actor.getId()+"","actorActriz")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando actorActriz... " );

					String sentencia = "UPDATE actorActriz SET "+columna+"="+valor+" WHERE ID='"+actor.getId()+"';";
					stmt.executeUpdate( sentencia );
					Servidor.logger.log( Level.INFO, " actorActriz modificado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR ACTOR ");
				}
			}
		}
		if (objeto instanceof Contenido) {
			Contenido contenido = (Contenido) objeto;
			if(checkObjeto(contenido.getId()+"","CONTENIDO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando contenido... " );

					String sentencia = "UPDATE contenido SET "+columna+"="+valor+" ID='"+contenido.getId()+ "';";
					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Tipo modificado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR CONTENIDO ");
				}
			}
		}
		if (objeto instanceof Genero) {
			Genero genero = (Genero) objeto;
			if(checkObjeto(genero.getId()+"","GENERO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando genero... " );

					String sentencia = "UPDATE genero SET "+columna+"="+valor+" WHERE ID='"+genero.getId()+"';";
					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Genero modificado! " );

				}catch (Exception e) {
					e.printStackTrace();
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR GENERO ");
				}
			}
		}
		if (objeto instanceof Temporada) {
			Temporada temporada = (Temporada) objeto;
			if(checkObjeto(temporada.getId()+"","TEMPORADA")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando temporada... " );

					String sentencia = "UPDATE temporada SET "+columna+"="+valor+" WHERE ID='"+temporada.getId()+ "';";
					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Temporada modificado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR TEMPORADA ");
				}
			}
		}
		if (objeto instanceof Tipo) {
			Tipo tipo = (Tipo) objeto;
			if(checkObjeto(tipo.getId()+"","TIPO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando tipo... " );

					String sentencia = "UPDATE tipo SET "+columna+"="+valor+" WHERE ID='"+tipo.getId()+ "';";

					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Tipo modificado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR TIPO ");
				}
			}
		}
		if (objeto instanceof Usuario) {
			Usuario user = (Usuario) objeto;
			if(checkObjeto(user.getNick(),"USUARIO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando usuario... " );

					String sentencia = "UPDATE usuario SET "+columna+"="+valor+" WHERE ID='"+user.getNick()+ "';";

					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Usuario modificado! " );

				}catch (Exception e) {
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR USUARIO ");
					e.printStackTrace();
				}
			}
		}
		if (objeto instanceof Video) {
			Video video = (Video) objeto;
			if(checkObjeto(video.getId()+"","VIDEO")) {
				try {
					Statement stmt = conexion.createStatement();

					Servidor.logger.log( Level.INFO, " Modificando video... " );
					String sentencia = null;

					sentencia = "UPDATE video SET "+columna+"="+valor+" WHERE ID='"+video.getId()+ "';";

					stmt.executeUpdate( sentencia );

					Servidor.logger.log( Level.INFO, " Video modificado! " );

				}catch (Exception e) {
					e.printStackTrace();
					Servidor.logger.log( Level.SEVERE, " ERROR AL MODIFICAR VIDEO ");
				}
			}
		}
	}
	/**
	 * @param id id del objeto que se busca
	 * @param tabla tabla en la que debe buscarse el objeto
	 * @return devuelve si el objeto está o no en la BD
	 */
	public static boolean checkObjeto(String id, String tabla) {
		boolean existe = false;
		try {
			Statement stmt = conexion.createStatement();

			try {
				ResultSet rs = stmt.executeQuery("SELECT * FROM "+tabla+" WHERE id='" + id + "';");	
				rs.getString("id");

				Servidor.logger.log( Level.SEVERE, " EL OBJETO " + id + " EXISTE " );

				existe = true;

			} catch (Exception e) {
				Servidor.logger.log( Level.SEVERE, " EL OBJETO " + id + " NO EXISTE " );
			}

		} catch (Exception e) {
			Servidor.logger.log( Level.SEVERE, " ERROR INESPERADO ");
			e.printStackTrace();
		}		
		return existe;
	}

	/** 
	 * BORRA TODOS LOS OBJETOS DE LA BASE DE DATOS
	 */
	public static void vaciaDB() {
		try {
			Statement stmt1 = conexion.createStatement();
			Statement stmt2 = conexion.createStatement();
			Statement stmt3 = conexion.createStatement();
			Statement stmt4 = conexion.createStatement();
			Statement stmt5 = conexion.createStatement();
			Statement stmt6 = conexion.createStatement();
			Statement stmt7 = conexion.createStatement();
			Statement stmt8 = conexion.createStatement();
			Statement stmt9 = conexion.createStatement();

			ResultSet[] rs = {
					stmt1.executeQuery("SELECT * FROM actoractriz;"),	
					stmt2.executeQuery("SELECT * FROM aparece;"),	
					stmt3.executeQuery("SELECT * FROM contenido;"),	
					stmt4.executeQuery("SELECT * FROM genero;"),
					stmt5.executeQuery("SELECT * FROM temporada;"),	
					stmt6.executeQuery("SELECT * FROM tipo;"),
					stmt7.executeQuery("SELECT * FROM usuario;"),	
					stmt8.executeQuery("SELECT * FROM usuariove;"),	
					stmt9.executeQuery("SELECT * FROM video;"),
			};
			String[] tablas = {
					"ACTORACTRIZ",
					"APARECE",
					"CONTENIDO",
					"GENERO",
					"TEMPORADA",
					"TIPO",
					"Usuario",
					"USUARIOVE",
					"VIDEO"
			};

			for (int i = 0;i<rs.length;i++) {
				Statement stmtd = conexion.createStatement();
				while (rs[i].next()) {
					String sentencia = "DELETE FROM "+tablas[i]+" WHERE ID='"+rs[i].getString("ID")+ "';";
					stmtd.executeUpdate( sentencia );
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			Servidor.logger.log( Level.SEVERE, " ERROR INESPERADO AL VACIAR DB ");
		}
	}


	/**
	 * @param user SE PASA UN OBJETO DE TIPO USUARIO Y SE MODIFICAN LOS PARAMETROS DEL MISMO
	 */
	public static void modifyUser(Usuario user) {
		String sentencia = "" ;

		try {
			Statement SNombre = conexion.createStatement();
			Statement SApellido = conexion.createStatement();
			Statement SNumCuenta = conexion.createStatement();
			Statement SPathFoto = conexion.createStatement();
			Statement SnumMovil = conexion.createStatement();
			Statement SEmail = conexion.createStatement();
			Statement Scontrasenya = conexion.createStatement();
			Statement SfechaNacimiento = conexion.createStatement();

			sentencia = "UPDATE usuario SET nombre='"+user.getNombre()+"' WHERE ID='"+user.getNick()+"';";
			SNombre.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET apellido='"+user.getApellido()+"' WHERE ID='"+user.getNick()+"';";
			SApellido.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET ncuenta='"+user.getNumCuenta()+"' WHERE ID='"+user.getNick()+"';";
			SNumCuenta.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET pathfoto='"+user.getPathFoto()+"' WHERE ID='"+user.getNick()+"';";
			SPathFoto.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET email='"+user.getEmail()+"' WHERE ID='"+user.getNick()+"';";
			SnumMovil.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET telefono='"+user.getNumMovil()+"' WHERE ID='"+user.getNick()+"';";
			SEmail.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET contrasenya='"+user.getContrasenya()+"' WHERE ID='"+user.getNick()+"';";
			Scontrasenya.executeUpdate( sentencia );

			sentencia = "UPDATE usuario SET fechaNacimiento='"+user.getFechaNacimiento()+"' WHERE ID='"+user.getNick()+"';";
			SfechaNacimiento.executeUpdate( sentencia );

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * @return DEVUELVE LOS NUEVOS CONTENIDOS DE VIPSTREAM
	 */
	public static String[] getNewsArray() {
		return arrayEjemplo();
	}

	/**
	 * @param USER USUARIO EXPECIFICO AL QUE SE LE HACE REFERENCIA
	 * @return LOS CONTENIDOS RECOMENDADOS PARA EL USUARIO
	 */
	public static String[] getRecommendedArray(Usuario user) {
		return arrayEjemplo();
	}

	/**
	 * @return ARRAY COMO EJEPLO
	 */
	private static String[] arrayEjemplo() {
		String[] array = {
				"567",
				"234",
				"235", 
				"236",
				"237", 
				"238",
				"239", 
				"240", 
				"241", 
				"242", 
				"243"
		}; 
		return array;
	}

}