# Even Steven
Even Steven is a class I wrote to check the distribution of the output of the [Shuffler](https://github.com/argonium/shuffler) program. This class will probably not be useful to anyone other than myself, but I'm posting it here so I can easily point to it in case someone says the Shuffler output is biased.

The input to the program is multiple rows of output from the Shuffler program; each row is of the format "n n n n n n n" (a series of numbers). If Shuffler is called with a value of 5 (the size of the list), then the output row will be the numbers zero through four in a random order (e.g., "3 1 4 2 0"). So, the Shuffler program should be called many, many times, in order to generate a large sample set for Even Steven.

Even Steven will process each row of input, storing the number of times each digit is stored in each position across all rows of input (it assumes that all rows have the same digits, just stored in a different order). Once the program has added up these totals, then for each number, it will compute the percentage of occurrences for the number in each column. It then computes the delta between the highest percentage and the lowest percentage. This difference should be very close to zero. Running this program against large sets of data (generated using Shuffler) showed that the distribution was nearly even.

The code is released under the MIT license.
