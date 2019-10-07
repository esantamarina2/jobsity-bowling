import org.apache.log4j.Logger;
import parser.FileParser;
import org.apache.commons.lang.StringUtils;

import java.io.File;

public class Application {

    protected static Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        if ((args.length > 0) && (StringUtils.isNotBlank(args[0]))) {
            FileParser fileParser = new FileParser();
            File file = new File(args[0]);
            fileParser.setFile(file);
            fileParser.process();
        }
        else {
            log.error("Missing file name");
        }
    }
}