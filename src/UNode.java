public class UNode {

    UNode parent;
    int key;
    int countLefter;
    int add;
    UNode left;
    UNode right;

    public UNode() {
    }

    public UNode(UNode parent, int key, int countLefter, int add, UNode left, UNode right) {
        this.parent = parent;
        this.key = key;
        this.countLefter = countLefter;
        this.add = add;
        this.left = left;
        this.right = right;
    }

    public void push() {
        countLefter += add;
        if (left != null)
            left.add += add;
        if (right != null)
            right.add += add;
        add = 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", countLefter=" + countLefter +
                '}';
    }
}
