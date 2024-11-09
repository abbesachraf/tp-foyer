package tn.esprit.tpfoyer.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;

@Controller
public class BlocRestController {

    @Autowired
    IBlocService blocService;

    @GetMapping("/bloc")
    public String showBlocList(Model model) {
        List<Bloc> listBloc = blocService.retrieveAllBlocs();
        model.addAttribute("listBloc", listBloc);

        return "Bloc/bloc";
    }

    @GetMapping("/bloc/{bloc-id}")
    public String retrieveBloc(@PathVariable("bloc-id") Long bId, Model model) {
        Bloc bloc = blocService.retrieveBloc(bId);
        model.addAttribute("bloc", bloc);
        return "Bloc/detail"; // This should match the name of your details view template
    }

    @GetMapping("/bloc/add")
    public String showNewForm(Model model) {
        model.addAttribute("bloc", new Bloc());
        return "Bloc/add";
    }

    @PostMapping("/bloc/save")
    public String saveBloc(Bloc bloc) {
        blocService.save(bloc);
        return "redirect:/bloc";
    }


    @GetMapping("/bloc/edit/{id}")
    public String showEditForm(@PathVariable("id") Long idBloc, Model model) {
            Bloc bloc = blocService.retrieveBloc(idBloc);
            model.addAttribute("bloc", bloc);
            return "Bloc/edit";
    }

    // Handle the form submission for updating a Bloc
    @PostMapping("/bloc/edit/{id}")
    public String updateBloc(@PathVariable("id") Long idBloc, @ModelAttribute("bloc") Bloc bloc) {
        bloc.setIdBloc(idBloc);
        blocService.save(bloc);
        return "redirect:/bloc";
    }

    @GetMapping("/bloc/delete/{bloc-id}")
    public String deleteBloc(@PathVariable("bloc-id") Long idBloc) {
            blocService.removeBloc(idBloc);
        return "redirect:/bloc";
    }

}
