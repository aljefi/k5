import java.util.*;

public class Tnode {
   String name;
   private Tnode firstChild; // left
   private Tnode nextSibling; // right
   StringBuffer buffer = new StringBuffer();

   public Tnode(String name) {
      this.name = name;
   }

   public Tnode(String name, Tnode firstChild, Tnode nextSibling) {
      this.name = name;
      this.firstChild = firstChild;
      this.nextSibling = nextSibling;
   }

   @Override
   public String toString() {
      StringBuffer b = new StringBuffer();
      // TODO!!!
      b.append(this.name);
      Tnode node = this;
      int i = 0;
      while (node.firstChild != null) {
         StringBuffer str = toStrHelper(node);
         b.append(str);
         node = this.firstChild;
         System.out.println("node: " + node);
         i++;
      }
      return b.toString();
   }

   public StringBuffer toStrHelper(Tnode node) {
      if (node.firstChild != null && node.nextSibling != null) {
         buffer.append("(");
         buffer.append(node.firstChild);
         buffer.append(",");
         buffer.append(node.nextSibling);
         buffer.append(")");
      }
      if (node.firstChild == null) {
         System.out.println(buffer.toString());
         return buffer;
      }
      toStrHelper(node.firstChild);
      toStrHelper(node.nextSibling);
      return buffer;
   }

   public static Tnode buildFromRPN(String pol) {
      // "2 1 - 4 * 6 3 / +"
      // +(*(-(2,1),4),/(6,3))
      Tnode root = null;
      // TODO!!!
      String operators = "+-*/";


      String[] workWith = pol.split(" ");
      ArrayList<String> polArr = new ArrayList<>();
      Collections.addAll(polArr, workWith);
      int n = 0;
      while (polArr.size() != 1) {
         // I
         if (operators.contains(polArr.get(n + 2))
                 && !operators.contains(polArr.get(n))
                 && !operators.contains(polArr.get(n + 1))) {
            Tnode leftChild = new Tnode(polArr.get(n));
            Tnode rightChild = new Tnode(polArr.get(n + 1));
            Tnode node = new Tnode(polArr.get(n + 2), leftChild, rightChild);
            polArr.remove(n + 1);
            polArr.remove(n);
            n = 0;
            root = node;

            System.out.println("name: " + node.name);
            System.out.println("left: " + leftChild);
            System.out.println("right: " + rightChild);
            System.out.println();

            continue;
         }
         // II
         if (operators.contains(polArr.get(n + 2))
                 && operators.contains(polArr.get(n))
                 && !operators.contains(polArr.get(n + 1))) {
            Tnode leftChild = new Tnode(polArr.get(n));
            Tnode rightChild = new Tnode(polArr.get(n + 1));
            Tnode node = new Tnode(polArr.get(n + 2), leftChild, rightChild);
            polArr.remove(n + 1);
            polArr.remove(n);
            n = 0;
            root = node;

            System.out.println("name: " + node.name);
            System.out.println("left: " + leftChild);
            System.out.println("right: " + rightChild);
            System.out.println();

            continue;
         }
         // III
         if (operators.contains(polArr.get(n + 2))
                 && operators.contains(polArr.get(n))
                 && operators.contains(polArr.get(n + 1))) {
            Tnode leftChild = new Tnode(polArr.get(n));
            Tnode rightChild = new Tnode(polArr.get(n + 1));
            Tnode node = new Tnode(polArr.get(n + 2), leftChild, rightChild);
            polArr.remove(n + 1);
            polArr.remove(n);
            n = 0;
            root = node;

            System.out.println("name: " + node.name);
            System.out.println("left: " + leftChild);
            System.out.println("right: " + rightChild);
            System.out.println();

            continue;
         }
         n++;
      }
      return root;
   }

   public static void main(String[] param) {
      String rpn = "2 1 - 4 * 6 3 / +";
      System.out.println("RPN: " + rpn);
      Tnode res = buildFromRPN(rpn);
      System.out.println("Tree: " + res);
      // TODO!!! Your tests here
   }
}

