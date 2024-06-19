package com.example.hotelmangment.billing.Service;

import com.example.hotelmangment.billing.Model.Billing;

import java.util.List;

public interface BillingService {
    Billing createBilling(Billing billing);
    Billing updateBilling(Long id, Billing billing);
    void deleteBilling(Long id);
    Billing getBillingById(Long id);
    List<Billing> getAllBillings();
}
