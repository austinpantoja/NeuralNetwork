package neuralNetwork;

/**
 * Created by @author austin on 2/7/15.
 */


public class NeuralNetwork {

    InputLayer inputLayer;
    OutputLayer outputLayer;


    public NeuralNetwork(int[] layerSizes) {
        inputLayer = new InputLayer(layerSizes[0]);
        outputLayer = new OutputLayer(layerSizes[layerSizes.length-1], 1);
        for (int i = 1; i < layerSizes.length-1; i++)
            inputLayer.insertLayer(new HiddenLayer(layerSizes[i]));
        inputLayer.insertLayer(outputLayer);
    }


    public void feedForward(double[] input) {
        inputLayer.feedForward(input);
    }


    public double[] getOutput() {
        return outputLayer.getOutput();
    }


    public void learn(double[] error) {
        outputLayer.backPropagate(error);
        inputLayer.resetWeights();
    }


    public void setNeuronTransferFunction(FunctionType type) {
        Neuron.setTransferFunction(type);
    }


    public void setInputTransfterFunction(FunctionType type) {
        inputLayer.setInputTransferFunction(type);
    }


    public void setInputMinMaxParams(double min, double max, double range) {
        inputLayer.setInputMinMaxParams(min, max, range);
    }


    public void printNetwork() {
        inputLayer.printLayers();
    }
}
