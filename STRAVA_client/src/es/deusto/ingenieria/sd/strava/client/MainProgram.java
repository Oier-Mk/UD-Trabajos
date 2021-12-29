package es.deusto.ingenieria.sd.strava.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.controller.HomeController;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.gui.HomeWindow;
import es.deusto.ingenieria.sd.strava.client.gui.LoginDialog;
import es.deusto.ingenieria.sd.strava.client.gui.GraphicalWindows.HomeW;
import es.deusto.ingenieria.sd.strava.client.gui.GraphicalWindows.WelcomeW;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.TrainingDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.strava.server.remote.IRemoteFacade;

public class MainProgram {

	public static void main(String[] args) {	
		ServiceLocator serviceLocator = new ServiceLocator();

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);

		LoginController loginController = new LoginController(serviceLocator);
		LoginDialog loginDialog = new LoginDialog(loginController);	

		HomeController homeController = new HomeController(serviceLocator);			
		HomeWindow homeWindow = new HomeWindow(loginDialog,homeController);

		prueba(loginDialog,homeWindow);		
		//prueba2(loginDialog,homeWindow);		

		String mail = null;
		String password = null;

		new WelcomeW(loginDialog,homeWindow,mail,password);


	}
	private static void prueba(LoginDialog loginDialog, HomeWindow homeWindow) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = format.parse ( "2009-12-31" );    
			loginDialog.register(1,"oiermentxaka@opendeusto.es","1234","1234",d,1.1,11,21,31);
			loginDialog.logout();
			System.out.println("------");
			loginDialog.register(1,"mail1@mail.com","1234","1234",d,1.1,11,21,31);
			loginDialog.logout();
			System.out.println("------");
			loginDialog.register(2,"mail2@mail.com","1234","1234",d,1.2,12,22,32);				
			loginDialog.logout();
			System.out.println("------");
			loginDialog.register(3,"mail3@mail.com","1234","1234",d,1.3,13,23,33);	
			loginDialog.logout();
			loginDialog.login(3,"mail3@mail.com","1234");		
			homeWindow.createTrainingSession(loginDialog.getToken(), "session3", "Bicycle" , 10.00, d, 15,30, 40.0);
			loginDialog.logout();

			//Login
			System.out.println("------------------");
			loginDialog.login(1,"oiermentxaka@opendeusto.es","1234");		
			loginDialog.logout();
			System.out.println("------");
			loginDialog.login(2,"oiermentxaka@opendeusto.es","1234");		
			loginDialog.logout();
			System.out.println("------");
			loginDialog.login(3,"oiermentxaka@opendeusto.es","1234");
			loginDialog.logout();
			//Get user
			System.out.println("---getuser---");
			loginDialog.login(1,"oiermentxaka@opendeusto.es","1234");		
			homeWindow.getUser(loginDialog.getToken());
			//Create training session
			System.out.println("---createTrainings---");
			homeWindow.createTrainingSession(loginDialog.getToken(), "session1", "Bicycle" , 10.00, d, 15,30, 40.0);
			homeWindow.createTrainingSession(loginDialog.getToken(), "session2", "Running" , 14.00, d, 15,30, 50.0);
			//Set up challenge
			System.out.println("---createChallenges---");
			homeWindow.setupChallenge(loginDialog.getToken(), "challenge1", d, d, 2.0, 45.0, "Bicycle");
			homeWindow.setupChallenge(loginDialog.getToken(), "challenge2", d, d, 4.0, 40.0, "Running");
			//Get challenges
			System.out.println("---getAllChallenges---");
			for(int i = 0; i<homeWindow.getAllChallenges().size();i++) {
				System.out.println(homeWindow.getAllChallenges().get(i).toString());
			}
			//Get trainings
			System.out.println("---getAllTrainigs---");
			for(int i = 0; i<homeWindow.getAllTrainings().size();i++) {
				System.out.println(homeWindow.getAllTrainings().get(i).toString());
			}	
			//Deslogueo
			loginDialog.logout();
			System.out.println("Salgo de prueba");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void prueba2(LoginDialog loginDialog, HomeWindow homeWindow) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d = format.parse ( "2009-12-31" );    
			loginDialog.register(1,"oiermentxaka@opendeusto.es","1234","1234",d,1.1,11,21,31);
			loginDialog.logout();
			loginDialog.login(2,"oiermentxaka@opendeusto.es","1234");		
			loginDialog.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}