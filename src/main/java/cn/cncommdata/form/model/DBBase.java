package cn.cncommdata.form.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.metadata.model
 * @date:2019-03-27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBBase {
    /**
     * id
     */

    private Long id;
    /**
     * 租户ID
     */
    @Field("tenant_id")
    private Long tenantId;
    /**
     * 删除标记
     */
    private Boolean deleted;
    /**
     * 创建者GrantId
     */
    private Long creator;
    /**
     * 创建时间
     */
    @Field("create_time")
    private Long createTime;
    /**
     * 更新者GrantId
     */
    private Long editor;
    /**
     * 更新时间
     */
    @Field("edit_time")
    private Long editTime;

//    public DBBase(Long id, Long tenantId, Boolean deleted, Long creator, Date createTime, Long editor, Date editTime) {
//        this.id = id;
//        this.tenantId = tenantId;
//        this.deleted = deleted;
//        this.creator = creator;
//        this.createTime = createTime;
//        this.editor = editor;
//        this.editTime = editTime;
//    }
}
