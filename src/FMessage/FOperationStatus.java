/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作状态
 * @author sq
 */
public enum FOperationStatus {
    Send("01", "发送者发送"),
    Return("02", "接收者正常响应并回复"),
    Refuse("03", "接收者拒绝请求（权限不足）"),
    Wait("04", "接收者告知等待（正忙）"),
    Ignore("05", "接收者收到但不做回应或更改"),
    Error("06", "接收者出错"),
    WTF("07", "接收者不理解请求");

    private final String value;
    private final String Description;

    /**
     * 构造器默认也只能是private, 从而保证构造函数只能在内部使用
     *
     */
    private FOperationStatus(String value, String desc) {
        this.value = value;
        this.Description = desc;
    }

    /**
     * 得到操作状态的值
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 得到操作状态的说明
     * @return
     */
    public String getDescription() {
        return Description;
    }

    // Implementing a fromString method on an enum type
    private static final Map<String, FOperationStatus> stringToEnum = new HashMap<String, FOperationStatus>();
    static {
        // Initialize map from constant name to enum constant
        for(FOperationStatus status : values()) {
            stringToEnum.put(status.value, status);
        }
    }
    
    /**
     * 从字符串获取操作状态
     * @param symbol
     * @return
     */
    public static FOperationStatus fromString(String value) {
        return stringToEnum.get(value);
    }

    @Override
    public String toString() {
        return value;
    }
    public static void main(String[] args) throws Exception {
        FOperationStatus sCode = FOperationStatus.Send;
        System.out.println(sCode.name());
        System.out.println(sCode.ordinal());
        System.out.println(sCode.value);
        System.out.println(sCode.Description);
    }

}
