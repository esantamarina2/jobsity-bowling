import parser.FileParser;

public class Application {

    public static void main(String[] args) throws Exception {
        FileParser fileParser = new FileParser();
        fileParser.process();
    }
}