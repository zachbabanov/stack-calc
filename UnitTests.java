import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Stack;

public class UnitTests
{

    @Test
    void testPushAndPrint() throws Exception
    {
        String input = "PUSH 5\nPRINT\n";
        double expectedOutput = 5.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAddition() throws Exception
    {
        String input = "PUSH 2\nPUSH 3\n+\nPRINT\n";
        double expectedOutput = 5.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testSubtraction() throws Exception
    {
        String input = "PUSH 7\nPUSH 4\n-\nPRINT\n";
        double expectedOutput = 3.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testMultiplication() throws Exception
    {
        String input = "PUSH 6\nPUSH 8\n*\nPRINT\n";
        double expectedOutput = 48.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDivision() throws Exception
    {
        String input = "PUSH 10\nPUSH 5\n/\nPRINT\n";
        double expectedOutput = 2.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testSqrt() throws Exception
    {
        String input = "PUSH 16\nSQRT\nPRINT\n";
        double expectedOutput = 4.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDefineVariable() throws Exception
    {
        String input = "DEFINE x 5\nPUSH x\nPRINT\n";
        double expectedOutput = 5.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testUseVariableInCalculation() throws Exception
    {
        String input = "DEFINE x 2\nDEFINE y 3\nPUSH x\nPUSH y\n*\nPRINT\n";
        double expectedOutput = 6.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEmptyInput() throws Exception
    {
        String input = "";
        double expectedOutput = 0.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testInvalidInput() throws Exception
    {
        String input = "PUSH 5\nINVALID\nPRINT\n";
        double expectedOutput = 5.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEmptyStack() throws Exception
    {
        String input = "+\nPRINT\n";
        double expectedOutput = 0.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testDivisionByZero() throws Exception
    {
        String input = "PUSH 5\nPUSH 0\n/\nPRINT\n";
        double actualOutput = runCalculation(input);
        Assertions.assertTrue(Double.isInfinite(actualOutput) && actualOutput > 0);
    }

    @Test
    void testSqrtOfNegativeNumber() throws Exception
    {
        String input = "PUSH -7\nSQRT\nPRINT\n";
        double actualOutput = runCalculation(input);
        Assertions.assertTrue(Double.isNaN(actualOutput));
    }

    @Test
    void testComments() throws Exception
    {
        String input = "# This is a comment\nPUSH 3\n# Another comment\nPUSH 4\n+\nPRINT\n";
        double expectedOutput = 7.0;
        double actualOutput = runCalculation(input);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    private double runCalculation(String input) throws Exception
    {
        BufferedReader reader = new BufferedReader(new StringReader(input));

        Stack<Double> stack = new Stack<>();
        HashMap<String, Double> parameters = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.startsWith("#"))
            {
                continue;
            }

            String[] tokens = line.split("\\s+");
            switch (tokens[0])
            {
                case "POP":
                    stack.pop();
                    break;
                case "PUSH":
                    if (parameters.containsKey(tokens[1]))
                    {
                        stack.push(parameters.get(tokens[1]));
                    }
                    else
                    {
                        stack.push(Double.parseDouble(tokens[1]));
                    }
                    break;
                case "+":
                    double a = stack.pop();
                    double b = stack.pop();
                    stack.push(b + a);
                    break;
                case "-":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b - a);
                    break;
                case "*":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b * a);
                    break;
                case "/":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b / a);
                    break;
                case "SQRT":
                    stack.push(Math.sqrt(stack.pop()));
                    break;
                case "PRINT":
                    double result = stack.peek();
                    stack.clear();
                    return result;
                case "DEFINE":
                    parameters.put(tokens[1], Double.parseDouble(tokens[2]));
                    break;
            }
        }
        reader.close();

        return 0;
    }
}