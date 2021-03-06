package com.shop.app.server.repository.appbasicsetup.usermanagement;
import com.shop.app.server.repository.core.SearchInterface;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Repository for Login Transaction table", complexity = Complexity.MEDIUM)
public interface LoginRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByContactId(String contactId) throws Exception;

    public List<T> findByUserId(String userId) throws Exception;

    public T findById(String loginPk) throws Exception;

    public List<T> FindUnMappedUser() throws Exception;

    public List<T> FindMappedUser() throws Exception;
}
