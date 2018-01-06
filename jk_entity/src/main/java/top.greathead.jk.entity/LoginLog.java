package top.greathead.jk.entity;

import java.util.Date;

public class LoginLog {
    private String id;
    private String loginName;
    private String ipAddress;
    private Date loginTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginLog loginLog = (LoginLog) o;

        if (id != null ? !id.equals(loginLog.id) : loginLog.id != null) return false;
        if (loginName != null ? !loginName.equals(loginLog.loginName) : loginLog.loginName != null) return false;
        if (ipAddress != null ? !ipAddress.equals(loginLog.ipAddress) : loginLog.ipAddress != null) return false;
        if (loginTime != null ? !loginTime.equals(loginLog.loginTime) : loginLog.loginTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
        return result;
    }
}
