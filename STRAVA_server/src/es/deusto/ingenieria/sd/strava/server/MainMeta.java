package es.deusto.ingenieria.sd.strava.server;


import es.deusto.ingenieria.sd.strava.server.services.LoginAppService;

public class MainMeta {

	public static void main(String[] args) throws Exception {

		LoginAppService loginService = LoginAppService.getInstance(args);
		
		System.out.println(loginService.check(2, "1234", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220"));
		
	}

}
