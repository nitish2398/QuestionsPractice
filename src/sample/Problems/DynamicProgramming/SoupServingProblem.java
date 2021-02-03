package sample.Problems.DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/*
There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

Serve 100 ml of soup A and 0 ml of soup B
Serve 75 ml of soup A and 25 ml of soup B
Serve 50 ml of soup A and 50 ml of soup B
Serve 25 ml of soup A and 75 ml of soup B
When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.

Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.

https://leetcode.com/problems/soup-servings/
 */
public class SoupServingProblem {
    static class Solution {
        Map<String, Double> map;

        public double soupServings(int N) {
            if(N > 5000) {
                return 1;
            }

            map = new HashMap<>();
            return probabilityUtil(N, N);
        }

        private double probabilityUtil(int soupA, int soupB) {

            // conditions are order specific
            if (soupA <= 0 && soupB > 0) {
                return 1;
            }
            if (soupA <= 0) {
                return 0.5;
            }
            if (soupB <= 0) {
                return 0;
            }

            if (!map.containsKey(soupA+"$"+soupB)) {
                double x = 0.25 * (probabilityUtil(soupA - 100, soupB) +
                        probabilityUtil(soupA - 75, soupB - 25) +
                        probabilityUtil(soupA - 50, soupB - 50) +
                        probabilityUtil(soupA - 25, soupB - 75));

                map.put(soupA+"$"+soupB, x);
            }

            return map.get(soupA+"$"+soupB);
        }

        public static void main(String[] args) {
            System.out.println(new Solution().soupServings(660295675));
        }
    }
}
