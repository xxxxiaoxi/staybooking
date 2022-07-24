package com.laioffer.staybooking.controller;

import com.laioffer.staybooking.model.Reservation;
import com.laioffer.staybooking.model.Stay;
import com.laioffer.staybooking.model.User;
import com.laioffer.staybooking.service.ReservationService;
import com.laioffer.staybooking.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
public class StayController {
    private StayService stayService;
    private ReservationService reservationService;

    @Autowired
    public StayController(StayService stayService, ReservationService reservationService) {
        this.stayService = stayService;
        this.reservationService = reservationService;
    }
    //通过url上的键值对 方式传递过来的用RequestParam    eg: stay_id=1&host=sun
    //找到属于一个人的所有房子
    //principal:从security框架中 中获得用户信息
    @GetMapping(value = "/stays")
    public List<Stay> listStays(Principal principal) {

        return stayService.listByUser(principal.getName());
    }
    //找到确切一个房子
    @GetMapping(value = "/stays/{stayId}")
    public Stay getStay(@PathVariable Long stayId, Principal principal) {
        return stayService.findByIdAndHost(stayId, principal.getName());
    }
    //add
    //RequestBody 主要用于post下 convert json to object
    //ResponseBody return data 时， convert object to json (jackson)
    @PostMapping("/stays")
    public void addStay(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("description") String description,
            @RequestParam("guest_number") int guestNumber,
            @RequestParam("images") MultipartFile[] images,
            Principal principal) {

        Stay stay = new Stay.Builder()
                .setName(name)
                .setAddress(address)
                .setDescription(description)
                .setGuestNumber(guestNumber)
                .setHost(new User.Builder().setUsername(principal.getName()).build())
                .build();
        stayService.add(stay, images);
    }
    //delete
    @DeleteMapping("/stays/{stayId}")
    public void deleteStay(@PathVariable Long stayId, Principal principal) {
        stayService.delete(stayId, principal.getName());
    }

    @GetMapping(value = "/stays/reservations/{stayId}")
    public List<Reservation> listReservations(@PathVariable Long stayId) {
        return reservationService.listByStay(stayId);
    }
}