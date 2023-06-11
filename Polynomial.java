import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class Polynomial {
    double[] coef;
    int[] exp;

    public Polynomial() {
        this.coef = new double[] { 0 };
        this.exp = new int[] { 0 };
    }

    public Polynomial(double[] new_coef, int[] new_exp) {
        this.coef = new double[new_coef.length];
        this.exp = new int[new_exp.length];
        for (int i = 0; i < new_coef.length; i++) {
            this.coef[i] = new_coef[i];
            this.exp[i] = new_exp[i];
        }
    }

    public Polynomial(File file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line = input.readLine();
        String[] terms = line.split("(?=[+-])");
        int index = 0;
        this.coef = new double[terms.length];
        this.exp = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {
            index = terms[i].indexOf('x');
            if (index == -1) {
                this.coef[i] = Double.parseDouble(terms[i]);
                this.exp[i] = 0;

            } else {
                this.coef[i] = Double.parseDouble(terms[i].substring(0, index));
                this.exp[i] = Integer.parseInt(terms[i].substring(index + 1));
            }

        }

        input.close();

    }

    public Polynomial add(Polynomial p) {
        int max = 0;
        int coef_val = 0;

        for (int i = 0; i < p.exp.length; i++) {
            max = Math.max(p.exp[i], max);
        }
        for (int i = 0; i < this.exp.length; i++) {
            max = Math.max(this.exp[i], max);
        }

        double[] new_coef = new double[max + 1];
        int[] new_exp = new int[max + 1];

        for (int i = 0; i <= max; i++) {
            coef_val = 0;
            for (int j = 0; j < this.exp.length; j++) {
                if (this.exp[j] == i) {
                    coef_val += this.coef[j];
                    break;
                }
            }
            for (int j = 0; j < p.exp.length; j++) {
                if (p.exp[j] == i) {
                    coef_val += p.coef[j];
                    break;
                }
            }
            new_exp[i] = i;
            new_coef[i] = coef_val;
        }

        return remove_zeroes(new_coef, new_exp);
    }

    public Polynomial remove_zeroes(double[] new_coef, int[] new_exp) {
        double[] no_zero_coef = new double[new_coef.length];
        int[] no_zero_exp = new int[new_exp.length];
        int new_length = 0;

        for (int i = 0; i < new_coef.length; i++) {
            if (new_coef[i] != 0) {
                no_zero_coef[new_length] = new_coef[i];
                no_zero_exp[new_length] = new_exp[i];
                new_length++;
            }
        }

        no_zero_coef = Arrays.copyOf(no_zero_coef, new_length);
        no_zero_exp = Arrays.copyOf(no_zero_exp, new_length);
        return new Polynomial(no_zero_coef, no_zero_exp);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coef.length; i++)
            result += coef[i] * Math.pow(x, exp[i]);

        return result;
    }

    public boolean hasRoot(double x) {
        return (this.evaluate(x) == 0);
    }

    public Polynomial multiply(Polynomial p) {
        int max_a = 0;
        int max_b = 0;

        for (int i = 0; i < p.exp.length; i++) {
            max_a = Math.max(p.exp[i], max_a);
        }
        for (int i = 0; i < this.exp.length; i++) {
            max_b = Math.max(this.exp[i], max_b);
        }

        int max = max_a + max_b + 1;

        double[] new_coef = new double[max];
        int[] new_exp = new int[max];

        for (int i = 0; i < max; i++) {
            new_coef[i] = 0;
            new_exp[i] = i;
        }

        for (int i = 0; i < this.exp.length; i++) {
            for (int j = 0; j < p.exp.length; j++) {
                new_coef[this.exp[i] + p.exp[j]] += this.coef[i] * p.coef[j];
            }
        }

        return remove_zeroes(new_coef, new_exp);
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder line = new StringBuilder();

            for (int i = 0; i < this.coef.length; i++) {
                if (i != 0 && this.coef[i] > 0) {
                    line.append("+");
                }
                line.append(this.coef[i]);
                if (this.exp[i] != 0) {
                    line.append('x');
                    if (this.exp[i] != 1) {
                        line.append(this.exp[i]);
                    }
                }
            }

            output.write(line.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
