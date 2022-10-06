package echoserver;

import java.net.*;
import java.io.*;


public class EchoClient {
    public static final int portNumber = 6013;

  public static void main(String[] args) throws IOException {
    String server;
    // Use "127.0.0.1", i.e., localhost, if no server is specified.
    if (args.length == 0) {
      server = "127.0.0.1";
    } else {
      server = args[0];
    }

    try {
      // Connect to the server
      Socket socket = new Socket(server, portNumber);

      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();
      
      // Loop over the input, sending it to the server
      while (true) {
        int data = System.in.read();

        if (data == -1) {
            break;
        }

        out.write(data);

        data = in.read();

        if (data == -1) {
            break;
        }

        System.out.write(data);
      }

      // Flush the output and tell the server we are done
      out.flush();
      socket.shutdownOutput();

      // Wait for any remaining data to be recieved
      while (true) {
        int data = in.read();

        if (data == -1) {
            break;
        }

        System.out.write(data);
      }

      // Make sure we flush that data to stdout
      System.out.flush();

      // Tell the server we are done listening
      socket.shutdownInput();

      // Close the socket when we're done reading from it
      socket.close();

    // Provide some minimal error handling.
    } catch (ConnectException ce) {
      System.out.println("We were unable to connect to " + server);
      System.out.println("You should make sure the server is running.");
    } catch (IOException ioe) {
      System.out.println("We caught an unexpected exception");
      System.err.println(ioe);
    }
  }
}