package com.shop.app.server.businessservice.shoppingcontext;
import com.shop.app.shared.shoppingcontext.CartTotal;
import java.util.List;

public interface CartTotalBzService {

    public List<CartTotal> executeQueryCartTotal() throws Exception;
}
