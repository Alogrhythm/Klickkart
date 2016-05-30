package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.businessservice.shoppingcontext.CartTotalBzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;

@Component
public class CartTotal {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CartTotalBzService cartTotalBzService;

    public com.shop.app.shared.shoppingcontext.CartTotal cartTotal() throws BusinessRuleUnableToProceedException, Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.CartTotal> cartTotalList = cartTotalBzService.executeQueryCartTotal();
        for (com.shop.app.shared.shoppingcontext.CartTotal cartTotalListElement : cartTotalList) {
            return cartTotalListElement;
        }
        throw new com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException();
    }
}
