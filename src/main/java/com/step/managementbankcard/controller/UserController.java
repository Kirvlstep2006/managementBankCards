package com.step.managementbankcard.controller;

import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.service.CardsService;
import com.step.managementbankcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class UserController {
    @Autowired
    private CardsService cardsService;

    @GetMapping("/allcards")
    public List<BankCard> getAllCards(){
        return cardsService.getAll();
    }

    @PutMapping("/blockcard/cardnumber")
    public void blockCard(@RequestParam("cardnumber") String card_number){
        cardsService.getBlockCard(card_number);
    }
}
