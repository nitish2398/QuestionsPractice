package sample.Problems.DynamicProgramming;

import java.util.*;

/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime , endTime and profit arrays,
you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

https://leetcode.com/problems/maximum-profit-in-job-scheduling/

 */
public class JobScheduling {
    static class Job {
        int startTime;
        int endTime;
        int profit;

        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        static Comparator<Job> jobComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.endTime - o2.endTime;
            }
        };
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = getJobList(startTime, endTime, profit);

        if (jobs.size() == 0) {
            return 0;
        }

        Collections.sort(jobs, Job.jobComparator);

        int[] longestIncreasingSubSeq = new int[profit.length];

        longestIncreasingSubSeq[0] = jobs.get(0).profit;
        int result = jobs.get(0).profit;

        for (int i = 1; i < longestIncreasingSubSeq.length; i++) {
            longestIncreasingSubSeq[i] = Math.max(jobs.get(i).profit, longestIncreasingSubSeq[i - 1]);
            // iterate from i-1 to zero, so that we can break on first larger match
            for (int j = i - 1; j >= 0; j--) {
                if (jobs.get(i).startTime >= jobs.get(j).endTime) {
                    longestIncreasingSubSeq[i] = Math.max(longestIncreasingSubSeq[i],
                            longestIncreasingSubSeq[j] + jobs.get(i).profit);
                    break;
                }
            }
            result = Math.max(result, longestIncreasingSubSeq[i]);
        }

        return result;
    }

    private List<Job> getJobList(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        return jobs;
    }

    public static void main(String[] args) {
        int[] startTime = {1, 2, 3, 4, 6};
        int[] endTime = {3, 5, 10, 6, 9};
        int[] profit = {20, 20, 100, 70, 60};

        // result = 150
        System.out.println(new JobScheduling().jobScheduling(startTime, endTime, profit));
    }
}
