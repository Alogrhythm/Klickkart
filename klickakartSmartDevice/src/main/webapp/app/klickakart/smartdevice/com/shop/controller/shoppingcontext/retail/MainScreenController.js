Ext.define('Klickakart.klickakart.smartdevice.com.shop.controller.shoppingcontext.retail.MainScreenController', {
     extend: 'Klickakart.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.MainScreenController',
     onFormExt3304Afterrender: function(me, eOpts) {
          var jsonData = {};
          var scope = this.getView();
          Ext.Ajax.request({
               url: AppRestUrl + 'secure/FetchLoggedInUserWS/fetchLoggedInUser',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         scope.sender.down('#welcomeMsg_displayfield').setValue(responseData.loginCorecontactsLastname);
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
     onItemsClick: function(me, e, eOpts) {
          var formComponent = new Ext.create('Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.ProductCatelogue');
          formComponent.title = 'ProductCatelogue';
          var tabs = Ext.getCmp('appMainTabPanel');
          tabs.remove(this.getView());
          tabs.add(formComponent);
          tabs.setActiveTab(formComponent);
     },
     onOrderClick: function(me, e, eOpts) {
          var formComponent = new Ext.create('Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.OrderProcessinggg');
          formComponent.title = 'OrderProcessinggg';
          var tabs = Ext.getCmp('appMainTabPanel');
          tabs.remove(this.getView());
          tabs.add(formComponent);
          tabs.setActiveTab(formComponent);
     }
});