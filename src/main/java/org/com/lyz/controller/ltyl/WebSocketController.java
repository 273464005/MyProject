package org.com.lyz.controller.ltyl;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.com.lyz.base.model.po.GG_CZRY;
import org.com.lyz.base.model.po.GG_FJRYB;
import org.com.lyz.base.model.po.GG_LTFJ;
import org.com.lyz.service.ltyl.LtgnService;
import org.com.lyz.util.ConvertUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 即时通信实现类
 * @author： 鲁玉震
 * @time： 2018/8/1
 */
@Component
@ServerEndpoint("/websocket/{conversation}")
public class WebSocketController {

    private final static Logger logger = Logger.getLogger(WebSocketController.class);

    //静态变量，记录当前在线连接数，应该为线程安全
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //private static CopyOnWriteArraySet<WebSocketTestController> webSocketTestControllers = new CopyOnWriteArraySet<WebSocketTestController>();

    //一对一的通信
    private static ConcurrentHashMap<String, WebSocketController> webSocketSet = new ConcurrentHashMap<String, WebSocketController>();

    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private javax.websocket.Session webSocketSession;

    private String homeId = "";

    private String userId = "";

    private String userName = "";

    private String homeName = "";

    private LtgnService ltgnService=(LtgnService) ContextLoader.getCurrentWebApplicationContext().getBean("ltgnService");

    /**
     * 连接建立成功调用的方法
     * @param WebSocketsession 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     * @param conversation 会话信息，由房间号和用户id组成，用{,}分开,前面为房间号，后面为用户id
     */
    @OnOpen
    public void onOpen(@PathParam(value = "conversation") String conversation, Session WebSocketsession) throws SQLException{
        this.webSocketSession = WebSocketsession;
        String[] homeUser = conversation.split(",");
        this.homeId = homeUser[0];
        this.userId = homeUser[1];
        if (userId != null && !"".equals(userId)){
            GG_CZRY gg_czry = ltgnService.getGg_czyb(userId);
            this.userName = gg_czry.getMc();
        }
        if (homeId != null && !"".equals(userId)){
            GG_LTFJ gg_ltfj = ltgnService.getGg_lifj(homeId);
            homeName = gg_ltfj.getFjmc();
        }
        //加入set中
        //webSocketTestControllers.add(this);
        //加入map中
//        webSocketSet.put(this.homeId,this);
        webSocketSet.put(this.userId,this);
        addOnlineCount();
        logger.info(userName + "加入房间"+ homeName +"，当前在线人数：" + getOnlineCount());
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        //从set中删除
        //webSocketTestControllers.remove(this);
        if (!this.userId.equals("")){
            webSocketSet.remove(this.userId);
            subOnlineCount();
            String str = userName + "退出房间，当前在线人数：" + getOnlineCount();
            logger.info(str);
        } else {
            logger.info("该用户已下线");
        }

    }

    /**
     * 发送消息
     * @param session 可选的参数
     * @param msg  客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(Session session,String msg){
        logger.info("来自客户端的消息：" + msg);
//        for (WebSocketTestController item : webSocketTestControllers){
//            try {
//                item.sendMessage(msg);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        try {
//            sendMessageToOne(msg);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        JSONObject json=JSONObject.fromObject(msg);
        String string = null;  //需要发送的信息
        String to = null;      //发送对象的用户标识
        if(json.has("msg")){
            string = (String) json.get("msg");
        }
        if(json.has("jsrId")){
            to = (String) json.get("jsrId");
        }
//        try {
//            sendMessageToAll(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        send(string,userId,to,homeId);
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
    private void sendMessage(String msg) throws IOException{
        this.webSocketSession.getBasicRemote().sendText(msg);
    }

 /*   *//**
     * 给指定用户发消息
     * @param msg 发送的消息
     * @throws IOException 异常信息
     *//*
    private void sendMessageToOne(String msg) throws IOException{
        String home = msg.split("[|]")[1];
        String message = msg.split("[|]")[0];
        logger.info("接收人："+home+"\t发送内容"+message);
        if (webSocketSet.get(home) != null){
            webSocketSet.get(home).sendMessage(message);
        }
    }*/


    /**
     * 发送给指定角色
     * @param msg 发送的消息
     * @param from 来自谁的消息
     * @param to 发给谁的消息
     * @param socketId 会话标识（房间号）
     */
    public void send(String msg,String from,String to,String socketId){
        try {
            //to指定用户（这里指房间号）
            GG_FJRYB gg_fjryb = new GG_FJRYB();
            gg_fjryb.setFjid(to);
            List<Map<String, Object>> fjryList = ltgnService.getFjry(gg_fjryb);
            for (Map<String, Object> map: fjryList) {
                WebSocketController con = webSocketSet.get(ConvertUtils.createString(map.get("ryid")));
                if(con!=null){
                    //只将消息发送当前房间
                    if (ConvertUtils.createString(map.get("fjid")) == con.homeId || ConvertUtils.createString(map.get("fjid")).equals(con.homeId)){
                        if(socketId==con.homeId||con.homeId.equals(socketId)){
                            con.webSocketSession.getBasicRemote().sendText(userName+"："+msg);
                        }
                    }
                }
            }
//            WebSocketTestController con = webSocketSet.get(to);
//            WebSocketTestController con = webSocketSet.get(socketId);
//            if(con!=null){
//                if(socketId==con.homeId||con.homeId.equals(socketId)){
//                    con.webSocketSession.getBasicRemote().sendText(userName+"："+msg);
//                }
//
//            }
//            //from具体用户
//            WebSocketTestController confrom = webSocketSet.get(from);
//            if(confrom!=null){
//                if(socketId==confrom.homeId||confrom.homeId.equals(socketId)){
//                    confrom.webSocketSession.getBasicRemote().sendText(userName+"："+msg);
//                }
//
//            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e2){

        }
    }


    /**
     * 给所有人发消息
     * @param msg 发送的消息  形式：  消息|发送的人
     * @throws IOException 异常信息
     */
    private void sendMessageToAll(String msg) throws IOException{
//        String sendMessage = msg.split("[|]")[0];
        //遍历HashMap
        for (String key : webSocketSet.keySet()) {
            try {
                //判断接收用户是否是当前发消息的用户
                if (!homeId.equals(key)) {
                    webSocketSet.get(key).sendMessage(userName+"："+ msg);
                }
//                if (homeId.equals(key)) {
//                    webSocketSet.get(key).sendMessage(userName+"说："+ msg);
//                    logger.info("homeId.equals(key)");
//                }
//                if (!userId.equals(key)) {
//                    webSocketSet.get(key).sendMessage(userName+"说："+ msg);
//                    logger.info("!userId.equals(key)");
//                }
//                if (userId.equals(key)) {
//                    webSocketSet.get(key).sendMessage(userName+"说："+ msg);
//                    logger.info("userId.equals(key)");
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取当前在线人数
     * @return 当前在线人数
     */
    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 当前在线人数+1
     */
    private static synchronized void addOnlineCount() {
        WebSocketController.onlineCount++;
    }

    /**
     * 只有当当前人数>0时，
     * 当前在线人数-1
     */
    private static synchronized void subOnlineCount() {
        if(getOnlineCount() > 0){
            WebSocketController.onlineCount--;
        }
    }
}
