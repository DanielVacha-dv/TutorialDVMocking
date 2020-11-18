package tutormocking.powermock;

public class SimplePojo {
    private String name;
    private String surName;

    private static int counter = 0;
    private static String versionInfo = "Super verze";

    public String processing() {
        int increment = increment();
        String s = "to je inkrement" + increment;
        return " String " + s + getInfoAndVersion();
    }

    private String getInfoAndVersion() {
        return "kurna string original " + versionInfo;
    }


    private static int increment() {
        return counter++;
    }
}
