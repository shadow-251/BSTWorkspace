package org.uma.ed.datastructures.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.uma.ed.datastructures.searchtree.BST;


public class BSTSetTest {

    // Pequeño tipo sin Comparable para probar comparador custom
    private static final class Point {
        final int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        @Override public String toString() { return "(" + x + "," + y + ")"; }
        // equals/hashCode solo para aserciones de contains()
        @Override public boolean equals(Object o) {
            if (!(o instanceof Point p)) return false;
            return x == p.x && y == p.y;
        }
        @Override public int hashCode() { return x * 31 + y; }
    }

    // Comparador por (x,y)
    private static final Comparator<Point> BY_XY =
            Comparator.<Point>comparingInt(p -> p.x).thenComparingInt(p -> p.y);

    @Test
    @DisplayName("empty() usa orden natural y comienza vacío")
    void testEmptyNaturalOrder() {
        BSTSet<Integer> s = BSTSet.empty();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
        assertEquals(Comparator.naturalOrder(), s.comparator());
    }

    @Test
    @DisplayName("empty(comparator) usa el comparador dado y comienza vacío")
    void testEmptyWithComparator() {
        Comparator<Integer> rev = Comparator.reverseOrder();
        BSTSet<Integer> s = BSTSet.empty(rev);
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
        assertEquals(rev, s.comparator());
    }

    @Test
    @DisplayName("insert/contains/size con enteros")
    void testInsertContainsSize() {
        BSTSet<Integer> s = BSTSet.empty();
        s.insert(5);
        s.insert(1);
        s.insert(9);
        assertEquals(3, s.size());
        assertTrue(s.contains(5));
        assertTrue(s.contains(1));
        assertTrue(s.contains(9));
        assertFalse(s.contains(7));
    }

    @Test
    @DisplayName("Insertar duplicados no aumenta el tamaño")
    void testDuplicateInsertions() {
        BSTSet<Integer> s = BSTSet.empty();
        s.insert(2);
        s.insert(2);
        s.insert(2);
        assertEquals(1, s.size());
        assertTrue(s.contains(2));
    }

    @Test
    @DisplayName("minimum/maximum en conjunto no vacío")
    void testMinimumMaximum() {
        BSTSet<Integer> s = BSTSet.of(5, 1, 9, 3, 7);
        assertEquals(1, s.minimum());
        assertEquals(9, s.maximum());
    }

    @Test
    @DisplayName("minimum/maximum lanzan NoSuchElementException en vacío")
    void testMinMaxOnEmptyThrows() {
        BSTSet<Integer> s = BSTSet.empty();
        assertThrows(NoSuchElementException.class, s::minimum);
        assertThrows(NoSuchElementException.class, s::maximum);
    }

    @Test
    @DisplayName("delete elimina elementos y actualiza tamaño/contains")
    void testDelete() {
        BSTSet<Integer> s = BSTSet.of(4, 2, 6, 1, 3, 5, 7);
        assertEquals(7, s.size());
        s.delete(4);
        assertEquals(6, s.size());
        assertFalse(s.contains(4));
        // Borrar inexistente no debe romper (ni lanzar)
        s.delete(100);
        assertEquals(6, s.size());
    }

    @Test
    @DisplayName("clear vacía el conjunto")
    void testClear() {
        BSTSet<Integer> s = BSTSet.of(10, 20, 30);
        assertFalse(s.isEmpty());
        s.clear();
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    @Test
    @DisplayName("Iterador recorre en orden (in-order) para orden natural")
    void testIteratorOrderNatural() {
        BSTSet<Integer> s = BSTSet.of(5, 1, 9, 3, 7, 2, 8);
        List<Integer> iter = new ArrayList<>();
        for (int v : s) iter.add(v);
        assertEquals(List.of(1, 2, 3, 5, 7, 8, 9), iter);
    }

    @Test
    @DisplayName("of(varargs) con orden natural")
    void testOfVarargsNatural() {
        BSTSet<String> s = BSTSet.of("d", "a", "c", "b");
        assertEquals(4, s.size());
        assertTrue(s.contains("a"));
        assertEquals("a", s.minimum());
        assertEquals("d", s.maximum());
    }

    @Test
    @DisplayName("of(comparator, varargs) con orden inverso")
    void testOfVarargsCustomComparator() {
        Comparator<Integer> rev = Comparator.reverseOrder();
        BSTSet<Integer> s = BSTSet.of(rev, 1, 4, 2, 3);
        assertEquals(rev, s.comparator());
        // El conjunto sigue siendo conjunto; min/max dependen de comparator del árbol
        // minimum() y maximum() delegan en el SearchTree: con comparador inverso,
        // el 'mínimo' según ese orden es el mayor en orden natural.
        assertEquals(4, s.minimum());
        assertEquals(1, s.maximum());
    }

    @Test
    @DisplayName("from(iterable) con orden natural")
    void testFromIterableNatural() {
        List<Integer> data = List.of(3, 1, 2, 3, 2, 1);
        BSTSet<Integer> s = BSTSet.from(data);
        assertEquals(3, s.size());
        List<Integer> iter = new ArrayList<>();
        for (int v : s) iter.add(v);
        assertEquals(List.of(1, 2, 3), iter);
    }

    @Test
    @DisplayName("from(comparator, iterable) con tipo no comparable")
    void testFromIterableCustomComparatorWithNonComparable() {
        List<Point> pts = List.of(new Point(2, 2), new Point(1, 5), new Point(1, 3), new Point(2, 2));
        BSTSet<Point> s = BSTSet.from(BY_XY, pts);
        assertEquals(3, s.size());
        assertTrue(s.contains(new Point(1, 3)));
        assertTrue(s.contains(new Point(1, 5)));
        assertTrue(s.contains(new Point(2, 2)));

        // Orden según comparador
        List<Point> seen = new ArrayList<>();
        for (Point p : s) seen.add(p);
        assertEquals(List.of(new Point(1, 3), new Point(1, 5), new Point(2, 2)), seen);
    }

    @Test
    @DisplayName("copyOf(BSTSet) crea copia independiente con el mismo comparador")
    void testCopyOfBSTSet() {
        Comparator<Integer> rev = Comparator.reverseOrder();
        BSTSet<Integer> original = BSTSet.of(rev, 2, 1, 3);
        BSTSet<Integer> copy = BSTSet.copyOf(original);

        assertNotSame(original, copy);
        assertEquals(original.comparator(), copy.comparator());
        assertEquals(original.size(), copy.size());
        for (int v : original) {
            assertTrue(copy.contains(v));
        }

        // Mutaciones no afectan al otro
        original.insert(10);
        copy.delete(1);
        assertTrue(original.contains(10));
        assertFalse(copy.contains(1));
    }

    @Test
    @DisplayName("El comparador se propaga correctamente")
    void testComparatorPropagation() {
        Comparator<String> byLenThenLex = Comparator.<String>comparingInt(String::length).thenComparing(Comparator.naturalOrder());
        BSTSet<String> s = BSTSet.empty(byLenThenLex);
        s.insert("bbb");
        s.insert("a");
        s.insert("cc");
        s.insert("aa"); // mismo tamaño, orden lex

        assertEquals(byLenThenLex, s.comparator());

        List<String> iter = new ArrayList<>();
        for (String v : s) iter.add(v);
        assertEquals(List.of("a", "aa", "cc", "bbb"), iter);

        assertEquals("a", s.minimum());
        assertEquals("bbb", s.maximum());
    }

    @Test
    @DisplayName("Iterator no permite modificar la colección durante la iteración (si se intentara, el contrato lo desaconseja)")
    void testIteratorIsStableIfNotMutated() {
        BSTSet<Integer> s = BSTSet.of(3, 1, 2);
        Iterator<Integer> it = s.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
        // Nota: no invocamos it.remove() porque la implementación documenta que no se soporta.
    }


    /**
     * Implementación mínima de SortedSet<T> para forzar el camino genérico de copyOf(SortedSet).
     * Envuelve un TreeSet con el comparador indicado.
     */
    private static final class FakeSortedSet<T> implements SortedSet<T> {
        private final Comparator<T> cmp;
        private final TreeSet<T> ts;

        FakeSortedSet(Comparator<T> cmp) {
            this.cmp = cmp;
            this.ts = new TreeSet<>(cmp);
        }

        @Override public Comparator<T> comparator() { return cmp; }
        @Override public boolean isEmpty() { return ts.isEmpty(); }
        @Override public int size() { return ts.size(); }
        @Override public void insert(T element) { ts.add(element); }
        @Override public boolean contains(T element) { return ts.contains(element); }
        @Override public void delete(T element) { ts.remove(element); }
        @Override public void clear() { ts.clear(); }
        @Override public T minimum() {
            if (ts.isEmpty()) throw new NoSuchElementException("minimum on empty set");
            return ts.first();
        }
        @Override public T maximum() {
            if (ts.isEmpty()) throw new NoSuchElementException("maximum on empty set");
            return ts.last();
        }
        @Override public Iterator<T> iterator() { return ts.iterator(); }
    }

    @Test
    @DisplayName("Inserciones degeneradas en orden ascendente mantienen orden e invariantes básicos")
    void testDegenerateAscendingInserts() {
        BSTSet<Integer> s = BSTSet.empty();
        for (int i = 1; i <= 100; i++) s.insert(i);
        assertEquals(100, s.size());
        // Iteración debe ir de 1..100
        int expected = 1;
        for (int v : s) assertEquals(expected++, v);
        assertEquals(1, s.minimum());
        assertEquals(100, s.maximum());
    }

    @Test
    @DisplayName("Inserciones degeneradas en orden descendente mantienen orden e invariantes básicos")
    void testDegenerateDescendingInserts() {
        BSTSet<Integer> s = BSTSet.empty();
        for (int i = 100; i >= 1; i--) s.insert(i);
        assertEquals(100, s.size());
        int expected = 1;
        for (int v : s) assertEquals(expected++, v);
        assertEquals(1, s.minimum());
        assertEquals(100, s.maximum());
    }

    @Test
    @DisplayName("Borrado de hoja, nodo con un hijo y nodo con dos hijos")
    void testDeleteLeafOneChildTwoChildren() {
        // Árbol con estructura:      5
        //                          /   \
        //                         3     8
        //                        / \   / \
        //                       2  4  7  10
        //                             \
        //                              9
        BSTSet<Integer> s = BSTSet.of(5,3,8,2,4,7,10,9);

        // Caso 1: hoja (4)
        s.delete(4);
        assertFalse(s.contains(4));
        assertEquals(7, s.size());

        // Caso 2: un hijo (7 tiene hijo derecho 9)
        s.delete(7);
        assertFalse(s.contains(7));
        assertTrue(s.contains(9));
        assertEquals(6, s.size());

        // Caso 3: dos hijos (8 tiene hijos 9 y 10)
        s.delete(8);
        assertFalse(s.contains(8));
        assertTrue(s.contains(9));
        assertTrue(s.contains(10));
        assertEquals(5, s.size());

        // Comprobación de orden
        List<Integer> order = new ArrayList<>();
        for (int v : s) order.add(v);
        assertEquals(List.of(2,3,5,9,10), order);
    }

    @Test
    @DisplayName("from(iterable) con muchos duplicados colapsa a conjunto")
    void testFromIterableManyDuplicates() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) data.add(42);
        for (int i = 0; i < 50; i++) data.add(7);
        for (int i = 0; i < 25; i++) data.add(100);
        BSTSet<Integer> s = BSTSet.from(data);
        assertEquals(3, s.size());
        List<Integer> order = new ArrayList<>();
        for (int v : s) order.add(v);
        assertEquals(List.of(7, 42, 100), order);
    }

    private static <T> List<T> toList(Iterable<T> it) {
        List<T> out = new ArrayList<>();
        for (T v : it) out.add(v);
        return out;
    }

    /**
     * Construye un árbol con forma conocida insertando en este orden:
     *
     *            5
     *          /   \
     *         3     8
     *        / \   / \
     *       1  4  7  10
     *                /
     *               9
     */
    private static BST<Integer> buildSampleBST() {
        BST<Integer> bst = BST.empty((Comparator<Integer>) Comparator.naturalOrder());
        for (int v : new int[]{5,3,8,1,4,7,10,9}) {
            bst.insert(v);
        }
        return bst;
    }

    @Test
    @DisplayName("Inorder devuelve elementos ordenados ascendentemente (orden natural)")
    void testInorder() {
        BST<Integer> bst = buildSampleBST();
        List<Integer> inorder = toList(bst.inOrder());
        assertEquals(List.of(1,3,4,5,7,8,9,10).stream().sorted().toList(), inorder,
                "El inorden debe ser la secuencia ordenada por el comparador");
        // Nota: la secuencia ordenada correcta es [1,3,4,5,7,8,9,10].
        assertEquals(List.of(1,3,4,5,7,8,9,10), inorder);
    }

    @Test
    @DisplayName("Preorder recorre raíz-izquierda-derecha")
    void testPreorder() {
        BST<Integer> bst = buildSampleBST();
        List<Integer> preorder = toList(bst.preOrder());
        // Con el árbol construido arriba, el preorden esperado:
        assertEquals(List.of(5,3,1,4,8,7,10,9), preorder);
    }

    @Test
    @DisplayName("Postorder recorre izquierda-derecha-raíz")
    void testPostorder() {
        BST<Integer> bst = buildSampleBST();
        List<Integer> postorder = toList(bst.postOrder());
        // Con el árbol construido arriba, el postorden esperado:
        assertEquals(List.of(1,4,3,7,9,10,8,5), postorder);
    }

    @Test
    @DisplayName("Inorder respeta el comparador: orden inverso")
    void testInorderWithReverseComparator() {
        BST<Integer> bst = BST.empty(Comparator.<Integer>reverseOrder());
        // Insertamos los mismos valores
        for (int v : new int[]{5,3,8,1,4,7,10,9}) bst.insert(v);
        List<Integer> inorder = toList(bst.inOrder());
        // Con comparador inverso, el 'inorder' debe producir descendente
        assertEquals(List.of(10,9,8,7,5,4,3,1), inorder);
    }

    @Test
    @DisplayName("Recorridos con duplicados: no deben duplicar (conjunto)")
    void testTraversalsWithDuplicates() {
        BST<Integer> bst = BST.empty(Comparator.<Integer>naturalOrder());
        for (int v : new int[]{5,5,5,3,3,8,8,1,4,7,10,9,9}) bst.insert(v);
        // Esperamos los mismos elementos únicos que en el árbol base
        assertEquals(List.of(1,3,4,5,7,8,9,10), toList(bst.inOrder()));
        assertEquals(List.of(5,3,1,4,8,7,10,9), toList(bst.preOrder()));
        assertEquals(List.of(1,4,3,7,9,10,8,5), toList(bst.postOrder()));
    }

    @Test
    @DisplayName("Recorridos en árbol pequeño y casos borde")
    void testTinyAndEdgeCases() {
        // Árbol vacío
        BST<Integer> empty = BST.empty(Comparator.<Integer>naturalOrder());
        assertTrue(toList(empty.inOrder()).isEmpty());
        assertTrue(toList(empty.preOrder()).isEmpty());
        assertTrue(toList(empty.postOrder()).isEmpty());

        // Un solo nodo
        BST<Integer> one = BST.empty(Comparator.<Integer>naturalOrder());
        one.insert(42);
        assertEquals(List.of(42), toList(one.inOrder()));
        assertEquals(List.of(42), toList(one.preOrder()));
        assertEquals(List.of(42), toList(one.postOrder()));

        // Cadena degenerada: inserciones ascendentes
        BST<Integer> chain = BST.empty(Comparator.<Integer>naturalOrder());
        for (int i = 1; i <= 5; i++) chain.insert(i);
        assertEquals(List.of(1,2,3,4,5), toList(chain.inOrder()));
        assertEquals(List.of(1,2,3,4,5), toList(chain.preOrder()));   // raíz va creciendo a la derecha
        assertEquals(List.of(5,4,3,2,1), toList(chain.postOrder()));
    }

}
