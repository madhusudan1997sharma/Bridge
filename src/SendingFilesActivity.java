/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Madhusudan
 */

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;

public class SendingFilesActivity extends javax.swing.JFrame {

    /**
     * Creates new form SendingFiles
     */
    public static boolean responseReceived = false;
    
    public SendingFilesActivity() {
        initComponents();
        
        // SENDING NUMBER OF FILES INFORMATION UNTIL GETS RESPONSE AND THEN SENDING FILES
        SendingDetails sendingDetails = new SendingDetails();
        sendingDetails.start();
                
        // RESPONSE LISTENING THREAD
        ListeningResponse listeningResponse = new ListeningResponse(this);
        listeningResponse.start();
        
        // KEEPING MACHINE AWAKE
        KeepSendingAwake keepAwake = new KeepSendingAwake();
        keepAwake.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bridge - Sending Files");
        setResizable(false);

        jLabel1.setText("Sending File: ");
        jLabel1.setName(""); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SendingFilesActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendingFilesActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendingFilesActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendingFilesActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendingFilesActivity().setVisible(true);
            }
        });
    }
    
    public void updataGUI(String fileName)
    {
        jLabel2.setText(fileName);
    }
    public void updataGUI(long fileSize, long sentSize)
    {
        int val = (int) (100-(((float)(fileSize-sentSize)/(float)fileSize)*100));
        jProgressBar1.setValue(val);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}

class SendingDetails extends Thread
{
    public void run()
    {
        while(SendingFilesActivity.responseReceived == false){
            try {
                DatagramSocket socket = new DatagramSocket();
                int i = 0;
                while(ChooseSendingFilesActivity.selectedFilesList[i] != null)
                    i++;
                String sendingData = Integer.toString(i) + "@" + InetAddress.getLocalHost().getHostAddress();
                byte data[] = sendingData.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(ChooseConnectionActivity.SelectedIp), 9876);
                socket.send(packet);
                System.out.print("\nsent data");
                Thread.sleep(1000);
            } catch (Exception e) { }
        }
    }
}

class ListeningResponse extends Thread
{
    SendingFilesActivity activity;
    ListeningResponse(SendingFilesActivity param){
        this.activity = param;
    }
    public void run()
    {
        try {
            while(true)
            {
                DatagramSocket responseSocket = new DatagramSocket(9876);
                byte[] temp = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(temp, temp.length);
                responseSocket.receive(responsePacket);
                responseSocket.close();
                byte[] messageByte = new byte[responsePacket.getLength()];
                System.arraycopy(responsePacket.getData(), responsePacket.getOffset(), messageByte, 0, responsePacket.getLength());
                if(((new String(messageByte)).trim().equals(ChooseConnectionActivity.SelectedIp)))
                {
                    SendingFilesActivity.responseReceived = true;
                    System.out.print("\nreceived response");
                    Thread.sleep(1000);

                    // START SENDING FILES
                    SendingFiles sendingFiles = new SendingFiles(this.activity);
                    sendingFiles.start();
                }
            }
        } catch (Exception e) { }
    }
}

class SendingFiles extends Thread {
    SendingFilesActivity activity;
    SendingFiles(SendingFilesActivity param){
        this.activity = param;
    }
    
    public void run()
    {
        try {
            ServerSocket sock = new ServerSocket(1997);
            int i = 0;
            while(ChooseSendingFilesActivity.selectedFilesList[i] != null)
            {
                File file = new File(ChooseSendingFilesActivity.selectedFilesList[i]);
                
                // SENDING FILE DETAILS TO SAVE AT OTHER END
                DatagramSocket tempSocket = new DatagramSocket();
                String fileDetails = file.getName() + "@" + file.length();
                DatagramPacket tempPacket = new DatagramPacket(fileDetails.getBytes(), fileDetails.getBytes().length, InetAddress.getByName(ChooseConnectionActivity.SelectedIp), 6397);
                tempSocket.send(tempPacket);
                this.activity.updataGUI(file.getName());
                Thread.sleep(500);
                
                byte[] byteArray = new byte[100000000];
                Socket socket = sock.accept();
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                OutputStream out = new BufferedOutputStream(socket.getOutputStream())      ;
                int bytesSend;
                long fileSize = file.length();
                long sentData = 0;
                while((bytesSend = bis.read(byteArray, 0, byteArray.length)) != -1)
                {
                    out.write(byteArray, 0, bytesSend);
                    sentData += bytesSend;
                    this.activity.updataGUI(fileSize, sentData);
                }
                out.flush();
                out.close();
                
                i++;
                Thread.sleep(1000);
            }
        } catch (Exception e) { e.printStackTrace(); }
        System.out.print("\nSent");
        
        // SENT ALL FILES, RESTARTING APPLICATION
        try { Runtime.getRuntime().exec("java -jar Bridge.jar"); } catch(Exception e) { }
    }
}

class KeepSendingAwake extends Thread {
    public void run()
    {
        try {
            Robot hal = new Robot();
            while(true) {
                hal.delay(1000 * 60);
                Point pointer = MouseInfo.getPointerInfo().getLocation();
                hal.mouseMove(pointer.x+1, pointer.y+1);
                hal.mouseMove(pointer.x-1, pointer.y-1);
                pointer = MouseInfo.getPointerInfo().getLocation();
            }
        } catch(Exception e) { }
    }
}