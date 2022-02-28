package knapsackBB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Hooman
 */
public class Coordinator {

    private int[] generateArray(int n, int min, int max) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * (max - min) + min);
        }
        return array;
    }

    private long getTime() {
        return System.nanoTime();
    }

    public void experiment(int n, int maxRep) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("KnapsackBB.txt"));
        for (int num = 10; num <= n; num += 10) {
            for (int rep = 0; rep < maxRep; rep++) {
                System.out.println("Testing n= " + num);
                KnapsackDPItr knapsackDPItr = new KnapsackDPItr();
                writer.write(num + ",");
                int[] w = generateArray(num, 1, 10 * num);
                int[] v = generateArray(num, 100, 500);
                long begin = getTime();
                int dpVal = knapsackDPItr.solve(num * 2, w, v, num);
                long finish = getTime();
                writer.write((finish - begin) + ",");
                begin = getTime();
                KnapsackLC lc = new KnapsackLC(w,v,num*2);
                int lcVal = lc.solve();
                finish = getTime();
                writer.write((finish - begin)+",");
                begin = getTime();
                KnapsackFIFO fifo = new KnapsackFIFO(w, v, num*2);
                int fifoVal = fifo.solve();
                finish = getTime();
                writer.write((finish - begin) + ",");
                if (dpVal != lcVal){
                    System.out.println("Error on LC!");
                    System.exit(0);
                }
                if (dpVal != fifoVal){
                    System.out.println("Error on FIFO!");
                    System.exit(0);
                }
                writer.write("\n");
            }
        }
        writer.close();
    }
}
