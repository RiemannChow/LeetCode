package com.test.mementoPattern;

/**
 * @author riemann
 * @date 2019/05/26 14:20
 */
public class Client {

    public static void main(String[] args) {
        GameRole riemann = new GameRole();
        riemann.init();
        riemann.show();
        RoleStateManage roleStateManage = new RoleStateManage();
        roleStateManage.setMemento(riemann.saveMemento());//保存
        riemann.fightBoss();
        riemann.show();
        riemann.remove(roleStateManage.getMemento());
        riemann.show();
    }

}
