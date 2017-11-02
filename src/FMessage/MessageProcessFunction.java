/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

/**
 * 消息处理器
 * @author sq
 */
@FunctionalInterface
public interface MessageProcessFunction {

    /**
     * 消息后处理
     * @param transMsg
     * @return
     */
    public boolean MessagePostProcess(TransmittedMessage transMsg);
}

