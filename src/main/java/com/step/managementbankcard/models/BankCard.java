package com.step.managementbankcard.models;

import com.step.managementbankcard.config.CryptoConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "bankcards")
public class BankCard  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cards;
    @Convert(converter = CryptoConverter.class)
    private String card_number;
    private String card_holder;
    private LocalDate expiry_date;
    private String status;
    private Double balance;
    private String transaction_history;



    public Long getId_cards() {
        return id_cards;
    }

    public void setId_cards(Long id_cards) {
        this.id_cards = id_cards;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getTransaction_history() {
        return transaction_history;
    }

    public void setTransaction_history(String transaction_history) {
        this.transaction_history = transaction_history;
    }


}
