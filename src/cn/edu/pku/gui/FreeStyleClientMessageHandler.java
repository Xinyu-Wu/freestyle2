/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.pku.gui;

import FMessage.MessageHandler;
import cn.edu.pku.datasource.DBManager;

/**
 * 客户端消息处理器
 * @author sq
 */
public class FreeStyleClientMessageHandler extends MessageHandler {

    public DBManager dbManager;

    public FreeStyleClientMessageHandler(String owner, DBManager dbmanager) {
        super(owner);
        this.dbManager = dbmanager;
    }
    
}
