/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.mydemik.com;

import db.mydemik.com.Koneksi;
import db.service.mydemik.com.SuratService;
import entiti.mydemik.com.Thajaran;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.mydemik.com.Fungsi;
import util.mydemik.com.HibernateUtil;

/**
 *
 * @author jajangtea
 */
public class __EntryTA extends javax.swing.JFrame {

    /**
     * Creates new form __EntryMahasiswa
     */
    
    SuratService SuratService;
    int idMhs,chkStatuses;
    Fungsi nf = new Fungsi();
    public __EntryTA() throws SQLException {
        initComponents();
        fillTable(TTA);
        Koneksi koneksi=new Koneksi();
        SuratService=new SuratService(koneksi.getConnection());
    }
    private void fillTable(final JTable table) 
    {
        try
        {
            int count=1;
            SessionFactory sf=HibernateUtil.getSessionFactory();
            Session s=sf.openSession();
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("FROM Thajaran");
            List resultList = q.list();
            Vector<String> tableHeaders = new Vector<String>();
            Vector tableData = new Vector();
            tableHeaders.add("No"); 
            tableHeaders.add("idThajaran");
            tableHeaders.add("Kode"); 
            tableHeaders.add("Thajaran"); 
            tableHeaders.add("Status"); 
            for(Object o : resultList) 
            {
                Thajaran pd = (Thajaran)o;
                Vector<Object> oneRow = new Vector<Object>();
                oneRow.add(count);
                oneRow.add(pd.getIdThajaran());
                oneRow.add(pd.getTahun());
                oneRow.add(pd.getSemester());
                oneRow.add(pd.getStatus()==1?"Aktif":"Tidak");
                tableData.add(oneRow);
                count++;
            }
            TTA.setModel(new DefaultTableModel(tableData, tableHeaders));
            s.flush();
            tx.commit();
            s.close();
            nf.setLebarKolom(table);
            nf.hideColumn(table, 1);
        }
        catch (ClassCastException e) 
        {
                e.printStackTrace();
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

        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TTA = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        lbtanggal1 = new javax.swing.JLabel();
        TxtTahun = new javax.swing.JTextField();
        btnKeluar = new javax.swing.JButton();
        chkStatus = new javax.swing.JCheckBox();
        cbbSemester = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kelola Mahasiswa");
        setResizable(false);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setText("Semester :");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Tahun :");

        TTA.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TTA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TTAMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TTA);

        btnHapus.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        lbtanggal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbtanggal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtanggal1.setText("KELOLA DATA TAHUN AJARAN");
        lbtanggal1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TxtTahun.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TxtTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTahunActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        chkStatus.setText("Status");
        chkStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkStatusActionPerformed(evt);
            }
        });

        cbbSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ganjil", "Genap", "Pendek" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKeluar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbtanggal1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(chkStatus)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbbSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TxtTahun))
                                        .addGap(9, 9, 9)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbtanggal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TxtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbbSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKeluar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TTAMouseClicked
        int baris =TTA.getSelectedRow();
        idMhs= (int) TTA.getModel().getValueAt(baris, 1);
        System.out.println(idMhs);
        TxtTahun.setText((String) TTA.getModel().getValueAt(baris, 2));
        cbbSemester.setSelectedItem((String) TTA.getModel().getValueAt(baris, 3));
        String cek=(String) TTA.getModel().getValueAt(baris, 4);
        if(cek.equals("Aktif"))
        {
            chkStatus.setSelected(true);
            chkStatuses=1;
        }
        else
        {
            chkStatuses=0;
            chkStatus.setSelected(false);
        }
    }//GEN-LAST:event_TTAMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        Hapus(idMhs);
        fillTable(TTA);       
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session s=sf.openSession();
        Thajaran pr=new Thajaran();
        pr.setIdThajaran(idMhs);
        pr.setTahun(TxtTahun.getText());
        pr.setSemester(cbbSemester.getSelectedItem().toString());
        Transaction tx=s.beginTransaction();
        s.saveOrUpdate(pr);
        tx.commit();
        s.flush();
        s.close();
        fillTable(TTA);
    }//GEN-LAST:event_btnSimpanActionPerformed
    private void Hapus(int hps) 
    {
        try
        {
            if (JOptionPane.showConfirmDialog(null,"Anda Yakin ? ","Konfirmasi" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                SessionFactory sf=HibernateUtil.getSessionFactory();
                Session s=sf.openSession();
                Transaction tx = s.beginTransaction();
                Query q = s.createQuery("Delete From Thajaran where idThajaran=:idThajaran");
                q.setParameter("idThajaran", hps);
                q.executeUpdate();
                tx.commit();
                s.flush();
                s.close();
            }
        }
        catch (ClassCastException e) 
        {
            System.out.println("Err :" + e);
        }
    }
    
    private void UpdateTA(int thn) 
    {
        try
        {
                SessionFactory sf=HibernateUtil.getSessionFactory();
                Session s=sf.openSession();
                Transaction tx = s.beginTransaction();
                Query q1 = s.createQuery("Update Thajaran set status=0");
                Query q2 = s.createQuery("Update Thajaran set status=1 where idThajaran=:idThajaran");
                q1.executeUpdate();
                
                q2.setParameter("idThajaran", thn);
                q2.executeUpdate();
                
                tx.commit();
                s.flush();
                s.close();
        }
        catch (ClassCastException e) 
        {
            System.out.println("Err :" + e);
        }
    }
    private void TxtTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTahunActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void chkStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkStatusActionPerformed
        // TODO add your handling code here:
        UpdateTA(idMhs);
        fillTable(TTA);
    }//GEN-LAST:event_chkStatusActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TTA;
    public javax.swing.JTextField TxtTahun;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cbbSemester;
    private javax.swing.JCheckBox chkStatus;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbtanggal1;
    // End of variables declaration//GEN-END:variables
}
