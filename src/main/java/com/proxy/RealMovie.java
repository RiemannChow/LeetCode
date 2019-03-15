package com.proxy;

public class RealMovie implements Movie {

    @Override
    public void play() {
        System.out.println("您正在观看电影 《盗梦空间》");
    }

}
