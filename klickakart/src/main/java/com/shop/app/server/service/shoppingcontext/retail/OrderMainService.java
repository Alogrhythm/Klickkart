package com.shop.app.server.service.shoppingcontext.retail;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.shop.app.shared.shoppingcontext.retail.OrderMain;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "12", comments = "Service for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public abstract class OrderMainService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(OrderMain entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<OrderMain> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(OrderMain entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<OrderMain> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByUserID(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
