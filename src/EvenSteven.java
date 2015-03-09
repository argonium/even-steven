import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * This class was written to verify the distribution of the random
 * numbers in the Shuffler project.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class EvenSteven
{
  /**
   * Default constructor.
   */
  private EvenSteven()
  {
    super();
  }
  
  /**
   * The current row.
   */
  private static int nCurrentRow = 0;
  
  /**
   * The number of columns.
   */
  private static int nNumColumns = -1;
  
  /**
   * A two-dimensional array used to store the number of occurrences
   * of all numbers.
   */
  private static int[][] allCounts = null;
  
  /**
   * The maximum difference, as a string.
   */
  private static String sMaxString = null;
  
  /**
   * The maximum difference, as a floating point number.
   */
  private static float fMaxDiff = 0.0f;
  
  
  /**
   * Writes a string to standard output.
   * 
   * @param msg the string to write
   */
  private static void write(final String msg)
  {
    System.out.println(msg);
  }
  
  
  /**
   * Writes an array to standard output.
   * 
   * @param data the data to write
   */
  private static void writeArray(final int[] data)
  {
    if (data == null)
    {
      write("The input array is null");
    }
    else
    {
      final int nSize = data.length;
      if (nSize == 0)
      {
        write("The input array is empty");
      }
      else
      {
        StringBuffer buf = new StringBuffer(100);
        for (int i = 0; i < nSize; ++i)
        {
          buf.append(Integer.toString(data[i]));
          buf.append(" ");
        }
        write(buf.toString());
      }
    }
  }
  
  
  /**
   * Parse a line of input.
   * 
   * @param str the input string to parse
   * @return the string as a list of words
   */
  private static String[] parseLine(final String str)
  {
    ++nCurrentRow;
    
    // Split the input string and return it
    String[] parts = str.trim().split(" ");
    return parts;
  }
  
  
  /**
   * Process the line.
   * 
   * @param parts the list of words in the line
   */
  private static void processLine(final String[] parts)
  {
    // If this is the first row, initialize the global variables
    if (nCurrentRow == 1)
    {
      nNumColumns = parts.length;
      
      allCounts = new int[nNumColumns][nNumColumns];
      for (int i = 0; i < nNumColumns; ++i)
      {
        Arrays.fill(allCounts[i], 0);
      }
    }
    
    // Update the counts for the values in this line
    for (int i = 0; i < parts.length; ++i)
    {
      // Use the token as an index into the array
      int j = Integer.parseInt(parts[i]);
      
      // Update the value
      allCounts[j][i]++;
    }
  }
  
  
  /**
   * Process all of the data.
   */
  private static void processData()
  {
    // Go through each input token
    for (int i = 0; i < nNumColumns; ++i)
    {
      int nMinOccs = 0;
      int nMaxOccs = 0;
      
      // Go through the number of occurrences
      for (int j = 0; j < nNumColumns; ++j)
      {
        int nValue = allCounts[i][j];
        if (j == 0)
        {
          nMaxOccs = nValue;
          nMinOccs = nValue;
        }
        else
        {
          nMaxOccs = Math.max(nValue, nMaxOccs);
          nMinOccs = Math.min(nValue, nMinOccs);
        }
      }
      
      int nDiff = nMaxOccs - nMinOccs;
      float fDiff = ((float) (nDiff * 100.0f)) / ((float) nCurrentRow);
      
      write("The range for " + i + " is " + fDiff + "%");
      
      if (i == 0)
      {
        sMaxString = "0";
        fMaxDiff = fDiff;
      }
      else
      {
        if (fDiff > fMaxDiff)
        {
          sMaxString = Integer.toString(i);
          fMaxDiff = fDiff;
        }
      }
    }
    
    write("");
    write("The biggest difference was " + fMaxDiff + "% for " + sMaxString);
  }
  
  
  /**
   * Reads the input from standard in.
   */
  private static void readFromStdIn()
  {
    try
    {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String str = "";
      while (str != null)
      {
        str = in.readLine();
        if (str != null)
        {
          processLine(parseLine(str));
        }
      }
    }
    catch (IOException e)
    {
      write(e.getMessage());
    }
  }
  
  
  /**
   * Reads the input from a file.
   * 
   * @param filename the input filename
   */
  private static void readFromFile(final String filename)
  {
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(filename));
      String str = "";
      while (str != null)
      {
        str = in.readLine();
        if (str != null)
        {
          processLine(parseLine(str));
        }
      }
    }
    catch (IOException e)
    {
      write(e.getMessage());
    }
  }
  
  
  /**
   * Writes out the usage for the program.
   */
  private static void writeUsage()
  {
    write("Usage: EvenSteven [<input filename>]");
  }
  
  
  /**
   * Main entry point for the application.
   * 
   * @param args arguments supplied by the user
   */
  public static void main(final String[] args)
  {
    // Check for a file name
    if (args.length == 1)
    {
      readFromFile(args[0]);
    }
    else if (args.length == 0)
    {
      readFromStdIn();
    }
    else
    {
      writeUsage();
      System.exit(0);
    }
    
    /*
    final int nSize = allCounts.length;
    for (int i = 0; i < nSize; ++i)
    {
      writeArray(allCounts[i]);
    }
    */
    
    processData();
  }
}
