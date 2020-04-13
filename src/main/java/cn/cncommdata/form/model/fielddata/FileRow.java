package cn.cncommdata.form.model.fielddata;

import com.fasterxml.jackson.annotation.JsonRawValue;
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
@NoArgsConstructor
@AllArgsConstructor
public class FileRow  implements Serializable {
    /**
     * 文件名称
     */
    @JsonRawValue
    private String name;
    /**
     * 文件url
     */
    @JsonRawValue
    private String url;

}
