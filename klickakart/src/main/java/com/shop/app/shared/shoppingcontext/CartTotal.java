package com.shop.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;

public class CartTotal implements DTOMapperInterface {

    private Double cartSubtotal;

    public CartTotal(Object obj) {
        if (obj != null) {
            cartSubtotal = (java.lang.Double) obj;
        } else {
            cartSubtotal = null;
        }
    }

    public Double getCartSubtotal() {
        return cartSubtotal;
    }
}
