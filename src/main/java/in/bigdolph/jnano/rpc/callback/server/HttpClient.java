package in.bigdolph.jnano.rpc.callback.server;

import java.io.*;
import java.net.Socket;

public class HttpClient implements Runnable {
    
    private Socket socket;
    private BlockCallbackServer callbackServer;
    
    public HttpClient(Socket socket, BlockCallbackServer callbackServer) {
        this.socket = socket;
        this.callbackServer = callbackServer;
    }
    
    
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "utf-8"));
    
            String[] request = reader.readLine().split(" ");
            if(!request[0].equalsIgnoreCase("POST")) return; //Ignore non-post
    
            int contentLength = -1;
    
            String s;
            while((s = reader.readLine()) != null) {
                if(s.equals("")) {
                    break; //End of headers
                } else if(contentLength == -1) {
                    String[] header = s.split(":", 2);
                    if(header[0].trim().equalsIgnoreCase("content-length")) {
                        contentLength = Integer.parseInt(header[1].trim());
                    }
                }
            }
            if(contentLength == -1) return; //Couldn't read length
    
            //Read request body
            char[] buffer = new char[contentLength];
            reader.read(buffer, 0, contentLength);
    
            //Return OK
            DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
            output.writeUTF("HTTP/1.1 200 OK");
            output.flush();
    
            //Close socket
            this.socket.close();
    
            HttpRequest requestData = new HttpRequest(this.socket.getInetAddress(), request[1], contentLength, String.valueOf(buffer));
            this.callbackServer.handleRequest(requestData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close socket
            try {
                if(!this.socket.isClosed()) this.socket.close();
            } catch (IOException e) {}
        }
    }
    
}