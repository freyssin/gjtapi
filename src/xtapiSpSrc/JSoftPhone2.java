/*
 * JSoftPhone.java
 *
 * Created on February 24, 2002, 8:48 AM
 */
import javax.telephony.*;
import javax.telephony.events.*;
import javax.telephony.media.events.*;

/*
*  XTAPI JTapi implementation
*  Copyright (C) 2002 Steven A. Frare
* 
*  This program is free software; you can redistribute it and/or
*  modify it under the terms of the GNU General Public License
*  as published by the Free Software Foundation; either version 2
*  of the License, or (at your option) any later version.
*  
*  This program is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*  
*  You should have received a copy of the GNU General Public License
*  along with this program; if not, write to the Free Software
*  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*
 * @author  Steven A. Frare
 * @version .01
 */

 // Provider around line 584
 
public class JSoftPhone2 extends javax.swing.JFrame implements CallObserver{

    private Provider    myprovider  = null;
    private Terminal    m_terminal  = null;
    private Call        m_call      = null;
    private Connection  m_connections[] = null;
    private TerminalConnection m_terminalConnection = null;
    private TerminalConnection m_destTerminalConnection = null;
    private boolean     bOrig = false;  // Did we originate this call?
    
    /** Creates new form JSoftPhone */
    public JSoftPhone2() {
        initComponents ();
        btnCall.setMnemonic('c'); 
        btnAnswer.setMnemonic('a'); 
        btnHangup.setMnemonic('h'); 
        pack ();
        
        java.awt.Rectangle r = getBounds();
        setBounds(r);
        setResizable(false);
        
        java.awt.Toolkit oToolkit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension d = oToolkit.getScreenSize();
        setLocation((d.width / 2) - (r.width / 2),(d.height / 2) - (r.height / 2));
        
        setTitle("JSoftPhone");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnPound = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnStar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lstLines = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        txtPhone = new javax.swing.JTextField();
        lblMisc = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnCall = new javax.swing.JButton();
        btnAnswer = new javax.swing.JButton();
        btnHangup = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblOrigConnState = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblOrigTConnState = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblConnState = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTermConnState = new javax.swing.JLabel();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        }
        );
        
        jPanel1.setLayout(new java.awt.GridLayout(4, 3));
        
        btn1.setText("1");
          btn1.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton1ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn1);
          
          
        btn2.setText("2");
          btn2.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton2ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn2);
          
          
        btn3.setText("3");
          btn3.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton3ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn3);
          
          
        btn4.setText("4");
          btn4.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton4ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn4);
          
          
        btn5.setText("5");
          btn5.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton5ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn5);
          
          
        btn6.setText("6");
          btn6.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton6ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn6);
          
          
        btn7.setText("7");
          btn7.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton7ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn7);
          
          
        btn8.setText("8");
          btn8.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton8ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn8);
          
          
        btn9.setText("9");
          btn9.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton9ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn9);
          
          
        btnPound.setText("#");
          btnPound.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton10ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btnPound);
          
          
        btn0.setText("0");
          btn0.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton11ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btn0);
          
          
        btnStar.setText("*");
          btnStar.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  jButton12ActionPerformed(evt);
              }
          }
          );
          jPanel1.add(btnStar);
          
          
        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);
        
        
        jPanel2.setLayout(new java.awt.GridLayout(2, 1));
        
        lstLines.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  lstLinesActionPerformed(evt);
              }
          }
          );
          lstLines.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
              public void propertyChange(java.beans.PropertyChangeEvent evt) {
                  lstLinesPropertyChange(evt);
              }
          }
          );
          jPanel2.add(lstLines);
          
          
        jPanel9.setLayout(new java.awt.GridLayout(1, 2));
          
          jPanel9.add(txtPhone);
            
            
          lblMisc.setText("N/A");
            jPanel9.add(lblMisc);
            
            jPanel2.add(jPanel9);
          
          
        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        
        
        jPanel3.setLayout(new java.awt.GridLayout(3, 1));
        
        btnCall.setText("Call");
          btnCall.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  btnCallActionPerformed(evt);
              }
          }
          );
          jPanel3.add(btnCall);
          
          
        btnAnswer.setText("Answer");
          btnAnswer.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  btnAnswerActionPerformed(evt);
              }
          }
          );
          jPanel3.add(btnAnswer);
          
          
        btnHangup.setText("Hangup");
          btnHangup.addActionListener(new java.awt.event.ActionListener() {
              public void actionPerformed(java.awt.event.ActionEvent evt) {
                  btnHangupActionPerformed(evt);
              }
          }
          );
          jPanel3.add(btnHangup);
          
          
        getContentPane().add(jPanel3, java.awt.BorderLayout.EAST);
        
        
        jPanel4.setLayout(new java.awt.GridLayout(2, 2));
        
        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(1));
          
          lblOrigConnState.setText("N/A");
            jPanel7.add(lblOrigConnState);
            
            jPanel4.add(jPanel7);
          
          
        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(1));
          
          lblOrigTConnState.setText("N/A");
            jPanel8.add(lblOrigTConnState);
            
            jPanel4.add(jPanel8);
          
          
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(1));
          
          lblConnState.setText("N/A");
            jPanel5.add(lblConnState);
            
            jPanel4.add(jPanel5);
          
          
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(1));
          
          lblTermConnState.setText("N/A");
            jPanel6.add(lblTermConnState);
            
            jPanel4.add(jPanel6);
          
          
        getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

  private void btnHangupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangupActionPerformed
// Add your handling code here:
/*      
      for(int loop = 0; loop < m_connections.length; loop++)
      {
          try{
            m_connections[loop].disconnect();
          }catch(Exception e){
              System.out.println("Disconnect exception.");
          }
      }
*/
    try{
        if(true == bOrig)
            m_connections[0].disconnect();
        else
            m_connections[1].disconnect();
    }catch(Exception e){
        System.out.println("Hangup Error: " + e.toString());
    }
    
    // Take a nap for an arbitrary amount of time and see if we get any events.
    int timeOut = 0;
    while(timeOut < 10)
    {
        try{
            Thread.currentThread().sleep(20);
            timeOut++;
        } catch (Exception excp) {
            System.out.println("Event wait error: " + excp.toString());  
        }
    }
    
    // Reset our member vars.
    m_call      = null;
    m_connections = null;
    m_terminalConnection = null;
    m_destTerminalConnection = null;
    
    lstLines.enable(true);
     
  }//GEN-LAST:event_btnHangupActionPerformed

  private void btnAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnswerActionPerformed
// Add your handling code here:
      if(null == m_terminalConnection)
      {
          javax.swing.JOptionPane.showMessageDialog(
              this, "No TerminalConnection is available."); 
      }
      else
      {
          /* Answer the telephone Call using "inner class" thread */
          try {
		final TerminalConnection _tc = m_destTerminalConnection;
	     	Runnable r = new Runnable() {
		  public void run(){
		    try{
				_tc.answer();
		    } catch (Exception excp){
		      // handle answer exceptions
                        System.out.println(excp.toString());
		    }
			};
		
		};
		Thread T = new Thread(r);
		T.start();
          } catch (Exception excp) {
            // Handle Exceptions;
              System.out.println("Exception in btnAnswerActionPerformed: " + excp.toString()) ;
          }          
      }
  }//GEN-LAST:event_btnAnswerActionPerformed

  private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
// Add your handling code here:
      txtPhone.requestFocus();
  }//GEN-LAST:event_formWindowActivated

  private void btnCallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCallActionPerformed
// Add your handling code here:

    // Create the telephone call object and add an observer.
    if(null != m_terminal)
    {
        if(null == m_call)
        {
            try {
              m_call = myprovider.createCall();
              m_call.addObserver(this);
            } catch (Exception excp) {
              // Handle exceptions
                System.err.println("Exception creating call: " + excp) ;
            }

            try {
              Address[] a = m_terminal.getAddresses();
              m_connections = m_call.connect(m_terminal, a[0], txtPhone.getText());
            } catch (Exception excp) {
              System.out.println("Exception placing call: " + excp.toString());
            }

            try{

			// wait for event to come in
			Thread.currentThread().sleep(4000);	// hack -- should wait for event
			
            TerminalConnection[] tc = m_connections[0].getTerminalConnections();
            m_terminalConnection = tc[0];
            
            tc = m_connections[1].getTerminalConnections();
            m_destTerminalConnection = tc[0];
            Terminal t = m_destTerminalConnection.getTerminal();

            t.addCallObserver(this);

            }catch(Exception e){
            	//e.printStackTrace();
                System.out.println("Exception getting remote terminal");
            }
            bOrig = true;
            
            lstLines.enable(false);
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(
                this, "Please hangup the current call first.");
        }        
    }
    else
    {
        javax.swing.JOptionPane.showMessageDialog(
            this, "Please select a valid Terminal first.");
    }
  }//GEN-LAST:event_btnCallActionPerformed

  private void lstLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lstLinesActionPerformed
// Add your handling code here:
    javax.swing.JComboBox j = (javax.swing.JComboBox)evt.getSource();
       
    try{
        if(null != m_terminal)
            m_terminal.removeCallObserver(this);
    }catch(Exception e){}//ignore the error
        
    m_terminal = (Terminal)j.getSelectedItem();
   
    try{
        m_terminal.addCallObserver(this);
    }catch(Exception e){
        javax.swing.JOptionPane.showMessageDialog(
            this, "Unable to add a CallObserver to the selected Terminal.");        
    }
    //System.out.println("Selected Terminal " + m_terminal);
  }//GEN-LAST:event_lstLinesActionPerformed

  private void lstLinesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lstLinesPropertyChange
// Add your handling code here:
     
  }//GEN-LAST:event_lstLinesPropertyChange

  private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "*");
  }//GEN-LAST:event_jButton12ActionPerformed

  private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "0");
  }//GEN-LAST:event_jButton11ActionPerformed

  private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "#");
  }//GEN-LAST:event_jButton10ActionPerformed

  private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "9");
  }//GEN-LAST:event_jButton9ActionPerformed

  private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "8");
  }//GEN-LAST:event_jButton8ActionPerformed

  private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "7");
  }//GEN-LAST:event_jButton7ActionPerformed

  private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "6");
  }//GEN-LAST:event_jButton6ActionPerformed

  private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "5");
  }//GEN-LAST:event_jButton5ActionPerformed

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "4");
  }//GEN-LAST:event_jButton4ActionPerformed

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "3");
  }//GEN-LAST:event_jButton3ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "2");
  }//GEN-LAST:event_jButton2ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// Add your handling code here:
      txtPhone.setText(txtPhone.getText() + "1");
  }//GEN-LAST:event_jButton1ActionPerformed

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        myprovider.shutdown();
        System.exit (0);
    }//GEN-LAST:event_exitForm

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        JSoftPhone2 o = new JSoftPhone2 ();
        java.awt.Cursor cursor = o.getCursor();
        o.show ();
        
        o.setCursor(WAIT_CURSOR);
        o.initJTapi();
        o.setCursor(cursor);

    }
    
    private void initJTapi()
    {
        try {
          JtapiPeer peer = JtapiPeerFactory.getJtapiPeer(
            //"net.xtapi.XJtapiPeer");
            "net.sourceforge.gjtapi.GenericJtapiPeer");
          //myprovider = peer.getProvider(null);
          //myprovider = peer.getProvider("Serial");
          myprovider = peer.getProvider("net.sourceforge.gjtapi.raw.xtapi.XtapiProvider; XtapiSp = TAPI");
          
          Terminal[] t = myprovider.getTerminals();
          
          for(int loop = 0; loop < t.length; loop++)
          {
              lstLines.insertItemAt(t[loop],loop);
          }
        } catch (Exception excp) {
        	excp.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(
            this, "Can not get a provider.");
        }        
    }

    /**
     * Reports all events associated with the Call object. This method passes
     * an array of CallEv objects as its arguments which correspond to the list
     * of events representing the changes to the Call object as well as changes
     * to all of the Connection and TerminalConnection objects associated with
     * this Call.
     * <p>
     * @param eventList The list of Call events.
 */
    public void callChangedEvent(CallEv[] eventList) 
    {
        if(m_call == null)
        {
            bOrig = false;
            setMVars(eventList[0].getCall());
        }
        //System.out.println("callChangedEvent");
        
        for (int i = 0; i < eventList.length; i++) 
        {

            if (eventList[i] instanceof TermConnEv) 
                    terminalEvent(eventList[i],true);
            else if
                (eventList[i] instanceof ConnEv)
                    connectionEvent(eventList[i]);
            else
                System.out.println("Unknown callChangedEvent");
        }
    }
    
    private void setMVars(Call c)
    {
        m_call = c;
        try{
            

            m_connections = c.getConnections();
            
            TerminalConnection[] tc;
            
            if(bOrig == false)
            {
                m_call.addObserver(this);
                tc = m_connections[0].getTerminalConnections();
                m_terminalConnection = tc[0];
                Terminal t = m_terminalConnection.getTerminal();
                t.addCallObserver(this);                
            }            

            // We already have the m_terminalConnection var, we now need the
            // remote terminal connection.  Since m_call had to be null for us
            // to get here we did not place this call.  So the remote connection
            // is the origination connection which is always the first connection
            // the the m_connections array.
            tc = m_connections[1].getTerminalConnections();
            m_destTerminalConnection = tc[0];
            
            setConnectionLabel(false, m_connections[1].getState());
            setConnectionLabel(true, m_connections[0].getState());
            
            lstLines.enable(false);
            
        }catch(Exception e){
            System.out.println("Error in setMVars: " + e.toString());
        }
    }
    
    private void connectionEvent(CallEv evt)
    {
        try{
            if(m_connections != null)
            {
                setConnectionLabel(false, m_connections[1].getState());
                setConnectionLabel(true, m_connections[0].getState());
            }
        
        }catch(Exception e){
            System.out.println("Exception in connectionEvent: " + e.toString());
        }
    }
    
    public void terminalEvent(CallEv evt, boolean bEvent)
    {
        // We want to display the Origination and destination 
        // TerminalConnection events.  
        
        //Origination
        setTerminalConnLabel(true, m_terminalConnection.getState());
        
        //Destination
        if (m_destTerminalConnection != null)
	        setTerminalConnLabel(false, m_destTerminalConnection.getState());
        
    }
   
   private void setConnectionLabel(boolean orig, int state)
   {
       switch(state)
       {
           case ConnAlertingEv.ID:
               if(!orig)
                   lblConnState.setText("ALERTING");
               else
                   lblOrigConnState.setText("ALERTING");
               break;

           case ConnInProgressEv.ID:
               if(!orig)
                   lblConnState.setText("INPROGRESS");
               else
                   lblOrigConnState.setText("INPROGRESS");
               break;

           case ConnConnectedEv.ID:
               if(!orig)
                   lblConnState.setText("CONNECTED");
               else
                   lblOrigConnState.setText("CONNECTED");
               break;

           case ConnDisconnectedEv.ID:
               if(!orig)
                   lblConnState.setText("DISCONNECTED");
               else
               {
                   lblOrigConnState.setText("DISCONNECTED");
                   m_call = null;
               }
               break;

           case ConnUnknownEv.ID:
               if(!orig)
                   lblConnState.setText("UNKNOWN");
               else
               {
                   lblOrigConnState.setText("UNKNOWN");
                   m_call = null;
               }
               break;
               
           // Connections start out in the IDLE state when they
           // are created.
           case ConnCreatedEv.ID:
               if(!orig)
                   lblConnState.setText("IDLE");
               else
                   lblOrigConnState.setText("IDLE");
               break;
               
           default:
               if(!orig)
                   lblConnState.setText("UNKNOWN ID: " + state );
               else
                   lblOrigConnState.setText("UNKNOWN ID: " + state );
               break;
       }
   }
   private void setTerminalConnLabel(boolean orig, int state)
   {
       switch(state)
       {
           case TermConnActiveEv.ID:
               if(!orig)
                   lblTermConnState.setText("ACTIVE");
               else
                   lblOrigTConnState.setText("ACTIVE");
               break;

           case TermConnRingingEv.ID:
               if(!orig)
                   lblTermConnState.setText("RINGING");
               else
                   lblOrigTConnState.setText("RINGING");
               break;

           case TermConnDroppedEv.ID:
               if(!orig)
                   lblTermConnState.setText("DROPPED");
               else
                   lblOrigTConnState.setText("DROPPED");
               break;
               
           case TermConnUnknownEv.ID:
               if(!orig)
                   lblTermConnState.setText("UNKNOWN");
               else
                   lblOrigTConnState.setText("UNKNOWN");
               break;               

           // Terminal Connections start out in the IDLE state when they
           // are created.
           case TermConnCreatedEv.ID:
               if(!orig)
                   lblTermConnState.setText("IDLE");
               else
                   lblOrigTConnState.setText("IDLE");
               break;
               
           default:
               if(!orig)
                   lblTermConnState.setText("UNKNOWN ID: " + state );
               else
                   lblOrigTConnState.setText("UNKNOWN ID: " + state );
               break;
       }
       
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel jPanel1;
   private javax.swing.JButton btn1;
   private javax.swing.JButton btn2;
   private javax.swing.JButton btn3;
   private javax.swing.JButton btn4;
   private javax.swing.JButton btn5;
   private javax.swing.JButton btn6;
   private javax.swing.JButton btn7;
   private javax.swing.JButton btn8;
   private javax.swing.JButton btn9;
   private javax.swing.JButton btnPound;
   private javax.swing.JButton btn0;
   private javax.swing.JButton btnStar;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JComboBox lstLines;
   private javax.swing.JPanel jPanel9;
   private javax.swing.JTextField txtPhone;
   private javax.swing.JLabel lblMisc;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JButton btnCall;
   private javax.swing.JButton btnAnswer;
   private javax.swing.JButton btnHangup;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel7;
   private javax.swing.JLabel lblOrigConnState;
   private javax.swing.JPanel jPanel8;
   private javax.swing.JLabel lblOrigTConnState;
   private javax.swing.JPanel jPanel5;
   private javax.swing.JLabel lblConnState;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JLabel lblTermConnState;
   // End of variables declaration//GEN-END:variables

}
