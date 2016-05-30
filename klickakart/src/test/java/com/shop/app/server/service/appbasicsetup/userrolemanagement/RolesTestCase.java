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
import com.shop.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.shop.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.shop.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.shop.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.shop.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("65AoG4Nwf8X6VeNwqlXVs64RkPXJzeX77oC6UJccOb4tTHhisG");
        roles.setRoleDescription("YbxO4OdRd5MGfAjvT0uS0VNFnQ1MmuLxBDv8piYGGSanvSSd6d");
        roles.setRoleIcon("gvPHaqkeCJe2GroQigFRM9wgbMZJX7LbUWLt3RXsT9mNLQgHfM");
        roles.setRoleName("jmp7rTAMk5ugahof97Vk22RA39blwvATKpopqJdZlly1bw4X8L");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setUiType("GRv");
        appmenus.setMenuAction("1TwFOgVc0ux7W3WSU0CSLL4LOxCSo5MDWafqDy3tYlFTWuO7LE");
        appmenus.setAppId("h3yPgoINAvOyPcnDTPctby2XKnTaoi2zb1ii2A5UlYToppl5Lc");
        appmenus.setMenuCommands("vReXr9nvLPzzxhkDTQDRdrnJZR4jNK2NUesmGfN5jcXMDnnLCR");
        appmenus.setAppType(1);
        appmenus.setRefObjectId("yAT5G0zNfa2R6jNSheE9UNd1hpvILPFXrTK1BqBWz1JOcBvPbC");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuTreeId("Fqv13M5sq1khGIvRDDcGLC4uuoeJNlaoiRw7yb7xUX4g117mQs");
        appmenus.setMenuAccessRights(8);
        appmenus.setMenuLabel("boOrBLhHJ5kVReSgdvKaPZO3gTrKalcZOuQtYK9YrVUTevv1nq");
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("BxTzZ6Q42wvJ0hnPacgLh33o72QK6fz08IKmXqCLk1ycgCVv2y");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsExecute(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("zEmEm7zGygYO9PbYauTcudwgWzn2eDuUvKT07UIgTzzAQUgfaP");
            roles.setRoleDescription("SoAHw1ucJUS8yxbAobBUWyRTuUWogYjsMz8lsTHH3vtpEbbX5t");
            roles.setRoleIcon("gNXz1joy4f5Hob6TONjdYlscZ9ZZVxXKJVGW9WdNyrdLu9NzX3");
            roles.setRoleName("BHdrlYnn5BiYvWoJdb5NUZNcWa1vpv7MWwtY38bKTMPLiIpvKk");
            roles.setVersionId(1);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "lCsncXetI3nkArprmQwnnr7yiy7UKuwHKv199jAbyKEg9eK42fhpX1TMJmcq7wTuaSe84w3kyQZsdrND1vLUXEeOGMQHXTfNcAEdqqrvLyMnooAWLe8xkHoGqUSigChAOyHhWMk5vI4kX2KDruIOsLVQuREWQoLXWrA16JOsPjsAco3Bt2iaHTfUel9Z3XjOv2I2hjD0Gg2kuaPLwCsvjt360UsaxZacZfSrUkozxJpFbxr3LRc6STls3b7abf4AP"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "uZjVAM7ZnmIAfF0asIpEldIF7BGhX1m9pzFVUEjDSRoismeoPjNdvSUrTRUnSoDFVcJQlsUrGfbxfSgRLUDIeANwBDwlUf93vvivhcQKQD8VmpOHm2aPitReHSZpzUrOCzQZokaL1vxdUfl5k7nEcub2rNgl0JDCareFgpXwwIBwvaXwD4lqqTHDvGECV7oEnMMnC0fnvZGkF1EUhWLlAYK0De4OpIyZ1ErQDO5zo7PmTOs3wgU8VWg0ccarleJaA"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "Te2rrRszeil6qrF5IbJBbi8qDkp9czPEAM7NhLtcGTrQdJvr6cMFNrJmHHGfWQBWU"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "51jklzjfnHVbeCIq99Xyj7pCYhTusYuGVcXFoppMTnw908g4TfhVvDBEsy2bTyRXr7sAoFwRQET5JLfDgWlrAqvelHTDxJMrGcWbWGnm7wrDT9fWIVBz089U8UeI0JxX3XqFWeJwbNGnExwLZIrf3Ov2lhDfFFdWjwByI3k2VUQWEuFFoFxGiODxexkqkXoKOhCovRidxHOgZuTObHZXMKVUcYeJWS7e6T6fdFoer4JXOx4t80J678nepmK0zpBxy"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
