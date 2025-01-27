/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package penjualan;

/**
 *
 * @author ACER
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LapPenjualan extends javax.swing.JFrame {
Connection Con;
ResultSet RsJual;
Statement stm;
PreparedStatement pstat;
String tgl1,tgl2;
private Object[][] dataTable = null;
private String[] header = {"No Jual","Tanggal","Nama Konsumen","Kode Barang","Nama Barang","Harga Satuan","Jumlah","Total"};
DefaultTableModel tableModel = new DefaultTableModel(new Object [][] {},header);

public LapPenjualan() {
initComponents();
dtAwal.setDate(new Date());
dtAkhir.setDate(new Date());
open_db();
baca_data();
}
private void open_db()
{
try{
KoneksiMysql kon = new KoneksiMysql ("localhost","root","","penjualan");
Con = kon.getConnection();
}catch (Exception e) {
System.out.println("Error : "+e);
}
}

private void baca_data()
{
try{
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
tgl1=sdf.format(dtAwal.getDate());
tgl2=sdf.format(dtAkhir.getDate());
stm = Con.createStatement();
pstat = Con.prepareStatement("SELECT a.no_jual,a.tgl_jual,c.nm_kons,d.kd_brg,d.nm_brg,b.harga_jual,b.jml_jual,(b.harga_jual*b.jml_jual) AS 'totjual' " +
"FROM jual a LEFT JOIN djual b ON (a.no_jual=b.no_jual) " + "LEFT JOIN konsumen c ON (a.kd_kons=c.kd_kons) " + "LEFT JOIN barang d ON (b.kd_brg=d.kd_brg)" + "where date(a.tgl_jual)>=" + "date('"+ tgl1+"') and date(a.tgl_jual)<=" + "date('"+tgl2+"')order by a.tgl_jual desc",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
RsJual = pstat.executeQuery();
ResultSetMetaData meta = RsJual.getMetaData();
int col = meta.getColumnCount();
int baris = 0;
while(RsJual.next()) {
baris = RsJual.getRow();
}
dataTable = new Object[baris][col];
int x = 0;
RsJual.beforeFirst();
while(RsJual.next()) {
dataTable[x][0] = RsJual.getString("no_jual");
dataTable[x][1] = RsJual.getDate("tgl_jual");
dataTable[x][2] = RsJual.getString("nm_kons");
dataTable[x][3] = RsJual.getString("kd_brg");
dataTable[x][4] = RsJual.getString("nm_brg");
dataTable[x][5] = RsJual.getDouble("harga_jual");
dataTable[x][6] = RsJual.getInt("jml_jual");
dataTable[x][7] = RsJual.getDouble("totjual");
x++;
}
tblLapJual.setModel(new DefaultTableModel(dataTable,header));
}catch(SQLException e){
JOptionPane.showMessageDialog(null, e);
}
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
        jDayChooser2 = new com.toedter.calendar.JDayChooser();
        jFrame1 = new javax.swing.JFrame();
        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBox1 = new javax.swing.JCheckBox();
        dtAwal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dtAkhir = new com.toedter.calendar.JDateChooser();
        cmdCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLapJual = new javax.swing.JTable();
        cmdExport = new javax.swing.JButton();
        cmdKeluar = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenuItem1.setText("jMenuItem1");

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dtAwal.setDateFormatString("yyyy-MM-dd");

        jLabel1.setText("Tgl");

        jLabel2.setText("s/d");

        dtAkhir.setDateFormatString("yyyy-MM-dd");

        cmdCari.setText("Cari");
        cmdCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCariActionPerformed(evt);
            }
        });

        tblLapJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Jual", "Tanggal", "Nama Konsumen", "Kode Barang", "Nama Barang", "Harga Satuan", "Jumlah", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblLapJual);

        cmdExport.setText("Export");
        cmdExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExportActionPerformed(evt);
            }
        });

        cmdKeluar.setText("Keluar");
        cmdKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdKeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdKeluar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(37, 37, 37)
                        .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(cmdCari)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdCari)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dtAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(dtAwal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdExport)
                    .addComponent(cmdKeluar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCariActionPerformed
        baca_data();
    }//GEN-LAST:event_cmdCariActionPerformed

    private void cmdExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExportActionPerformed
        try{
ExportToExcel ex=new ExportToExcel(tblLapJual, new
File("DataPenjualan.xls"));
//exportToExcel(tblBrg, new File("DataBarang.xls"));
JOptionPane.showMessageDialog(null, "Sukses Export data .....");
}catch(Exception e){
JOptionPane.showMessageDialog(null, e);
}
    }//GEN-LAST:event_cmdExportActionPerformed

    private void cmdKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_cmdKeluarActionPerformed

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
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LapPenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LapPenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCari;
    private javax.swing.JButton cmdExport;
    private javax.swing.JButton cmdKeluar;
    private com.toedter.calendar.JDateChooser dtAkhir;
    private com.toedter.calendar.JDateChooser dtAwal;
    private javax.swing.JCheckBox jCheckBox1;
    private com.toedter.calendar.JDayChooser jDayChooser2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLapJual;
    // End of variables declaration//GEN-END:variables
}
