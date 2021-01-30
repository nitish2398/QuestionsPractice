package sample.Problems.Tree;

class AVLNode extends Node {
    int height;

    AVLNode(int data) {
        super(data);
        this.height = 1;
    }
}

class AVLTree {
    Node insertToAVL(Node node, int data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (node.data > data) {
            node.left = insertToAVL(node.left, data);
        } else if (node.data < data) {
            node.right = insertToAVL(node.right, data);
        }

        initHeight(node);

        int balance = findBalance(node);

        if (balance > 1) {
            if (data < node.left.data) {
                // left - left case
                return rightRotate(node);
            } else {
                // left - right case
                node.left = leftRotate(node);
                return rightRotate(node);
            }
        } else if (balance < -1) {
            if (data > node.right.data) {
                // right - right case
                return leftRotate(node);
            } else {
                // right - left case
                node.right = rightRotate(node);
                return leftRotate(node);
            }
        }

        return node;
    }

    Node deleteFromAVL(Node node, int data) {
        if (node == null) {
            return node;
        }

        if (data > node.data) {
            node.right = deleteFromAVL(node.right, data);
        } else if (data < node.data) {
            node.left = deleteFromAVL(node.left, data);
        } else {
            // No child or exactly one child
            if (node.left == null || node.right == null) {
                Node temp = null;

                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }


                if (temp == null) {
                    node = null; // no child case
                } else {
                    node = temp;
                }
            } else {
                // both child are present
                Node temp = getMin(node.right);

                node.data = temp.data;
                node.right = deleteFromAVL(node.right, temp.data);
            }

            if (node == null) {
                return node;
            }

            initHeight(node);

            int balance = findBalance(node);

            if (balance > 1) {
                if (findBalance(node.left) >= 0) {
                    return rightRotate(node);
                } else {
                    node.left = leftRotate(node);
                    return rightRotate(node);
                }
            } else if (balance < -1) {
                if (findBalance(node.right) <= 0) {
                    return leftRotate(node);
                } else {
                    node.right = rightRotate(node);
                    return leftRotate(node);
                }
            }
        }
        return node;
    }

    private Node rightRotate(Node node) {
        Node result = node.left;
        Node temp = result.right;

        node.left = temp;
        result.right = node;

        // sequence specific
        initHeight(node);
        initHeight(result);

        return result;
    }

    private Node leftRotate(Node node) {
        Node result = node.right;
        Node temp = result.left;

        node.right = temp;
        result.left = node;

        // sequence specific
        initHeight(node);
        initHeight(result);

        return result;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return ((AVLNode) node).height;
    }

    private void initHeight(Node node) {
        ((AVLNode) node).height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private int findBalance(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node getMin(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }
}

class Test {
    public static void main(String[] arg) {
        int[] arr = {-10, 2, 18, -13, -15, 15};
        Node node = null;

        AVLTree avlTree = new AVLTree();

        for (int i : arr) {
            node = avlTree.insertToAVL(node, i);
        }

        avlTree.inorder(node);
        System.out.println();

        avlTree.deleteFromAVL(node, 15);

        avlTree.inorder(node);
    }
}
