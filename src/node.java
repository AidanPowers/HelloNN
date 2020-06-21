import java.util.Random;

public class node {

    Random rand = new Random();
    node targets[];
    double weights[];
    double result= 0;
    double outputInputs = 0;
    boolean iFlag = false;
    boolean hFlag = false;
    boolean oFlag = false;

    public void setTypeO(){
        oFlag = true;
    }
    public void setTypeH(){
        hFlag = true;
    }
    public void setTypeI(){
        iFlag = true;
    }

    public void setup(node inpTargets[]){
        targets=inpTargets;
        weights = new double[targets.length];
        for (int i = 0; i<weights.length; i++){
            weights[i] = rand.nextDouble();
        }

    }

    public void input(double val){
        if (iFlag){
            output(val);
        }
        else if(hFlag){
            output(sigmoid(val));
        }
        else if(oFlag){
            outputInputs++;
            result = result + val;
        }
        else
            System.out.println("no flag error");
    }

    private void output(double val){
        for (int i = 0; i<targets.length; i++){
            node next = targets[i];
            double weight = weights[i];
            next.input(val * weight);
        }
    }
    public double getResult(){
            return result/outputInputs;
    }

    //activation algotihm
    public double sigmoid(double inp){
        return 1/(1+Math.exp(-1*inp));
    }
}

