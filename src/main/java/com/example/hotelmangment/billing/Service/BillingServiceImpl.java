package com.example.hotelmangment.billing.Service;

import com.example.hotelmangment.billing.Model.Billing;
import com.example.hotelmangment.billing.Model.BillingRepository;
import com.example.hotelmangment.reservation.Model.Reservation;
import com.example.hotelmangment.reservation.Model.ReservationRepository;
import com.example.hotelmangment.reservation.ReservationException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Billing createBilling(Billing billing) {
        Optional<Reservation> reservation = reservationRepository.findById(billing.getReservation().getId());
        if (reservation.isPresent()) {
            return billingRepository.save(billing);
        } else {
            throw new ResourceNotFoundException("Reservation not found with id " + billing.getReservation().getId());
        }
    }

    @Override
    public Billing updateBilling(Long id, Billing billing) {
        Optional<Billing> existingBilling = billingRepository.findById(id);
        if (existingBilling.isPresent()) {
            Optional<Reservation> reservation = reservationRepository.findById(billing.getReservation().getId());
            if (reservation.isPresent()) {
                Billing updatedBilling = existingBilling.get();
                updatedBilling.setId(billing.getId());
                updatedBilling.setAmount(billing.getAmount());
                updatedBilling.setBillingDate(billing.getBillingDate());
                return billingRepository.save(updatedBilling);
            } else {
                throw new ResourceNotFoundException("Reservation not found with id " + billing.getId());
            }
        } else {
            throw new ResourceNotFoundException("Billing not found with id " + id);
        }
    }

    @Override
    public void deleteBilling(Long id) {
        if (billingRepository.existsById(id)) {
            billingRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Billing not found with id " + id);
        }
    }

    @Override
    public Billing getBillingById(Long id) {
        return billingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Billing not found with id " + id));
    }

    @Override
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }
}