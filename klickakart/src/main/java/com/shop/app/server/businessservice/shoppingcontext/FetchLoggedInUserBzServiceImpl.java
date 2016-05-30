package com.shop.app.server.businessservice.shoppingcontext;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.shop.app.shared.shoppingcontext.FetchLoggedInUser;
import java.lang.Override;
import java.util.List;

@Component
public class FetchLoggedInUserBzServiceImpl implements FetchLoggedInUserBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<FetchLoggedInUser> executeQueryFetchLoggedInUser() throws Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.FetchLoggedInUser> listDtoInterface = new java.util.ArrayList<com.shop.app.shared.shoppingcontext.FetchLoggedInUser>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "6808B609-C035-4E22-858D-1D9BBAB4FA57");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            atg.taglib.json.util.JSONObject jsonObjectUserId1 = new atg.taglib.json.util.JSONObject();
            jsonObjectUserId1.put("name", "userId");
            jsonObjectUserId1.put("value", "");
            jsonObjectUserId1.put("datatype", "VARCHAR");
            jsonObjectUserId1.put("index", 1);
            jsonObjectUserId1.put("sessionInput", true);
            jsonObjectUserId1.put("attributeName", "userId");
            jsonObjectUserId1.put("attributeDatatype", "java.lang.String");
            jsonArray.add(jsonObjectUserId1);
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.shop.app.shared.shoppingcontext.FetchLoggedInUser", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
