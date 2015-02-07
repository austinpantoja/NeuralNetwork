package neuralNetwork;

/**
 * Created by @author austin on 2/7/15.
 */

public class OutputLayer extends Layer {

    private double[] output;


    public OutputLayer(int numOfNeurons, double initialRange) {
        output = new double[numOfNeurons];
        neurons = new Neuron[numOfNeurons];
        for (int i = 0; i < numOfNeurons; i++)
            neurons[i] = new Neuron(inputSize);
    }


    protected void feedForward(double[] input) {
        for (int i = 0; i < neurons.length; i++)
            output[i] = neurons[i].fire(input);
    }


    protected void resetWeights(double[] inputs) {
        for (int i = 0; i < neurons.length; i++)
            neurons[i].setWeights(inputs);
    }


    public double[] getOutput() {
        return output;
    }
}
