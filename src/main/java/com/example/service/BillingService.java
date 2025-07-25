package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.InvoiceDTO;
import com.example.entity.Invoice;
import com.example.repository.InvoiceRepository;
@Service
public class BillingService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public Invoice createInvoice(InvoiceDTO dto) {
	    Invoice invoice = new Invoice();
	    invoice.setUserEmail(dto.getUserEmail());
	    invoice.setServiceType(dto.getServiceType());
	    invoice.setAmount(dto.getAmount());
	    invoice.setPaymentMethod(dto.getPaymentMethod());
	    invoice.setStatus(dto.getStatus());
	    invoice.setPurchaseDate(dto.getPurchaseDate());
	    return invoiceRepository.save(invoice);
	}

	public List<Invoice>getInvoice(String userEmail){
		return invoiceRepository.findByUserEmail(userEmail);
	}
		
	}
	
	


