import java.io.File;  
import java.util.*;
import java.text.SimpleDateFormat;  
import java.io.IOException;
import java.nio.file.*;
public class dateClassifier 
{  
    public static void main(String args[]) 
    {    
        String srcPath = "/Users/yogesshv/Documents/imagesTest/";
        File folder = new File(srcPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".gif") || name.endsWith(".png"));
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        Map<String, Map<String,ArrayList<File>>> years = new HashMap<String,Map<String,ArrayList<File>>>();
        for (File file : listOfFiles) 
        {  
                String month = sdf1.format(file.lastModified());
                String year = sdf2.format(file.lastModified());     
                years.computeIfAbsent(year, it-> new HashMap<>())
                .computeIfAbsent(month, it -> new ArrayList<>())
                .add(file);
        }
        String yearPath= "/Users/yogesshv/Documents/";
        for(String y: years.keySet())
        {
            String path = yearPath+y;
            File f1 = new File(path);   
            boolean bool1 = f1.mkdir(); 
            Map<String,ArrayList<File>> months = years.get(y);
            for(String s: months.keySet())
            {
                String monthPath = path+"//"+s;
                File f2 = new File(monthPath);
                boolean bool2 = f2.mkdir(); 
                String destPath = monthPath;  
                ArrayList<File> monthlyImages = months.get(s);
                for(File fn: monthlyImages)
                {
                    Path sourcepath = Paths.get(fn.getAbsolutePath());
                    String dPath = destPath+"//"+fn.getName();
                    Path destinationepath = Paths.get(dPath);
                    Path result = null;
                    try 
                    {
                        result = Files.move(sourcepath,destinationepath);
                    } 
                    catch (IOException e) 
                    {
                        System.out.println("Exception while moving file: " + e.getMessage());
                    }
                }
            }               
        }
    }  
}  
