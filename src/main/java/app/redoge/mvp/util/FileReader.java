package app.redoge.mvp.util;



import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    /**
     * Reads the contents of a file at a given file path.
     *
     * @param path the file path to read
     * @return a list of strings, where each string represents a line in the file
     * @throws IOException if an error occurs while reading the file
     */
    protected static List<String> readFile(String path) throws IOException {
        File file = new File(path);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        return Arrays.stream(IOUtils.toString(reader).split("\n")).collect(Collectors.toList());
    }
}
