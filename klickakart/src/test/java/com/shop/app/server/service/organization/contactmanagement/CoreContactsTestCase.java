package com.shop.app.server.service.organization.contactmanagement;
import com.shop.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.shop.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.shop.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.shop.app.shared.organization.contactmanagement.CoreContacts;
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
import com.shop.app.shared.organization.contactmanagement.Title;
import com.shop.app.server.repository.organization.contactmanagement.TitleRepository;
import com.shop.app.shared.organization.locationmanagement.Language;
import com.shop.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.shop.app.shared.organization.locationmanagement.Timezone;
import com.shop.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.shop.app.shared.organization.contactmanagement.Gender;
import com.shop.app.server.repository.organization.contactmanagement.GenderRepository;
import com.shop.app.shared.organization.locationmanagement.Address;
import com.shop.app.server.repository.organization.locationmanagement.AddressRepository;
import com.shop.app.shared.organization.locationmanagement.AddressType;
import com.shop.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.shop.app.shared.organization.locationmanagement.Country;
import com.shop.app.server.repository.organization.locationmanagement.CountryRepository;
import com.shop.app.shared.organization.locationmanagement.City;
import com.shop.app.server.repository.organization.locationmanagement.CityRepository;
import com.shop.app.shared.organization.locationmanagement.State;
import com.shop.app.server.repository.organization.locationmanagement.StateRepository;
import com.shop.app.shared.organization.contactmanagement.CommunicationData;
import com.shop.app.shared.organization.contactmanagement.CommunicationGroup;
import com.shop.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.shop.app.shared.organization.contactmanagement.CommunicationType;
import com.shop.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("tplC1bRvxn9BQTk2E8NLykXKRaQzyxUCuFCgFJt5JJbv1Wamcx");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha4("DiZT");
        language.setLanguage("BUxOxyU0lyYJ0sb8XGCIQbkrR0gdBhni9gTNXW3c61Hz8xq52a");
        language.setAlpha2("d4");
        language.setLanguageDescription("1KFilb4GriVTHcILqU6souVRtOGPE6X79dY9H8QxlA7UHPOibU");
        language.setLanguageType("6o3igmb6FqSXUDhEKXhVDcB9IC7PZn8I");
        language.setAlpha4parentid(1);
        language.setAlpha3("T4w");
        language.setLanguageIcon("WEZbFtamybfs7XxbSU2yeRGLMTTVuZ4a9v86GURcGXzZiovhqz");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("Wv2Ms3Z381LOVXbCaPD42TEXRoopLJgOIXzjMd1ox7XfofJ6MX");
        timezone.setCountry("gMSIej3676xhMH9mBtW5cCP0snpsqW0tJwqFF94nJGETKuSrtB");
        timezone.setGmtLabel("OnY3J1qEsYTgVUiSgX0SFGsXZ1LFP74ZTAM37g9RTtJqGTkF81");
        timezone.setUtcdifference(9);
        timezone.setTimeZoneLabel("rFX7LWrvtX7tQtWmk0OH6acbCo7rZCWtngSLMgcHM5Nh8jsGam");
        Gender gender = new Gender();
        gender.setGender("nXlfyvD2FcpXNuyLtPAdGFQdL92MNWlIUWeNPwoeH7mEXyKHrA");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setEmailId("vtcnQMg4FuwbRQMNnOTKEezt0tfAATQv8z3ZKCCL9FaQN5nTgB");
        corecontacts.setNativeLastName("F3eAXmYfnsDeaDg4dlNM3ZSi7VH2AIlzsXg3FKLQaQVfZ6RZCq");
        corecontacts.setPhoneNumber("m2rQaK1LNxA5UFvLJK3Z");
        corecontacts.setLastName("PysHjlT9F76mhCUYF2FocfNDhYw9oIDhKotH1Vhxq6iKlYCZb7");
        corecontacts.setMiddleName("vFJvesWdNKQ0iIYQn7Y5dwkblnyELV6eF7SJ2Ib5FzYT7NZjh8");
        corecontacts.setNativeFirstName("R9R2xeknJ9lh2zO2fP5fKdHZ95YD82ejzUYFvzjFqH8NYXLzre");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464507507412l));
        corecontacts.setFirstName("0tMqJaEkTNqwmJ33jKXHhze5Gl50Ut6KgTkm351jXcS9MHYbHh");
        corecontacts.setNativeMiddleName("h0McaXL0kedAqusxQRsqhKzK2fcEPMWWvMesfnw5oD08Dht7CH");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464507507449l));
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("s91Hg1U8DXb5NdSQnTSunfxqMMSuQRE1FY9kKSp5VWmVbFiGKr");
        corecontacts.setAge(33);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("12qM6WEp96nPdT47NGPpK5RnJsBuMmu3QWfrPP47yn7OdRJB44");
        addresstype.setAddressTypeIcon("MnKVQzRgADLXKS1p3oCuKdtTJPWWXSUYE6Y14WxNbRGOaEm1EQ");
        addresstype.setAddressTypeDesc("4oYo46O0l7SWbhyOuRnSvdO5dgDRJfNAgKKoaAvsvwY2LLSIx4");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCapitalLongitude(10);
        country.setCurrencySymbol("hFzTzVEIQkUKkrWIiYae2DhHx8Ipbs7z");
        country.setCountryFlag("EtzEvla5cCaXlHzd3q2YJ8dc3GEWY1N2mpOh7H0N8xaqjqpEMk");
        country.setCountryCode2("dM5");
        country.setCountryCode1("49q");
        country.setCurrencyName("oR69Tag9MWBbtW7hlCb1ZWzMK2MwguqWOfwHVFigpWG3Ub70Su");
        country.setIsoNumeric(600);
        country.setCurrencyCode("IMl");
        country.setCapitalLatitude(5);
        country.setCountryName("KdOIlPMvvXGUGuogVLbIFHielfeyrfNIfOzJxsfjHE6dSpqh8U");
        country.setCapital("uBPJYOxgkHSg09ZHLAl4VgH1DUoSgOVg");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(11);
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("znH2DyccBgoHYsocSETsiLOewMWzmXI0");
        state.setStateCapitalLatitude(10);
        state.setStateCode(2);
        state.setStateCodeChar3("n83J9RibeqLPYsfTPIeqjqbNgTMGmkrW");
        state.setStateCapital("qWnoJno4nYxhLsaD9NXAsRHOSj8FXU1e2uZhRHP01OejPocPdQ");
        state.setStateCapitalLongitude(5);
        state.setStateDescription("XRrpAYG5UB2EQOAwtR90OBaKWBU8k1o1fRRuPSnRdtadcrhcof");
        state.setStateName("YaoaTbSeQOe04vBPy5DwU9d7v6SalhBHQXcu9DAEoIxgEkWpus");
        state.setStateFlag("dOb2iZ6D70Xi2LJDiPUYmdX9ZNJD27pNKcZ6oEpKv4Aq2YumHj");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(10);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("OGoMkYA7Xs548s24ZUdVTdEYSMRIXgozjdnAznakKG5ZXFCoHb");
        city.setCityCode(2);
        city.setCityDescription("WGGfJsik67i57up8Frq1hWPKiUggKBqyz1UfWU5SPEutcOEHFx");
        city.setCityCodeChar2("TTORAdN1YuSo5X60ch9lujp4Tecm8yI5");
        city.setCityLongitude(5);
        city.setCityFlag("tj5XpXaTV7M9sqV8YspIqZf1d5ztD0z5qKH0iSTGr6q6g8AAqo");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("kGJA2zZJyMEpLJBwHDOdf9LWjy6un5yFpRt6gpbJ7q2FO37K98");
        address.setAddressLabel("pnfjBtGKbY4");
        address.setZipcode("h0D5Dp");
        address.setLongitude("FrG07fXYpNCXLpJ52L49AfBQsS0Ei9CedgbOmNnudqby8Gf4II");
        address.setAddress2("0xbu6dD2lSlE1pugyUNtraZRJUSVwUitD4PRr59apyj3tsO6zZ");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("I2viN5qXsRlrxiwknhvnWEbDrSSy3c2F1d9Jrvp2eaZuATo1my");
        address.setLatitude("izRWXWdWYLHcnd8XREkhK9tZzz7VsVZwBerV9iX1zME6H8uemw");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("G9dayVZ9At20Kas8cTMJGruW6TQH8ClODpLe4WAiN6yKYgike2");
        communicationgroup.setCommGroupDescription("2YMo2kieLQOZZ9nNEFEkTr8hTTGehFRbniKprSVnWajO6f2lPu");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("1qDeDr6rBoRHN61FZ2QzQFsBqFfDuAA6pXWhJFgssH1QVkSxFH");
        communicationtype.setCommTypeDescription("AHiYmms6rrFWfRk1hHd6wSvVNAAhUHufU7yj3vrriIjrJHY6qf");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("dwoiQBl2cz5DCPYpvMcAsL4P0jjtwFDbORpb9J9eiSo2aOcLxB");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("rq6Fs92d7t");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setEmailId("aWnPm33SMtVwXbkpXmtsEVCiJNQ8WIAgxcucb1FgPLO6rMyUZb");
            corecontacts.setNativeLastName("XMWgMJLMqpf8L7wC6JLqzKUulQfyaeyyiafPPsVz84JxIVrogn");
            corecontacts.setPhoneNumber("t3Zw2XHhIms0J6NHHk3V");
            corecontacts.setLastName("I9Ou665ClKH4OZFXa01nCLQ7bysAWz4Ls0my8GXNoAcV6LTgvM");
            corecontacts.setMiddleName("z8TCSHSEnWPVn7OJW6L30CE6PSeBpvkwCPS6FvlIXY62krkh0n");
            corecontacts.setNativeFirstName("DX6n0jsOjschXONtTu8Nf4gzPKJ1u4Q8kiVl8ggBVSHlxM7GfP");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1464507507897l));
            corecontacts.setVersionId(1);
            corecontacts.setFirstName("bODuuUePA501ae7NImnINwCjbX4x0YpcRq6GfepNB8fK6hM3xl");
            corecontacts.setNativeMiddleName("pJFkU0mLM0e0jcNGCDWJgUpp9MtaZeqlKlYO37yfj70rxVwY26");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1464507507935l));
            corecontacts.setNativeTitle("UkLh5tjY9VTiicbuNX0Xv77gj4So8ePtWBB4td78OvGSNcmjp6");
            corecontacts.setAge(106);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "JrCMvEe1o9r0WWMzex7jrW3nw0LJMpj8GNKlH6A1lWXMYJTWcPBfm7PYxe6xDTlqt1OKCTp6rOHSZmABuan7ekcf7MroMm21LHqvJT8EGo9Uy79asemcUdLoyI3sKxFgSkiygsAuOMNuKrvqTNrtYpb2eGUtN3yIpJkaAdRc8gI0Y1nbN9R6iFtFkdAb6JwsTOTpnNqwmBAgfdhWUAYQSDmew3Z74FAv9LRbdHY1GRDzqpWacecJNVgsmEvjkO47c"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "6ErzFJcQQApv0VkMmFTH87WgChsVjdxwG7FgRIMUdnhVpw765OYvB4mk85SteKz48OmuocqV3aL1HoqjC052s4LPp363nKDq4tUFOTR900KyI6gz28Y7R8WvjoEDJFma4entKegIijshqphSukioBTQoVCkVx86Ad4xOhyj4qzWMIhuZIohgZBMuRfXxiQLPi68S7tXf0TjwHXVAvIjX6Tdi3qrPIlmN3ncMF2SXaJfepSRjh4VRkCBGqZ54vcKac"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "JfGtABbE5q1EcWcS73Glcst04ncKHOEJoTD6gAbQLu4zmVf5KWcDfKwCCzIiLrXjulBgsUNPRJIHgCuCt8WoObXsjfD1bmohQVY4XBhc1tjnhanu4JSWhmaq9TmRO0pB7MkPMydGJue9uD7mZ19eocWz62YvRJyF0yRrrXaWmRad4K4VUtsENa58LkjmCqLYjNhJd1umvDu4uOIWcv4Na7bblsakPt2eMunFkDFOpSZfsYW2R2oeBmcaLsK0VnL7H"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "Nw710HnpVhgb5JMVriZ63qsjqeFDUkzeloieNKxcV3x5wvjRQH01tdHJP37WzGccH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "hxbnN2vKUabYXyRS2gwlUYzfzqTYPQSbi9G3idP3M5MSjwDK3mDgPEqh9n0wP9Lhh4KZVAx2o4k1DJSOtMeeycxu8LU1HA15puM4OjRNwyNRUt4GUFf3ZeRyFumi0A0973toFEvoxutSOLVY9GdMAIYqB7MKOsqBjD9ALz4VFuEb8V0IWNnB6ewDQ5TyS52f6CQZ4Q4iTgx29x3tfyL6rKDpHuGjE2XoqPnGET8bKszdoOgu7y09NrJeTUZY0jOlH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "CixFts4UEFUm6sF8Gk8cLPadyB4Bons727LbVmPQ6nizFt9jqwSevhFE7VAVIAWkAitA2QEtDw7YMtd8TzjmlH4S9hPo0lO9ed603U5synRRQ7p2co3aVcaQ9ELMYPs2Xu9kl1Oo7slWXZcsLO3ecSFRU3LVOYBIHv7sG67Luq6tlNAGOvUI7c0aQ0hx9WPmgOZ8JzjSUgzXlLxbyli6uIYItgAWB8UHqxQ1CSm7uv1qeSTI0TSNlyjKQASBIW0wX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "4bf7prAL1hrWQZgyU4OzD8dGaa4nIvttxpAv2MrrC8LLDLIFhMPSjpjVb2Fdq6BLo320SOgi6xTGgkmyfJxQSezbIcls3n3oVAfprLWb87Ob9qTRAMPyyZA18DaQA2S7GBtVuzbtW0E00ZJGoMzppIEe3ULe4hxuY4m7t4QmyLMSODMUMS27MzE9EANsfhshjiNk6MOIHUmIlX2w2HlG6oGW2GWFhwi6DbofKBkIMQmGhTneQ8M0xtsjv50nUEwgF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 228));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "e98zf6967xSVUA0ckPY3EYl4CEeft3cKpq5NA9o9oFBxclFSobf6X6LFrmZJPqD0phikMz3yMT33xPoe119WMdXPi0dt1D3YdVtDzkWskmciy9tY0Z0yXRiU06CNr3Ax0YSanksrXiXeLWpzrq5yq9Hsag2XQRgev6DFJ4OqOB35XhfaNYfkr4H5zkibbIVk3H1VgW8s0uAYnsdQwt8vLdHC3KG9cFpxzpUUaqGwKt8n1SywFgijI1CWbLsA2e1ra"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "BgGCpAWtOQe2Y8CQbR71A"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
