package test.com.keren; 

import com.keren.CapitalOneChecks;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

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
public void testCheck() throws Exception {
    String test1 = "/Users/akayayy/Desktop/test.java";
    String test2 = "/Users/akayayy/Desktop/test.py";
    String test3 = "/Users/akayayy/Desktop/test.js";
    String test4 = "/Users/akayayy/Desktop/test.css";
    String test5 = "/Users/akayayy/Desktop/test.html";
    String test6 = "/Users/akayayy/Desktop/test";
    String test7 = "/Users/akayayy/Desktop/te.java";
    String test8 = "/Users/akayayy/Desktop/test.ja";

    System.out.println(test1+ " ------------");
    coc.check(test1);
    System.out.println();
    System.out.println(test2+ " ------------");
    coc.check(test2);
    System.out.println();
    System.out.println(test3+ " ------------");
    coc.check(test3);
    System.out.println();
    System.out.println(test4+ " ------------");
    coc.check(test4);
    System.out.println();
    System.out.println(test5+ " ------------");
    coc.check(test5);
    System.out.println();
    System.out.println(test6+ " ------------");
    coc.check(test6);
    System.out.println();
    System.out.println(test7+ " ------------");
    coc.check(test7);
    System.out.println();
    System.out.println(test8+ " ------------");
    coc.check(test8);



} 


} 
