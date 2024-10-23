package excursionweb.modelo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import excursionweb.modelo.javabean.Excursion;

@Repository
public class ExcursionDaoImplList implements ExcursionDao {

	private List<Excursion> lista;
	public ExcursionDaoImplList() {
		lista = new ArrayList<>();
		cargarDatos();
	}
	
	private void cargarDatos() {
		
		SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy");
		try {
	lista.add(new Excursion(101, "Viaje a Toledo", "Madrid", "Toledo", sdt.parse("15-02-2025"), 4,
			"CREADO", "S", 45, 12, 25, "Foto Catedral", sdt.parse("10-07-2024")));
	lista.add(new Excursion(102, "Viaje a Islas Cíes", "Madrid", "Toledo", sdt.parse("22-02-2025"), 4,
			"CANCELADO", "S", 45, 12, 25, "Foto playas", sdt.parse("10-07-2024")));
	lista.add(new Excursion(103, "Viaje a Salamanca", "Pontevedra", "Toledo", sdt.parse("22-02-2025"), 4,
			"TERMINADO", "N", 45, 12, 25, "Foto Catedral", sdt.parse("10-07-2024")));
	lista.add(new Excursion(104, "Viaje a Soria", "Madrid", "Toledo", sdt.parse("22-02-2025"), 4,
			"CANCELADO", "N", 45, 12, 25, "Foto Catedral", sdt.parse("10-07-2024")));
	lista.add(new Excursion(105, "Viaje a Bilbao", "Barcelona", "Toledo", sdt.parse("22-02-2025"), 4,
			"CREADO", "S", 45, 12, 25, "Foto Catedral", sdt.parse("10-07-2024")));
	lista.add(new Excursion(106, "Viaje a Sevilla", "Madrid", "Toledo", sdt.parse("22-02-2025"), 4,
			"TERMINADO", "S", 45, 12, 25, "Foto Catedral", sdt.parse("10-07-2024")));
	lista.add(new Excursion(107, "Viaje a Cantabria", "Zamora", "Toledo", sdt.parse("22-02-2025"), 4,
			"TERMINADO", "N", 45, 12, 25, "Foto Catedral", sdt.parse("10-07-2024")));
		}catch(ParseException e) {
			e.getMessage();
		}
		}
	
	@Override
	public Excursion findById(int idExcursion) {
		for (Excursion ele: lista) {
			if (ele.getIdExcursion()== idExcursion)
				return ele;
		}
		
		return null;
	}

	@Override
	public List<Excursion> findAll() {
		
		return lista;
	}

	@Override
	public int insertOne(Excursion excursion) {
		if (lista.contains(excursion))
			return 0;
		else 
			
		return lista.add(excursion) ? 1 : 0;
	}

	@Override
	public int updateOne(Excursion excursion) {
		int pos = lista.indexOf(excursion);
		
		if (pos == -1)
		return 0;
		
		return lista.set(pos, excursion) != null ? 1 : 0;
	}

	@Override
	public List<Excursion> findByCancelados() {
List<Excursion> exdes = new ArrayList<>();
		
		for (Excursion ex: lista) {
			if ("CANCELADO".equals(ex.getEstado())) {
				exdes.add(ex);
			}
		}
		return exdes;
	}

	@Override
	public List<Excursion> findByDestacados() {
		List<Excursion> exdes = new ArrayList<>();
		
		for (Excursion ex: lista) {
			if ("S".equals(ex.getDestacado())) {
				exdes.add(ex);
			}
		}
		return exdes;
	}

	@Override
	public List<Excursion> findByActivos() {
		List<Excursion> exdes = new ArrayList<>();
		
		for (Excursion ex: lista) {
			if ("CREADO".equals(ex.getEstado())) {
				exdes.add(ex);
			}
		}
		return exdes;
	}

	@Override
	public List<Excursion> findByTerminados() {
		List<Excursion> exdes = new ArrayList<>();
		
		for (Excursion ex: lista) {
			if ("TERMINADO".equals(ex.getEstado())) {
				exdes.add(ex);
			}
		}
		return exdes;
	}

}
