package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;

@Controller
public class UniversiteRestController {

    @Autowired
    IUniversiteService universiteService;

    @GetMapping("/universite")
    public String showUniversiteList(Model model) {
        List<Universite> listUniversite = universiteService.retrieveAllUniversites();
        model.addAttribute("listUniversite", listUniversite);

        return "Universite/universite";
    }

    @GetMapping("/universite/{universite-id}")
    public String retrieveUniversite(@PathVariable("universite-id") Long idUniversite, Model model) {
        Universite universite = universiteService.retrieveUniversite(idUniversite);
        model.addAttribute("universite", universite);
        return "Universite/detail";
    }

    @GetMapping("/universite/add")
    public String showNewForm(Model model) {
        model.addAttribute("universite", new Universite());
        return "Universite/add";
    }

    @PostMapping("/universite/save")
    public String saveUniversite(Universite universite) {
        universiteService.save(universite);
        return "redirect:/universite";
    }


    @GetMapping("/universite/edit/{id}")
    public String showEditForm(@PathVariable("id") Long idUniversite, Model model) {
        Universite universite = universiteService.retrieveUniversite(idUniversite);
        model.addAttribute("universite", universite);

        return "/Universite/edit";
    }

    // Handle the form submission for updating a Chambre
    @PostMapping("/universite/edit/{id}")
    public String updateUniversite(@PathVariable("id") Long idUniversite, @ModelAttribute("universite") Universite universite) {
        universite.setIdUniversite(idUniversite);
        universiteService.save(universite);
        return "redirect:/universite";
    }

    @GetMapping("/universite/delete/{universite-id}")
    public String deleteUniversite(@PathVariable("universite-id") Long idUniversite) {
        universiteService.removeUniversite(idUniversite);
        return "redirect:/universite";
    }




























    // http://localhost:8089/tpfoyer/universite/retrieve-all-universites
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        return listUniversites;
    }
    // http://localhost:8089/tpfoyer/universite/retrieve-universite/8
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        Universite universite = universiteService.retrieveUniversite(uId);
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/add-universite
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.addUniversite(u);
        return universite;
    }

    // http://localhost:8089/tpfoyer/universite/remove-universite/{universite-id}
    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        universiteService.removeUniversite(uId);
    }

    // http://localhost:8089/tpfoyer/universite/modify-universite
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        Universite universite = universiteService.modifyUniversite(u);
        return universite;
    }

}
