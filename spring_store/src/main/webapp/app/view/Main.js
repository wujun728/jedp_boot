Ext.define('MyApp.view.Main', {
    extend:'Ext.container.Viewport',
    layout:'fit',

    requires:[
        'MyApp.view.user.List',
        'MyApp.view.user.Edit'
    ],

    initComponent:function ()
    {
        this.items = {items: [
            {
                xtype: 'container'
                , html:'<div>Double-Click to edit</div>'
                , style: {
                    padding: '10px',
                    font: '1.2em'
                }
            },
            {
                xtype:'userlist'
            }
        ]};

        this.callParent();
    }
});