/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import org.geotools.data.simple.SimpleFeatureSource;

/**
 * 消息产生器
 *
 * @author sq
 */
public class MessageCreator {

    private String Owner;

    //获得当前时间戳
    long getCurTime() {
        return System.currentTimeMillis() / 1000;
    }

    public MessageCreator(String owner) {
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
     *
     * @param sReceiver
     * @param msgID
     * @param user
     * @param pwd
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserSignUp(String sReceiver, String msgID, String user, String pwd) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignIn 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserSignIn 生成函数为空");
        }
    }

    /**
     * 用户登录
     *
     *
     * @param sReceiver
     * @param msgID
     * @param user
     * @param pwd
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserSignIn(String sReceiver, String msgID, String user, String pwd) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignIn 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserSignIn 生成函数为空");
        }
    }

    /**
     * 用户登出
     *
     *
     * @param sReceiver
     * @param msgID
     * @param user
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserSignOut(String sReceiver, String msgID, String user) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserSignOut 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserSignOut 生成函数为空");
        }
    }

    /**
     * 用户聊天信息上传
     *
     *
     * @param sReceiver
     * @param msgID
     * @param chatContent
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserChatLoadUp(String sReceiver, String msgID,String chatContent) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserChatLoadUp 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserChatLoadUp 生成函数为空");
        }
    }

    /**
     * 用户聊天信息广播
     *
     *
     * @param sReceiver
     * @param msgID
     * @param chatContent
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserChatBroadcast(String sReceiver, String msgID,String chatContent) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserChatBroadcast 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserChatBroadcast 生成函数为空");
        }
    }

    /**
     * 添加用户到工程
     *
     *
     * @param sReceiver
     * @param msgID
     * @param userTobeAdded
     * @param ProjectTobeModified
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserAddToProject(String sReceiver, String msgID,String userTobeAdded,String ProjectTobeModified) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserAddToProject 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserAddToProject 生成函数为空");
        }
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
    public TransmittedMessage UserProjectUserList(String sReceiver, String msgID,String userToBeQueried) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserProjectUserList 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserProjectUserList 生成函数为空");
        }
    }

    /**
     * 将用户从工程中删除
     *
     *
     * @param sReceiver
     * @param msgID
     * @param userTobeDeleted
     * @param ProjectTobeModified
     * @return
     * @throws Exception
     */
    public TransmittedMessage UserDeleteFromProject(String sReceiver, String msgID,String userTobeDeleted,String ProjectTobeModified) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("UserDeleteFromProject 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "UserDeleteFromProject 生成函数为空");
        }
    }

    /**
     * 服务器刷新
     *
     *
     * @param sReceiver
     * @param msgID
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverRefresh(String sReceiver, String msgID,String serverMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverRefresh 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "SeverRefresh 生成函数为空");
        }
    }

    /**
     * 服务器崩溃
     *
     *
     * @param sReceiver
     * @param msgID
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverBreakDown(String sReceiver, String msgID,String serverMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverBreakDown 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "SeverBreakDown 生成函数为空");
        }
    }

    /**
     * 服务器重启
     *
     *
     * @param sReceiver
     * @param msgID
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverRestart(String sReceiver, String msgID) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverRestart 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "SeverRestart 生成函数为空");
        }
    }

    /**
     * 服务器维护
     *
     *
     * @param sReceiver
     * @param msgID
     * @param serverMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverMaintain(String sReceiver, String msgID,String serverMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverMaintain 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "SeverMaintain 生成函数为空");
        }
    }

    /**
     * 服务器传输数据
     *
     *
     * @param sReceiver
     * @param msgID
     * @param data
     * @param serverMsg
     * @return
     * @throws Exception
     */
    public TransmittedMessage SeverTransportData(String sReceiver, String msgID,String serverMsg) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SeverTransportData 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "SeverTransportData 生成函数为空");
        }
    }

    /**
     * 获取工程列表
     *
     *
     * @param sReceiver
     * @param msgID
     * @param userTobeQueried
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetProjectList(String sReceiver, String msgID,String userTobeQueried) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetProjectList 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetProjectList 生成函数为空");
        }
    }

    /**
     * 获取工程内容
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetProjectContetnt(String sReceiver, String msgID,String projectName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetProjectContetnt 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetProjectContetnt 生成函数为空");
        }
    }

    /**
     * 获取图层内容
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetLayerContent(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerContent 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetLayerContent 生成函数为空");
        }
    }

    /**
     * 获取图层读锁
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetLayerReadLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerReadLock 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetLayerReadLock 生成函数为空");
        }
    }

    /**
     * 释放图层读锁
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @return
     * @throws Exception
     */
    public TransmittedMessage ReleaseLayerReadLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseLayerReadLock 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "ReleaseLayerReadLock 生成函数为空");
        }
    }

    /**
     * 获取查询结果
     *
     *
     * @param sReceiver
     * @param msgID
     * @param sql
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetQueryResult(String sReceiver, String msgID,String sql) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetQueryResult 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetQueryResult 生成函数为空");
        }
    }

    /**
     * 获取暗中观察锁
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetSecretlyObserveLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetSecretlyObserveLock 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetSecretlyObserveLock 生成函数为空");
        }
    }

    /**
     * 释放暗中观察锁
     *
     *
     * @param sReceiver
     * @param msgID
     * @return
     * @throws Exception
     */
    public TransmittedMessage ReleaseSecretlyObserveLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseSecretlyObserveLock 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "ReleaseSecretlyObserveLock 生成函数为空");
        }
    }

    /**
     * 发送暗中观察数据
     *
     * @param sReceiver
     * @param msgID      *
     * @return
     * @throws Exception
     */
    public TransmittedMessage SendSecretlyObserveData(String sReceiver, String msgID) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("SendSecretlyObserveData 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "SendSecretlyObserveData 生成函数为空");
        }
    }

    /**
     * 获得图层写锁
     *
     *
     * @param sReceiver
     * @param msgID
     * @return
     * @throws Exception
     */
    public TransmittedMessage GetLayerWriteLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("GetLayerWriteLock 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "GetLayerWriteLock 生成函数为空");
        }
    }

    /**
     * 释放图层写锁
     *
     *
     * @param sReceiver
     * @param msgID
     * @return
     * @throws Exception
     */
    public TransmittedMessage ReleaseLayerWriteLock(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ReleaseLayerWriteLock 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "ReleaseLayerWriteLock 生成函数为空");
        }
    }

    /**
     * 添加一个新图层
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @return
     * @throws Exception
     */
    public TransmittedMessage AddOneNewLayer(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("AddOneNewLayer 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "AddOneNewLayer 生成函数为空");
        }
    }

    /**
     * 删除一个图层
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @return
     * @throws Exception
     */
    public TransmittedMessage DeleteOneLayer(String sReceiver, String msgID,String projectName,String layerName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteOneLayer 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "DeleteOneLayer 生成函数为空");
        }
    }

    /**
     * 添加要素
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @param simplefeature
     * @return
     * @throws Exception
     */
    public TransmittedMessage AddFeatures(String sReceiver, String msgID,String projectName,String layerName,SimpleFeatureSource simplefeature) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("AddFeatures 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "AddFeatures 生成函数为空");
        }
    }

    /**
     * 修改要素
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @param simplefeature
     * @return
     * @throws Exception
     */
    public TransmittedMessage ModifyFeatures(String sReceiver, String msgID,String projectName,String layerName,SimpleFeatureSource simplefeature) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("ModifyFeatures 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "ModifyFeatures 生成函数为空");
        }
    }

    /**
     * 删除要素
     *
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @param layerName
     * @param simplefeature
     * @return
     * @throws Exception
     */
    public TransmittedMessage DeleteFeatures(String sReceiver, String msgID,String projectName,String layerName,SimpleFeatureSource simplefeature) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteFeatures 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "DeleteFeatures 生成函数为空");
        }
    }

    /**
     * 创建新工程
     *
     * @param sReceiver
     * @param msgID
     * @param projectName
     * @return
     * @throws Exception
     */
    public TransmittedMessage CreateProject(String sReceiver, String msgID,String projectName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("CreateProject 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "CreateProject 生成函数为空");
        }
    }
    
     /**
     * 连接测试
     *
     * @param sReceiver
     * @param msgID
     * @param username
     * @param ipport
     * @param operator
     * @return
     * @throws Exception
     */
    public TransmittedMessage helloHello(String sReceiver, String msgID,String username,String ipport, String operator) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("helloHello 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "helloHello 生成函数为空");
        }
    }

    /**
     * 删除工程
     *
     * @param sReceiver 
     * @param msgID 
     * @param projectName 
     * @return
     * @throws Exception
     */
    public TransmittedMessage DeleteProject(String sReceiver, String msgID,String projectName) throws Exception {
        if (this.isDebug() == true) {
            throw new UnsupportedOperationException("DeleteProject 生成函数为空. Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            return this.getDefaultMsg(sReceiver, msgID, "DeleteProject 生成函数为空");
        }
    }

    /**
     * 返回一条默认消息
     *
     *
     * @param sReceiver
     * @param msgID
     * @param helloMsg
     * @return
     */
    public TransmittedMessage getDefaultMsg(String sReceiver, String msgID, String attachedMsg) {
        String receiver = sReceiver;
        String sender = this.getOwner();
        long sendTime = this.getCurTime();
        String sendMsgId = msgID;
        String sendMsgType = "Request";
        FOperationCode sendCode = FOperationCode.HelloWorld;
        FOperationStatus sendStatus = FOperationStatus.Send;
        HashMap<String, Object> sendData = new HashMap<>();
        //HashMap<String, Class> sendDataType = transMsg.getDataType();
        sendData.put("AttachedMessage", attachedMsg);
        //sendDataType.put("ReturnMsg", String.class);
        return new TransmittedMessage(sender, receiver, sendTime, sendMsgType, sendMsgId, sendCode, sendStatus, sendData);
    }
}
