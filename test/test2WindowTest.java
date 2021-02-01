

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import local.Main;
import server.Actor;
import server.Api;
import server.Contenido;
import server.Genero;
import server.Servidor;
import server.Temporada;
import server.Tipo;
import server.Usuario;
import server.Video;

public class test2WindowTest {


	@Test
	public void testCrearUsuario () throws Exception{
		Usuario u = new Usuario("prueba", "Pedro", "Perez", "123456789", "path/...", 
				"123123123", "pedro@gmail.com", "1234", "2000/01/01", new ArrayList<>());
		assertEquals(123123123	, u.getNumMovil());
	}

	@Test
	public void testIniciarBD() throws Exception{
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		assertTrue(Api.abrirConexion("VIPStream.db"));

	}

	@Test
	public void testComprobarServidor() throws Exception{ 
		Servidor.logger = Servidor.creacionDeLogger();
		Servidor.inicioServidor();
	}


	@Test
	public void testObtenerObjectioDeBBDD() throws Exception{
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		assertNotNull(Api.getObjectFromDB("oier", "Usuario"));
	}

	@Test
	public void testEliminarUsuario() throws Exception{
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Usuario u = new Usuario("prueba", "Pedro", "Perez", "123456789", "path/...", 
				"123123123", "pedro@gmail.com", "1234", "2000/01/01", new ArrayList<>());
		Api.deleteObjetoFromDb(u);
		assertFalse(Api.checkObjeto("prueba", "Usuario"));
	}

	@Test
	public void testEliminarActor() {
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Actor a = new Actor("052", "Luis", "Fernandez", "asdf", new ArrayList<>());
		Api.deleteObjetoFromDb(a);
		assertFalse(Api.checkObjeto("052", "Actor"));
	}
	
	@Test
	public void testEliminarContenido() {
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Servidor.logger = Servidor.creacionDeLogger();
		Contenido c = new Contenido("123", "Sabrina", "Carlota", "3", "bfhbjfbjBJ", "2000/01/01","path/..." , new Tipo("3", "Pelicula"), new Genero("43", "Comedia"));
		Api.deleteObjetoFromDb(c);
		assertFalse(Api.checkObjeto("123", "Contenido"));
	}

	@Test
	public void testEliminarGenero() {
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Genero g = new Genero("245", "Misterio");
		Api.deleteObjetoFromDb(g);
		assertFalse(Api.checkObjeto("245", "Genero"));
	}

	@Test
	public void testEliminarTemporada() {
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Temporada t = new Temporada("97", "6", new Contenido("123", "Sabrina", "Carlota", "3", "bfhbjfbjBJ", "2000/01/01","path/..." , new Tipo("3", "Pelicula"), new Genero("43", "Comedia")));
		Api.deleteObjetoFromDb(t);
		assertFalse(Api.checkObjeto("97", "Temporada"));
	}

	@Test
	public void testEliminarTipo() {
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Tipo tp = new Tipo("58", "Serie");
		Api.deleteObjetoFromDb(tp);
		assertFalse(Api.checkObjeto("58", "Tipo"));
	}

	@Test (expected = NullPointerException.class)
	public void testEliminarVideo() {
		Servidor.logger = Servidor.creacionDeLogger();
		Api.abrirConexion("VIPStream.db");
		Video v = new Video("567", "La cenicienta","path/..." , "4", new Contenido("123", "Sabrina", "Carlota", "3", "bfhbjfbjBJ", "2000/01/01","path/..." , new Tipo("3", "Pelicula"), new Genero("43", "Comedia")));
		Api.deleteObjetoFromDb(v);
		assertFalse(Api.checkObjeto("567", "Video"));
	}

//		@Test
//		public void testModificarActor() {
//			Servidor.logger = Servidor.creacionDeLogger();
//			Api.abrirConexion("VIPStream.db");
//			Actor a = new Actor("052", "Luis", "Fernandez", "asdf", new ArrayList<>());
//			Api.modifyObjetoFromDb(a, "2", "Roberto");
//			assertEquals("Roberto", a.getNombre());
//		}
	//
	//	@Test
	//	public void testModificarContenido() {
	//		Servidor.logger = Servidor.creacionDeLogger();
	//		Api.abrirConexion("VIPStream.db");
	//		Contenido c = new Contenido("123", "Sabrina", "Carlota", "3", "bfhbjfbjBJ", "2000/01/01","path/..." , new Tipo("3", "Pelicula"), new Genero("43", "Comedia"));
	//		Api.modifyObjetoFromDb(c, "2", "Coco");
	//		assertEquals("Coco", c.getTitulo());
	//	}

	//	@Test
	//	public void testModificarGenero() {
	//		Servidor.logger = Servidor.creacionDeLogger();
	//		Api.abrirConexion("VIPStream.db");
	//		Servidor.logger = Servidor.creacionDeLogger();
	//		Genero g = new Genero("245", "Misterio");
	//		Api.modifyObjetoFromDb(g, "2", "Comedia");
	//		assertEquals("Comedia", g.getNombre());
	//	}

	//	@Test
	//	public void testModificarTempora() {
	//		Servidor.logger = Servidor.creacionDeLogger();
	//		Api.abrirConexion("VIPStream.db");
	//		Temporada t = new Temporada("97", "6", new Contenido("123", "Sabrina", "Carlota", "3", "bfhbjfbjBJ", "2000/01/01","path/..." , new Tipo("3", "Pelicula"), new Genero("43", "Comedia")));
	//		Api.modifyObjetoFromDb(t, "2", "4");
	//		assertEquals("4", t.getNumero());
	//	}
	//	@Test
	//	public void testModificarTipo() {
	//		Servidor.logger = Servidor.creacionDeLogger();
	//		Api.abrirConexion("VIPStream.db");
	//		Tipo tp = new Tipo("58", "Serie");
	//		Api.modifyObjetoFromDb(tp, "1", "72");
	//		assertEquals("72", tp.getId());
	//	}
	//	@Test
	//	public void testModificarUsuario() {
	//		Servidor.logger = Servidor.creacionDeLogger();
	//	Api.abrirConexion("VIPStream.db");
	//		Usuario u = new Usuario("prueba", "Pedro", "Perez", "123456789", "path/...", 
	//				"123123123", "pedro@gmail.com", "1234", "2000/01/01", new ArrayList<>());
	//		Api.modifyObjetoFromDb(u, "6", "234567890");
	//		assertEquals("234567890", u.getNumMovil());
	//	}

	//	@Test
	//	public void testVaciarBD() {
	//		Servidor.logger = Servidor.creacionDeLogger();
	//		Api.abrirConexion("VIPStream.db");
	//		Usuario u = new Usuario("prueba", "Pedro", "Perez", "123456789", "path/...", 
	//				"123123123", "pedro@gmail.com", "1234", "2000/01/01", new ArrayList<>());
	//		Api.vaciaDB();	
	//		assertNull(Api.checkObjeto("prueba", "Usuario"));
	//	}
}
