package com.shop.app.server.repository.shoppingcontext.retail;
import com.shop.app.server.repository.core.SearchInterfaceImpl;
import com.shop.app.shared.shoppingcontext.retail.OrderMain;
import org.springframework.stereotype.Repository;
import com.shop.app.config.annotation.Complexity;
import com.shop.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.shop.app.shared.shoppingcontext.retail.OrderDetail;

@Repository
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "12", comments = "Repository for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public class OrderMainRepositoryImpl extends SearchInterfaceImpl implements OrderMainRepository<OrderMain> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<OrderMain> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.shop.app.shared.shoppingcontext.retail.OrderMain> query = emanager.createQuery("select u from OrderMain u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public OrderMain save(OrderMain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SHPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<OrderMain> save(List<OrderMain> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.shop.app.shared.shoppingcontext.retail.OrderMain obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SHPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.shop.app.shared.shoppingcontext.retail.OrderMain s = emanager.find(com.shop.app.shared.shoppingcontext.retail.OrderMain.class, id);
        emanager.remove(s);
        Log.out.println("SHPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteOrderDetail(List<OrderDetail> orderdetail) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.shop.app.shared.shoppingcontext.retail.OrderDetail _orderdetail : orderdetail) {
            com.shop.app.shared.shoppingcontext.retail.OrderDetail s = emanager.find(com.shop.app.shared.shoppingcontext.retail.OrderDetail.class, _orderdetail.getDetId());
            emanager.remove(s);
        }
    }

    @Override
    @Transactional
    public void update(OrderMain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SHPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<OrderMain> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.shop.app.shared.shoppingcontext.retail.OrderMain obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SHPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<OrderMain> findByUserID(String userID) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("OrderMain.findByUserID");
        query.setParameter("userID", userID);
        java.util.List<com.shop.app.shared.shoppingcontext.retail.OrderMain> listOfOrderMain = query.getResultList();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findByUserID", "Total Records Fetched = " + listOfOrderMain.size());
        return listOfOrderMain;
    }

    @Transactional
    public OrderMain findById(String orderId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("OrderMain.findById");
        query.setParameter("orderId", orderId);
        com.shop.app.shared.shoppingcontext.retail.OrderMain listOfOrderMain = (com.shop.app.shared.shoppingcontext.retail.OrderMain) query.getSingleResult();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findById", "Total Records Fetched = " + listOfOrderMain);
        return listOfOrderMain;
    }
}
