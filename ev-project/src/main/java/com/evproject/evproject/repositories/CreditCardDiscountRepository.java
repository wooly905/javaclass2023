package com.evproject.evproject.repositories;

import com.evproject.evproject.entities.CreditCardDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardDiscountRepository extends JpaRepository<CreditCardDiscount, Long> {

    // 如果有需要可以新增自定義的查詢方法
}