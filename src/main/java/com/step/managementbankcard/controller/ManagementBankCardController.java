package com.step.managementbankcard.controller;

import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class ManagementBankCardController {

    private CardsService cardsService;
    public ManagementBankCardController(CardsService cardsService) {
        this.cardsService = cardsService;
    }



    @GetMapping("/read")
    public List<BankCard> getAll() {
        return cardsService.getAll();
    }

    @PostMapping("/create")
    public BankCard getAddBankCard(@RequestBody BankCard bankCard) {
        return cardsService.getAddBankCard(bankCard);
    }

    @DeleteMapping("/{balance}")
    public void deleteBankCard(@PathVariable("balance") double balance) {
        cardsService.getDeleteBankCard(balance);
    }

    @PutMapping("/{id_cards}/balance")
    public void updateBankCard(@PathVariable("id_cards") Long id_cards, @RequestParam("balance") double amount) {
        cardsService.getUpdateBankCard(id_cards,amount);
    }

    @PutMapping("/perevod/{source_card_number}/{targret_card_number2}/amount")
    public void getAllTransfer(@PathVariable("source_card_number") String source_card_number,
                               @PathVariable("targret_card_number2") String targret_card_number2,
                               @RequestParam double amount
                               ) {
        if (amount <= 0){
            throw new RuntimeException("Сумма должна быть положительной ");
        }
        cardsService.getAllTransfer(source_card_number,targret_card_number2,amount);
    }


    //    @GetMapping("/test")
//    public String test() {
//        return "работает";
//    }
}
