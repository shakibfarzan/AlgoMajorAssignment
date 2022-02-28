package knapsackBB;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Hooman
 */

// Iterative Dynamic Programming for Knapsack 0/1
public class KnapsackDPItr implements Solve
{ 
    public int K[][];
    public int select[][];
    public ArrayList<Integer> answers;
    public int[] weights;
    public int solve(int W, int wt[], int v[], int n) 
    { 
        int i, w; 
        K= new int[n + 1][W + 1]; // the array to solve the problem iteratively
        select = new int[n+1][W+1];
        weights = wt;
        //fill the array
        for (i = 0; i<= n; i++) { 
                for (w = 0; w<= W; w++) { 
                        if (i == 0 || w == 0) 
                                K[i][w] = 0; //basic problems
                        else if (wt[i - 1]<= w)
                            if (v[i - 1] + K[i - 1][w - wt[i - 1]] > K[i - 1][w]){
                                K[i][w] = v[i - 1] + K[i - 1][w - wt[i - 1]];
                                select[i][w]=1;
                            }else{
                                K[i][w] = K[i - 1][w];
                            }
//                                K[i][w] = Math.max(v[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                        else
                                K[i][w] = K[i - 1][w];
                } 
        } 

        return K[n][W];
    }
    public void print()
    {
        System.out.println("Iterative array");   
        for ( int i=0;i< K.length;i++) // static array
        {
            for ( int j=0;j< K[0].length;j++)
            {
                System.out.print(K[i][j]+"\t");
            }
            System.out.println();
        }  
    }

    public void printAnswers(){
        answers = new ArrayList<>();
        int i = select.length - 1;
        int j = select[0].length - 1;
        while (i >= 1 && j >= 1){
            if (select[i][j] != 0) {
                answers.add(i - 1);
                j -= weights[i - 1];
            }
            i--;
        }
        System.out.print(answers);
    }
}    
