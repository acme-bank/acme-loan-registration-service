package com.acme.bank.loan.service.repository;

import com.acme.bank.loan.domain.entity.RegisterLoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegisterLoanRepository extends JpaRepository<RegisterLoanEntity, Long> {

    RegisterLoanEntity findByEventId(UUID eventId);

    List<RegisterLoanEntity> findAllByPersonId(UUID personId);
}
