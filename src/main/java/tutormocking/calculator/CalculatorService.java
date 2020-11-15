package tutormocking.calculator;

/**
 * The interface Calculator service.
 */
public interface CalculatorService {
    /**
     * Add double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double
     */
    public double add(double input1, double input2);

    /**
     * Subtract double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double
     */
    public double subtract(double input1, double input2);

    /**
     * Multiply double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double
     */
    public double multiply(double input1, double input2);

    /**
     * Divide double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double
     */
    public double divide(double input1, double input2);
}
