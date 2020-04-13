package cn.cncommdata.form.model.fielddata;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: create by songhongzhe
 * @version: v1.0
 * @description: cn.cncommdata.form.model.fielddata
 * @date:2019-04-18
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageData extends DataBase {

    /**
     * 图片数据
     */
    @JsonRawValue
    private List<ImageRow> value;


}
