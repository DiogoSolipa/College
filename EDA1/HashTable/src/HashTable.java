public abstract class HashTable<T> implements iHashTable<T> {

    int dim, currentSize;
    protected Element<T>[] arr;

    public int hash(T x) {

        return hashCode() % dim;
    }

    private int nextPrime(int n) {

        int counter;
        n++;

        while (true) {

            int l = (int) Math.sqrt(n);

            counter = 0;

            for (int i = 2; i <= l; i++) {

                if (n % i == 0) counter++;
            }

            if (counter == 0)

                return n;

            else {

                n++;
                continue;
            }
        }
    }


    public int nElements() {

        return currentSize;
    }

    public float loadFactor() {

        return currentSize / dim;
    }

    public abstract int searchPos(T x);

    public void allocateTable(int dim) {

        arr = new Element[nextPrime(dim)];
    }

    /*
    Colocar a tabela vazia
    */
    public void empty() {

        currentSize = 0;

        for (int i = 0; i < arr.length; i++) {

            arr[i] = null;
        }

    }


    public T search(T x) {

        int currentPos = searchPos(x);

        return arr[currentPos].element;

    }

    public void remove(T x) {

        int currentPos = searchPos(x);

        if (x == search(x))

            arr[currentPos] = null;

        currentSize--;
    }

    /*
    Inserir na Tabela x
    Se ja existir x, nao fazer nada
    */
    public void insert(T x) {

        int currentPos = searchPos(x);


        if (x == search(x)) //se o elemento ja existe sai da funcao
            return;

        arr[currentPos] = new Element(x);

        currentSize++;

        if (loadFactor() > 0.5)//fator de carga > 0.5 reHash
            reHash();

    }

    public void reHash() {

        Element<T>[] oldArr = arr;

        allocateTable(nextPrime(2 * oldArr.length));
        currentSize = 0;

        for (int i = 0; i < oldArr.length; i++) {
            if (oldArr[i] != null && oldArr[i] == search(arr[i].element)) //pode ter problemas
                insert(oldArr[i].element);
        }
    }

    public void print() {

        String s = "";

        for (int i = 0; i < arr.length; i++) {

            s += arr[i] + "\n";
        }
    }
}