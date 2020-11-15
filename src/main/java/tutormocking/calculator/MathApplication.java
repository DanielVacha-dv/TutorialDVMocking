package tutormocking.calculator;

/**
 * The type Math application.
 * Object MathApplication does simple math operation by calling object that implements
 * CalculatorService interface
 * Objekt MathApplication provadi matematicke operace volanim funkci
 * objektu tridy CalculatorService
 */
public class MathApplication {
    private  CalculatorService calcService;

    /**
     * Set calculator service.
     *
     * @param calcService the calc service
     */
    public void setCalculatorService(CalculatorService calcService){
        this.calcService = calcService;
    }

    /**
     * Add double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double after add operation
     */
    public double add(double input1, double input2){
        return calcService.add(input1, input2);
    }

    /**
     * Subtract double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double of subtract
     */
    public double subtract(double input1, double input2){
        return calcService.subtract(input1, input2);
    }

    /**
     * Multiply double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double
     */
    public double multiply(double input1, double input2){
        return calcService.multiply(input1, input2);
    }

    /**
     * Divide double.
     *
     * @param input1 the input 1
     * @param input2 the input 2
     * @return the double
     */
    public double divide(double input1, double input2){
        return calcService.divide(input1, input2);
    }

    /**
     * Sets calc service.
     *
     * @param calcService the calc service
     */
    public void setCalcService(CalculatorService calcService) {
        this.calcService = calcService;
    }
}
