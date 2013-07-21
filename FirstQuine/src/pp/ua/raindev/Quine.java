package pp.ua.raindev;

/**
 * The simplest quine, implemented in Java.
 *
 * @author raindev
 */
public class Quine {
    private static final String source = "package pp.ua.raindev;\n\n/**\n * My first quine, implemented in Java.\n */\npublic class Quine {\n    private static final String source = \"code\";\n\n    public static void main(String[] args) {\n        System.out.println(source.replace(\"code\", source));\n    }\n\n}\n";

    public static void main(String[] args) {
        System.out.println(source.replaceFirst("code", source.replace("\n", "\\\\n").replace("\"code\"", "\\\\\"code\\\\\"")));
    }

}
