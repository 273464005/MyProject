package org.com.lyz.util.returnvalue;

/**
 * 作者： 鲁玉震
 * 创建时间： 2018/6/1
 */

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReturnValue<T> implements Serializable {
    public static String SUCCESS_TEXT = "操作成功,3秒后返回上一页面";
    public static String ERROR_TEXT = "系统异常，请与管理员联系。";
    public static String RESUBMIT_TEXT = "重复提交！";
    public static int SUCCESS = 1;
    public static int ERROR = 2;
    public static int CUSTOM = 3;
    public static int RESUBMIT = 4;
    private String text;
    private int state;
    private T data;
    private String _ResubmitToken;

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String get_ResubmitToken() {
        return this._ResubmitToken;
    }

    public void set_ResubmitToken(String _ResubmitToken) {
        this._ResubmitToken = _ResubmitToken;
    }

    private ReturnValue() {
    }

    public static ReturnValue newSuccessInstance() {
        ReturnValue rv = new ReturnValue();
        rv.setText(SUCCESS_TEXT);
        rv.setState(SUCCESS);
        return rv;
    }

    public static ReturnValue newSuccessInstance(String text) {
        ReturnValue rv = new ReturnValue();
        rv.setText(text);
        rv.setState(SUCCESS);
        return rv;
    }

    public static ReturnValue newErrorInstance() {
        ReturnValue rv = new ReturnValue();
        rv.setText(ERROR_TEXT);
        rv.setState(ERROR);
        return rv;
    }

    public static ReturnValue newErrorInstance(String text) {
        ReturnValue rv = new ReturnValue();
        rv.setText(text);
        rv.setState(ERROR);
        return rv;
    }

    public static ReturnValue newErrorInstance(HttpServletRequest request) {
        ReturnValue rv = new ReturnValue();
        rv.setText(ERROR_TEXT);
        rv.setState(ERROR);
        rv.set_ResubmitToken((String)request.getAttribute("_ResubmitToken"));
        return rv;
    }

    public static ReturnValue newResubmitInstance() {
        ReturnValue rv = new ReturnValue();
        rv.setText(RESUBMIT_TEXT);
        rv.setState(RESUBMIT);
        return rv;
    }

    public static ReturnValue newCustomInstance(HttpServletRequest request) {
        ReturnValue rv = new ReturnValue();
        rv.setState(CUSTOM);
        rv.set_ResubmitToken((String)request.getAttribute("_ResubmitToken"));
        return rv;
    }

    public static ReturnValue newCustomInstance(int state, HttpServletRequest request) {
        ReturnValue rv = new ReturnValue();
        rv.setState(state);
        rv.set_ResubmitToken((String)request.getAttribute("_ResubmitToken"));
        return rv;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
