package LineEditor.OBSOLETE.UI.windows;

import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shapes.Pipe;
import LineEditor.data.WorldGeometryData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nicole
 */
public class LineOptions extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9123869497270162044L;
	private Pipe 				rect;
	private WorldGeometryData 	data;

	
    /**
     * Creates new form LineValueEditor
     */
    public LineOptions(Pipe LINE, WorldGeometryData DATA) {
    	rect = LINE;
    	data = DATA;
        initComponents();
        setLocation(0, 154); // arbitrary
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valueLabel = new javax.swing.JLabel();
        xShiftLabel = new javax.swing.JLabel();
        yShiftLabel = new javax.swing.JLabel();
        xShiftTextField = new javax.swing.JTextField();
        yShiftTextField = new javax.swing.JTextField();
        shiftButton = new javax.swing.JButton();
        scaleLabel = new javax.swing.JLabel();
        splitLabel = new javax.swing.JLabel();
        rotatorLabel = new javax.swing.JLabel();
        scaleTextField = new javax.swing.JTextField();
        scaleButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        splitterTextField = new javax.swing.JTextField();
        splitterButton = new javax.swing.JButton();
        leftSplitterPercent = new javax.swing.JLabel();
        centerSplitterPercent = new javax.swing.JLabel();
        rightSplitterPercent = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        rotatorTextField = new javax.swing.JTextField();
        rotatorSlider = new javax.swing.JSlider();
        splitterSlider = new javax.swing.JSlider();
        leftRotateDegree = new javax.swing.JLabel();
        middleRotateDegree = new javax.swing.JLabel();
        rightRotateDegree = new javax.swing.JLabel();
        degreeLabel = new javax.swing.JLabel();
        percentLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();

        setTitle("Line Value Editor");

        valueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        valueLabel.setText("Line Values:");

        xShiftLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        xShiftLabel.setText("x:");

        yShiftLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        yShiftLabel.setText("y:");

        xShiftTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        xShiftTextField.setText("0.0");
        xShiftTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xShiftTextFieldActionPerformed(evt);
            }
        });

        yShiftTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        yShiftTextField.setText("0.0");
        yShiftTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yShiftTextFieldActionPerformed(evt);
            }
        });

        shiftButton.setText("Shift");
        shiftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	shiftButtonActionPerformed(evt);
            }
        });

        scaleLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        scaleLabel.setText("Scale:");

        splitLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        splitLabel.setText("Split:");

        rotatorLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rotatorLabel.setText("Rotate:");

        scaleTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        scaleTextField.setText("1.0");
        scaleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scaleTextFieldActionPerformed(evt);
            }
        });

        scaleButton.setText("Scale");
        scaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	scaleButtonActionPerformed(evt);
            }
        });

        splitterTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        splitterTextField.setText("50");
        splitterTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitterTextFieldActionPerformed(evt);
            }
        });

        splitterButton.setText("Split");
        splitterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitterButtonActionPerformed(evt);
            }
        });

        leftSplitterPercent.setText("0%");
        centerSplitterPercent.setText("50%");
        rightSplitterPercent.setText("100%");
        
        splitterSlider.setMinimum(0);
        splitterSlider.setMaximum(100);
        splitterSlider.setValue(50);
        splitterSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				 splitSliderActionPerformed(e);
			}
        });

        rotatorTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        rotatorTextField.setText("0");
        rotatorTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotatorTextFieldActionPerformed(evt);
            }
        });
        
        rotatorSlider.setMinimum(-180);
        rotatorSlider.setMaximum(180);
        rotatorSlider.setValue(0);
        rotatorSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				 rotatorSliderActionPerformed(e);
			}
        });

        leftRotateDegree.setText("-180°");
        middleRotateDegree.setText("0°");
        rightRotateDegree.setText("180°");

        degreeLabel.setText("°");
        percentLabel.setText("%");

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addComponent(splitterSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(rotatorSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valueLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(yShiftLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(yShiftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scaleLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(splitLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(splitterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(percentLabel)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(leftSplitterPercent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(centerSplitterPercent)
                        .addGap(44, 44, 44)
                        .addComponent(rightSplitterPercent)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(scaleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xShiftLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xShiftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(shiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rotatorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rotatorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(degreeLabel)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leftRotateDegree)
                        .addGap(59, 59, 59)
                        .addComponent(middleRotateDegree)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rightRotateDegree)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(splitterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(valueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xShiftLabel)
                    .addComponent(xShiftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shiftButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yShiftLabel)
                    .addComponent(yShiftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(scaleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scaleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scaleButton))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(splitLabel)
                    .addComponent(splitterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(percentLabel))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftSplitterPercent)
                    .addComponent(centerSplitterPercent)
                    .addComponent(rightSplitterPercent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitterSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(splitterButton)
                .addGap(13, 13, 13)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotatorLabel)
                    .addComponent(rotatorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(degreeLabel))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leftRotateDegree)
                    .addComponent(middleRotateDegree)
                    .addComponent(rightRotateDegree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotatorSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteButton)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scaleButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
    	try{
    		rect.scale(Float.parseFloat(scaleTextField.getText().trim()));
    	}catch(Exception e){}
    	scaleTextField.setText("1.0");
	}

	private void shiftButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
    	try{
    		rect.setCenterPosition(Float.parseFloat(xShiftTextField.getText().trim()), Float.parseFloat(yShiftTextField.getText().trim()));
    	}catch(Exception e){}
    	xShiftTextField.setText("0");
    	yShiftTextField.setText("0");
	}

	private void scaleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    	try{
    		rect.scale(Float.parseFloat(scaleTextField.getText().trim()));
    	}catch(Exception e){}
    	scaleTextField.setText("1.0");
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void rotatorTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    	try{
    		rect.rotate(Integer.parseInt(rotatorTextField.getText().trim()));
    		//rotatorSlider.setValue(Integer.parseInt(rotatorTextField.getText().trim()));
    	}catch(Exception e){}
    	rotatorTextField.setText("0");
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed
    
    private void rotatorSliderActionPerformed(ChangeEvent e){
        // TODO add your handling code here:
    	try{
    		rotatorTextField.setText(""+rotatorSlider.getValue());
    		rect.rotate(Integer.parseInt(rotatorTextField.getText().trim()));
    	}catch(Exception ex){}
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void splitterTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    	try{
    		
    		data.splitCollisionBox(rect, Integer.parseInt(splitterTextField.getText().trim()));
    		
    		//splitterSlider.setValue(Integer.parseInt(splitterTextField.getText().trim()));
    	}catch(Exception e){}
    	splitterTextField.setText("50");
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed
    
    private void splitSliderActionPerformed(ChangeEvent e){//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    	try{
    		splitterTextField.setText(""+splitterSlider.getValue());
    		//WorldEditorUITool.drawPoint(g, p, color);
    		//data.split(rect, Integer.parseInt(splitterTextField.getText().trim()));
    	}catch(Exception ex){}
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void xShiftTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
        // TODO add your handling code here:
    	try{
    		rect.shift(Float.parseFloat(xShiftTextField.getText().trim()), 0f);
    	}catch(Exception e){}
    	xShiftTextField.setText("0");
    }//GEN-LAST:event_jFormattedTextField4ActionPerformed

    private void yShiftTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField5ActionPerformed
        // TODO add your handling code here:
    	try{
    		rect.shift(0f, Float.parseFloat(yShiftTextField.getText().trim()));
    	}catch(Exception e){}
    	yShiftTextField.setText("0");
    }//GEN-LAST:event_jFormattedTextField5ActionPerformed

    
    private void splitterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitButtonActionPerformed
        // TODO add your handling code here:
    	try{
    		data.splitCollisionBox(rect, Integer.parseInt(splitterTextField.getText().trim()));
    		
    	}catch(Exception e){}
    	splitterTextField.setText("50");
    	
    }//GEN-LAST:event_splitButtonActionPerformed
    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
    	data.remove(rect);
    	this.dispose();
    }//GEN-LAST:event_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel centerSplitterPercent;
    private javax.swing.JLabel degreeLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel leftRotateDegree;
    private javax.swing.JLabel leftSplitterPercent;
    private javax.swing.JLabel middleRotateDegree;
    private javax.swing.JLabel percentLabel;
    private javax.swing.JLabel rightRotateDegree;
    private javax.swing.JLabel rightSplitterPercent;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel rotatorLabel;
    private javax.swing.JSlider rotatorSlider;
    private javax.swing.JTextField rotatorTextField;
    private javax.swing.JButton scaleButton;
    private javax.swing.JLabel scaleLabel;
    private javax.swing.JTextField scaleTextField;
    private javax.swing.JButton shiftButton;
    private javax.swing.JButton splitterButton;
    private javax.swing.JLabel splitLabel;
    private javax.swing.JSlider splitterSlider;
    private javax.swing.JTextField splitterTextField;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JLabel xShiftLabel;
    private javax.swing.JTextField xShiftTextField;
    private javax.swing.JLabel yShiftLabel;
    private javax.swing.JTextField yShiftTextField;
    // End of variables declaration//GEN-END:variables
}
