package structures.rbt;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import structures.Tree;
import structures.splay.Splay;

public class RBTTest {

    private static RBT<String> tree;

    @Before
    public void before() {
        tree = new RBT<>();
    }

    @Test
    public void insert() {
        tree.insert("J");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        tree.insert("H");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getBlack());
        tree.insert("S");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getBlack());
        Assert.assertFalse(tree.getRoot().getRight().getBlack());
        tree.insert("C");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertEquals("C", tree.getRoot().getLeft().getLeft().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        Assert.assertTrue(tree.getRoot().getLeft().getBlack());
        Assert.assertTrue(tree.getRoot().getRight().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getBlack());
        tree.insert("I");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getRight());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertEquals("C", tree.getRoot().getLeft().getLeft().getKey());
        Assert.assertEquals("I", tree.getRoot().getLeft().getRight().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        Assert.assertTrue(tree.getRoot().getLeft().getBlack());
        Assert.assertTrue(tree.getRoot().getRight().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getRight().getBlack());
        tree.insert("O");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getRight().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getRight());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertEquals("O", tree.getRoot().getRight().getLeft().getKey());
        Assert.assertEquals("C", tree.getRoot().getLeft().getLeft().getKey());
        Assert.assertEquals("I", tree.getRoot().getLeft().getRight().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        Assert.assertTrue(tree.getRoot().getLeft().getBlack());
        Assert.assertTrue(tree.getRoot().getRight().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getRight().getBlack());
        Assert.assertFalse(tree.getRoot().getRight().getLeft().getBlack());
        tree.insert("U");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getRight().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getRight());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft().getLeft());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertEquals("O", tree.getRoot().getRight().getLeft().getKey());
        Assert.assertEquals("C", tree.getRoot().getLeft().getLeft().getKey());
        Assert.assertEquals("I", tree.getRoot().getLeft().getRight().getKey());
        Assert.assertEquals("U", tree.getRoot().getLeft().getLeft().getLeft().getKey());
        Assert.assertTrue(tree.getRoot().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getBlack());
        Assert.assertTrue(tree.getRoot().getRight().getBlack());
        Assert.assertTrue(tree.getRoot().getLeft().getLeft().getBlack());
        Assert.assertTrue(tree.getRoot().getLeft().getRight().getBlack());
        Assert.assertFalse(tree.getRoot().getRight().getLeft().getBlack());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getLeft().getBlack());
    }

    @Test
    public void delete() {
        tree.insert("J");
        tree.insert("H");
        tree.insert("S");
        tree.insert("C");
        tree.insert("I");
        tree.insert("O");
        tree.insert("U");
        Assert.assertTrue(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertTrue(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertTrue(tree.search("I"));
        Assert.assertTrue(tree.search("O"));
        Assert.assertTrue(tree.search("U"));
        tree.delete("J");
        Assert.assertFalse(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertTrue(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertTrue(tree.search("I"));
        Assert.assertTrue(tree.search("O"));
        Assert.assertTrue(tree.search("U"));
        tree.delete("S");
        Assert.assertFalse(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertFalse(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertTrue(tree.search("I"));
        Assert.assertTrue(tree.search("O"));
        Assert.assertTrue(tree.search("U"));
        tree.delete("O");
        Assert.assertFalse(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertFalse(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertTrue(tree.search("I"));
        Assert.assertFalse(tree.search("O"));
        Assert.assertTrue(tree.search("U"));
        tree.delete("I");
        Assert.assertFalse(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertFalse(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertFalse(tree.search("I"));
        Assert.assertFalse(tree.search("O"));
        Assert.assertTrue(tree.search("U"));
        tree.delete("U");
        Assert.assertFalse(tree.search("J"));
        Assert.assertTrue(tree.search("H"));
        Assert.assertFalse(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertFalse(tree.search("I"));
        Assert.assertFalse(tree.search("O"));
        Assert.assertFalse(tree.search("U"));
        tree.delete("H");
        Assert.assertFalse(tree.search("J"));
        Assert.assertFalse(tree.search("H"));
        Assert.assertFalse(tree.search("S"));
        Assert.assertTrue(tree.search("C"));
        Assert.assertFalse(tree.search("I"));
        Assert.assertFalse(tree.search("O"));
        Assert.assertFalse(tree.search("U"));
        tree.delete("C");
        Assert.assertFalse(tree.search("J"));
        Assert.assertFalse(tree.search("H"));
        Assert.assertFalse(tree.search("S"));
        Assert.assertFalse(tree.search("C"));
        Assert.assertFalse(tree.search("I"));
        Assert.assertFalse(tree.search("O"));
        Assert.assertFalse(tree.search("U"));
    }

    @Test
    public void search() {
        tree.insert("J");
        tree.insert("H");
        tree.insert("S");
        tree.insert("C");
        tree.insert("I");
        tree.insert("O");
        tree.insert("U");
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
        throw new RuntimeException();
    }
}