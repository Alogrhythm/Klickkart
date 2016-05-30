package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.repository.shoppingcontext.retail.CartRepository;
import com.shop.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.shop.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.shop.app.shared.shoppingcontext.retail.Cart;
import com.shop.app.shared.shoppingcontext.retail.Item;
import com.shop.app.shared.shoppingcontext.retail.OrderMain;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shop.app.customexceptions.InvalidCreditCard;
import com.shop.app.customexceptions.TransactionFailed;
import com.shop.app.shared.shoppingcontext.retail.PaymentDetails;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;

@Component
public class OrderProcessing {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private OrderProcessServiceImpl orderProcessServiceImpl;

    @Autowired
    private OrderMainRepository<OrderMain> orderMainRepository;

    @Autowired
    private SessionDataMgtService sessionService;

    @Autowired
    private CartRepository<Cart> cartRepository;

    public void orderProcessing(PaymentDetails orderProcess) throws TransactionFailed, SessionDataNotFoundException, InvalidCreditCard, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        if (orderProcess != null) {
            if (orderProcess.getCreditCardNo() == null) {
                throw new com.shop.app.customexceptions.InvalidCreditCard("Invalid Credit Card", "SHPRT232102400", null);
            }
            com.shop.app.shared.shoppingcontext.retail.TransactionResponse transactionresponse = orderProcessServiceImpl.processOrder(orderProcess);
            if (transactionresponse != null) {
                if (transactionresponse.getStatus().equals("false")) {
                    throw new com.shop.app.customexceptions.TransactionFailed("Transaction Failed", "SHPRT247103401", null);
                }
                java.util.List<com.shop.app.shared.shoppingcontext.retail.Cart> cartList = cartRepository.findByUserId(userIdFromSession);
                com.shop.app.shared.shoppingcontext.acl.OrderProcessingACL orderProcessingACL = new com.shop.app.shared.shoppingcontext.acl.OrderProcessingACL(cartList);
                com.shop.app.shared.shoppingcontext.retail.OrderMain orderMain = orderMainRepository.save(orderProcessingACL.mapCartToOrder());
                for (com.shop.app.shared.shoppingcontext.retail.Cart cartListElement : cartList) {
                    com.shop.app.shared.shoppingcontext.retail.Item item = itemRepository.findById(cartListElement.getItemID());
                    item.setItemStock(item.getItemStock() - cartListElement.getQty());
                    itemRepository.update(item);
                    cartListElement.getSystemInfo().setActiveStatus(0);
                    cartRepository.update(cartListElement);
                }
                orderMain.setGrandTotal(orderMain.getTotalSubTotal());
                orderMain.setUserID(runtimeLogInfoHelper.getRuntimeLogInfo().getUserId());
                orderMain.setOrderDate(new java.sql.Timestamp(java.lang.System.currentTimeMillis()));
                orderMainRepository.update(orderMain);
            }
        }
    }
}
