package com.shop.app.server.service.appbasicsetup.userrolemanagement;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.shop.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import java.util.List;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Service for AppMenus Master table Entity", complexity = Complexity.LOW)
public abstract class AppMenusService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(AppMenus entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<AppMenus> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(AppMenus entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<AppMenus> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
