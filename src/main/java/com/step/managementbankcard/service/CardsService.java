package com.step.managementbankcard.service;

import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.repository.ManagementRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CardsService {

    private ManagementRepo managementRepo;
    public CardsService(ManagementRepo managementRepo) {
        this.managementRepo = managementRepo;
    }



    public List<BankCard> getAll() {
        return managementRepo.findAll();
    }

    public BankCard getAddBankCard(BankCard bankCard) {
        return managementRepo.save(bankCard);
    }

    @Transactional
    public void getDeleteBankCard(double balance) {
        managementRepo.deleteByBalance(balance);
    }

    @Transactional
    public void getUpdateBankCard(Long id_cards, double balance) {
         managementRepo.updateByBalance(id_cards, balance);
    }

    public void getAllTransfer(String source_card_number,String targret_card_number2,double amount) {
        BankCard sourceCard = managementRepo.findByCardNumber(source_card_number);
        BankCard targetCard = managementRepo.findByCardNumber(targret_card_number2);



        if (sourceCard == null || targetCard == null) {
            throw new RuntimeException("Первая или второая карта не найдена ");
        }

        if (sourceCard.getCard_number().equals(targetCard.getCard_number()) ) {
            throw new RuntimeException("Ошибка в выборе карты");
        }

        if (sourceCard.getBalance() < 0 ) {
            throw new RuntimeException("Недостаточно средств на карте для перевода");
        }

        sourceCard.setBalance(sourceCard.getBalance() - amount);
        targetCard.setBalance(targetCard.getBalance() + amount);

        managementRepo.save(sourceCard);
        managementRepo.save(targetCard);

    }

    @Transactional
    public void getBlockCard(String card_number){
        managementRepo.findByCardNumber(card_number);
    }

    @Transactional
    public void getActivatecard(String card_number){
        managementRepo.findByCardNumber(card_number);
    }
}
