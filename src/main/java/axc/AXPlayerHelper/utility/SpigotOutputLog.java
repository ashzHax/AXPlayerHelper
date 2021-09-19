package axc.AXPlayerHelper.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpigotOutputLog  extends AbstractAppender{


    private static final org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();

    // other imports that you need here



    // your variables

        public SpigotOutputLog() {
            // do your calculations here before starting to capture
            super("MyLogAppender", null, null);
            start();
        }

        @Override
        public void append(LogEvent event) {


            SimpleDateFormat formatter;
//you might want another format
//visit this link for the opportunities: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
            formatter = new SimpleDateFormat("HH:mm:ss");

            // if you don`t make it immutable, than you may have some unexpected behaviours
            LogEvent log = event.toImmutable();

            // you can get only the log message like this:
            String message = log.getMessage().getFormattedMessage();
            try {
                Files.write(Paths.get("C:\\myfile.txt"), message.getBytes(), StandardOpenOption.APPEND);
            } catch (NoSuchFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                //exception handling left as an exercise for the reader
            }


            // and you can construct your whole log message like this:
            message = "[" +formatter.format(new Date(event.getTimeMillis())) + " " + event.getLevel().toString() + "] " + message;
        }
}