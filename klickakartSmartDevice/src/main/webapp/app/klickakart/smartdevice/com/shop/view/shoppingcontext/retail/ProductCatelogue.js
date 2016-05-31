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
               "url": AppRestUrl+"secure/FetchItemsWS/fetchItems",
               "serviceId": "8C8C74B5-5CCC-4764-B704-54A145C16CEF",
               "serviceType": 3,
               "serviceOperationId": "20F14EA7-36CD-4D73-BEB1-9E0DE83C7EF1",
               "requestMethodType": "POST"
          },
          "padding": 0,
          "margin": 5,
          "dockedItems": [],
          "itemId": "itemCatelogueList_listViewBaseView"
     }],
     "border": true,
     "autoScroll": false,
     "layout": "fit",
     "title": "Product Catelogue",
     "margin": 5,
     "dockedItems": [],
     "itemId": "panel_ext_16704",
     "requires": ["Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.TemplateOfItem", "Klickakart.klickakart.smartdevice.com.shop.controller.shoppingcontext.retail.ProductCatelogueController", "Klickakart.klickakart.shared.com.shop.viewmodel.shoppingcontext.retail.ProductCatelogueViewModel", "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.retail.ProductCatelogueModel", "Klickakart.view.fw.component.ListViewBaseView"],
     "extend": "Ext.panel.Panel",
     "viewModel": "ProductCatelogueViewModel",
     "controller": "ProductCatelogueController"
});