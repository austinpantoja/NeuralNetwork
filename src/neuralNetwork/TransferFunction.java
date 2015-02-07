package neuralNetwork;


/**
 * Created by @author austin on 2/6/15.
 */


public class TransferFunction {

    public static final int SIGMOID = 0;
    public static final int TANH = 1;
    public static final int MIN_MAX = 2;
    public static final int NONE = 3;

    private int activateFunction;
    private double min = -1, max = 1;


    public TransferFunction(int type) {
        if (type < 0 || type > 3)
            throw new IllegalArgumentException(type + " is not in transfer function type range 0..3");
        activateFunction = type;
    }


    public static TransferFunction getTransferFunction(int type) {
        if (type < 0 || type > 3)
            throw new IllegalArgumentException(type + " is not in transfer function type range 0..3");
        TransferFunction tf = new TransferFunction(type);
        return tf;
    }


    public double activate(double x) {
        switch (activateFunction) {
            case SIGMOID:
                return sigmoid(x);
            case TANH:
                return Math.tanh(x);
            case MIN_MAX:
                return minMax(x);
            case NONE:
                return x;
            default:
                return 0;
        }
    }


    public double activateDer(double x) {
        switch (activateFunction) {
            case SIGMOID:
                return dSigmoid(x);
            case TANH:
                return dTanh(x);
            case MIN_MAX:
                return dMinMax();
            case NONE:
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
        return (x - min)/(max - min);
    }


    private double dMinMax() {
        return 2 / (max - min);
    }


    private void minMaxUpdate(double x) {
        if (x < min) min = x;
        if (x > max) max = x;
    }


    protected void setActivateFunction(int activateFunction) {
        if (activateFunction < 0 || activateFunction > 3)
            throw new IllegalArgumentException(activateFunction + " is not in transfer function type range 0..3");
        this.activateFunction = activateFunction;
    }

}
