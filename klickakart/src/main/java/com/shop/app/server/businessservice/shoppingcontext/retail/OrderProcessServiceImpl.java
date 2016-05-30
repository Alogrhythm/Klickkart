package com.shop.app.server.businessservice.shoppingcontext.retail;
import org.springframework.stereotype.Service;
import com.athena.deo.camel.utility.ExternalIntegrationCaller;
import org.springframework.beans.factory.annotation.Autowired;
import com.shop.app.shared.shoppingcontext.retail.PaymentDetails;
import com.shop.app.shared.shoppingcontext.retail.TransactionResponse;

@Service
public class OrderProcessServiceImpl {

    @Autowired
    private ExternalIntegrationCaller externalIntegrationCaller;

    public TransactionResponse processOrder(PaymentDetails paymentDetails) throws Exception {
        try {
            java.util.HashMap map = new java.util.HashMap();
            map.put("paymentDetails", paymentDetails);
            com.shop.app.shared.shoppingcontext.retail.TransactionResponse transactionresponse = (com.shop.app.shared.shoppingcontext.retail.TransactionResponse) externalIntegrationCaller.executeRoute("033CEF27-B2BE-48EC-B98E-C136DD419ED1", map);
            return transactionresponse;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
