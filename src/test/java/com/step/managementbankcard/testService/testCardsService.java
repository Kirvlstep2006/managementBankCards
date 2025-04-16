package com.step.managementbankcard.testService;

import com.step.managementbankcard.models.BankCard;
import com.step.managementbankcard.repository.ManagementRepo;
import com.step.managementbankcard.service.CardsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class testCardsService {

    @Mock
    ManagementRepo managementRepo;

    @InjectMocks
    private CardsService cardsService;

    private BankCard bankCard = new BankCard();


    @Test
    void testAddBankCard(){
            when(managementRepo.save(bankCard)).thenReturn(bankCard);

            BankCard result = cardsService.getAddBankCard(bankCard);

            assertEquals(bankCard, result);
            verify(managementRepo, times(1)).save(bankCard);
        }

    @Test
    void testDeleteBankCard(){
        // Мок не возвращает значение (void-метод)
        doNothing().when(managementRepo).deleteByBalance(3200.0);

        cardsService.getDeleteBankCard(3200.0);

        verify(managementRepo, times(1)).deleteByBalance(3200.0);
    }

    @Test
    void testUpdateBankCard(){
        doNothing().when(managementRepo).updateByBalance(1L, 1500.0);

        cardsService.getUpdateBankCard(1L, 1500.0);

        verify(managementRepo, times(1)).updateByBalance(1L, 1500.0);
    }

    @Test
    void testAllTransfer(){
        when(managementRepo.findByCardNumber("3607050000000000")).thenReturn(bankCard);
        when(managementRepo.findByCardNumber("3607050000000001")).thenReturn(bankCard);

        cardsService.getAllTransfer("3607050000000000", "3607050000000001", 200.0);

        assertEquals(800.0, bankCard.getBalance());
        assertEquals(700.0, bankCard.getBalance());
        verify(managementRepo, times(2)).save(any(BankCard.class));
    }
}
