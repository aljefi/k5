import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tnode {

    private String name;
    private Tnode firstChild;
    private Tnode nextSibling;

    public Tnode() {
    }

    public Tnode(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tnode getFirstChild() {
        return firstChild;
    }


    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        // TODO!!!
        //"+(*(-(2,1),4),/(6,3))"

        Tnode temp = this;

        b.append(temp.name);
        if (temp.firstChild != null) {
            b.append("(");
            b.append(temp.firstChild);
        }
        if (temp.nextSibling != null) {
            b.append(",");
            b.append(temp.nextSibling);
            b.append(")");
        }

        return b.toString();
    }

    private static void addChildOrSibling(Tnode parent, String name, Tnode node) {
        node.setName(name);
        if (parent.firstChild == null) {
            parent.firstChild = node;
        } else {
            parent.getFirstChild().nextSibling = node;
        }
    }

    public static Tnode buildFromRPN(String pol) {
        Tnode root = null;
        // TODO!!!
        String regex = "^.*[a-zA-Z]+.*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pol);
        if (matcher.matches()) {
            throw new RuntimeException("illegal symbols in: " + pol);
        }

        String operators = "+-*/";
        String[] splitter = pol.split(" ");
        ArrayList<String> polArr = new ArrayList<>();
        Collections.addAll(polArr, splitter);

        int numbers = 0;
        int operands = 0;
        int n = 0;

        ArrayList<Tnode> tnodeArrayList = new ArrayList<>();
        for (String elem : polArr) {
            Tnode tnode = new Tnode(elem);
            tnodeArrayList.add(tnode);
            if (elem.matches(".*\\d.*")){
                numbers++;
            } else {
                operands++;
            }
        }
        if (numbers - 1 > operands) {
            throw new RuntimeException("too many numbers in: " + pol);
        } else if (numbers <= operands){
            throw new RuntimeException("too few numbers in: " + pol);
        }
        if (polArr.size() == 1) {
            return tnodeArrayList.get(0);
        }
        if (polArr.size() == 3) {
            addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n), tnodeArrayList.get(n));
            addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n + 1), tnodeArrayList.get(n + 1));
            return tnodeArrayList.get(n + 2);
        }
        while (n < polArr.size() - 2) {
            if (operators.contains(polArr.get(n + 2)) && !operators.contains(polArr.get(n)) && !operators.contains(polArr.get(n + 1))
                    || operators.contains(polArr.get(n + 2)) && operators.contains(polArr.get(n)) && !operators.contains(polArr.get(n + 1))
                    || operators.contains(polArr.get(n + 2)) && operators.contains(polArr.get(n)) && operators.contains(polArr.get(n + 1))) {

                addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n), tnodeArrayList.get(n));
                addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n + 1), tnodeArrayList.get(n + 1));
                n++;
                continue;
            }
            n++;
            if (n == polArr.size() - 2) {
                addChildOrSibling(tnodeArrayList.get(n + 1), polArr.get(n - 3), tnodeArrayList.get(n - 3));
                addChildOrSibling(tnodeArrayList.get(n + 1), polArr.get(n), tnodeArrayList.get(n));
                root = tnodeArrayList.get(n + 1);
            }
        }

        return root;
    }

    public static void main(String[] param) {
        String rpn = "2 xx";
        System.out.println("RPN: " + rpn);
        Tnode res = buildFromRPN(rpn);
        System.out.println("Tree: " + res);
        // TODO!!! Your tests here
    }
}

