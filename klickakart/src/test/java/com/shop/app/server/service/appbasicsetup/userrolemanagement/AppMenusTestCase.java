package com.shop.app.server.service.appbasicsetup.userrolemanagement;
import com.shop.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.shop.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.shop.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.shop.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setUiType("GqQ");
        appmenus.setMenuAction("VFOX6uD5rsLiMi75kIgClZohbXeXhxJ945m03Sm3gcWmtGUqu2");
        appmenus.setAppId("mYFaiC7Z0hkQ0A64f1yyWbn2bVVTvPK07TkLpYsCY73c6VsE6f");
        appmenus.setMenuCommands("3puSm80bOrM3TqBPFKan0DXb24YiNOdyZMiJUjhAmXL5xhIqC2");
        appmenus.setAppType(2);
        appmenus.setRefObjectId("felu3NJlvWe4MKWplbitkGf44cpDdyC6cum6X1ATXsce2YIZum");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuTreeId("5oxaNxyvcQM0x1c1azl9fFgfBEZg4bPQZe8nBJSo1Kl78OIFSx");
        appmenus.setMenuAccessRights(6);
        appmenus.setMenuLabel("LXke9oc0XaGIdWSXM9t8N8xRFSfVSsG624csebRz8PjffjXftW");
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("UxdnHWJHqv09RkQMNOkzFDIj0aj2vWJqgtSY4nl6nFh8imMp8C");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setUiType("SmI");
            appmenus.setMenuAction("arM7gX0ODzxe7jURARhvPE42w0a2vib569HiF4VoytmUoCD1rJ");
            appmenus.setAppId("Pfuz9rkCvjPFHNbkobZXJWbvp2Qaju2I27ADWUPxK2M7iTsO5a");
            appmenus.setMenuCommands("Wo1ZDFUMeYA96HU3WtafsvQ4zH02dJwrYqd03P6RJqqshqMqAr");
            appmenus.setAppType(2);
            appmenus.setRefObjectId("pH5xF76ALlMRmSolnzJL1nIlW4NDmlypgNv0LR8e1vJBvYopPx");
            appmenus.setVersionId(1);
            appmenus.setMenuTreeId("A6aDKH1jKz62XgyMEkRid3DhSH9a348iykrBdUiaEzfXDNcBId");
            appmenus.setMenuAccessRights(11);
            appmenus.setMenuLabel("r8aI0KLnbMGqDIRdYuoLZdcvZ83Ic4npNZCE194Unto3v4wwOQ");
            appmenus.setMenuIcon("0v73KBUQBWMywJy7jBMF0N4CRJqQ7rpuq3pa6ZVTqyOt5C6fYf");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "2DzOA9AWDuJcNmXNfleqosXgPSguZ0Az4QnNBlpuVZJUwgQriReXtjYKZkdf16qWfMNHfqQ004CUw28d73MSyn6PEOquNOzbFcPiECLAP0HwYrpDJO3DIskkrYMzRgw0aqJK7tJRdiw4U3KLEvB1hCtnULnqX5nJeMvqGB8IYj0BAjEwwhCyz3GLNaygcHhYsZP9SgKDxGvwBCjmJoBiHVPouKA463nzaZ5X52W4XV7FEEkA6RDqf6Fv6paiCry6U"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "y3EdfTAzDQCTKWdzWSPZys9Ar5k4BBavz8H3LHG5ipeBpWH6qzLOr4UTrdjWPOl6DaEnky6zT2zx37JMlKmoL711Wb2SfYsXDjglgA5PwPF4i6JacrXbChc1R1JeNemxqvTnLWOIwobSwMRBPiL2oTbvDZilN1XrV2RAVf3yXhE3X9ZZXgKXwFdeEWiDDuXCHN7kMZozCJULFgLPWf4KCqJVGbhXZ4JpYC5tXwqaZzVbyKDQfz6v9tFseQfhBfyGx"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "Z08g5wQXJrDxqA4DM0xFHelFKBPr6VscyRj88qWry5dxVda3cYhtnPO4NN2cYEjYzIoaJF4856aeN1F269sdd07Xyt6SulOnn7U8uSFNE8qh7UubAgEjQSqRb3PyhZ6KC0Zt0zeju19A0KRVmrSVX9nh9NvaX9FjoE5m7yzzmmhTpSqROCh0IH7iJVBfBJKxzojrc4fFkiz3de0sK9ZtWUhOj2PFQHeSJQnyscnDsX4CSxLv25g8fCPzDsFmdT0bB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "O9fG6qq4DEd1Y1BoRjBUjKQItCAlrKRT9pmqmuS6qlYDDesbT10TUwbXmY8M9Hr1d"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "QxBrGBP3n3bnIbLTdZO8q6arBv1SowHAC8Zc5CflkGwaXqyYyxunjjCqqnVfjZnNBoG49vOCgjOkjpVdvBFTfkADzYpW7fhLg0TlYx2EyYwCYxQAZjsEhq24gRYLjHREvg93dToC9rN9my6DEIHa2ZdVY7AVXW0iCLcNsso4Pwke5cr8Gqjz1pdupIwFmMlsuzz853FiIc2xHelMEWH87hnIxsYafudWLnDVKzir2PR4gtGEi2RScA66eHAWIAkDJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "bHuD"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "rd6zy4fWCLkPlQXAV5XpCiD2pv4HQsRIauTcNvYtmaE1Htt6KKOwF2Scb2Gdi3vBfOIuLWZVE03YwFBnGa0PIXEOhVCZbYk8plG7Wdd1vVF3YT8eWDtzatVDjilWzAfjZP4t1yaQM5ExHbvwpn5Wjx4m1TYkOS59gSIsPU7SWrF1Pi0ZWSxm8gfCsQeVuxYFriUYI7DcPlDWiOleo4gIZUEUfIWmrMZt08xiWy40U1yPOjDk3OMaAeF0jb8JPDWGY"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "2T4TgVXTJ2Nb3rJp4ZKRmAe5b9o01IWtRgQYcKUrjy6p6PVACgjoiTPu6aJkDZscqhJK9nwkPsSabh98Sl75N7UzQaHe28lTk3bybsoX1ti1kaXkCsaasviiyGAc5iytUyAdNFc1iWjqohpR7Gm1rSX9WDEfmnrbE8Nhkg25O7FKedXkV0uExeKvWaiXW4r2lTCEsi3upCDRbgXzG8pUL7SZG0wZgxj3vqnEYsqtShwyu8RE2cIpeyPGUMENZOHrW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
