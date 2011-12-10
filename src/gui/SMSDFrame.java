package gui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SMSDFrame.java
 * @author Syed Asad Rahman, EBI-EMBL
 *         asad@ebi.ac.uk
 * @modifed Franz
 *
 * Created on Mar 29, 2009, 3:07:42 AM
 */
import gui.helper.FileExportFilter;
import gui.helper.ImagePreView;
import gui.helper.molFileFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.aromaticity.CDKHueckelAromaticityDetector;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.io.IChemObjectReader;
import org.openscience.cdk.io.MDLReader;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;
import org.openscience.smsd.AtomAtomMapping;
import org.openscience.smsd.Isomorphism;
import org.openscience.smsd.interfaces.Algorithm;
import org.openscience.smsd.tools.GraphMolecule;

/**
 *
 * @author sar
 */
public class SMSDFrame extends JFrame {

    private static final long serialVersionUID = 7878619981017L;
    /** Creates new form SMSDFrame */
    static private String newline = "\n";
    private File QueryFileName = null;
    private File TargetFileName = null;
    private List<Object> molFiles = new ArrayList<Object>(2);
    private Integer count;
    static private JPanel panels;
    File file = null;

    public SMSDFrame() {
        count = 0;
        initComponents();

//        System.out.println(buttonGroup1.getSelection().getActionCommand());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        SelectionPanel = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        OutputPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SelectionPanel.setBackground(new java.awt.Color(0, 204, 153));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Bond Sensitive");
        jRadioButton1.setToolTipText("Bond order match is turned on");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Bond InSensitive");
        jRadioButton2.setToolTipText("Bond order is not considered while matching");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Remove Hydrogen");
        jCheckBox1.setToolTipText("Make Hydrogen implicite");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Stereo Filter");

        jCheckBox3.setText("Fragment Filter");

        jCheckBox4.setText("Energy Filter");

        javax.swing.GroupLayout SelectionPanelLayout = new javax.swing.GroupLayout(SelectionPanel);
        SelectionPanel.setLayout(SelectionPanelLayout);
        SelectionPanelLayout.setHorizontalGroup(
                SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SelectionPanelLayout.createSequentialGroup().addGroup(SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jRadioButton1).addComponent(jCheckBox1).addComponent(jCheckBox3)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE).addGroup(SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jCheckBox2).addComponent(jRadioButton2).addComponent(jCheckBox4)).addContainerGap()));
        SelectionPanelLayout.setVerticalGroup(
                SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SelectionPanelLayout.createSequentialGroup().addContainerGap().addGroup(SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jRadioButton1).addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jCheckBox1).addComponent(jCheckBox2)).addGap(18, 18, 18).addGroup(SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jCheckBox3).addComponent(jCheckBox4)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        OutputPanel.setBackground(new java.awt.Color(0, 204, 204));

        jButton1.setText("Browse");
        jButton1.setActionCommand("QueryFile");
        buttonGroup2.add(jButton1);
        jButton1.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Browse");
        jButton3.setActionCommand("TargetFile");
        buttonGroup2.add(jButton3);
        jButton3.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jFormattedTextField1.setText("Enter Query Smiles");

        jFormattedTextField2.setText("Enter Target Smiles");

        jLabel1.setText("Read Source Mol File");

        jLabel2.setText("Read Target Mol File");

        jLabel3.setText("Enter Query Smiles");

        jLabel4.setText("Enter Target Smiles");

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Mol Format");

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("Smiles Format");

        javax.swing.GroupLayout OutputPanelLayout = new javax.swing.GroupLayout(OutputPanel);
        OutputPanel.setLayout(OutputPanelLayout);
        OutputPanelLayout.setHorizontalGroup(
                OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(OutputPanelLayout.createSequentialGroup().addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OutputPanelLayout.createSequentialGroup().addGap(19, 19, 19).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(OutputPanelLayout.createSequentialGroup().addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jFormattedTextField2, javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jFormattedTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(OutputPanelLayout.createSequentialGroup().addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OutputPanelLayout.createSequentialGroup().addComponent(jLabel1).addGap(18, 18, 18)).addGroup(OutputPanelLayout.createSequentialGroup().addComponent(jLabel2).addGap(20, 20, 20))).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))).addGroup(OutputPanelLayout.createSequentialGroup().addContainerGap().addComponent(jRadioButton3)).addGroup(OutputPanelLayout.createSequentialGroup().addContainerGap().addComponent(jRadioButton4))).addContainerGap()));
        OutputPanelLayout.setVerticalGroup(
                OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OutputPanelLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jRadioButton3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton1).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton3).addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jRadioButton4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(OutputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4)).addContainerGap()));

        jButton2.setText("Run");
        jButton2.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Small GraphMolecule SubGraph Detector (Isomorphism)");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(20);
        jTextArea1.setText("Progress ");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(35, 35, 35).addComponent(jLabel5)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addContainerGap().addComponent(SelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE).addComponent(OutputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addGroup(layout.createSequentialGroup().addGap(151, 151, 151).addComponent(jButton2))).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(SelectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(OutputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(3, 3, 3).addComponent(jButton2)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if (jRadioButton4.isSelected()) {
                try {
                    StructureDiagramGenerator sdg = new StructureDiagramGenerator();
                    SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
                    IAtomContainer query = new GraphMolecule(sp.parseSmiles(jFormattedTextField1.getText()));
                    molFiles.add(count++, jFormattedTextField1.getText());
                    molFiles.add(count++, query);
                    jTextArea1.append("Attaching smile: " + jFormattedTextField1.getText() + "." + newline);

                    IAtomContainer target = new GraphMolecule(sp.parseSmiles(jFormattedTextField2.getText()));
                    molFiles.add(count++, jFormattedTextField2.getText());
                    molFiles.add(count++, target);
                    jTextArea1.append("Attaching smile: " + jFormattedTextField2.getText() + "." + newline);

                    jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
                } catch (InvalidSmilesException ise) {
                    ise.printStackTrace();
                }
            }

            //Mappping

            JFrame frame = new JFrame("Isomorphism Maxmimum Common Substructure (Highlighted)");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            GridBagConstraints gbc = new GridBagConstraints();

            if (molFiles.size() >= 4) {

                IAtomContainer QMol = (IAtomContainer) molFiles.get(1);
                IAtomContainer TMol = (IAtomContainer) molFiles.get(3);
                jTextArea1.append("Calculating MCS...." + "." + newline);
//                System.out.println("Calculating MCS....");

                if (jCheckBox1.isSelected()) {

                    QMol = AtomContainerManipulator.removeHydrogens(QMol);
                    TMol = AtomContainerManipulator.removeHydrogens(TMol);

                }
                AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(TMol);
                AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(QMol);
                CDKHueckelAromaticityDetector.detectAromaticity(TMol);
                CDKHueckelAromaticityDetector.detectAromaticity(QMol);

//                CanonicalLabeler c=new CanonicalLabeler();
//                c.canonLabel(QMol);
//                c.canonLabel(TMol);
                Isomorphism comparison = new Isomorphism(QMol, TMol, Algorithm.DEFAULT, jRadioButton1.isSelected(), false);
                comparison.setChemFilters(jCheckBox2.isSelected(), jCheckBox3.isSelected(), jCheckBox4.isSelected());


                jTextArea1.append("Number of overlaps = " + comparison.getFirstAtomMapping().getCount() + newline);
                jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());

                String[] QName = ((String) molFiles.get(0)).split(".mol");
                String[] TName = ((String) molFiles.get(2)).split(".mol");
                final String FileName = QName[0] + "_" + TName[0];


                JLabel l1 = new JLabel("Query: " + (String) molFiles.get(0), SwingConstants.CENTER);
                JLabel l2 = new JLabel("Target: " + (String) molFiles.get(2), SwingConstants.CENTER);

                GridBagLayout gridbag = new GridBagLayout();

                panels = new JPanel(gridbag);
                gbc.gridwidth = GridBagConstraints.REMAINDER;//GridBagConstraints.HORIZONTAL;
                final JPanel jPanel = new JPanel(gridbag);
                jPanel.setPreferredSize(new Dimension(300, 30));
                jPanel.add(l1, gbc);
                jPanel.add(l2, gbc);
                jPanel.setBackground(Color.cyan);
                panels.add(jPanel, gbc);
                panels.add(new JLabel(new ImageIcon(generateImage(QMol, TMol, comparison))));


                JButton button = new JButton("Save as");
                JPanel southPanel = new JPanel();
                southPanel.add(button);
//
//              Comments Asad: If you want to colose all after saving then uncomment this
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new JScrollPane(panels));
                frame.getContentPane().add(southPanel, "South");

                button.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String path = System.getProperty("user.dir");
                        JFileChooser fc = new JFileChooser(path);
                        FileExportFilter.addChoosableFileFilters(fc);
                        int rc = fc.showDialog(null, "Save");
                        if (rc == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();

                            String type = null;
                            FileFilter currentFilter = fc.getFileFilter();
                            type = ((FileExportFilter) currentFilter).getType();

                            int width = panels.getWidth();
                            int height = panels.getHeight();
                            BufferedImage image = new BufferedImage(width, height,
                                    BufferedImage.TYPE_INT_RGB);
                            Graphics2D g2 = image.createGraphics();
                            panels.paint(g2);
                            panels.doLayout();

                            g2.dispose();

                            try {
                                String fileName = file.getAbsolutePath();//Path + File.separator + fc.getName();
                                ImageIO.write(image, type, new File(fileName + "." + type));
                            } catch (IOException ioe) {
                                jTextArea1.append("Note: " + ioe.getMessage() + newline);
                                jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
//                                System.out.println(ioe.getMessage());
                            }
                        } else {
                            jTextArea1.append("File chooser cancel button clicked" + newline);
                            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
//
//                            System.out.println("File chooser cancel button clicked");
                            return;
                        }
                    }
                });

                molFiles.clear();
                count = 0;

                //Display the window.
                frame.setSize(400, 300);
                frame.setLocation(400, 300);
                frame.pack();
                frame.setVisible(true);
            } else {
                jTextArea1.append("Error: " + "Please provide query and target molecule information" + newline);
                jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
//
//                System.out.println("Error: " + "Please provide query and target molecule information");
            }


        } catch (Exception ex) {
            Logger.getLogger(SMSDFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private IAtomContainer readMol(File fileName) {

        IAtomContainer container = null;
        try {
            MDLReader reader2 = new MDLReader(new FileReader(fileName), IChemObjectReader.Mode.RELAXED);
            IAtomContainer molecule = (IAtomContainer) reader2.read(new GraphMolecule());
            reader2.close();

            count = 0;
            container = molecule;
//            container = AtomContainerManipulator.removeHydrogens(mol);
            jTextArea1.append("Created Mol = " + fileName.getName() + newline);
            jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
//            System.out.println("Created Mol = " + fileName.getName());
        } catch (Exception ex) {
            Logger.getLogger(SMSDFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return container;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String wd = System.getProperty("user.dir");
        JFileChooser fileChooser = new JFileChooser(wd);
        String ac = evt.getActionCommand();
//      File file = null;
        fileChooser = null;
        if (fileChooser == null) {
            fileChooser = new JFileChooser(wd);
            //Add a custom file filter and disable the default
            //(Accept All) file filter.
            fileChooser.addChoosableFileFilter(new molFileFilter());
            fileChooser.setAcceptAllFileFilterUsed(false);

            fileChooser.setAccessory(new ImagePreView(fileChooser));
        }

        if (file != null) {
            fileChooser.setSelectedFile(file.getParentFile());
        }

        //Show it.
        int returnVal = fileChooser.showDialog(SMSDFrame.this,
                "Attach");

        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (ac.equals("QueryFile")) {
                try {
                    QueryFileName = fileChooser.getSelectedFile();
                    IAtomContainer mol = readMol(QueryFileName);
                    molFiles.add(count++, QueryFileName.getName());
                    molFiles.add(count++, mol);
                    jTextArea1.append("Attaching file: " + QueryFileName.getName() + "." + newline);
                } catch (Exception ex) {
                    Logger.getLogger(SMSDFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ac.equals("TargetFile")) {
                try {
                    TargetFileName = fileChooser.getSelectedFile();
                    IAtomContainer mol = readMol(TargetFileName);
                    molFiles.add(count++, TargetFileName.getName());
                    molFiles.add(count++, mol);
                    jTextArea1.append("Attaching file: " + TargetFileName.getName() + "." + newline);
                } catch (Exception ex) {
                    Logger.getLogger(SMSDFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            jTextArea1.append("Attachment cancelled by user." + newline);
        }
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());

        //Reset the file chooser for the next time it's shown.
        fileChooser.setSelectedFile(null);

    }//GEN-LAST:event_jButton1ActionPerformed

    private static Image generateImage(IAtomContainer query, IAtomContainer target, Isomorphism smsd) throws Exception {

        ImageGenerator imageGenerator = new ImageGenerator();

        ////set the format right for the Tanimoto score (only two digits printed)
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        System.out.println("Output of the final Mappings: ");
        int counter = 1;
        for (AtomAtomMapping aam : smsd.getAllAtomMapping()) {
            Map<IAtom, IAtom> mappings = aam.getMappings();
            Map<Integer, Integer> mapping = new TreeMap<Integer, Integer>();
            for (IAtom keys : mappings.keySet()) {
                mapping.put(aam.getQueryIndex(keys), aam.getTargetIndex(mappings.get(keys)));
            }

            String tanimoto = nf.format(smsd.getTanimotoSimilarity());
            String stereo = "NA";
            if (smsd.getStereoScore(counter - 1) != null) {
                stereo = nf.format(smsd.getStereoScore(counter - 1));
            }
            String label = "Scores [" + "Tanimoto: " + tanimoto + ", Stereo: " + stereo + "]";
            imageGenerator.addImages(query, target, label, mapping);
            counter++;

        }
        return (Image) imageGenerator.createImage();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SMSDFrame().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OutputPanel;
    private javax.swing.JPanel SelectionPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
