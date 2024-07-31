package com.example.demo.repository;

import com.example.demo.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    @Query("select (count(b) > 0) from Balance b where b.userId = ?1")
    boolean existsByUserId(Long userId);
    @Transactional
    @Modifying
    @Query("update Balance b set b.amount = ?1 where b.userId = ?2")
    void updateAmountByUserId(Double amount, Long userId);

    Balance findByUserId(Long userId);
}
