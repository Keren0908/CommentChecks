package test.com.keren; 

import com.keren.CapitalOneChecks;
import com.keren.Comment;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/** 
* CapitalOneChecks Tester. 
* 
* @author <Authors name> 
* @since <pre>Oct 23, 2018</pre> 
* @version 1.0 
*/ 
public class CapitalOneChecksTest {
    CapitalOneChecks coc = new CapitalOneChecks();

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: check(String filepath) 
* 
*/ 
    @Test
    public void testCheck_1() throws Exception {


        Comment comment = coc.check("/Users/akayayy/Desktop/test.java");
        assertEquals(60,comment.getCountLines());
        assertEquals(28,comment.getCountCommentLines());
        assertEquals(6,comment.getCountSingleComments());
        assertEquals(22,comment.getCountBlockCommentsLines());
        assertEquals(2,comment.getCountBlockComments());
        assertEquals(2,comment.getCountTODO());




}

    @Test
    public void testCheck_2() throws Exception {


        Comment comment = coc.check("/Users/akayayy/Desktop/test.py");
        assertEquals(61,comment.getCountLines());
        assertEquals(19,comment.getCountCommentLines());
        assertEquals(9,comment.getCountSingleComments());
        assertEquals(10,comment.getCountBlockCommentsLines());
        assertEquals(3,comment.getCountBlockComments());
        assertEquals(3,comment.getCountTODO());


    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFoundCheck() throws IOException {
        Comment comment = coc.check("/Users/akayayy/Desktop/te.py");
    }

    @Test
    public void testExtensionNotValie() throws IOException {
        Comment comment = coc.check("/Users/akayayy/Desktop/test.df");
        assertEquals("Extention is not valid!",comment.getError());
    }

} 
