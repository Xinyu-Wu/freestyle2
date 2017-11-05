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
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONArray;

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
                        
                        sResult =dbManager.queryUserbyProject(projectName);
                        isSuccessed= true;
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
     * 获取用户的工程列表
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
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
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
                    String layerName = sendData.get("layerName").toString();
                    String errorMsg = "";
                    List<String> sResult = null;
                    boolean isSuccessed = false;
                    try {
                        //TODO 等接口完善
                        //sResult = dbManager.getLayerNamesByProjectName(projectName);
                        //isSuccessed = true;
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
