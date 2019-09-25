package com.hsbc.repository;


import com.hsbc.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByCustId(Long custId);
}
