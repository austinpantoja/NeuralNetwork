package neuralNetwork;

/**
 * Created by @author austin on 2/6/15.
 */

public class InputLayer {

    private TransferFunction transferFunction;
    private double[] input;
    private Layer outLayer;

    public InputLayer(int numOfInputs) {
        input = new double[numOfInputs];
        transferFunction = new TransferFunction(FunctionType.MinMax);
    }


    public void feedForward(double[] input) {
        for (int i = 0; i < input.length; i++)
            this.input[i] = transferFunction.activate(input[i]);
        outLayer.feedForward(input);
    }


    public void resetWeights() {
        outLayer.resetWeights(input);
    }


    public void insertLayer(Layer layer) {
        if (outLayer == null) {
            outLayer = layer;
            layer.layerID = 1;
            layer.setInputSize(input.length);
            layer.createNeurons();
        }
        else outLayer.insertLayer(layer);
    }


    protected void setInputTransferFunction(FunctionType type) {
        transferFunction.setActivateFunction(type);
    }


    protected void setInputMinMaxParams(double min, double max, double range) {
        transferFunction.setMinMaxValues(min, max, range);
    }


    protected void printLayers() {
        outLayer.print();
    }

}
