package com.shop.app.server.service.organization.locationmanagement;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.shop.app.shared.organization.locationmanagement.Language;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Service for Language Master table Entity", complexity = Complexity.LOW)
public abstract class LanguageService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Language entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Language> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Language entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Language> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
