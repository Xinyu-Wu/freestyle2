/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

/**
 * 消息处理器
 * @deprecated 
 * @author sq
 */
public interface MessageProcessor {

    /**
     * 消息处理
     * @param transMsg
     * @return
     */
    public TransmittedMessage MessageProcess(TransmittedMessage transMsg);
}

class MessageGG implements  MessageProcessor{

    @Override
    public TransmittedMessage MessageProcess(TransmittedMessage transMsg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
