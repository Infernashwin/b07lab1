import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double[] c1 = { 6, 0, 0, 5 };
        int[] e1 = { 0, 1, 2, 3, 4 };
        Polynomial p1 = new Polynomial(c1, e1);
        double[] c2 = { 0, -2, 0, 0, -9 };
        Polynomial p2 = new Polynomial(c2, e1);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        // New Test Cases
        double[] c3 = { 2, -3, -5, 6 };
        int[] e3 = { 0, 1, 2, 3 };
        Polynomial p3 = new Polynomial(c3, e3);

        double[] c4 = { 32, 1 };
        int[] e4 = { 0, 5 };
        Polynomial p4 = new Polynomial(c4, e4);

        double[] c5 = { 3, -4, 5, -6, 7, -8 };
        int[] e5 = { 2, 3, 4, 5, 6, 7 };
        Polynomial p5 = new Polynomial(c5, e5);

        double[] c6 = { -32, -1 };
        int[] e6 = { 0, 5 };
        Polynomial p6 = new Polynomial(c6, e6);

        System.out.println("---------------NEW TESTS---------------");

        System.out.println("Test Read from File:");
        File f = new File("poly.txt");
        try {
            Polynomial p7 = new Polynomial(f);
            System.out.println("Received: " + Arrays.toString(p7.coef) + "  " + Arrays.toString(p7.exp));
            System.out.println("Expected: [5.0, -3.0, 7.0]  [0, 2, 8]");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File f2 = new File("poly2.txt");
        try {
            Polynomial p8 = new Polynomial(f2);
            System.out.println("Received: " + Arrays.toString(p8.coef) + "  " + Arrays.toString(p8.exp));
            System.out.println("Expected: [3.0, 6.0, -2.0, 4.0, -8.0]  [0, 1, 2, 5, 9]");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Test Add:");
        Polynomial sum = p4.add(p3);
        System.out.println("Received: " + Arrays.toString(sum.coef) + "  " + Arrays.toString(sum.exp));
        System.out.println("Expected: [34.0, -3.0, -5.0, 6.0, 1.0]  [0, 1, 2, 3, 5]");
        System.out.println();

        Polynomial sum2 = p3.add(p4);
        System.out.println("Received: " + Arrays.toString(sum2.coef) + "  " + Arrays.toString(sum2.exp));
        System.out.println("Expected: [34.0, -3.0, -5.0, 6.0, 1.0]  [0, 1, 2, 3, 5]");
        System.out.println();

        Polynomial sum3 = p3.add(p5);
        System.out.println("Received: " + Arrays.toString(sum3.coef) + "  " + Arrays.toString(sum3.exp));
        System.out.println("Expected: [2.0, -3.0, -2.0, 2.0, 5.0, -6.0, 7.0, -8.0]  [0, 1, 2, 3, 4, 5, 6, 7]");
        System.out.println();

        Polynomial sum4 = p4.add(p6);
        System.out.println("Received: " + Arrays.toString(sum4.coef) + "  " + Arrays.toString(sum4.exp));
        System.out.println("Expected: null  null");
        System.out.println();

        Polynomial sum5 = sum4.add(sum4);
        System.out.println("Received: " + Arrays.toString(sum5.coef) + "  " + Arrays.toString(sum5.exp));
        System.out.println("Expected: null  null");
        System.out.println();

        Polynomial sum6 = sum5.add(sum3);
        System.out.println("Received: " + Arrays.toString(sum6.coef) + "  " + Arrays.toString(sum6.exp));
        System.out.println("Expected: [2.0, -3.0, -2.0, 2.0, 5.0, -6.0, 7.0, -8.0]  [0, 1, 2, 3, 4, 5, 6, 7]");
        System.out.println();

        Polynomial sum7 = sum3.add(sum5);
        System.out.println("Received: " + Arrays.toString(sum7.coef) + "  " + Arrays.toString(sum7.exp));
        System.out.println("Expected: [2.0, -3.0, -2.0, 2.0, 5.0, -6.0, 7.0, -8.0]  [0, 1, 2, 3, 4, 5, 6, 7]");
        System.out.println();

        System.out.println("Test Multiply:");
        Polynomial prod1 = p3.multiply(p4);
        System.out.println("Received: " + Arrays.toString(prod1.coef) + "  " + Arrays.toString(prod1.exp));
        System.out.println("Expected: [64.0, -96.0, -160.0, 192.0, 2.0, -3.0, -5.0, 6.0]  [0, 1, 2, 3, 5, 6, 7, 8]");
        System.out.println();

        Polynomial prod2 = p4.multiply(p3);
        System.out.println("Received: " + Arrays.toString(prod2.coef) + "  " + Arrays.toString(prod2.exp));
        System.out.println("Expected: [64.0, -96.0, -160.0, 192.0, 2.0, -3.0, -5.0, 6.0]  [0, 1, 2, 3, 5, 6, 7, 8]");
        System.out.println();

        Polynomial prod3 = p5.multiply(p4);
        System.out.println("Received: " + Arrays.toString(prod3.coef) + "  " + Arrays.toString(prod3.exp));
        System.out.println(
                "Expected: [96.0, -128.0, 160.0, -192.0, 224.0, -253.0, -4.0, 5.0, -6.0, 7.0, -8.0]  [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]");
        System.out.println();

        Polynomial prod4 = p5.multiply(sum4);
        System.out.println("Received: " + Arrays.toString(prod4.coef) + "  " + Arrays.toString(prod4.exp));
        System.out.println("Expected: null  null");
        System.out.println();

        Polynomial prod5 = sum4.multiply(p4);
        System.out.println("Received: " + Arrays.toString(prod5.coef) + "  " + Arrays.toString(prod5.exp));
        System.out.println("Expected: null  null");
        System.out.println();

        System.out.println("Test Evaluate:");
        System.out.println("Received: " + p3.evaluate(5));
        System.out.println("Expected: 612.0");
        System.out.println();

        System.out.println("Received: " + p4.evaluate(2));
        System.out.println("Expected: 64.0");
        System.out.println();

        System.out.println("Received: " + p5.evaluate(0));
        System.out.println("Expected: 0.0");
        System.out.println();

        System.out.println("Received: " + prod5.evaluate(0));
        System.out.println("Expected: 0.0");
        System.out.println();

        System.out.println("Received: " + prod5.evaluate(1034));
        System.out.println("Expected: 0.0");
        System.out.println();

        System.out.println("Test HasRoot:");
        System.out.println("Received: " + p3.hasRoot(1));
        System.out.println("Expected: true");
        System.out.println();

        System.out.println("Received: " + p5.hasRoot(-1));
        System.out.println("Expected: false");
        System.out.println();

        System.out.println("Received: " + p5.hasRoot(0));
        System.out.println("Expected: true");
        System.out.println();

        System.out.println("Received: " + prod5.hasRoot(9013));
        System.out.println("Expected: true");
        System.out.println();

        System.out.println("Test Save To File:");
        p5.saveToFile("output.txt");
        System.out.println("Successfully written to output.txt");
        System.out.println();
        System.out.println("Reading from output.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received: " + line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        System.out.println("Expected: 3.0x2-4.0x3+5.0x4-6.0x5+7.0x6-8.0x7");
    }
}
