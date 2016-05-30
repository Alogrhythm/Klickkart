Ext.define('Klickakart.klickakart.web.com.shop.controller.shoppingcontext.retail.OrderProcessingController', {
     extend: 'Klickakart.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.OrderProcessingController',
     onPaymnetClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#amount_numberfield'), this.view.down('#ccHolderName_textfield'), this.view.down('#creditCardNo_textfield'), this.view.down('#customerId_textfield'), this.view.down('#cvvNo_textfield'), this.view.down('#expiryMonth_numberfield'), this.view.down('#expiryYear_numberfield')];
          var invalidCompoentArray = this.validateComponents(componentArray);
          if (invalidCompoentArray.length > 0) {
               return;
          }
          var jsonData = {};
          jsonData.amount = this.view.down('#amount_numberfield').getValue();
          jsonData.ccHolderName = this.view.down('#ccHolderName_textfield').getValue();
          jsonData.creditCardNo = this.view.down('#creditCardNo_textfield').getValue();
          jsonData.customerId = this.view.down('#customerId_textfield').getValue();
          jsonData.cvvNo = this.view.down('#cvvNo_textfield').getValue();
          jsonData.expiryMonth = this.view.down('#expiryMonth_numberfield').getValue();
          jsonData.expiryYear = this.view.down('#expiryYear_numberfield').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/OrderProcessingWS/orderProcessing',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               maskEnable: true,
               maskEle: scope,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     },
     onOrderProcessAfterrender: function(me, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/CartTotalWS/cartTotal',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         scope.sender.down('#orderTotal_displayfield').setValue(responseData.cartSubtotal);
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});