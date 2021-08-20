package pl.venustus.invoicecom.controller;

import com.stripe.model.Invoice;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.venustus.invoicecom.service.StripeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
class StripeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StripeService stripeService;

    @Before
    void setUp() {
        Mockito.reset(stripeService);
    }

    @Test
    void shouldAddInnvoiceWith2BasicParameters() throws Exception {
        //given
        String customer = "testCustomer";
        String price = "testPrice";

        //when
        when(stripeService.createInvoice(any(), any())).thenReturn(String.valueOf(Invoice.class));

        //then
        mockMvc.perform(post("/v1/invoices?{customer}&{price}", customer, price)
                        .param("customer", customer)
                        .param("price", price))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotAddInnvoiceWithout1BasicPriceParameter() throws Exception {
        //given
        String customer = "testCustomer";

        //when
        when(stripeService.createInvoice(any(), any())).thenReturn(String.valueOf(Invoice.class));

        //then
        mockMvc.perform(post("/v1/invoices?{customer}", customer)
                        .param("customer", customer))
                .andExpect(status().isBadRequest());
    }
}