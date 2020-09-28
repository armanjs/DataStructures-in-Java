/** In this program we will be investigating the Binary Search Tree or BST.
 * First we need to define the TreeNode class and define the properties
 * such as left child and right child. After doing so, we need to define
 * all the proper functions necessary to take full advantage of our Binary
 * Tree structure. Functions such as insert, delete, preorder, inorder
 * and postorder are quite useful.
 * April 30, 2020
 * Arman Sadeghi
 */
package BinarySearchTree;

public class BST<E extends Comparable<E>>  {

    protected TreeNode <E> root;
    protected int size = 0;

    /** create a default binary search tree */
    public BST(){
    }

    /** Create a binary search tree from an array of objects */
    public BST(E[] objects){
        for (int i = 0; i < objects.length; i++){
            insert(objects[i]);
        }
    }

    /** return true if the element is in the tree */
    public boolean search(E e){
        TreeNode<E> current = root; // start from the root

        while (current != null){
            if (e.compareTo(current.element) < 0){
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0){
                current = current.right;
            }
            else  // if element matches current.element
                return true;  // Element is found
        }
        return false;
    }

    /** Insert element e into the binary search tree.
    * Return true if the element is inserted successfully. */
    public boolean insert (E e){
        if (root == null){
            root = createNewNode(e); // create a new root
        }
        else {
            // locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null){
                if (e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.right;
                }
                else
                    return false;  // duplicate node not inserted
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0){
                parent.left = createNewNode(e);
            }
            else if (e.compareTo(parent.element) > 0){
                parent.right = createNewNode(e);
            }
        }
        size ++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<E>(e);
    }

    /** inorder traversal from the root */
    public void inorder(){
        inorder(root);
    }

    /** inorder traversal from the subtree */
    protected void inorder(TreeNode<E> root){
        if (root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /** Postorder traversal from the root */
    public void postorder(){
        postorder(root);
    }

    /** Preorder traversal from a subtree */
    protected void postorder(TreeNode<E> root){
        if (root == null){
            return;
        }
        System.out.print(root.element + " ");
        postorder(root.left);
        postorder(root.right);
    }

    /** Preorder traversal from the root */
    public void preorder(){
        preorder(root);
    }

    /** Postorder traversal from the subtree */
    protected void preorder(TreeNode<E> root){
        if (root == null){
            return;
        } // apparently the book's code was different for some reason.
        preorder(root.left);
        preorder(root.right);
        System.out.print(root.element + " ");
    }


    /** This inner class is static, because it does not access
     any instance members defined in its outer class */
    public static class TreeNode<E extends Comparable<E>> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }

    /** Get the number of nodes in the tree */
    public int getSize(){
        return size;
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot(){
        return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path (E e){
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root; // start from root

        while (current != null){
            list.add(current);
            if (e.compareTo(current.element) < 0){
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0){
                current = current.right;
            }
            else
                break;
        }
        return list; //  Return an array list of nodes
    }

    /** Delete an element from the binary search tree.
     * Return true if the element is deleted successfully.
     * Return false if the element is not in the tree. */
    public boolean delete(E e){
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0){
                parent = current;
                current = current.left;
            }
            else
                break; // Element is in the tree pointed at by current
        }
        if (current == null)
            return false; // Element is not in the tree
        // Case 1: current has no left child
        if (current.left == null){
            if (parent == null){
                root = current.right;
            }
            else {
                if (e.compareTo(current.element) < 0){
                    parent.left = current.right;
                }
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent.
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // keep going to the right
            }
            current.element = rightMost.element;
            // Eliminate the rightmost node
            if (parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            }
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }
        size --;
        return true;
    }

    java.util.Iterator<E> iterator(){
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {

        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in the list

        public InorderIterator(){
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void inorder(){
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root){
            if (root == null){
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        /** More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size()){
                return true;
            }
            return false;
        }

        @Override
        /** Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }

        @Override
        /** Remove the current element */
        public void remove(){
            delete(list.get(current)); // Delete the current element
            list.clear(); // clear the list
            inorder(); // rebuild the list
        }
    }

    /** Remove all elements from the tree */
    public void clear(){
        root = null;
        size = 0;
    }

    public static void main(String[] args) {
        // create a new BST
        TestBST<String> tree = new TestBST<>();
        System.out.print("Enter (1) to Search, (2) to insert, (3) to print inorder\n" +
                "Enter (4) to print postorder, (5) to print preorder, (6) to getSize\n" +
                "Enter (7) to get path from root, (8) to delete, Enter (9) to clear list, (-1) to quit: ");
        java.util.Scanner input = new java.util.Scanner(System.in);
        int option = input.nextInt();
        while (option != -1){
            if (option == 1){
                System.out.print("Please enter an element to search for: ");
                String element = input.next();
                if (tree.search(element))
                    System.out.println("Yes! (" + element + ") exists in the tree.");
                else
                    System.out.println("The element was not found.");
            }
            else if (option == 2){
                System.out.print("Enter an element to be inserted into the tree: ");
                String element = input.next();
                tree.insert(element);
                System.out.println("Your tree has been updated.");
            }
            else if (option == 3){
                System.out.print("Inorder tree is: ");
                tree.inorder();
                System.out.print("\n");
            }
            else if (option == 4){
                System.out.print("Postorder tree is: ");
                tree.postorder();
                System.out.print("\n");
            }
            else if (option == 5){
                System.out.print("Preorder tree is: ");
                tree.preorder();
                System.out.print("\n");
            }
            else if (option == 6){
                System.out.println("The size of the tree is: " + tree.getSize());
            }
            else if (option == 7){
                System.out.print("Enter an element from the tree to find path: ");
                String element = input.next();
                if (tree.search(element)){
                    System.out.print("\nA path from the root to " + element + " is: ");
                    java.util.ArrayList<TestBST.TreeNode<String>> path = tree.path(element);
                    for (int i = 0; path != null && i < path.size(); i++)
                        System.out.print(path.get(i).element + " ");
                    System.out.print("\n");
                }
                else
                    System.out.println("The entered element was not found.");
            }
            else if (option == 8){
                System.out.print("Enter an element to be removed from your tree: ");
                String element = input.next();
                if (tree.delete(element))
                    System.out.println("The entered element has been removed from the tree.");
                else
                    System.out.println("The entered element was no found in the tree.");
            }
            else if (option == 9){
                tree.clear();
                System.out.println("Your tree has been cleared.");
            }
            else {
                System.out.println("Invalid option. Try again.");
            }
            System.out.print("Please select a number from (1 - 9) to perform an action or -1 to quit: ");
            option = input.nextInt();
        }
        System.out.print("the final list in order is: ");
        tree.inorder();
        System.out.print("\n");

        /* Uncomment to test TestBST
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");
        // traverse tree
        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder: ");
        tree.postorder();
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.print("\nThe number of nodes is " + tree.getSize());
        // Search for an element
        System.out.print("\nIs Peter in the tree? " + tree.search("Peter"));
        // Get a path from the root to Peter
        System.out.print("\nA path from the root to Peter is: ");
        java.util.ArrayList<TestBST.TreeNode<String>> path = tree.path("Peter");

        for (int i = 0; path != null && i < path.size(); i++)
            System.out.print(path.get(i).element + " ");

        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        TestBST<Integer> intTree = new TestBST<>(numbers);
        System.out.print("\nInorder (sorted): ");
        intTree.inorder();
         */
    }
}
