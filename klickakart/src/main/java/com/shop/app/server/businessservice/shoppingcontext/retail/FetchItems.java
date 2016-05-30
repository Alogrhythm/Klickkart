package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.businessservice.shoppingcontext.FetchItemsBzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FetchItems {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchItemsBzService fetchItemsBzService;

    public List<com.shop.app.shared.shoppingcontext.FetchItems> fetchItems() throws Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.FetchItems> fetchItemsList = fetchItemsBzService.executeQueryFetchItems();
        return fetchItemsList;
    }
}
