package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.repository.shoppingcontext.retail.CartRepository;
import com.shop.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.shop.app.shared.shoppingcontext.retail.Cart;
import com.shop.app.shared.shoppingcontext.retail.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shop.app.customexceptions.ItemOutOOfStock;

@Component
public class AddTOCart {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private CartRepository<Cart> cartRepository;

    public void addtoCart(Cart entity) throws ItemOutOOfStock, Exception {
        if (entity != null) {
            com.shop.app.shared.shoppingcontext.retail.Item item = itemRepository.findById(entity.getItemID());
            if (item.getItemStock() < entity.getQty()) {
                throw new com.shop.app.customexceptions.ItemOutOOfStock("Out Of Stock", "SHPRT234101400", null);
            }
            if (item.getItemStock() >= entity.getQty()) {
                entity.setSubTotal(item.getItemPrice() * entity.getQty());
                entity.setUserId(runtimeLogInfoHelper.getRuntimeLogInfo().getUserId());
                com.shop.app.shared.shoppingcontext.retail.Cart cart2 = cartRepository.save(entity);
            }
        }
    }
}
