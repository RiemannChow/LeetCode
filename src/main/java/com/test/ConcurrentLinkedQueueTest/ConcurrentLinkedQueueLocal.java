package com.test.ConcurrentLinkedQueueTest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

/**
 * 本地oncurrentLindedQueue
 */
public class ConcurrentLinkedQueueLocal<E> extends AbstractQueue<E>
        implements Queue<E>, java.io.Serializable {

    private static class Node<E> {
        volatile E item;
        volatile Node<E> next;

        /**
         * Constructs a new node.  Uses relaxed write because item can
         * only be seen after publication via casNext.
         */
        Node(E item) {
            UNSAFE.putObject(this, itemOffset, item);
        }

        boolean casItem(E cmp, E val) {
            return UNSAFE.compareAndSwapObject(this, itemOffset, cmp, val);
        }

        void lazySetNext(Node<E> val) {
            UNSAFE.putOrderedObject(this, nextOffset, val);
        }

        boolean casNext(Node<E> cmp, Node<E> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        // Unsafe mechanics

        private static final Unsafe UNSAFE;
        private static final long itemOffset;
        private static final long nextOffset;

        static {
            try {
                Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
                singleoneInstanceField.setAccessible(true);
                UNSAFE = (Unsafe)singleoneInstanceField.get(null);
                Class<?> k = Node.class;
                itemOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("item"));
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
    }

    private transient volatile Node<E> head;

    private transient volatile Node<E> tail;

    public ConcurrentLinkedQueueLocal() {
        head = tail = new Node<E>(null);
    }

    public ConcurrentLinkedQueueLocal(Collection<? extends E> c) {
        Node<E> h = null, t = null;
        for (E e : c) {
            checkNotNull(e);
            Node<E> newNode = new Node<E>(e);
            if (h == null)
                h = t = newNode;
            else {
                t.lazySetNext(newNode);
                t = newNode;
            }
        }
        if (h == null)
            h = t = new Node<E>(null);
        head = h;
        tail = t;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    final void updateHead(Node<E> h, Node<E> p) {
        if (h != p && casHead(h, p))
            h.lazySetNext(h);
    }

    final Node<E> succ(Node<E> p) {
        Node<E> next = p.next;
        return (p == next) ? head : next;
    }

    @Override
    public boolean offer(E e) {
        checkNotNull(e);
        final Node<E> newNode = new Node<E>(e);

        for (Node<E> t = tail, p = t;;) {
            Node<E> q = p.next;
            if (q == null) {
                // p is last node
                if (p.casNext(null, newNode)) {
                    // Successful CAS is the linearization point
                    // for e to become an element of this queue,
                    // and for newNode to become "live".
                    if (p != t) // hop two nodes at a time
                        casTail(t, newNode);  // Failure is OK.
                    return true;
                }
                // Lost CAS race to another thread; re-read next
            }
            else if (p == q) {
                System.out.println("第二次走了p==q分支");
                // We have fallen off list.  If tail is unchanged, it
                // will also be off-list, in which case we need to
                // jump to head, from which all live nodes are always
                // reachable.  Else the new tail is a better bet.
                p = (t != (t = tail)) ? t : head;
            } else {
                System.out.println("第二次走了最后的else分支");
                // Check for tail updates after two hops.
                p = (p != t && t != (t = tail)) ? t : q;
            }
        }
    }

    @Override
    public E poll() {
        restartFromHead:
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
                E item = p.item;

                if (item != null && p.casItem(item, null)) {
                    // Successful CAS is the linearization point
                    // for item to be removed from this queue.
                    if (p != h) // hop two nodes at a time
                        updateHead(h, ((q = p.next) != null) ? q : p);
                    return item;
                }
                else if ((q = p.next) == null) {
                    updateHead(h, p);
                    return null;
                }
                else if (p == q)
                    continue restartFromHead;
                else
                    p = q;
            }
        }
    }

    @Override
    public E peek() {
        restartFromHead:
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
                E item = p.item;
                if (item != null || (q = p.next) == null) {
                    updateHead(h, p);
                    return item;
                }
                else if (p == q)
                    continue restartFromHead;
                else
                    p = q;
            }
        }
    }

    Node<E> first() {
        restartFromHead:
        for (;;) {
            for (Node<E> h = head, p = h, q;;) {
                boolean hasItem = (p.item != null);
                if (hasItem || (q = p.next) == null) {
                    updateHead(h, p);
                    return hasItem ? p : null;
                }
                else if (p == q)
                    continue restartFromHead;
                else
                    p = q;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return first() == null;
    }

    @Override
    public int size() {
        int count = 0;
        for (Node<E> p = first(); p != null; p = succ(p))
            if (p.item != null)
                // Collection.size() spec says to max out
                if (++count == Integer.MAX_VALUE)
                    break;
        return count;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null && o.equals(item))
                return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            Node<E> next, pred = null;
            for (Node<E> p = first(); p != null; pred = p, p = next) {
                boolean removed = false;
                E item = p.item;
                if (item != null) {
                    if (!o.equals(item)) {
                        next = succ(p);
                        continue;
                    }
                    removed = p.casItem(item, null);
                }

                next = succ(p);
                if (pred != null && next != null) // unlink
                    pred.casNext(p, next);
                if (removed)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == this)
            // As historically specified in AbstractQueue#addAll
            throw new IllegalArgumentException();

        // Copy c into a private chain of Nodes
        Node<E> beginningOfTheEnd = null, last = null;
        for (E e : c) {
            checkNotNull(e);
            Node<E> newNode = new Node<E>(e);
            if (beginningOfTheEnd == null)
                beginningOfTheEnd = last = newNode;
            else {
                last.lazySetNext(newNode);
                last = newNode;
            }
        }
        if (beginningOfTheEnd == null)
            return false;

        // Atomically append the chain at the tail of this collection
        for (Node<E> t = tail, p = t;;) {
            Node<E> q = p.next;
            if (q == null) {
                // p is last node
                if (p.casNext(null, beginningOfTheEnd)) {
                    // Successful CAS is the linearization point
                    // for all elements to be added to this queue.
                    if (!casTail(t, last)) {
                        // Try a little harder to update tail,
                        // since we may be adding many elements.
                        t = tail;
                        if (last.next == null)
                            casTail(t, last);
                    }
                    return true;
                }
                // Lost CAS race to another thread; re-read next
            }
            else if (p == q)
                // We have fallen off list.  If tail is unchanged, it
                // will also be off-list, in which case we need to
                // jump to head, from which all live nodes are always
                // reachable.  Else the new tail is a better bet.
                p = (t != (t = tail)) ? t : head;
            else
                // Check for tail updates after two hops.
                p = (p != t && t != (t = tail)) ? t : q;
        }
    }

    @Override
    public Object[] toArray() {
        // Use ArrayList to deal with resizing.
        ArrayList<E> al = new ArrayList<E>();
        for (Node<E> p = first(); p != null; p = succ(p)) {
            E item = p.item;
            if (item != null)
                al.add(item);
        }
        return al.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // try to use sent-in array
        int k = 0;
        Node<E> p;
        for (p = first(); p != null && k < a.length; p = succ(p)) {
            E item = p.item;
            if (item != null)
                a[k++] = (T)item;
        }
        if (p == null) {
            if (k < a.length)
                a[k] = null;
            return a;
        }

        // If won't fit, use ArrayList version
        ArrayList<E> al = new ArrayList<E>();
        for (Node<E> q = first(); q != null; q = succ(q)) {
            E item = q.item;
            if (item != null)
                al.add(item);
        }
        return al.toArray(a);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        /**
         * Next node to return item for.
         */
        private Node<E> nextNode;

        /**
         * nextItem holds on to item fields because once we claim
         * that an element exists in hasNext(), we must return it in
         * the following next() call even if it was in the process of
         * being removed when hasNext() was called.
         */
        private E nextItem;

        /**
         * Node of the last returned item, to support remove.
         */
        private Node<E> lastRet;

        Itr() {
            advance();
        }

        /**
         * Moves to next valid node and returns item to return for
         * next(), or null if no such.
         */
        private E advance() {
            lastRet = nextNode;
            E x = nextItem;

            Node<E> pred, p;
            if (nextNode == null) {
                p = first();
                pred = null;
            } else {
                pred = nextNode;
                p = succ(nextNode);
            }

            for (;;) {
                if (p == null) {
                    nextNode = null;
                    nextItem = null;
                    return x;
                }
                E item = p.item;
                if (item != null) {
                    nextNode = p;
                    nextItem = item;
                    return x;
                } else {
                    // skip over nulls
                    Node<E> next = succ(p);
                    if (pred != null && next != null)
                        pred.casNext(p, next);
                    p = next;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (nextNode == null) throw new NoSuchElementException();
            return advance();
        }

        @Override
        public void remove() {
            Node<E> l = lastRet;
            if (l == null) throw new IllegalStateException();
            // rely on a future traversal to relink.
            l.item = null;
            lastRet = null;
        }
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {

        // Write out any hidden stuff
        s.defaultWriteObject();

        // Write out all elements in the proper order.
        for (Node<E> p = first(); p != null; p = succ(p)) {
            Object item = p.item;
            if (item != null)
                s.writeObject(item);
        }

        // Use trailing null as sentinel
        s.writeObject(null);
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();

        // Read in elements until trailing null sentinel found
        Node<E> h = null, t = null;
        Object item;
        while ((item = s.readObject()) != null) {
            @SuppressWarnings("unchecked")
            Node<E> newNode = new Node<E>((E) item);
            if (h == null)
                h = t = newNode;
            else {
                t.lazySetNext(newNode);
                t = newNode;
            }
        }
        if (h == null)
            h = t = new Node<E>(null);
        head = h;
        tail = t;
    }

    /** A customized variant of Spliterators.IteratorSpliterator */
    static final class CLQSpliterator<E> implements Spliterator<E> {
        static final int MAX_BATCH = 1 << 25;  // max batch array size;
        final ConcurrentLinkedQueueLocal<E> queue;
        Node<E> current;    // current node; null until initialized
        int batch;          // batch size for splits
        boolean exhausted;  // true when no more nodes
        CLQSpliterator(ConcurrentLinkedQueueLocal<E> queue) {
            this.queue = queue;
        }

        @Override
        public Spliterator<E> trySplit() {
            Node<E> p;
            final ConcurrentLinkedQueueLocal<E> q = this.queue;
            int b = batch;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!exhausted &&
                    ((p = current) != null || (p = q.first()) != null) &&
                    p.next != null) {
                Object[] a = new Object[n];
                int i = 0;
                do {
                    if ((a[i] = p.item) != null)
                        ++i;
                    if (p == (p = p.next))
                        p = q.first();
                } while (p != null && i < n);
                if ((current = p) == null)
                    exhausted = true;
                if (i > 0) {
                    batch = i;
                    return Spliterators.spliterator
                            (a, 0, i, Spliterator.ORDERED | Spliterator.NONNULL |
                                    Spliterator.CONCURRENT);
                }
            }
            return null;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Node<E> p;
            if (action == null) throw new NullPointerException();
            final ConcurrentLinkedQueueLocal<E> q = this.queue;
            if (!exhausted &&
                    ((p = current) != null || (p = q.first()) != null)) {
                exhausted = true;
                do {
                    E e = p.item;
                    if (p == (p = p.next))
                        p = q.first();
                    if (e != null)
                        action.accept(e);
                } while (p != null);
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            Node<E> p;
            if (action == null) throw new NullPointerException();
            final ConcurrentLinkedQueueLocal<E> q = this.queue;
            if (!exhausted &&
                    ((p = current) != null || (p = q.first()) != null)) {
                E e;
                do {
                    e = p.item;
                    if (p == (p = p.next))
                        p = q.first();
                } while (e == null && p != null);
                if ((current = p) == null)
                    exhausted = true;
                if (e != null) {
                    action.accept(e);
                    return true;
                }
            }
            return false;
        }

        @Override
        public long estimateSize() { return Long.MAX_VALUE; }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.NONNULL |
                    Spliterator.CONCURRENT;
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        return new CLQSpliterator<E>(this);
    }

    /**
     * Throws NullPointerException if argument is null.
     *
     * @param v the element
     */
    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    private boolean casTail(Node<E> cmp, Node<E> val) {
        return UNSAFE.compareAndSwapObject(this, tailOffset, cmp, val);
    }

    private boolean casHead(Node<E> cmp, Node<E> val) {
        return UNSAFE.compareAndSwapObject(this, headOffset, cmp, val);
    }

    // Unsafe mechanics

    private static final Unsafe UNSAFE;
    private static final long headOffset;
    private static final long tailOffset;
    static {
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            UNSAFE = (Unsafe)singleoneInstanceField.get(null);
            Class<?> k = ConcurrentLinkedQueueLocal.class;
            headOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("head"));
            tailOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("tail"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
