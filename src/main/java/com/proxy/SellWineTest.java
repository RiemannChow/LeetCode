package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class SellWineTest {

    public static void main(String[] args) {

        MaotaiJiu maotaiJiu = new MaotaiJiu();
        Wuliangye wu = new Wuliangye();
//        Furongwang fu = new Furongwang();

        InvocationHandler riemann = new GuitaiA(maotaiJiu);
        InvocationHandler riemann2 = new GuitaiA(wu);
//        InvocationHandler riemann3 = new GuitaiA(fu);

        SellWine dynamicProxy = (SellWine) Proxy.newProxyInstance(MaotaiJiu.class.getClassLoader(),
                MaotaiJiu.class.getInterfaces(), riemann);

        SellWine dynamicProxy2 = (SellWine) Proxy.newProxyInstance(Wuliangye.class.getClassLoader(),
                Wuliangye.class.getInterfaces(),riemann2);

//        SellCigarette dynamicProxy3 = (SellCigarette) Proxy.newProxyInstance(Furongwang.class.getClassLoader(),
//                Furongwang.class.getInterfaces(), jingxiao3);

        dynamicProxy.mainJiu();
        dynamicProxy2.mainJiu();
//        dynamicProxy3.sell();

        System.out.println("dynamicProxy class name:"+dynamicProxy.getClass().getName());
        System.out.println("dynamicProxy1 class name:"+dynamicProxy2.getClass().getName());
//       System.out.println("dynamicProxy3 class name:"+dynamicProxy3.getClass().getName());


    }

}
