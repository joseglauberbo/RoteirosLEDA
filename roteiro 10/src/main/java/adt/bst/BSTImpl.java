package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;
   private static final int ZERO = 0;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      return this.recursiveHeight(this.root);
   }

   private int recursiveHeight(BSTNode<T> node) {

      if (node.isEmpty()) {
         return -1;
      } else {
         int leftHeight = recursiveHeight((BSTNode<T>) node.getLeft());
         int rightHeight = recursiveHeight((BSTNode<T>) node.getRight());
         return Math.max(leftHeight, rightHeight) + 1;
      }

   }

   @Override
   public BSTNode<T> search(T element) {
      return this.recursiveSearch(element, this.root);
   }

   private BSTNode<T> recursiveSearch(T element, BSTNode<T> node) {

      if (element != null) {
         if (node.isEmpty()) {
            return node;
         }

         if (element.compareTo(node.getData()) == ZERO) {
            return node;
         } else {
            if (element.compareTo(node.getData()) > ZERO) {
               return recursiveSearch(element, (BSTNode<T>) node.getRight());
            } else {
               return recursiveSearch(element, (BSTNode<T>) node.getLeft());
            }
         }
      }
      return node;
   }

   @Override
   public void insert(T element) {
      this.recursiveInsert(element, this.root);
   }

   private void recursiveInsert(T element, BSTNode<T> node) {

      if (element != null) {
         if (node.isEmpty()) {
            node.setData(element);
            node.setLeft(new BSTNode<T>());
            node.setRight(new BSTNode<T>());
            node.getLeft().setParent(node);
            node.getRight().setParent(node);
         } else {
            if (element.compareTo(node.getData()) > ZERO) {
               recursiveInsert(element, (BSTNode<T>) node.getRight());
            } else if (element.compareTo(node.getData()) < ZERO) {
               recursiveInsert(element, (BSTNode<T>) node.getLeft());
            }
         }
      }
   }

   @Override
   public BSTNode<T> maximum() {
      return recursiveMaximum(this.root);
   }

   private BSTNode<T> recursiveMaximum(BSTNode<T> node) {

      if (node.isEmpty()) {
         return null;
      } else if (node.getRight().isEmpty()) {
         return node;
      } else {
         return recursiveMaximum((BSTNode<T>) node.getRight());
      }
   }

   @Override
   public BSTNode<T> minimum() {
      return recursiveMinimum(this.root);
   }

   private BSTNode<T> recursiveMinimum(BSTNode<T> node) {

      if (node.isEmpty()) {
         return null;
      } else if (node.getLeft().isEmpty()) {
         return node;
      } else {
         return recursiveMinimum((BSTNode<T>) node.getLeft());
      }
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Not implemented yet!");
   }

   @Override
   public BSTNode<T> predecessor(T element) {

      if (element.compareTo(this.root.getData()) == ZERO) {
         return null;
      }
      return recursivePredecessor(element, this.root);
   }

   private BSTNode<T> recursivePredecessor(T element, BSTNode<T> node) {

      if (element != null) {
         if (node.isEmpty()) {
            return null;
         } else {
            if (element.compareTo(node.getData()) == ZERO) {
               return (BSTNode<T>) node.getParent();
            } else {
               if (element.compareTo(node.getData()) > ZERO) {
                  return recursivePredecessor(element, (BSTNode<T>) node.getRight());
               } else if (element.compareTo(node.getData()) < ZERO) {
                  return recursivePredecessor(element, (BSTNode<T>) node.getLeft());
               }
            }
         }
      }
      return null;
   }

   @Override
   public void remove(T element) {
      this.recursiveRemove(element, this.root);
   }

   private void recursiveRemove(T element, BSTNode<T> node) {

      if (element != null) {
         if (!node.isEmpty()) {

            if (element.compareTo(node.getData()) == ZERO) {
               node.setData(null);
               node.getRight().setParent(node.getParent());
               node.getLeft().setParent(node.getParent());
            } else {

            }

         }
      }

   }

   @Override
   public T[] preOrder() {
      T[] newArray = (T[]) new Comparable[this.size()];
      this.preOrder(newArray, this.root, ZERO);
      return newArray;
   }

   private int preOrder(T[] array, BSTNode<T> node, int index) {
      if (!node.isEmpty()) {
         array[index] = node.getData();
         index++;
         index = this.preOrder(array, (BSTNode) node.getLeft(), index);
         index = this.preOrder(array, (BSTNode) node.getRight(), index);
      }
      return index;
   }

   @Override
   public T[] order() {
      T[] newArray = (T[]) new Comparable[this.size()];
      this.order(newArray, this.root, ZERO);
      return newArray;
   }

   private int order(T[] array, BSTNode<T> node, int index) {
      if (!node.isEmpty()) {
         index = this.preOrder(array, (BSTNode) node.getLeft(), index);
         array[index] = node.getData();
         index++;
         index = this.preOrder(array, (BSTNode) node.getRight(), index);
      }
      return index;
   }

   @Override
   public T[] postOrder() {
      T[] newArray = (T[]) new Comparable[this.size()];
      this.postOrder(newArray, this.root, ZERO);
      return newArray;
   }

   private int postOrder(T[] array, BSTNode<T> node, int index) {
      if (!node.isEmpty()) {
         index = this.preOrder(array, (BSTNode) node.getLeft(), index);
         index = this.preOrder(array, (BSTNode) node.getRight(), index);
         array[index] = node.getData();
         index++;
      }
      return index;
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty()) { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

}
