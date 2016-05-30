package com.shop.app.server.service.organization.contactmanagement;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.shop.app.shared.organization.contactmanagement.CommunicationGroup;
import java.util.List;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Service for CommunicationGroup Master table Entity", complexity = Complexity.LOW)
public abstract class CommunicationGroupService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(CommunicationGroup entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<CommunicationGroup> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(CommunicationGroup entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<CommunicationGroup> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
