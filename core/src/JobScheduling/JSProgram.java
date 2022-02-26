package JobScheduling;

import java.io.IOException;

public class JSProgram {
    public static void main(String[] args) {
        Coordinator coordinator = new Coordinator();
        try {
            coordinator.experiment(26, 30);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
