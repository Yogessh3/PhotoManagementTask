import java.io.File;  
import java.util.*;
import java.text.SimpleDateFormat;  
import java.io.IOException;
import java.nio.file.*;
public class FolderUpdate {  
    public static void main(String args[]) {    
        String srcPath = "/Users/yogesshv/Documents/imagesTest/";
        File folder = new File(srcPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".gif") || name.endsWith(".png"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        Map<String, Map<String,ArrayList<File>>> years = new HashMap<String,Map<String,ArrayList<File>>>();
        for (File file : listOfFiles) {  
            String month = sdf1.format(file.lastModified());
            String year = sdf2.format(file.lastModified());    
            years.computeIfAbsent(year, it-> new HashMap<>())
            .computeIfAbsent(month, it -> new ArrayList<>())
            .add(file);
        }
        String yearPath = "/Users/yogesshv/Documents/";
        years.forEach((year,months) ->  {
        new File(yearPath+year).mkdir();
        months.forEach((month,images) -> {
        new File(yearPath+year+"//"+month).mkdir();
        images.forEach((image) -> {
        Path sourcepath = Paths.get(image.getAbsolutePath());
        Path destinationpath = Paths.get(yearPath+year+"//"+month+"//"+image.getName());
        try {
            Files.move(sourcepath,destinationpath);
        } 
        catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }});});});
    }
}
