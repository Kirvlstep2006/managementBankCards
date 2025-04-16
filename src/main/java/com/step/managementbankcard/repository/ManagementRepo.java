package com.step.managementbankcard.repository;

import com.step.managementbankcard.models.BankCard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagementRepo extends JpaRepository<BankCard, Integer> {

    Optional<BankCard> findByEmail(String email);

    List<BankCard> findAll();

    BankCard save(BankCard bankCard);

    @Transactional
    void deleteByBalance(double balance);

    @Transactional
    @Modifying
    @Query("UPDATE BankCard b set b.balance = :balance where b.id_cards = :id_cards")
    void updateByBalance(Long id_cards, double balance);

    @Query("select b from BankCard b where :card_number = b.card_number")
    BankCard findByCardNumber(String card_number);



}
