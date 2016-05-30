package com.shop.app.server.businessservice.shoppingcontext;
import com.shop.app.shared.shoppingcontext.FetchCartItems;
import java.util.List;

public interface FetchCartItemsBzService {

    public List<FetchCartItems> executeQueryFetchCartItems() throws Exception;
}
