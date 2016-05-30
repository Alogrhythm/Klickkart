package com.shop.app.server.businessservice.shoppingcontext;
import com.shop.app.shared.shoppingcontext.FetchLoggedInUser;
import java.util.List;

public interface FetchLoggedInUserBzService {

    public List<FetchLoggedInUser> executeQueryFetchLoggedInUser() throws Exception;
}
