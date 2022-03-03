import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Greedy Assignment II Template
 */

public class JobSchedulingBlind {
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




