Ext.define('Klickakart.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Klickakart.view.appreportui.ReportViewController',
	            'Klickakart.view.appreportui.datagrid.DataGridPanel',
	            'Klickakart.view.appreportui.datagrid.DataGridView',
	            'Klickakart.view.appreportui.querycriteria.QueryCriteriaView',
	            'Klickakart.view.appreportui.chart.ChartView',
	            'Klickakart.view.appreportui.datapoint.DataPointView',
	            'Klickakart.view.googlemaps.map.MapPanel',
	            'Klickakart.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
