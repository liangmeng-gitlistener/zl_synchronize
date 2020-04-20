package cn.cncommdata.bean.data.row;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JsonRows {
    private String formId;
    private String editor;
    private String creator;
    private Map<String,Map<String,String>> data;
    //TODO
    private String qr_Code;
    private Integer index;
    //TODO
    private String treeNodeId;
    private Long editTime;
    private String type;
    //TODO
    private String copySourceId;
    //TODO
    private String version;
    //TODO
    private String treeId;
    //TODO
    private String formSources;
    //TODO
    private String ruleIds;
    //TODO
    private String deleted;
    //TODO
    private String originId;
    private Long createTime;
    private String tenantId;
    //TODO
    private List selectedRows;
    //TODO
    private String formStatus;
    private String id;
    private String status;
}
