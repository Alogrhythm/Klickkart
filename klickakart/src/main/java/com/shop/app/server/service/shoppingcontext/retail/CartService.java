package com.shop.app.server.service.shoppingcontext.retail;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.shop.app.shared.shoppingcontext.retail.Cart;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for Cart Transaction table", complexity = Complexity.MEDIUM)
public abstract class CartService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Cart entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Cart> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Cart entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Cart> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByItemID(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByUserId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
