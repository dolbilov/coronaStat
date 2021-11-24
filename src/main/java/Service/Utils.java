package Service;

import logger.Logger;

/**
 * Class with helpful methods
 */
public class Utils {
    /** Private constructor to avoid creating instances*/
    private Utils() {}

    /**
     * Method for capitalize string (first letter if UPPER case and all the other is lower case)
     * @param oldString String that will be capitalized
     * @return Capitalized string
     */
    public static String Capitalize(String oldString)
    {
        Logger.writeDebug("Capitalize method was called");
        return oldString.substring(0,1).toUpperCase() + oldString.substring(1);
    }

    /**
     * Method to union string arrays
     * @param first First string array
     * @param second Second string array
     * @return New string array that is the union of arrays
     */
    public static String[] UnionArrays(String[] first, String[] second)
    {
        String[] result = new String[first.length + second.length];
        int ind = 0;
        for (String s : first) result[ind++] = s;
        for (String s : second) result[ind++] = s;

        Logger.writeDebug("UnionArrays method was called");
        return result;
    }
}
