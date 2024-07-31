package com.example.demo.controller;

import com.example.demo.dao.BalanceService;
import com.example.demo.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dge/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{userId}")
    public ResponseEntity<Balance> getUserBalance(@PathVariable Long userId) {
        return new ResponseEntity<>(balanceService.getBalanceByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/{userId}/update")
    public ResponseEntity<Balance> updateUserBalance(@PathVariable Long userId, @RequestBody Balance balance) {
        balance.setUserId(userId);
        Balance savedBalance = balanceService.updateBalance(userId, balance.getAmount());
        return new ResponseEntity<>(savedBalance, HttpStatus.OK);
    }
}
