package pl.venustus.invoicecom.service;

import com.stripe.Stripe;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import com.stripe.model.InvoiceItem;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.InvoiceItemCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.venustus.invoicecom.config.StripeConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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

    public String createInvoice(String customer, String price) {
        try {
            Stripe.apiKey = stripeConfig.getStripePrivateKey();

            InvoiceItemCreateParams invoiceItemParams =
                    InvoiceItemCreateParams.builder()
                            .setCustomer(customer)
                            .setPrice(price)
                            .build();
            InvoiceItem.create(invoiceItemParams);

            InvoiceCreateParams invoiceParams =
                    InvoiceCreateParams.builder()
                            .setCustomer(customer)
                            .setAutoAdvance(true) // auto-finalize this draft after ~1 hour
                            .build();

            return Invoice.create(invoiceParams).toJson();
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
