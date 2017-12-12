import java.io.File;
import java.util.*;

public class Demo {

	public static void main(String[] args) {
		run();
	}
	
	public static void run(){
		EntryFactory factory = new EntryFactory();
		HMap<Long, Entry> sizeMap = new HMap<Long, Entry>();
		ArrayList<ArrayList<Entry>> duplicates = new ArrayList<ArrayList<Entry>>();
		
		File folder = new File("/Users/jacobtower/Desktop/Photos/");
		ArrayList<String> fileLocations = scanfolder.listFilesForFolder(folder);
		System.out.println();
		
		for(String name : fileLocations){
			Entry e = factory.buildEntry(name);
			sizeMap.insert(e.getFileSize(), e);
		}

		for(Long key : sizeMap.getKey()){
			if(sizeMap.get(key).size() > 1){
				duplicates.add(sizeMap.get(key));
			}
		}
		
		for(ArrayList<Entry> d : duplicates){
			for(int i = 0; i < d.size(); i++){
				System.out.println(d.get(i).getFileName());
			}
		}
	}
}
