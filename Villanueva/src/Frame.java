import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
        
public class Frame extends javax.swing.JFrame {
    
     private Connection conn;
     
    public Frame() {
        initComponents();
        setLocationRelativeTo(null);
         connectToDatabase();
    }

    private void connectToDatabase() {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/comm"; // Change to your database URL
        String user = "root"; // Change to your database username
        String password = "nemuimors"; // Change to your database password

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
        }
    }
    
     private void loadData() {
        String query = "SELECT product_id, name, description, price, stock_quantity, created_at FROM products";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Product ID", "Name", "Description", "Price", "Stock", "Created At"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getTimestamp("created_at")
                });
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void saveData() {
        String name = NameField.getText();
        String description = DescriptionField.getText();
        double price = Double.parseDouble(PriceField.getText());
        int stockQuantity = Integer.parseInt(StockField.getText());

        String query = "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stockQuantity);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product saved successfully!");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    private void updateData() {
        int productId = Integer.parseInt(DescriptionField1.getText());
        String name = NameField.getText();
        String description = DescriptionField.getText();
        double price = Double.parseDouble(PriceField.getText());
        int stockQuantity = Integer.parseInt(StockField.getText());

        String query = "UPDATE products SET name = ?, description = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stockQuantity);
            pstmt.setInt(5, productId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product updated successfully!");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data: " + e.getMessage());
        }
    }

    private void deleteData() {
        int productId = Integer.parseInt(DescriptionField1.getText());

        String query = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product deleted successfully!");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting data: " + e.getMessage());
        }
    }

    private void clearFields() {
        NameField.setText ("");
        DescriptionField.setText("");
        PriceField.setText("");
        StockField.setText("");
        DescriptionField1.setText(""); // Assuming this field is for product ID
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        DescriptionField = new javax.swing.JTextField();
        PriceField = new javax.swing.JTextField();
        StockField = new javax.swing.JTextField();
        DisplayAllBTN = new javax.swing.JButton();
        UpdateBTN = new javax.swing.JButton();
        SaveBTN = new javax.swing.JButton();
        DeleteBTN = new javax.swing.JButton();
        DescriptionField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ExitBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setBackground(new java.awt.Color(0, 153, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Name", "Description", "Price", "Stock", "Created At"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setText("Description:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel3.setText("Price:");

        jLabel4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel4.setText("Stock:");

        NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameFieldActionPerformed(evt);
            }
        });

        DisplayAllBTN.setBackground(new java.awt.Color(102, 102, 102));
        DisplayAllBTN.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        DisplayAllBTN.setForeground(new java.awt.Color(255, 255, 255));
        DisplayAllBTN.setText("Refresh");
        DisplayAllBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayAllBTNActionPerformed(evt);
            }
        });

        UpdateBTN.setBackground(new java.awt.Color(102, 102, 102));
        UpdateBTN.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        UpdateBTN.setForeground(new java.awt.Color(255, 255, 255));
        UpdateBTN.setText("Update");
        UpdateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBTNActionPerformed(evt);
            }
        });

        SaveBTN.setBackground(new java.awt.Color(102, 102, 102));
        SaveBTN.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        SaveBTN.setForeground(new java.awt.Color(255, 255, 255));
        SaveBTN.setText("Save");
        SaveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBTNActionPerformed(evt);
            }
        });

        DeleteBTN.setBackground(new java.awt.Color(102, 102, 102));
        DeleteBTN.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        DeleteBTN.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBTN.setText("Delete");
        DeleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBTNActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel5.setText("ID:");

        ExitBTN.setBackground(new java.awt.Color(102, 102, 102));
        ExitBTN.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ExitBTN.setForeground(new java.awt.Color(255, 255, 255));
        ExitBTN.setText("Exit");
        ExitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(DescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StockField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DisplayAllBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DescriptionField1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(ExitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SaveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DeleteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(211, 211, 211))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(PriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(DescriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(StockField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DescriptionField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(DisplayAllBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameFieldActionPerformed

    private void DisplayAllBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayAllBTNActionPerformed
        loadData();
    }//GEN-LAST:event_DisplayAllBTNActionPerformed

    private void SaveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBTNActionPerformed
      saveData();
    }//GEN-LAST:event_SaveBTNActionPerformed

    private void UpdateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBTNActionPerformed
        updateData();
    }//GEN-LAST:event_UpdateBTNActionPerformed

    private void DeleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBTNActionPerformed
     deleteData();
    }//GEN-LAST:event_DeleteBTNActionPerformed

    private void ExitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBTNActionPerformed
        Login logForm = new Login();
        logForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ExitBTNActionPerformed

    public static void main(String args[]) {
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBTN;
    private javax.swing.JTextField DescriptionField;
    private javax.swing.JTextField DescriptionField1;
    private javax.swing.JButton DisplayAllBTN;
    private javax.swing.JButton ExitBTN;
    private javax.swing.JTextField NameField;
    private javax.swing.JTextField PriceField;
    private javax.swing.JButton SaveBTN;
    private javax.swing.JTextField StockField;
    private javax.swing.JButton UpdateBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
