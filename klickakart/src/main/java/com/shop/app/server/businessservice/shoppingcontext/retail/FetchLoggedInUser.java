package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.businessservice.shoppingcontext.FetchLoggedInUserBzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FetchLoggedInUser {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchLoggedInUserBzService fetchLoggedInUserBzService;

    public List<com.shop.app.shared.shoppingcontext.FetchLoggedInUser> fetchLoggedInUser() throws Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.FetchLoggedInUser> fetchLoggedInUserList = fetchLoggedInUserBzService.executeQueryFetchLoggedInUser();
        return fetchLoggedInUserList;
    }
}
