/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;
import cn.edu.pku.datasource.SimpleFeatureManager;
import com.vividsolutions.jts.io.ParseException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
/**
 * 传输信息类
 *
 * @author sq
 */
import java.util.Iterator;
import net.sf.json.JSONArray;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;

public class TransmittedMessage {

    private String Sender;
    private String Receiver;
    private long TimeStamp;
    private String MessageType;
    private String MessageId;
    private FOperationCode Code;
    private FOperationStatus Status;
    private HashMap<String, Object> Data;

    public String CachePath;
    //private HashMap<String, Class> DataType;

    public TransmittedMessage() {

    }

    /**
     * 构造传输信息
     *
     * @param sender 发送者
     * @param receiver 接收者
     * @param timeStamp Unix时间戳
     * @param messageType 信息类型
     * @param messageId 信息ID
     * @param code 指令代码
     * @param status 指令状态
     * @param data 传输的数据
     */
    public TransmittedMessage(
            String sender,
            String receiver,
            long timeStamp,
            String messageType,
            String messageId,
            FOperationCode code,
            FOperationStatus status,
            HashMap<String, Object> data
    ) {
        this.Sender = sender;
        this.Receiver = receiver;
        this.TimeStamp = timeStamp;
        this.MessageType = messageType;
        this.MessageId = messageId;
        this.Code = code;
        this.Status = status;
        this.Data = data;
    }

    /**
     * 获取发送者
     *
     * @return
     */
    public String getSender() {
        return this.Sender;
    }

    /**
     * 获取接收者
     *
     * @return
     */
    public String getReceiver() {
        return this.Receiver;
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public long getTimeStamp() {
        return this.TimeStamp;
    }

    /**
     * 获取消息ID
     *
     * @return
     */
    public String getMessageId() {
        return this.MessageId;
    }

    /**
     * 获取消息类型
     *
     * @return
     */
    public String getMessageType() {
        return this.MessageType;
    }

    /**
     * 获取指令代码
     *
     * @return
     */
    public FOperationCode getCode() {
        return this.Code;
    }

    /**
     * 获取指令状态
     *
     * @return
     */
    public FOperationStatus getStatus() {
        return this.Status;
    }

    /**
     * 获取消息数据
     *
     * @return
     */
    public HashMap<String, Object> getData() {
        return this.Data;
    }

    /*
    public HashMap<String,Class> getDataType()
    {
        return this.DataType;
    }*/
    /**
     * 将要传输消息转为Json格式
     *
     * @return
     */
    public JSONObject convertMessageToJson() throws IOException {
        JSONObject jObject = new JSONObject();
        jObject.put("Sender", this.Sender);
        jObject.put("Receiver", this.Receiver);
        jObject.put("TimeStamp", this.TimeStamp);
        jObject.put("MessageType", this.MessageType);
        jObject.put("MessageId", this.MessageId);
        jObject.put("Instruction", this.Code.getDescription());
        jObject.put("InstructionCode", this.Code.getValue() + this.Status.getValue());

        JSONObject sDataObject = new JSONObject();
        for (Map.Entry<String, Object> entry : Data.entrySet()) {
            if (entry.getKey() == "Features") {
                sDataObject.put(entry.getKey(), SimpleFeatureManager.convertFeaturesToJSONArray((SimpleFeatureSource) entry.getValue()));
            } else {
                sDataObject.put(entry.getKey(), entry.getValue());
            }
        }
        /*
        JSONObject sDataTypeObject = new JSONObject();
        for (Map.Entry<String, Class> entry : DataType.entrySet()) {
        sDataTypeObject.put(entry.getKey(), entry.getValue().getName());
        }
        jObject.put("DataType", sDataTypeObject);*/
        jObject.put("Data", sDataObject);
        return jObject;
    }

    /**
     * 将要传输消息转为String格式
     *
     * @return
     * @throws IOException
     */
    public String convertMessageToString() throws IOException {
        JSONObject jObject = new JSONObject();
        jObject.put("Sender", this.Sender);
        jObject.put("Receiver", this.Receiver);
        jObject.put("TimeStamp", this.TimeStamp);
        jObject.put("MessageType", this.MessageType);
        jObject.put("MessageId", this.MessageId);
        jObject.put("Instruction", this.Code.getDescription());
        jObject.put("InstructionCode", this.Code.getValue() + this.Status.getValue());

        JSONObject sDataObject = new JSONObject();
        for (Map.Entry<String, Object> entry : Data.entrySet()) {
            if (entry.getKey() == "Features") {
                sDataObject.put(entry.getKey(), SimpleFeatureManager.convertFeaturesToJSONArray((SimpleFeatureSource) entry.getValue()));
            } else {
                sDataObject.put(entry.getKey(), entry.getValue());
            }
        }
        /*
        JSONObject sDataTypeObject = new JSONObject();
        for (Map.Entry<String, Class> entry : DataType.entrySet()) {
        sDataTypeObject.put(entry.getKey(), entry.getValue().getName());
        }
        jObject.put("DataType", sDataTypeObject);*/
        jObject.put("Data", sDataObject);
        return jObject.toString();
    }

    /**
     * 将Json转为传输的消息
     *
     * @param jObject
     * @return
     */
    public static TransmittedMessage convertJsonToMessage(JSONObject jObject, String CachePath) throws ClassNotFoundException, IOException, MalformedURLException, ParseException {
        String sender = jObject.get("Sender").toString();
        String receiver = jObject.get("Receiver").toString();
        long timeStamp = Long.parseLong(jObject.get("TimeStamp").toString());
        String messageType = jObject.get("MessageType").toString();
        String messageId = jObject.get("MessageId").toString();
        String CodeStatus = jObject.get("InstructionCode").toString();
        FOperationCode code = FOperationCode.fromString(CodeStatus.substring(0, 4));
        FOperationStatus status = FOperationStatus.fromString(CodeStatus.substring(4));
        HashMap<String, Object> data = new HashMap<String, Object>();
        //读取数据类型
        /*
        HashMap<String, Class> dataType = new HashMap<String, Class>();
        JSONObject sdataTypeObject = jObject.getJSONObject("DataType");
        for (Iterator it = sdataTypeObject.keys(); it.hasNext();) {  
            String key =(String)it.next();
            dataType.put(key, Class.forName(sdataTypeObject.get(key).toString()));
        }*/
        //读取数据
        JSONObject sdataObject = jObject.getJSONObject("Data");
        for (Iterator it = sdataObject.keys(); it.hasNext();) {
            String key = (String) it.next();
            if (key == "Features") {
                JSONArray features = (JSONArray) sdataObject.get(key);
                JSONObject featureFieldss = (JSONObject) sdataObject.get("FeatureFields");
                data.put(key, SimpleFeatureManager.convertJSONObjectsToSimpleFeatureSource(features, featureFieldss, CachePath));
            } else if (key == "FeatureFields") {
                data.put("FeatureFields", (JSONObject) sdataObject.get("FeatureFields"));
            } else {
                data.put(key, (String.class).cast(sdataObject.get(key)));
            }
        }
        return new TransmittedMessage(sender, receiver, timeStamp, messageType, messageId, code, status, data);
    }

    /**
     * 将String转为传输的消息
     *
     * @param jString
     * @return
     */
    public static TransmittedMessage convertStringToMessage(String jString) throws ClassNotFoundException {
        JSONObject jObject = JSONObject.fromObject(jString);
        String sender = jObject.get("Sender").toString();
        String receiver = jObject.get("Receiver").toString();
        long timeStamp = Long.parseLong(jObject.get("TimeStamp").toString());
        String messageType = jObject.get("MessageType").toString();
        String messageId = jObject.get("MessageId").toString();
        String CodeStatus = jObject.get("InstructionCode").toString();
        FOperationCode code = FOperationCode.fromString(CodeStatus.substring(0, 4));
        FOperationStatus status = FOperationStatus.fromString(CodeStatus.substring(4));
        HashMap<String, Object> data = new HashMap<String, Object>();
        //读取数据类型
        /*
        HashMap<String, Class> dataType = new HashMap<String, Class>();
        JSONObject sdataTypeObject = jObject.getJSONObject("DataType");
        for (Iterator it = sdataTypeObject.keys(); it.hasNext();) {  
            String key =(String)it.next();
            dataType.put(key, Class.forName(sdataTypeObject.get(key).toString()));
        }*/
        //读取数据
        JSONObject sdataObject = jObject.getJSONObject("Data");
        for (Iterator it = sdataObject.keys(); it.hasNext();) {
            String key = (String) it.next();
             data.put(key, sdataObject.get(key));
        }
        return new TransmittedMessage(sender, receiver, timeStamp, messageType, messageId, code, status, data);
    }

    public static void main(String[] args) throws Exception {

        String swtf = String.class.getName();
        Class sclass = Class.forName(swtf);
        String sender = "Tester";
        TransmittedMessageIDPool idPool = new TransmittedMessageIDPool(sender);
        String receiver = "Debuger";
        long timeStamp = System.currentTimeMillis() / 1000;
        String messageType = "Request";
        String messageId = idPool.getOneRandomID(sender);
        FOperationCode code = FOperationCode.HelloWorld;
        FOperationStatus status = FOperationStatus.Send;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("ProjectName", "MyFirstProject");
        data.put("LayerName", "MyFirstLayer");

        File newFile = new File("F:\\ArcGISDoc\\suzhou\\test.shp");
        FileDataStore store = FileDataStoreFinder.getDataStore(newFile);
        SimpleFeatureSource featureSource = store.getFeatureSource();
        JSONObject featureFields = SimpleFeatureManager.convertFeaturesFieldsToJSONObject(featureSource);
        data.put("FeatureFields", featureFields);
        data.put("Features", featureSource);
        TransmittedMessage sMessage = new TransmittedMessage(sender, receiver, timeStamp, messageType, messageId, code, status, data);
        JSONObject jObject = sMessage.convertMessageToJson();
        System.out.print(jObject);
        String cachePath = "F:\\ArcGISDoc\\suzhou\\1.shp";
        TransmittedMessage sMessage2 = TransmittedMessage.convertJsonToMessage(jObject, cachePath);
        System.out.print(sMessage2.convertMessageToJson());
        return;
    }
}
