package pl.venustus.invoicecom.service;

import com.stripe.Stripe;
import com.stripe.model.*;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.InvoiceSendInvoiceParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.venustus.invoicecom.config.StripeConfig;

import java.util.*;

@Service
public class StripeService {
    @Autowired
    StripeConfig stripeConfig;

    public String getInvoices() {
        try {
            Stripe.apiKey = stripeConfig.getStripePrivateKey();
            Map<String, Object> params = new HashMap<>();
            params.put("limit", 3);

            InvoiceCollection invoices = Invoice.list(params);

            return invoices.toString();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public String createInvoice() {
        try {
            Stripe.apiKey = stripeConfig.getStripePrivateKey();

            Map<String, Object> paramsItem = new HashMap<>();
            paramsItem.put("customer", "cus_K3ebwjIxWGqyBN");
            paramsItem.put(
                    "price",
                    "price_1JPANkKij36Zr1mM2P2fCDgg"
            );

            Invoice invoice = Invoice.create(paramsItem);

            return invoice.toString();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
