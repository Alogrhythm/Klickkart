package com.shop.app.server.repository.shoppingcontext.retail;
import com.shop.app.server.repository.core.SearchInterface;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for Cart Transaction table", complexity = Complexity.MEDIUM)
public interface CartRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByItemID(String itemID) throws Exception;

    public List<T> findByUserId(String userId) throws Exception;

    public T findById(String cartId) throws Exception;
}
