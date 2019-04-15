package com.test;

import java.util.ArrayList;
import java.util.List;

public class ComponentDemo {
    public abstract class Component {
        String name;

        public abstract void add(Component c);

        public abstract void remove(Component c);

        public abstract void eachChild();
    }

    // 组合部件类
    public class Leaf extends Component {

        // 叶子节点不具备添加的能力，所以不实现
        @Override
        public void add(Component c) {
            // TODO Auto-generated method stub
            System.out.println("");
        }

        // 叶子节点不具备添加的能力必然也不能删除
        @Override
        public void remove(Component c) {
            // TODO Auto-generated method stub
            System.out.println("");
        }

        // 叶子节点没有子节点所以显示自己的执行结果
        @Override
        public void eachChild() {
            // TODO Auto-generated method stub
            System.out.println(name + "执行了");
        }

    }

    // 组合类
    public class Composite extends Component {

        // 用来保存节点的子节点
        List<Component> list = new ArrayList<Component>();

        // 添加节点 添加部件
        @Override
        public void add(Component c) {
            // TODO Auto-generated method stub
            list.add(c);
        }

        // 删除节点 删除部件
        @Override
        public void remove(Component c) {
            // TODO Auto-generated method stub
            list.remove(c);
        }

        // 遍历子节点
        @Override
        public void eachChild() {
            // TODO Auto-generated method stub
            System.out.println(name + "执行了");
            for (Component c : list) {
                c.eachChild();
            }
        }
    }

    public static void main(String[] args) {
        ComponentDemo demo = new ComponentDemo();
        // 构造根节点
        Composite rootComposite = demo.new Composite();
        rootComposite.name = "根节点";

        // 左节点
        Composite compositeLeft = demo.new Composite();
        compositeLeft.name = "左节点";

        // 构建右节点，添加两个叶子几点，也就是子部件
        Composite compositeRight = demo.new Composite();
        compositeRight.name = "右节点";
        Leaf leaf1 = demo.new Leaf();
        leaf1.name = "右-子节点1";
        Leaf leaf2 = demo.new Leaf();
        leaf2.name = "右-子节点2";
        compositeRight.add(leaf1);
        compositeRight.add(leaf2);

        // 左右节点加入 根节点
        rootComposite.add(compositeRight);
        rootComposite.add(compositeLeft);
        // 遍历组合部件
        rootComposite.eachChild();
    }
}

