/*
 * Copyright 2017 KiWiPeach.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.demo.domain.dto;

/**
 * Create Date: 2018/01/27
 * Description: 员工部门信息
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class EmployDeptDTO {
    private String empno;
    private String ename;
    private String job;
    private String mgr;
    private String mgrName;
    private String deptno;
    private String dname;
    private String loc;

    public String getEmpno() {
        return empno;
    }

    public String getLoc() {
        return loc;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getMgr() {
        return mgr;
    }

    public void setMgr(String mgr) {
        this.mgr = mgr;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
