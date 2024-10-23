package excursionweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import excursionweb.modelo.dao.ExcursionDao;

@Controller
public class DestacadoController {
	@Autowired
	private ExcursionDao exdao;

	@GetMapping ("/excursion/destacados")
	 	public String destacado(Model model) {
		model.addAttribute("excursiones", exdao.findByDestacados());
		return "infoDestacados";
  }
}
