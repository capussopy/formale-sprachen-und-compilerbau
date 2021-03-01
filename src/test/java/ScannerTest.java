import org.junit.Test;



public class ScannerTest {

    //    private ByteArrayOutputStream outputStream;
//
//    @Before
//    public void setUp() {
//        outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        outputStream.close();
//    }
//
//
    @Test
    public void output() throws Exception {
        File inputFile = openFile("src/test/data/sample.in");
//        assertThat(inputFile.isFile()).isTrue();
//
        String[] argv = new String[] {inputFile.getPath()};
//
        //Subst.main(argv);
//
//        // test actual is expected
//        File expected = openFile("src/test/data/sample.expected");
//        assertThat(expected.isFile()).isTrue();
//
//        BufferedReader actualContent = readOutputStream();
//        BufferedReader expectedContent = new BufferedReader(new FileReader(expected));
//
//        for (int lineNumber = 1; lineNumber != -1; lineNumber++) {
//            String expectedLine = expectedContent.readLine();
//            String actualLine = actualContent.readLine();
//            assertWithMessage("Line " + lineNumber).that(actualLine).isEqualTo(expectedLine);
//            if (expectedLine == null) lineNumber = -2; // EOF
//        }
    }
    //
//    private BufferedReader readOutputStream() {
//        byte[] rawOutput = outputStream.toByteArray();
//        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(rawOutput)));
//    }
//
//    /**
//     * Opens the given file.
//     *
//     * <p>This method also works around a build difficulty:
//     *
//     * <ul>
//     *   <li>Maven uses the directory that contains {@code pom.xml} as a working directory, i.e.
//     *       {@code examples/simple}
//     *   <li>ant uses the directory that contains {@code build.xml} as a working directory, i.e.
//     *       {@code examples/simple}
//     *   <li>bazel uses the directory that contains {@code WORKSPACE} as a working directory, i.e.
//     *       {@code __main__} in <em>runfiles</em>.
//     * </ul>
//     */
    private File openFile(String pathName) throws IOException {
        String path = pathName;
        File pwd = new File(".").getCanonicalFile();
        assertThat(pwd.isDirectory()).isTrue();
        if (Objects.equals(pwd.getName(), "__main__")) {
            path = "jflex/examples/simple/" + path;
        }
        File file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException(path);
        }
        return file;
    }
}
