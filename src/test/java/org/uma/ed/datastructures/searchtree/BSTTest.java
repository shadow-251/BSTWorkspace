package org.uma.ed.datastructures.searchtree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class BSTTest {

    private BST<Integer> tree;

    @BeforeEach
    void setUp() {
        // Inicializamos el árbol antes de cada prueba.
        tree = BST.empty();
    }

    @Test
    void testInsert() {
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        assertEquals(3, tree.size());
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
    }

    @Test
    void testInsertDuplicate() {
        tree.insert(5);
        tree.insert(5); // Intentamos insertar un duplicado.

        assertEquals(1, tree.size()); // No se deben permitir duplicados
        assertTrue(tree.contains(5));
    }

    @Test
    void testSearch() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        assertEquals(20, tree.search(20));
        assertEquals(5, tree.search(5));
        assertNull(tree.search(30)); // Clave no presente
    }

    @Test
    void testDelete() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(5); // Eliminar nodo con un solo hijo

        assertEquals(2, tree.size());
        assertFalse(tree.contains(5));
    }

    @Test
    void testDeleteRoot() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(10); // Eliminar la raíz

        assertEquals(2, tree.size());
        assertFalse(tree.contains(10));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(15));
    }

    @Test
    void testMinimum() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertEquals(5, tree.minimum());
    }

    @Test
    void testMaximum() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        assertEquals(15, tree.maximum());
    }

    @Test
    void testInOrder() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        Iterator<Integer> inOrderIterator = tree.inOrder().iterator();
        assertEquals(5, inOrderIterator.next());
        assertEquals(10, inOrderIterator.next());
        assertEquals(15, inOrderIterator.next());
    }

    @Test
    void testPreOrder() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        Iterator<Integer> preOrderIterator = tree.preOrder().iterator();
        assertEquals(10, preOrderIterator.next());
        assertEquals(5, preOrderIterator.next());
        assertEquals(15, preOrderIterator.next());
    }

    @Test
    void testPostOrder() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        Iterator<Integer> postOrderIterator = tree.postOrder().iterator();
        assertEquals(5, postOrderIterator.next());
        assertEquals(15, postOrderIterator.next());
        assertEquals(10, postOrderIterator.next());
    }

    @Test
    void testClear() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.clear();

        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }
}
