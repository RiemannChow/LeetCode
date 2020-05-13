package com.algorithm.tree.avlTree;

public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root; // 根结点

    class AVLNode<T extends Comparable<T>> {
        T key; // 关键字(键值)
        int height; // 高度
        AVLNode<T> left; // 左孩子
        AVLNode<T> right; // 右孩子

        public AVLNode(T key, AVLNode<T> left, AVLNode<T> right) {
            this.key = key;
            this.height = 0;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 空构造函数
     */
    public AVLTree() {
        root = null;
    }

    /**
     * 获取树的高度
     * @param tree
     * @return
     */
    private int height(AVLNode<T> tree) {
        if (tree != null) return tree.height;
        return 0;
    }

    public int height() {
        return height(root);
    }

    /**
     * 比较两个值的大小
     * @param a
     * @param b
     * @return
     */
    public int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * 前序遍历"AVL树"
     * @param tree
     */
    private void preOrder(AVLNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历"AVL树"
     * @param tree
     */
    private void inOrder(AVLNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历"AVL树"
     * @param tree
     */
    private void postOrder(AVLNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * (递归实现)查找"AVL树node"中键值为key的节点
     * @param node
     * @param key
     * @return
     */
    private AVLNode<T> search(AVLNode<T> node, T key) {
        if (node == null) return node;
        int temp = key.compareTo(node.key);
        if (temp < 0) {
            return search(node.left, key);
        } else if (temp > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }

    public AVLNode<T> search(T key) {
        return search(root, key);
    }

    /**
     * 查找最小结点：返回tree为根结点的AVL树的最小结点。
     * @param tree
     * @return
     */
    private AVLNode<T> minNode(AVLNode<T> tree) {
        if (tree == null) return null;
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    public T minNode() {
        AVLNode<T> temp = minNode(root);
        if (temp != null) return temp.key;
        return null;
    }

    /**
     * 查找最大结点：返回tree为根结点的AVL树的最大结点。
     * @param tree
     * @return
     */
    private AVLNode<T> maxNode(AVLNode<T> tree) {
        if (tree == null) return null;
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    public T maxNode() {
        AVLNode<T> temp = maxNode(root);
        if (temp != null) return temp.key;
        return null;
    }

    /**
     * LL：左左对应的情况(左单旋转)。
     * @param p2
     * @return 旋转后的根节点
     */
    private AVLNode<T> leftLeftRotation(AVLNode<T> p2) {
        AVLNode<T> p1;
        p1 = p2.left;
        p2.left = p1.right;
        p1.right = p2;
        p2.height = max(height(p2.left), height(p2.right)) + 1;
        p1.height = max(height(p1.left), p2.height) + 1;
        return p1;
    }

    /**
     * RR：右右对应的情况(右单旋转)。
     * @param p1
     * @return 旋转后的根节点
     */
    private AVLNode<T> rightRightRotation(AVLNode<T> p1) {
        AVLNode<T> p2;
        p2 = p1.right;
        p1.right = p2.left;
        p2.left = p1;
        p1.height = max(height(p1.left), height(p1.right)) + 1;
        p2.height = max(height(p2.right), p1.height) + 1;
        return p2;
    }

    /**
     * LR：左右对应的情况(左双旋转)。
     * @param p3
     * @return 旋转后的根节点
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> p3) {
        p3.left = rightRightRotation(p3.left);
        return leftLeftRotation(p3);
    }

    /**
     * RL：右左对应的情况(右双旋转)。
     * @param p4
     * @return 旋转后的根节点
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> p4) {
        p4.right = leftLeftRotation(p4.right);
        return rightRightRotation(p4);
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     * @param tree AVL树的根结点
     * @param key 插入的结点的键值
     * @return  根节点
     */
    private AVLNode<T> insert(AVLNode<T> tree, T key) {
        if (tree == null) {
            // 新建节点
            tree = new AVLNode<>(key, null, null);
            if (tree == null) {
                System.err.println("Create AVLNode failed!");
                return null;
            }
        } else {
            int temp = key.compareTo(tree.key);
            if (temp < 0) { // 应该将key插入到"tree的左子树"的情况
                tree.left = insert(tree.left, key);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(tree.left) - height(tree.right) ==2) {
                    if (key.compareTo(tree.left.key) < 0) {
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftRightRotation(tree);
                    }
                }
            } else if (temp > 0) { // 应该将key插入到"tree的右子树"的情况
                tree.right = insert(tree.right, key);
                // 插入节点后，若AVL树失去平衡，则进行相应的调节。
                if (height(tree.right) - height(tree.left) ==2) {
                    if (key.compareTo(tree.right.key) > 0) {
                        tree = rightRightRotation(tree);
                    } else {
                        tree = rightLeftRotation(tree);
                    }
                }
            } else { // temp == 0
                System.out.println("Add failed, it is not allowed to add the same node!");
            }
        }
        tree.height = max(height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    public void insert(T key) {
        root = insert(root, key);
    }

    /**
     * 删除结点(p)，返回根节点
     * @param tree AVL树的根结点
     * @param p 待删除的结点
     * @return 根节点
     */
    private AVLNode<T> remove(AVLNode<T> tree, AVLNode<T> p) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (tree == null || p == null) return null;
        int temp = p.key.compareTo(tree.key);
        if (temp < 0) { // 待删除的节点在"tree的左子树"中
            tree.left = remove(tree.left, p);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.right) - height(tree.left) == 2) {
                AVLNode<T> r = tree.right;
                tree = height(r.left) > height(r.right) ? rightLeftRotation(tree) : rightRightRotation(tree);
            }
        } else if (temp > 0) { // 待删除的节点在"tree的右子树"中
            tree.right = remove(tree.right, p);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(tree.left) - height(tree.right) == 2) {
                AVLNode<T> l = tree.left;
                tree = height(l.right) > height(l.left) ? leftRightRotation(tree) : leftLeftRotation(tree);
            }
        } else { // tree是对应要删除的节点。
            // tree的左右孩子都非空
            if (tree.left != null && tree.right != null) {
                if (height(tree.left) > height(tree.right)) {
                    /**
                     * 如果tree的左子树比右子树高；
                     * 则(01)找出tree的左子树中的最大节点
                     *   (02)将该最大节点的值赋值给tree
                     *   (03)删除该最大节点
                     * 这类似于用"tree的左子树中最大节点"做"tree"的替身；
                     * 采用这种方式的好处是：删除"tree的左子树中最大节点"之后，AVL树仍然是平衡的。
                     */
                    AVLNode<T> max = maxNode(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                } else {
                    /**
                     * 如果tree的右子树比左子树高；
                     * 则(01)找出tree的右子树中的最小节点
                     *   (02)将该最小节点的值赋值给tree
                     *   (03)删除该最下节点
                     * 这类似于用"tree的右子树中最小节点"做"tree"的替身；
                     * 采用这种方式的好处是：删除"tree的右子树中最小节点"之后，AVL树仍然是平衡的。
                     */
                    AVLNode<T> min = minNode(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min);
                }
            } else {
                AVLNode<T> tempNode = tree;
                tree = (tree.left != null) ? tree.left : tree.right;
                tempNode = null;
            }
        }
        return tree;
    }

    public void remove(T key) {
        AVLNode<T> p;
        if ((p = search(root, key)) != null) {
            root = remove(root, p);
        }
    }

    /**
     * 销毁AVL树
     * @param tree AVL树的根结点
     */
    private void destroy(AVLNode<T> tree) {
        if (tree == null) return;
        if (tree.left != null) destroy(tree.left);
        if (tree.right != null) destroy(tree.right);
        tree = null;
    }

    public void destroy() {
        destroy(root);
    }

    /**
     * 打印"二叉查找树"
     * @param tree AVL树的根结点
     * @param key 节点的键值
     * @param direction 0，表示该节点是根节点;
     *                  -1，表示该节点是它的父结点的左孩子;
     *                  1，表示该节点是它的父结点的右孩子。
     */
    private void print(AVLNode<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0) { // tree是根节点
                System.out.printf("%2d is root\n", tree.key, key);
            } else { // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key,  1);
        }
    }

    public void print() {
        if (root != null) {
            print(root, root.key, 0);
        }
    }

}
