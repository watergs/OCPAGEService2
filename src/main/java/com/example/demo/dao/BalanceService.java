package com.example.demo.dao;

import com.example.demo.entity.Balance;
import com.example.demo.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public Balance getBalanceByUserId(Long userId) {
        boolean exists = balanceRepository.existsByUserId(userId);
        if(exists) {
            return balanceRepository.findByUserId(userId);
        } else {
            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setAmount((double) 0);
            return saveBalance(balance);
        }
    }

    public Balance saveBalance(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Transactional
    public Balance updateBalance(Long userId, Double amount) {
        Balance balance = getBalanceByUserId(userId);
        balanceRepository.updateAmountByUserId(amount, userId);
        return getBalanceByUserId(userId);
    }
}
