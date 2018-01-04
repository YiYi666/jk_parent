package com.yaorange.webservice;

/**报运结果对象
 * @author coach tam
 * @date 2018/1/4
 */
public class ExportResult {
    private String exportId;
    private String state;
    private String remark;

    public String getExportId() {
        return exportId;
    }

    public void setExportId(String exportId) {
        this.exportId = exportId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
