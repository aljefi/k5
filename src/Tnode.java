import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tnode {

    private String name;
    private Tnode firstChild;
    private Tnode nextSibling;
    private Tnode parent;

    public Tnode() {
    }

    public Tnode(String name, Tnode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tnode getFirstChild() {
        return firstChild;
    }

    public Tnode getNextSibling() {
        return nextSibling;
    }

    public Tnode getParent() {
        return parent;
    }


    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        // TODO!!!
        //"+(*(-(2,1),4),/(6,3))"

//        b.append(this.name); // +
//        b.append("(");
//        b.append(this.firstChild.name); // *
//        b.append("(");
//        b.append(this.firstChild.firstChild.name); // -
//        b.append("(");
//        b.append(this.firstChild.firstChild.firstChild.name); // 2
//        b.append(",");
//        b.append(this.firstChild.firstChild.firstChild.nextSibling.name); // 1
//        b.append(")");
//        b.append(",");
//        b.append(this.firstChild.firstChild.nextSibling.name); // 4
//        b.append(")");
//        b.append(",");
//        b.append(this.firstChild.nextSibling.name); // /
//        b.append("(");
//        b.append(this.firstChild.nextSibling.firstChild.name); // 6
//        b.append(",");
//        b.append(this.firstChild.nextSibling.firstChild.nextSibling.name); // 3
//        b.append(")");
//        b.append(")");

        Tnode temp = this;

        b.append(temp.name);
        if (temp.firstChild != null) {
            b.append("(");
            b.append(temp.firstChild.toString());
        }
        if (temp.nextSibling != null) {
            b.append(",");
            b.append(temp.nextSibling.toString());
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
        //Tnode parent = nodeMain;
//      if (polArr.size() == 2) {
//         Tnode node = new Tnode(parent, polArr.get(0));
//         addChild(nodeMain, polArr.get(0), node);
//         node = new Tnode(parent, polArr.get(1));
//         addChild(nodeMain, polArr.get(1), node);
//      }

//      while (n < polArr.size() - 2) {
////         // I
////         if (operators.contains(polArr.get(n + 2))
////                 && !operators.contains(polArr.get(n))
////                 && !operators.contains(polArr.get(n + 1))) {
////
////            Tnode node = new Tnode(null);
////            Tnode firstChild = new Tnode();
////            firstChild.setName(polArr.get(n));
////
////            Tnode nextSibling = new Tnode();
////            nextSibling.setName(polArr.get(n + 1));
////
////            node.setName(polArr.get(n + 2));
////            node.firstChild = firstChild;
////            node.nextSibling = nextSibling;
////
////
////            polArr.remove(n + 1);
////            polArr.remove(n);
////            n = 0;
////            root = node;
////            continue;
////         }
////         // II
////         if (operators.contains(polArr.get(n + 2))
////                 && operators.contains(polArr.get(n))
////                 && !operators.contains(polArr.get(n + 1))) {
////
////            Tnode node = new Tnode();
////            Tnode firstChild = new Tnode();
////            firstChild.setName(polArr.get(n));
////            firstChild.setParent(node);
////            Tnode nextSibling = new Tnode();
////            nextSibling.setName(polArr.get(n + 1));
////            nextSibling.setParent(node);
////            node.setName(polArr.get(n + 2));
////            node.firstChild = firstChild;
////            node.nextSibling = nextSibling;
////
////            polArr.remove(n + 1);
////            polArr.remove(n);
////            n = 0;
////            root = node;
////            continue;
////         }
////         // III
////         if (operators.contains(polArr.get(n + 2))
////                 && operators.contains(polArr.get(n))
////                 && operators.contains(polArr.get(n + 1))) {
////
////            Tnode node = new Tnode();
////            Tnode firstChild = new Tnode();
////            firstChild.setName(polArr.get(n));
////            firstChild.setParent(node);
////            Tnode nextSibling = new Tnode();
////            nextSibling.setName(polArr.get(n + 1));
////            nextSibling.setParent(node);
////            node.setName(polArr.get(n + 2));
////            node.firstChild = firstChild;
////            node.nextSibling = nextSibling;
////
////            polArr.remove(n + 1);
////            polArr.remove(n);
////            n = 0;
////            root = node;
////            continue;
////         }
////         n++;
//         //Tnode firstChild = addChild(nodeMain, polArr.get(n));
//         String top = polArr.get((n));
//         String right = polArr.get(n + 1);
//         String left = polArr.get(n + 2);
//
//         if ((operators.contains(top)
//                 && !operators.contains(right)
//                 && !operators.contains(left))
//                 ||
//                 (operators.contains(top)
//                         && !operators.contains(right)
//                         && operators.contains(left))) {
//
//            Tnode node = new Tnode(parent, top);
//            Tnode tRight = new Tnode(node, right);
//            Tnode tLeft = new Tnode(node, left);
//            //node.setName(top);
//            addChild(node, right, tRight);
//            addChild(node, left, tLeft);
//            if (nodeMain.firstChild == null) {
//               node = new Tnode(parent, top);
//               addChild(nodeMain, top, node);
//            } else if (nodeMain.nextSibling == null) {
//               node = new Tnode(parent, top);
//               addChild(nodeMain, top,node);
//            }
//            if (parent.firstChild != null && parent.nextSibling != null) {
//               parent = node;
//            }
//            n++;
//            count++;
//
//            continue;
//
//         }
//         count++;
//         n++;
//      }
        ArrayList<Tnode> tnodeArrayList = new ArrayList<>();
        for (String elem : polArr) {
            Tnode tnode = new Tnode(elem, null);
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
            return root = tnodeArrayList.get(n + 2);
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
        //System.out.println(res.firstChild.getFirstChild());
        //System.out.println(res.nextSibling.getFirstChild());
        System.out.println("Tree: " + res);
        // TODO!!! Your tests here
    }
}

