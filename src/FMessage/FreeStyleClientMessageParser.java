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
public class FreeStyleClientMessageParser  extends MessageParser{
    
    public FreeStyleClientMessageParser(String owner) {
        super(owner);
    }
    
    public String UserSignUp(TransmittedMessage transMsg) throws Exception {
        String sender = transMsg.getSender();
        String receiver = transMsg.getReceiver();
        long sendTime = transMsg.getTimeStamp();
        String sendMsgId = transMsg.getMessageId();
        String sendMsgType = transMsg.getMessageType();
        FOperationCode sendCode = transMsg.getCode();
        FOperationStatus sendStatus = transMsg.getStatus();
        HashMap<String, Object> returnData = transMsg.getData();
        if (!sender.equals(this.getOwner()) || !"Response".equals(sendMsgType) || sendStatus != FOperationStatus.Send) {
            return null;
        } else {
            if (sendCode != FOperationCode.UserSignUp) {
                //根据对应的操作类型进行更改
                return null;
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
                    return null;
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
        return null;
    }
    
}
