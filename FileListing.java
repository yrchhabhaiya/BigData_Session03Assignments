package assignments3;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class FileListing {
	
	public static void main(String[] args) throws IOException {
		Path path = new Path(args[0]);
		
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
		
		FileListing.Listing(fileSystem, path);
		
	}
	
	
	
	public static void Listing(FileSystem fileSystem, Path path) throws FileNotFoundException, IOException{
		FileStatus[] fileStatus = fileSystem.listStatus(path);
		
		for (FileStatus fStat : fileStatus) {
			if (fStat.isDirectory()) {
				System.out.println("Directory: " + fStat.getPath());
				FileListing.Listing(fileSystem, fStat.getPath());
			}
			else if (fStat.isFile()) {
				System.out.println("File: " + fStat.getPath());
			}
			else if (fStat.isSymlink()) {
				System.out.println("Symlink: " + fStat.getPath());
			}
		}

	}
	
	
}