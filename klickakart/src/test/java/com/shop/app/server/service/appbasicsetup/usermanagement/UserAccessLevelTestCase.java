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
import com.shop.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("Bu6ixBaFy4PBAz2uJ2L4g42Z1x1xtPqTCGH78rBk3qIrlFxO9T");
        useraccesslevel.setLevelHelp("Iw5KqPu9zj7w2rxPbP5zZgYcXYq18zlLIoPEvRGyle3TdQK3TB");
        useraccesslevel.setLevelIcon("QlkOdZIOmEWR1qSRmYvDfKm7CaFWgJfPef5xspHYdW6IWE7wxn");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("73ghFruDrVPX7URmp7cNhc6Hn2D1lcGDEBNy3vdM3RkiZgqNBs");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelName("qjhFQKbbK3xO7LqvseCiIyK4vpesvsfAMfcZv3cU2m87uLrLFv");
            useraccesslevel.setLevelHelp("hdKAmAeei62Hyjn725Ap7pWY88zltnSH1bvS1Dj335gp3eMJKJ");
            useraccesslevel.setLevelIcon("5GhhpqfBNw2HQpqPOWNe97qUHymAiZ5lnWOOscWBY5pPdGP0Dr");
            useraccesslevel.setUserAccessLevel(63697);
            useraccesslevel.setLevelDescription("I9StWgxntTgY7StSpxUffaosyDIaBCEx3jLJ4aipIC1TLPwtPm");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 151708));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "a48djdhiKC8oqMllbYLPG1zQpLC1GpVO8RnyJDt469Fw9MmpHGZWf41JD8tvgWbIfewIIKawPPtjAZyWNj0kDXFkieCK2Zb08AbPodX1nS0Jith1cbLQJb1FXbDrOGz9acxGWMobx1Z5yepeTVfTlfsa1GVUmEGoAK9H2Hn7AnhW5uBFnU5IZIrakOK5602nu3Vtgv4yFYrphDrQNFipi85xfgCfJVRGjSyFhZfQBD3ShHWl8zaC7gqsLTN5rSOOS"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "xw2rV9GYJobZy8nJIgkSvsxhB1JC010sCKhCXJzXITzDpjtKb9ZckPq8e6Ki60eg6FVOAT5TMuLFCoFSWRMjB87CSkcplwdyQbDHOVaV8obnFV9M0Wuak1yY8nzMed09eJcx6LvKhIzcdizIAPj5J21aFbO9Lp5EpwR04H59f8AGvfIN6YavsuBwOeoidHjKUBKLW2wtZFXnjUGH9RvBSVIG784XsXvoVL88LunU6qD5PatyJw7DRSzU0vC88BZmC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "VdTTYlrNmc6op2CuuGAZKo1WdwgM0JrYSjqg8p2wbzRC4Crl2T5QX6G01W07iZDsxwNHBvzOv4waCX1SAPYU8ObPDJJflOdh9oJiQWzJLlz6Pe1xdIUNH3JckhGWp2niWfSYn8IuusgGlORmDekBDSQHKT1r0Pe0Y1yzsqmzF1mzsoN5Mt4owEPxqWOAE0xrUQEHPDgWl3GZaZESTDkoaLVzq6PLR9hhQgxTI5hSB4yIBIlAtdDABUAyZKLRFjUTQ"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
