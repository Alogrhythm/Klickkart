package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.businessservice.shoppingcontext.FetchCartItemsBzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FetchCartItems {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchCartItemsBzService fetchCartItemsBzService;

    public List<com.shop.app.shared.shoppingcontext.FetchCartItems> fetchCartItems() throws Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.FetchCartItems> fetchCartItemsList = fetchCartItemsBzService.executeQueryFetchCartItems();
        return fetchCartItemsList;
    }
}
