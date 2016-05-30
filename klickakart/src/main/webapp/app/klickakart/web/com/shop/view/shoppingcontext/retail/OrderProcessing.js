Ext.define('Klickakart.klickakart.web.com.shop.view.shoppingcontext.retail.OrderProcessing', {
     "xtype": "orderProcessing",
     "name": "orderProcess",
     "items": [{
          "xtype": "panel",
          "items": [{
               "xtype": "panel",
               "items": [{
                    "xtype": "textfield",
                    "fieldLabel": "Credit Card No",
                    "margin": 5,
                    "bindable": "creditCardNo",
                    "name": "creditCardNo",
                    "text": "creditCardNo",
                    "fieldName": "creditCardNo",
                    "displayName": "creditCardNo",
                    "fieldType": "java.lang.String",
                    "widget": "textfield",
                    "isField": true,
                    "itemId": "creditCardNo_textfield"
               }, {
                    "xtype": "numberfield",
                    "fieldLabel": "Amount",
                    "name": "amount",
                    "margin": 5,
                    "bindable": "amount",
                    "text": "amount",
                    "fieldName": "amount",
                    "displayName": "amount",
                    "fieldType": "java.lang.Double",
                    "widget": "numberfield",
                    "isField": true,
                    "itemId": "amount_numberfield"
               }, {
                    "xtype": "textfield",
                    "fieldLabel": "Card On Name",
                    "margin": 5,
                    "bindable": "ccHolderName",
                    "name": "ccHolderName",
                    "text": "ccHolderName",
                    "fieldName": "ccHolderName",
                    "displayName": "ccHolderName",
                    "fieldType": "java.lang.String",
                    "widget": "textfield",
                    "isField": true,
                    "itemId": "ccHolderName_textfield"
               }, {
                    "xtype": "textfield",
                    "fieldLabel": "CVV No",
                    "margin": 5,
                    "bindable": "cvvNo",
                    "name": "cvvNo",
                    "text": "cvvNo",
                    "fieldName": "cvvNo",
                    "displayName": "cvvNo",
                    "fieldType": "java.lang.String",
                    "widget": "textfield",
                    "isField": true,
                    "itemId": "cvvNo_textfield"
               }],
               "layout": {
                    "type": "vbox"
               },
               "border": true,
               "autoScroll": true,
               "margin": 5,
               "flex": 1,
               "dockedItems": [],
               "itemId": "panel_ext_23600"
          }, {
               "xtype": "panel",
               "items": [{
                    "xtype": "numberfield",
                    "fieldLabel": "ExpiryYear ",
                    "name": "expiryYear",
                    "margin": 5,
                    "bindable": "expiryYear",
                    "text": "expiryYear",
                    "fieldName": "expiryYear",
                    "displayName": "expiryYear",
                    "fieldType": "java.lang.Integer",
                    "widget": "numberfield",
                    "isField": true,
                    "itemId": "expiryYear_numberfield"
               }, {
                    "xtype": "numberfield",
                    "fieldLabel": "Expiry Month",
                    "name": "expiryMonth",
                    "margin": 5,
                    "bindable": "expiryMonth",
                    "text": "expiryMonth",
                    "fieldName": "expiryMonth",
                    "displayName": "expiryMonth",
                    "fieldType": "java.lang.Integer",
                    "widget": "numberfield",
                    "isField": true,
                    "itemId": "expiryMonth_numberfield"
               }, {
                    "xtype": "textfield",
                    "fieldLabel": "customerId",
                    "margin": 5,
                    "bindable": "customerId",
                    "name": "customerId",
                    "text": "customerId",
                    "fieldName": "customerId",
                    "displayName": "customerId",
                    "fieldType": "java.lang.String",
                    "widget": "textfield",
                    "isField": true,
                    "hidden": true,
                    "value": "101",
                    "itemId": "customerId_textfield"
               }],
               "layout": {
                    "type": "vbox"
               },
               "border": true,
               "autoScroll": true,
               "margin": 5,
               "flex": 1,
               "dockedItems": [],
               "itemId": "panel_ext_23685"
          }],
          "layout": {
               "type": "hbox"
          },
          "autoScroll": true,
          "border": true,
          "margin": 5,
          "dockedItems": [],
          "itemId": "panel_ext_22782"
     }, {
          "xtype": "grids",
          "name": "cartDetails",
          "title": "Cart Items",
          "autoScroll": true,
          "hiddenName": "Grid",
          "margin": 5,
          "collapseMode": "header",
          "border": true,
          "editTools": false,
          "features": [],
          "plugins": [{
               "ptype": "cellediting",
               "clicksToEdit": 1
          }],
          "columns": [{
               "xtype": "gridcolumn",
               "header": "Quantity",
               "name": "qty",
               "dataIndex": "qty",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Sub Total",
               "name": "subTotal",
               "dataIndex": "subTotal",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Item",
               "name": "itemName",
               "dataIndex": "itemName",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Item ID",
               "name": "itemId",
               "hidden": true,
               "dataIndex": "itemId",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Image",
               "name": "itemImg",
               "hidden": true,
               "dataIndex": "itemImg",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Stock",
               "name": "itemStock",
               "dataIndex": "itemStock",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Cost",
               "name": "itemPrice",
               "dataIndex": "itemPrice",
               "flex": 1
          }, {
               "xtype": "gridcolumn",
               "header": "Product",
               "name": "productDesc",
               "hidden": true,
               "dataIndex": "productDesc",
               "flex": 1
          }],
          "height": 300,
          "itemId": "cartDetails_grids",
          "store": {
               "autoLoad": true,
               "autoSync": true,
               "model": "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.FetchCartItemsModel",
               "proxy": {
                    "type": "ajax",
                    "url": "secure/FetchCartItemsWS/fetchCartItems",
                    "serviceId": "7839A293-91E8-4DC8-8E90-E6F0C226807F",
                    "serviceOperationId": "8CEBE108-7ED7-4E66-BEB8-861D3B37F3A5",
                    "serviceType": 3,
                    "actionMethods": {
                         "read": "POST"
                    },
                    "headers": {
                         "Content-Type": "application/json"
                    },
                    "extraParams": {},
                    "reader": {
                         "type": "json",
                         "rootProperty": "response.data"
                    }
               }
          },
          "tools": [{
               "type": "refresh",
               "tooltip": "Refresh Grid Data",
               "handler": "onGridRefreshClick"
          }]
     }, {
          "xtype": "button",
          "name": "paymnet",
          "text": "Process Payment",
          "margin": 5,
          "height": "300",
          "width": "1000",
          "icon": "images/1464514751354.png",
          "itemId": "paymnet_button",
          "listeners": {
               "click": "onPaymnetClick"
          }
     }, {
          "xtype": "displayfield",
          "fieldLabel": "<b>Order Total <\/b>",
          "margin": 5,
          "bindable": "orderTotal",
          "style": "word-break: break-word; word-wrap: break-word;",
          "name": "orderTotal",
          "itemId": "orderTotal_displayfield"
     }],
     "border": true,
     "autoScroll": true,
     "title": "Order Processing",
     "margin": 5,
     "dockedItems": [],
     "itemId": "orderProcess_orderProcessing",
     "requires": ["Klickakart.klickakart.shared.com.shop.model.shoppingcontext.FetchCartItemsModel", "Klickakart.klickakart.web.com.shop.controller.shoppingcontext.retail.OrderProcessingController", "Klickakart.klickakart.shared.com.shop.viewmodel.shoppingcontext.retail.OrderProcessingViewModel", "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.retail.OrderProcessingModel", "Klickakart.view.fw.component.Grids"],
     "extend": "Ext.form.Panel",
     "listeners": {
          "afterrender": "onOrderProcessAfterrender",
          "scope": "controller"
     },
     "viewModel": "OrderProcessingViewModel",
     "controller": "OrderProcessingController"
});