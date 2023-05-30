package io.windfree.util;
import java.io.*;

public class ProcessUtil {
    public void execute() throws InterruptedException, IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("java","-cp","/Users/windfree/workspace","Test");

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        writer.write("aaaa\n");
        writer.flush();
        writer.write("bbb\n");
        writer.flush();
        writer.write("q\n");
        writer.flush();
      //  writer.close();
        String line;
        /*
        while (reader.ready() && (line = reader.readLine()) != null) {
            System.out.println("reader.ready():" + reader.ready());
            System.out.println(line);
        }

         */
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int  ret = process.waitFor();
        System.out.printf("Program exited with code: %d", ret);
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new ProcessUtil().execute();
    }
}
