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
import com.shop.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.shop.app.shared.shoppingcontext.retail.OrderMain;
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
import com.shop.app.shared.appbasicsetup.usermanagement.User;
import com.shop.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.shop.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.shop.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.shop.app.shared.appbasicsetup.usermanagement.Question;
import com.shop.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.UserData;
import com.shop.app.shared.shoppingcontext.retail.OrderDetail;
import com.shop.app.shared.shoppingcontext.retail.Item;
import com.shop.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.shop.app.shared.shoppingcontext.retail.Product;
import com.shop.app.server.repository.shoppingcontext.retail.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class OrderMainTestCase extends EntityTestCriteria {

    @Autowired
    private OrderMainRepository<OrderMain> ordermainRepository;

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

    private OrderMain createOrderMain(Boolean isSave) throws Exception {
        User user = new User();
        user.setIsDeleted(1);
        user.setSessionTimeout(1256);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("T3zDQaROlrAyKoTf8zpocgmdTQcOvvpeUXjHnoEHzOn7ZTOb0Z");
        useraccessdomain.setDomainHelp("qpyauiTrg1ZqnAxMIdbmQCW4ElDQoym0eV8oOwNAeqeOyEY6Lu");
        useraccessdomain.setDomainName("KSnN8gkioeVXkhOGx8067ByHNmmWZqvbbOUiJmXE7oXfOABv87");
        useraccessdomain.setDomainDescription("IHhtIbXFBaHJeUHXOqGpeOdiaG9PbtPZFYHDgSQyQViX3sHibk");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("ttSLVvHXhM9cPIpY3Rts6kBZaAh0Oadnpszl1Yw2wGQUOXwFo8");
        useraccesslevel.setLevelHelp("PjwU5DU9PYPLG9qWeLKsgcW1QaQ7WC3kxqaRFqHzuc4OeHZQwQ");
        useraccesslevel.setLevelIcon("LVuGGXVpJgUJbTRHgfFZPvABPf9RMFx9qGsY3lfneReChOJNH9");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("uB9Y5iCTuNGjWi6v4cM0ftEdita8W77NVKVWJERv2ZHJYwli82");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setSessionTimeout(2777);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(33657);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("Z6eroDKGbBNmV9LkCxR04sBcspLd7eSY4reMrRIlVg9s7pyVXb");
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464603017727l));
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464603017727l));
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("0oEZGA4oFtaIcbw2CLiYMXizvVHJboPeN4NmR64EKC3F2xxR3E");
        question.setQuestion("G1h6039GiYWYSU2QE4b8pCiz8cFpqhFKFTfcU8Qp6TieImvdkM");
        question.setLevelid(1);
        question.setQuestionDetails("7EMIeXyITx");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("tB6lzSQhYCKmfRm2lheUqBJoetXOyXnFF2hEIvm0lCCOy5AthP");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("b3YK9Pp30WgaruiqrVu8p3PQF9yia3wp");
        userdata.setLast5Passwords("Gdba3u3EqNhkld9cRAhjNzUhyrftWkKpqS0MG3sFDyKcejA3IV");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464603017913l));
        userdata.setOneTimePasswordExpiry(11);
        userdata.setPassword("Nflu1Fqi2bulUnhe2P2eDIe77gVbz39XIYP8Iy4i2vttZuquHm");
        userdata.setOneTimePassword("PyhIOQ9b86YNtHCN90ryGbn0tmziu1ne");
        userdata.setLast5Passwords("30bvUnJOs6Vfi86ZBRITboQTIwqSrOsIOnHR9TE4LCfnpFe5BH");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464603017921l));
        userdata.setOneTimePasswordExpiry(6);
        userdata.setPassword("gB4skUwOMfgbXBqOHw9R8JhK9BxW2PFMy2Iw9s1WDLsYW2dWdI");
        userdata.setUser(user);
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        OrderMain ordermain = new OrderMain();
        ordermain.setUserID((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        ordermain.setGrandTotal(300.0d);
        ordermain.setOrderDate(new java.sql.Timestamp(1464603017984l));
        java.util.List<OrderDetail> listOfOrderDetail = new java.util.ArrayList<OrderDetail>();
        OrderDetail orderdetail = new OrderDetail();
        orderdetail.setSubTotal(9100.0d);
        orderdetail.setQty(2147483647);
        Item item = new Item();
        item.setItemImg("3iXFHkNnK4N965zCKkKDh8qm2qV0QH5noeedfrPtbfQcoALXCq");
        Product product = new Product();
        product.setProductDesc("bvlzds3p8lSkuDu3hHARkTU0IUY3MGZHbRqTuL2ZenMeSAQoWG");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        item.setItemImg("WqNBHWDwFvThQLBTpAiH3ZFWq17cwd92HdRWYPS5FWuWfLLeqh");
        item.setProductId((java.lang.String) ProductTest._getPrimarykey()); /* ******Adding refrenced table data */
        item.setItemPrice(1284.63d);
        item.setItemName("FmdyNGTRfsti3alBhBVkQjZJ1SwKAnT4vk05sYOB8u027KNyKj");
        item.setItemStock(223574147);
        Item ItemTest = new Item();
        if (isSave) {
            ItemTest = itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        }
        orderdetail.setSubTotal(-830.0d);
        orderdetail.setQty(2147483647);
        orderdetail.setItemId((java.lang.String) ItemTest._getPrimarykey());
        orderdetail.setOrderMain(ordermain);
        listOfOrderDetail.add(orderdetail);
        ordermain.addAllOrderDetail(listOfOrderDetail);
        ordermain.setEntityValidator(entityValidator);
        return ordermain;
    }

    @Test
    public void test1Save() {
        try {
            OrderMain ordermain = createOrderMain(true);
            ordermain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            ordermain.isValid();
            ordermainRepository.save(ordermain);
            map.put("OrderMainPrimaryKey", ordermain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private ProductRepository<Product> productRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("OrderMainPrimaryKey"));
            OrderMain ordermain = ordermainRepository.findById((java.lang.String) map.get("OrderMainPrimaryKey"));
            ordermain.setVersionId(1);
            ordermain.setGrandTotal(4100.0d);
            ordermain.setOrderDate(new java.sql.Timestamp(1464603018148l));
            ordermain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            ordermainRepository.update(ordermain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserID() {
        try {
            java.util.List<OrderMain> listofuserID = ordermainRepository.findByUserID((java.lang.String) map.get("UserPrimaryKey"));
            if (listofuserID.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("OrderMainPrimaryKey"));
            ordermainRepository.findById((java.lang.String) map.get("OrderMainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("OrderMainPrimaryKey"));
            ordermainRepository.delete((java.lang.String) map.get("OrderMainPrimaryKey")); /* Deleting refrenced data */
            itemRepository.delete((java.lang.String) map.get("ItemPrimaryKey")); /* Deleting refrenced data */
            productRepository.delete((java.lang.String) map.get("ProductPrimaryKey")); /* Deleting refrenced data */
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateOrderMain(EntityTestCriteria contraints, OrderMain ordermain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            ordermain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            ordermain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            ordermain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            ordermainRepository.save(ordermain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "grandTotal", 1.4045381144936065E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                OrderMain ordermain = createOrderMain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = ordermain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        ordermain.setGrandTotal(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateOrderMain(contraints, ordermain);
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
