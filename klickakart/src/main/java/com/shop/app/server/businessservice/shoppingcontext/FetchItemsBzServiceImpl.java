package com.shop.app.server.businessservice.shoppingcontext;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.shop.app.shared.shoppingcontext.FetchItems;
import java.lang.Override;
import java.util.List;

@Component
public class FetchItemsBzServiceImpl implements FetchItemsBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<FetchItems> executeQueryFetchItems() throws Exception {
        java.util.List<com.shop.app.shared.shoppingcontext.FetchItems> listDtoInterface = new java.util.ArrayList<com.shop.app.shared.shoppingcontext.FetchItems>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "71EBFAA3-A620-45EE-9DBB-C692B7C9C4EB");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.shop.app.shared.shoppingcontext.FetchItems", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
