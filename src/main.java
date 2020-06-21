public class main {


    public static void main(String[] args) {
        node[] iLayer = new node[2];
        node[] hLayer = new node[3];
        node[] oLayer = new node[2];

        node test = new node();
        System.out.println(test.sigmoid(10));

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

        iLayer[0].input(.5);
        iLayer[1].input(.5);


        for (node oNode: oLayer){
            System.out.println(oNode.getResult() + "out");
        }

        System.out.println("done");

    }



    //the game we are trying to play
    public static boolean game(double dist, double height){
        if (dist/height >= 2){
            return true;
        }
        else {
            return false;
        }
    }
}
