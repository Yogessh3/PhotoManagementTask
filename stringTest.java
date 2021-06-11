import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
public class stringTest {

   final static String SAVE_PATH = "/Users/yogesshv/Documents/imagesTest/";
   static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy/MMM/MMM-dd-yyyy");

   public static String searchDirForFile(String dir, String fileName, ArrayList results) {
      File[] files = new File(dir).listFiles();
      for(File f:files) {
          if(f.isDirectory()) {
              String loc = searchDirForFile(f.getPath(), fileName, results);
              if(loc != null)
                  return loc;
          }
          if(f.getName().toLowerCase().contains(fileName))
          {
              results.add(f.getPath());
          }
      }
      return null;
   }
   public static String searchDirForYear(String dir, String year, ArrayList yearResults) {
      File[] filesObj = new File(dir).listFiles();
      for(File f:filesObj) {
          if(f.isDirectory()) {
              String loc = searchDirForFile(f.getPath(), year, yearResults);
              if(loc != null)
                  return loc;
          }
          if(sdfMonth.format(f.lastModified()).contains(year))
          {
              yearResults.add(f.getPath());
          }
      }
      return null;
   }

   public static void main(String args[]) {
      Scanner scan = new Scanner(System.in);
      File[] listOfFiles = new File(SAVE_PATH).listFiles((dir, name) -> name.endsWith(".jpeg") || name.endsWith(".jpg") || name.endsWith(".gif") || name.endsWith(".png"));
      if (Objects.nonNull(listOfFiles)) {
         Arrays.stream(listOfFiles)
                  .filter(file -> file.renameTo(new File(checkAndCreateDir(SAVE_PATH+sdfMonth.format(file.lastModified())), file.getName())))
                  .map(File::getName).forEach(System.err::println);
      }
      System.out.println("1.Enter a file Name: \n2.Enter an extension(.jpg,.gif,...): \n3.Enter an year:");
      int choice = scan.nextInt();
      switch(choice) 
      {
         case 1:
               String fileName = scan.next().toLowerCase();
               ArrayList<String> nameResults = new ArrayList<>();
               searchDirForFile(SAVE_PATH,fileName,nameResults);
               if(nameResults.size()==0)
               {
                  System.out.println("Nothing Found");
               }
               else{
                  nameResults.forEach(System.out::println);
               }
               break;
               
         case 2:
               String extension = scan.next().toLowerCase();
               ArrayList<String> extensionResults = new ArrayList<>();
               searchDirForFile(SAVE_PATH,extension,extensionResults);
               if(extensionResults.size()==0)
               {
                  System.out.println("Nothing Found");
               }
               else{
                  extensionResults.forEach(System.out::println);
               }
               break;
         case 3:
               String year = scan.next();
               ArrayList<String> yearResults = new ArrayList<>();
               searchDirForYear(SAVE_PATH,year,yearResults);
               if(yearResults.size()==0)
               {
                  System.out.println("Nothing Found");
               }
               else{
                  yearResults.forEach(System.out::println);
               }
               break;
         default:
               System.out.println("Wrong Choice");
               break;
         }
   }
   private static String checkAndCreateDir(String path) {
      final File file = new File(path);
      if (!file.exists()) {
         return file.mkdirs() ? path : path;
      }
      return path;
 }
}


