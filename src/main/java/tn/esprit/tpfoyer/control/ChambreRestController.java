package tn.esprit.tpfoyer.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;
import java.util.List;
import org.springframework.ui.Model;


@Controller
public class ChambreRestController {
    @Autowired
    IChambreService chambreService;

// http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
    @GetMapping("/chambre")
    public String showChambreList(Model model) {
        List<Chambre> listChambre = chambreService.listAll();
        model.addAttribute("listChambre", listChambre);

        return "Chambre/chambre";
    }



    @GetMapping("/chambre/{chambre-id}")
    public String retrieveChambre(@PathVariable("chambre-id") Long idChambre, Model model) {
        Chambre chambre = chambreService.retrieveChambre(idChambre);
        model.addAttribute("chambre", chambre);
        return "Chambre/detail";
    }

    @GetMapping("/chambre/add")
    public String showNewForm(Model model) {
        model.addAttribute("chambre", new Chambre());
        return "/Chambre/add";
    }

    @PostMapping("/chambre/save")
    public String saveChambre(Chambre chambre, RedirectAttributes ra) {
        chambreService.save(chambre);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/chambre";
    }


    @GetMapping("/chambre/edit/{id}")
    public String showEditForm(@PathVariable("id") Long idChambre, Model model) {
        Chambre chambre = chambreService.retrieveChambre(idChambre);
        model.addAttribute("chambre", chambre);
        model.addAttribute("pageTitle", "Edit chambre (ID: " + idChambre + ")");

        return "/Chambre/edit";
    }

    // Handle the form submission for updating a Chambre
    @PostMapping("/chambre/edit/{id}")
    public String updateChambre(@PathVariable("id") Long idChambre, @ModelAttribute("chambre") Chambre chambre) {
        chambre.setIdChambre(idChambre); // Ensure the correct ID is set
        chambreService.save(chambre); // Assuming there's a save method that updates if the ID exists
        return "redirect:/chambre"; // Redirect to the list or details page
    }

    @GetMapping("/chambre/delete/{chambre-id}")
    public String deleteChambre(@PathVariable("chambre-id") Long idChambre) {
        chambreService.removeChambre(idChambre);
        return "redirect:/chambre";
    }





























    // http://localhost:8089/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    // http://localhost:8089/tpfoyer/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }


    @GetMapping("/trouver-chambres-selon-typ/{tc}")
    public List<Chambre> trouverChSelonTC(@PathVariable("tc") TypeChambre tc)
    {
        return chambreService.recupererChambresSelonTyp(tc);
    }

    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/trouver-chambre-selon-etudiant/{cin}")
    public Chambre trouverChSelonEt(@PathVariable("cin") long cin) {
        Chambre chambre = chambreService.trouverchambreSelonEtudiant(cin);
        return chambre;
    }


}
