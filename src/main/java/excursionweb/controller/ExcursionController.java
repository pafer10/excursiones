package excursionweb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import excursionweb.modelo.dao.ExcursionDao;
import excursionweb.modelo.javabean.Excursion;

@Controller
@RequestMapping("/excursion")
public class ExcursionController {
@Autowired

private ExcursionDao exdao;
	
	@GetMapping("/cancelar/{idExcursion}")
	public String cambiarEstado(@PathVariable int idExcursion, RedirectAttributes ratt) {
		Excursion excursion = exdao.findById(idExcursion);
		
		if(!"CANCELADO".equals(excursion.getEstado())) {
			excursion.setEstado("CANCELADO");
			exdao.updateOne(excursion);
			ratt.addFlashAttribute("mensaje", "La excursión se ha cancelado");
			return "redirect:/";
		}
		else {
			ratt.addFlashAttribute("mensaje", "La excursión ya estaba cancelada");
		}
		return "redirect:/";
	}
	
	@PostMapping("/alta") 
	public String procFormAlta (Excursion excursion, RedirectAttributes ratt) {
		if (exdao.insertOne(excursion) == 1)
			ratt.addFlashAttribute("mensaje", "Excursión añadida");
		else
			ratt.addFlashAttribute("mensaje", "No se ha podido añadir la excursión");
		return "redirect:/";
	}

	@GetMapping("/alta")
	public String procAlta(Model model) {
			model.addAttribute("excursiones", exdao.findAll());
		return "formAltaEx";
	}
	
	@PostMapping("/modificar/{idExcursion}")
	public String procFormMod(Model model, @PathVariable int idExcursion,
			Excursion excursion, RedirectAttributes ratt) {
		excursion.setIdExcursion(idExcursion);
		
		if (exdao.updateOne(excursion) == 1) {
			ratt.addFlashAttribute("mensaje", "Excursión modificada correctamente");
		}
		else 
			ratt.addFlashAttribute("mensaje", "Excursión NOO modificada correctamente");
		
		return "redirect:/";
	}

	@GetMapping("/modificar/{idExcursion}")
	public String modExcursion (Model model, @PathVariable int idExcursion) {
		Excursion excursion = exdao.findById(idExcursion);
		
		if(excursion == null) {
			model.addAttribute("mensaje", "El producto no existe");
			return "forward:/";
		}
		model.addAttribute("excursion", excursion);
		return "formEdicionExcursion";
	}
	
	@GetMapping("/detalle/{idExcursion}")
	public String detalleExcursion (Model model, @PathVariable int idExcursion){
		
		Excursion excursion = exdao.findById(idExcursion);
		
		model.addAttribute("excursion", excursion);
		return "verDetalle";
	} 
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
