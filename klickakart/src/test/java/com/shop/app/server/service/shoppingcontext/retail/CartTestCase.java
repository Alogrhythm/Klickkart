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
import com.shop.app.server.repository.shoppingcontext.retail.CartRepository;
import com.shop.app.shared.shoppingcontext.retail.Cart;
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
import com.shop.app.shared.shoppingcontext.retail.Item;
import com.shop.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.shop.app.shared.shoppingcontext.retail.Product;
import com.shop.app.server.repository.shoppingcontext.retail.ProductRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CartTestCase extends EntityTestCriteria {

    @Autowired
    private CartRepository<Cart> cartRepository;

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

    private Cart createCart(Boolean isSave) throws Exception {
        Item item = new Item();
        item.setItemImg("ckze52bSJlBTuyHeUnEpRgQ4Bw3yI0IogYMWCtkwLTIWPaz2Ki");
        Product product = new Product();
        product.setProductDesc("10WqAEimGRFcjTc3MCsbi9gNbzkijpuDaqBaLgMUfokWC2m5Xs");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        item.setItemImg("csacDU0czjdRaTh2CnTU7MNbiTTIQwJgPVryip95WbnuIpNPK6");
        item.setProductId((java.lang.String) ProductTest._getPrimarykey()); /* ******Adding refrenced table data */
        item.setItemPrice(9024.12d);
        item.setItemName("5x91vVYbNaJ7HcbNS3CJRaFOIyNw6Fxlrf19PwSYNKrvsR1JZg");
        item.setItemStock(168458349);
        Item ItemTest = new Item();
        if (isSave) {
            ItemTest = itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        }
        User user = new User();
        user.setIsDeleted(1);
        user.setSessionTimeout(2132);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("DAWIrEzeZbj5NkvRQOn3mZwAojaSP9GTKTKzi7zvAKfjqgQ6uL");
        useraccessdomain.setDomainHelp("hUtAgq4pwPp7J8lreuASUtGntYNiGf6WcCDyYe1ejBdER33xCp");
        useraccessdomain.setDomainName("xoPcf3MQOJycOCTL59wqREjbI2k1P57I8SUpL2lYiSarneZXS6");
        useraccessdomain.setDomainDescription("NowCfhE6MZ5fQiW1FlRXHMYzlsZCDgU9Klzd25ZR45Zt8SmuGc");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("rL3Bz2DhzipWtTUeR9xasydmvW9O4BG0DbaiPweBO1bOqweJ33");
        useraccesslevel.setLevelHelp("LP4WK996j7BQL8BiV8X3oyfKKLoMsU4sSv9rwOv9QL9JjjGzY2");
        useraccesslevel.setLevelIcon("EIDZzyqm3BANeZwhxntxHLJBgDfcrWkgQO84T3IOL7U0wKDyiE");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("s12O0oKxwyk3YUa3FM5jHPuXjZM3zTbGohT4opAKH1XKYZLlkm");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setSessionTimeout(472);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(55368);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("BcW7suFg821C2Zi1lddXhiznFZMCxgH6rtvigsbyoCOBhxKsQT");
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464509945448l));
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464509945448l));
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("ARhUTp9eaAQGWxYbLtukGBGdFxTmvVUCouhlel6Roe2t5BE8bK");
        question.setQuestion("2kq6tiAOk4g9ZX3tsZ1GKEakF5CBoYFOVdmdYJv4btVO9N6eaR");
        question.setLevelid(6);
        question.setQuestionDetails("iXRWd1g98d");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("WHW3SwIGERVXRKRfZKiLwoteuNzWImt3VTcpVD5rckq2rDudVY");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("B7XlRHOoLvtfbjCNzHhV7dTp3yqpPFtd");
        userdata.setLast5Passwords("UybWgtYv4RiSIAyFNir260JMNnuek2U1gVbNMJGRckVDwxh2wd");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464509945603l));
        userdata.setOneTimePasswordExpiry(9);
        userdata.setPassword("BBCgq11izXaXGegF0pZdym8pFrg8SQTgAQ9ew22xbLrjd2N8mQ");
        userdata.setOneTimePassword("nNqaubzfF0uni35glXUhpulfyFKnoFGu");
        userdata.setLast5Passwords("I4xtkahgdVBciRJgmpcMHXp2UBFR1u3kJCAZENcWBnLd0hclxI");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464509945609l));
        userdata.setOneTimePasswordExpiry(4);
        userdata.setPassword("mNYoTHFgiYx8pMcVyW6EbqchGPGj9WwVDmpfQPvFvSb0VNtURG");
        userdata.setUser(user);
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        Cart cart = new Cart();
        cart.setSubTotal(7472.0d);
        cart.setItemID((java.lang.String) ItemTest._getPrimarykey()); /* ******Adding refrenced table data */
        cart.setQty(676327145);
        cart.setUserId((java.lang.String) UserTest._getPrimarykey());
        cart.setEntityValidator(entityValidator);
        return cart;
    }

    @Test
    public void test1Save() {
        try {
            Cart cart = createCart(true);
            cart.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            cart.isValid();
            cartRepository.save(cart);
            map.put("CartPrimaryKey", cart._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private ProductRepository<Product> productRepository;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CartPrimaryKey"));
            Cart cart = cartRepository.findById((java.lang.String) map.get("CartPrimaryKey"));
            cart.setVersionId(1);
            cart.setSubTotal(6448.0d);
            cart.setQty(900678648);
            cart.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cartRepository.update(cart);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CartPrimaryKey"));
            cartRepository.findById((java.lang.String) map.get("CartPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByitemID() {
        try {
            java.util.List<Cart> listofitemID = cartRepository.findByItemID((java.lang.String) map.get("ItemPrimaryKey"));
            if (listofitemID.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<Cart> listofuserId = cartRepository.findByUserId((java.lang.String) map.get("UserPrimaryKey"));
            if (listofuserId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CartPrimaryKey"));
            cartRepository.delete((java.lang.String) map.get("CartPrimaryKey")); /* Deleting refrenced data */
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            itemRepository.delete((java.lang.String) map.get("ItemPrimaryKey")); /* Deleting refrenced data */
            productRepository.delete((java.lang.String) map.get("ProductPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCart(EntityTestCriteria contraints, Cart cart) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            cart.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            cart.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            cart.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cartRepository.save(cart);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "qty", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "qty", 1018504141));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "subTotal", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "subTotal", 1.2749613955400315E19d));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Cart cart = createCart(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = cart.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(cart, null);
                        validateCart(contraints, cart);
                        failureCount++;
                        break;
                    case 2:
                        cart.setQty(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCart(contraints, cart);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(cart, null);
                        validateCart(contraints, cart);
                        failureCount++;
                        break;
                    case 4:
                        cart.setSubTotal(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCart(contraints, cart);
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
