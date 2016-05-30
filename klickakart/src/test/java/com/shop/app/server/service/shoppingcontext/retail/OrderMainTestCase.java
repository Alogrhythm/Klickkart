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
        user.setSessionTimeout(1461);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("9GfGWL6wdGNylSVfzaJjDbqFiwCpkUjtdm162OIODkimTBEK6p");
        useraccessdomain.setDomainHelp("N2Hg8xVaZI5zen3PvFiburkJ7Ujdi7RaBe1wSX1dzrLFHi8G17");
        useraccessdomain.setDomainName("VApUCjSNfb8K6GL7TbAfIGuOMA0MjpekWz7VPEe1UdUFQ1zuzP");
        useraccessdomain.setDomainDescription("2eXRI9Ux7UfyidGCGRkkqT65ye7SQrdbQtdD8txVjTCfNBbITj");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("sijoGJdaPiNxf2h89WHKtAV5JFqq60UbilDoYnhXMSU4uZCfh8");
        useraccesslevel.setLevelHelp("04tCGTJwfd3EOTRCX3YHgQF1zYSut2CGVchPIiM8QC2GRKjOTP");
        useraccesslevel.setLevelIcon("AHE446gfd7sGvb6xjYR6L2n3Rias9JP4h68EWLyOpyItnYCUDu");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("GSG1hfeIpPBsUkNErAYyFTrBWk8Y6dqdTBASmrGSJFRfY2cmUN");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setSessionTimeout(3572);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(42877);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("5s4JAWDYvUjAPpgQ4Izh3mqZ3lDfuV2lLxAysk1x7B3qYajKEy");
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464509946455l));
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464509946455l));
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("mgXYXrNq96owDf5NagLrZ2KxnvbTEemrD1mG9vA5JBb8UWA2YM");
        question.setQuestion("nLXVE7sA5TPA9x6Wk7t3FOVwaDHebZf7WnjOzMNJM0sNe5JMkh");
        question.setLevelid(5);
        question.setQuestionDetails("U69L7M7oif");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("tJIUJ0hPbTbqOPUTgbPSGUhD1epEpXPNGKTIF0jRL60yLeN9mP");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("NKkwp04UHYsKtT9Nn92nYapgxgSpDBHO");
        userdata.setLast5Passwords("JK8FSS3GiPpCuwJkdMynYrgFHl9C6b03QbuRuPo3A7Vf5WlrfL");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464509946636l));
        userdata.setOneTimePasswordExpiry(7);
        userdata.setPassword("EEJyNBMukHkLdrDT01xzqGhLfkWKOMwvhmAkTklGwi6SLNoYb0");
        userdata.setOneTimePassword("S7vIWdPWpvylfSLMDnx2TexvFmxfYGNf");
        userdata.setLast5Passwords("0HnEd2oPtdOCsjzWeZBLm5wer4WEwzeFcC0Xgk0vFruJAmgS8o");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464509946644l));
        userdata.setOneTimePasswordExpiry(11);
        userdata.setPassword("miXaPTypDAOqGpO0YtNMJyAzc6OOSlbSFgPs4yAJs0dzHxM5mr");
        userdata.setUser(user);
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        OrderMain ordermain = new OrderMain();
        ordermain.setUserID((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        ordermain.setGrandTotal(-4300.0d);
        ordermain.setOrderDate(new java.sql.Timestamp(1464509946705l));
        java.util.List<OrderDetail> listOfOrderDetail = new java.util.ArrayList<OrderDetail>();
        OrderDetail orderdetail = new OrderDetail();
        orderdetail.setSubTotal(7100.0d);
        orderdetail.setQty(2147483647);
        Item item = new Item();
        item.setItemImg("rwN1HLKRLbc8PfLS5wLt7HiT6M6U8ZKonJuYvR67DeWTZdLOpW");
        Product product = new Product();
        product.setProductDesc("I97Dwa8btCukjNzmOF66M8JwIMdMiaDzFd1zADapzJvvYPJskf");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        item.setItemImg("LxWMcPSCn8eMxkaoHL9TdU9MlwGM7WnlksVSpNX1k91rgFu0W4");
        item.setProductId((java.lang.String) ProductTest._getPrimarykey()); /* ******Adding refrenced table data */
        item.setItemPrice(1265.45d);
        item.setItemName("A4Ij0lHyNYOcEaQtAUuJBVn6rU659d1Efvfnc3SLsmPijOB9zl");
        item.setItemStock(826730108);
        Item ItemTest = new Item();
        if (isSave) {
            ItemTest = itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        }
        orderdetail.setSubTotal(2368.0d);
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
            ordermain.setGrandTotal(-7390.0d);
            ordermain.setVersionId(1);
            ordermain.setOrderDate(new java.sql.Timestamp(1464509946871l));
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
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "orderDate", null));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 2, "grandTotal", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "grandTotal", 1.2284194204159711E19d));
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
                        field.setAccessible(true);
                        field.set(ordermain, null);
                        validateOrderMain(contraints, ordermain);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(ordermain, null);
                        validateOrderMain(contraints, ordermain);
                        failureCount++;
                        break;
                    case 3:
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
