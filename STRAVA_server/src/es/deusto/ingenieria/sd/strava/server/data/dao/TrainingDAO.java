package es.deusto.ingenieria.sd.strava.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.strava.server.data.domain.Training;


//This class implements Singleton and DAO patterns
public class TrainingDAO extends DataAccessObjectBase implements IDataAccessObject<Training> {

	private static TrainingDAO instance;	
	
	private TrainingDAO() { }
	
	public static TrainingDAO getInstance() {
		if (instance == null) {
			instance = new TrainingDAO();
		}		
		
		return instance;
	}	
	
	@Override
	public void save(Training object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Training object) {
		super.deleteObject(object);
	}

	@Override
	public List<Training> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Training> Trainings = new ArrayList<>();

		try {
			tx.begin();
			
			Extent<Training> TrainingExtent = pm.getExtent(Training.class, true);
			
			for (Training Training : TrainingExtent) {
				Trainings.add(Training);
			}
						
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying all Trainings: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return Trainings;
	}

	@Override
	public Training find(String param) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Training result = null; 

		try {
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + Training.class.getName() + " WHERE title == '" + param + "'");
			query.setUnique(true);
			result = (Training) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Training: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}