Ext.define('MyApp.view.user.List', {
    extend:'Ext.grid.Panel',
    alias:'widget.userlist',
    title:'All Users',
    store:'Users',

    initComponent:function ()
    {
        console.log("{MyApp.view.user.List} init user.List view");

        this.columns = [
            {header:'Name', dataIndex:'name', flex:1},
            {header:'Email', dataIndex:'email', flex:1}
        ];

        this.buttons = [
            {
                text:'Sync',
                action:'sync'
            }
        ];

        this.callParent(arguments);
    }
});