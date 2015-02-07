package neuralNetwork;

/**
 * Created by @author austin on 2/6/15.
 */

public class InputLayer {

    private TransferFunction[] transferFuncs;
    private double[] input;
    private Layer outLayer;

    public InputLayer(int numOfInputs) {
        input = new double[numOfInputs];
        transferFuncs = new TransferFunction[numOfInputs];
        for (int i = 0; i < numOfInputs; i++)
            transferFuncs[i] = new TransferFunction(TransferFunction.MIN_MAX);
    }


    public void feedForward(double[] input) {
        for (int i = 0; i < input.length; i++)
            this.input[i] = transferFuncs[i].activate(input[i]);
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


    protected void setTransferFunction(int activateType) {
        for (TransferFunction tf : transferFuncs)
            tf.setActivateFunction(activateType);
    }


    protected void printLayers() {
        outLayer.print();
    }

}
