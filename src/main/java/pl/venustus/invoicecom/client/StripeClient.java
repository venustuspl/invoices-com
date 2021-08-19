package pl.venustus.invoicecom.client;

import com.stripe.net.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.venustus.invoicecom.config.StripeConfig;

import java.net.URI;

@Component
public class StripeClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(StripeClient.class);
}
