package com.shop.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;

public class FetchItems implements DTOMapperInterface {

    private String itemName;

    private String itemImg;

    private Integer itemStock;

    private Double itemPrice;

    private String itemId;

    private String productDesc;

    public FetchItems(Object[] aryObject) {
        if (aryObject != null) {
            itemName = (java.lang.String) aryObject[0];
            itemImg = (java.lang.String) aryObject[1];
            itemStock = (aryObject[2] == null ? null : new Integer(aryObject[2].toString()));
            itemPrice = (java.lang.Double) aryObject[3];
            itemId = (java.lang.String) aryObject[4];
            productDesc = (java.lang.String) aryObject[5];
        } else {
            itemName = null;
            itemImg = null;
            itemStock = null;
            itemPrice = null;
            itemId = null;
            productDesc = null;
        }
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImg() {
        return itemImg;
    }

    public Integer getItemStock() {
        return itemStock;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public String getProductDesc() {
        return productDesc;
    }
}
