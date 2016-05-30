package com.shop.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;

public class FetchCartItems implements DTOMapperInterface {

    private Integer qty;

    private Double subTotal;

    private String itemName;

    private String itemId;

    private String itemImg;

    private Integer itemStock;

    private Double itemPrice;

    private String productDesc;

    public FetchCartItems(Object[] aryObject) {
        if (aryObject != null) {
            qty = (aryObject[0] == null ? null : new Integer(aryObject[0].toString()));
            subTotal = (java.lang.Double) aryObject[1];
            itemName = (java.lang.String) aryObject[2];
            itemId = (java.lang.String) aryObject[3];
            itemImg = (java.lang.String) aryObject[4];
            itemStock = (aryObject[5] == null ? null : new Integer(aryObject[5].toString()));
            itemPrice = (java.lang.Double) aryObject[6];
            productDesc = (java.lang.String) aryObject[7];
        } else {
            qty = null;
            subTotal = null;
            itemName = null;
            itemId = null;
            itemImg = null;
            itemStock = null;
            itemPrice = null;
            productDesc = null;
        }
    }

    public Integer getQty() {
        return qty;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return itemId;
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

    public String getProductDesc() {
        return productDesc;
    }
}
