
import java.net.*;
import java.util.Vector;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Madhusudan
 */
public class ChooseConnectionActivity extends javax.swing.JFrame {
    
    public static String[] IpList = new String[10];
    public static String[] NameList = new String[10];
    public static String SelectedIp = null;
    DefaultListModel<String> model = new DefaultListModel<>();
    
    /**
    * Creates new form ChooseConnection     */
    public ChooseConnectionActivity() {
        initComponents();
       
        try { jLabel2.setText(InetAddress.getLocalHost().getHostName()); } catch(Exception e) { e.printStackTrace(); }
        
        FindConnections findConnections = new FindConnections(this);
        findConnections.start();
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bridge - Choose Connection");
        setPreferredSize(new java.awt.Dimension(400, 400));
        setResizable(false);

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Refresh");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Select");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        jLabel3.setText("Updating in 10s");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        FindConnections findConnections = new FindConnections(this);
        findConnections.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(jList1.getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(null, "Select a Connection", "Select Connection", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            SelectedIp = IpList[jList1.getSelectedIndex()];
            SelectedIp = SelectedIp.trim();
            System.out.print(SelectedIp);
            
            if(ChooseFileSharingActionActivity.action.equals("send"))
            {
                ChooseSendingFilesActivity chooseSendingFiles = new ChooseSendingFilesActivity();
                chooseSendingFiles.setVisible(true);
                this.dispose();
            }
            else if(ChooseFileSharingActionActivity.action.equals("receive"))
            {
                ReceivingFilesActivity receivingFiles = new ReceivingFilesActivity();
                receivingFiles.setVisible(true);
            }
            else if(ChooseFileSharingActionActivity.action.equals("calling"))
            {
                CallingActivity calling = new CallingActivity();
                calling.setVisible(true);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ChooseConnectionActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChooseConnectionActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChooseConnectionActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChooseConnectionActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChooseConnectionActivity().setVisible(true);
            }
        });
    }
    
    public void UpdateUI()
    {          
        int i = 0;
        model.clear();
        while(NameList[i] != null)
        {
            model.addElement(NameList[i]);
            System.out.print(NameList[i] + "\n");
            i++;
        }
        jList1.setModel(model);
    }
    public void UpdateUI(int time)
    {   
        jLabel3.setText("Updating in " + time + "s");
        if(time == 0)
        {
            jLabel3.setText("");
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

class FindConnections extends Thread {
    ChooseConnectionActivity activity;
    FindConnections(ChooseConnectionActivity param)
    {
        this.activity = param;
    }
    
    
    public void run() {
        try {
            // SENDING
            DatagramSocket sendsocket = new DatagramSocket();
            String sendmessage = InetAddress.getLocalHost().getHostName() + "@" + InetAddress.getLocalHost().getHostAddress();
            DatagramPacket sendpacket = new DatagramPacket(sendmessage.getBytes(), sendmessage.getBytes().length, InetAddress.getByName("225.1.1.1"), 4446);

            //RECEIVING
            MulticastSocket receivesocket = new MulticastSocket(4446);
            receivesocket.joinGroup(InetAddress.getByName("225.1.1.1"));
            
            int found = 0;
            int i = 0;
            while(i <= 10)
            {
                this.activity.UpdateUI(10 - i);
                try {	
                    sendsocket.send(sendpacket);

                    byte[] receivedata = new byte[1024];
                    DatagramPacket receivepacket = new DatagramPacket(receivedata, receivedata.length);
                    receivesocket.receive(receivepacket);
                    String data[] = new String(receivepacket.getData()).split("@");
                    if(!data[1].trim().equals(InetAddress.getLocalHost().getHostAddress().trim()))
                    {
                            for(int j=0; j<10; j++)
                            {
                                if(ChooseConnectionActivity.IpList[j] != null && ChooseConnectionActivity.IpList[j].equals(data[1]))
                                    found = 1;
                            }
                            if(found == 0)
                            {
                                for(int j=0; j<10; j++)
                                {
                                    if(ChooseConnectionActivity.IpList[j] == null)
                                    {
                                        ChooseConnectionActivity.NameList[j] = data[0];
                                        ChooseConnectionActivity.IpList[j] = data[1];
                                        //System.out.print(data[0] + ": " + data[1] + "\n");
                                        break;
                                    }
                                }
                            }
                    }
                    Thread.sleep(1000);
                    i++;
                } catch(Exception e) { }
            }
            // UPDATING LIST
            this.activity.UpdateUI();
            
        } catch (Exception e) { }
    }
}