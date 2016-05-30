package com.shop.app.server.service.organization.locationmanagement;
import com.shop.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.shop.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.shop.app.server.repository.organization.locationmanagement.StateRepository;
import com.shop.app.shared.organization.locationmanagement.State;
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
import com.shop.app.shared.organization.locationmanagement.Country;
import com.shop.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLongitude(4);
        country.setCurrencySymbol("cfjF016SMozHfKrifMuBIRmMGsEvq72N");
        country.setCountryFlag("nyHIwEdBN3yy3J1cXV0VxOAiN8IYhxVQqAoIz2RsRn03sPZVxd");
        country.setCountryCode2("NMq");
        country.setCountryCode1("EWL");
        country.setCurrencyName("DxaOeSL20QpPypLhxOitHCQ2jaWiQlyiBjEHMCvpXau6FLmZ5g");
        country.setIsoNumeric(971);
        country.setCurrencyCode("EKj");
        country.setCapitalLatitude(2);
        country.setCountryName("wrlXOQ3U57i5QCWtMus75VjRu3sZlmcvmYDVA6EgqUkRVL5Ipa");
        country.setCapital("4DtkpvG8Pz2h5MLZKB4Pble4XjCJSqZu");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("jWw2Q6Kyghkt92lnbeJhHTRJbLRmKbL5");
        state.setStateCapitalLatitude(11);
        state.setStateCode(2);
        state.setStateCodeChar3("T6LJydTS0Ilc5m9Pd61eYYmMGcVlvOBX");
        state.setStateCapital("T2TzZ91ua0fbqrGkJA8mE6XqlU16KfLBrIcva42DX8Qo0E03d3");
        state.setStateCapitalLongitude(7);
        state.setStateDescription("IcMT7ZXBtzBXFSrU8zfDMUpi5HQuom3Wb3weF2flRnaInPgr6N");
        state.setStateName("zvzHAC5fN7svCLYanXDshOWxHeVsAjaoHEfbqYioBUYZffcjc9");
        state.setStateFlag("n3EYu45mADUdhMZur7Pt48B0Mae8EE1BckJIUtYgLRQkbF9wvL");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCodeChar2("ioK6ZE2unjJhsExSL0AttaMygrcAzGc6");
            state.setVersionId(1);
            state.setStateCapitalLatitude(3);
            state.setStateCode(2);
            state.setStateCodeChar3("2NFALvWJLB7hhPPTuKodFz5kf15XPvrp");
            state.setStateCapital("GgDCy5pCjUkQ8qYW1mZKwwEfXKaYPIqDv5QFejko2fxqXEs6EM");
            state.setStateCapitalLongitude(7);
            state.setStateDescription("6eUnVkgPKpzxGe6kcXmUbWglcYySOhStxpDo8WLW45HL6yVQyC");
            state.setStateName("4DsMyERkpNbkOL4HcuUydrb8XPOA33V8ShdZ2CMC8MJYbuEIu5");
            state.setStateFlag("o0xSAM6qcK2llOusswPQKNUFYyS2tzYJ89WjgC99xQcomgKX5R");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "B7WHU5WrfHn1FCW4SBHwUhGQabRuaJD1XH4gA3ZXN0v5PR25249wuJxC2AMFFF2nIzJ5LODsG1SrAiSdE0S1mtYww8hNZi37PDNqfL7JzwmheWchjVUzAk46TOtWTjVIdeQD0ENnXbdtSOJF4jTmJXA4od3VPgGzH4BcjYKPHAQkE71UIxhVNT8GA4TQDmdMqO7eY71sZ8Z5zH9DjHtsclZkQxlVGK1s5buJu94q8OqXzx63Exx74N0sFnmf21Suh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "VaB1eGB7i1QXPegeLdtJGzuKAr6QMKzNw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "udfcUwthaIR7ayg1iS3NSYZyEkTXYjsxS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "zUOECV0D7NDLBveESYpO6FRrRLp5SAa4vw4kaitu7jNjwUIxWyRc9sofIKD7iOYjWrvd7uHjFVoENV69SdL8bRV6gioCEOTwkhqyBfzwCjer6YZeY3GPi4t2Mn9NgjtjbV11xnX3It2m2jKeIwlOHCluCpV7OJEma3TUEOz8xj1acaMa901Lt1OVDwA8DdDWrOW8QRj4TDy0FydOludEAaLhyGPLkP37oCoG0xr32vmsYeOSSSDUBHMoLfpTm3iVH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "PnPJ9e1pVdptjphrVekzVgKys0aeOKN30hhXsqN4Tif8fGts8Dy3LJibSftGG18Br7o7FdYjL4dOjk2xtXH6uFrInOmvRnsUhHReQv2hmqgkPNDhvPGYx1lgySddJtB6J"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "i5mcr5mEPIjSiTO7BLfFLS0tReJo12mhLmiIltDm37uG66KhE04nmTyFxHgZFnXdXupPZTo5R8Z0EmwN2QxLVXbU1Eqetp1vF1zFTA1pSMv4FaWZ3G8KLaClnSQpTNCH1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
