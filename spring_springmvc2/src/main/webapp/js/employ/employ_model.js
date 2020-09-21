/**
 * Create Date: 2018/01/26
 * Description:
 * @author kiwipeach [1099501218@qq.com]
 */
var employModel = {
    URL: {
        ctx: '/',
        emp_home: function () {
            return this.ctx + '/employ/home';
        },
        emp_query_all: function () {
            return this.ctx + '/employ/queryAll';
        },
        emp_update: function (empno) {
            return this.ctx + '/employ/update?empno=' + empno;
        },
        emp_delete: function (empno) {
            return this.ctx + '/employ/delete/' + empno;
        },
        emp_deptinfo: function (empno) {
            return this.ctx + '/employ/queryEmpDeptMsg/' + empno;
        }
    },
    getDom: function (domName) {
        return $('#' + domName);
    },
    /**
     * 加载table员工数据
     */
    ajaxReloadData: function () {
        $.ajax({
            url: employModel.URL.emp_query_all(),
            type: 'GET',
            data: {},
            success: function (data) {
                //TODO 查询所有员工信息
            }
        })
    },
    /**
     * 修改员工信息
     * @param empno
     */
    update: function (empno) {
        window.location.href = employModel.URL.emp_update(empno);
    },
    delete: function (empno) {
        var r = confirm("是否确认删除编号为[" + empno + "]该条记录")
        if (r == true) {
            $.ajax({
                url: employModel.URL.emp_delete(empno),
                type: 'DELETE',
                data: {},
                success: function (data) {
                    console.log(data);
                    var message = data.success == true ? '删除成功' : '删除失败';
                    alert(message);
                    window.location.href = employModel.URL.emp_home();
                }
            })
        } else {
            //do nothing
        }
    },
    deptInfoQuery: function (empno) {
        $.ajax({
            url: employModel.URL.emp_deptinfo(empno),
            type: 'GET',
            data: {},
            success: function (empDeptInfo) {
                var alertInfo = '';
                if (empDeptInfo.success) {
                    alertInfo = '姓名：'+empDeptInfo.data.ename + '    职业：' + empDeptInfo.data.job + '    经理：' + empDeptInfo.data.mgrName + '    部门：' + empDeptInfo.data.dname + '  地址：' + empDeptInfo.data.loc;
                }
                alert(alertInfo);
            }
        })
    }

}