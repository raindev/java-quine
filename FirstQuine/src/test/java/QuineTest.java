package pp.ua.raindev;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

/**
 * Tests quine correctness.
 */
public class QuineTest {
    private static final String rootPath = "./src/main/java/";
    private static final String sourcePath = "pp/ua/raindev/Quine.java";
    private static final String fullPath = rootPath + sourcePath;
    private static String sourceContent;
    private static String output;

    String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println(new File(".").getAbsoluteFile());
        sourceContent = readFile(fullPath, Charset.forName("utf8"));

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        javac.run(null, null, null, fullPath);
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(rootPath).toURI().toURL()});
        Class<?> cls = Class.forName(sourcePath.replace("/", ".").replace(".java", ""), true, classLoader);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        cls.getMethod("main", String[].class).invoke(null, new Object[]{new String[]{}});
        output = baos.toString("utf8");
    }


    @Test
    public void run() {
        assertEquals(output, sourceContent, "Quine output should be equal to its source");
    }
}