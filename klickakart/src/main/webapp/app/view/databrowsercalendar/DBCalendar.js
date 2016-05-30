Ext.define('Klickakart.view.databrowsercalendar.DBCalendar', {
	extend : 'Klickakart.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Klickakart.view.databrowsercalendar.DBCalendarController',
	             'Klickakart.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
