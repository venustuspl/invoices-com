package pl.venustus.invoicecom.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class StripeConfig {
    @Value("${stripe.api.public.key}")
    private String stripePublicKey;

    @Value("${stripe.api.secret.key}")
    private String stripePrivateKey;
}
