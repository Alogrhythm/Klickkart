package com.shop.app.server.service.appbasicsetup.usermanagement;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.shop.app.shared.appbasicsetup.usermanagement.Question;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Service for Question Master table Entity", complexity = Complexity.LOW)
public abstract class QuestionService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Question entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Question> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Question entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Question> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
