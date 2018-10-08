package org.com.lyz.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织 layUI 树形结构数据
 * @author： 鲁玉震
 * @time： 2018/6/22
 */
public class TreeNodeUtils {

    public TreeNodeUtils() {
    }
    public TreeNodeUtils(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TreeNodeUtils(String id,String name,boolean spread) {
        this.id = id;
        this.name = name;
        this.spread = spread;
    }

    // 在新窗口中打开被连接的文档
    public final static String TARGET_BLANK = "_blank";
    // 在父框架集中打开被连接的文档
    public final static String TARGET_PARENT = "_parent";
    // 在相同的框架中打开被链接的文档
    public final static String TARGET_SELF = "_self";
    // 在整个窗口中打开被链接的文档
    public final static String TARGET_TOP = "_top";

    // 树节点名称
    private String name;
    // 树节点ID
    private String id;
    // 树节点连接地址
    private String href;
    // 树节点是否展开
    private boolean spread = false;
    //自定义树形
    private String custom;
    // 风格
    private String skin;
    // 节点打开方式
    private String target = getTarget_self();
    // 是否追加逗号
    private boolean addComma = false;
    // 追加子节点
    private List<TreeNodeUtils> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getTarget_blank() {
        return TARGET_BLANK;
    }
    public String getTarget_parent() {
        return TARGET_PARENT;
    }
    public String getTarget_self() {
        return TARGET_SELF;
    }
    public String getTarget_top() {
        return TARGET_TOP;
    }

    /**
     * 追加子节点
     * @param childrenNode 子节点
     */
    public void addChildrenNode(TreeNodeUtils childrenNode) {
        getItems().add(childrenNode);
    }

    public boolean isAddComma() {
        return addComma;
    }

    public void setAddComma(boolean addComma) {
        this.addComma = addComma;
    }

    public List<TreeNodeUtils> getItems() {
        if (items == null) {
            items = new ArrayList();
        }
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String data = "{" +
                "spread:" + spread ;

        if(StringUtils.isNotEmpty(name)){
            data += ", name:'" + name + "'";
        }
        if(StringUtils.isNotEmpty(id)){
            data += ", id:'" + id + "'";
        }
        if(StringUtils.isNotEmpty(href)){
            data += ", href:'" + href + "'";
            data += ", target:'" + target + "'";
        }
        if(StringUtils.isNotEmpty(custom)){
            data += ", custom:'" + custom + "'";
        }
        if(StringUtils.isNotEmpty(skin)){
            data += ", skin:'" + skin + "'";
        }
        if(items != null && items.size() > 0){
            data += ", children:[";
            for (int i = 0; i < items.size(); i++) {
                data += items.get(i).toString();
                if (i == items.size() - 1) {
                    data += "";
                } else {
                    data += ", ";
                }
            }
            data += " ]";
        }
        data += '}';
        if(addComma){
            data += ",";
        }
        return data;
    }

    /**
     * 组装带复选框的树形
     * @return
     */
    public  String xTree(){
        String data = "{" +
                "title:'" + name + "'" +
                ", value:'" + id + "'" +
                ", data:[";
        if(items != null && items.size() > 0){
            for (int i = 0; i < items.size(); i++) {
                data += items.get(i).xTree();
                if (i == items.size() - 1) {
                    data += "";
                } else {
                    data += ", ";
                }
            }
        }
        data += " ]";
        data += "}";
        return data;
    }
}
