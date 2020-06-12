package cn.cncommdata.strategy_pattern.my_abstract;

import cn.cncommdata.strategy_pattern.my_enum.InspectionConstant;

/**
 * Strategy
 */
public abstract class InspectionSolver {

    public abstract void solve(Long orderId, Long userId);
    public abstract InspectionConstant supports();
}
