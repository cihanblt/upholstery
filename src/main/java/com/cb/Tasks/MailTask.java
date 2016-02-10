package com.cb.Tasks;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

/**
 * Created by cihan on 10.02.2016.
 */
public class MailTask implements Runnable {
    public void run() {
//        processIt("smtp.gmail.com","cihanblt@gmail.com","cihanblt@gmail.com");
        sendMail("smtp.live.com","chn_blt@hotmail.com","cihanblt@gmail.com");
    }

    public void sendMail(String host, String from, String to){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chn_blt@hotmail.com","CHNBLT1988*");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject("sub");
            message.setText("text text");
            Transport.send(message);
            System.out.println("sent message...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void processIt(String urlArg, String from, String to) {
        URI url = null;
//        Socket socket = null;
        SSLSocket sslSocket = null;
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        String host = null;
        int port = 0;
        String path = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URI(urlArg);
            host = url.getHost();
            port = url.getPort();
            System.out.println(port);
            if (port == -1) {
                port = 465; //gmail's port
            }
//            socket = new Socket(urlArg,port);
            sslSocket = (SSLSocket) factory.createSocket(urlArg,port);
            inputStream = sslSocket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            outputStream = sslSocket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.println("HELLO " + urlArg);
            System.out.println(bufferedReader.readLine());
            printWriter.println("MAIL From:<" + from + ">");
            System.out.println(bufferedReader.readLine());
            printWriter.println("RCPT To:<" + to + ">");
            System.out.println(bufferedReader.readLine());
            printWriter.println("DATA");
            System.out.println(bufferedReader.readLine());
            System.out.println();
            printWriter.flush();
            printWriter.close();
            sslSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
