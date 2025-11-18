package org.uma.ed.datastructures.searchtree;


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Search tree implemented using an unbalanced binary search tree. Nodes are sorted according to their keys and keys are
 * sorted using the provided comparator or their natural order if no comparator is provided.
 *
 * @param <K> Type of keys.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BST<K> implements SearchTree<K> {
  private static final class Node<K> {
    K key;
    Node<K> left, right;

    Node(K key) {
      this.key = key;
      this.left = null;
      this.right = null;
    }
    Node(K key, Node<K> left, Node<K> right) {
      this.key = key;
      this.left = left;
      this.right = right;
    }
  }

  /*
   INVARIANT:
   - Keys in left child are smaller than key in node.
   - Keys in right child are greater than key in node.
   - There are no duplicate keys in tree.
   - size is number of nodes in tree.
  */

  private final Comparator<K> comparator;
  private Node<K> root;
  private int size;

  /**
   * Creates an empty unbalanced binary search tree. Keys are sorted according to provided comparator.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of keys in this search tree.
   */
  public BST(Comparator<K> comparator) {
    this(comparator, null, 0);
  }

  private BST(Comparator<K> comparator, Node<K> root, int size) {
    this.comparator = comparator;
    this.root = root;
    this.size = size;
  }

  /**
   * Creates an empty unbalanced binary search tree. Keys are sorted according to their natural order.
   * <p> Time complexity: O(1)
   */
  public static <K extends Comparable<? super K>> BST<K> empty() {
    return new BST(Comparator.naturalOrder());
  }

  /**
   * Creates an empty unbalanced binary search tree. Keys are sorted according to provided comparator.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of keys in this search tree.
   */
  public static <K> BST<K> empty(Comparator<K> comparator) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Returns a new binary search tree with same elements and same structure as argument.
   * <p> Time complexity: O(n²)
   *
   * @param that binary search tree to be copied.
   *
   * @return a new BST with same elements and structure as {@code that}.
   */
  public static <K> BST<K> copyOf(SearchTree<K> that) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Returns a new unbalanced binary search tree with same elements and same structure as argument.
   * <p> Time complexity: O(n)
   *
   * @param that binary search tree to be copied.
   *
   * @return a new BST with same elements and structure as {@code that}.
   */
  public static <K> BST<K> copyOf(BST<K> that) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  private static <K> Node<K> copyOf(Node<K> node) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<K> comparator() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public int height() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  private static int height(Node<?> node) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void insert(K key) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  // returns modified tree.
  private Node<K> insert(Node<K> node, K key) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public K search(K key) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  private K search(Node<K> node, K key) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public boolean contains(K key) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }


  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public K minimum() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public K maximum() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void delete(K key) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }




  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void deleteMinimum() {
    throw new EmptySearchTreeException("deleteMinimum on empty tree");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void deleteMaximum() {
    throw new EmptySearchTreeException("deleteMinimum on empty tree");
  }


  @Override
  public Iterable<K> preOrder() {
    throw new EmptySearchTreeException("deleteMinimum on empty tree");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<K> inOrder() {
    throw new EmptySearchTreeException("deleteMinimum on empty tree");
  }



  @Override
  public Iterable<K> postOrder() {
    throw new EmptySearchTreeException("deleteMinimum on empty tree");
  }

  /**
   * Returns representation of this search tree as a String.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringBuilder sb = new StringBuilder(className).append("(");
    toString(sb, root);
    sb.append(")");

    return sb.toString();
  }

  private static void toString(StringBuilder sb, Node<?> node) {
    if (node == null) {
      sb.append("null");
    } else {
      String className = node.getClass().getSimpleName();
      sb.append(className).append("(");
      toString(sb, node.left);
      sb.append(", ");
      sb.append(node.key);
      sb.append(", ");
      toString(sb, node.right);
      sb.append(")");
    }
  }
}
