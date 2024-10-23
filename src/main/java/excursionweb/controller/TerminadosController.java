package excursionweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import excursionweb.modelo.dao.ExcursionDao;

@Controller
public class TerminadosController {
@Autowired
private ExcursionDao exdao;

@GetMapping ("/excursion/terminados")
 	public String terminado (Model model) {
	model.addAttribute("excursiones", exdao.findByTerminados());
	return "infoTerminados";
  }
}