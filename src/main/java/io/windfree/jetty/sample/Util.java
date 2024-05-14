package io.windfree.jetty.sample;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
	 public static byte[] readAll(File file) throws IOException {
	        FileInputStream in = null;
	        try {
	            in = new FileInputStream(file);
	            return readAll(in);
	        } catch (Exception e) {
	        } finally {
	           in.close();
	        }
	        return null;
	    }
	 
	 public static byte[] readAll(InputStream fin) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int n = fin.read(buff);
        while (n >= 0) {
            out.write(buff, 0, n);
            n = fin.read(buff);
        }
        return out.toByteArray();
    }
	 
	 public static boolean saveText(File file, String contents) throws IOException {
        OutputStream out = null;
        try {
          
            out = new FileOutputStream(file);
            out.write(contents.getBytes());
            //out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return false;
    }


}
