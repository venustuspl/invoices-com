package pl.venustus.invoicecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.venustus.invoicecom.service.StripeService;


@RestController
@RequestMapping(value = "/v1")
public class StripeController {
    @Autowired
    StripeService stripeService;

    @GetMapping(value = "/invoices")
    public String getData() {
        return stripeService.getInvoices();
    }

    @PostMapping(value = "/invoices")
    public String createInvoice(@RequestParam String customer, @RequestParam String price) {
        return stripeService.createInvoice(customer, price);
    }
}