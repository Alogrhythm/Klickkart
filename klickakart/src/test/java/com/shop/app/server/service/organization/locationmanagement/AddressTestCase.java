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
import com.shop.app.server.repository.organization.locationmanagement.AddressRepository;
import com.shop.app.shared.organization.locationmanagement.Address;
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
import com.shop.app.shared.organization.locationmanagement.AddressType;
import com.shop.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.shop.app.shared.organization.locationmanagement.Country;
import com.shop.app.server.repository.organization.locationmanagement.CountryRepository;
import com.shop.app.shared.organization.locationmanagement.City;
import com.shop.app.server.repository.organization.locationmanagement.CityRepository;
import com.shop.app.shared.organization.locationmanagement.State;
import com.shop.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("BORjNAqcwCoJG722N64t0Y0NPfK1gyhi6KSC3atNRmh5fI2NhM");
        addresstype.setAddressTypeIcon("G53qIBjlf0nuqojMCdvGW2rc6RR02qiIaXy7T0XqfPKRFhvSZ6");
        addresstype.setAddressTypeDesc("SMc1ivYHBgJmm1sUizwPUTaHiteTmhWhy2YfIoLrtoF2nZDhwp");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCapitalLongitude(2);
        country.setCurrencySymbol("Bov89dwWS9b1VVTEPSlQqvftMDdRGOdb");
        country.setCountryFlag("cTQGZxnZezhgrJr6j4Drd7f9ZT7stQhg2mdgotAsPct2IGkVkV");
        country.setCountryCode2("lTZ");
        country.setCountryCode1("xcg");
        country.setCurrencyName("RQpLIgEIOrr5qQb3lDwpIQq0aOn8uxtc8znxfx6346BbfmHOAC");
        country.setIsoNumeric(9);
        country.setCurrencyCode("ZF1");
        country.setCapitalLatitude(9);
        country.setCountryName("z7cpUo2yQmCos4mJnFGup7CgD2klGtJScl3SbdxktX7AzMRE25");
        country.setCapital("ePUUvvAU5BEGi2yuUfGz4A4kxdVAqUj4");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(5);
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("NAOyY6p9OSV3aThgs9XD5eHzxF7FiNQu");
        state.setStateCapitalLatitude(9);
        state.setStateCode(1);
        state.setStateCodeChar3("lvBmycCHsuNUybP5klF6TuwmhHBALFX3");
        state.setStateCapital("YThA0ANKqkngv5WZlvZ3bcDb9KgS5yAJN5JzGWWchX6TX8E0VQ");
        state.setStateCapitalLongitude(7);
        state.setStateDescription("vHhIlS0MPw1GDVXHU1wudtd2EYTAYZFkzLN7jJn2nLZcTbGh0X");
        state.setStateName("rJarhJyiTX4ojAbikDdGc23FzBJ4uKSLpjeoyGPmQ6N23t9lYc");
        state.setStateFlag("uTquvRz2R2o95Kd1EMokdCFzVZXQIkjbreoYcXJIB6IoEReuHo");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("mqj2ZuaKum1wbtZp7EaoDCTQrqC19datcimduwTH2hxD2MABik");
        city.setCityCode(1);
        city.setCityDescription("Ma3cxLwt3j1RMRDwGuzx2Q979YMIu6SdHYkFW6jibaU52rHwUy");
        city.setCityCodeChar2("ID7X3SxzIDzvVQ9sKvh9ReP7r0frG8Pe");
        city.setCityLongitude(8);
        city.setCityFlag("zaw969bPUSgHIQrJTAuslLpzYRg3rUcCqQqpduhtK8NIGH8dqj");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("AjyVRJC2p3wgj0EaqyhDbR6o591FsxfOEboWwLLmqnYhyTd935");
        address.setAddressLabel("84aSlIK6A7g");
        address.setZipcode("8lkZ41");
        address.setLongitude("ojGOfBYu0cE2WCzdLgkH31wnZD2DKValW1CJt8zYYlNYtn1i9o");
        address.setAddress2("inQ10A4dzr0sii28RxW1bC3hc8Mn0XjYuAGoqVq78pbKs7NmIb");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("PVkuD57LnViDLyZZnHyOP26zGdCttXnx4Wpx9DSCqSs4rLJwJz");
        address.setLatitude("8YfdUTY1PWymZYrZNask3hKwF99NatzOqK19dlNq7vOZuE3QGq");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress3("ZWL9zSzsZtyWHpFAU998NEHh4NSIIc1KxAKpKUuVmNjw9G8dCw");
            address.setAddressLabel("0CpBJxu1FYc");
            address.setVersionId(1);
            address.setZipcode("Ix9s08");
            address.setLongitude("9lKJA7Axh15f6fLMFZ11fL8yEae9BtBlj6TgzmORnMIvRAp3rR");
            address.setAddress2("AzvckXV8Pqa7MwLLdK1UGlAbcUhqd6XQ6hfxPFoK2Ad0y9J43a");
            address.setAddress1("FQNBish4wiO7IOU8ttaUrTzKlDjXa2PfB0wLZtkTxCLOefnU3P");
            address.setLatitude("BoAdhbuhZFc8HbrJGpAqv5FzczHpv8U1iSr8iMPkBk645rCO45");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "NF7veKikHhHV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "yyUaNl6cb5yDmySElxBUggePTHDy1RsPmBBlrkfUHFAuWm4J390256SRK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "pfCYtyRJKw3NN3eEip45oO7qdGXJGlB7vTSkkkaVgSYRhTFsV2ZrrHbav"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "arh8nI34VzsKKtxEcayaziVVQjBVZ0YeRkDWcsdw9Go9yRSNSwB4fgbCQ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "Ky3z6lR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "uNQKyisvC9uigRQsGE0odIT7iphdmiBfemQNdRGIZrVZuctXz7NwAUFnFMTtEdWXt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "pfdHixDPrnOcSM6SY4IQ74OjG1ufr3TO6wyAc6sDNdCO6kiw6UcJsejLOLmtbPReH"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
