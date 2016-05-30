Ext.define('Klickakart.klickakart.web.com.shop.controller.shoppingcontext.retail.ItemTemplateController', {
     extend: 'Klickakart.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.ItemTemplateController',
     onAddToCartClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#itemId_hiddenfield'), this.view.down('#qty_numberfield')];
          var invalidCompoentArray = this.validateComponents(componentArray);
          if (invalidCompoentArray.length > 0) {
               return;
          }
          var jsonData = {};
          jsonData.itemID = this.view.down('#itemId_hiddenfield').getValue();
          jsonData.qty = this.view.down('#qty_numberfield').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/Cart/',
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
                         Ext.Msg.alert('Server Response', 'Item Added To Cart');
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    Ext.Msg.alert('Server Response', 'Add To Cart Failed');
               }
          }, scope);
     }
});