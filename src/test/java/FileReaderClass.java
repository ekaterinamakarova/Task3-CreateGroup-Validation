import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderClass {

    private String fileDirectory = "/home/user/Documents/CreateGroupDGROOPSValidation/src/test/java/credentials";

    public String readFromFile(int k) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileDirectory));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return (lines.get(k));

    }

}
