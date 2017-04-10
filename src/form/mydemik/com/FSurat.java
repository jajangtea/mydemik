/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.mydemik.com;

import db.mydemik.com.Koneksi;
import db.service.mydemik.com.SuratService;
import entiti.mydemik.com.Jenissurat;

import entiti.mydemik.com.Kategori;
import entiti.mydemik.com.Keperluan;
import entiti.mydemik.com.Mahasiswa;
import entiti.mydemik.com.Surat;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import util.mydemik.com.HibernateUtil;

/**
 *
 * @author jajangtea
 */
public final class FSurat extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    Integer idmhs,idkat,idSurat;
    Date tgl;
    List<Jenissurat> jenissurat=new ArrayList<Jenissurat>();
    List<Keperluan> kpr=new ArrayList<Keperluan>();
    SuratService  SuratService;
    public FSurat() throws SQLException {
        initComponents();
        fillTable(jTSurat);
        tampiltanggal();
        cetak();
        Koneksi koneksi=new Koneksi();
        SuratService=new SuratService(koneksi.getConnection());
        jCJenis.addActionListener(new ComboBoxListener());
        jCKeperluan.addActionListener(new ComboBoxDengar());
        loadjenis();
        loadkeperluan();
        jTSurat.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTSurat.getColumnModel().getColumn(1).setPreferredWidth(5);
    }
    private void loadjenis() throws SQLException{
        jCJenis.removeAllItems();
        List<Jenissurat> jnss=SuratService.getAllJenis();
        for(Jenissurat mhs:jnss){
            jCJenis.addItem(mhs);
        }
    }
    private void loadkeperluan() throws SQLException{
        jCKeperluan.removeAllItems();
        List<Keperluan> kper=SuratService.getAllKeperluan();
        for(Keperluan mhs:kper){
            jCKeperluan.addItem(mhs);
        }
    }
    public void cetak()
    {
        jTSurat.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent me) {
            JTable table =(JTable) me.getSource();
            Point p = me.getPoint();
            int row = table.rowAtPoint(p);
            if (me.getClickCount() == 2) {
                int index = jTSurat.getSelectedRow();
                TableModel model = jTSurat.getModel();
                String value3 = model.getValueAt(index, 3).toString();
                int dialogbtn = JOptionPane.YES_NO_OPTION;
                int dr=JOptionPane.showConfirmDialog(null, "Cetak Surat", "Pertanyaan", dialogbtn);
                if(dr==0)
                {
                     JOptionPane.showMessageDialog(null, value3);
                }
                else
                {
                   txtNim.setText("");
                   txtNim.requestFocus();
                           
               }
            }
        }
    });
    }
    public void tampiltanggal()
    {
        if(jXDatePicker1.getDate()==null)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd.hhmmss");
            Calendar cal = Calendar.getInstance();
            //System.out.println(dateFormat.format(cal.getTime())); 
            
            try 
            {
                tgl=dateFormat.parse(dateFormat.format(cal.getTime()).toString());
                jXDatePicker1.setDate(tgl);
                Date dt=new Date();
                lbtanggal.setText(dt.toString());
                //System.out.println(tgl.toString().split(" ")[0]);
            } catch (ParseException ex) 
            {
                System.out.println(ex);
            }
        }
        else
        {
           // pm.setTanggal(jXDatePicker1.getDate());
        }
    }
    
    private void alignCenter(JTable tbl,int kolom)
    {
        DefaultTableCellRenderer centerRenderer=new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl.getColumnModel().getColumn(kolom).setCellRenderer(centerRenderer);
    }
    private void alignRight(JTable tbl,int kolom)
    {
        DefaultTableCellRenderer rightRenderer=new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tbl.getColumnModel().getColumn(kolom).setCellRenderer(rightRenderer);
    }
    
    private void hideColumn(JTable tbl,int kolom)
    {
        TableColumnModel tcm =tbl.getColumnModel();
        tcm.removeColumn(tcm.getColumn(kolom));
    }
    
    private void boldHeader(JTable tbl,int ukuran)
    {
       // tbl.getTableHeader().setFont(new Font("SanSerif",Font.ITALIC,12));
        JTableHeader header=tbl.getTableHeader();
        header.setFont(new Font("Dialog",Font.BOLD,ukuran));
    }
    public void loacComboKeperluan(JComboBox jk)
    {
        try {
            SessionFactory sf=HibernateUtil.getSessionFactory();
            Session s=sf.openSession();
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("FROM Keperluan");
            List resultList = q.list();
            for(Object o : resultList) 
            {
                Keperluan sr = (Keperluan)o;
                jk.addItem(sr.getKeperluan());
            }
            s.flush();
            tx.commit();
            s.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void fillTable(final JTable table) 
    {
        try
        {
            int count=1;
            SessionFactory sf=HibernateUtil.getSessionFactory();
            Session s=sf.openSession();
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("FROM Surat");
            List resultList = q.list();
            Vector<String> tableHeaders = new Vector<String>();
            Vector tableData = new Vector();
            tableHeaders.add("No");
            tableHeaders.add("Tanggal Buat"); 
            tableHeaders.add("NIM");
            tableHeaders.add("Nama");
            tableHeaders.add("Telepon");
            tableHeaders.add("Tanggal Surat"); 
            tableHeaders.add("Jenis Surat");
            tableHeaders.add("Alamat");
            tableHeaders.add("Keperluan");
            tableHeaders.add("idSurat");
            for(Object o : resultList) 
            {
                Surat sr = (Surat)o;
                Vector<Object> oneRow = new Vector<Object>();
                oneRow.add(count);
                oneRow.add(sr.getTanggalBuat().toString());
                oneRow.add(sr.getMahasiswa().getNim());
                oneRow.add(sr.getMahasiswa().getNama());
                oneRow.add(sr.getMahasiswa().getTlp());
                oneRow.add(sr.getTanggalSurat().toString());
                oneRow.add(sr.getJenissurat().getJenisSurat());
                oneRow.add(sr.getMahasiswa().getAlamat());
                oneRow.add(sr.getKeperluan().getKeperluan());
                oneRow.add(sr.getIdSurat());
                count++;
                tableData.add(oneRow);
            }
            jTSurat.setModel(new DefaultTableModel(tableData, tableHeaders));
            alignCenter(jTSurat, 0);
            hideColumn(jTSurat, 9);
            hideColumn(jTSurat, 1);
            hideColumn(jTSurat, 6);
            boldHeader(jTSurat,12);
            s.flush();
            tx.commit();
            s.close();
        }
        catch (ClassCastException e) 
        {
                e.printStackTrace();
        }
        
    }
    
    private void Hapus(int hps) 
    {
        try
        {
            if (JOptionPane.showConfirmDialog(null,"Anda Yakin ? ","Konfirmasi" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
                SessionFactory sf=HibernateUtil.getSessionFactory();
                Session s=sf.openSession();
                Transaction tx = s.beginTransaction();

                Query q = s.createQuery("Delete From Surat where idSurat=:idSurat");
                q.setParameter("idSurat", hps);
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
 
    public final void cari (final String nim) 
    {
        try
        {
            SessionFactory sf=HibernateUtil.getSessionFactory();
            Session s=sf.openSession();
            Transaction tx = s.beginTransaction();
           // String sql="FROM Barang b where b.kodeBarang=:kodeBarang";
            String sql="select * from mahasiswa where nim=:nim";
            Query q = s.createSQLQuery(sql);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            q.setParameter("nim", nim);
            List mhs=q.list();
            if(txtNim.getText().equals(""))
            {
                FMahasiswa f =new FMahasiswa();
                f.setVisible(rootPaneCheckingEnabled);
            }
            else if (mhs.isEmpty())
            {
                int dialogbtn = JOptionPane.YES_NO_OPTION;
                int dr=JOptionPane.showConfirmDialog(this, "Form pencarian.", "Pertanyaan", dialogbtn);
                if(dr==0)
                {
                     FMahasiswa f =new FMahasiswa();
                     f.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                     f.setVisible(rootPaneCheckingEnabled);
                     f.txtCari.setText("");
                     f.txtCari.requestFocus();
                     this.setVisible(false);
                }
                else
                {
                   txtNim.setText("");
                   txtNim.requestFocus();
                           
               }
                
            }
            else
            {
                for (Object br : mhs )
                {
                    Map row = (Map)br;
                    idmhs=Integer.parseInt(row.get("idMahasiswa").toString());
                  
                    lbNim.setText(row.get("nim").toString());
                    lbNama.setText(row.get("nama").toString());
                    lbTlp.setText(row.get("tlp").toString());
                    lbAlamat.setText(row.get("alamat").toString());
                    
                   // a=Float.parseFloat(row.get("Harga").toString());
                    //lbHarga.setText(NumberFormat.getNumberInstance().format(a));
                    //txtJml.requestFocus();
                }
                s.flush();
                tx.commit();
                s.close();
            }
        }
        catch (Exception e) 
        {
                System.out.println(e);
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

        jCJenis = new javax.swing.JComboBox();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        btnCari = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbNim = new javax.swing.JLabel();
        lbNama = new javax.swing.JLabel();
        lbTlp = new javax.swing.JLabel();
        lbAlamat = new javax.swing.JLabel();
        jCKeperluan = new javax.swing.JComboBox();
        jradmasuk = new javax.swing.JRadioButton();
        jradkeluar = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        txtNim = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTSurat = new javax.swing.JTable();
        lbtanggal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 0, 51));

        jCJenis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCJenisItemStateChanged(evt);
            }
        });
        jCJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCJenisActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 0, 51));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Mahasiswa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("NIM :");

        jLabel7.setText("Nama :");

        jLabel8.setText("Alamat :");

        jLabel9.setText("Telepon :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTlp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbNim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbTlp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAlamat)
                    .addComponent(jLabel8))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jradmasuk.setText("Masuk");

        jradkeluar.setText("Keluar");

        jLabel2.setText("Jenis Surat");

        jLabel3.setText("Tanggal Surat");

        jLabel4.setText("Mahasiswa");

        jLabel5.setText("Keperluan");

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnClose.setText("Keluar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        txtNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNimActionPerformed(evt);
            }
        });

        jTSurat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTSurat.setModel(new javax.swing.table.DefaultTableModel(
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
        jTSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTSuratMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTSurat);

        jScrollPane2.setViewportView(jScrollPane1);

        lbtanggal.setText("Tanggal : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnClose))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jCJenis, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCKeperluan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jradmasuk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jradkeluar)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 400, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbtanggal))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbtanggal)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCari)
                            .addComponent(jLabel4)
                            .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCKeperluan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jradmasuk)
                            .addComponent(jradkeluar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnHapus)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        try 
        {
            cari(txtNim.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session s=sf.openSession();
        Surat sr=new Surat();
        Jenissurat js=new Jenissurat();
        Mahasiswa mhs = new Mahasiswa();
        Keperluan perlu = new Keperluan();
        Kategori kat=new Kategori();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime())); 
        sr.setJenissurat(js);
        Keperluan kprl=(Keperluan) jCKeperluan.getSelectedItem();
        Jenissurat jns=(Jenissurat) jCJenis.getSelectedItem();
        js.setIdJenis((Integer) jns.getIdJenis());
        sr.setKeperluan(perlu);
        perlu.setIdKeperluan(kprl.getIdKeperluan());
        if(jXDatePicker1.getDate()==null)
        {
           
            try 
            {
                tgl=dateFormat.parse(dateFormat.format(cal.getTime()).toString());
                sr.setTanggalSurat(tgl);
                jXDatePicker1.setDate(tgl);
            } catch (ParseException ex) 
            {
                System.out.println(ex);
            }
        }
        else
        {
              sr.setTanggalSurat(jXDatePicker1.getDate());
        }
        Date sekarang =new Date();
        sr.setTanggalBuat(sekarang);
        sr.setMahasiswa(mhs);
        mhs.setIdMahasiswa(idmhs);
        if(jradmasuk.isSelected())
        {
            idkat=1;
        }
        else if (jradkeluar.isSelected())
        {
            idkat=2;
        }
        sr.setKategori(kat);
        kat.setIdKategori(idkat);
        Transaction tx=s.beginTransaction();
        s.saveOrUpdate(sr);
        tx.commit();
        s.flush();
        s.close();
        fillTable(jTSurat);
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNimActionPerformed

    private void jTSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTSuratMouseClicked
        int baris =jTSurat.getSelectedRow();
        idSurat = (Integer)   jTSurat.getModel().getValueAt(baris, 9);
    }//GEN-LAST:event_jTSuratMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void jCJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCJenisActionPerformed

    private void jCJenisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCJenisItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCJenisItemStateChanged

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        Hapus(idSurat);
        fillTable(jTSurat);
    }//GEN-LAST:event_btnHapusActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FSurat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FSurat().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FSurat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private  class ComboBoxDengar implements ActionListener {

        public ComboBoxDengar() {
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Keperluan mahasiswa=(Keperluan) jCKeperluan.getSelectedItem();
            //jLabel10.setText(mahasiswa.getIdKeperluan().toString());
        }
    }
    
    private class ComboBoxListener implements ActionListener {
        

    public ComboBoxListener() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Jenissurat mahasiswa=(Jenissurat) jCJenis.getSelectedItem();
            //jLabel10.setText(mahasiswa.getIdJenis().toString());
    }
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    public javax.swing.JComboBox jCJenis;
    private javax.swing.JComboBox jCKeperluan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTSurat;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JRadioButton jradkeluar;
    private javax.swing.JRadioButton jradmasuk;
    private javax.swing.JLabel lbAlamat;
    private javax.swing.JLabel lbNama;
    private javax.swing.JLabel lbNim;
    private javax.swing.JLabel lbTlp;
    private javax.swing.JLabel lbtanggal;
    public javax.swing.JTextField txtNim;
    // End of variables declaration//GEN-END:variables
}
