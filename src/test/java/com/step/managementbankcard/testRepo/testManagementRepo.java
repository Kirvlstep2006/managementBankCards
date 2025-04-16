package com.step.managementbankcard.testRepo;

import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.repository.ManagementRepo;
import com.step.managementbankcard.service.CardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class testManagementRepo {

    @Autowired
    ManagementRepo managementRepo;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void testSave(){
        BankCard bankCard = new BankCard();
        BankCard saved = managementRepo.save(bankCard);
        testEntityManager.find(BankCard.class, saved.getId_cards());
        Assertions.assertNotNull(bankCard.getId_cards());
    }
}
