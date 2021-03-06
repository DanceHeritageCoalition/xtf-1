package org.apache.lucene.util;

/*
 * Copyright 2006-2007 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.BitSet;

/**
 * Utlity class for finding prime numbers (useful for making hash tables).
 *
 * @author Martin Haye
 */
public class Prime 
{
  /**
   * Determines a prime number greater than n, but not too much greater.
   * Useful for sizing a hash table so that modulo arithmetic produces
   * good results.
   */
  public static int findAfter(int n) 
  {
    assert n < primeList[primeList.length - 1] : "n out of range";

    int i = 0;
    while (i < primeList.length && primeList[i] <= n)
      i++;
    return primeList[i];
  } // findAfter

  /**
   * Makes a bit set of primes below n. The returned set will have a clear
   * bit at each location (prime/2).
   */
  private static BitSet makePrimeSet(final int n) 
  {
    BitSet bits = new BitSet(n);
    for (int i = 2; i <= n / 2; i++) {
      if (bits.get(i))
        continue;
      for (int j = i * 2; j < n && j > 0; j += i)
        bits.set(j);
    }

    return bits;
  }

  /** Print a list of primes 1..n, evenly spaced at intervals v */
  public static void printPrimeList(final int n, final float v) 
  {
    assert v > 1.0f : "Invalid interval";
    BitSet bits = makePrimeSet(n);
    int lc = 6;
    for (int i = 1; i < n && i > 0; i = (int)((i + 1) * v)) 
    {
      while (i < n && i > 0 && bits.get(i))
        i++;
      if (i < n && i > 0) 
      {
        System.out.print(", ");
        if (lc == 6) {
          System.out.print("\n");
          lc = 0;
        }
        lc++;
        System.out.print(i);
      }
    }
  }

  /**
   * A handy quick-reference table of primes 1 .. 2^31, spaced evenly in a
   * logarithmic sense. This was generated by calling:
   * printPrimeList(Integer.MAX_VALUE, 1.1f)
   */
  private static int[] primeList = {
                                     1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 37,
                                     41, 47, 53, 59, 67, 79, 89, 101, 113, 127,
                                     149, 167, 191, 211, 233, 257, 283, 313, 347,
                                     383, 431, 479, 541, 599, 661, 733, 809, 907,
                                     1009, 1117, 1229, 1361, 1499, 1657, 1823,
                                     2011, 2213, 2437, 2683, 2953, 3251, 3581,
                                     3943, 4339, 4783, 5273, 5801, 6389, 7039,
                                     7753, 8537, 9391, 10331, 11369, 12511,
                                     13763, 15149, 16673, 18341, 20177, 22229,
                                     24469, 26921, 29629, 32603, 35869, 39461,
                                     43411, 47777, 52561, 57829, 63617, 69991,
                                     76991, 84691, 93169, 102497, 112757, 124067,
                                     136481, 150131, 165161, 181693, 199873,
                                     219871, 241861, 266051, 292661, 321947,
                                     354143, 389561, 428531, 471389, 518533,
                                     570389, 627433, 690187, 759223, 835207,
                                     918733, 1010617, 1111687, 1222889, 1345207,
                                     1479733, 1627723, 1790501, 1969567, 2166529,
                                     2383219, 2621551, 2883733, 3172123, 3489347,
                                     3838283, 4222117, 4644347, 5108813, 5619701,
                                     6181699, 6799889, 7479887, 8227883, 9050687,
                                     9955783, 10951363, 12046553, 13251233,
                                     14576381, 16034021, 17637437, 19401197,
                                     21341339, 23475481, 25823047, 28405367,
                                     31245911, 34370527, 37807591, 41588377,
                                     45747223, 50321981, 55354217, 60889649,
                                     66978673, 73676563, 81044231, 89148671,
                                     98063549, 107869939, 118656953, 130522661,
                                     143574929, 157932449, 173725751, 191098351,
                                     210208199, 231229043, 254351957, 279787171,
                                     307765891, 338542499, 372396793, 409636483,
                                     450600131, 495660161, 545226203, 599748823,
                                     659723717, 725696131, 798265729, 878092289,
                                     965901569, 1062491797, 1168741003,
                                     1285615151, 1414176653, 1555594373,
                                     1711153849, 1882269197, 2070496163
                                   };
} // class Prime
