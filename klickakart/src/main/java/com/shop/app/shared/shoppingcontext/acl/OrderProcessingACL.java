package com.shop.app.shared.shoppingcontext.acl;
import com.shop.app.shared.shoppingcontext.retail.Cart;
import java.util.List;
import com.shop.app.shared.shoppingcontext.retail.OrderMain;

public class OrderProcessingACL {

    public OrderProcessingACL(List<Cart> _cart) {
        this.cartInput = _cart;
        this.doMapping();
    }

    private List<Cart> cartInput;

    private OrderMain ordermainOutput;

    public OrderMain mapCartToOrder() {
        return ordermainOutput;
    }

    public void doMapping() {
        ordermainOutput = new OrderMain();
        java.util.List<com.shop.app.shared.shoppingcontext.retail.OrderDetail> lstorderDetailEntities = new java.util.ArrayList<com.shop.app.shared.shoppingcontext.retail.OrderDetail>();
        for (com.shop.app.shared.shoppingcontext.retail.Cart _orderDetail : cartInput) {
            com.shop.app.shared.shoppingcontext.retail.OrderDetail orderdetail = new com.shop.app.shared.shoppingcontext.retail.OrderDetail();
            orderdetail.setItemId(_orderDetail.getItemID());
            orderdetail.setQty(_orderDetail.getQty());
            orderdetail.setSubTotal(_orderDetail.getSubTotal());
            lstorderDetailEntities.add(orderdetail);
        }
        ordermainOutput.setOrderDetail(lstorderDetailEntities);
    }
}
