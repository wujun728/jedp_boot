Ext.define("MyApp.controller.Users", {
    extend:'Ext.app.Controller',

    stores:['Users'],
    models:['User'],
    views:['user.List', 'user.Edit'],

    init:function ()
    {
        console.log("{MyApp.controller.Users} Initialized Users! This happens before the Application launch function is called");

        this.control({
            'userlist':{
                itemdblclick:this.editUser
            },
            'userlist button[action=sync]':{
                click:this.syncUser
            },
            'useredit button[action=save]':{
                click:this.updateUser
            }
        });
    },

    editUser:function (grid, record)
    {
        console.log('{MyApp.controller.Users} Double clicked on ' + record.get('name'));

        var view = Ext.widget('useredit');
        view.down('form').loadRecord(record);

    },

    updateUser:function (button)
    {
        console.log('{MyApp.controller.Users} clicked the SAVE button');

        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();

        record.set(values);
        win.close();

    },

    syncUser:function (button)
    {
        console.log('{MyApp.controller.Users} clicked the SYNC button');

        this.getUsersStore().sync();
    }
});