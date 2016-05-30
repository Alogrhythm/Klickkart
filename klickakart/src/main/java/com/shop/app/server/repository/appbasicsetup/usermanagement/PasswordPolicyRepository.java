package com.shop.app.server.repository.appbasicsetup.usermanagement;
import com.shop.app.server.repository.core.SearchInterface;
import com.spartan.server.password.interfaces.PasswordPolicyRepositoryInterface;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.spartan.server.password.interfaces.PasswordPolicyInterface;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Repository for PasswordPolicy Master table Entity", complexity = Complexity.LOW)
public interface PasswordPolicyRepository<T> extends SearchInterface, PasswordPolicyRepositoryInterface {

    public List<PasswordPolicyInterface> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public T findById(String policyId) throws Exception;
}
