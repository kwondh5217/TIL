import java.util.*;

class Solution {
    class Node {
        int index;
        Node prev;
        Node next;

        Node(int index) {
            this.index = index;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
                nodes[i - 1].next = nodes[i];
            }
        }

        Node current = nodes[k];
        Stack<Node> stack = new Stack<>();

        for (String command : cmd) {
            char op = command.charAt(0);

            if (op == 'U') {
                int x = Integer.parseInt(command.substring(2));
                while (x-- > 0) {
                    current = current.prev;
                }
            } else if (op == 'D') {
                int x = Integer.parseInt(command.substring(2));
                while (x-- > 0) {
                    current = current.next;
                }
            } else if (op == 'C') {
                stack.push(current);
                if (current.prev != null) current.prev.next = current.next;
                if (current.next != null) current.next.prev = current.prev;

                current = (current.next != null) ? current.next : current.prev;
            } else if (op == 'Z') {
                Node node = stack.pop();
                if (node.prev != null) node.prev.next = node;
                if (node.next != null) node.next.prev = node;
            }
        }

        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop().index, 'X');
        }

        return sb.toString();
    }
}
