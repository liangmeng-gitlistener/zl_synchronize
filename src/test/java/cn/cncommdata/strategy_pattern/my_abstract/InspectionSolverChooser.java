package cn.cncommdata.strategy_pattern.my_abstract;
import	java.util.HashMap;

import cn.cncommdata.strategy_pattern.my_enum.InspectionConstant;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Context
 * 这里是在应用启动的时候，加载spring容器中所有InspectionSolver类型的处理器，
 * 放到InspectionSolverChooser的map容器中。注意是InspectionSolver类型，
 * 所以定义的处理器都得继承InspectionSolver，其次是spring容器中的才能加载，
 * 所以定义的处理器都得放到spring容器中
 */
@Component
public class InspectionSolverChooser implements ApplicationContextAware {
    private Map<InspectionConstant, InspectionSolver> chooseMap = new HashMap<>();

    private ApplicationContext context;

    public InspectionSolver choose(InspectionConstant type) {
        return chooseMap.get(type);
    }

    /**
     * 从Java EE5规范开始，Servlet中增加了两个影响Servlet生命周期的注解
     * 在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
     * 服务器加载servlet->servlet构造函数->PostConstruct->Init->Service
     * ->destroy->PreDestroy->服务器卸载servlet
     * Constructor >> @Autowired >> @PostConstruct
     * @PostConstruct 注解的方法将会在依赖注入完成后被自动调用。
     */
    @PostConstruct
    private void register() {
        Map<String, InspectionSolver> solverMap = context.getBeansOfType(InspectionSolver.class);
        for (InspectionSolver solver : solverMap.values()) {
            chooseMap.put(solver.supports(),solver);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}