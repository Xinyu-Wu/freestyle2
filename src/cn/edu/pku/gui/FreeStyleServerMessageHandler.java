/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import FMessage.MessageHandler;
import FMessage.TransmittedMessage;
/**
 * FreeStyle服务端消息处理器
 * @author sq
 */
public class FreeStyleServerMessageHandler extends  MessageHandler{
    
    public FreeStyleServerMessageHandler(String owner) {
        super(owner);
    }
    
    @Override
    public TransmittedMessage UserSignUp(TransmittedMessage transMsg) throws Exception
    {
        //TODO
        //写入数据库
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignIn 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getErrorMsg(transMsg, "UserSignIn 处理函数为空");
        }
    }
    
}
