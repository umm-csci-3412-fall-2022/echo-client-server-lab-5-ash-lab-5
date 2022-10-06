package echoserver;

import java.net.*;
import java.io.*;
import java.io.IOException;

public class EchoServer {
    public static final int portNumber = 6013;
    public static void main(String[] args) throws IOException {
        try {
            // Start listening on the specified port
            ServerSocket sock = new ServerSocket(portNumber);
      
            // Run forever, which is common for server style services
            while (true) {
              // Wait until someone connects, thereby requesting a date
              Socket client = sock.accept();
              System.out.println("Got a request!");
      
              OutputStream out = client.getOutputStream();
              InputStream in = client.getInputStream();
              
              // Read data until no more is avaialble
              while (true) {
                int data = in.read();

                if (data == -1) {
                    break;
                }

                out.write(data);
              }

              // Flush to output just to be sure
              out.flush();

              // Close the output
              client.shutdownOutput();
              
              // Close the client socket since we're done.
              client.close();
            }
          // *Very* minimal error handling.
          } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
          }

        while (true) {
            System.out.print((char)System.in.read());
        }
    } 
}