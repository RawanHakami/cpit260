
package group_project260;

import java.util.*;
public class Group_project260 {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MemorySimulator mem = new MemorySimulator();

        while (true) {
            System.out.println("1. Allocate"); // add process in memory blocks
            System.out.println("2. Deallocate");// remove process from memory blocks
            System.out.println("3. Show Memory");
            System.out.println("4. Exit");
            System.out.print("ُEnter Choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter process name (p1): ");
                    String name = input.next();
                    System.out.print("Enter size blocks: ");
                    int size = input.nextInt();
                   
                    System.out.print("Algorithm (1 for First Fit, 2 for Best Fit): ");
                    int algo = input.nextInt();

                   if (algo == 1) {
                       mem.allocateFirstFit(name, size);

                   } else if (algo == 2) {
                       mem.allocateBestFit(name, size);
                       
                   } else {   
                       System.out.println(" Invalid algorithm choice.");
                   }
                    break;

                case 2:
                    System.out.print("Enter process name to deallocate: ");
                    String deName = input.next();
                    mem.deallocate(deName);
                    break;

                case 3:
                    mem.showMemory();
                    break;

                case 4:
                    System.out.println("Exiting.");
                    return;

                default:
                   System.out.println("Invalid choice.");
            }
        }
    }
}