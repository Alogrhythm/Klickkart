Ext.define('Klickakart.view.smartdevice.design.drawer.main.MainPanel', {
    extend : 'Ext.panel.Panel',
    xtype : 'mainPanel',
    requires : ['Klickakart.view.googlemaps.map.MapPanel','Klickakart.view.smartdevice.searchengine.SearchEngineMainView','Klickakart.view.smartdevice.design.drawer.main.MainPanelController','Klickakart.view.smartdevice.design.resource.AppFormsList'],
    userinfo:{},
    id:'mainPanel',
    controller:'mainPanel',
    
    layout: {
        type: 'border'
    },

    items: [
            {
                region : 'west',
                itemId : 'westPanel',
                id:'westPanel',
                split : false,
                width : '80%',
                height:'100%',
                xtype : 'appFormsList',
                collapsible:false,
                hidden:true,
                title:"",
                autoScroll:true,
                style:{
                    background:"#ffffff"
                },
                header : {
        			titlePosition : 1,
                    style:"box-shadow: 1px 1px 1px 1px #888888;margin:0px 0px 5px 0px;",
        			items : [ {
        				xtype : 'image',
        				src : 'resources/appicons/logo.png',
        				id : 'userImg',
        				height : 50,
        				width : 50,
        				margin : '0 10 0 0'
        			}],
        			tools:[{ 
                               xtype: 'button',
                               text: '',
                               scale:'large',
                               icon:'resources/appicons/ic_drawer_collapse.png',
                               style:{
        	                 	  borderWidth:'0px'
        	                   },
                               margin:'0 0 0 10',
                               listeners:{
                                  click:'onDrawerBtnClick'
                               }
                   }]
        		}
            },{
                region : 'center',
                itemId : 'centerPanel',
                id:'centerPanel',
                title:'',
                layout:'fit',
                style:{
                    background:"#ffffff"
                },
                header:{

                         titlePosition:1,
                         style:{
				"box-shadow": "1px 1px 1px 1px #888888;margin:0px 0px 5px 0px;",
				"background":"#35baf6"
			 },
                         tools:[{
                     		xtype:'textfield',
                     		id:"searchTxt",
                     		itemId:'searchTxt',
                     		emptyText:'Search',
                     		margin:'0 5 0 5',
                            width:'18%',
                            height:40,
                            hidden:false,
                     		listeners:{
                     			focus : function(searchTxt){
                     				this.setWidth('50%');
                     			},
                     			blur : function(searchTxt){
                                    this.setWidth('18%');
                                }
                     		}
                         },{
                             xtype: 'button',
                             id:"smartSearchBtn",
                             text:'',
                             margin:'0 5 0 5',
                             scale:'large',
                             hidden:false,
                             style:{
        	                 	  borderWidth:'0px'
        	                 },
                             icon:'resources/appicons/ic_search.png',
                             listeners : {
                     			click : 'onSmartSearchClick'
                     		 }
                             
                          },{
                            xtype: 'button',
                            text:'',
                            scale:'large',
                            arrowVisible:false,
                            style:{
        	                 	  borderWidth:'0px'
        	                   },
                            icon:'resources/appicons/ic_action_navigation_more_vert.png',
                            arrowAlign:'bottom',
                            menu:{
                                items:[{
                                    xtype:'menuitem',
                                    height:35,
                                    text:'Logout',
                                    listeners:{
                                        click:'onLogoutMenuItemClick'
                                    }
                                }]
                            }
                         }],
                         items:[
                         { 
                               xtype: 'button',
                               text: '',
                               style:{
         	                 	  borderWidth:'0px'
         	                   },
                               scale:'large',
                               icon:'resources/appicons/ic_drawer_button.png',
                               margin:'0 10 0 0',
                               listeners:{
                                  click:'onDrawerBtnClick'
                               }
                         }
                         
                         ]
                },
                items:[
                    {
                        xtype:'panel',
                        title:'',
                        id:'appMainTabPanel',
                        layout:'fit',
                        itemId:'appMainTabPanel'
                    }
                ]

            }
    ],
    listeners:{
        afterrender:'onMainPanelAfterRender'
    }

});
