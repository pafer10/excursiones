package excursionweb.modelo.dao;

import java.util.List;

import excursionweb.modelo.javabean.Excursion;

public interface ExcursionDao {
	
	Excursion findById (int idExcursion); 
	List<Excursion> findAll();
	int insertOne (Excursion excursion);
	int updateOne (Excursion excursion);
	List<Excursion> findByCancelados();
	List<Excursion> findByDestacados();
	List<Excursion> findByActivos();
	List<Excursion> findByTerminados();
}
