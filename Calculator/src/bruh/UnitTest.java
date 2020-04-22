package bruh;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    @Test
    public void testPush() {
        Push push = new Push();
        Context context = new Context();
        String[] command = {"push", "123"};
        push.toDo(command, context);
        assertEquals(123.0, Double.parseDouble(context.stack.get(0)));
    }

    @Test
    public void testPop() {
        Pop pop = new Pop();
        Context context = new Context();
        String[] command = {"pop", "432"};
        context.pushStack(command[1]);
        pop.toDo(command, context);
        assertTrue(context.stack.isEmpty());
    }

    @Test
    public void testPlus() {
        Plus plus = new Plus();
        Context context = new Context();
        context.pushStack("900");
        context.pushStack("33");
        String[] command = {"plus", "900", "33"};
        plus.toDo(command, context);
        assertEquals(933.0, Double.parseDouble(context.stack.get(context.stack.size() - 1)));
    }

    @Test
    public void testMinus() {
        Minus minus = new Minus();
        Context context = new Context();
        context.pushStack("900");
        context.pushStack("33");
        String[] command = {"minus", "900", "33"};
        minus.toDo(command, context);
        assertEquals(867.0, Double.parseDouble(context.stack.get(context.stack.size() - 1)));
    }

    @Test
    public void testMultiplication() {
        Multiplication multiplication = new Multiplication();
        Context context = new Context();
        String[] command = {"*", "900", "33"};
        context.pushStack(command[1]);
        context.pushStack(command[2]);
        multiplication.toDo(command, context);
        assertEquals(29700.0, Double.parseDouble(context.stack.get(context.stack.size() - 1)));
    }

    @Test
    public void testDivision() {
        Division division = new Division();
        Context context = new Context();
        String[] command = {"/", "8", "5"};
        context.pushStack(command[1]);
        context.pushStack(command[2]);
        division.toDo(command, context);
        assertEquals(1.6, Double.parseDouble(context.stack.get(context.stack.size() - 1)));
    }

    @Test
    public void testSqrt() {
        Sqrt sqrt = new Sqrt();
        Context context = new Context();
        String[] command = {"sqrt", "788544"};
        context.pushStack(command[1]);
        sqrt.toDo(command, context);
        assertEquals(888.0, Double.parseDouble(context.stack.get(context.stack.size() - 1)));
    }

    @Test
    public void testDefine() {
        Define define = new Define();
        Context context = new Context();
        String[] command = {"define", "a", "322"};
        define.toDo(command, context);
        assertEquals(322.0, context.getDefineValue(command[1]));
    }
}