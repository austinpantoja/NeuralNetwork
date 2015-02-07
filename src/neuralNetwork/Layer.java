package neuralNetwork;


/**
 * Created by @author austin on 2/6/15.
 */

public abstract class Layer {

    protected Neuron[] neurons;
    protected int inputSize, layerID;
    protected Layer inLayer, outLayer;

    protected abstract void feedForward(double[] input);
    protected abstract void resetWeights(double[] inputs);


    protected void createNeurons() {
        for (int i = 0; i < neurons.length; i++)
            neurons[i] = new Neuron(inputSize);
    }


    protected void backPropagate(double[] deltas) {
        if (inLayer == null) {
            setDeltas(deltas);
            return;
        }
        double[] tmpDeltas, inDeltas = new double[inputSize];
        for (int i = 0; i < neurons.length; i++) {
            tmpDeltas = neurons[i].backPropagate(deltas[i]);
            for (int j = 0; j < tmpDeltas.length; j++)
                inDeltas[j] += tmpDeltas[i];
        }
        inLayer.backPropagate(inDeltas);
    }


    protected void setDeltas(double[] deltas) {
        for (int i = 0; i < neurons.length; i++)
            neurons[i].setDelta(deltas[i]);
    }


    protected void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }


    protected void insertLayer(Layer layer) {
        if (outLayer == null) {
            outLayer = layer;
            layer.inLayer = this;
            layer.layerID = layerID+1;
            layer.setInputSize(neurons.length);
            layer.createNeurons();
        }
        else outLayer.insertLayer(layer);
    }


    protected void print() {
        System.out.println("\n______________________");
        if (outLayer == null) System.out.println("Output Layer\n");
        else System.out.println("Hidden Layer " + layerID + "\n");
        for (Neuron n : neurons) n.print();
        if (outLayer != null) outLayer.print();
    }

}
