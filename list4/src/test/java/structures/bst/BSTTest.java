package structures.bst;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import structures.Tree;

public class BSTTest {

    private static Tree<String> tree;

    @Before
    public void before() {
        tree = new BST<>();
        tree.insert("J");
        tree.insert("H");
        tree.insert("S");
        tree.insert("C");
        tree.insert("I");
        tree.insert("O");
        tree.insert("U");
    }

    @Test
    public void insert() {
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getRight());
        Assert.assertNotNull(tree.getRoot().getRight().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight().getRight());
        Assert.assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        Assert.assertNull(tree.getRoot().getLeft().getLeft().getRight());
        Assert.assertNull(tree.getRoot().getLeft().getRight().getLeft());
        Assert.assertNull(tree.getRoot().getLeft().getRight().getRight());
        Assert.assertNull(tree.getRoot().getRight().getLeft().getLeft());
        Assert.assertNull(tree.getRoot().getRight().getLeft().getRight());
        Assert.assertNull(tree.getRoot().getRight().getRight().getLeft());
        Assert.assertNull(tree.getRoot().getRight().getRight().getRight());
    }

    @Test
    public void delete() {
        tree.delete("U");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNull(tree.getRoot().getRight().getRight());
        tree.delete("J");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNull(tree.getRoot().getRight().getLeft());
        tree.delete("C");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNull(tree.getRoot().getLeft().getLeft());
        tree.delete("H");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNull(tree.getRoot().getLeft().getRight());
        tree.delete("O");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNull(tree.getRoot().getRight());
        tree.delete("I");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNull(tree.getRoot().getLeft());
        tree.delete("S");
        Assert.assertNull(tree.getRoot());
    }

    @Test
    public void search() {
        Assert.assertTrue(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertTrue(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertTrue(tree.search("I"));
        Assert.assertTrue(tree.search("O"));
        Assert.assertTrue(tree.search("U"));
        Assert.assertFalse(tree.search("A"));
    }

    @Test
    public void load() {
    }
}