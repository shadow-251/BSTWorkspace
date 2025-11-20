package org.uma.ed.datastructures.searchtree;


import java.security.Key;
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
    return new BST<>(comparator);
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
    root=null;
    size=0;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<K> comparator() {
    return comparator;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return size==0;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public int height() {
    return height(root);
  }

  private static int height(Node<?> node) {
    if(node==null){
        return 0;
    }else{
        return Math.max(height(node.left),height(node.right))+1;
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void insert(K key) {
      root=insert(root,key);
  }

  // returns modified tree.
  private Node<K> insert(Node<K> node, K key) {
    if(node==null){
        size++;
        return new Node<>(key);
    }else if(comparator.compare(node.key, key)==0){
        return new Node<>(key,node.left,node.right);
    }else if(comparator.compare(node.key, key)>0){
        node.left=insert(node.left, key);
        return node;
    }else{
        node.right=insert(node.right, key);
        return node;
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public K search(K key) {
      return search(root, key);
  }

  private K search(Node<K> node, K key) {
      if(node==null){
        return null;
      }else if(comparator.compare(node.key, key)==0){
        return node.key;
      }else if(comparator.compare(node.key, key)>0){
        return search(node.left, key);
      }else{
        return search(node.right, key);
      }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public boolean contains(K key) {
    boolean contains=true;
    if(search(key)==null){
        contains=false;
    }
    return contains;
  }


  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public K minimum() {
      if(isEmpty()){
        throw new NoSuchElementException();
      }
      Node<K> aux=root.left;
      while(aux.left!=null){
          aux=aux.left;
      }
      return aux.key;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public K maximum() {
      if(isEmpty()){
          throw new NoSuchElementException();
      }
      Node<K> aux=root.right;
      while(aux.right!=null){
          aux=aux.right;
      }
      return aux.key;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void delete(K key) {
    if(contains(key)){
        root=delete(root,key);
    }
  }

    private Node<K> delete(Node<K> node, K key) {
        if(node==null){
            return null;
        }else if(comparator.compare(node.key, key)==0){
            if(node.left==null&&node.right==null){
                size--;
                return null;
            }
            if(node.left==null){
                size--;
                return node.right;
            }else if(node.right==null){
                size--;
                return node.left;
            }else{
                Node<K> min=findMin(node.right);
                node.key=min.key;
                node.right=delete(node.right,min.key);
                return node;
            }
        }else if(comparator.compare(node.key, key)>0){
            node.left=delete(node.left,key);
            return node;
        }else{
            node.right=delete(node.right,key);
            return node;
        }
    }

    private Node<K> findMin(Node<K> node){
      while(node.left!=null){
          node=node.left;
      }
      return node;
    }



  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void deleteMinimum() {
    delete(root,minimum());
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void deleteMaximum() {
    delete(root,maximum());
  }


  @Override
  public Iterable<K> preOrder(){
      if(root==null){
          throw new EmptySearchTreeException("preOrder on empty tree");
      }
      List<K> list=new ArrayList<>();
      preOrder(root,list);
      return list;
  }

    private void preOrder(Node<K> node,List<K> list){
        if(node!=null){
            list.add(node.key);
            preOrder(node.left,list);
            preOrder(node.right,list);
        }
    }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<K> inOrder(){
      if(root==null){
          throw new EmptySearchTreeException("inOrder on empty tree");
      }
      List<K> list=new ArrayList<>();
      inOrder(root,list);
      return list;
  }

    private void inOrder(Node<K> node,List<K> list){
        if(node!=null){
            inOrder(node.left,list);
            list.add(node.key);
            inOrder(node.right,list);
        }
    }



  @Override
  public Iterable<K> postOrder(){
      if(root==null){
          throw new EmptySearchTreeException("postOrder on empty tree");
      }
      List<K> list=new ArrayList<>();
      postOrder(root,list);
      return list;
  }

    private void postOrder(Node<K> node,List<K> list){
        if(node!=null){
            postOrder(node.left,list);
            postOrder(node.right,list);
            list.add(node.key);
        }
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
