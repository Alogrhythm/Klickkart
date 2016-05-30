package com.shop.app.server.service.appbasicsetup.usermanagement;
import com.shop.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.shop.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.shop.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("QnEjOjSMkvJCCmbj1phGY6Z4fvqTN3rZRwqP7fE1sudKuf5nW3");
        useraccessdomain.setDomainHelp("25XzRjSC7BP1lnWxNwX1Gs6unmxjlzBny8vnZTMaHiauZTVw3J");
        useraccessdomain.setDomainName("jnf2IfVzoiJ7bskCyQ42Bx8TA2yyHM4NbLWxVumEF0Uuo6Vqko");
        useraccessdomain.setDomainDescription("EKh9hXBTqe7CNBu2sVQ607EpUOXIgddOG5NfecP4fai5DdOrfw");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(97085);
            useraccessdomain.setDomainIcon("8ZVRXQQZ3vB3s31RHTRgEA44bjgRcg7gs1aWXwATP1KpPZn46w");
            useraccessdomain.setDomainHelp("MOFhVycING9BBBSASpCFyACpK5qSCg3jEt4vGYkDsTTWZKxaNI");
            useraccessdomain.setDomainName("HYzabBgk3RefygImXsMSkv4y0uh5IJakM3XZtH7iE8dpQqTKEJ");
            useraccessdomain.setDomainDescription("f2ZugL5YKhiyuDDwapSpJaCcA8hLvfTLLqEsfXuix46waE9ygq");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 150781));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "FRDm1urpHf3XVW9QW0QXT4tkqLCtkFLyNpIYeUlSnYYlCg7MNT7kcJW2lPZ7A6mDZuMDsuECa4GQ2CHZq14CjqbgoIyfH3sSgtsDk2s6ovolZDUjgtea0t9tMs1CB6v8w7XWsdUi0W3g1rULychKvgQgYD9BQKFdIwwqFnydVGTwUe5RtGZnZr7ZI0nn28qoRYL57AQKqcJCK7NGfZ4TCD5q7HlpeMmNEZ2wDKyvUiab6MOQfchXKZxvppeRwAUgM"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "cO9ENxMGOo1dhR4q6RbOvNSkFdn015ndwXMKnnN4dkEgV9jBNnHqfHLOxmutDOTeY5gE1Iwf1tPfAynBChCdJea1u06MPBEmp2MHbPVE7kgHzgs4uGHRCA9szkdP7jHoTrnlPagSux7iN9uVZjV9XfcTWt4Xf1gfs17aklk4MKW2utxrR6nqYLfN5fxQS71vyHLFiWYysdtJoTlfVyHY4gSDflrBo93VIlEdzGQ9sHjV98G8CCxy9C8syQ3WgK0wl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "Y7g33FLmE39BFtJ369Qd62m59KNUWcujWy20yqcogFhArLDucPQcn1F9GhVrVBpNEkvAlGvyqFkC6sW1i8aQCD3siWoh2QkEdXPbr78TomsbFNMUkZ23whcEsx8jGjR7w0L3si8kbHMaefT0Dyd1BdAGb6sdpvUD65ONKfynl076AQIEBX81nWDDw6vDsPjCUBO6vUqRXgJWp9slhQxGm259qNfGgQtLpOJzbzLPnLUuYUFNsZTa4jrFa7oSX7oyx"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
