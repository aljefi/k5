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
        String regex = "(^.*[a-zA-Z]$)";
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
            if (elem.matches(".*\\d.*")) numbers++;
            else if (elem.equals("%") || elem.equals("/")
                    || elem.equals("*") || elem.equals("+") || elem.equals("-"))
                operands++;
        }
        if (numbers - 1 > operands) throw new RuntimeException("too many numbers in: " + pol);
        else if (numbers <= operands && !polArr.contains("DUP"))
            throw new RuntimeException("too few numbers in: " + pol);
        if (polArr.size() == 1) return tnodeArrayList.get(0);

        if (polArr.size() == 3 && !polArr.contains("DUP")) {
            addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n), tnodeArrayList.get(n));
            addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n + 1), tnodeArrayList.get(n + 1));
            return tnodeArrayList.get(n + 2);
        }
        while (n < polArr.size() - 2) {
            if (polArr.get(n + 2).equals("SWAP")) {
                if (operators.contains(polArr.get(n + 1))){
                    String a = polArr.get(n+2); //SWAP
                    String b = polArr.get(n-2);
                    polArr.set(n + 2, b);
                    polArr.set(n-2, a);
                    polArr.remove(n-2);
                } else {
                    String a = polArr.get(n);
                    String b = polArr.get(n + 1);
                    polArr.set(n + 1, a);
                    polArr.set(n, b);
                    polArr.remove(n + 2);
                }
                System.out.println("before  SWAP:"+polArr);
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < polArr.size(); i++) {
                    temp.append(polArr.get(i));
                    if (i != polArr.size() - 1) temp.append(" ");
                }
                System.out.println("after   SWAP:" + polArr);
                return buildFromRPN(temp.toString());
            } else if ((polArr.get(n + 1).equals("DUP"))) {
                System.out.println("before  DUP:"+polArr);
                if (n + 1 == polArr.size()-2) {
                    StringBuilder temp = new StringBuilder();
                    for (int i = 0; i < n+1; i++) {
                        temp.append(polArr.get(i));
                        if (i != polArr.size() - 1) temp.append(" ");
                    }
                    Tnode deepBuild = buildFromRPN(temp.toString());
                    Tnode deepBuild2 = buildFromRPN(temp.toString());
                    addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n), deepBuild);
                    addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n), deepBuild2);
                    root = tnodeArrayList.get(tnodeArrayList.size()-1);
                    return root;
                }
                polArr.set(n + 1, polArr.get(n));
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < polArr.size(); i++) {
                    temp.append(polArr.get(i));
                    if (i != polArr.size() - 1) temp.append(" ");
                }
                System.out.println("after   DUP:" + polArr);

                return buildFromRPN(temp.toString());
            } else if (n < polArr.size() - 3 && polArr.get(n + 3).equals("ROT")
            && polArr.get(n).matches(".*\\d.*") && polArr.get(n+1).matches(".*\\d.*") && polArr.get(n+2).matches(".*\\d.*")) {
                System.out.println("before  ROT:"+polArr);
                String a = polArr.get(n);
                String b = polArr.get(n + 1);
                String c = polArr.get(n + 2);
                polArr.set(n, b);
                polArr.set(n + 1, c);
                polArr.set(n + 2, a);
                polArr.remove(n + 3);
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < polArr.size(); i++) {
                    temp.append(polArr.get(i));
                    if (i != polArr.size() - 1) temp.append(" ");
                }
                System.out.println("after   ROT:" + polArr);
                return buildFromRPN(temp.toString());
            }
            if (operators.contains(polArr.get(n + 2)) && !operators.contains(polArr.get(n)) && !operators.contains(polArr.get(n + 1))
                    || operators.contains(polArr.get(n + 2)) && operators.contains(polArr.get(n)) && !operators.contains(polArr.get(n + 1))
                    || operators.contains(polArr.get(n + 2)) && operators.contains(polArr.get(n)) && operators.contains(polArr.get(n + 1))) {

                addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n), tnodeArrayList.get(n));
                addChildOrSibling(tnodeArrayList.get(n + 2), polArr.get(n + 1), tnodeArrayList.get(n + 1));

                root = tnodeArrayList.get(tnodeArrayList.size()-1);
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
    }
}

