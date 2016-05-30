/**
 *Copyright (c) 2016 Applifire
 *Project : Klickakart(V1.0)
 */


package com.shop.app.customexceptions;

import com.spartan.pluggable.exception.core.BaseBusinessServiceException;


/**
 *Author : John S Doe
 *Date :Sun May 29 09:14:18 UTC 2016
 */

public class TransactionFailed extends BaseBusinessServiceException {


     private static final long serialVersionUID = 5929364046825424176L;


     /** Create TransactionFailed with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public TransactionFailed(final String _appAlarmId, Throwable _throwable) {
          super("TransactionFailed", _appAlarmId, _throwable);
     }

     /** Create TransactionFailed with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public TransactionFailed(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create TransactionFailed with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public TransactionFailed(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}