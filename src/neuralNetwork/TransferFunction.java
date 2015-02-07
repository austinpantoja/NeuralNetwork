package neuralNetwork;


/**
 * Created by @author austin on 2/6/15.
 */


public class TransferFunction {

    private FunctionType functionType;
    private double min = -1, max = 1, range = 2;


    public TransferFunction(FunctionType type) {
        functionType = type;
    }


    public static TransferFunction getTransferFunction(FunctionType type) {
        TransferFunction tf = new TransferFunction(type);
        return tf;
    }


    public double activate(double x) {
        switch (functionType) {
            case Sigmoid:
                return sigmoid(x);
            case Tanh:
                return Math.tanh(x);
            case MinMax:
                return minMax(x);
            case Heaviside:
                return heaviside(x);
            case None:
                return x;
            default:
                return 0;
        }
    }


    public double activateDer(double x) {
        switch (functionType) {
            case Sigmoid:
                return dSigmoid(x);
            case Tanh:
                return dTanh(x);
            case MinMax:
                return dMinMax();
            case Heaviside:
                return dHeaviside(x);
            case None:
                return 1;
            default:
                return 0;
        }
    }


    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }


    private double dSigmoid(double x) {
        double sig = sigmoid(x);
        return sig*(1 - sig);
    }


    private double dTanh(double x) {
        return 1 - Math.pow(Math.tanh(x), 2);
    }


    private double minMax(double x) {
        return range*(x - min)/(max - min) - (range/2);
    }


    private double dMinMax() {
        return 2 / (max - min);
    }


    private double heaviside(double x) {
        return (x < 0) ? 0 : 1;
    }


    private double dHeaviside(double x) {
        return 0;
    }


    protected void setMinMaxValues(double min, double max, double range) {
        this.min = min;
        this.max = max;
        this.range = range;
    }


    protected void setActivateFunction(FunctionType type) {
        functionType = type;
    }

}
