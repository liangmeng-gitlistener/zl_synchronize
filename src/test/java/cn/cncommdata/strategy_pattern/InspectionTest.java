package cn.cncommdata.strategy_pattern;

import cn.cncommdata.ZlSynchronizeApplicationTests;
import cn.cncommdata.strategy_pattern.my_abstract.InspectionSolver;
import cn.cncommdata.strategy_pattern.my_abstract.InspectionSolverChooser;
import cn.cncommdata.strategy_pattern.my_enum.InspectionConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 策略模式
 */
@Slf4j
@SpringBootTest
public class InspectionTest  extends ZlSynchronizeApplicationTests {
    @Autowired
    private InspectionSolverChooser chooser;

    @Test
    public void test(){
        //准备数据
        InspectionConstant taskType = InspectionConstant.BATCH_CHANGE_WAREHOUSE;
        Long orderId = 12345L;
        Long userId = 123L;

        //获取任务类型对应的solver
        InspectionSolver solver = chooser.choose(taskType);
        if (solver == null) {
            throw new RuntimeException("任务类型暂时无法处理!");
        }
        //调用不同solver的方法进行处理
        solver.solve(orderId,userId);
    }
}
