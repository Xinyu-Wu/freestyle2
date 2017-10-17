/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * 消息处理器
 *
 * @author sq
 */
public class MessageHandler {

    private String Owner;
    public MessageProcessor HelloWorld;
    public MessageProcessor UserSignUp;
    public MessageProcessor UserSignIn;
    public MessageProcessor UserSignOut;
    public MessageProcessor UserChatLoadUp;
    public MessageProcessor UserChatBroadcast;
    public MessageProcessor UserAddToProject;
    public MessageProcessor UserProjectUserList;
    public MessageProcessor UserDeleteFromProject;
    public MessageProcessor SeverRefresh;
    public MessageProcessor SeverBreakDown;
    public MessageProcessor SeverRestart;
    public MessageProcessor SeverMaintain;
    public MessageProcessor SeverTransportData;
    public MessageProcessor GetProjectList;
    public MessageProcessor GetProjectContetnt;
    public MessageProcessor GetLayerContent;
    public MessageProcessor GetLayerReadLock;
    public MessageProcessor ReleaseLayerReadLock;
    public MessageProcessor GetQueryResult;
    public MessageProcessor GetSecretlyObserveLock;
    public MessageProcessor ReleaseSecretlyObserveLock;
    public MessageProcessor SendSecretlyObserveData;
    public MessageProcessor GetLayerWriteLock;
    public MessageProcessor ReleaseLayerWriteLock;
    public MessageProcessor AddOneNewLayer;
    public MessageProcessor DeleteOneLayer;
    public MessageProcessor AddFeatures;
    public MessageProcessor ModifyFeatures;
    public MessageProcessor DeleteFeatures;
    public MessageProcessor CreateProject;
    public MessageProcessor DeleteProject;

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

    public TransmittedMessage instructionClassification(TransmittedMessage transMsg) throws Exception {
        FOperationCode sendCode = transMsg.getCode();
        switch (sendCode) {
            case HelloWorld:
                if (HelloWorld != null) {
                    return HelloWorld.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "HelloWorld处理函数为空,not support yet");
                }    
            case UserSignUp:
                if (UserSignUp != null) {
                    return UserSignUp.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserSignUp处理函数为空,not support yet");
                }    
                case UserSignIn:
                if (UserSignIn != null) {
                    return UserSignIn.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserSignIn处理函数为空,not support yet");
                }    
                case UserSignOut:
                if (UserSignOut != null) {
                    return UserSignOut.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserSignOut 处理函数为空,not support yet");
                }    
                case UserChatLoadUp:
                if (UserChatLoadUp != null) {
                    return UserChatLoadUp.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserChatLoadUp 处理函数为空,not support yet");
                }    
                case UserChatBroadcast:
                if (UserChatBroadcast != null) {
                    return UserChatBroadcast.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserChatBroadcast 处理函数为空,not support yet");
                }    
                case UserAddToProject:
                if (UserAddToProject != null) {
                    return UserAddToProject.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserAddToProject 处理函数为空,not support yet");
                }    
                case UserProjectUserList:
                if (UserProjectUserList != null) {
                    return UserProjectUserList.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserProjectUserList 处理函数为空,not support yet");
                }    
                case UserDeleteFromProject:
                if (UserDeleteFromProject != null) {
                    return UserDeleteFromProject.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "UserDeleteFromProject 处理函数为空,not support yet");
                }    
                case SeverRefresh:
                if (SeverRefresh != null) {
                    return SeverRefresh.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SeverRefresh 处理函数为空,not support yet");
                }    
                case SeverBreakDown:
                if (SeverBreakDown != null) {
                    return SeverBreakDown.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SeverBreakDown 处理函数为空,not support yet");
                }
                case SeverRestart:
                if (SeverRestart != null) {
                    return SeverRestart.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SeverRestart 处理函数为空,not support yet");
                }
                case SeverMaintain:
                if (SeverMaintain != null) {
                    return SeverMaintain.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SeverMaintain 处理函数为空,not support yet");
                }
                case SeverTransportData:
                if (SeverTransportData != null) {
                    return SeverTransportData.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SeverTransportData 处理函数为空,not support yet");
                }
                case GetProjectList:
                if (GetProjectList != null) {
                    return GetProjectList.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "GetProjectList 处理函数为空,not support yet");
                }
                case GetProjectContetnt:
                if (GetProjectContetnt != null) {
                    return GetProjectContetnt.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "GetProjectContetnt 处理函数为空,not support yet");
                }
                case GetLayerContent:
                if (GetLayerContent != null) {
                    return GetLayerContent.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "GetLayerContent 处理函数为空,not support yet");
                }
                case GetLayerReadLock:
                if (SeverBreakDown != null) {
                    return SeverBreakDown.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SeverBreakDown 处理函数为空,not support yet");
                }
                case ReleaseLayerReadLock:
                if (ReleaseLayerReadLock != null) {
                    return ReleaseLayerReadLock.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "ReleaseLayerReadLock 处理函数为空,not support yet");
                }
                case GetQueryResult:
                if (GetQueryResult != null) {
                    return GetQueryResult.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "GetQueryResult 处理函数为空,not support yet");
                }
                case GetSecretlyObserveLock:
                if (GetSecretlyObserveLock != null) {
                    return GetSecretlyObserveLock.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "GetSecretlyObserveLock 处理函数为空,not support yet");
                }
                case ReleaseSecretlyObserveLock:
                if (ReleaseSecretlyObserveLock != null) {
                    return ReleaseSecretlyObserveLock.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "ReleaseSecretlyObserveLock 处理函数为空,not support yet");
                }
                case SendSecretlyObserveData:
                if (SendSecretlyObserveData != null) {
                    return SendSecretlyObserveData.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "SendSecretlyObserveData 处理函数为空,not support yet");
                }
                case GetLayerWriteLock:
                if (GetLayerWriteLock != null) {
                    return GetLayerWriteLock.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "GetLayerWriteLock 处理函数为空,not support yet");
                }
                case ReleaseLayerWriteLock:
                if (ReleaseLayerWriteLock != null) {
                    return ReleaseLayerWriteLock.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "ReleaseLayerWriteLock 处理函数为空,not support yet");
                }
                case AddOneNewLayer:
                if (AddOneNewLayer != null) {
                    return AddOneNewLayer.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "AddOneNewLayer 处理函数为空,not support yet");
                }
                case DeleteOneLayer:
                if (DeleteOneLayer != null) {
                    return DeleteOneLayer.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "DeleteOneLayer 处理函数为空,not support yet");
                }
                case AddFeatures:
                if (AddFeatures != null) {
                    return AddFeatures.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "AddFeatures 处理函数为空,not support yet");
                }
                case ModifyFeatures:
                if (ModifyFeatures != null) {
                    return ModifyFeatures.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "ModifyFeatures 处理函数为空,not support yet");
                }
                case DeleteFeatures:
                if (DeleteFeatures != null) {
                    return DeleteFeatures.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "DeleteFeatures 处理函数为空,not support yet");
                }
                case CreateProject:
                if (CreateProject != null) {
                    return CreateProject.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "CreateProject 处理函数为空,not support yet");
                }
                case DeleteProject:
                if (DeleteProject != null) {
                    return DeleteProject.MessageProcess(transMsg);
                } else {
                    return this.getErrorMsg(transMsg, "DeleteProject 处理函数为空,not support yet");
                }
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
        sendData.put("ReturnMsg", errorMsg);
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
        TransmittedMessage sMessage = new TransmittedMessage(sender, receiver, timeStamp, messageType, messageId, code, status, data);
        //测试部分
        MessageHandler msgHandler = new MessageHandler("Debuger");
        TransmittedMessage sReturnMsg = msgHandler.helloHello(sMessage);
        JSONObject jObject = sReturnMsg.convertMessageToJson();
        System.out.print(jObject);
    }

}
