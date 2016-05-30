Ext.define('Klickakart.klickakart.web.com.shop.view.shoppingcontext.retail.ItemCatelogue', {
     "xtype": "itemCatelogue",
     "name": "itemcat",
     "items": [{
          "xtype": "listViewBaseView",
          "name": "item",
          "isListPanel": true,
          "autoScroll": true,
          "border": false,
          "layout": "column",
          "defaults": {
               "columnWidth": ".5"
          },
          "templateConfig": {
               "uiId": "F9DFC7DD-0CA7-4E1F-A375-6F42153085A7",
               "uiClass": "Klickakart.klickakart.web.com.shop.view.shoppingcontext.retail.ItemTemplate",
               "uiType": 2,
               "url": "secure/FetchItemsWS/fetchItems",
               "serviceId": "8C8C74B5-5CCC-4764-B704-54A145C16CEF",
               "serviceType": 3,
               "serviceOperationId": "20F14EA7-36CD-4D73-BEB1-9E0DE83C7EF1",
               "requestMethodType": "POST"
          },
          "padding": 0,
          "margin": 5,
          "itemId": "item_listViewBaseView",
          "dockedItems": []
     }],
     "border": true,
     "autoScroll": true,
     "title": "Item Catelogue",
     "margin": 5,
     "itemId": "itemcat_itemCatelogue",
     "dockedItems": [],
     "requires": ["Klickakart.klickakart.web.com.shop.view.shoppingcontext.retail.ItemTemplate", "Klickakart.klickakart.web.com.shop.controller.shoppingcontext.retail.ItemCatelogueController", "Klickakart.klickakart.shared.com.shop.viewmodel.shoppingcontext.retail.ItemCatelogueViewModel", "Klickakart.klickakart.shared.com.shop.model.shoppingcontext.retail.ItemCatelogueModel", "Klickakart.view.fw.component.ListViewBaseView"],
     "extend": "Ext.form.Panel",
     "viewModel": "ItemCatelogueViewModel",
     "controller": "ItemCatelogueController"
});