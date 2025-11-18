package org.uma.ed.datastructures.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.uma.ed.datastructures.searchtree.BST;
import org.uma.ed.datastructures.searchtree.SearchTree;

/**
 * Sets implemented using Binary Search Trees. Order of elements is defined by provided comparator or natural order if
 * none is provided.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BSTSet<T> extends AbstractSortedSet<T> implements SortedSet<T> {
  private final SearchTree<T> binarySearchTree;

  private BSTSet(BST<T> binarySearchTree) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Constructs an empty sorted set with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted set.
   */
  public BSTSet(Comparator<T> comparator) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Constructs an empty sorted set with natural order of elements.
   * <p> Time complexity: O(1)
   */
  public static <T extends Comparable<? super T>> BSTSet<T> empty() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Constructs an empty sorted set with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted set.
   */
  public static <T> BSTSet<T> empty(Comparator<T> comparator) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Creates a new BSTSet with provided comparator and elements.
   * <p> Time complexity: O(n²)
   *
   * @param comparator Comparator defining order of elements in new sorted set.
   * @param elements Elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New BSTSet with provided comparator and elements.
   */
  @SafeVarargs
  public static <T> BSTSet<T> of(Comparator<T> comparator, T... elements) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }



  /**
   * Creates a new BSTSet with natural order and provided elements.
   * <p> Time complexity: O(n²)
   *
   * @param elements Elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return a new BSTSet with natural order and provided elements
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> BSTSet<T> of(T... elements) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Creates a new BSTSet with provided comparator and elements in iterable.
   * <p> Time complexity: O(n²)
   *
   * @param comparator Comparator defining order of elements in new sorted set.
   * @param iterable iterable with elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New BSTSet with provided comparator and elements.
   */
  public static <T> BSTSet<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Creates a new BSTSet with natural order and elements in iterable.
   * <p> Time complexity: O(n²)
   *
   * @param iterable iterable with elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New BSTSet with provided comparator and elements.
   */
  public static <T extends Comparable<? super T>> BSTSet<T> from(Iterable<T> iterable) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Returns a new BSTSet with same elements as argument.
   * <p> Time complexity: O(n)
   *
   * @param that BSTSet to be copied.
   *
   * @return a new BSTSet with same elements as {@code that}.
   */
  public static <T> BSTSet<T> copyOf(BSTSet<T> that) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Returns a new BSTSet with same elements as argument.
   * <p> Time complexity: O(n²)
   *
   * @param that Sorted set to be copied.
   *
   * @return a new BSTSet with same elements as {@code that}.
   */
  public static <T> BSTSet<T> copyOf(SortedSet<T> that) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<T> comparator() {
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
  public void insert(T element) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public boolean contains(T element) {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void delete(T element) {
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
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public T minimum() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public T maximum() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }

  /**
   * Iterator over elements in set. Notice that {@code remove} method is not supported. Note also that set should not be
   * modified during iteration as iterator state may become inconsistent.
   *
   * @see Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("Te toca implementarlo");
  }
}
