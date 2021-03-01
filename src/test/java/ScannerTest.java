import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


public class ScannerTest {

        private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() throws Exception {
        outputStream.close();
    }


    @Test
    public void output() throws Exception {
        File inputFile = openFile("src/test/resources/sample.in");

        String[] argv = new String[] {inputFile.getPath()};
        Subst.main(argv);

        // test actual is expected
        File expected = openFile("src/test/resources/sample.expected");


        BufferedReader actualContent = readOutputStream();
        BufferedReader expectedContent = new BufferedReader(new FileReader(expected));

        for (int lineNumber = 1; lineNumber != -1; lineNumber++) {
            String expectedLine = expectedContent.readLine();
            String actualLine = actualContent.readLine();
            assertThat(actualLine).isEqualTo(expectedLine);
           // assertWithMessage("Line " + lineNumber).that(actualLine).isEqualTo(expectedLine);
            if (expectedLine == null) lineNumber = -2; // EOF
        }
    }

    private BufferedReader readOutputStream() {
        byte[] rawOutput = outputStream.toByteArray();
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(rawOutput)));
    }


    private File openFile(String path) throws IOException {
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException(path);
        }
        return file;
    }
}
