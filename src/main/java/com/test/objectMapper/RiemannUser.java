package com.test.objectMapper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author riemann
 * @date 2019/05/27 22:48
 */
public class RiemannUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String message;

    private String sendDate;

    private String nodeName;

    private List<Integer> intList;

    public RiemannUser() {
        super();
    }

    public RiemannUser(int id, String message, String sendDate) {
        super();
        this.id = id;
        this.message = message;
        this.sendDate = sendDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<Integer> getIntList() {
        return intList;
    }

    public void setIntList(List<Integer> intList) {
        this.intList = intList;
    }

    @Override
    public String toString() {
        return "RiemannUser{" + "id=" + id + ", message='" + message + '\'' + ", sendDate=" + sendDate + ", nodeName='" + nodeName + '\'' + ", intList=" + intList + '}';
    }

}
