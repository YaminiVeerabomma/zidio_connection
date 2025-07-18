package com.example.repository;

import com.example.entity.Invoice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	List<Invoice> findByUserEmail(String userEmail);
	
	
}

