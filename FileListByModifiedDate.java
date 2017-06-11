package assignments3;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileStatus;

public class FileListByModifiedDate {
	
	public static void main(String[] args) throws IOException {
		Path path = new Path(args[0]);
		
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
		
		long start_ts = 0;
		long end_ts = Long.MAX_VALUE - 1;
		
		
		
		FileListByModifiedDate.Listing(fileSystem, path, start_ts, end_ts);
		
	}
	
	
	
	public static void Listing(FileSystem fileSystem, Path path, long start_ts, long end_ts) throws FileNotFoundException, IOException{
		
		
		FileStatus[] fileStatus = fileSystem.listStatus(path);
		
		for (FileStatus fStat : fileStatus) {
			if (fStat.isDirectory() && (fStat.getModificationTime() > start_ts) && (fStat.getModificationTime() < end_ts)) {
				System.out.println("Directory: " + fStat.getPath() + "\t" + fStat.getModificationTime());
				FileListByModifiedDate.Listing(fileSystem, fStat.getPath(), start_ts, end_ts);
			}
			else if (fStat.isFile() && (fStat.getModificationTime() > start_ts) && (fStat.getModificationTime() < end_ts)) {
				System.out.println("File: " + fStat.getPath() + "\t" + fStat.getModificationTime());
			}
			else if (fStat.isSymlink()&& (fStat.getModificationTime() > start_ts) && (fStat.getModificationTime() < end_ts)) {
				System.out.println("Symlink: " + fStat.getPath() + "\t" + fStat.getModificationTime());
			}
		}

	}
	
	
}