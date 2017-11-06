/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import FMessage.FOperationCode;
import FMessage.FOperationStatus;
import FMessage.MessageHandler;
import FMessage.TransmittedMessage;
import cn.edu.pku.datasource.DBManager;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.geotools.data.FeatureWriter;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

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
                    boolean isverfied = dbManager.signUp(userName, password);
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

    @Override
    public TransmittedMessage UserSignIn(TransmittedMessage transMsg) throws Exception {
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
                    boolean isverfied = dbManager.signIn(userName, password);
                    if (isverfied) {
                        returnData.put("ReturnMsg", "Welcome");
                        List<String> mProject = dbManager.queryProjectbyUser(userName);
                        JSONArray ja = new JSONArray();
                        for (int i = 0; i < mProject.size(); i++) {
                            ja.add(mProject.get(i));
                        }
                        returnData.put("ProjectList", ja);
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
     * 连接测试
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage helloHello(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.HelloWorld) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.HelloWorld");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    returnData.put("ReturnMsg", "Hello");
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
        //最后把这个return null 删掉，仅为了通过编译
    }

    /**
     * 模板
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage UserChatLoadUp(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserChatLoadUp) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserChatLoadUp");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String chatContent = sendData.get("UserChatContent").toString();
                    boolean isRepostSuccessed = false;
                    try {
                        //转发出去
                        //TODO
                        isRepostSuccessed = true;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (isRepostSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", "Sorry");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     *
     * 添加用户到工程
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage UserAddToProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserAddToProject) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserAddToProject");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同

                    String userName = sendData.get("UserName").toString();
                    String projectName = sendData.get("ProjectName").toString();
                    //boolean isverfied=dbManager.verifyLogin(userName, password);
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //
                        isSuccessed = dbManager.addUsertoProject(projectName, userName);
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 获取工程中用户列表
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage UserProjectUserList(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserProjectUserList) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserProjectUserList");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String userName = sendData.get("UserName").toString();
                    String projectName = sendData.get("ProjectName").toString();
                    //boolean isverfied=dbManager.verifyLogin(userName, password);
                    String errorMsg = "";
                    List<String> sResult = null;
                    boolean isSuccessed = false;
                    try {

                        sResult = dbManager.queryUserbyProject(projectName);
                        isSuccessed = true;
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        JSONArray ja = new JSONArray();
                        for (int i = 0; i < sResult.size(); i++) {
                            ja.add(sResult.get(i));
                        }
                        returnData.put("UserList", ja);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 从工程中移除用户
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage UserDeleteFromProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserDeleteFromProject) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.UserDeleteFromProject");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String userName = sendData.get("UserName").toString();
                    String projectName = sendData.get("ProjectName").toString();
                    //boolean isverfied=dbManager.verifyLogin(userName, password);
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //isSuccessed = 
                        //dbManager.removeUserFromProject(projectName);
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 获取用户的工程列表
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage GetProjectList(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetProjectList) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetProjectList");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String userName = sendData.get("UserName").toString();
                    String errorMsg = "";
                    List<String> sResult = null;
                    boolean isSuccessed = false;
                    try {
                        //isSuccessed = 
                        sResult = dbManager.queryProjectbyUser(userName);
                        isSuccessed = true;
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        JSONArray ja = new JSONArray();
                        for (int i = 0; i < sResult.size(); i++) {
                            ja.add(sResult.get(i));
                        }
                        returnData.put("ProjectList", ja);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 获取工程内容
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage GetProjectContetnt(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetProjectContetnt) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetProjectContetnt");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("ProjectName").toString();
                    String errorMsg = "";
                    List<String> sResult = null;
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        sResult = dbManager.openProject(projectName, receiver);
                        //isSuccessed = true;
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        JSONArray ja = new JSONArray();
                        for (int i = 0; i < sResult.size(); i++) {
                            ja.add(sResult.get(i));
                        }
                        returnData.put("ProjectList", ja);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 获取图层内容
     *
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage GetLayerContent(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetLayerContent) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetLayerContent");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    SimpleFeatureSource returnSource = null;
                    SimpleFeatureCollection returnCollection;
                    String sCachePath = sendMsgId + ".shp";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //ListFeatureCollection lfc;
                        
                        returnCollection = dbManager.getCollection(projectName, layerName);
                        SimpleFeatureType featureType = returnCollection.getSchema();
                        Map<String, Serializable> params = new HashMap<>();
                        FileDataStoreFactorySpi factory = new org.geotools.data.shapefile.ShapefileDataStoreFactory();
                        params.put(org.geotools.data.shapefile.ShapefileDataStoreFactory.URLP.key, new File(sCachePath).toURI().toURL());
                        ShapefileDataStore ds = (ShapefileDataStore) factory.createNewDataStore(params);
                        //SimpleFeatureSource featureSource = (SimpleFeatureSource) new DefaultFeatureCollection();
                        ds.createSchema(featureType);
                        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = ds.getFeatureWriter(ds.getTypeNames()[0], Transaction.AUTO_COMMIT);
                        SimpleFeatureIterator it = returnCollection.features();
                        try {
                            while (it.hasNext()) {
                                SimpleFeature feature = it.next();
                                SimpleFeature fNew = writer.next();
                                fNew.setAttributes(feature.getAttributes());
                                writer.write();
                            }
                        } finally {
                            it.close();
                        }
                        writer.close();
                        returnSource = ds.getFeatureSource();
                        ds.dispose();

                        isSuccessed = true;
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        returnData.put("Features", returnSource);
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 释放图层读锁
     * @deprecated 
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage ReleaseLayerReadLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ReleaseLayerReadLock) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.ReleaseLayerReadLock");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //ListFeatureCollection lfc;
                        String status = dbManager.checkLayerLock(projectName, layerName);
                        if (status == null) {
                            errorMsg = "该图层当前没有锁";
                        }
                        else if (status == "READ") {
                            //好像不用更改
                            isSuccessed = true;
                        }
                        else
                        {
                            //有读锁的情况下可以有写锁
                            //errorMsg = "该图层已经有了Write锁";
                            isSuccessed = true;
                        }
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    
    
    /**
     * 获取图层写锁
     * @deprecated 
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage GetLayerReadLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetLayerReadLock) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetLayerReadLock");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //ListFeatureCollection lfc;
                        String status = dbManager.checkLayerLock(projectName, layerName);
                        if (status == null) {
                            
                            isSuccessed = dbManager.setLayerLock(projectName, layerName, "READ");
                        }
                        else if ("READ".equals(status)) {
                            //好像不用更改
                            isSuccessed = dbManager.setLayerLock(projectName, layerName, "READ");
                        }
                        else
                        {
                            //有读锁的情况下可以有写锁
                            //errorMsg = "该图层已经有了Write锁";
                            isSuccessed = true;
                        }
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 获取查询结果
     * @deprecated 
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage GetQueryResult(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetQueryResult) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetQueryResult");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String sql =sendData.get("SQL").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 获取暗中观察锁
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage GetSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetSecretlyObserveLock) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetSecretlyObserveLock");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        String status = dbManager.checkLayerLock(projectName, layerName);
                        if (status ==null || !"WRITE".equals(status)) {
                            errorMsg = "该图层当前没有写锁，不能暗中观察！";
                        }
                        else
                        {
                            //有读锁的情况下可以有写锁
                            //errorMsg = "该图层已经有了Write锁";
                            isSuccessed = true;
                        }
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

     /**
     * 释放暗中观察锁
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage ReleaseSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ReleaseSecretlyObserveLock) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.ReleaseSecretlyObserveLock");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        String status = dbManager.checkLayerLock(projectName, layerName);
                        if (status ==null || !"WRITE".equals(status)) {
                            errorMsg = "该图层当前没有写锁，不能释放暗中观察！";
                        }
                        else
                        {
                            //有读锁的情况下可以有写锁
                            //errorMsg = "该图层已经有了Write锁";
                            isSuccessed = true;
                        }
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    @Override
    public TransmittedMessage GetLayerWriteLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetLayerWriteLock) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.GetLayerWriteLock");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        String status = dbManager.checkLayerLock(projectName, layerName);
                        if (status == null || !"READ".equals(status)) {
                            isSuccessed = true;
                        }
                        else
                        {
                            errorMsg = "该图层当前已经有了写锁，不能获取写锁！";
                        }
                        
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    /**
     * 释放图层写锁
     * @param transMsg
     * @return
     * @throws Exception
     */
    @Override
    public TransmittedMessage ReleaseLayerWriteLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ReleaseLayerWriteLock) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.ReleaseLayerWriteLock");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "Default Message";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        String status = dbManager.checkLayerLock(projectName, layerName);
                        if (status == null || !"WRITE".equals(status)) {
                            errorMsg = "该图层当前没有写锁，不能释放写锁！";
                            isSuccessed = dbManager.setLayerLock(projectName, layerName, "READ");
                        }
                        else
                        {
                            //有读锁的情况下可以有写锁
                            //errorMsg = "该图层已经有了Write锁";
                            isSuccessed = dbManager.setLayerLock(projectName, layerName, "READ");
                        }
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    @Override
    public TransmittedMessage AddOneNewLayer(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.AddOneNewLayer) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.AddOneNewLayer");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        //String status = dbManager.添加图层(projectName, layerName);
                        throw new Exception("AddOneNewLayer 尚未实现");
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    @Override
    public TransmittedMessage DeleteOneLayer(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.DeleteOneLayer) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.DeleteOneLayer");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        isSuccessed = dbManager.deleteLayer(projectName, layerName);
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    @Override
    public TransmittedMessage AddFeatures(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.AddFeatures) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.AddFeatures");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        //String status = dbManager.添加图层(projectName, layerName);
                        throw new Exception("AddFeatures 尚未实现");
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

     @Override
    public TransmittedMessage ModifyFeatures(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ModifyFeatures) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.ModifyFeatures");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        //String status = dbManager.添加图层(projectName, layerName);
                        throw new Exception("ModifyFeatures 尚未实现");
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

    
     @Override
    public TransmittedMessage DeleteFeatures(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.DeleteFeatures) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.DeleteFeatures");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        //String status = dbManager.添加图层(projectName, layerName);
                        throw new Exception("DeleteFeatures 尚未实现");
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

     @Override
    public TransmittedMessage CreateProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.CreateProject) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.CreateProject");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        //String status = dbManager.添加图层(projectName, layerName);
                        isSuccessed = dbManager.newProject(projectName, receiver);
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
                    returnData.put("ReturnMsg", err.getMessage());
                    return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
                }

            }
        }
    }

     @Override
    public TransmittedMessage DeleteProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.DeleteProject) {
                //根据对应的操作类型进行更改
                returnData.put("ReturnMsg", "sendCode != FOperationCode.DeleteProject");
                return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Error, returnData);
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    String projectName = sendData.get("projectName").toString();
                    String errorMsg = "";
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //dbManager.属性查询
                        //String status = dbManager.添加图层(projectName, layerName);
                        isSuccessed = dbManager.deleteProject(projectName);
                        
                    } catch (Exception ex) {
                        errorMsg = ex.getMessage();
                    }
                    if (isSuccessed == true) {
                        returnData.put("ReturnMsg", "OK");
                        //TODO
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Return, returnData);
                    } else {
                        returnData.put("ReturnMsg", errorMsg);
                        return new TransmittedMessage(this.getOwner(), receiver, System.currentTimeMillis() / 1000, "Response", sendMsgId, sendCode, FOperationStatus.Refuse, returnData);
                    }

                } catch (Exception err) {
                    //输出报错信息
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
