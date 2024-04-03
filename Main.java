import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void DisplayOptions(Tree tree) {
        System.out.println("\n\n");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Tree: ");
        //TODO: print out the tree
        tree.DisplayTree();
        System.out.println("");
        System.out.println("-------------------------------------------------------------");
        System.out.println("1. Insert a new key into the Binary Search Tree.");
        System.out.println("2. Delete an existing key from the Binary Search Tree.");
        System.out.println("3. Search for an existing key from the Binary Search Tree.");
        System.out.println("0. Exit Program.");
    }

    public static int PromptUser(boolean forKey) {
        boolean isValid;
        int data = -1;

        do {
            isValid = true;
            
            if (forKey) {
                System.out.print("\nPlease input a number for the Key data: ");
            } else {
                System.out.print("\nPlease choose a number corresponding with the choices above: ");
            }

            try {
                data = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please make sure the input is an integer number.");
                isValid = false;
            }
            scan.nextLine();
        } while (!isValid);

        return data;
    }


    public static void Insertion(Tree tree) {
        tree.InsertItem(PromptUser(true));
    }

    public static void Deletion(Tree tree) {
        Node<Integer> deletedNode = tree.DeleteItem(PromptUser(true));
        if (deletedNode != null) {
            System.out.printf("%d was found and has been deleted", deletedNode.GetKey());
        } else {
            System.out.printf("The key you requested could not be found. No deletion took place.");
        }
    }

    public static void Searching(Tree tree) {
        int key = PromptUser(true);
        if (tree.SearchItem(key)) {
            System.out.printf("\n%d does exist within the Tree.\n", key);
        } else {
            System.out.printf("\n%d does NOT exist within the Tree.\n", key);
        }
    }

    public static void main(String[] args) {
        Tree<Integer> myTree = new Tree<Integer>();

        myTree.InsertItem(55);
        myTree.InsertItem(21);
        myTree.InsertItem(25);
        myTree.InsertItem(78);
        myTree.InsertItem(63);
        myTree.InsertItem(4);
        myTree.InsertItem(90);
        myTree.InsertItem(34);


        while (true) {
            DisplayOptions(myTree);
            switch (PromptUser(false)) {
                case -1:
                    System.out.println("\nDEBUG: There is something wrong with the user's input loop.");
                case 0:
                    scan.close();
                    System.out.println("\n\n\nExiting Program...");
                    System.exit(1);
                    break;
                case 1:
                    Insertion(myTree);
                    break;
                case 2:
                    Deletion(myTree);
                    break;
                case 3:
                    Searching(myTree);
                    break;
                default:
                    System.out.println("\nPlease make sure that you are inputting one of the choices listed.");
                    break;
            }
        }
    }
}