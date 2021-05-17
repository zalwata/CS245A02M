import java.util.Iterator;
import java.util.NoSuchElementException;

public class BagStructure<CountingObjects> implements Iterable<CountingObjects> {
    private int COUNTEDOBJECTSINTHEBAG;
    private lINKEDlISTNode<CountingObjects> first;

    //linkedlist
    private class lINKEDlISTNode<CountingObjects> {
        private CountingObjects data;
        private lINKEDlISTNode<CountingObjects> next;
    }

    //bag constructor
    public BagStructure() {
        first = null;
        COUNTEDOBJECTSINTHEBAG = 0;
    }

    public Iterator<CountingObjects> iterator()  {
        return new ListIterator<CountingObjects>(first);
    }

    //using iterator to go through the linked list
    private class ListIterator<CountingObjects> implements Iterator<CountingObjects> {
        private lINKEDlISTNode<CountingObjects> current;

        public ListIterator(lINKEDlISTNode<CountingObjects> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public CountingObjects next() {
            if (!hasNext()) throw new NoSuchElementException();
            CountingObjects item = current.data;
            current = current.next;
            return item;
        }
    }

    //add linkedlist
    public void add(CountingObjects item) {
        lINKEDlISTNode<CountingObjects> oldfirst = first;
        first = new lINKEDlISTNode<CountingObjects>();
        first.data = item;
        first.next = oldfirst;
        COUNTEDOBJECTSINTHEBAG++;
    }
}