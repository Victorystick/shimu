package vs.shimu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveHandeler {
	public void save(Savefile sfile, File file) throws FileNotFoundException, IOException {
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
		oout.writeObject(sfile);
	}
	
	public File getFile(boolean isload) {
		return null;
	}
	
	public Savefile load(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file));
		Object o = oIn.readObject();
		
		return (o instanceof Savefile) ? (Savefile) o : null;
	}
}
