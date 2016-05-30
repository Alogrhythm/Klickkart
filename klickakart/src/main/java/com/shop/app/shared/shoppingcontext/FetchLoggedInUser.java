package com.shop.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;

public class FetchLoggedInUser implements DTOMapperInterface {

    private String loginCorecontactsLastname;

    private String firstName;

    private String lastName;

    public FetchLoggedInUser(Object[] aryObject) {
        if (aryObject != null) {
            loginCorecontactsLastname = (java.lang.String) aryObject[0];
            firstName = (java.lang.String) aryObject[1];
            lastName = (java.lang.String) aryObject[2];
        } else {
            loginCorecontactsLastname = null;
            firstName = null;
            lastName = null;
        }
    }

    public String getLoginCorecontactsLastname() {
        return loginCorecontactsLastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
