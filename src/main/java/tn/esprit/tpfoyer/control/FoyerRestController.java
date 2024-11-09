package tn.esprit.tpfoyer.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class FoyerRestController {

    @Autowired
    IFoyerService foyerService;

    @GetMapping("/foyer")
    public String showFoyerList(Model model) {
        List<Foyer> listFoyer = foyerService.retrieveAllFoyers();
        model.addAttribute("listFoyer", listFoyer);

        return "Foyer/foyer";
    }

    @GetMapping("/foyer/{foyer-id}")
    public String retrieveFoyer(@PathVariable("foyer-id") Long idFoyer, Model model) {
        Foyer foyer = foyerService.retrieveFoyer(idFoyer);
        model.addAttribute("foyer", foyer);
        return "foyer/detail";
    }

    @GetMapping("/foyer/add")
    public String showNewForm(Model model) {
        model.addAttribute("foyer", new Foyer());
        return "/foyer/add";
    }

    @PostMapping("/foyer/save")
    public String saveFoyer(Foyer foyer, RedirectAttributes ra) {
        foyerService.save(foyer);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/foyer";
    }


    @GetMapping("/foyer/edit/{id}")
    public String showEditForm(@PathVariable("id") Long idFoyer, Model model) {
        Foyer foyer = foyerService.retrieveFoyer(idFoyer);
        model.addAttribute("foyer", foyer);

        return "/Foyer/edit";
    }

    // Handle the form submission for updating a Chambre
    @PostMapping("/foyer/edit/{id}")
    public String updateFoyer(@PathVariable("id") Long idFoyer, @ModelAttribute("foyer") Foyer foyer) {
        foyer.setIdFoyer(idFoyer);
        foyerService.save(foyer);
        return "redirect:/foyer";
    }

    @GetMapping("/foyer/delete/{foyer-id}")
    public String deleteFoyer(@PathVariable("foyer-id") Long idFoyer) {
        foyerService.removeFoyer(idFoyer);
        return "redirect:/foyer";
    }
































    // http://localhost:8089/tpfoyer/foyer/retrieve-all-foyers
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        List<Foyer> listFoyers = foyerService.retrieveAllFoyers();
        return listFoyers;
    }
    // http://localhost:8089/tpfoyer/foyer/retrieve-foyer/8
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long fId) {
        Foyer foyer = foyerService.retrieveFoyer(fId);
        return foyer;
    }

    // http://localhost:8089/tpfoyer/foyer/add-foyer
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    // http://localhost:8089/tpfoyer/foyer/remove-foyer/{foyer-id}
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long fId) {
        foyerService.removeFoyer(fId);
    }

    // http://localhost:8089/tpfoyer/foyer/modify-foyer
    @PutMapping("/modify-foyer")
    public Foyer modifyFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.modifyFoyer(f);
        return foyer;
    }

}
