/**
 * The main application class. An instance of this class is created by app.js when it calls
 * Ext.application(). This is the ideal place to handle application launch and initialization
 * details.
 */
Ext.define('Klickakart.Application', {
    extend: 'Ext.app.Application',
    
    name: 'Klickakart',
requires : ["Ext.button.*","Ext.container.*","Ext.dashboard.*","Ext.dd.*","Ext.dom.*","Ext.event.*","Ext.flash.*","Ext.form.*","Ext.fx.*","Ext.grid.*","Ext.layout.*","Ext.menu.*","Ext.panel.*","Ext.picker.*","Ext.plugin.*","Ext.resizer.*","Ext.rtl.*","Ext.selection.*","Ext.slider.*","Ext.sparkline.*","Ext.state.*","Ext.tab.*","Ext.tip.*","Ext.toolbar.*","Ext.tree.*","Ext.util.*","Ext.view.*","Ext.window.*","Ext.Action","Ext.Component","Ext.ComponentLoader","Ext.ElementLoader","Ext.EventManager","Ext.FocusManager","Ext.Img","Ext.LoadMask","Ext.ProgressBar","Ext.ProgressBarWidget","Ext.ZIndexManager","Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.OrderProcessinggg","Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.MainScreen","Klickakart.view.smartdevice.reportview.ReportMainView","Klickakart.klickakart.smartdevice.com.shop.view.shoppingcontext.retail.ProductCatelogue","Klickakart.view.smartdevice.reportview.ReportMainView"],

    stores: [
        // TODO: add global / shared stores here
    ],
    
    launch: function () {
        // TODO - Launch the application
    }
});
