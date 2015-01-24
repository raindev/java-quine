package pp.ua.raindev;

import java.util.regex.Matcher;

public class Quine {
    private static final String self = "package pp.ua.raindev;\n" +
            "\n" +
            "import java.util.regex.Matcher;\n" +
            "\n" +
            "public class Quine {\n" +
            "    private static final String self = \"~\";\n" +
            "\n" +
            "    public static void main(String[] args) {\n" +
            "        System.out.print(self.replaceFirst(\"~\", Matcher.quoteReplacement(\n" +
            "                self.replace((char) 34 + \"\", (char) 92 + \"\" + (char) 34)\n" +
            "                        .replace((char) 10 + \"\",\n" +
            "                                (char) 92 + \"n\" + (char) 34 + \" +\"\n" +
            "                                        + (char) 10 + \"            \" + (char) 34))));\n" +
            "    }\n" +
            "\n" +
            "}";

    public static void main(String[] args) {
        System.out.print(self.replaceFirst("~", Matcher.quoteReplacement(
                self.replace((char) 34 + "", (char) 92 + "" + (char) 34)
                        .replace((char) 10 + "",
                                (char) 92 + "n" + (char) 34 + " +"
                                        + (char) 10 + "            " + (char) 34))));
    }

}