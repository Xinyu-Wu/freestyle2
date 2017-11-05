/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import cn.edu.pku.gui.FLogin;
import cn.edu.pku.gui.Main_win;
import java.util.HashMap;

/**
 *
 * @author sq
 */
public class FreeStyleClientMessageParser extends MessageParser {

    public FreeStyleClientMessageParser(String owner) {
        super(owner);
    }

    public FLogin fLogin;
    public Main_win fMainWin;

    public void setFLogin(FLogin flogin) {
        fLogin = flogin;
        System.out.println("CMP:Flogin is setted");
    }

    public void setFMainWin(Main_win fmainwin) {
        fMainWin = fmainwin;
        System.out.println("CMP:FMainWin is setted");
    }

    @Override
    public String UserSignIn(TransmittedMessage transMsg) {
        if (fLogin.userLoginReceive(transMsg)) {
            return "OK";
        } else {
            return "Error";
        }
    }

    /**
     *
     * @param transMsg
     * @return
     */
    @Override
    public String helloHello(TransmittedMessage transMsg) {
        HashMap<String, Object> mdata = transMsg.getData();
        if (mdata.get("ReturnMsg").equals("Hello")) {
            System.out.println("helloHello:OK");
            return "OK";
        } else {
            return "Error";
        }
    }

    @Override
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
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String UserSignOut(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserSignOut) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String UserChatLoadUp(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserChatLoadUp) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String UserAddToProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserAddToProject) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String UserProjectUserList(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserProjectUserList) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String UserDeleteFromProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.UserDeleteFromProject) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetProjectList(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetProjectList) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetProjectContetnt(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetProjectContetnt) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetLayerContent(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetLayerContent) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetLayerReadLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetLayerReadLock) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String ReleaseLayerReadLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ReleaseLayerReadLock) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetQueryResult(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetQueryResult) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetSecretlyObserveLock) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String ReleaseSecretlyObserveLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ReleaseSecretlyObserveLock) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String SendSecretlyObserveData(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.SendSecretlyObserveData) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String GetLayerWriteLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.GetLayerWriteLock) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String ReleaseLayerWriteLock(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ReleaseLayerWriteLock) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String AddOneNewLayer(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.AddOneNewLayer) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String DeleteOneLayer(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.DeleteOneLayer) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String AddFeatures(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.AddFeatures) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String ModifyFeatures(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.ModifyFeatures) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String DeleteFeatures(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.DeleteFeatures) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String CreateProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.CreateProject) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

    @Override
    public String DeleteProject(TransmittedMessage transMsg) throws Exception {
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
            if (sendCode != FOperationCode.DeleteProject) {
                //根据对应的操作类型进行更改
                return null;
            } else {
                //基本信息正确，进行下一步具体的操作
                try {
                    //执行操作 每个消息处理方式不同
                    return "OK";
                } catch (Exception err) {
                    //输出报错信息
                    return "Error";
                }
            }
        }
    }

}
