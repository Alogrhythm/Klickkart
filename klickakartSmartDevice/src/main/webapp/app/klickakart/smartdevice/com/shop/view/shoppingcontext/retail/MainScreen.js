Ext.define('Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.MainScreen', {
     "xtype": "mainScreen",
     "items": [{
          "xtype": "panel",
          "items": [{
               "xtype": "panel",
               "items": [{
                    "xtype": "displayfield",
                    "margin": 5,
                    "bindable": "welcomeee",
                    "style": "word-break: break-word; word-wrap: break-word;",
                    "value": "<I>Welcome !!<\/i>",
                    "name": "welcomeee",
                    "flex": 1,
                    "itemId": "welcomeee_displayfield"
               }, {
                    "xtype": "displayfield",
                    "margin": 5,
                    "bindable": "welcomeMsg",
                    "style": "word-break: break-word; word-wrap: break-word;",
                    "name": "welcomeMsg",
                    "flex": 1,
                    "itemId": "welcomeMsg_displayfield"
               }],
               "layout": {
                    "type": "hbox"
               },
               "autoScroll": true,
               "border": true,
               "margin": 5,
               "dockedItems": [],
               "itemId": "panel_ext_18907"
          }, {
               "xtype": "button",
               "name": "items",
               "text": "Item Catelogue",
               "margin": 5,
               "height": 100,
               "width": 300,
               "icon": "images/1464514751354.png",
               "itemId": "items_button",
               "listeners": {
                    "click": "onItemsClick"
               }
          }, {
               "xtype": "button",
               "name": "order",
               "text": "Order Processing",
               "margin": 5,
               "height": 100,
               "width": 300,
               "icon": "images/1464514751354.png",
               "itemId": "order_button",
               "listeners": {
                    "click": "onOrderClick"
               }
          }],
          "layout": {
               "type": "vbox"
          },
          "border": true,
          "autoScroll": true,
          "margin": 5,
          "dockedItems": [],
          "itemId": "panel_ext_3347"
     }],
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "dockedItems": [],
     "itemId": "form_ext_3304",
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onFormExt3304Afterrender",
          "scope": "controller"
     },
     "requires": ["Klickakart.klickakart.smartdevice.com.shop.controller.shoppingcontext.retail.MainScreenController", "Klickakart.klickakart.shared.com.shop.viewmodel.shoppingcontext.retail.MainScreenViewModel", "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.retail.MainScreenModel"],
     "viewModel": "MainScreenViewModel",
     "controller": "MainScreenController"
});