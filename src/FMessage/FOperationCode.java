/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作代码
 *
 * @author sq
 */
public enum FOperationCode {
    HelloWorld("0101","你好"),
    UserSignUp("0201","用户注册"),
    UserSignIn("0202","用户登录"),
    UserSignOut("0203","用户登出"),
    UserChatLoadUp("0204","用户聊天上传"),
    UserChatBroadcast("0205","用户聊天广播"),
    UserAddToProject("0206","用户添加至工程"),
    UserProjectUserList("0207","获取某一工程的用户列表"),
    UserDeleteFromProject("0208","用户从工程中删除"),
    SeverRefresh("0301","服务器刷新"),
    SeverBreakDown("0302","服务器崩溃"),
    SeverRestart("0303","服务器重启"),
    SeverMaintain("0304","服务器维护"),
    SeverTransportData("0305","服务器传输数据"),
    GetProjectList("0401","获取工程列表"),
    GetProjectContetnt("0402","获取某工程内容"),
    GetLayerContent("0403","获取图层内容"),
    GetLayerReadLock("0404","获取图层读锁"),
    ReleaseLayerReadLock("0405","释放图层读锁"),
    GetQueryResult("0406","获取查询结果"),
    GetSecretlyObserveLock("0407","获取暗中观察锁"),
    ReleaseSecretlyObserveLock("0408","释放暗中观察锁"),
    SendSecretlyObserveData("0409","传输暗中观察数据"),
    GetLayerWriteLock("0501","获取图层写锁"),
    ReleaseLayerWriteLock("0502","释放图层写锁"),
    AddOneNewLayer("0503","添加新图层"),
    DeleteOneLayer("0504","删除一个图层"),
    AddFeatures("0505","添加要素"),
    ModifyFeatures("0506","修改要素"),
    DeleteFeatures("0507","删除要素"),
    CreateProject("0508","新建工程"),
    DeleteProject("0509","删除工程");
    
    private final String value;
    private final String Description;

    /**
     * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
     *
     */
    private FOperationCode(String value,String desc) {
        this.value = value;
        this.Description = desc;
    }

    /**
     * 获取指令值
     * @return
     */
    public String getValue() {
        return value;
    }
    
    /**
     * 获取说明
     * @return
     */
    public String getDescription()
    {
        return Description;
    }
    // Implementing a fromString method on an enum type
    private static final Map<String, FOperationCode> stringToEnum = new HashMap<String, FOperationCode>();
    static {
        // Initialize map from constant name to enum constant
        for(FOperationCode code : values()) {
            stringToEnum.put(code.value, code);
        }
    }
    
    /**
     * 从字符串获取操作状态
     * @param symbol
     * @return
     */
    public static FOperationCode fromString(String value) {
        return stringToEnum.get(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public static void main(String[] args) throws Exception {
        FOperationCode sCode = FOperationCode.HelloWorld;
        System.out.println(sCode.name());
        System.out.println(sCode.ordinal());
        System.out.println(sCode.value);
        System.out.println(sCode.Description);
    }
}
