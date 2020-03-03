package com.algorithm.list.doublyLinkedList;

/**
 * 双向链表
 */
public class DubboLinkedList {
    // 头结点，头结点不保存数据
    Node head = new Node();

    // 获取链表的第一个节点（不是头结点）
    public Node getFirst() {
        return head.getNext();
    }

    // 获取链表的最后那个节点
    public Node getLast() {
        // temp变量来保存链表的最后那个节点
        Node temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        // 循环结束时，temp就是最后那个节点
        return temp;
    }

    // 根据id查找指定节点
    public Node get(int id) {
        Node temp = head.getNext();
        while (temp != null) {
            if (temp.getId() == id) {
                break;
            }
            // temp后移
            temp = temp.getNext();
        }
        return temp;
    }

    // 正序遍历链表
    public void list() {
        // 判空
        if (head.getNext() == null) {
            System.out.println("DubboLinkedList is empty");
            return;
        }
        Node temp = head.getNext();
        while (temp != null) {
            System.out.println(temp);
            // temp后移
            temp = temp.getNext();
        }
    }

    // 倒序遍历链表
    public void reverseOrderList() {
        // 判空
        if (head.getNext() == null) {
            System.out.println("DubboLinkedList is empty");
            return;
        }
        // 先得到最后那个节点
        Node temp = getLast();
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getPrev();
            if (temp.getPrev() == null) {
                // 如果temp.getPrev()==null，说明当前temp是头结点，不打印头结点
                break;
            }
        }
    }

    // 添加新节点到链表尾部
    public void append(Node node) {
        Node last = getLast();
        // 添加新节点
        last.setNext(node);
        node.setPrev(last);
    }

    // 插入节点到指定节点后
    public void insertAfter(Node node, Node newNode) {
        // 先根据id找到这个节点
        Node beforeNode = get(node.getId());
        // 插入节点
        beforeNode.getNext().setPrev(newNode);
        newNode.setNext(beforeNode.getNext());
        beforeNode.setNext(newNode);
        newNode.setPrev(beforeNode);
    }

    // 删除指定节点,并返回被删除节点
    public Node delete(Node node) {
        if (head.getNext() == null) {
            System.out.println("DubboLinkedList is empty");
            return null;
        }
        // 找到被删除节点
        Node deleteNode = get(node.getId());
        if (deleteNode == null) {
            System.out.println("The specified node was not found");
            return deleteNode;
        }
        // 删除节点
        deleteNode.getPrev().setNext(deleteNode.getNext());
        // 如果被删除的节点不是最后那个节点才执行，因为最后的节点的next指针为null,不判断可能产生空指针异常
        if (deleteNode.getNext() != null) {
            deleteNode.getNext().setPrev(deleteNode.getPrev());
        }
        return deleteNode;
    }

    // 修改节点
    public boolean update(Node node) {
        // 先找到节点
        Node updateNode = get(node.getId());
        if (updateNode == null) {
            return false;
        }
        updateNode.setName(node.getName());
        return true;
    }
}
