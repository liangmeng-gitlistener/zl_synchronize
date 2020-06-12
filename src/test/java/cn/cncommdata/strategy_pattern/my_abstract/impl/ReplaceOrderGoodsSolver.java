package cn.cncommdata.strategy_pattern.my_abstract.impl;

import cn.cncommdata.strategy_pattern.my_abstract.InspectionSolver;
import cn.cncommdata.strategy_pattern.my_enum.InspectionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ConcreteStrategy
 */
@Component
@Slf4j
public class ReplaceOrderGoodsSolver extends InspectionSolver {
    @Override
    public void solve(Long orderId, Long userId) {
        log.info("订单{}开始进行{}了", orderId,
                InspectionConstant.BATCH_REPLACE_ORDER_GOODS.getMessage());
    }

    @Override
    public InspectionConstant supports() {
        return InspectionConstant.BATCH_REPLACE_ORDER_GOODS;
    }
}
