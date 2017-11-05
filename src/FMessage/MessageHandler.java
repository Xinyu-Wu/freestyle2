/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * 消息处理器
 *
 * @author sq
 */
public class MessageHandler {

    private String Owner;


    
    public MessageHandler(String owner) {
        this.Owner = owner;
    }

    /**
     * 获取所属者
     *
     * @return
     */
    public String getOwner() {
        return this.Owner;
    }

    public boolean isDebug() {
        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        boolean isDebug = false;
        for (String arg : args) {
            if (arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")) {
                isDebug = true;
                break;
            }
        }
        return isDebug;
    }

    /**
     * 打招呼
     *
     * @param helloMsg 消息
     * @return
     * @throws Exception
     */
    public TransmittedMessage helloHello(TransmittedMessage helloMsg) throws Exception {
        String receiver = helloMsg.getSender();
        String sender = helloMsg.getReceiver();
        long sendTime = helloMsg.getTimeStamp();
        String sendMsgId = helloMsg.getMessageId();
        String sendMsgType = helloMsg.getMessageType();
        FOperationCode sendCode = helloMsg.getCode();
        FOperationStatus sendStatus = helloMsg.getStatus();
        HashMap<String, Object> sendData = helloMsg.getData();
        if (sender != Owner || sendMsgType != "Request" || sendStatus != FOperationStatus.Send) {
            return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, sendData);
        } else {
            if (sendCode != FOperationCode.HelloWorld) {
                return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, sendData);
            } else {
                return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, sendData);
            }
        }

    }

    /**
     * 用户注册
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserSignUp(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignUp 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserSignUp 处理函数为空");
        }
    }

    /**
     * 用户登录
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserSignIn(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignIn 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserSignIn 处理函数为空");
        }
    }

    /**
     * 用户登出
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserSignOut(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignOut 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserSignOut 处理函数为空");
        }
    }

    /**
     * 用户聊天信息上传
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserChatLoadUp(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserChatLoadUp 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserChatLoadUp 处理函数为空");
        }
    }

    /**
     * 用户聊天信息广播
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserChatBroadcast(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserChatBroadcast 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserChatBroadcast 处理函数为空");
        }
    }

    /**
     * 添加用户到工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserAddToProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserAddToProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserAddToProject 处理函数为空");
        }
    }

    /**
     * 获取用户工程列表
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserProjectUserList(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserProjectUserList 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserProjectUserList 处理函数为空");
        }
    }

    /**
     * 将用户从工程中删除
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserDeleteFromProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserDeleteFromProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserDeleteFromProject 处理函数为空");
        }
    }

    /**
     * 服务器刷新
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverRefresh(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverRefresh 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "SeverRefresh 处理函数为空");
        }
    }

    /**
     * 服务器崩溃
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverBreakDown(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverBreakDown 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "SeverBreakDown 处理函数为空");
        }
    }

    /**
     * 服务器重启
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverRestart(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverRestart 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "SeverRestart 处理函数为空");
        }
    }

    /**
     * 服务器维护
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverMaintain(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverMaintain 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "SeverMaintain 处理函数为空");
        }
    }

    /**
     * 服务器传输数据
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverTransportData(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverTransportData 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "SeverTransportData 处理函数为空");
        }
    }

    /**
     * 获取工程列表
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetProjectList(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetProjectList 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetProjectList 处理函数为空");
        }
    }

    /**
     * 获取工程内容
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetProjectContetnt(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetProjectContetnt 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetProjectContetnt 处理函数为空");
        }
    }

    /**
     * 获取图层内容
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetLayerContent(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerContent 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetLayerContent 处理函数为空");
        }
    }

    /**
     * 获取图层读锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetLayerReadLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerReadLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetLayerReadLock 处理函数为空");
        }
    }

    /**
     * 释放图层读锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage ReleaseLayerReadLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseLayerReadLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "ReleaseLayerReadLock 处理函数为空");
        }
    }

    /**
     * 获取查询结果
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetQueryResult(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetQueryResult 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetQueryResult 处理函数为空");
        }
    }

    /**
     * 获取暗中观察锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetSecretlyObserveLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetSecretlyObserveLock 处理函数为空");
        }
    }

    /**
     * 释放暗中观察锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage ReleaseSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseSecretlyObserveLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "ReleaseSecretlyObserveLock 处理函数为空");
        }
    }

    /**
     * 释放暗中观察锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SendSecretlyObserveData(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SendSecretlyObserveData 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "SendSecretlyObserveData 处理函数为空");
        }
    }

    /**
     * 获得图层写锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetLayerWriteLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerWriteLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "GetLayerWriteLock 处理函数为空");
        }
    }

    /**
     * 释放图层写锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage ReleaseLayerWriteLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseLayerWriteLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "ReleaseLayerWriteLock 处理函数为空");
        }
    }

    /**
     * 添加一个新图层
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage AddOneNewLayer(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("AddOneNewLayer 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "AddOneNewLayer 处理函数为空");
        }
    }

    /**
     * 删除一个图层
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage DeleteOneLayer(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteOneLayer 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "DeleteOneLayer 处理函数为空");
        }
    }

    /**
     * 添加要素
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage AddFeatures(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("AddFeatures 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "AddFeatures 处理函数为空");
        }
    }

    /**
     * 修改要素
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage ModifyFeatures(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ModifyFeatures 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "ModifyFeatures 处理函数为空");
        }
    }

    /**
     * 删除要素
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage DeleteFeatures(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteFeatures 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "DeleteFeatures 处理函数为空");
        }
    }

    /**
     * 创建新工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage CreateProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("CreateProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "CreateProject 处理函数为空");
        }
    }

    /**
     * 删除工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage DeleteProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "DeleteProject 处理函数为空");
        }
    }

    public TransmittedMessage instructionClassification(TransmittedMessage transMsg) throws Exception {
        FOperationCode sendCode = transMsg.getCode();
        switch (sendCode) {
            case HelloWorld:
                return this.helloHello(transMsg);
            case UserSignUp:
                return this.UserSignUp(transMsg);
            case UserSignIn:
                return this.UserSignIn(transMsg);
            case UserSignOut:
                return this.UserSignOut(transMsg);
            case UserChatLoadUp:
                return this.UserChatLoadUp(transMsg);
            case UserChatBroadcast:
                return this.UserChatBroadcast(transMsg);
            case UserAddToProject:
                return this.UserAddToProject(transMsg);
            case UserProjectUserList:
                return this.UserProjectUserList(transMsg);
            case UserDeleteFromProject:
                return this.UserDeleteFromProject(transMsg);
            case SeverRefresh:
                return this.SeverRefresh(transMsg);
            case SeverBreakDown:
                return this.SeverBreakDown(transMsg);
            case SeverRestart:
                return this.SeverRestart(transMsg);
            case SeverMaintain:
                return this.SeverMaintain(transMsg);
            case SeverTransportData:
                return this.SeverTransportData(transMsg);
            case GetProjectList:
                return this.GetProjectList(transMsg);
            case GetProjectContetnt:
                return this.GetProjectContetnt(transMsg);
            case GetLayerContent:
                return this.GetLayerContent(transMsg);
            case GetLayerReadLock:
                return this.GetLayerReadLock(transMsg);
            case ReleaseLayerReadLock:
                return this.ReleaseLayerReadLock(transMsg);
            case GetQueryResult:
                return this.GetQueryResult(transMsg);
            case GetSecretlyObserveLock:
                return this.GetSecretlyObserveLock(transMsg);
            case ReleaseSecretlyObserveLock:
                return this.ReleaseSecretlyObserveLock(transMsg);
            case SendSecretlyObserveData:
                return this.SendSecretlyObserveData(transMsg);
            case GetLayerWriteLock:
                return this.GetLayerWriteLock(transMsg);
            case ReleaseLayerWriteLock:
                return this.ReleaseLayerWriteLock(transMsg);
            case AddOneNewLayer:
                return this.AddOneNewLayer(transMsg);
            case DeleteOneLayer:
                return this.DeleteOneLayer(transMsg);
            case AddFeatures:
                return this.AddFeatures(transMsg);
            case ModifyFeatures:
                return this.ModifyFeatures(transMsg);
            case DeleteFeatures:
                return this.DeleteFeatures(transMsg);
            case CreateProject:
                return this.CreateProject(transMsg);
            case DeleteProject:
                return this.DeleteProject(transMsg);
            default:
                return this.getErrorMsg(transMsg, "没有相应的处理函数！");
        }
    }

    /**
     * 返回一条出错消息
     *
     * @param transMsg
     * @param errorMsg
     * @return
     */
    public TransmittedMessage getErrorMsg(TransmittedMessage transMsg, String errorMsg) {
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        //HashMap<String, Class> sendDataType = transMsg.getDataType();
        sendData.put("ReturnMsg", errorMsg);
        //sendDataType.put("ReturnMsg", String.class);
        if (sender != Owner || sendMsgType != "Request" || sendStatus != FOperationStatus.Send) {
            return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, sendData);
        } else {
            return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, sendData);
        }
    }

    public static void main(String[] args) throws Exception {
        String sender = "Tester";
        TransmittedMessageIDPool idPool = new TransmittedMessageIDPool(sender);
        String receiver = "Debuger";
        long timeStamp = System.currentTimeMillis() / 1000;
        String messageType = "Request";
        String messageId = idPool.getOneRandomID(sender);
        FOperationCode code = FOperationCode.HelloWorld;
        FOperationStatus status = FOperationStatus.Send;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("ProjectName", "MyFirstProject");
        data.put("LayerName", "MyFirstLayer");
        data.put("Feature", "MyFirserFeature");
        /*
        HashMap<String, Class> dataType = new HashMap<String, Class>();
        dataType.put("ProjectName", String.class);
        dataType.put("LayerName",String.class);
        dataType.put("Feature", String.class);*/
        TransmittedMessage sMessage = new TransmittedMessage(sender, receiver, timeStamp, messageType, messageId, code, status, data);
        //测试部分
        MessageHandler msgHandler = new MessageHandler("Debuger");
        TransmittedMessage sReturnMsg = msgHandler.helloHello(sMessage);
        JSONObject jObject = sReturnMsg.convertMessageToJson();
        System.out.print(jObject);
    }

}
