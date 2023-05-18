import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StackCalculator
{
    private static final Logger logger = Logger.getLogger(StackCalculator.class.getName());

    public static void main(String[] args) throws Exception
    {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        BufferedReader reader;
        if (args.length > 0)
        {
            reader = new BufferedReader(new FileReader(args[0]));
        }
        else
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        Stack<Double> stack = new Stack<>();
        HashMap<String, Double> parameters = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.startsWith("#"))
            {
                continue;
            }
            logger.info("Processing line: " + line);

            String[] tokens = line.split("\\s+");
            switch (tokens[0])
            {
                case "POP":
                    logger.info("POP operation performed");
                    stack.pop();
                    break;
                case "PUSH":
                    if (parameters.containsKey(tokens[1]))
                    {
                        double value = parameters.get(tokens[1]);
                        stack.push(value);
                        logger.info("PUSH operation performed with parameter " + tokens[1] + " and value " + value);
                    }
                    else
                    {
                        double value = Double.parseDouble(tokens[1]);
                        stack.push(value);
                        logger.info("PUSH operation performed with value " + value);
                    }
                    break;
                case "+":
                    logger.info("Addition operation performed");
                    double a = stack.pop();
                    double b = stack.pop();
                    stack.push(b + a);
                    break;
                case "-":
                    logger.info("Subtraction operation performed");
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b - a);
                    break;
                case "*":
                    logger.info("Multiplication operation performed");
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b * a);
                    break;
                case "/":
                    logger.info("Division operation performed");
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b / a);
                    break;
                case "SQRT":
                    logger.info("Square root operation performed");
                    stack.push(Math.sqrt(stack.pop()));
                    break;
                case "PRINT":
                    logger.info("Print operation performed");
                    System.out.println(stack.peek());
                    break;
                case "DEFINE":
                    double value = Double.parseDouble(tokens[2]);
                    parameters.put(tokens[1], value);
                    logger.info("Variable " + tokens[1] + " defined with value " + value);
                    break;
            }
        }
        reader.close();
    }
}