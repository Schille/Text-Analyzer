package org.textanalyzer.frontend;
import java.awt.Font;
import javax.swing.JFrame;
import org.textanalyzer.profilemanager.ProfileManager;

/**
 * @author Katharina Sandrock
 * 
 */

public class FrontendProfileManager extends JFrame implements IFrontendProfileManager {
	
                 
    private javax.swing.JButton addButton;
    private javax.swing.JList authorList;
    private javax.swing.JPanel authorListPanel;
    private javax.swing.JScrollPane authorScrollPane;
    private javax.swing.JPanel bannerPanel;
    private javax.swing.JLabel bannerText;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel selectionPanel;
    private ProfileManager profileLogic;
    
    public FrontendProfileManager(ProfileManager myManager){
    	profileLogic = myManager;
    	
    }
    
    
    
	@Override
    public void showFrontendProfileManager(){
                    
	        authorListPanel = new javax.swing.JPanel();
	        authorScrollPane = new javax.swing.JScrollPane();

	        bannerPanel = new javax.swing.JPanel();
	        bannerText = new javax.swing.JLabel();
	        icon = new javax.swing.JLabel();
	        selectionPanel = new javax.swing.JPanel();
	        addButton = new javax.swing.JButton();
	        deleteButton = new javax.swing.JButton();

	        contentPanel = new javax.swing.JPanel();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        
	        authorList = new javax.swing.JList(profileLogic.getAuthorList().toArray());
	        
	
	        
	        authorList.setFont(new Font("Dialog",1,16));
	        authorScrollPane.setViewportView(authorList);

	        javax.swing.GroupLayout authorListPanelLayout = new javax.swing.GroupLayout(authorListPanel);
	        authorListPanel.setLayout(authorListPanelLayout);
	        authorListPanelLayout.setHorizontalGroup(
	            authorListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(authorListPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(authorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        authorListPanelLayout.setVerticalGroup(
	            authorListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(authorListPanelLayout.createSequentialGroup()
	                .addComponent(authorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 495, Short.MAX_VALUE))
	        );

	        bannerText.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
	        bannerText.setText("analytiX");

	        icon.setIcon(new javax.swing.ImageIcon("C:\\Users\\sandrock\\DHBW\\3. Semester\\Software-Engineering\\Coding\\icon.png")); // NOI18N

	        javax.swing.GroupLayout bannerPanelLayout = new javax.swing.GroupLayout(bannerPanel);
	        bannerPanel.setLayout(bannerPanelLayout);
	        bannerPanelLayout.setHorizontalGroup(
	            bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bannerPanelLayout.createSequentialGroup()
	                .addGap(61, 61, 61)
	                .addComponent(icon)
	                .addGap(324, 324, 324)
	                .addComponent(bannerText, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(340, Short.MAX_VALUE))
	        );
	        bannerPanelLayout.setVerticalGroup(
	            bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(bannerPanelLayout.createSequentialGroup()
	                .addGroup(bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(bannerPanelLayout.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(icon))
	                    .addGroup(bannerPanelLayout.createSequentialGroup()
	                        .addGap(29, 29, 29)
	                        .addComponent(bannerText, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(20, Short.MAX_VALUE))
	        );

	        addButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\sandrock\\DHBW\\3. Semester\\Software-Engineering\\Coding\\addButton.jpg")); // NOI18N
	        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                addButtonMouseClicked(evt);
	            }
	        });
	        addButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                addButtonActionPerformed(evt);
	            }
	        });

	        deleteButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\sandrock\\DHBW\\3. Semester\\Software-Engineering\\Coding\\deleteButton.jpg")); // NOI18N
	        deleteButton.setActionCommand("-");
	        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                deleteButtonMouseClicked(evt);
	            }
	        });
	        deleteButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                deleteButtonActionPerformed(evt);
	            }
	        });


	        javax.swing.GroupLayout selectionPanelLayout = new javax.swing.GroupLayout(selectionPanel);
	        selectionPanel.setLayout(selectionPanelLayout);
	        selectionPanelLayout.setHorizontalGroup(
	            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(selectionPanelLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        selectionPanelLayout.setVerticalGroup(
	            selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectionPanelLayout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addGroup(selectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );

	        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
	        contentPanel.setLayout(contentPanelLayout);
	        contentPanelLayout.setHorizontalGroup(
	            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );
	        contentPanelLayout.setVerticalGroup(
	            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                    .addComponent(selectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(authorListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            .addComponent(bannerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addComponent(bannerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(authorListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	        );

	        pack();
	        setVisible(true);
	    }                      

	    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {                                          
	        // TODO add your handling code here:
	    }                                         

	    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
	        // TODO add your handling code here:
	    }                                            
                                        

	    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {                                       
	        // add new author
	        contentPanel.removeAll();
	        contentPanel.add(addButton);

	    }                                      

	    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
	        // delete new author
	        
	        contentPanel.add(deleteButton);
	    }                                         
               
	}
