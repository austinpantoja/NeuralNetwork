package neuralNetwork;


/**
 * Created by @author austin on 2/7/15.
 */

public class HiddenLayer extends Layer {


    public HiddenLayer(int numOfNeurons) {
        neurons = new Neuron[numOfNeurons];
    }


    protected void feedForward(double[] input) {
        double[] output = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++)
            output[i] = neurons[i].fire(input);
        outLayer.feedForward(output);
    }


    protected void resetWeights(double[] inputs) {
        double[] output = new double[neurons.length];
        for (int i = 0; i < neurons.length; i++) {
            neurons[i].setWeights(inputs);
            output[i] = neurons[i].fire(inputs);
        }
        outLayer.resetWeights(output);
    }

}
