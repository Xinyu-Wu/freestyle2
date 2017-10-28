/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.geotools.data.simple.SimpleFeatureSource;

/**
 * 消息解析器
 * @author sq
 */
public class MessageParser {
    private String Owner;
    
    //获得当前时间戳
    protected long getCurTime()
    {
        return System.currentTimeMillis() / 1000;
    }
    
    public MessageParser(String owner) {
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

    public boolean isDebug() {
        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        boolean isDebug = false;
        for (String arg : args) {
            if (arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")) {
                isDebug = true;
                break;
            }
        }
        return isDebug;
    }
    
    /**
     * 用户注册
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserSignUp(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignUp 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 用户登录
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserSignIn(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignIn 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 用户登出
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserSignOut(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignOut 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 用户聊天信息上传
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserChatLoadUp(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserChatLoadUp 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 用户聊天信息广播
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserChatBroadcast(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserChatBroadcast 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 添加用户到工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserAddToProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserAddToProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return  null;
        }
    }

    /**
     * 获取用户工程列表
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public ArrayList<String> UserProjectUserList(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserProjectUserList 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 将用户从工程中删除
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String UserDeleteFromProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserDeleteFromProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return  null;
        }
    }

    /**
     * 服务器刷新
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String SeverRefresh(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverRefresh 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return  null;
        }
    }

    /**
     * 服务器崩溃
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String SeverBreakDown(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverBreakDown 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 服务器重启
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String SeverRestart(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverRestart 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return  null;
        }
    }

    /**
     * 服务器维护
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String SeverMaintain(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverMaintain 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 服务器传输数据
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String SeverTransportData(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverTransportData 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获取工程列表
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public ArrayList<String> GetProjectList(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetProjectList 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获取工程内容
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public ArrayList<String> GetProjectContetnt(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetProjectContetnt 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获取图层内容
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public SimpleFeatureSource GetLayerContent(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerContent 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获取图层读锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String GetLayerReadLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerReadLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 释放图层读锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String ReleaseLayerReadLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseLayerReadLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获取查询结果
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public SimpleFeatureSource GetQueryResult(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetQueryResult 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获取暗中观察锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String GetSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetSecretlyObserveLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 释放暗中观察锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String ReleaseSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseSecretlyObserveLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 释放暗中观察锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public SimpleFeatureSource SendSecretlyObserveData(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SendSecretlyObserveData 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 获得图层写锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String GetLayerWriteLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerWriteLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 释放图层写锁
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String ReleaseLayerWriteLock(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseLayerWriteLock 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 添加一个新图层
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String AddOneNewLayer(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("AddOneNewLayer 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 删除一个图层
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String DeleteOneLayer(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteOneLayer 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 添加要素
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String AddFeatures(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("AddFeatures 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 修改要素
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String ModifyFeatures(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ModifyFeatures 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 删除要素
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String DeleteFeatures(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteFeatures 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 创建新工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String CreateProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("CreateProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
        }
    }

    /**
     * 删除工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    public String DeleteProject(TransmittedMessage transMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteProject 处理函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return null;
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
        //HashMap<String, Class> sendDataType = transMsg.getDataType();
        sendData.put("ReturnMsg", errorMsg);
        //sendDataType.put("ReturnMsg", String.class);
        if (sender != Owner || sendMsgType != "Request" || sendStatus != FOperationStatus.Send) {
            return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.WTF, sendData);
        } else {
            return new TransmittedMessage(Owner, receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, sendData);
        }
    }
}
