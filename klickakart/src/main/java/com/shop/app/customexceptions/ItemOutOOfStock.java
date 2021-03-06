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

public class ItemOutOOfStock extends BaseBusinessServiceException {


     private static final long serialVersionUID = 1664437692683632428L;


     /** Create ItemOutOOfStock with Alarm Id & Throwable.
      * @param _appAlarmId
      * @param _throwable
      */
     public ItemOutOOfStock(final String _appAlarmId, Throwable _throwable) {
          super("ItemOutOOfStock", _appAlarmId, _throwable);
     }

     /** Create ItemOutOOfStock with Message, Alarm Id & Throwable.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      */
     public ItemOutOOfStock(final String _msg, final String _appAlarmId, final Throwable _throwable) {
          super(_msg, _appAlarmId, _throwable);
     }

     /** Create ItemOutOOfStock with Message, Alarm Id, Throwable & Variable Arguments.
      * @param _msg
      * @param _appAlarmId
      * @param _throwable
      * @param _params
      */
     public ItemOutOOfStock(final String _msg, final String _appAlarmId, final Throwable _throwable, final Object..._params) {
          super(_msg, _appAlarmId, _throwable, _params);
     }

}