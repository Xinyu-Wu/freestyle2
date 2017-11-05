/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.HashMap;
import org.geotools.data.simple.SimpleFeatureSource;

/**
 *
 * @author sq
 */
public class FreeStyleClientMessageCreator extends MessageCreator{
    
    public FreeStyleClientMessageCreator(String owner) {
        super(owner);
    }
    
    
    @Override
    public TransmittedMessage helloHello(String sReceiver,String msgID,String username,String ipport, String operator) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.HelloWorld;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", username);
        sendData.put("IpPort", ipport);
        sendData.put("Operator", operator);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage UserSignUp(String sReceiver,String msgID,String user,String pwd) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserSignUp;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", user);
        sendData.put("Password", pwd);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage UserSignIn(String sReceiver, String msgID, String user, String pwd) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserSignIn;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", user);
        sendData.put("Password", pwd);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage UserSignOut(String sReceiver, String msgID, String user) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserSignOut;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", user);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
   
    @Override
    public TransmittedMessage UserChatLoadUp(String sReceiver, String msgID,String chatContent) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserChatLoadUp;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserChatContent", chatContent);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage UserAddToProject(String sReceiver, String msgID,String userTobeAdded,String ProjectTobeModified) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserAddToProject;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", userTobeAdded);
        sendData.put("ProjectName", ProjectTobeModified);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
     /**
     * 获取用户工程列表
     *
     *
     * @param sReceiver
     * @param msgID
     * @param userToBeQueried
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage UserProjectUserList(String sReceiver, String msgID,String userToBeQueried) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserProjectUserList;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", userToBeQueried);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage UserDeleteFromProject(String sReceiver, String msgID,String userTobeDeleted,String ProjectTobeModified) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserDeleteFromProject;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", userTobeDeleted);
        sendData.put("ProjectName", ProjectTobeModified);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetProjectList(String sReceiver, String msgID,String userTobeQueried) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetProjectList;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserName", userTobeQueried);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetProjectContetnt(String sReceiver, String msgID,String projectName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetProjectContetnt;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetLayerContent(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetLayerContent;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetLayerReadLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetLayerReadLock;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage ReleaseLayerReadLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.ReleaseLayerReadLock;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetQueryResult(String sReceiver, String msgID,String sql) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetQueryResult;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("SQL", sql);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetSecretlyObserveLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetSecretlyObserveLock;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage ReleaseSecretlyObserveLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.ReleaseSecretlyObserveLock;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage GetLayerWriteLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.GetLayerWriteLock;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage ReleaseLayerWriteLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.ReleaseLayerWriteLock;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage AddOneNewLayer(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.AddOneNewLayer;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage DeleteOneLayer(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.DeleteOneLayer;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage AddFeatures(String sReceiver, String msgID,String projectName,String layerName,SimpleFeatureSource simplefeature) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.AddFeatures;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        sendData.put("Features", simplefeature);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage ModifyFeatures(String sReceiver, String msgID,String projectName,String layerName,SimpleFeatureSource simplefeature) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.ModifyFeatures;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        sendData.put("Features", simplefeature);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage DeleteFeatures(String sReceiver, String msgID,String projectName,String layerName,SimpleFeatureSource simplefeature) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.DeleteFeatures;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        sendData.put("LayerName", layerName);
        sendData.put("Features", simplefeature);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage CreateProject(String sReceiver, String msgID,String projectName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.CreateProject;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage DeleteProject(String sReceiver, String msgID,String projectName) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.DeleteProject;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("ProjectName", projectName);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
}
