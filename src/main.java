import java.util.Random;


public class main {
    static node[] iLayer = new node[2];
    static node[] hLayer = new node[3];
    static node[] oLayer = new node[2];

    public static void main(String[] args) {
        iLayer = new node[2];
        hLayer = new node[3];
        oLayer = new node[2];


        for (int i = 0; i < iLayer.length; i++){
            iLayer[i] = new node();
            iLayer[i].setTypeI();
            iLayer[i].setup(hLayer);
        }
        for (int i = 0; i < hLayer.length; i++){
            hLayer[i] = new node();
            hLayer[i].setTypeH();
            hLayer[i].setup(oLayer);
        }
        for (int i = 0; i < oLayer.length; i++){
            oLayer[i] = new node();
            oLayer[i].setTypeO();
        }
        Random rand = new Random();
        double height = rand.nextInt(100);
        double dist = (rand.nextDouble()+rand.nextDouble())*height*2;
        double[] initData = new double[]{dist,height};
        double[] resultant = run(initData);
        for (double result: resultant){
            System.out.println(result);
        }
        train(10);
        resultant = run(initData);
        for (double result: resultant){
            System.out.println(result);
        }
        resultant = game(initData);
        for (double result: resultant){
            System.out.println(result);
        }
        System.out.println("done");
    }


    public static void train(int iterations){
        Random rand = new Random();
        double height = rand.nextInt(10000);
        double dist = (rand.nextDouble()+rand.nextDouble())*height*2;
        double[] conditions = new double[]{dist,height};
        int iter = 0;
        while (iter < iterations) {
            for (int i = 0; i < hLayer.length; i++) {
                double[] initalWeight = hLayer[i].getWeight();
                double baseDif = error(conditions);
                for (int j = 0; j < initalWeight.length; j++) {
                    double[] currentWeight = initalWeight.clone();
                    currentWeight[j] = currentWeight[j] + ((rand.nextDouble() - .5) / 100);
                    hLayer[i].adjustWeight(currentWeight);
                    double newDif = error(conditions);
                    if (newDif >= baseDif) {
                        hLayer[i].adjustWeight(initalWeight);
                    }
                }
            }
            iter++;
        }


    }


    public static double[] run(double[] input){
        for (int i = 0; i < input.length; i++) {
            iLayer[i].input(input[i]);
        }
        double[] output = new double[oLayer.length];
        for (int i = 0; i < output.length; i++){
            output[i] = oLayer[i].getResult();
        }
        return output;
    }

    public static double error(double[] input){
        return dif(run(input),game(input));
    }

    public static double dif(double[] inp1,double[] inp2){
        double output = 0;
        for (int i = 0; i < inp1.length; i++){
           output = output + Math.abs(inp1[i]-inp2[i]);
        }
        return output;
    }


    //the game we are trying to play
    public static double[] game(double[] inp){
        double dist = inp[0];
        double height = inp[1];
        if (dist/height >= 2){
            return new double[]{1, 0};
        }
        else {
            return new double[]{0, 1};
        }
    }
}
