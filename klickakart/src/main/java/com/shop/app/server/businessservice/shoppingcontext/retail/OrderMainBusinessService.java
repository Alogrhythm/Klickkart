package com.shop.app.server.businessservice.shoppingcontext.retail;
import com.shop.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.shop.app.shared.shoppingcontext.retail.OrderMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderMainBusinessService {

    @Autowired
    private OrderMainRepository orderMainRepository;

    public void update(OrderMain entity) throws Exception {
        if (entity.isHardDelete()) {
            orderMainRepository.delete(entity.getOrderId());
        } else {
            orderMainRepository.deleteOrderDetail(entity.getDeletedOrderDetailList());
            orderMainRepository.update(entity);
        }
    }

    public void update(List<OrderMain> entity) throws Exception {
        for (OrderMain _ordermain : entity) {
            if (_ordermain.isHardDelete()) {
                orderMainRepository.delete(_ordermain.getOrderId());
            } else {
                orderMainRepository.deleteOrderDetail(_ordermain.getDeletedOrderDetailList());
                orderMainRepository.update(_ordermain);
            }
        }
    }
}
