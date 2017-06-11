package assignments3;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class FileListingMultiplePath {
	
	public static void main(String[] args) throws IOException {
		Path path1 = new Path(args[0]);
		Path path2 = new Path(args[1]);
		
		Configuration conf = new Configuration();
		
		FileSystem fileSystem = FileSystem.get(path1.toUri(), conf);
		FileListingMultiplePath.Listing(fileSystem, path1);
		
		fileSystem = FileSystem.get(path2.toUri(), conf);
		FileListingMultiplePath.Listing(fileSystem, path2);
	}
	
	
	
	public static void Listing(FileSystem fileSystem, Path path) throws FileNotFoundException, IOException{
		FileStatus[] fileStatus = fileSystem.listStatus(path);
		
		for (FileStatus fStat : fileStatus) {
			if (fStat.isDirectory()) {
				System.out.println("Directory: " + fStat.getPath());
				FileListingMultiplePath.Listing(fileSystem, fStat.getPath());
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