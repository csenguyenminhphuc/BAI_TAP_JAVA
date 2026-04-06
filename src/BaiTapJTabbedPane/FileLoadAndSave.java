package BaiTapJTabbedPane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileLoadAndSave {
	
	public static boolean saveFile(Object obj, String path) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(obj);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			 e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Object loadFile(String path) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			Object obj = ois.readObject();
			ois.close();
			return obj;
		} catch (Exception e) {
			return null;
		}
	}
}
