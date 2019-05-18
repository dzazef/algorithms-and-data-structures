package structures.bst;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import structures.Tree;
import structures.splay.Splay;

public class SplayTest {

    private static Splay<String> tree;

    @Before
    public void before() {
        tree = new Splay<>();
    }

    @Test
    public void insert() {
        tree.insert("J");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertEquals("J", tree.getRoot().getKey());
        tree.insert("H");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertEquals("H", tree.getRoot().getKey());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertEquals("J", tree.getRoot().getRight().getKey());
        tree.insert("S");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertEquals("S", tree.getRoot().getKey());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertEquals("J", tree.getRoot().getLeft().getKey());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertEquals("H", tree.getRoot().getLeft().getLeft().getKey());
        tree.insert("K");
        Assert.assertNotNull(tree.getRoot());
        Assert.assertEquals("K", tree.getRoot().getKey());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertEquals("J", tree.getRoot().getLeft().getKey());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertEquals("H", tree.getRoot().getLeft().getLeft().getKey());
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

    }
}