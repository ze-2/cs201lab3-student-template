
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1Test {

    public static void main(String[] args) {

        String result, traversal1, traversal2, traversal3;

        traversal1 = "1-2";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "2-1-3";
        traversal3 = "1-3-2";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "1-2-3";
        traversal2 = "3-2-1";
        traversal3 = "2-3-1";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println("Expected Result : Invalid traversals");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "3-1-2-5-4";
        traversal2 = "2-1-4-5-3";
        traversal3 = "1-2-3-4-5";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();

        traversal1 = "10-20-30-40-50";
        traversal2 = "20-10-30-40-50";
        traversal3 = "10-50-40-30-20";
        result = verify(traversal1, traversal2, traversal3);
        System.out.println("Traversal 1 : " + traversal1);
        System.out.println("Traversal 2 : " + traversal2);
        System.out.println("Traversal 3 : " + traversal3);
        System.out.println(
                "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
        System.out.println("  Actual Result : " + result);
        System.out.println();
    }

    static class Node {

        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    enum Order {
        PRE("Preorder"), IN("Inorder"), POST("Postorder");

        final String label;

        Order(String label) {
            this.label = label;
        }
    }

    static final Order[][] ASSIGNMENTS = {
        {Order.PRE, Order.IN, Order.POST},
        {Order.PRE, Order.POST, Order.IN},
        {Order.IN, Order.PRE, Order.POST},
        {Order.POST, Order.PRE, Order.IN},
        {Order.IN, Order.POST, Order.PRE},
        {Order.POST, Order.IN, Order.PRE}
    };

    public static String verify(String traversal1, String traversal2, String traversal3) {
        String[] t = {traversal1, traversal2, traversal3};

        for (Order[] labels : ASSIGNMENTS) {
            if (isValid(t, labels)) {
                return "Traversal 1 - " + labels[0].label
                        + ", Traversal 2 - " + labels[1].label
                        + ", Traversal 3 - " + labels[2].label;
            }
        }
        return "Invalid traversals";
    }

    static boolean isValid(String[] t, Order[] labels) {
        String pre = t[Arrays.asList(labels).indexOf(Order.PRE)];
        Node root = null;
        for (String s : pre.split("-")) {
            root = insert(root, Integer.parseInt(s));
        }
        for (int i = 0; i < t.length; i++) {
            if (!walk(root, labels[i]).equals(t[i])) {
                return false;
            }
        }
        return true;
    }

    static Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }
        return node;
    }

    static String walk(Node root, Order order) {
        List<String> out = new ArrayList<>();
        walk(root, order, out);
        return String.join("-", out);
    }

    static void walk(Node node, Order order, List<String> out) {
        if (node == null) {
            return;
        }
        if (order == Order.PRE) {
            out.add(String.valueOf(node.val));
        }
        walk(node.left, order, out);
        if (order == Order.IN) {
            out.add(String.valueOf(node.val));
        }
        walk(node.right, order, out);
        if (order == Order.POST) {
            out.add(String.valueOf(node.val));
        }
    }
}
