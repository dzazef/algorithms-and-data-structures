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
        tree.inorder();
        Assert.assertNotNull(tree.getRoot());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertTrue(tree.getRoot().getColor());
        tree.insert("H");
        tree.inorder();
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertTrue(tree.getRoot().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getColor());
        tree.insert("S");
        tree.inorder();
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertTrue(tree.getRoot().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getColor());
        Assert.assertFalse(tree.getRoot().getRight().getColor());
        tree.insert("C");
        tree.inorder();
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertEquals("C", tree.getRoot().getLeft().getLeft().getKey());
        Assert.assertTrue(tree.getRoot().getColor());
        Assert.assertTrue(tree.getRoot().getLeft().getColor());
        Assert.assertTrue(tree.getRoot().getRight().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getColor());
        tree.insert("I");
        tree.inorder();
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
        Assert.assertTrue(tree.getRoot().getColor());
        Assert.assertTrue(tree.getRoot().getLeft().getColor());
        Assert.assertTrue(tree.getRoot().getRight().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getRight().getColor());
        tree.insert("O");
        tree.inorder();
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
        Assert.assertTrue(tree.getRoot().getColor());
        Assert.assertTrue(tree.getRoot().getLeft().getColor());
        Assert.assertTrue(tree.getRoot().getRight().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getRight().getColor());
        Assert.assertFalse(tree.getRoot().getRight().getLeft().getColor());
        tree.insert("U");
        tree.inorder();
        Assert.assertNotNull(tree.getRoot());
        Assert.assertNotNull(tree.getRoot().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight());
        Assert.assertNotNull(tree.getRoot().getRight().getLeft());
        Assert.assertNotNull(tree.getRoot().getRight().getRight());
        Assert.assertNotNull(tree.getRoot().getLeft().getLeft());
        Assert.assertNotNull(tree.getRoot().getLeft().getRight());
        Assert.assertEquals("J", tree.getRoot().getKey());
        Assert.assertEquals("H", tree.getRoot().getLeft().getKey());
        Assert.assertEquals("S", tree.getRoot().getRight().getKey());
        Assert.assertEquals("O", tree.getRoot().getRight().getLeft().getKey());
        Assert.assertEquals("C", tree.getRoot().getLeft().getLeft().getKey());
        Assert.assertEquals("I", tree.getRoot().getLeft().getRight().getKey());
        Assert.assertEquals("U", tree.getRoot().getRight().getRight().getKey());
        Assert.assertTrue(tree.getRoot().getColor());
        Assert.assertTrue(tree.getRoot().getLeft().getColor());
        Assert.assertTrue(tree.getRoot().getRight().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getLeft().getColor());
        Assert.assertFalse(tree.getRoot().getLeft().getRight().getColor());
        Assert.assertFalse(tree.getRoot().getRight().getLeft().getColor());
        Assert.assertFalse(tree.getRoot().getRight().getRight().getColor());
        tree.inorder();
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