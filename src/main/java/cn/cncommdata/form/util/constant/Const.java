package cn.cncommdata.form.util.constant;

/**
 * @author libing.niu
 * @ClassName: Const
 * @description: 常量类
 * @date: 2019/9/26 11:22
 */
public final class Const {
    /**
     * 常量2
     */
    public static final int TWO = 2;
    /**
     * 常量4
     */
    public static final int FOUR = 4;
    /**
     * 过期时间
     */
    public static final int TIMEOUT = 24;
    /**
     * 常量10
     */
    public static final int TEN = 10;
    /**
     * 常量13
     */
    public static final int THIRTEEN = 13;
    /**
     * 常量16
     */
    public static final int SIXTEEN = 16;
    /**
     * 常量19
     */
    public static final int NINETEEN = 19;
    /**
     * 常量1000
     */
    public static final int THOUSAND = 1000;
    /**
     * 部门id
     */
    public static final Long DEPARTMENT = 1000000000000000001L;
    /**
     * 模板id
     */
    public static final Long TEMP_ID = 1240215509488767003L;
    /**
     * 常量T
     */
    public static final String STRT = "T";
    /**
     * 需要鉴权的数据的key
     */
    public static final String AUTHORITY_QUERY_DATA = "authorityQueryData";
    /**
     * 不需要鉴权的数据的key
     */
    public static final String NO_AUTHORITY_QUERY_DATA = "noAuthorityQueryData";
    /**
     * 获取数据权限的key
     */
    public static final String DATA_AUTH = "all";
    /**
     * 明细信息
     */
    public static final String DETAIL_INFORMATION = "明细信息";
    /**
     * 获取当前值的参数
     */
    public static final String USER_NAME = "username";
    /**
     * 编辑中
     */
    public static final String EDITING = "EDITING";
    /**
     * 删除
     */
    public static final String DELETED = "DELETED";
    /**
     * 新增审核
     */
    public static final String APPROVAL_CREATE = "APPROVAL_CREATE";
    /**
     * 取消编辑
     */
    public static final String CANCEL_EDIT = "CANCEL_EDIT";
    /**
     * 复制
     */
    public static final String COPY = "COPY";
    /**
     * 类型
     */
    public static final String TYPE = "type";
    /**
     * 值
     */
    public static final String VALUE = "value";
    /**
     * 名称
     */
    public static final String NAME = "name";
    /**
     * 表单定义中的fields
     */
    public static final String FIELD = "field";

    /**
     * current_order 当前进度的行序号
     */
    public static final String CURRENT_ORDER = "current_order";
    /**
     * lock 字段里面的锁
     */
    public static final String LOCK = "lock";
    /**
     * 字符串类型
     */
    public static final String STRING = "string";
    /**
     * 数字类型
     */
    public static final String NUMBER = "number";
    /**
     * 自增类型
     */
    public static final String INCREMENT = "increment";
    /**
     * 日期类型
     */
    public static final String DATE = "date";
    /**
     * 选择类型
     */
    public static final String SELECT = "select";
    /**
     * 文件类型
     */
    public static final String FILE = "file";
    /**
     * 图片类型
     */
    public static final String IMAGE = "image";
    /**
     * form类型
     */
    public static final String FORM = "form";
    /**
     * tree类型
     */
    public static final String TREE = "tree";
    /**
     * 引用类型
     */
    public static final String QUOTE = "quote";
    /**
     * 映射类型
     */
    public static final String FOREIGN = "foreign";
    /**
     * list类型
     */
    public static final String LIST = "list";
    /**
     * 当前值
     */
    public static final String CURRENT = "current";
    /**
     * 集合名称-formData
     */
    public static final String COLLECTION_FORM_DATA = "formData";
    /**
     * 集合名称-formDataApproval
     */
    public static final String COLLECTION_FORM_APPROVAL = "formDataApproval";
    /**
     * 当前版本
     */
    public static final String CURRENT_VERSION = "current_version";
    /**
     * 默认FormId缓存的数量，32约能缓存服务10个tenant
     */
    public static final int FORM_ID_CACHE_SIZE = 32;

    /**
     * 项目执行情况查询数据中间接口参数
     */
    /**
     * 内部研发项目
     */
    public static final String I_R_D_P = "内部研发项目";
    /**
     * 内部研发项目表单ID
     */
    public static final long I_R_D_P_F = 1237569872439939097L;
    /**
     * 外部研发项目
     */
    public static final String E_R_D_P = "外部研发项目";
    /**
     * 外部研发项目表单ID
     */
    public static final long E_R_D_P_F = 1237569872439939096L;
    /**
     * 生产技术准备
     */
    public static final String P_A_T_P = "生产技术准备";
    /**
     * 生产技术准备表单ID
     */
    public static final long P_A_T_P_F = 1237569872439939095L;
    /**
     * 产品安装调试
     */
    public static final String P_I_A_D = "产品安装调试";
    /**
     * 产品安装调试表单ID
     */
    public static final long P_I_A_D_F = 1237569872439939094L;
    /**
     * 进度计划维护
     */
    public static final String S_M = "进度计划维护";
    /**
     * 进度计划维护表单ID
     */
    public static final long S_M_F = 1237569872439939093L;

    /**
     * 项目名称
     */
    public static final String E_N = "项目名称";

    /**
     * 项目类别
     */
    public static final String P_T = "项目类别";

    /**
     * 负责人
     */
    public static final String L_C = "负责人";

    /**
     * 计划项目开始日期
     */
    public static final String P_P_S_D = "计划项目开始日期";

    /**
     * 计划项目结束日期
     */
    public static final String P_P_E_D = "计划项目结束日期";

    /**
     * 每天86400000毫秒
     */
    public static final String M_P_D = "86400000";

    /**
     * 计划工期
     */
    public static final String P_P_D = "计划工期";

    /**
     * 状态
     */
    public static final String STATUS = "状态";

    /**
     * 实际工期
     */
    public static final String A_C_T = "实际工期";

    /**
     * 实际开始时间
     */
    public static final String A_S_T = "实际开始时间";

    /**
     * 实际完成时间
     */
    public static final String A_E_T = "实际完成时间";

    /**
     * 功能
     */
    public static final String FUNCTION = "功能";

    /**
     * 详情
     */
    public static final String DETAIL = "详情";

    /**
     * 等于
     */
    public static final String EQUAL = "等于";

    /**
     * 项目编号
     */
    public static final String P_N = "项目编号";

    /**
     * 进度计划ID
     */
    public static final String S_ID = "进度计划ID";

    /**
     * 任务名称
     */
    public static final String TASK = "任务名称";

    /**
     * 标准工期
     */
    public static final String S_T_L = "标准工期";

    /**
     * 进度负责人
     */
    public static final String P_L = "进度负责人";

    /**
     * 优先级
     */
    public static final String PRIORITY = "优先级";

    /**
     * 计划开始时间
     */
    public static final String P_S_T = "计划开始时间";

    /**
     * 计划完成时间
     */
    public static final String P_E_T = "计划完成时间";

    /**
     * 进度状态
     */
    public static final String S_S = "进度状态";

    private Const() {
    }
}
