/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import FMessage.FOperationCode;
import FMessage.FOperationStatus;
import FMessage.MessageHandler;
import FMessage.TransmittedMessage;
import cn.edu.pku.datasource.DBManager;
import java.util.HashMap;

/**
 * FreeStyle服务端消息处理器
 *
 * @author sq
 */
public class FreeStyleServerMessageHandler extends MessageHandler {

    public DBManager dbManager;

    public FreeStyleServerMessageHandler(String owner, DBManager dbmanager) {
        super(owner);
        this.dbManager = dbmanager;
    }

    @Override
    public TransmittedMessage UserSignUp(TransmittedMessage transMsg) throws Exception {
        //TODO
        String receiver = transMsg.getSender();
        String sender = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> sendData = transMsg.getData();
        HashMap<String, Object> returnData = new HashMap<>();
        if (!sender.equals(this.getOwner()) || !"Request".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            returnData.put("ReturnMsg", "!sender.equals(this.getOwner()) || !Request.equals(sendMsgType) || sendStatus != FOperationStatus.Send");
            return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, returnData);
        } else {
            if (sendCode != FOperationCode.UserSignIn) {
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //TODO
                try {
                    String userName = sendData.get("UserName").toString();
                    String password = sendData.get("Password").toString();
                    boolean isverfied = dbManager.verifyLogin(userName, password);
                    if (isverfied) {
                        returnData.put("ReturnMsg", "Welcome");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", "Not Welcome");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }

    }

    /**
     * 模板
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage Pattern(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserSignIn) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserSignIn");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    /*
                    String userName=sendData.get("UserName").toString();
                    String password = sendData.get("Password").toString();
                    boolean isverfied=dbManager.verifyLogin(userName, password);
                    if (isverfied) {
                      returnData.put("ReturnMsg", "Welcome");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    }
                    else
                    {
                       returnData.put("ReturnMsg", "Not Welcome");
                      return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }*/

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
