package cn.cncommdata.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderSchedule)实体类
 *
 * @author makejava
 * @since 2020-04-21 10:04:00
 */
@Getter
@Setter
public class OrderSchedule implements Serializable {
    private static final long serialVersionUID = 540458671139379157L;
    /**
    * 订单进度id
    */
    private Long id;
    /**
    * 流程生产订单
    */
    private String processProductOrder;
    /**
    * 生产订单编号
    */
    private String productOrderNum;
    /**
    * 产品名称
    */
    private String productName;
    /**
    * 客户名称
    */
    private String customer;
    /**
    * 合金状态
    */
    private String alloyState;
    /**
    * 本次订单计划卷数
    */
    private Integer planVolume;
    /**
    * 已完成卷
    */
    private Integer completedVolume;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;

    /**
     * 对象相等
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof OrderSchedule)) {
            return false;
        } else {
            OrderSchedule object = (OrderSchedule) o;
            if (!StrUtil.equals(processProductOrder, object.getProcessProductOrder())) {
                return false;
            }
            if (!StrUtil.equals(productOrderNum, object.getProductOrderNum())) {
                return false;
            }
            if (!StrUtil.equals(productName, object.getProductName())) {
                return false;
            }
            if (!StrUtil.equals(customer, object.getCustomer())) {
                return false;
            }
            if (!StrUtil.equals(alloyState, object.getAlloyState())) {
                return false;
            }
            if (!planVolume.equals(object.getPlanVolume())) {
                return false;
            }
            if (!completedVolume.equals(object.getCompletedVolume())) {
                return false;
            }
            return true;
        }
    }

    /**
     * 数据库已经存在
     * @param o
     * @return
     */
    public boolean alreadyInDB(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof OrderSchedule)) {
            return false;
        } else {
            OrderSchedule object = (OrderSchedule) o;
            if (!StrUtil.equals(processProductOrder, object.getProcessProductOrder())) {
                return false;
            }
            return true;
        }
    }
}