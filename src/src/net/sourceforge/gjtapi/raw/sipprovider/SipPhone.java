/* ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2000 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Apache" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * Large portions of this software are based upon public domain software
 * https://sip-communicator.dev.java.net/
 *
 *//*
 * SipProvider.java
 *
 * Created on November 18, 2003, 2:18 PM
 */

package net.sourceforge.gjtapi.raw.sipprovider;

import net.sourceforge.gjtapi.raw.sipprovider.sip.CommunicationsException;
import net.sourceforge.gjtapi.raw.sipprovider.sip.SipManager;
import net.sourceforge.gjtapi.raw.sipprovider.sip.security.SecurityAuthority;
import net.sourceforge.gjtapi.raw.sipprovider.sip.security.UserCredentials;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.CallRejectedEvent;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.CallStateEvent;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.CommunicationsErrorEvent;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.CommunicationsListener;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.MessageEvent;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.RegistrationEvent;
import net.sourceforge.gjtapi.raw.sipprovider.sip.event.UnknownMessageEvent;
import net.sourceforge.gjtapi.raw.sipprovider.media.MediaException;
import net.sourceforge.gjtapi.raw.sipprovider.media.MediaManager;
import net.sourceforge.gjtapi.raw.sipprovider.media.event.MediaErrorEvent;
import net.sourceforge.gjtapi.raw.sipprovider.media.event.MediaEvent;
import net.sourceforge.gjtapi.raw.sipprovider.media.event.MediaListener;
import net.sourceforge.gjtapi.raw.sipprovider.common.Console;
import net.sourceforge.gjtapi.raw.sipprovider.common.NetworkAddressManager;
import java.util.*;
import net.sourceforge.gjtapi.*;
import javax.telephony.*;
/**
 *
 * @author  root
 */
public class SipPhone implements  MediaListener, CommunicationsListener,  SecurityAuthority,net.sourceforge.gjtapi.raw.sipprovider.sip.event.CallListener
{
    private TelephonyListener listener;
    private List addresses;
    private TermData terminal;
    private final static String RESOURCE_NAME = "sip.props";
    private Properties properties = new Properties();
    protected MediaManager mediaManager;
    protected SipManager sipManager;
    protected static Console console = Console.getConsole(SipPhone.class);
    private CallId idd;
    private Vector idVector = new Vector();
    private String address ;
    private SipProvider sipProvider;
    public Properties sipProp;
    /** Add an observer for RawEvents
     *
     * @param ro New event listener
     * @return void
     *
     */
    //constructeur
    public SipPhone( Properties sipProperties , SipProvider sipProvider)
    {
        sipProp = new Properties() ;
        sipProp.putAll(sipProperties);
        mediaManager = new MediaManager(sipProp);
    
        this.sipProvider = sipProvider;
        sipManager = new SipManager(sipProp);
        this.launch();
        address = "sip:" + sipManager.getLocalUser() + "@" + sipManager.getLocalHostAddress() ;
        
    }
    public String getAddress()
    {
        return address;
    }
    
    //sip call control section-------------------------------------------------------------------------
    public void createCall(CallId id, String address, String term, String dest) throws
    ResourceUnavailableException, PrivilegeViolationException, InvalidPartyException, InvalidArgumentException, RawStateException, MethodNotSupportedException
    {
        console.logEntry();
        console.debug("id = " + id);
        idd = id;
        
        try
        {
            
            console.debug("trentative de connection  a " +address);
            //CREATION D'UN CALL (SIP)
            net.sourceforge.gjtapi.raw.sipprovider.sip.Call call =  sipManager.establishCall(dest, mediaManager.generateSdpDescription());
            
            SipCallId sipCallId = (SipCallId)(id);
            sipCallId.setSipId(call.getID());
            idVector.add(new ListIdElement(id, call.getID(), term, dest));
            
       
            call.addStateChangeListener(this);
        
            
        }
        catch (net.sourceforge.gjtapi.raw.sipprovider.media.MediaException ex)
        {
            console.debug(ex.toString());
        }
        catch ( net.sourceforge.gjtapi.raw.sipprovider.sip.CommunicationsException ex)
        {
            console.debug(ex.toString());
        }
        
    }
    
    public void SipHangup(CallId callId)
    {
        try
        {
            console.logEntry();
            ListIdElement lI = this.getElementIdListByJtapiId(callId);
            sipManager.endCall( lI.getSipId());
            
        }
        catch (CommunicationsException exc)
        {
            console.showException("Could not properly terminate call!\n"
            + "(This is not a fatal error)",
            exc
            );
        }
        finally
        {
            console.logExit();
        }
    }
    // end of sip call control section--------------------------------------------------
    
    ///---------------------------------------------------------------------
    // *************************************************************************
    // *************************************************************************
    // ************************************************************************
    ///sip------------------------------------------------------------------
    //======================= CALL LISTENER ==============================
    //callback du sipManager
    public void callStateChanged(CallStateEvent evt)
    {
        console.logEntry();
        console.debug("++++++++++++++++++++++++++++++++++++++++  ++++++++++++++++"+evt.getNewState()+"    old   " +evt.getOldState());
        
        try
        {
            
            net.sourceforge.gjtapi.raw.sipprovider.sip.Call call = evt.getSourceCall();
            int sipId = evt.getSourceCall().getID();
            ListIdElement el =  getElementIdListBySipId(sipId);
            
            
            if (evt.getNewState() ==  net.sourceforge.gjtapi.raw.sipprovider.sip.Call.RINGING)
            {
                if (evt.getOldState() == net.sourceforge.gjtapi.raw.sipprovider.sip.Call.DIALING)
                {
                    System.out.println("remote address = " + el.getAddress());
                    
                    sipProvider.sipTerminalConnectionCreated(el.getJtapiId(), el.getAddress() , "remote", ConnectionEvent.CAUSE_NORMAL );
                    sipProvider.sipConnectionInProgress(el.getJtapiId(), el.getAddress(), Event.CAUSE_NORMAL);
                    sipProvider.sipConnectionAlerting(el.getJtapiId(), el.getAddress(), ConnectionEvent.CAUSE_NORMAL);
                    
                   
                }
              
                
            }
            if (evt.getNewState() ==  net.sourceforge.gjtapi.raw.sipprovider.sip.Call.CONNECTED)
            {
                console.debug("+++++++++++++          ++++++      +++++ conecTTTTEDDD");
                
                // sipProvider.sipConnectionConnected(el.getJtapiId(), el.getAddress(), ConnectionEvent.CAUSE_NORMAL);
                sipProvider.sipConnectionConnected(el.getJtapiId(), el.getAddress(), ConnectionEvent.CAUSE_NORMAL);
                sipProvider.sipCallActive(el.getJtapiId(), Event.CAUSE_NORMAL);
                try
                {
                    this.mediaManager.openMediaStreams(call.getRemoteSdpDescription());
                    
                    
                }
                catch (net.sourceforge.gjtapi.raw.sipprovider.media.MediaException ex)
                {
                    console.debug(ex.toString());
                }
                
                
            }
            else if (evt.getNewState() ==  net.sourceforge.gjtapi.raw.sipprovider.sip.Call.DISCONNECTED)
            {
                console.debug("++++++++++++ +          ++++++      +++++ DIIIS conecTTTTEDDD");
                //listener.connectionSuspended(el.getJtapiId(), el.getAddress(), ConnectionEvent.CAUSE_NORMAL);
                try
                {
                    mediaManager.closeStreams(call.getRemoteSdpDescription());
                }
                catch (MediaException ex)
                {
                    console.showException(        "The following exception occurred while trying to open media connection:\n"
                    + ex.getMessage(),           ex);
                }
                call.getRemoteSdpDescription();
                console.debug("+++++++++++++          ++++++      +++++ DIIIS conecTTTTEDDD"+ call.getRemoteName());
                sipProvider.sipConnectionDisconnected(el.getJtapiId(), el.getAddress(), ConnectionEvent.CAUSE_NORMAL);
            }
        }
        finally
        {
            console.logExit();
        }
    }
    
    public void messageReceived(MessageEvent evt)
    {
      /*  try
        {
            console.logEntry();
            String fromAddress = evt.getFromAddress();
            String fromName = evt.getFromName();
            String messageBody = evt.getBody();
            console.showDetailedMsg(
            "Incoming MESSAGE",
            "You received a MESSAGE\n"
            + "From:    " + fromName + "\n"
            + "Address: " + fromAddress + "\n"
            + "Message: " + messageBody + "\n");
        }
        finally
        {
            console.logExit();
        }*/
    }
    
    public void nonFatalMediaErrorOccurred(MediaErrorEvent evt)
    {
        console.logEntry();
    }
    
    /** Returns a Credentials object associated with the specified realm.
     * @param realm The realm that the credentials are needed for.
     * @param defaultValues the values to propose the user by default
     * @return The credentials associated with the specified realm or null if
     * none could be provided.
     *
     */
    public UserCredentials obtainCredentials(String realm, UserCredentials defaultValues)
    {
        console.logEntry();
        return null;
    }
    
    public void playerStarting(MediaEvent evt)
    {console.logEntry();
    }
    
    public void playerStopped()
    {console.logEntry();
    }
    
    public void receivedUnknownMessage(UnknownMessageEvent evt)
    {console.logEntry();
    }
    
    public void registered(RegistrationEvent evt)
    {console.logEntry();
    }
    
    public void registering(RegistrationEvent evt)
    {
    }
    
    
    public void callReceived(net.sourceforge.gjtapi.raw.sipprovider.sip.event.CallEvent evt)
    {
        console.logEntry();
    }
    
    public void callRejectedLocally(CallRejectedEvent evt)
    {
        console.logEntry();
    }
    
    public void callRejectedRemotely(CallRejectedEvent evt)
    {
        console.logEntry();
    }
    
    
    
    public void communicationsErrorOccurred(CommunicationsErrorEvent evt)
    {
        console.logEntry();
    }
    
    
    
    
    
    public void unregistered(RegistrationEvent evt)
    {console.logEntry();
    }
    
    
    
    //init methode section---------------------------------------------------------------
    public void launch()
    {
        try
        {
            console.logEntry();
            //mode = PHONE_MODE;
            NetworkAddressManager.start();
            try
            {
                mediaManager.start();
                
                
            }
            catch (MediaException exc)
            {
                console.error("Failed to start mediaManager", exc);
                console.showException(
                "The following exception occurred while initializing media!\n"
                + exc.getMessage(),
                exc);
            }
            mediaManager.addMediaListener(this);            
                     
            sipManager.addCommunicationsListener(this);
            sipManager.setSecurityAuthority(this);
            
            
            try
            {
                sipManager.start();
                if (sipManager.isStarted())
                {
                    terminal = new TermData("sip:" + sipManager.getLocalUser() + "@" + sipManager.getLocalHostAddress(),true );// + "@" + sipManager.getLocalHostAddress(), true);
                    
                    console.trace(
                    "sipManager appears to be successfully started");
                    //  guiManager.setCommunicationActionsEnabled(true);
                }
            }
            catch (CommunicationsException exc)
            {
                console.showException(
                "An exception occurred while initializing communication stack!\n"
                + "You won't be able to send or receive calls",
                exc);
                return;
            }
            /*try
            {
                sipManager.register();
            }
            catch (CommunicationsException exc)
            {
                console.error(
                "An exception occurred while trying to register, exc");
                console.showException(
                "Failed to register!\n"
                + exc.getMessage() + "\n"
                + "This is a warning only. The phone would still function",
                exc);
            }*/
        }
        finally
        {
            console.logExit();
        }
    }
    //end of init method section-------------------------------------------------------
    
    private ListIdElement getElementIdListBySipId(int sipId)
    {
        ListIdElement ret = null;
        Enumeration enum = idVector.elements();
        while (enum.hasMoreElements())
        {
            ListIdElement lst = (ListIdElement)enum.nextElement();
            if ( lst.getSipId() == sipId)
            {
                ret = lst;
            }
        }
        return ret;
    }
    
    private ListIdElement getElementIdListByJtapiId(CallId callid)
    {
        ListIdElement ret = null;
        Enumeration enum = idVector.elements();
        while (enum.hasMoreElements())
        {
            ListIdElement lst = (ListIdElement)enum.nextElement();
            if ( lst.getJtapiId().equals(callid))
            {
                ret = lst;
            }
        }
        return ret;
    }
    
    public SipManager getSipManager()
    {
        return sipManager;
    }
    
    public void unregistering(RegistrationEvent evt)
    {
    }
    
    public void play(String url)
    {
        try
        {
            this.mediaManager.play(url);
        }
        catch(Exception ex)
        {
            console.debug(ex.toString());
        }
    }
    public void record(String url)
    {
        try
        {
            this.mediaManager.record(url);
        }
        catch(Exception ex)
        {
            console.debug(ex.toString());
        }
    }
    public void stop()
    {
        
        this.mediaManager.stopPlaying();
        this.mediaManager.stopRecording();
    }
    
   
    
}