package cn.cncommdata.form.model;

import cn.cncommdata.form.model.fielddata.TableRow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: CopySource：表单详情数据拆分接口参数
 * @Version: v1.0
 * @Date: 2019-08-08 16:26
 * @Description:
 * @author chenpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopySource {
    /**
     * 被拆分的行id
     */
    private Long rowId;
    /**
     * 拆分后生成的行
     */
    private List<TableRow> tableRows;
}
