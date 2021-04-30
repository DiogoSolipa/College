public class QuadHashTable<T> extends HashTable<T>{

    private static final int DEFAULT_SIZE = 11;

    QuadHashTable() {
        this(DEFAULT_SIZE);
    }

    QuadHashTable(int dim) {

        this.dim = dim;

        allocateTable(dim);
        empty();
    }

    public int searchPos(T x) {

        int offset = 1;
        int currentPos = hash(x);

        while (arr[currentPos] != null && !arr[currentPos].element.equals(x)) {

            currentPos = hash(x) + (int)Math.pow(offset, 2);
            offset ++;

            if(currentPos >= arr.length)
                currentPos -= arr.length;
        }

        return currentPos;
    }

    public static void main (String [] args) {

        QuadHashTable<Integer> table = new QuadHashTable<>();

        System.out.println(table.searchPos(20));

        table.insert(1);
        table.insert(2);
        table.insert(3);

        table.print();
    }
}