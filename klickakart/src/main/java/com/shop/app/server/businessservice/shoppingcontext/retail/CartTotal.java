package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.businessservice.shoppingcontext.CartTotalBzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CartTotal {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CartTotalBzService cartTotalBzService;

    public List<com.shop.app.shared.shoppingcontext.CartTotal> cartTotal() throws Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.CartTotal> cartTotalList = cartTotalBzService.executeQueryCartTotal();
        return cartTotalList;
    }
}
