package com.acme.bank.loan.registration.service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.bank.loan.registration.domain.entity.RegisterLoanEntity;

@Repository
public interface RegisterLoanRepository extends JpaRepository<RegisterLoanEntity, Long> {

    RegisterLoanEntity findByUuid(UUID uuid);

    List<RegisterLoanEntity> findAllByPersonalId(String personalId);
}
