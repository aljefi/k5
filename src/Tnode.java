import java.util.*;

public class Tnode {

   private String name;
   private Tnode firstChild;
   private Tnode nextSibling;
   private Tnode parent;

   public Tnode() {
   }

   public Tnode(Tnode parent, String name) {
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
      b.append(this.name);
      b.append("(");
      b.append(this.getFirstChild().name);
      b.append(",");
      b.append(this.getNextSibling().name);
      b.append(")");
      return b.toString();
   }

   private static Tnode addChild(Tnode parent, String name, Tnode node) {
      //Tnode node = new Tnode(parent, name);
      node.setName(name);
      if (parent.nextSibling == null) {
         parent.nextSibling = node;
      } else {
         parent.firstChild = node;
      }
      return node;
   }

   public static Tnode buildFromRPN(String pol) {
      Tnode root = null;
      // TODO!!!
      String operators = "+-*/";
      String[] workWith = pol.split(" ");
      ArrayList<String> polArr = new ArrayList<>();
      Collections.addAll(polArr, workWith);
      Tnode nodeMain = new Tnode(null, polArr.get(polArr.size() - 1));
      //nodeMain.setName(polArr.get(polArr.size() - 1));
      Collections.reverse(polArr);
      polArr.remove(0);
      int n = 0;
      int count = 0;
      int p = 0;
      Tnode parent = nodeMain;
      if (polArr.size() == 2) {
         Tnode node = new Tnode(parent, polArr.get(0));
         addChild(nodeMain, polArr.get(0), node);
         node = new Tnode(parent, polArr.get(1));
         addChild(nodeMain, polArr.get(1), node);
      }
      while (n < polArr.size() - 2) {
//         // I
//         if (operators.contains(polArr.get(n + 2))
//                 && !operators.contains(polArr.get(n))
//                 && !operators.contains(polArr.get(n + 1))) {
//
//            Tnode node = new Tnode(null);
//            Tnode firstChild = new Tnode();
//            firstChild.setName(polArr.get(n));
//
//            Tnode nextSibling = new Tnode();
//            nextSibling.setName(polArr.get(n + 1));
//
//            node.setName(polArr.get(n + 2));
//            node.firstChild = firstChild;
//            node.nextSibling = nextSibling;
//
//
//            polArr.remove(n + 1);
//            polArr.remove(n);
//            n = 0;
//            root = node;
//            continue;
//         }
//         // II
//         if (operators.contains(polArr.get(n + 2))
//                 && operators.contains(polArr.get(n))
//                 && !operators.contains(polArr.get(n + 1))) {
//
//            Tnode node = new Tnode();
//            Tnode firstChild = new Tnode();
//            firstChild.setName(polArr.get(n));
//            firstChild.setParent(node);
//            Tnode nextSibling = new Tnode();
//            nextSibling.setName(polArr.get(n + 1));
//            nextSibling.setParent(node);
//            node.setName(polArr.get(n + 2));
//            node.firstChild = firstChild;
//            node.nextSibling = nextSibling;
//
//            polArr.remove(n + 1);
//            polArr.remove(n);
//            n = 0;
//            root = node;
//            continue;
//         }
//         // III
//         if (operators.contains(polArr.get(n + 2))
//                 && operators.contains(polArr.get(n))
//                 && operators.contains(polArr.get(n + 1))) {
//
//            Tnode node = new Tnode();
//            Tnode firstChild = new Tnode();
//            firstChild.setName(polArr.get(n));
//            firstChild.setParent(node);
//            Tnode nextSibling = new Tnode();
//            nextSibling.setName(polArr.get(n + 1));
//            nextSibling.setParent(node);
//            node.setName(polArr.get(n + 2));
//            node.firstChild = firstChild;
//            node.nextSibling = nextSibling;
//
//            polArr.remove(n + 1);
//            polArr.remove(n);
//            n = 0;
//            root = node;
//            continue;
//         }
//         n++;
         //Tnode firstChild = addChild(nodeMain, polArr.get(n));
         String top = polArr.get((n));
         String right = polArr.get(n + 1);
         String left = polArr.get(n + 2);

         if ((operators.contains(top)
                 && !operators.contains(right)
                 && !operators.contains(left))
                 ||
                 (operators.contains(top)
                         && !operators.contains(right)
                         && operators.contains(left))) {

            Tnode node = new Tnode(parent, top);
            Tnode tRight = new Tnode(node, right);
            Tnode tLeft = new Tnode(node, left);
            //node.setName(top);
            addChild(node, right, tRight);
            addChild(node, left, tLeft);
            if (nodeMain.firstChild == null) {
               node = new Tnode(parent, top);
               addChild(nodeMain, top, node);
            } else if (nodeMain.nextSibling == null) {
               node = new Tnode(parent, top);
               addChild(nodeMain, top,node);
            }
            if (parent.firstChild != null && parent.nextSibling != null) {
               parent = node;
            }
            n++;
            count++;

            continue;

         }
         count++;
         n++;
      }

      root = nodeMain;
      return root;
   }

   public static void main(String[] param) {
      String rpn = "2 1 - 4 * 6 3 / +";
      System.out.println("RPN: " + rpn);
      Tnode res = buildFromRPN(rpn);
      System.out.println(res.firstChild.getFirstChild());
      System.out.println(res.nextSibling.getFirstChild());
      System.out.println("Tree: " + res);
      // TODO!!! Your tests here
   }
}

