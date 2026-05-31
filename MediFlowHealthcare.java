class Node {
    int patientId;
    String name;
    Node left, right;
    int height;

    Node(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.height = 1;
    }
}

public class MediFlowHealthcare {

    Node root;

    int height(Node n) {
        if (n == null)
            return 0;
        return n.height;
    }

    int getBalance(Node n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int id, String name) {

        if (node == null)
            return new Node(id, name);

        if (id < node.patientId)
            node.left = insert(node.left, id, name);
        else if (id > node.patientId)
            node.right = insert(node.right, id, name);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && id < node.left.patientId)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && id > node.right.patientId)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && id > node.left.patientId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && id < node.right.patientId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node search(Node root, int id) {
        if (root == null || root.patientId == id)
            return root;

        if (id < root.patientId)
            return search(root.left, id);

        return search(root.right, id);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.patientId + " -> " + root.name);
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        MediFlowHealthcare tree = new MediFlowHealthcare();

        tree.root = tree.insert(tree.root, 120, "Akash");
        tree.root = tree.insert(tree.root, 105, "Priya");
        tree.root = tree.insert(tree.root, 150, "Kiran");
        tree.root = tree.insert(tree.root, 101, "Rahul");
        tree.root = tree.insert(tree.root, 135, "Sneha");

        System.out.println("=== MediFlow Healthcare Management System ===\n");

        System.out.println("Patient Records (Inorder Traversal):");
        tree.inorder(tree.root);

        Node result = tree.search(tree.root, 120);

        if (result != null) {
            System.out.println("\nPatient Found:");
            System.out.println("ID: " + result.patientId);
            System.out.println("Name: " + result.name);
        }

        System.out.println("\nAVL Tree Balanced Successfully");
        System.out.println("All operations execute in O(log n) time.");
    }
}
