/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import cn.edu.pku.datasource.DBManager;
import java.util.HashMap;

/**
 * 客户端消息处理器
 * @author sq
 */
public class FreeStyleClientMessageHandler extends MessageHandler {

    public DBManager dbManager;//感觉其实并不用DBManager

    public FreeStyleClientMessageHandler(String owner) {
        super(owner);
    }
    
    public FreeStyleClientMessageHandler(String owner, DBManager dbmanager) {
        super(owner);
        this.dbManager = dbmanager;
    }
    
    @Override
    public TransmittedMessage UserChatBroadcast(TransmittedMessage transMsg)
    {
        //获取transMsg 对应的参数
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        HashMap<String, Object> returnData = new HashMap<>();
        //检查消息的正确性
        if (!sender.equals(this.getOwner()) || !"Request".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            returnData.put("ReturnMsg", "!sender.equals(this.getOwner()) || !Request.equals(sendMsgType) || sendStatus != FOperationStatus.Send");
            return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, returnData);
        } else {
            if (sendCode != FOperationCode.UserChatBroadcast) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    /*
                    
                    */

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
        return null;
    }
    
    @Override
    public TransmittedMessage SeverRefresh(TransmittedMessage transMsg)
    {
        //获取transMsg 对应的参数
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        HashMap<String, Object> returnData = new HashMap<>();
        //检查消息的正确性
        if (!sender.equals(this.getOwner()) || !"Request".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            returnData.put("ReturnMsg", "!sender.equals(this.getOwner()) || !Request.equals(sendMsgType) || sendStatus != FOperationStatus.Send");
            return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, returnData);
        } else {
            if (sendCode != FOperationCode.SeverRefresh) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    /*
                    
                    */

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
        return null;
    }
    
    @Override
    public TransmittedMessage SeverBreakDown(TransmittedMessage transMsg)
    {
        //获取transMsg 对应的参数
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        HashMap<String, Object> returnData = new HashMap<>();
        //检查消息的正确性
        if (!sender.equals(this.getOwner()) || !"Request".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            returnData.put("ReturnMsg", "!sender.equals(this.getOwner()) || !Request.equals(sendMsgType) || sendStatus != FOperationStatus.Send");
            return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, returnData);
        } else {
            if (sendCode != FOperationCode.SeverBreakDown) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    /*
                    
                    */

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
        return null;
    }
    
    @Override
    public TransmittedMessage SeverMaintain(TransmittedMessage transMsg)
    {
        //获取transMsg 对应的参数
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        HashMap<String, Object> returnData = new HashMap<>();
        //检查消息的正确性
        if (!sender.equals(this.getOwner()) || !"Request".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            returnData.put("ReturnMsg", "!sender.equals(this.getOwner()) || !Request.equals(sendMsgType) || sendStatus != FOperationStatus.Send");
            return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, returnData);
        } else {
            if (sendCode != FOperationCode.SeverMaintain) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    /*
                    
                    */

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
        return null;
    }
    
    @Override
    public TransmittedMessage SeverTransportData(TransmittedMessage transMsg)
    {
        //获取transMsg 对应的参数
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        HashMap<String, Object> returnData = new HashMap<>();
        //检查消息的正确性
        if (!sender.equals(this.getOwner()) || !"Request".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            returnData.put("ReturnMsg", "!sender.equals(this.getOwner()) || !Request.equals(sendMsgType) || sendStatus != FOperationStatus.Send");
            return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, returnData);
        } else {
            if (sendCode != FOperationCode.SeverTransportData) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    /*
                    
                    */

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
        return null;
    }
    
}
