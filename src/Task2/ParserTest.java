import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    /*
        We suggest you test your parser by also running it in your own terminal and checking the output.

        We advise you write additional tests to increase the confidence of your implementation. Simply getting these
	    tests correct does not mean your solution is robust enough pass the marking tests.
     */

    private static Tokenizer tokenizer;
    
    private static final String SIMPLE_CASE = "1+2";
    private static final String SIMPLE_ASSOCIATIVE_CASE = "5 + 2 + 9 + 8";
    private static final String MID_CASE = "12 * 5 - 3";
    private static final String COMPLEX_CASE1 = "(10 - 2) * (10 / 2) + 1";
    private static final String COMPLEX_CASE2 = "((4+5) / 3)!";

    private static final String[] testExample = new String[]{"2+1", "2-1", "2*1", "2/1"};
    
    
    @Test(timeout=1000)
    public void testSimleAdd() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[0]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(3, t1.evaluate());            
    }
    
    @Test(timeout=1000)
    public void testSimleSub() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[1]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(1, t1.evaluate());            
    }
    
    @Test(timeout=1000)
    public void testSimleMul() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[2]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(2, t1.evaluate());            
    }
    
    @Test(timeout=1000)
    public void testSimleDiv() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[3]);
        Exp t1 = new Parser(mathTokenizer).parseExp();
        assertEquals(2, t1.evaluate());            
    }

    @Test(timeout=1000)
    public void testSimpleCase(){
        tokenizer = new Tokenizer(SIMPLE_CASE);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format", "(1 + 2)", exp.show());
            assertEquals("incorrect evaluate value", 3, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test(timeout=1000)
    public void testSimpleAssociativeCase(){
        tokenizer = new Tokenizer(SIMPLE_ASSOCIATIVE_CASE);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format", "(5 + (2 + (9 + 8)))", exp.show());
            assertEquals("incorrect evaluate value", 24, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test(timeout=1000)
    public void testMidCase(){
        tokenizer = new Tokenizer(MID_CASE);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format", "((12 * 5) - 3)", exp.show());
            assertEquals("incorrect evaluate value", 57, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test(timeout=1000)
    public void testComplexCase1() {
        tokenizer = new Tokenizer(COMPLEX_CASE1);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format","(((10 - 2) * (10 / 2)) + 1)", exp.show());
            assertEquals("incorrect evaluate value", 41, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test(timeout=1000)
    public void testComplexCase2() {
        tokenizer = new Tokenizer(COMPLEX_CASE2);
        try{
            Exp exp = new Parser(tokenizer).parseExp();
            assertEquals("incorrect display format","(((4 + 5) / 3)!)", exp.show());
            assertEquals("incorrect evaluate value", 6, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test(timeout=1000)
    public void testIllegalProductionException() {
        // Provide a series of tokens that should invoke this exception
        assertThrows(Parser.IllegalProductionException.class, () -> {
           tokenizer = new Tokenizer("++");
           Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("-+");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("1 + -");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("1 5");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("(");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("(1+2");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("1+2)");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("1-2)");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("1/2)");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("1*2)");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("((1+2)/2");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("5 / )");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("5 5 2 + )");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("5 + !");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("!3");
            Exp exp = new Parser(tokenizer).parseExp();
        });

        assertThrows(Parser.IllegalProductionException.class, () -> {
            tokenizer = new Tokenizer("10! !");
            Exp exp = new Parser(tokenizer).parseExp();
        });
    }
}


