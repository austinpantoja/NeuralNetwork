package neuralNetwork;

/**
 * Created by @author austin on 2/4/15.
 */


public class Neuron {

    private static double learningRate = 0.0004;
    private static TransferFunction transferFunction;

    private double[] weights;
    private double delta;


    public Neuron(int numOfInputs) {
        delta = 0;
        weights = new double[numOfInputs+1];
        for (int i = 0; i < weights.length; i++)
            weights[i] = 2*Math.random() - 1;
        if (transferFunction == null)
            setTransferFunction(FunctionType.Tanh);
    }


     protected double inputSum(double[] input) {
        double sum = weights[weights.length-1];
        for (int i = 0; i < input.length; i++)
            sum += input[i]*weights[i];
        return sum;
    }


    protected double fire(double[] input) {
        return transferFunction.activate(inputSum(input));
    }


    protected double fireDerivative(double[] input) {
        return transferFunction.activateDer(inputSum(input));
    }


    protected double[] backPropagate(double delta) {
        this.delta = delta;
        double[] inDeltas = new double[weights.length-1];
        for (int i = 0; i < inDeltas.length; i++)
            inDeltas[i] = weights[i] * delta;
        return inDeltas;
    }


    protected void setDelta(double delta) {
        this.delta = delta;
    }


    public void setWeights(double[] input) {
        double x = fireDerivative(input);
        weights[weights.length-1] += (learningRate*x*delta);
        for (int i = 0; i < input.length; i++)
            weights[i] += (input[i]*learningRate*x*delta);
    }


    public static void setTransferFunction(FunctionType type) {
        transferFunction.setActivateFunction(type);
    }


    public void print() {
        for (double d : weights)
            System.out.println(d);
        System.out.print("\n");
    }
}
