public class Polynomial {
    double[] coef;

    public Polynomial() {
        coef = new double[] { 0 };
    }

    public Polynomial(double[] value) {
        coef = new double[value.length];
        for (int i = 0; i < value.length; i++)
            coef[i] = value[i];
    }

    public Polynomial add(Polynomial p) {
        Polynomial big_p = new Polynomial();
        Polynomial small_p = new Polynomial();

        big_p.coef = this.coef;
        small_p.coef = p.coef;

        if (this.coef.length < p.coef.length) {
            big_p.coef = p.coef;
            small_p.coef = this.coef;
        }

        double[] result = new double[big_p.coef.length];

        for (int i = 0; i < big_p.coef.length; i++) {
            result[i] = big_p.coef[i];
            if (i < small_p.coef.length) {
                result[i] += small_p.coef[i];
            }
        }

        return new Polynomial(result);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coef.length; i++)
            result += coef[i] * Math.pow(x, i);

        return result;
    }

    public boolean hasRoot(double x) {
        return (this.evaluate(x) == 0);
    }
}
