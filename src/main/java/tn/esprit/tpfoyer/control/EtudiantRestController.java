package tn.esprit.tpfoyer.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;


@Controller
public class EtudiantRestController {

    @Autowired
    IEtudiantService etudiantService;


    @GetMapping("/etudiant")
    public String showEtudiantList(Model model) {
        List<Etudiant> listEtudiant = etudiantService.retrieveAllEtudiants();
        model.addAttribute("listEtudiant", listEtudiant);

        return "Etudiant/etudiant";
    }
    @GetMapping("/etudiant/{etudiant-id}")
    public String retrieveEtudiant(@PathVariable("etudiant-id") Long idEtudiant, Model model) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(idEtudiant);
        model.addAttribute("etudiant", etudiant);
        return "Etudiant/detail";
    }

    @GetMapping("/etudiant/add")
    public String showNewForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "Etudiant/add";
    }

    @PostMapping("/etudiant/save")
    public String saveEtudiant(Etudiant etudiant, RedirectAttributes ra) {
        etudiantService.save(etudiant);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/etudiant";
    }


    @GetMapping("/etudiant/edit/{id}")
    public String showEditForm(@PathVariable("id") Long idEtudiant, Model model) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(idEtudiant);
        model.addAttribute("etudiant", etudiant);

        return "Etudiant/edit";
    }

    // Handle the form submission for updating a Chambre
    @PostMapping("/etudiant/edit/{id}")
    public String updateEtudiant(@PathVariable("id") Long idEtudiant, @ModelAttribute("etudiant") Etudiant etudiant) {
        etudiant.setIdEtudiant(idEtudiant);
        etudiantService.save(etudiant);
        return "redirect:/etudiant";
    }

    @GetMapping("/etudiant/delete/{etudiant-id}")
    public String deleteEtudiant(@PathVariable("etudiant-id") Long idEtudiant) {
        etudiantService.removeEtudiant(idEtudiant);
        return "redirect:/etudiant";
    }





























    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }

    @GetMapping("/retrieve-etudiant-cin/{cin}")
    public Etudiant retrieveEtudiantParCin(@PathVariable("cin") Long cin) {
        Etudiant etudiant = etudiantService.recupererEtudiantParCin(cin);
        return etudiant;
    }


    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long chId) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(chId);
        return etudiant;
    }

    // http://localhost:8089/tpfoyer/etudiant/add-etudiant
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant c) {
        Etudiant etudiant = etudiantService.addEtudiant(c);
        return etudiant;
    }

    // http://localhost:8089/tpfoyer/etudiant/remove-etudiant/{etudiant-id}
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long chId) {
        etudiantService.removeEtudiant(chId);
    }

    // http://localhost:8089/tpfoyer/etudiant/modify-etudiant
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant c) {
        Etudiant etudiant = etudiantService.modifyEtudiant(c);
        return etudiant;
    }


}
