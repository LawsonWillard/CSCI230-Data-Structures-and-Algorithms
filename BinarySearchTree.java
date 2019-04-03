package csci230.hwk6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Binary search that does not allow two (or more) binary nodes 
 * in the search tree to have the same element value.
 * 
 * @author CSCI 230: Data Structures and Algorithms Spring 2017
 *
 * @param <AnyType>
 */
public class BinarySearchTree <AnyType extends Comparable> {

	// --------------------------------------
	// instance variable
	private BinaryNode<AnyType> root;

	/**
	 * Constructor with one parameter that
	 * sets the root node of the BST.
	 * 
	 * @param root
	 */
	public BinarySearchTree( AnyType rootElement ) throws NullBinaryNodeException {

		if ( rootElement == null ) {
			throw new NullBinaryNodeException();
		}

		this.root = new BinaryNode<AnyType>( rootElement ) ;

	} // end constructor

	/**
	 * If the BST does not have a root node, then the BST is empty. 
	 * 
	 * @return
	 */
	public boolean isEmpty() {

		return ( root == null );

	} // end isEmpty() method

	/**
	 * Make the BST empty, i.e. a BST that has 
	 * no nodes (including a root node).
	 * 
	 */
	public void makeEmpty() {

		root = null;

	} // end makeEmpty() method

	/**
	 * Method that adds a new node with the specified element 
	 * value in the BST.
	 * 
	 * This method has one parameter:
	 *  1) The element value to be added
	 * 
	 * If the BST has an existing node with the same element 
	 * value, throw an duplicate element exception.
	 * 
	 * @param element
	 * @throws NullBinaryNodeException 
	 * @throws DuplicateElementException 
	 */
	public void add( AnyType element ) throws DuplicateElementException, NullBinaryNodeException {

		if ( element == null ) {

			throw new NullBinaryNodeException();

		} if ( root == null ) {

			this.root = new BinaryNode<AnyType>( element );

		} else {

			add( root, element );

		}

	} // end add() method

	/**
	 * Private recursive method that adds a new node (with the 
	 * specified element value) in the BST.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value to be added
	 * 
	 * If the BST has an existing node with the same element 
	 * value, throw an duplicate element exception.
	 * 
	 * @param node
	 * @param element
	 */
	private void add( BinaryNode<AnyType> node, AnyType element )  throws DuplicateElementException {

		int compare = element.compareTo( node.getElement() );

		if ( compare == 0 ) {

			throw new DuplicateElementException();

		} else if ( compare < 0 ) {

			if ( node.getLeft() == null ) {

				node.setLeft( new BinaryNode<AnyType>( element ) );
				node.getLeft().setParent( node );

			} else {

				add( node.getLeft(), element );

			}

		} else if ( compare > 0 ) {

			if ( node.getRight() == null ) {

				node.setRight( new BinaryNode<AnyType>( element ) );
				node.getRight().setParent( node );

			} else {

				add( node.getRight(), element );

			}

		}

	} // end add() overloaded method

	/**
	 * Method that determines if a node with the specified element value 
	 * exists in the BST. 
	 * 
	 * This method has one parameter:
	 *  1) The element value that is being searched.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @param element
	 * @return
	 * @throws NullBinaryNodeException 
	 * @throws EmptyBSTException 
	 * 
	 */
	public boolean hasElement( AnyType element ) throws EmptyBSTException, NullBinaryNodeException {

		if ( isEmpty() )
			throw new EmptyBSTException();
		else if ( element == null )
			throw new NullBinaryNodeException();

		return hasElement( root, element );

	} // end hasElement() method

	/**
	 * Private recursive method that determines if the element is 
	 * currently stored in the BST.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value that is being searched.
	 *  
	 *  Hint: use the compareTo() method
	 * 
	 * @param element
	 * @param node
	 * @return
	 */
	private boolean hasElement( BinaryNode<AnyType> node, AnyType element ) {

		if ( node == null )
			return false;

		int compare = element.compareTo( node.getElement() );

		if ( compare == 0 ) {

			return true;

		} else if ( compare < 0 ) {

			return hasElement( node.getLeft(), element );

		} else {

			return hasElement( node.getRight(), element );

		}

	} // end hasElement() overloaded method

	/**
	 * Find the node with the minimum element value in the BST.
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @return
	 * @throws EmptyBSTException
	 */
	public AnyType findMin() throws EmptyBSTException {

		if ( isEmpty() )
			throw new EmptyBSTException();

		return findMin( root ).getElement();

	} // end findMin() method

	/**
	 * Private recursive method that walks the BST searching
	 * for the node with the minimum element value.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 * 
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> findMin( BinaryNode<AnyType> node ) {

		if ( node.getLeft() == null ) {

			return node;

		} else {

			return findMin( node.getLeft() );
		}

	} // end findMin() method

	/**
	 * Find the node with the maximum element value in the BST.
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * @return
	 * @throws EmptyBSTException
	 */
	public AnyType findMax() throws EmptyBSTException {

		if ( isEmpty() )
			throw new EmptyBSTException();

		return findMax( root ).getElement();

	} // end findMax() method

	/**
	 * Private recursive method that walks the BST searching
	 * for the node with the maximum element value.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 * 
	 * @param node
	 * @return
	 */
	private BinaryNode<AnyType> findMax( BinaryNode<AnyType> node ) {

		if ( node.getRight() == null ) {

			return node;

		} else {

			return findMax( node.getRight() );
		}

	} // end findMax() method

	/**
	 * Delete the node with the specified element value in the BST.
	 * 
	 * This method has one parameter:
	 * 	1) the element value to be deleted
	 * 
	 * This method performs the following delete operations
	 * 	1) delete node with no children (case 1)
	 * 	2) delete node with one child (case 2)
	 * 	3) delete node with two children (case 3)
	 * 
	 * If the BST is empty, throw an empty binary search tree
	 * exception.
	 * 
	 * If the element is null, throw a null binary node 
	 * exception
	 *  
	 * @param element
	 * @throws EmptyBSTException
	 * @throws NullBinaryNodeException
	 */
	public void delete( AnyType element ) throws EmptyBSTException, NullBinaryNodeException {
            if(isEmpty() == true){
                throw new EmptyBSTException();
            }
            else if(element == null){
                throw new NullBinaryNodeException();
            }
            delete(root, element);
            } // end delete() method

	/**
	 * Private recursive method that walk the BST looking for 
	 * the specified element value to be removed.
	 * 
	 * This method has two parameters:
	 *  1) The node visited while recursively walking the BST
	 *  2) The element value that is being removed.
	 *  
	 * @param node
	 * @param element
	 */
	private void delete( BinaryNode<AnyType> node, AnyType element ) {

		if(node == null){
                    return;
                }
                //Searches tree to find the node with element
                AnyType val1 = node.getElement(); //Goes to the left if it is less
                if(element.compareTo(val1) == -1){
                    delete(node.getLeft(), element);
                }
                else if(element.compareTo(val1 )== 1){//Goes to the right if it is greater
                    delete(node.getRight(), element);
                }
                else{ //If node with element is found 
                   if(node.getLeft() != null && node.getRight() != null){ //If Node has two children
                       BinaryNode<AnyType> MinRight = findMin(node.getRight()); //Finds the smallest value on the rightmost subtree
                       AnyType temp = MinRight.getElement();
                       node.setElement(MinRight.getElement());
                       delete(node.getRight(), MinRight.getElement());
                   }
                   else if(node.getRight() != null){
                       BinaryNode<AnyType> Parent = node.getParent();
                       Parent.setRight(node.getRight());
                       node = null;
                   }
                   else if(node.getLeft() != null){
                       BinaryNode<AnyType> Parent = node.getParent();
                       Parent.setLeft(node.getLeft());
                       node = null;
                   }
                   else{
                       BinaryNode<AnyType> Parent = node.getParent();
                       if(val1.compareTo(Parent.getElement()) == 1){
                           Parent.setRight(null);
                       }
                        else if(val1.compareTo(Parent.getElement()) == -1){
                           Parent.setLeft(null);
                       }
                        else if(Parent.getElement().compareTo(val1) == 0){
                           Parent.setRight(null);
                           Parent.setLeft(null);
                       }
                   }
                }

		





	} // end delete() method

	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an post-order 
	 * tree traversal format.
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	1,3,2
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * This method has no parameters.
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return
	 * 
	 */
	public String postOrder() throws EmptyBSTException {
                if(root == null){
                    throw new EmptyBSTException();
                }
                String S1 = postOrder(root);
                return S1.substring(0, S1.length()-1);
		

		

	} // end postOrder() method

	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an post-order 
	 * tree traversal format. 
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String postOrder( BinaryNode<AnyType> node ) {

		String nodes = "";
                if(node != null){
                    nodes = nodes + postOrder(node.getLeft());
                    nodes = nodes + postOrder(node.getRight());
                    nodes = nodes + node.getElement() + ",";
                }
		return nodes;




	} // end postOrder() method

	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an pre-order 
	 * tree traversal format.
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	2,1,3
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return 
	 * 
	 */
	public String preOrder() throws EmptyBSTException {
                if(root==null){
                    throw new EmptyBSTException();
                }
                String S1 = preOrder(root);
                return S1.substring(0, S1.length()-1);
	} // end preOrder() method

	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an pre-order 
	 * tree traversal format. 
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String preOrder( BinaryNode<AnyType> node ) {
                String nodes = "";
                if(node != null){
                    nodes = nodes + node.getElement() + ",";
                    nodes = nodes + preOrder(node.getLeft());
                    nodes = nodes + preOrder(node.getRight());
                }
                return nodes;
        }//End preOrder Method

	/**
	 * Recursive method that traverses the BST 
	 * dynamically creating a string with the
	 * element values stored in an in-order 
	 * tree traversal format. 
	 * 
	 * The return string MUST be formated as follows:
	 * <element>,<element>,<element>,<element>, .... <element>
	 * where <element> is the element value
	 * For example,
	 * 	1,2,3
	 * Hint: you may want to use regular expressions
	 * 
	 * Discussed in class, please review 
	 * your notes
	 * 
	 * If the BST is empty, throw an empty binary search 
	 * tree exception
	 * 
	 * @throws EmptyBSTException 
	 * @return
	 * 
	 */
	public String inOrder() throws EmptyBSTException {
            if(root == null){
                throw new EmptyBSTException();
            }
            String S1 = inOrder(root);
            return S1.substring(0, S1.length()-1);

	} // end inOrder() method


	/**
	 * Private recursive method that traverses the BST 
	 * dynamically creating a string with the element 
	 * values stored in an in-order tree traversal format.
	 * 
	 * This method has one parameter:
	 *  1) The node visited while recursively walking the BST
	 *  
	 * @param node
	 * @return
	 * 
	 */
	private String inOrder( BinaryNode<AnyType> node ) {
                
		String nodes = "";
                if(node != null){
                    nodes = nodes + inOrder(node.getLeft());
                    nodes = nodes + node.getElement() + ",";
                    nodes = nodes + inOrder(node.getRight());
                }
                return nodes;
	} // end inOrder() method

	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {
                try{
                    BinarySearchTree BST1  = new BinarySearchTree<Integer>(40);
                    //Builds tree
                    System.out.println("Building Tree");
                    BST1.add(25);
                    BST1.add(78);
                    BST1.add(10);
                    BST1.add(3);
                    BST1.add(17);
                    BST1.add(32);
                    BST1.add(30);
                    BST1.add(38);
                    BST1.add(50);
                    BST1.add(93);
                    BST1.add(100);
                    BST1.add(1);
                    BST1.add(0);
                    BST1.add(39);
                    System.out.println("Done");
                    
                    //Case 1 No leaves:
                    System.out.println("\nCase 1, No Leaves");
                    int n = 100;
                    int k = 25;
                    int j = 1;
                    int i = 39;
                    BST1.delete(n);
                    System.out.println("Deleting element: " + n);
                    System.out.println("Does element " + n + " exist? -> " + BST1.hasElement(n));
                    System.out.println("\nCase 2, 1 Child on left side");
                    BST1.delete(j);
                    System.out.println("Deleting element: " + j);
                    System.out.println("Does element " + j + " exist? -> " + BST1.hasElement(j));
                    System.out.println("\nCase 3, 1 Child on right side");
                    BST1.delete(i);
                    System.out.println("Deleting element: " + i);
                    System.out.println("Does element " + i + " exist? -> " + BST1.hasElement(i));
                    System.out.println("\nCase 4, 2 children");
                    BST1.delete(k);
                    System.out.println("Deleting element: " + k);
                    System.out.println("Does element " + k + " exist? -> " + BST1.hasElement(k));
                    
                    
                    
                    System.out.println("\nPreOrder Tansversal: " + BST1.preOrder());
                    System.out.println("PostOrder Tansversal: " + BST1.postOrder());
                    System.out.println("InOrder Tansversal: " + BST1.inOrder());
                    
                } catch (NullBinaryNodeException ex) {
                    System.out.println("Null Binary Node Exception");
                } catch (DuplicateElementException ex) {
                    System.out.println("Duplicate Element Exception");
                } catch (EmptyBSTException ex) {
                    System.out.println("Empty BST Exception");
            }
                
                try{
                    BinarySearchTree BST1  = new BinarySearchTree<Integer>(40);
                    //Builds tree again for Empty Binary Tree
                    System.out.println("\nBuilding Tree for Empty Binary Tree Test");
                    BST1.makeEmpty();
                    BST1.delete(12);
                } catch (NullBinaryNodeException ex) {
                    System.out.println("Null Binary Node Exception");
                } catch (EmptyBSTException ex) {
                    System.out.println("Empty BST Exception");
            }
		try{
                    BinarySearchTree BST1  = new BinarySearchTree<Integer>(40);
                    //Builds tree again for Empty Binary Tree
                    System.out.println("\nBuilding Tree for Null Binary Node Exception Test");
                    BST1.delete(null);
                } catch (NullBinaryNodeException ex) {
                    System.out.println("Null Binary Node Exception");
                } catch (EmptyBSTException ex) {
                    System.out.println("Empty BST Exception");
            }
	} // end main method


} // end BinarySearchTree class
