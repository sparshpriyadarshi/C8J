package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

class Rom{
    Path file;
    byte[] fileBytes;
    public Rom(Path file) throws IOException {
        this.file = file;
        fileBytes = Files.readAllBytes(this.file);
    }

    @Override
    public String toString() {
        return "Rom{" +
                "file=" + file +
                ", fileBytes=" + Arrays.toString(fileBytes) +
                '}';
    }
}
class Emulator{
    Rom rom;

}
public class Main {
    public static String USAGE = "Usage: c8j <path-to-rom>";
    public static void main(String[] args) {


        if(args.length == 0){
            System.out.println("No ROM to load...");
            System.out.println(USAGE);
        }else{
            System.out.println("C8J");
            System.out.printf("args = %s", Arrays.toString(args));
        }
    }
}

