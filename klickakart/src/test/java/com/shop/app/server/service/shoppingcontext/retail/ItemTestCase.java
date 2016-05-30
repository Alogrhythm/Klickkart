package com.shop.app.server.service.shoppingcontext.retail;
import com.shop.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.shop.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.shop.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.shop.app.shared.shoppingcontext.retail.Item;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.shop.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.shop.app.shared.shoppingcontext.retail.Product;
import com.shop.app.server.repository.shoppingcontext.retail.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class ItemTestCase extends EntityTestCriteria {

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Item createItem(Boolean isSave) throws Exception {
        Product product = new Product();
        product.setProductDesc("bmPQ5icqnbx62J3jQ17LAGe4GZbmY3cXD9ke76xzwBHBHrivcd");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        Item item = new Item();
        item.setItemImg("x6WRFYIAKn6YiNZREdmBcSZrtnDCv5h6mZqDUK4bP78UqFQNpR");
        item.setProductId((java.lang.String) ProductTest._getPrimarykey());
        item.setItemPrice(5905.52d);
        item.setItemName("1YFe6EvzlvVWO2jquZdyI3XaPDPiotqY5wHMAnKasWpyxEGtPN");
        item.setItemStock(1085393314);
        item.setEntityValidator(entityValidator);
        return item;
    }

    @Test
    public void test1Save() {
        try {
            Item item = createItem(true);
            item.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            item.isValid();
            itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private ProductRepository<Product> productRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("ItemPrimaryKey"));
            Item item = itemRepository.findById((java.lang.String) map.get("ItemPrimaryKey"));
            item.setItemImg("5hK8g1LUQaxs0YOPgay56yJymcn8rC0DhhH4nZAugKtKR8Gjd2");
            item.setItemPrice(6760.13d);
            item.setVersionId(1);
            item.setItemName("O0Im2qgI2irXyyEGRvevBGrscBLjutoYYu62HnTIaw113tOLBP");
            item.setItemStock(1637983137);
            item.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            itemRepository.update(item);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByproductId() {
        try {
            java.util.List<Item> listofproductId = itemRepository.findByProductId((java.lang.String) map.get("ProductPrimaryKey"));
            if (listofproductId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("ItemPrimaryKey"));
            itemRepository.findById((java.lang.String) map.get("ItemPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("ItemPrimaryKey"));
            itemRepository.delete((java.lang.String) map.get("ItemPrimaryKey")); /* Deleting refrenced data */
            productRepository.delete((java.lang.String) map.get("ProductPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateItem(EntityTestCriteria contraints, Item item) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            item.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            item.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            item.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            itemRepository.save(item);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "itemName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "itemName", "wDWwWQlVNBjx1ttBzdAahLESIWaLJ6h0vDKBX0YfudXbO6RdOWoSpj5EITZt90f0glftaK38fr7AdZI4DHmWz79gMTNcYxlkNmidvzjZl03GcCn2NbrRpDmmXcxxzVV2yv496CWb0bcy5LqlTjzEKhiusGQfTnSNFF2WwYJ65PpXnvRuaxtKBwtAgk9Am6jBzawTcUgkVz1qgPszjNeoAHawJPQC54aTcmdJcmJTgRL4OtEJ5MsuasXhom3w7vPBD"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "itemImg", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "itemImg", "T8IDYIytlPOztjeP82NBFSrulL2w02QRr7IcbX0uXSlvQJTHeNVG7WT18WS3TDbDMp8jBjH04VgLpvim9j5ZQ1JkEQOxGPOThd5NsYSTkeGRi5Xj19ZrF645eBJQKngE7D5Nzd5LCpy7fQ8XVWKXtrAL8Sl0sZMtAEG9OAkrERt3C4rRTkPDDKAgoGCDEhm9UbtfPxq5BeaiYLGZWwDljnLIMAx3OZYl6UfZPpr0KKYwKBRETRRm98TaOk9BJFewf"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "itemStock", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "itemPrice", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "itemPrice", 1.8425009658293E11d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Item item = createItem(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = item.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 2:
                        item.setItemName(contraints.getNegativeValue().toString());
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 4:
                        item.setItemImg(contraints.getNegativeValue().toString());
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 7:
                        item.setItemPrice(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
