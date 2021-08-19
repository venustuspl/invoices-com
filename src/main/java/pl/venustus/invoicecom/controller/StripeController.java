package pl.venustus.invoicecom.controller;

import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.venustus.invoicecom.service.StripeService;


@RestController
@RequestMapping(value = "/v1")
public class StripeController {
    @Autowired
    StripeService stripeService;

    @GetMapping(value = "/invoices")
    public String getData(){
        return stripeService.getInvoices();
    }

    @PostMapping (value = "/invoices")
    public String createInvoice(){
        return stripeService.createInvoice();
    }
}