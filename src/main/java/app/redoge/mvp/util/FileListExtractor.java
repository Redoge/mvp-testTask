package app.redoge.mvp.util;


import java.io.File;
import app.redoge.mvp.exceptions.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static app.redoge.mvp.util.PropertiesReader.getPathFromProperties;


public class FileListExtractor{
    private static List<String>  getAllFilesPathByFileType(String fileType, String path) throws FileNotFoundException {
        Pattern pattern = Pattern.compile(String.format("^.*\\.(%s)$",fileType));
        final List<String> list;
        if(path == null || path.length() == 0){
            list = FileListExtractor.getResources(pattern);
        }else{
            list = FileListExtractor.getResourcesFromDirectory(new File(path), pattern);
        }
        if(list.isEmpty()) throw new FileNotFoundException(fileType.toUpperCase()+"s file not found in directory -> " + path);
        return list;
    }
    /**
     * Gets the file paths of all CSV files in the default directory specified in the properties file.
     *
     * @return a list of file paths of all CSV files in the default directory
     * @throws FileNotFoundException if the default directory specified in the properties file does not exist
     */
    public static List<String> getAllCsvFilesByPath() throws FileNotFoundException {
        String path = getPathFromProperties();
        return getAllFilesPathByFileType("csv", path);
    }
    /**
     * Gets the file paths of all CSV files in a specified directory.
     *
     * @param path the directory to search for CSV files
     * @return a list of file paths of all CSV files in the specified directory
     * @throws FileNotFoundException if the specified directory does not exist
     */
    public static List<String> getAllCsvFilesByPath(String path) throws FileNotFoundException {
        return getAllFilesPathByFileType("csv", path);
    }


    /**
     * for all elements of java.class.path get a List of resources Pattern
     *
     * @param pattern
     *            the pattern to match
     * @return the resources in the order they are found
     */
    private static List<String> getResources(
            final Pattern pattern) throws FileNotFoundException {
        final ArrayList<String> retval = new ArrayList<>();
        final String classPath = System.getProperty("java.class.path", ".");
        final String[] classPathElements = classPath.split(System.getProperty("path.separator"));
        for(final String element : classPathElements){
            retval.addAll(getResources(element, pattern));
        }
        if(retval.isEmpty()) throw new FileNotFoundException("CSVs file not found in ClassPath directory");

        return retval;
    }

    private static List<String> getResources(
            final String element,
            final Pattern pattern){
        final ArrayList<String> retval = new ArrayList<>();
        final File file = new File(element);
        if(file.isDirectory()){
            retval.addAll(getResourcesFromDirectory(file, pattern));
        }
        return retval;
    }


    private static List<String> getResourcesFromDirectory(
            final File directory,
            final Pattern pattern){
        final ArrayList<String> retval = new ArrayList<>();
        final File[] fileList = directory.listFiles();
        if(fileList==null) return Collections.emptyList();
        for(final File file : fileList){
            if(file.isDirectory()){
                retval.addAll(getResourcesFromDirectory(file, pattern));
            } else{
                try{
                    final String fileName = file.getCanonicalPath();
                    final boolean accept = pattern.matcher(fileName).matches();
                    if(accept){
                        retval.add(fileName);
                    }
                } catch(final IOException e){
                    throw new Error(e);
                }
            }
        }
        return retval;
    }

}