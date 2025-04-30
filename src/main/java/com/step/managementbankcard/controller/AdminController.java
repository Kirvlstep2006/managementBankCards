package com.step.managementbankcard.controller;

import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private CardsService cardsService;

    @PostMapping("/createcard")
    public BankCard createCard(@RequestBody BankCard card) {
        return cardsService.getAddBankCard(card);
    }

    @DeleteMapping("/{balance}")
    public void deleteBankCard(@PathVariable("balance") double balance) {
        cardsService.getDeleteBankCard(balance);
    }

    @GetMapping("/allcards")
    public List<BankCard> getAllCards(){
        return cardsService.getAll();
    }

    @PutMapping("/blockcard/cardnumber")
    public void blockCard(@RequestParam("cardnumber") String card_number){
        cardsService.getBlockCard(card_number);
    }

    @PutMapping("/activatecard/cardnumber")
    public void activateCard(@RequestParam("cardnumber") String card_number){
        cardsService.getActivatecard(card_number);
    }


}
