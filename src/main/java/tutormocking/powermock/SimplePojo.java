package tutormocking.powermock;

/**
 * The type Simple pojo.
 * class used in test for show of powermock capabilities
 */
public class SimplePojo {
    private String name;
    private String surName;

    private static int counter = 0;
    private static String versionInfo = "Super verze";

    /**
     * Processing string.
     *
     * @return the string
     */
    public String processing() {
        String s = "to je inkrement " + increment();
        return "String " + s + getInfoAndVersion();
    }

    private String getInfoAndVersion() {
        return "kurna string original " + versionInfo;
    }


    private static int increment() {
        return counter++;
    }
}
