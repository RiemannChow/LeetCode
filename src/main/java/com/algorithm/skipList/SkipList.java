package com.algorithm.skipList;

import java.util.Random;

/**
 * 跳表代码实现
 * 跳表中存储的是正整数，并且存储的是不重复的。
 */
public class SkipList {

    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;

    // 带头链表
    private Node head = new Node(MAX_LEVEL);
    private Random random = new Random();

    public Node find(int value) {
        Node cur = head;
        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; i--) {
            while (cur.forwards[i] != null && cur.forwards[i].data < value) {
                // 找到前一节点
                cur = cur.forwards[i];
            }
        }

        if (cur.forwards[0] != null && cur.forwards[0].data == value) {
            return cur.forwards[0];
        } else {
            return null;
        }
    }

    /**
     * 优化插入版本
     * @param value
     */
    public void insert(int value) {
        int level = head.forwards[0] == null ? 1 : randomLevel();
        // 每次只增加一层，如果条件满足
        if (level > levelCount) {
            level = ++levelCount;
        }
        Node newNode = new Node(level);
        newNode.data = value;
        Node cur = head;
        // 从最大层开始查找，找到前一节点，通过--i，移动到下层再开始查找
        for (int i = levelCount - 1; i >= 0; --i) {
            while (cur.forwards[i] != null && cur.forwards[i].data < value) {
                // 找到前一节点
                cur = cur.forwards[i];
            }
            // levelCount 会 > level，所以加上判断
            if (level > i) {
                if (cur.forwards[i] == null) {
                    cur.forwards[i] = newNode;
                } else {
                    Node next = cur.forwards[i];
                    cur.forwards[i] = newNode;
                    newNode.forwards[i] = next;
                }
            }
        }
    }

    /**
     * 未优化插入版本
     * @param value
     * @param level 0 表示随机层数，不为0，表示指定层数，指定层数可以让每次打印结果不变动。
     */
    public void insert(int value, int level) {
        // 随机一个层数
        if (level == 0) {
            level = randomLevel();
        }
        // 创建新节点
        Node newNode = new Node(level);
        newNode.data = value;
        // 表示从最大层到低层，都要有节点数据
        newNode.maxLevel = level;
        // 记录要更新的层数，表示新节点要更新到哪几层
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        /**
         * 1，说明：层是从下到上的，这里最下层编号是0，最上层编号是15
         * 2，这里没有从已有数据最大层（编号最大）开始找，（而是随机层的最大层）导致有些问题。
         *    如果数据量为1亿，随机level=1 ，那么插入时间复杂度为O（n）
         */
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            // 这里update[i]表示当前层节点的前一节点，因为要找到前一节点，才好插入数据
            update[i] = p;
        }

        // 将每一层节点和后面节点关联
        for (int i = 0; i < level; ++i) {
            // 记录当前层节点后面节点指针
            newNode.forwards[i] = update[i].forwards[i];
            // 前一个节点的指针，指向当前节点
            update[i].forwards[i] = newNode;
        }

        // 更新层高
        if (levelCount < level) levelCount = level;
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node cur = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (cur.forwards[i] != null && cur.forwards[i].data < value) {
                cur = cur.forwards[i];
            }
            update[i] = cur;
        }

        if (cur.forwards[0] != null && cur.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }

    /**
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    /**
     * 打印每个节点数据和最大层数
     */
    public void printAll() {
        Node cur = head;
        while (cur.forwards[0] != null) {
            System.out.print(cur.forwards[0] + " ");
            cur = cur.forwards[0];
        }
        System.out.println();
    }

    /**
     * 打印所有数据
     */
    public void printAll_beautiful() {
        Node p = head;
        Node[] c = p.forwards;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].data : null) + ":" + i + "-------");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
    }

    /**
     * 跳表的节点，每个节点记录了当前节点数据和所在层数数据
     */
    public class Node {
        private int data = -1;
        /**
         * 表示当前节点位置的下一个节点所有层的数据，从上层切换到下层，就是数组下标-1，
         * forwards[3]表示当前节点在第三层的下一个节点。
         */
        private Node forwards[];

        /**
         * 这个值其实可以不用，看优化insert()
         */
        private int maxLevel = 0;

        public Node(int level) {
            forwards = new Node[level];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        // 优化前insert()
        SkipList list = new SkipList();
        list.insert(1, 3);
        list.insert(2, 3);
        list.insert(3, 2);
        list.insert(4, 4);
        list.insert(5, 10);
        list.insert(6, 4);
        list.insert(8, 5);
        list.insert(7, 4);
        list.printAll_beautiful();
        list.printAll();

        // 优化后insert()

        SkipList list2 = new SkipList();
        list2.insert(1);
        list2.insert(2);
        list2.insert(6);
        list2.insert(7);
        list2.insert(8);
        list2.insert(3);
        list2.insert(4);
        list2.insert(5);
        System.out.println();
        list2.printAll_beautiful();
    }
}
