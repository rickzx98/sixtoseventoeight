package seven;

import java.io.*;

public class TryWithResource {
    public static void main(String...args){
        InputStream scratchFileStream = TryWithResource.class.getClassLoader().getResourceAsStream("ascratchfile");
        try (BufferedInputStream br = new BufferedInputStream(scratchFileStream)) {
           br.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
