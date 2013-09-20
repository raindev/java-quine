package pp.ua.raindev;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

/**
 * Tests quine correctness from source code.
 * Read source code, compile source, run {@code main()} capturing output, compare output with the source.
 */
public class QuineTest {
    private static final String rootPath = "./src/main/java/";
    private static final String sourcePath = "pp/ua/raindev/Quine.java";
    private static final String fullPath = rootPath + sourcePath;
    private String source;
    private String output;

    @BeforeMethod
    public void setUp() throws Exception {
        //Read source code
        source = Charset.forName("utf8")
                .decode(ByteBuffer.wrap(Files.readAllBytes(Paths.get(fullPath))))
                .toString();

        //Compile source
        ToolProvider.getSystemJavaCompiler().run(null, null, null, fullPath);

        //Redirect stdout to capturing stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        //Load quine's class and execute main method
        Class.forName(
                sourcePath.replace("/", ".").replace(".java", ""),
                true,
                URLClassLoader.newInstance(new URL[]{new File(rootPath).toURI().toURL()})
        ).getMethod("main", String[].class).invoke(null, new Object[]{new String[]{}});
        output = baos.toString("utf8");
    }


    @Test
    public void run() {
        assertEquals(output, source, "Quine output should be equal to its source");
    }
}