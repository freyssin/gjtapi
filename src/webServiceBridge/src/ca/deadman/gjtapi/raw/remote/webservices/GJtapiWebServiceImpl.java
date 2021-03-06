package ca.deadman.gjtapi.raw.remote.webservices;
/*
	Copyright (c) 2003 Richard Deadman, Deadman Consulting (www.deadman.ca)

	All rights reserved.

	This software is dual licenced under the GPL and a commercial license.
	If you wish to use under the GPL, the following license applies, otherwise
	please contact Deadman Consulting at sales@deadman.ca for commercial licensing.

    ---

	This program is free software; you can redistribute it and/or
	modify it under the terms of the GNU General Public License
	as published by the Free Software Foundation; either version 2
	of the License, or (at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import net.sourceforge.gjtapi.TermData;

/**
 * This is the implementation class for the GJTAPI web service bridge server-side part.
 * Since the web service servlet loads this as needed and is free to dispose of the instance and create
 * a new instance, we use this as a simple delegate to a singleton instance that is looked up when needed.
 *
 * For now the lookup uses a static class variable, but this could also use JNDI or some other technique.
 * @author rdeadman
 *
 */
public class GJtapiWebServiceImpl implements GJtapiWebServiceIF {

	private static GJtapiWebServiceIF delegate = null;

        /**
         * Simple Launch point to create the web service client
         * TODO: Pick up seb service end-point from the configuration file.
         */
        public static void main(String[] args) {

              Endpoint.publish(
                 "http://localhost:8080/GjtapiWebService/WebServiceProvider",
                 new GJtapiWebServiceImpl());

        }

	/**
	 * Constructor for GJtapiWebServicesImpl.
	 */
	public GJtapiWebServiceImpl() {
		super();
	}

	/**
	 * Lazy internal accessor for the delegate.
	 * This will lazily create the delegate the first time it is needed by any instance.	 */
	private GJtapiWebServiceIF getDelegate() {
		if (delegate == null) {
			synchronized (this) {
				if (delegate == null) {	// avoid dual creation
					delegate = new AdapterFactory().getAdapter();
				}
			}
		}
		return delegate;
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#registerQueue()
	 */
	public int registerQueue() throws RemoteException {
		return this.getDelegate().registerQueue();
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#pollEvents(int)
	 */
	public EventHolder[] pollEvents(int id) throws RemoteException {
		return this.getDelegate().pollEvents(id);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#allocateMedia(java.lang.String, int, java.util.HashMap)
	 */
	public boolean allocateMedia(String terminal, int type, HashMap params)
		throws RemoteException {
		return this.getDelegate().allocateMedia(terminal, type, params);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#answerCall(int, java.lang.String, java.lang.String)
	 */
	public void answerCall(int call, String address, String terminal)
		throws
			RemoteException,
			MobileJavaxException,
			MobileStateException {
				this.getDelegate().answerCall(call, address, terminal);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#attachMedia(int, java.lang.String, boolean)
	 */
	public boolean attachMedia(int call, String address, boolean onFlag)
		throws RemoteException {
		return this.getDelegate().attachMedia(call, address, onFlag);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#beep(int)
	 */
	public void beep(int call) throws RemoteException {
		this.getDelegate().beep(call);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#createCall(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int createCall(int id, String address, String term, String dest)
		throws
			RemoteException,
			MobileJavaxException,
			MobileStateException {
		return this.getDelegate().createCall(id, address, term, dest);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#freeMedia(java.lang.String, int)
	 */
	public boolean freeMedia(String terminal, int type)
		throws RemoteException {
		return this.getDelegate().freeMedia(terminal, type);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getAddresses()
	 */
	public String[] getAddresses()
		throws RemoteException, MobileJavaxException {
		return this.getDelegate().getAddresses();
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getAddressesForTerminal(String)
	 */
	public String[] getAddressesForTerminal(String terminal)
		throws RemoteException, MobileJavaxException {
		return this.getDelegate().getAddressesForTerminal(terminal);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getAddressType(java.lang.String)
	 */
	public int getAddressType(String name) throws RemoteException {
		return this.getDelegate().getAddressType(name);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getCall(int)
	 */
	public MovableCallData getCall(int id) throws RemoteException {
		return this.getDelegate().getCall(id);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getCallsOnAddress(java.lang.String)
	 */
	public MovableCallData[] getCallsOnAddress(String number)
		throws RemoteException {
		return this.getDelegate().getCallsOnAddress(number);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getCallsOnTerminal(java.lang.String)
	 */
	public MovableCallData[] getCallsOnTerminal(String name)
		throws RemoteException {
		return this.getDelegate().getCallsOnTerminal(name);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getCapabilities()
	 */
	public Properties getCapabilities() throws RemoteException {
		return this.getDelegate().getCapabilities();
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getDialledDigits(int, java.lang.String)
	 */
	public String getDialledDigits(int id, String address)
		throws RemoteException {
		return this.getDelegate().getDialledDigits(id, address);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getTerminals()
	 */
	public TermData[] getTerminals()
		throws MobileJavaxException, RemoteException {
		return this.getDelegate().getTerminals();
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#getTerminals(java.lang.String)
	 */
	public TermData[] getTerminalsForAddress(String address)
		throws RemoteException, MobileJavaxException {
		return this.getDelegate().getTerminalsForAddress(address);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#hold(int, java.lang.String, java.lang.String)
	 */
	public void hold(int call, String address, String term)
		throws
			RemoteException,
			MobileStateException,
			MobileJavaxException {
				this.getDelegate().hold(call, address, term);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#isMediaTerminal(java.lang.String)
	 */
	public boolean isMediaTerminal(String terminal) throws RemoteException {
		return this.getDelegate().isMediaTerminal(terminal);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#join(int, int, java.lang.String, java.lang.String)
	 */
	public int join(int call1, int call2, String address, String terminal)
		throws
			RemoteException,
			MobileStateException,
			MobileJavaxException {
		return this.getDelegate().join(call1, call2, address, terminal);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#play(java.lang.String, java.lang.String, int, ca.deadman.gjtapi.raw.remote.webservices.RTCPair, java.util.HashMap)
	 */
	public void play(
		String terminal,
		String[] streamIds,
		int offset,
		RTCPair[] rtcs,
		HashMap optArgs)
		throws MobileResourceException, RemoteException {
			this.getDelegate().play(terminal, streamIds, offset, rtcs, optArgs);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#record(java.lang.String, java.lang.String, ca.deadman.gjtapi.raw.remote.webservices.RTCPair, java.util.HashMap)
	 */
	public void record(
		String terminal,
		String streamId,
		RTCPair[] rtcs,
		HashMap optArgs)
		throws MobileResourceException, RemoteException {
			this.getDelegate().record(terminal, streamId, rtcs, optArgs);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#release(java.lang.String, int)
	 */
	public void release(String address, int call)
		throws
			RemoteException,
			MobileJavaxException,
			MobileStateException {
				this.getDelegate().release(address, call);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#releaseCallId(int)
	 */
	public void releaseCallId(int id) throws RemoteException {
		this.getDelegate().releaseCallId(id);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#removeQueue(int)
	 */
	public void removeQueue(int id) throws RemoteException {
		this.getDelegate().removeQueue(id);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#reportCallsOnAddress(java.lang.String, boolean)
	 */
	public void reportCallsOnAddress(String address, boolean flag)
		throws
			RemoteException,
			MobileJavaxException {
				this.getDelegate().reportCallsOnAddress(address, flag);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#reportCallsOnTerminal(java.lang.String, boolean)
	 */
	public void reportCallsOnTerminal(String terminal, boolean flag)
		throws
			RemoteException,
			MobileJavaxException {
				this.getDelegate().reportCallsOnTerminal(terminal, flag);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#reserveCallId(java.lang.String)
	 */
	public int reserveCallId(String address)
		throws RemoteException, MobileJavaxException {
		return this.getDelegate().reserveCallId(address);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#retrieveSignals(java.lang.String, int, int, ca.deadman.gjtapi.raw.remote.webservices.RTCPair, java.util.HashMap)
	 */
	public EventHolder retrieveSignals(
		String terminal,
		int num,
		int[] patterns,
		RTCPair[] rtcs,
		HashMap optArgs)
		throws MobileResourceException, RemoteException {
		return this.getDelegate().retrieveSignals(terminal, num, patterns, rtcs, optArgs);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#sendSignals(java.lang.String, int, ca.deadman.gjtapi.raw.remote.webservices.RTCPair, java.util.HashMap)
	 */
	public void sendSignals(
		String terminal,
		int[] syms,
		RTCPair[] rtcs,
		HashMap optArgs)
		throws MobileResourceException, RemoteException {
			this.getDelegate().sendSignals(terminal, syms, rtcs, optArgs);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#setLoadControl(java.lang.String, java.lang.String, double, double, double, int)
	 */
	public void setLoadControl(
		String startAddr,
		String endAddr,
		double duration,
		double admissionRate,
		double interval,
		int[] treatment)
		throws MobileJavaxException, RemoteException {
			this.getDelegate().setLoadControl(startAddr, endAddr, duration, admissionRate, interval, treatment);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#shutdown()
	 */
	public void shutdown() throws RemoteException {
		this.getDelegate().shutdown();
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#stop(java.lang.String)
	 */
	public void stop(String terminal) throws RemoteException {
		this.getDelegate().stop(terminal);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#stopReportingCall(int)
	 */
	public boolean stopReportingCall(int call) throws RemoteException {
		return this.getDelegate().stopReportingCall(call);
	}


	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#triggerRTC(java.lang.String, int)
	 */
	public void triggerRTC(String terminal, int action)
		throws RemoteException {
			this.getDelegate().triggerRTC(terminal, action);
	}

	/**
	 * @see ca.deadman.gjtapi.raw.remote.webservices.GJtapiWebServiceIF#unHold(int, java.lang.String, java.lang.String)
	 */
	public void unHold(int call, String address, String term)
		throws
			RemoteException,
			MobileStateException,
			MobileJavaxException {
				this.getDelegate().unHold(call, address, term);
	}

}
