package org.com.lyz.controller;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 即时通信实现类
 * @author： 鲁玉震
 * @time： 2018/8/1
 */
@ServerEndpoint("/websocket")
public class WebSocketTestController {

    private final static Logger logger = Logger.getLogger(WebSocketTestController.class);

    //静态变量，记录当前在线连接数，应该为线程安全
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketTestController> webSocketTestControllers = new CopyOnWriteArraySet<WebSocketTestController>();

    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private javax.websocket.Session session;

    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        //加入set中
        webSocketTestControllers.add(this);
        addOnlineCount();
        logger.info("有新的连接加入，当前在线人数：" + getOnlineCount());
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        //从set中删除
        webSocketTestControllers.remove(this);
        subOnlineCount();
        logger.info("有断开的连接，当前在线人数：" + getOnlineCount());
    }

    /**
     * 发送消息
     * @param session 可选的参数
     * @param msg  客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(Session session,String msg){
        logger.info("来自客户端的消息：" + msg);
        for (WebSocketTestController item : webSocketTestControllers){
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误
     * @param session 参数
     * @param error 错误信息
     */
    @OnError
    public void onError(Session session, Throwable error){
        logger.info("发生错误！");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param msg 发送的消息
     * @throws IOException 异常
     */
    public void sendMessage(String msg) throws IOException{
        this.session.getBasicRemote().sendText(msg);
    }


    /**
     * 获取当前在线人数
     * @return 当前在线人数
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 当前在线人数+1
     */
    public static synchronized void addOnlineCount() {
        WebSocketTestController.onlineCount++;
    }

    /**
     * 只有当当前人数>0时，
     * 当前在线人数-1
     */
    public static synchronized void subOnlineCount() {
        if(getOnlineCount() > 0){
            WebSocketTestController.onlineCount--;
        }
    }
}
