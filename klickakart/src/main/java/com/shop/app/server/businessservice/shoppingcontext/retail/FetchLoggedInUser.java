package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.shop.app.server.businessservice.shoppingcontext.FetchLoggedInUserBzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;

@Component
public class FetchLoggedInUser {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchLoggedInUserBzService fetchLoggedInUserBzService;

    public com.shop.app.shared.shoppingcontext.FetchLoggedInUser fetchLoggedInUser() throws BusinessRuleUnableToProceedException, Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.FetchLoggedInUser> fetchLoggedInUserList = fetchLoggedInUserBzService.executeQueryFetchLoggedInUser();
        for (com.shop.app.shared.shoppingcontext.FetchLoggedInUser fetchLoggedInUserListElement : fetchLoggedInUserList) {
            return fetchLoggedInUserListElement;
        }
        throw new com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException();
    }
}
