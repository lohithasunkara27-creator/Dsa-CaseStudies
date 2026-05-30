class Patient {
    int patientId;
    String name;

    Patient(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }
}

class AVLNode {
    Patient patient;
    AVLNode left, right;
    int height;

    AVLNode(Patient patient) {
        this.patient = patient;
        height = 1;
    }
}

class AVLTree {

    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, Patient patient) {

        if (node == null)
            return new AVLNode(patient);

        if (patient.patientId < node.patient.patientId)
            node.left = insert(node.left, patient);
        else if (patient.patientId > node.patient.patientId)
            node.right = insert(node.right, patient);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && patient.patientId < node.left.patient.patientId)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && patient.patientId > node.right.patient.patientId)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && patient.patientId > node.left.patient.patientId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && patient.patientId < node.right.patient.patientId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.patient.patientId + " -> " + root.patient.name);
            inorder(root.right);
        }
    }

    AVLNode search(AVLNode root, int id) {
        if (root == null || root.patient.patientId == id)
            return root;

        if (id < root.patient.patientId)
            return search(root.left, id);

        return search(root.right, id);
    }
}

public class MediFlowHealthcare {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        AVLNode root = null;

        root = tree.insert(root, new Patient(120, "Akash"));
        root = tree.insert(root, new Patient(105, "Priya"));
        root = tree.insert(root, new Patient(150, "Kiran"));
        root = tree.insert(root, new Patient(101, "Rahul"));
        root = tree.insert(root, new Patient(135, "Sneha"));

        System.out.println("=== MediFlow Healthcare Management System ===");

        System.out.println("\nPatient Records (Inorder Traversal):");
        tree.inorder(root);

        int searchId = 120;

        AVLNode result = tree.search(root, searchId);

        if (result != null) {
            System.out.println("\nPatient Found:");
            System.out.println("ID: " + result.patient.patientId);
            System.out.println("Name: " + result.patient.name);
        } else {
            System.out.println("\nPatient Not Found");
        }

        System.out.println("\nAVL Tree Balanced Successfully");
        System.out.println("All operations execute in O(log n) time.");
    }
}