package com.shop.app.server.service.appbasicsetup.userrolemanagement.aspect;
import com.shop.app.server.service.aspect.ServiceAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;
import org.springframework.http.HttpStatus;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import com.spartan.healthmeter.msgWriter.config.ExecutionTimer;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import java.util.concurrent.atomic.AtomicLong;
import com.spartan.pluggable.logger.api.LogManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import java.util.UUID;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import org.eclipse.persistence.exceptions.TransactionException;
import com.spartan.pluggable.exception.security.InvalidDataException;
import com.spartan.pluggable.logger.alarms.EventAppLayers;
import com.spartan.pluggable.logger.alarms.EventAction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.annotation.Order;
import java.io.IOException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class ServiceuserrolemanagementAspect extends ServiceAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    @Autowired
    private Healthmeter healthmeter;

    public HttpStatus httpStatusCode;

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeservice;

    @Autowired
    private ExecutionTimer executionTimer;

    @Autowired
    private MetricRepository repository;

    public AtomicLong autoRequestId = new AtomicLong(1);

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Around("allOperation()")
    @Order(1)
    public Object aroundAdvice1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        HttpServletResponse response = servletWebRequest.getResponse();
        HttpSession session = request.getSession();
        long nextAutoNum = autoRequestId.getAndIncrement();
        methodCallStack.setRequestId(UUID.randomUUID().toString().toUpperCase());
        methodCallStack.setAppSessionId(getSessionId(request));
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            serviceLogic(session, request, response, methodCallStack.getRequestId(), methodCallStack.getAppSessionId());
            Object obj = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) obj;
            httpStatusCode = responseEntity.getStatusCode();
        } catch (com.spartan.pluggable.exception.core.AppBaseException e) {
            AppAlarm appAlarm = Log.getAlarm(e.getAppAlarmId());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getExceptionMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getAlarm("ABSRM144900500");
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), String.format(appAlarm.getMessage(), e.getMessage()));
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        }
        return responseEntity;
    }

    @Around("saveOperation()")
    @Order(2)
    public Object aroundAdvice2Save(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            Log.out.println("ABSRM112900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            aroundAdviceSaveAndUpdateLogin(proceedingJoinPoint);
            returnObject = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) returnObject;
            httpStatusCode = responseEntity.getStatusCode();
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
            Log.out.println("ABSRM147900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("ABS", "RM", EventAppLayers.WEB_SERVICE, EventAction.WRITE, e.getClass().getName());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
            if (appAlarm.getAppLayer() == EventAppLayers.WEB_SERVICE) {
                Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            }
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } finally {
            setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
        }
        return responseEntity;
    }

    @Around("updateOperation()")
    @Order(2)
    public Object aroundAdvice2Update(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            Log.out.println("ABSRM111900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            aroundAdviceSaveAndUpdateLogin(proceedingJoinPoint);
            returnObject = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) returnObject;
            httpStatusCode = responseEntity.getStatusCode();
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
            Log.out.println("ABSRM147900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("ABS", "RM", EventAppLayers.WEB_SERVICE, EventAction.UPDATE, e.getClass().getName());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
            if (appAlarm.getAppLayer() == EventAppLayers.WEB_SERVICE) {
                Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            }
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } finally {
            setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
        }
        return responseEntity;
    }

    @Around("deleteOperation()")
    @Order(2)
    public Object aroundAdvice2Delete(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            Log.out.println("ABSRM118900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            returnObject = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) returnObject;
            httpStatusCode = responseEntity.getStatusCode();
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
            Log.out.println("ABSRM147900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("ABS", "RM", EventAppLayers.WEB_SERVICE, EventAction.DELETE, e.getClass().getName());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
            if (appAlarm.getAppLayer() == EventAppLayers.WEB_SERVICE) {
                Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), String.format(appAlarm.getMessage(), e.getMessage()), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            }
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } finally {
            setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
        }
        return returnObject;
    }

    @Around("findOperation()||getOperation()")
    @Order(2)
    public Object aroundAdvicefindOperation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            Log.out.println("ABSRM114900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            returnObject = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) returnObject;
            httpStatusCode = responseEntity.getStatusCode();
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
            Log.out.println("ABSRM147900100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), methodCallDetails.getExecution_Time());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("ABS", "RM", EventAppLayers.WEB_SERVICE, EventAction.READ, e.getClass().getName());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
            if (appAlarm.getAppLayer() == EventAppLayers.WEB_SERVICE) {
                Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            }
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } finally {
            setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
        }
        return returnObject;
    }

    @AfterReturning("allOperation()")
    public void afterReturning(JoinPoint join) throws IOException {
        counterService.increment("counter.HttpStatus." + httpStatusCode.name() + "." + join.getSignature().getDeclaringType().getSimpleName() + "." + join.getSignature().getName() + ".calls");
        counterService.increment("counter.numberof.calls");
    }

    public String incrementUricounter(String className, String methodName) {
        counterService.increment(className + "." + methodName);
        Metric metric = repository.findOne("gauge." + className + "." + methodName + "");
        if (metric != null) {
            gaugeservice.submit(className + "." + methodName, (Double) metric.getValue() + 1);
        } else {
            gaugeservice.submit(className + "." + methodName, 1);
        }
        return className + "." + methodName;
    }

    @Around("allOtherOperation()")
    @Order(2)
    public Object aroundAdvice2AllOther(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            returnObject = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) returnObject;
            httpStatusCode = responseEntity.getStatusCode();
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
        } catch (Exception e) {
            AppAlarm appAlarm = Log.getExceptionAlarm("ABS", "RM", EventAppLayers.WEB_SERVICE, EventAction.READ_WRITE_UPDATE, e.getClass().getName());
            ResponseBean exceptionbean = new ResponseBean(appAlarm);
            exceptionbean.add("message", String.format(appAlarm.getMessage(), e.getMessage()));
            httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
            methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
            if (appAlarm.getAppLayer() == EventAppLayers.WEB_SERVICE) {
                Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
            }
            return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
        } finally {
            setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
        }
        return returnObject;
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..save*(..))")
    protected void saveOperation() {
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..update*(..))")
    protected void updateOperation() {
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..delete*(..))")
    protected void deleteOperation() {
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..find*(..))")
    protected void findOperation() {
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..get*(..)) && !within(com.spartan.server.session.bizService.SessionDataMgtService+)")
    protected void getOperation() {
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..*(..)) && !within(com.spartan.server.session.bizService.SessionDataMgtService+)")
    protected void allOperation() {
    }

    @Pointcut("execution(* com.shop.app.server.service.appbasicsetup.userrolemanagement..*(..)) && !within(com.spartan.server.session.bizService.SessionDataMgtService+) && ! findOperation() && ! saveOperation() && ! updateOperation() && ! deleteOperation()")
    protected void allOtherOperation() {
    }
}
