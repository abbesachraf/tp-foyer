package tn.esprit.tpfoyer.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.Date;
import java.util.List;

@Controller
public class ReservationRestController {

    @Autowired
    IReservationService reservationService;

    @GetMapping("/reservation")
    public String showReservationList(Model model) {
        List<Reservation> listReservation = reservationService.retrieveAllReservations();
        model.addAttribute("listReservation", listReservation);

        return "Reservation/reservation";
    }




    @GetMapping("/reservation/{reservation-id}")
    public String retrieveReservation(@PathVariable("reservation-id") String idReservation, Model model) {
        Reservation reservation = reservationService.retrieveReservation(idReservation);
        model.addAttribute("reservation", reservation);
        return "Reservation/detail";
    }

    @GetMapping("/reservation/add")
    public String showNewForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "Reservation/add";
    }

    @PostMapping("/reservation/save")
    public String saveReservation(Reservation reservation) {
        reservationService.save(reservation);
        return "redirect:/reservation";
    }


    @GetMapping("/reservation/edit/{id}")
    public String showEditForm(@PathVariable("id") String  idReservation, Model model) {
        Reservation reservation = reservationService.retrieveReservation(idReservation);
        model.addAttribute("reservation", reservation);

        return "Reservation/edit";
    }

    // Handle the form submission for updating a Reservation
    @PostMapping("/reservation/edit/{id}")
    public String updateReservation(@PathVariable("id") String  idReservation, @ModelAttribute("reservation") Reservation reservation) {
        reservation.setIdReservation(idReservation); // Ensure the correct ID is set
        reservationService.save(reservation); // Assuming there's a save method that updates if the ID exists
        return "redirect:/reservation"; // Redirect to the list or details page
    }

    @GetMapping("/reservation/delete/{reservation-id}")
        public String deleteReservation(@PathVariable("reservation-id") String idReservation) {
        reservationService.removeReservation(idReservation);
        return "redirect:/reservation";
    }






















    // http://localhost:8089/tpfoyer/reservation/retrieve-all-reservations
    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }
    // http://localhost:8089/tpfoyer/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String rId) {
        Reservation reservation = reservationService.retrieveReservation(rId);
        return reservation;
    }


    @GetMapping("/retrieve-reservation-date-status/{d}/{v}")
    public List<Reservation> retrieveReservationParDateEtStatus
            (@PathVariable("d") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d, @PathVariable("v") boolean b) {
        return reservationService.trouverResSelonDateEtStatus(d, b);
    }


    // http://localhost:8089/tpfoyer/reservation/add-reservation
    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.addReservation(r);
        return reservation;
    }

    // http://localhost:8089/tpfoyer/reservation/remove-reservation/{reservation-id}
    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") String rId) {
        reservationService.removeReservation(rId);
    }

    // http://localhost:8089/tpfoyer/reservation/modify-reservation
    @PutMapping("/modify-reservation")
    public Reservation modifyReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.modifyReservation(r);
        return reservation;
    }

}
