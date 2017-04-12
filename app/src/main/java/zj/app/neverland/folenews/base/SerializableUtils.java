package zj.app.neverland.folenews.base;

import android.os.Environment;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidParameterException;

import zj.app.neverland.folenews.app.myapp;

public class SerializableUtils {

	private static final String	SERIALIZABLE_FILE_EXT	= ".cache";

	public static void clearAll() {
		File dir = getBasePath();
		if (dir != null) {
			for (File file : dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getParent().endsWith(SERIALIZABLE_FILE_EXT);
				}
			})) {
				file.delete();
			}
		}
	}

	public static synchronized void clear(String key) {
		File dir = getBasePath();
		if (dir != null) {
			File file = getSerialiableFile(dir, key);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	public static boolean exist(String key) {
		checkParamter(key, "key cannot be null");
		File dir = getBasePath();
		if (dir != null) {
			File file = getSerialiableFile(dir, key);
			return file.exists();
		}

		return false;
	}

	public static <T extends Serializable> boolean save(String key, T obj) {
		checkParamter(key, "key cannot be null");

		if (obj == null) {
			return true;
		}

		ObjectOutputStream out = null;
		FileOutputStream fos = null;

		File dir = getBasePath();
		if (dir != null) {
			try {
				clear(key);
				File file = getSerialiableFile(dir, key);
				file.createNewFile();
				fos = new FileOutputStream(file);
				out = new ObjectOutputStream(fos);

				out.writeObject(obj);
				out.flush();

				return true;
			} catch (IOException e) {
				Log.e("SerializableUtils", "Error on saving Serializable object:" + e.getMessage());
			} finally {
				closeStream(fos);
				closeStream(out);
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T load(String key) {
		checkParamter(key, "key cannot be null");

		T obj = null;
		File dir = getBasePath();
		if (dir != null) {
			File file = getSerialiableFile(dir, key);
			if (file.exists()) {
				FileInputStream fis = null;
				ObjectInputStream oin = null;
				try {
					fis = new FileInputStream(file);
					oin = new ObjectInputStream(fis);

					obj = (T) oin.readObject();
				} catch (IOException e) {
					Log.e("SerializableUtils", "Error on load Serializable object:" + e.getMessage());
				} catch (ClassNotFoundException e) {
					obj = null;
				} finally {
					closeStream(fis);
					closeStream(oin);
				}
			}
		}

		return obj;
	}

	private static File getSerialiableFile(File dir, String key) {
		return new File(String.format("%s%s%s%s", dir.getAbsolutePath(), File.separator, key, SERIALIZABLE_FILE_EXT));
	}

	private static File getBasePath() {
		String storageState = Environment.getExternalStorageState();
		File dir = null;
		if (storageState.equals(Environment.MEDIA_MOUNTED) && !storageState.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			dir = new File(String.format("%s%s", myapp.getTempRootDir(), "/objcache"));
			if (!dir.exists()) {
				dir.mkdir();
			}
		} else {
			Log.d("SerializableUtils", "External Storage is not available");
		}

		return dir;
	}

	private static <T> void checkParamter(T param, String errMsg) {
		if (param == null) {
			throw new InvalidParameterException(errMsg);
		}
	}

	private static void closeStream(Closeable s) {
		if (s != null) {
			try {
				s.close();
			} catch (IOException e) {
				Log.e("SerializableUtils", s.getClass().getSimpleName() + " Error On Closing: " + e.getMessage());
			}
		}
	}
}
