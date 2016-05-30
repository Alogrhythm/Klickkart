package com.shop.app.server.businessservice.shoppingcontext;
import com.shop.app.shared.shoppingcontext.FetchItems;
import java.util.List;

public interface FetchItemsBzService {

    public List<FetchItems> executeQueryFetchItems() throws Exception;
}
