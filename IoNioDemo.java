import java.io.*;
import java.nio.file.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class IoNioDemo {
    public static void main(String[] args) throws Exception {
        String ioFile = "io.txt";
        String nioFile = "nio.txt";

        FileWriter fw = new FileWriter(ioFile);
        fw.write("Olá! ");
        fw.close();

        FileReader fr = new FileReader(ioFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("io: " + line);
        }
        br.close();

        Path nioPath = Paths.get(nioFile);
        Files.write(nioPath, "Hello from java.nio".getBytes(StandardCharsets.UTF_8));

        FileChannel channel = FileChannel.open(nioPath, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesReabd = channel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("nio: " + content);
        channel.close();
    }
}
