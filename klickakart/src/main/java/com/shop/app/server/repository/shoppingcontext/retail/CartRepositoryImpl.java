package com.shop.app.server.repository.shoppingcontext.retail;
import com.shop.app.server.repository.core.SearchInterfaceImpl;
import com.shop.app.shared.shoppingcontext.retail.Cart;
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

@Repository
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Repository for Cart Transaction table", complexity = Complexity.MEDIUM)
public class CartRepositoryImpl extends SearchInterfaceImpl implements CartRepository<Cart> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Cart> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.shop.app.shared.shoppingcontext.retail.Cart> query = emanager.createQuery("select u from Cart u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Cart save(Cart entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("SHPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Cart> save(List<Cart> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.shop.app.shared.shoppingcontext.retail.Cart obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("SHPRT322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.shop.app.shared.shoppingcontext.retail.Cart s = emanager.find(com.shop.app.shared.shoppingcontext.retail.Cart.class, id);
        emanager.remove(s);
        Log.out.println("SHPRT328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Cart entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("SHPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Cart> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.shop.app.shared.shoppingcontext.retail.Cart obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("SHPRT321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<Cart> findByItemID(String itemID) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Cart.findByItemID");
        query.setParameter("itemID", itemID);
        java.util.List<com.shop.app.shared.shoppingcontext.retail.Cart> listOfCart = query.getResultList();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findByItemID", "Total Records Fetched = " + listOfCart.size());
        return listOfCart;
    }

    @Transactional
    public List<Cart> findByUserId(String userId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Cart.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.shop.app.shared.shoppingcontext.retail.Cart> listOfCart = query.getResultList();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfCart.size());
        return listOfCart;
    }

    @Transactional
    public Cart findById(String cartId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Cart.findById");
        query.setParameter("cartId", cartId);
        com.shop.app.shared.shoppingcontext.retail.Cart listOfCart = (com.shop.app.shared.shoppingcontext.retail.Cart) query.getSingleResult();
        Log.out.println("SHPRT324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartRepositoryImpl", "findById", "Total Records Fetched = " + listOfCart);
        return listOfCart;
    }
}
