package com.shop.app.server.repository.shoppingcontext.retail;
import com.shop.app.server.repository.core.SearchInterface;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.shop.app.shared.shoppingcontext.retail.OrderDetail;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "4", comments = "Repository for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public interface OrderMainRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void deleteOrderDetail(List<OrderDetail> orderdetail);

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByUserID(String userID) throws Exception;

    public T findById(String orderId) throws Exception;
}
