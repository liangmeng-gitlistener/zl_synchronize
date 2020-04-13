package cn.cncommdata.form.model.fielddata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBase  implements Serializable {
    /**
     * 数据类型
     */
    private String type;

}
