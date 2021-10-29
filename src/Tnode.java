import java.util.*;

public class Tnode {
   private String name;
   private Tnode firstChild; // left
   private Tnode nextSibling; // right
   //private List<Tnode> children = new ArrayList<>();
   private Tnode parent;
   static StringBuffer buffer = new StringBuffer();

   public Tnode(Tnode parent) {
      this.parent = parent;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name=name;
   }
   //public List<Tnode> getChildren() {
   //   return children;
   //}
   //public Tnode getParent(){
   //   return parent;
   //}

   public Tnode() {}

   //public Tnode(String name, Tnode firstChild, Tnode nextSibling) {
   //   this.name = name;
   //   this.firstChild = firstChild;
   //   this.nextSibling = nextSibling;
   //}

   @Override
   public String toString() {
      StringBuffer b = new StringBuffer();
      // TODO!!!
      // +[*{-(2,1),4},/(6,3)]

      Tnode node = this;
      b.append(node.name);
      b.append(node.getFirstChild());

      return b.toString();
   }

   public static StringBuffer toStrHelp(Tnode node) {
      System.out.println("Node: " + node.getName());
      buffer.append("(");
      buffer.append(node.firstChild.getName());
      toStrHelp(node.firstChild);
      return buffer;
   }

   private static Tnode addChild(Tnode parent, Tnode firstChild, Tnode nextSibling){
      Tnode node = new Tnode(parent);
      node.setFirstChild(firstChild);
      node.setNextSibling(nextSibling);
      return node;
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
            // Tnode leftChild = new Tnode(polArr.get(n));
            // Tnode rightChild = new Tnode(polArr.get(n + 1));
            Tnode node = new Tnode();
            node.setName(polArr.get(n+2));
            Tnode firstChild = new Tnode();
            firstChild.setName(polArr.get(n));
            Tnode nextSibling = new Tnode();
            nextSibling.setName(polArr.get(n + 1));
            node = addChild(node, firstChild, nextSibling);

            polArr.remove(n + 1);
            polArr.remove(n);
            n = 0;
            root = node;

            System.out.println("name: " + node.getName());
            System.out.println("left: " + node.firstChild.name);
            System.out.println("right: " + node.getNextSibling().name);
            System.out.println();

            continue;
         }
         // II
         if (operators.contains(polArr.get(n + 2))
                 && operators.contains(polArr.get(n))
                 && !operators.contains(polArr.get(n + 1))) {
            //Tnode leftChild = new Tnode(polArr.get(n));
            //Tnode rightChild = new Tnode(polArr.get(n + 1));
            Tnode node = new Tnode();
            node.setName(polArr.get(n+2));
            Tnode firstChild = new Tnode();
            firstChild.setName(polArr.get(n));
            Tnode nextSibling = new Tnode();
            nextSibling.setName(polArr.get(n + 1));
            addChild(node, firstChild, nextSibling);

            polArr.remove(n + 1);
            polArr.remove(n);
            n = 0;
            root = node;

            //System.out.println("name: " + node.name);
            //System.out.println("left: " + node.firstChild.name);
            //System.out.println("right: " + node.getNextSibling().name);
            //System.out.println();

            continue;
         }
         // III
         if (operators.contains(polArr.get(n + 2))
                 && operators.contains(polArr.get(n))
                 && operators.contains(polArr.get(n + 1))) {
            // Tnode leftChild = new Tnode(polArr.get(n));
            // Tnode rightChild = new Tnode(polArr.get(n + 1));
            Tnode node = new Tnode();
            node.setName(polArr.get(n+2));
            Tnode firstChild = new Tnode();
            firstChild.setName(polArr.get(n));
            Tnode nextSibling = new Tnode();
            nextSibling.setName(polArr.get(n + 1));
            addChild(node, firstChild, nextSibling);

            polArr.remove(n + 1);
            polArr.remove(n);
            n = 0;
            root = node;

            //System.out.println("name: " + node.name);
            //System.out.println("left: " + node.firstChild.name);
            //System.out.println("right: " + node.getNextSibling().name);
            //System.out.println();

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
      System.out.println("res: " + res);
      System.out.println("Tree: " + res);
      // TODO!!! Your tests here
   }

   public Tnode getFirstChild() {
      return firstChild;
   }

   public void setFirstChild(Tnode firstChild) {
      this.firstChild = firstChild;
   }

   public Tnode getNextSibling() {
      return nextSibling;
   }

   public void setNextSibling(Tnode nextSibling) {
      this.nextSibling = nextSibling;
   }
}

