import java.util.LinkedList;

public class UTree {

    private UNode root;
    private int size;

    public UTree() {

    }

    public int size() {
        return size;
    }

    public int get(int index) {
        return get(index, root);
    }

    public boolean add(int key) {
        if (root == null) {
            root = new UNode(null, key, 0, 0, null, null);
            size++;
            return true;
        }
        boolean res = add(key, root);
        if (res) size++;
        return res;
    }

    public boolean remove(int key) {
        boolean res = remove(key, root);
        if (res) size--;
        return res;
    }

    private int get(int index, UNode root) {
        root.push();
        if (index == root.countLefter)
            return root.key;
        if (index < root.countLefter)
            return get(index, root.left);
        return get(index, root.right);
    }

    private boolean add(int key, UNode root) {
        if (key == root.key) return false;
        root.push();
        if (key < root.key)
            if (root.left != null) {
                boolean res = add(key, root.left);
                if (res) {
                    root.countLefter++;
                    if (root.right != null)
                        root.right.add++;
                }
                return res;
            } else {
                root.left = new UNode(root, key, root.countLefter, 0, null, null);
                root.countLefter++;
                if (root.right != null)
                    root.right.add++;
                return true;
            }
        if (root.right != null)
            return add(key, root.right);
        else {
            root.right = new UNode(root, key, root.countLefter + 1, 0, null, null);
            return true;
        }
    }

    public boolean removeByIndex(int index) {
        if(this.root == null) return false;
        root.push();
        if (index == this.root.countLefter) {
            removeRoot();
            return true;
        }
        boolean res = removeByIndex(index, root);
        if (res) size--;
        return res;
    }

    private boolean removeByIndex(int index, UNode root) {
        if (root == null)
            return false;
        root.push();
        if (index == root.countLefter)
            return removeNode(root);
        if (index < root.countLefter) {
            boolean res = removeByIndex(index, root.left);
            if (res) {
                root.countLefter--;
                if (root.right != null)
                    root.right.add--;
            }
            return res;
        } else
            return removeByIndex(index, root.right);
    }

    private boolean removeNode(UNode root) {
        if (root.left == root.right) {
            if (root.parent.left == root)
                root.parent.left = null;
            else root.parent.right = null;
            return true;
        }
        if (root.left == null) {
            if (root.parent.left == root) {
                root.parent.left = root.right;
                root.right.add--;
            } else {
                root.parent.right = root.right;
                root.right.add--;
            }
            root.right.parent = root.parent;
            return true;
        }
        if (root.right == null) {
            if (root.parent.left == root)
                root.parent.left = root.left;
            else
                root.parent.right = root.left;
            root.left.parent = root.parent;
            return true;
        }
        UNode right = getRight(root.left);
        cut(right);
        root.key = right.key;
        root.countLefter--;
        root.right.add--;
        return true;
    }

    private boolean remove(int key, UNode root) {
        if (root == null)
            return false;
        root.push();
        if (key == this.root.key) {
            removeRoot();
            return true;
        }
        if (key == root.key)
            return removeNode(root);
        if (key < root.key) {
            boolean res = remove(key, root.left);
            if (res) {
                root.countLefter--;
                if (root.right != null)
                    root.right.add--;
            }
            return res;
        } else
            return remove(key, root.right);
    }

    private void removeRoot() {
        if (root.left == root.right) {
            root = null;
            return;
        }
        if (root.left == null) {
            root = root.right;
            root.add--;
            return;
        }
        if (root.right == null) {
            root = root.left;
            return;
        }
        UNode right = getRight(root.left);
        cut(right);
        root.key = right.key;
        root.countLefter--;
        root.right.add--;
    }

    private static void cut(UNode node) {
        if (node.parent.left == node)
            node.parent.left = node.left;
        else
            node.parent.right = node.left;
        if (node.left != null)
            node.left.parent = node.parent;

    }

    private UNode getRight(UNode root) {
        root.push();
        if (root.right == null)
            return root;
        return getRight(root.right);
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(UNode root) {
        if (root == null) return;
        root.push();
        printTree(root.left);
        System.out.println(root);
        printTree(root.right);
    }

    public LinkedList<UNode> getAll(){
        LinkedList<UNode> res = new LinkedList<>();
        getAll(root, res);
        return res;
    }

    private void getAll(UNode root, LinkedList<UNode> res){
        if (root == null) return;
        root.push();
        getAll(root.left, res);
        res.add(root);
        getAll(root.right, res);

    }

}
