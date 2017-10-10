package seven;

import java.io.Closeable;
import java.io.IOException;

public class TryWithResourceWithThrow {

    public static class FetchDataAndMapToObject implements Closeable {
        @Override
        public void close() throws IOException {
            System.out.println("FetchDataAndMapToObject will be closed no matter what.");
        }
    }

    public static void main(String...args){
        try (FetchDataAndMapToObject fetchDataAndMapToObject = new FetchDataAndMapToObject()) {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
