package Service;

public class Utils {
    private Utils() {}

    public static String Capitalize(String oldString)
    {
        return oldString.substring(0,1).toUpperCase() + oldString.substring(1);
    }

    public static String[] UnionArrays(String[] first, String[] second)
    {
        String[] result = new String[first.length + second.length];
        int ind = 0;
        for (String s : first) result[ind++] = s;
        for (String s : second) result[ind++] = s;

        return result;
    }
}
