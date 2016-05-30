Ext.define('Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.ProductCatelogue', {
     "xtype": "productCatelogue",
     "items": [{
          "xtype": "listViewBaseView",
          "name": "itemCatelogueList",
          "isListPanel": true,
          "autoScroll": true,
          "border": false,
          "layout": "column",
          "defaults": {
               "columnWidth": "1.0"
          },
          "templateConfig": {
               "uiId": "BA704403-F700-43BD-B854-CD6216654B57",
               "uiClass": "Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.TemplateOfItem",
               "uiType": 2,
               "url": AppRestUrl+"secure/Item/findAll",
               "serviceId": "E44E11CC-72BE-4F9D-ABF8-5D4374E74288",
               "serviceType": 1,
               "serviceOperationId": "9AA1D44F-F46A-431A-9C7C-A027F196185A",
               "requestMethodType": "GET"
          },
          "padding": 0,
          "margin": 5,
          "itemId": "itemCatelogueList_listViewBaseView",
          "dockedItems": []
     }],
     "border": true,
     "autoScroll": false,
     "layout": "fit",
     "title": "Product Catelogue",
     "margin": 5,
     "itemId": "panel_ext_16704",
     "dockedItems": [],
     "requires": ["Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.TemplateOfItem", "Klickakart.klickakart.smartdevice.com.shop.controller.shoppingcontext.retail.ProductCatelogueController", "Klickakart.klickakart.shared.com.shop.viewmodel.shoppingcontext.retail.ProductCatelogueViewModel", "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.retail.ProductCatelogueModel", "Klickakart.view.fw.component.ListViewBaseView"],
     "extend": "Ext.panel.Panel",
     "viewModel": "ProductCatelogueViewModel",
     "controller": "ProductCatelogueController"
});