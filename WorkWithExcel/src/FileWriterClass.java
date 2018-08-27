import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FileWriterClass {
	
	private BufferedWriter buffer;
	
	public FileWriterClass(String fileToWrite) throws IOException {
		FileWriter writer = new FileWriter(fileToWrite);
		this.buffer = new BufferedWriter(writer);
	}

	public BufferedWriter getBuffer() {
		return buffer;
	}

//	Takes a List<Student> and write all Student objects to a file
	public void writeToFile(List<Student> studentList) throws IOException {
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) {
			Student stud = iter.next();
			this.buffer.write(stud.getName()+"\t\t"+stud.getRoll()+"\t\t"+stud.getStudClass()+
					"\t\t"+stud.getGrade());
			this.buffer.newLine();
		}		
	}

//	Closes the buffer
	public void closeBuffer() throws IOException {
		this.buffer.close();
	}
	
}