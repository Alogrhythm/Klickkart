/**
 *Copyright (c) 2016 Applifire
 *Project : Klickakart(V1.0)
 */


package com.shop.app.customexceptions;

import com.spartan.pluggable.exception.core.BaseBusinessServiceException;


/**
 *Author : John S Doe
 *Date :Sun May 29 08:56:40 UTC 2016
 */

public class InvalidCreditCard extends BaseBusinessServiceException {


     private static final long serialVersionUID = 362861644836645721L;


     /** Create InvalidCreditCard with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public InvalidCreditCard(final String _appAlarmId, Throwable _throwable) {
          super("Invalid Credit Card", _appAlarmId, _throwable);
     }

     /** Create InvalidCreditCard with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public InvalidCreditCard(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create InvalidCreditCard with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public InvalidCreditCard(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}