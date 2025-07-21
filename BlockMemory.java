
package group_project260;

public class BlockMemory {
    String name;
    int start;
    int size;
    boolean isFree;

    public BlockMemory(String name, int start, int size, boolean isFree) {
        this.name = name;
        this.start = start;
        this.size = size;
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return "[" + name + " | Start: " + start + " | Size: " + size + " | " + (isFree ? "Free" : "Used") + "]";
    }
}