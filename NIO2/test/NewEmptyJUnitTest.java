/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import nio2.Helo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MyParinya
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass");
    }

    @Before
    public void setUp() {
        System.out.println("setUp");
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void hello() {
        Helo badboy = new Helo();
        badboy.setName("Badboy");

        Assert.assertEquals("ไม่ผ่าน", "Badboy", badboy.getName());
    }

    @Test
    public void openingFiles() {
        Path log = Paths.get("B:\\Backup\\log.txt");
        try {
            List<String> lines = Files.readAllLines(log, StandardCharsets.UTF_8);
            //byte[] bytes = Files.readAllBytes(logFile);  
            for (String t : lines) {
                System.out.println(t);
            }
        } catch (IOException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void seekableByteChannel() {
        Path logFile = Paths.get("B:\\Backup\\log.txt");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            FileChannel channel = FileChannel.open(logFile, StandardOpenOption.READ);
            channel.read(buffer, channel.size());
            System.out.println(channel.size());

        } catch (IOException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void asynchronousIOFutureStyle() {
        try {
            Path file = Paths.get("B:\\harddisk\\ebook\\Economics\\An Introduction to Game Theory.pdf");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(100_000);
            Future<Integer> result = channel.read(buffer, 0);
            while (!result.isDone()) {
                System.out.println("do");
            }
            Integer bytesRead = result.get();
            System.out.println("Bytes read [" + bytesRead + "]");
        } catch (IOException | ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void asynchronousIOCallbackStyle() {
        try {
            Path file = Paths.get("B:\\harddisk\\ebook\\Economics\\An Introduction to Game Theory.pdf");
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
            ByteBuffer buffer = ByteBuffer.allocate(100_000);

            channel.read(buffer, 0, buffer,
                    new CompletionHandler<Integer, ByteBuffer>() {
                        public void completed(Integer result,
                                ByteBuffer attachment) {
                            System.out.println("Bytes read [" + result + "]");
                        }

                        public void failed(Throwable exception, ByteBuffer attachment) {
                            System.out.println(exception.getMessage());
                        }
                    });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
