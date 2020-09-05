package org.wyjs.server.bean;

import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        MessageBean messageBean = new MessageBean();
        messageBean.setName("菲菲");
        Schema<MessageBean> schema = RuntimeSchema.getSchema(MessageBean.class);
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("C:\\cc.txt"));
        ProtobufIOUtil.writeDelimitedTo(dataOutputStream,messageBean,schema);

        File file = new File("C:\\\\cc.txt");
        System.out.println(file.length());

        //反序列化
        DataInputStream dis = new DataInputStream(new FileInputStream("C:\\cc.txt"));
        byte[] bytes=new byte[(int) file.length()];
        System.out.println(dis.read(bytes));

//        Schema<MessageBean> sh = RuntimeSchema.getSchema(MessageBean.class);
//        MessageBean messageBean1 = sh.newMessage();
        MessageBean messageBean1 = schema.newMessage();

        ProtobufIOUtil.mergeFrom(bytes, messageBean1, schema);
        System.out.println(messageBean1);

    }
}
