Ext.define('Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.TemplateOfItem', {
     "xtype": "templateOfItem",
     "items": [{
          "xtype": "textfield",
          "fieldLabel": "Product",
          "margin": 5,
          "bindable": "productDesc",
          "name": "productDesc",
          "itemId": "productDesc_textfield"
     }, {
          "xtype": "textfield",
          "fieldLabel": "Item",
          "margin": 5,
          "bindable": "itemName",
          "name": "itemName",
          "itemId": "itemName_textfield"
     }, {
          "xtype": "image",
          "name": "itemImg",
          "height": 100,
          "width": 100,
          "border": true,
          "src": AppRestUrl+"",
          "margin": 5,
          "bindable": "itemImg",
          "bindSrc": "itemImg",
          "itemId": "itemImg_image",
          "bind": {
               "src": "{itemImg}"
          }
     }, {
          "xtype": "hiddenfield",
          "fieldLabel": "itemId",
          "bindable": "itemId",
          "margin": 5,
          "name": "itemId",
          "itemId": "itemId_hiddenfield"
     }, {
          "xtype": "numberfield",
          "fieldLabel": "Price",
          "name": "itemPrice",
          "margin": 5,
          "bindable": "itemPrice",
          "itemId": "itemPrice_numberfield"
     }, {
          "xtype": "numberfield",
          "fieldLabel": "Quantity",
          "name": "qty",
          "margin": 5,
          "bindable": "qty",
          "value": "0",
          "itemId": "qty_numberfield"
     }, {
          "xtype": "button",
          "name": "addToCart",
          "text": "Add To Cart",
          "margin": 5,
          "itemId": "addToCart_button",
          "listeners": {
               "click": "onAddToCartClick"
          }
     }],
     "layout": {
          "type": "vbox",
          "align": "center"
     },
     "border": true,
     "autoScroll": true,
     "margin": 5,
     "itemId": "panel_ext_16446",
     "dockedItems": [],
     "extend": "Ext.panel.Panel",
     "requires": ["Klickakart.klickakart.smartdevice.com.shop.controller.shoppingcontext.retail.TemplateOfItemController", "Klickakart.klickakart.shared.com.shop.viewmodel.shoppingcontext.retail.TemplateOfItemViewModel", "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.retail.TemplateOfItemModel"],
     "viewModel": "TemplateOfItemViewModel",
     "controller": "TemplateOfItemController"
});