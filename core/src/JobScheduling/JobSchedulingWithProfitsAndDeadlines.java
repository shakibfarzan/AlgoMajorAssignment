package JobScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Greedy Assignment II Template
 */

public class JobSchedulingWithProfitsAndDeadlines {

    private static boolean isValid(ArrayList<Integer> answer, int[][] data, int[] deadlines, int index, int[] executionTimes) {
        if (data[index][1]<data[index][3]) return false;
        int ind = 0;
        boolean isAdded = false;
        for (int j = 0; j < answer.size(); j++) {
            if (data[index][1] <= deadlines[answer.get(j)]) {
                answer.add(j, data[index][0]);
                ind = j;
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            answer.add(data[index][0]);
            ind = answer.size()-1;
        }
        int totalExecutionTime = 0;
        boolean isValid = true;
        for (Integer integer : answer) {
            totalExecutionTime += executionTimes[integer];
            if (totalExecutionTime > deadlines[integer]) {
                isValid = false;
                answer.remove(ind);
                break;
            }
        }
        return isValid;
    }


    public static int JSGreedy(int[] deadlines, int[] profits, int[] executionTimes, ArrayList<Integer> answer) // Greedy fast job schedualing arbitary execution time . Approximation method
    {
        //This method can call another private method. return is the total profit and answer is the subset returned as call by reference
        int[][] data = new int[deadlines.length][4];
        for (int i = 0; i < deadlines.length; i++) {
            data[i][0] = i;
            data[i][1] = deadlines[i];
            data[i][2] = profits[i];
            data[i][3] = executionTimes[i];
        }
        return forGreedy(deadlines, answer, data, executionTimes);
    }

    private static int forGreedy(int[] deadlines, ArrayList<Integer> answer, int[][] data, int[] executionTimes) {
        Arrays.sort(data, Comparator.comparingInt(a -> -a[2]));
        answer.add(data[0][0]);
        int totalProfit = data[0][2];
        for (int i = 1; i < deadlines.length; i++) {
            if (isValid(answer, data, deadlines, i, executionTimes)) {
                totalProfit += data[i][2];
            }
        }
        return totalProfit;
    }

    public static int JSBlindIterative(int[] deadlines, int[] profits, int[] executionTimes, ArrayList<Integer> answer) // The same as iterative blind method with the same template /pattern by using bitstream
    {
        //This method can call another private method. return is the total profit and answer is the subset returned as call by reference
        // Use bitstring the same as lecture code subset, sum of subset, and knapsack implementations
        int maxProfit = -1;
        long bitString = 0;
        long one = 1;
        do {
            ArrayList<Integer> selectedJob = new ArrayList<>();
            long bitStringCopy = bitString;
            int value = 0;
            for (int i = 0; i < deadlines.length; i++) {
                if ((bitStringCopy & one) == one) {
                    selectedJob.add(i);
                    value += profits[i];
                }
                bitStringCopy = bitStringCopy >> 1;
            }
            if (isValid(selectedJob, deadlines, executionTimes)) {
                if (value > maxProfit) {
                    maxProfit = value;
                    answer.clear();
                    answer.addAll(selectedJob);
                }
            }
            bitString++;
        } while (bitString < Math.pow(2, deadlines.length));
        return maxProfit;
    }

    private static boolean isValid(ArrayList<Integer> selectedJob, int[] deadlines, int[] executionTimes) {
        selectedJob.sort(Comparator.comparingInt(j -> deadlines[j]));
        int totalExecutionTime = 0;
        boolean isValid = true;
        for (Integer integer : selectedJob) {
            totalExecutionTime += executionTimes[integer];
            if (totalExecutionTime > deadlines[integer]) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}




