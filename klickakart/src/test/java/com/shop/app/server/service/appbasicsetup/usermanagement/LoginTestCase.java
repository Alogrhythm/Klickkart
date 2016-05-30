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
import com.shop.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.shop.app.shared.appbasicsetup.usermanagement.Login;
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
import com.shop.app.shared.organization.contactmanagement.CoreContacts;
import com.shop.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setIsDeleted(1);
        user.setSessionTimeout(1363);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("dj39fVky7bNmHxvOscRc5avqK4k4WdEe1HDzgrNhzbpFA4EJ1b");
        useraccessdomain.setDomainHelp("2cShASXJ7EGbzjars7rCSIHfOqQpjGYNQbcYfiiltXtOGJw7Ss");
        useraccessdomain.setDomainName("38RQhFgTnsdyRHlbZzhqBR2mGWxkRUNS9ILqxPo0kDh6Gh9wWJ");
        useraccessdomain.setDomainDescription("LlVSTEylTPelJ7dfvxa9IKMEG3MhzfVtpDJXUX0SjmtiNoQi2o");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("5zxNaDRI8FmEEg6rZKEkvSexQLnXFYgfkSUSnigoYS8ttc1doD");
        useraccesslevel.setLevelHelp("qz1geqfFWuWrxwYpUhrSavFU8MS3c7cRp9vCmY0gT9mni9mNIW");
        useraccesslevel.setLevelIcon("eWFvUD9bczTGUYe2txWt1k3huXh1K4axaD0WtaH4zvtE0bwZWl");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("qfDwDbsaJq2VP0hWe4SnVUDFJdGHrGXtGBxeqVyjPv9UOZOy41");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setSessionTimeout(529);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(34864);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setPasswordAlgo("RPSxs3scZmoEtbkO51zqu8J8UBu3yIxSc91AXDjlfSH23Y7Rsc");
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464507514920l));
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464507514920l));
        user.setIsLocked(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("BnlBrKr4BM8T1rOYttFHOv0IjZIDLgDwpQjGYq7GT4NkaGRO5r");
        question.setQuestion("e6HZt7K7S92jnBueVUIumFzKgx7kLxDssDy3svv6pSeyLO4OkF");
        question.setLevelid(5);
        question.setQuestionDetails("S0g5P72ODe");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("emrLi1c1E3o3XaYyjhWQEmP9foJtqkYP2roswu9ah3d8ZZ9TMZ");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("6Fenkitpdts6bEn2KchSU0bTnfYyAwSZ");
        userdata.setLast5Passwords("8dXG0OwdlWtXJgyOTTbGMxaAmedZRdMXBxgg14GsUcO3TQ1LKk");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464507515111l));
        userdata.setOneTimePasswordExpiry(5);
        userdata.setPassword("CH3vOacqrNDvuqDvnIrXXc1248htX4bdDjglaEhrGljuP42Xbz");
        userdata.setOneTimePassword("H8OIikOGDQVekA7U2NiwYadpRHGX1LpB");
        userdata.setLast5Passwords("sfxduOrOAiOKWtQyIjsen01sdhwa2T06pemDQZdLsFvTF8YhKy");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464507515127l));
        userdata.setOneTimePasswordExpiry(9);
        userdata.setPassword("rzIvhwQBDYhTFSxAi8BPV4l0U2RrpJSn26OLAiIGSRq9N1m92E");
        userdata.setUser(user);
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setEmailId("FYb7tJikx0ohqFHtyDwSXlBrvMtGosKjHbsyEFi4M5TvtWr6mX");
        corecontacts.setNativeLastName("5xasbsC33ZNNYv6Je9OEPON6JJ8Fcc7MR15ZwGagWzurJOBVex");
        corecontacts.setPhoneNumber("CeUDlg35Pmw5E8I1Nacz");
        corecontacts.setLastName("1THbUDFOA1xqub3n22dTkm3BFusGXnt0Ttfwues8dL9SdYuASf");
        corecontacts.setMiddleName("iq1fVnmh3ecUz5g9EPj5vMPlIy7LPr4njigcxxroVieEEFMqXh");
        corecontacts.setNativeFirstName("YYoWGpOBR03IwA2OK43awP7zqcVOcuR3xGnT3R74MV4UDeeR75");
        Title title = new Title();
        title.setTitles("MvSPglfy8L4y1L6buaja0m6XyBOu0g9j8zsFVVozEH5YY9myDb");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha4("YknY");
        language.setLanguage("QVWsaF6luuax496kWFnZ4DmcLXqU4RABoPsXY05J9P4ar48wKl");
        language.setAlpha2("Bg");
        language.setLanguageDescription("m1LxCpZgymma5RrU0OxbcgR2oSKTgbd4KGplxNXwjTrQln4jXC");
        language.setLanguageType("vdgKqeSYMp0h2m1R1JCeZ8C1BefnRHpb");
        language.setAlpha4parentid(2);
        language.setAlpha3("p7o");
        language.setLanguageIcon("biEyDqao9GKCU90zGmoc40SzrbFuwfMqwrlYLhOrPtJ9cXCtuB");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("CgzkanfBn5Qz2AL8QGeRU2VfSIAO9ir7qG742ZvloOcjOf4ULp");
        timezone.setCountry("P1qVTbPptFsaLIyJral6JsZfAo0fzaTrAGMuvSseAOO5UpTmSY");
        timezone.setGmtLabel("gjvRmA1CcjEPwKgb1FbkMQe6ndOEV9zsxb1PtgAlXNb5soupFE");
        timezone.setUtcdifference(3);
        timezone.setTimeZoneLabel("GhWOgqhvRzvTXjPgGnrWoTBl89edoCiMV6s0h8OVr24jpZAPBy");
        Gender gender = new Gender();
        gender.setGender("lhlYB1hBNXdpk30LHJmVbfkTUlAViBCFH1exepQ52yvC9tMSfV");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setEmailId("J8kI4gUW1OovqkJvBZByK0YXCvc3uZsxSexbOlGEIRVi1v4NAR");
        corecontacts.setNativeLastName("oKILHRKVHn7hSiOoW2cD0BT528nJOa2KxutqChGafhnxY95oSz");
        corecontacts.setPhoneNumber("WavctG07wltzoMaHuTXo");
        corecontacts.setLastName("SGLvmIdHeeVnb7XjfyKDPfaDwBf3hrOwlb4iNxbcRcQQcrmgDB");
        corecontacts.setMiddleName("YK5mf80N1Z5ADPFFbL29VwkAxzANsH7DPQ0GkG8wLJNriawtv2");
        corecontacts.setNativeFirstName("DFpfumOj5WTTDWc7NMzQSajZUYrHh3D0iitmFK2qujOL2gG2xW");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464507515280l));
        corecontacts.setFirstName("040WbUVpT08ZgiMUBCxHfYVhBRhc7g0bNudzu9gCsTRhmyU7E3");
        corecontacts.setNativeMiddleName("l4Z6Hp6Kc0BL8lBOQjOiAB7El610BqMh4p3QqvqFqxNMjsxCSn");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464507515316l));
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("T5nArREcgSkgoktVDguULPrYjzTrlMjnjL8KFGrHmfsqJkyaFP");
        corecontacts.setAge(66);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("ZNpd8Xs6uPBnSY4I0Oedp44ZpjtuJox6rgC8XfN4F23i83756Y");
        addresstype.setAddressTypeIcon("b528ZGV55L68exLjFWiCfNx8rRu7qqGkEcZy7XsbXYsd596YsS");
        addresstype.setAddressTypeDesc("T7FQ3h67gcc0c3wLLbJKF9CrwF0w8tSd1Y6K0K4p0fvxfXlbIB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCapitalLongitude(10);
        country.setCurrencySymbol("yeWg9sjIJyBz0o2jiF0c1FXCjiKl7ggb");
        country.setCountryFlag("y1nYii899zAdV73eGfeqpWfoKtEERBTJZttQahiGz448kxyR3t");
        country.setCountryCode2("0FC");
        country.setCountryCode1("5Eu");
        country.setCurrencyName("48ibcNAV1WHDTR0DBaso2YY6w5Z1UgtF4rjVMkjwjzpBTlpWtQ");
        country.setIsoNumeric(669);
        country.setCurrencyCode("TnY");
        country.setCapitalLatitude(1);
        country.setCountryName("awXyU3ooxmNI1IEOB9HCkI9deMegFT7veo1dY81Ey9KeX8W0ub");
        country.setCapital("uNbUFWWCIVemuhUDJM4S8GilfdQAs6Sc");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(5);
        State state = new State();
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("CeztdUADNfotH6xfxSevDZ9hGIu0m7Gl");
        state.setStateCapitalLatitude(9);
        state.setStateCode(1);
        state.setStateCodeChar3("gWvG43zc3Pi6XHW5nBJVElOJumuXMbUW");
        state.setStateCapital("ogCFJ31ea3rHxEHD4OPB5H3FOGDP3kMcyuBLOk9KB8gS3FuN7K");
        state.setStateCapitalLongitude(2);
        state.setStateDescription("NhsZ1fKIEWqVeKbFwjZd39Y1SxAdnAcxHx69KufhtVHcyVs8iV");
        state.setStateName("RZhjkMimnIOICGqbL9MlfuGF8FoUJiDXgDvBO6L11WTFkIFDVj");
        state.setStateFlag("LexfeCuYeo5zuCZ19kWNBbraoQKGlWfm0HhNH3v4oWEWxGeR5v");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(11);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("Rw5WeYlmMCvQbfWxyZp0LTGYQkfvvb73NYviFu07ZaKnrEjoHN");
        city.setCityCode(2);
        city.setCityDescription("YDWfOtvZGv9aJGSlewPXanOiLNKufQCkqDkDIAh55mfXlrpXG8");
        city.setCityCodeChar2("19k5r9LCbw5PIroUlnRbALKAXzPBbqEg");
        city.setCityLongitude(6);
        city.setCityFlag("JUO1FhhArdeE5IMU98lLg3EI5ibpnIZM6l71pKPOD76HDMST86");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("QZfcuBATDo3ZddAJIOfj5HtyeveCS1T03eoSTzmIJdNOgnNrdN");
        address.setAddressLabel("2wZ8kUHav2R");
        address.setZipcode("oMwmOU");
        address.setLongitude("LjRn3M7jDKgdJaJGrWRKYXUI7OFHeJJG6YMIaeF2cvzQdjVFSo");
        address.setAddress2("PlEvQg8fNCchdMtimbDIvMpTtRCudXKztbvmgjgKiHSLcXiaK3");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("7mzlUgUetI7JGysmlYdxSQloe7jA3NSJxUJRRWIpZ9hOauzd00");
        address.setLatitude("P5s6ROfhasjcxTgQBvKwXxOUfHe2qgxQM9Ur3evELdDALg49Jz");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("SOy3EylwryoO0U70Q4FbXMum10TNHjwRGDyuq8h0WLXDoTee17");
        communicationgroup.setCommGroupDescription("omfoXjEMfyDh5XJFNXMBcGOShbL6ITnVyh8ku3s2JY4KiVKc7O");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("bo0ep9YWPFSjexcNVm4gfZh54FVHPTMFijficBMoemnRqzVxTr");
        communicationtype.setCommTypeDescription("CndmY5bKz4FNmbyEY4edeW2oKNB7DfNg2hZWspBg1h2irSThn2");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("dTa9TvUgV58JvWLPMknyvbPm1Qntvp3lnTiDM5iKAHMcsl1vvf");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("FECWgIJx7h");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        login.setServerAuthImage("PbHkjneP3Es3EjPTLqQMFvpJQDT7jvKa");
        login.setServerAuthText("C9p1ZF1e9pUwpHLs");
        login.setFailedLoginAttempts(11);
        login.setLoginId("tIGltLQTIhdlNT5JUV7pmItSBE7JXMEYulzecRVHnauVoppjE7");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
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
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setServerAuthImage("xfbskDCZ6P5hbwRr2xeBNVYzNJTqfF4P");
            login.setServerAuthText("UFHMgoFAtTcIrQFQ");
            login.setFailedLoginAttempts(7);
            login.setLoginId("g63hUoYyhhM87JxcaY5h0TJNihkQpUzNUnXLGbiUVHj68LheJX");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "Z43LoiwWIo2I8eEppkSu4RfoTOXsUM9DLckWA23X2XMrfAsy6lRtdmIg31tSniyOKPJOcjE8BzNJqQzXv9NqjJeWJmdrQWVH7EUGltpwgxYQJxbvKxnZ0eM1kbUtDzJ43G8pZaFnZ6vg5PNXtvNE9W2V0tVwjtMYQvsJWulydqhfiwj0FmGoL7GykLLBvCpPS8cdlPZPk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "UMeVwW6VjU5S92RXJN2HvsnCeKpK5pCWd"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "bn6egM1UkdulReVga"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
