
package group_project260;

import java.util.*;

public class MemorySimulator {
    private final int TOTAL_MEMORY = 100;
    private final LinkedList<BlockMemory> memory = new LinkedList<>();

    public MemorySimulator() {
        memory.add(new BlockMemory("Free", 0, TOTAL_MEMORY, true));
    }

    public void showMemory() {
        System.out.println("\n Process in Memory :");
        for (BlockMemory seg : memory) {
            System.out.println(seg);
        }
        System.out.println();
    }

    
   // Allocate memory using First Fit algorithm
    public void allocateFirstFit(String name, int size) {
        List<BlockMemory> holes = new ArrayList<>();

        // Find all free segments large enough for the process
        for (BlockMemory seg : memory) {
            if (seg.isFree && seg.size >= size) {
                holes.add(seg);
            }
        }

        if (holes.isEmpty()) {
            System.out.println("No suitable hole found for First Fit.");
            return;
        }

        // Sort holes by starting address (First Fit strategy)
        holes.sort(Comparator.comparingInt(s -> s.start));
        BlockMemory block = holes.get(0);
        int index = memory.indexOf(block);

        
        // Replace hole with the new process BlockMemory
        BlockMemory process = new BlockMemory(name, block.start, size, false);
        memory.set(index, process);

        // If there's remaining space, insert a new hole after the allocated process
        if (block.size > size) {
            BlockMemory remaining = new BlockMemory("Free", block.start + size, block.size - size, true);
            memory.add(index + 1, remaining);
        }

        System.out.println("Process " + name + " allocated using First Fit.");
    }

    // Allocate memory using Best Fit algorithm
    public void allocateBestFit(String name, int size) {
        List<BlockMemory> holes = new ArrayList<>();

        // Find all free segments large enough for the process
        for (BlockMemory seg : memory) {
            if (seg.isFree && seg.size >= size) {
                holes.add(seg);
            }
        }

        if (holes.isEmpty()) {
            System.out.println("No suitable hole found for Best Fit.");
            return;
        }

        // Sort holes by size (Best Fit strategy)
        holes.sort(Comparator.comparingInt(s -> s.size));
        BlockMemory hole = holes.get(0);
        int index = memory.indexOf(hole);

        System.out.println("Best Fit selected hole → Start: " + hole.start + ", Size: " + hole.size);

        // Replace hole with the new process segment
        BlockMemory process = new BlockMemory(name, hole.start, size, false);
        memory.set(index, process);

        // If there's remaining space, insert a new hole after the allocated process
        if (hole.size > size) {
            BlockMemory remaining = new BlockMemory("Free", hole.start + size, hole.size - size, true);
            memory.add(index + 1, remaining);
        }

        System.out.println("Process " + name + " allocated using Best Fit.");
    }

    // Deallocate a process from memory
    public void deallocate(String name) {
        boolean found = false;

        for (BlockMemory seg : memory) {
            if (!seg.isFree && seg.name.equalsIgnoreCase(name)) {
                seg.name = "Free";
                seg.isFree = true;
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Process not found.");
            return;
        }

       mergeFree();
       System.out.println(" Deallocated " + name);
    }

   private void mergeFree() {
        for (int i = 0; i < memory.size() - 1; ) {
            BlockMemory current = memory.get(i);
            BlockMemory next = memory.get(i + 1);

            if (current.isFree && next.isFree) {
                current.size += next.size;
                memory.remove(i + 1);
            } else {
                i++;
            }
        }
    }

}