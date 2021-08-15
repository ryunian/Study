
public class CustomBinaryTree {
    public static void main(String[] args) {
        // BOJ 의 트리순회(1991) 기반으로 만들었다.
        int n = 7;
        Tree tree = new Tree();
        char[][] arr = {
                {'A', 'B', 'C'},
                {'B', 'D', '.'},
                {'C', 'E', 'F'},
                {'E', '.', '.'},
                {'F', '.', 'G'},
                {'D', '.', '.'},
                {'G', '.', '.'}
        };
        for (int i = 0; i < n; i++) {
            tree.add(arr[i][0], arr[i][1], arr[i][2]);
        }
        System.out.print("preorder = ");
        tree.preorder(tree.root);
        System.out.println();

        System.out.print("inorder = ");
        tree.inorder(tree.root);
        System.out.println();

        System.out.print("postOrder = ");
        tree.postOrder(tree.root);
        System.out.println();
    }

    private static class Node {
        char data;
        Node left;
        Node right;

        public Node(char data) {
            this.data = data;
        }
    }

    private static class Tree {
        Node root;

        public Tree() {
            root = new Node('A');
        }

        public void add(char data, char left, char right) {
            if (root.data == data) {
                if (left != '.') root.left = new Node(left);
                if (right != '.') root.right = new Node(right);
            } else {
                next(root.left, data, left, right);
                next(root.right, data, left, right);
            }
        }

        private void next(Node node, char data, char left, char right) {
            if (node == null) {
                return;
            } else if (node.data == data) {
                if (left != '.') node.left = new Node(left);
                if (right != '.') node.right = new Node(right);
            } else {
                next(node.left, data, left, right);
                next(node.right, data, left, right);
            }

        }

        public void preorder(Node node) {
            System.out.print(node.data+" ");
            if (node.left != null) preorder(node.left);
            if (node.right != null) preorder(node.right);

        }

        public void inorder(Node node) {
            if (node.left != null) inorder(node.left);
            System.out.print(node.data+" ");
            if (node.right != null) inorder(node.right);

        }

        public void postOrder(Node node) {
            if (node.left != null) postOrder(node.left);
            if (node.right != null) postOrder(node.right);
            System.out.print(node.data+" ");
        }
    }
}
