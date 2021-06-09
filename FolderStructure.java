import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class FolderStructure
{
    public static void main(String[] args) {
        File folder = new File("imagesTest");
		File[] listOfFiles = folder.listFiles();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy");
        ArrayList<String> photoDates = new ArrayList<>();
		Map<String, Map<String,ArrayList<String>>> years = new HashMap<String,Map<String,ArrayList<String>>>();
        int startIdx=0;
        for (File file : listOfFiles) 
        {
            if (file.isFile()) 
            {
                photoDates.add(sdf.format(file.lastModified()));
            }
        }
        for(int j=0; j<photoDates.size();j++)
        {
            String[] arrList = photoDates.get(j).split("-");
            System.out.println(arrList[0]+" "+arrList[1]+" "+arrList[2]);
            String monthText = arrList[0];
            System.out.println(arrList[2]+" "+years.containsKey(arrList[2]));
            if(years.containsKey(arrList[2]))
            {
                if(years.get(arrList[2]).containsKey(monthText))
                {
                    years.get(arrList[2]).get(monthText).add(listOfFiles[startIdx].getName());   
                }
                else
                {
                    ArrayList<String> Images = new ArrayList<>();
                    Images.add(listOfFiles[startIdx].getName());
                    years.get(arrList[2]).put(monthText,Images);   
                }        
            }
            else
            {
                ArrayList<String> Images = new ArrayList<>();
                years.put(arrList[2],new HashMap<String, ArrayList<String>>());
                Images.add(listOfFiles[startIdx].getName());
                years.get(arrList[2]).put(monthText,Images);
            }      
            startIdx+=1;
        } 
        System.out.println(years);
	}
}
