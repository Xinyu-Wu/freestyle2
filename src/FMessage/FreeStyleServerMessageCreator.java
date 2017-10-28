/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.HashMap;

/**
 *
 * @author sq
 */
public class FreeStyleServerMessageCreator extends MessageCreator{
    
    public FreeStyleServerMessageCreator(String owner) {
        super(owner);
    }
    
    @Override
    public TransmittedMessage UserChatBroadcast(String sReceiver, String msgID,String chatContent) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.UserChatBroadcast;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("UserChatContent", chatContent);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage SeverRefresh(String sReceiver, String msgID,String serverMsg) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.SeverRefresh;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("AttachedMessage", serverMsg);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage SeverBreakDown(String sReceiver, String msgID,String serverMsg) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.SeverBreakDown;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("AttachedMessage", serverMsg);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage SeverMaintain(String sReceiver, String msgID,String serverMsg) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.SeverMaintain;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("AttachedMessage", serverMsg);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
    
    @Override
    public TransmittedMessage SeverTransportData(String sReceiver, String msgID,String serverMsg) throws Exception {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = super.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.SeverTransportData;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        sendData.put("AttachedMessage", serverMsg);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
}
