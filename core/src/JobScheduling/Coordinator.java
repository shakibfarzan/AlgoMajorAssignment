package JobScheduling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Coordinator {

    private int[] generateArray(int n,int min,int max)
    {
        int [] array=new int[n];
        for ( int i=0;i<n;i++)
        {
            array[i]=(int)(Math.random()*(max-min)+min);
        }
        return array;
    }
    private long getTime()
    {
        return System.nanoTime();
    }
    public void experiment(int n,int maxRep) throws IOException
    {
        BufferedWriter writer=new BufferedWriter(new FileWriter("JSOutput.txt"));
        for (int num=4;num<=n;num+=2) // all knapsack sizes
        {
            for (int rep=0;rep<maxRep;rep++)
            {
                System.out.println("Testing n= "+num);
                writer.write(num+",");
                int[] profits=generateArray(num, 1, 30);
                int[] deadlines=generateArray(num, 15, 30);
                int[] executionTimes = generateArray(num, 1, 10);

                JobScheduling1 js1 = new JobScheduling1(profits, deadlines, executionTimes);
                JobScheduling2 js2 = new JobScheduling2(profits, deadlines, executionTimes);
                JobScheduling3 js3 = new JobScheduling3(profits, deadlines, executionTimes);

                long begin=getTime();
                js1.solve();
                long finish=getTime();
                writer.write((finish-begin)+",");
                begin=getTime();
                js2.solve();
                finish=getTime();
                writer.write((finish-begin)+",");
                begin=getTime();
                js3.solve();
                finish=getTime();
                writer.write((finish-begin)+",");
                begin=getTime();
                int blindVal = JobSchedulingWithProfitsAndDeadlines.JSBlindIterative(deadlines,profits,executionTimes, new ArrayList<>());
                finish=getTime();
                writer.write((finish-begin)+",");
                begin=getTime();
                JobSchedulingWithProfitsAndDeadlines.JSGreedy(deadlines,profits,executionTimes,new ArrayList<>());
                finish=getTime();
                writer.write((finish-begin)+",");
                if (js1.finalProfit != blindVal) System.out.println("Error on JobScheduling1");
                if (js2.finalProfit != blindVal) System.out.println("Error on JobScheduling2");
                if (js3.finalProfit != blindVal) System.out.println("Error on JobScheduling3");
                writer.write("\n");
            }
        }
        writer.close();
    }
}
