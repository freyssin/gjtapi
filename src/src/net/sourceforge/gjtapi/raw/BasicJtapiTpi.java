package net.sourceforge.gjtapi.raw;

/*
	Copyright (c) 2002 8x8 Inc. (www.8x8.com) 

	All rights reserved. 

	Permission is hereby granted, free of charge, to any person obtaining a 
	copy of this software and associated documentation files (the 
	"Software"), to deal in the Software without restriction, including 
	without limitation the rights to use, copy, modify, merge, publish, 
	distribute, and/or sell copies of the Software, and to permit persons 
	to whom the Software is furnished to do so, provided that the above 
	copyright notice(s) and this permission notice appear in all copies of 
	the Software and that both the above copyright notice(s) and this 
	permission notice appear in supporting documentation. 

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS 
	OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT 
	OF THIRD PARTY RIGHTS. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
	HOLDERS INCLUDED IN THIS NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL 
	INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING 
	FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, 
	NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION 
	WITH THE USE OR PERFORMANCE OF THIS SOFTWARE. 

	Except as contained in this notice, the name of a copyright holder 
	shall not be used in advertising or otherwise to promote the sale, use 
	or other dealings in this Software without prior written authorization 
	of the copyright holder.
*/
import javax.telephony.*;
import net.sourceforge.gjtapi.*;
/**
 * This includes all methods that are required by any JTAPI implementation
 * Creation date: (2000-10-04 13:45:41)
 * @author: Richard Deadman
 */
public interface BasicJtapiTpi extends CoreTpi {
/**
 * Get a list of available addresses.
 * This may be null if the Telephony (raw) Provider does not support Addresses!
 * If the Address set it too large, this will throw a ResourceUnavailableException
 * Creation date: (2000-02-11 12:29:00)
 * @author: Richard Deadman
 * @return An array of address names
 * @exception ResourceUnavailableException if the set it too large to be returned dynamically.
 */
String[] getAddresses() throws ResourceUnavailableException;
/**
 * Get all the addresses associated with a terminal.
 * Creation date: (2000-06-02 12:30:54)
 * @author: Richard Deadman
 * @return An array of address numbers.
 * @param terminal The terminal name we want address numbers for.
 * @throws InvalidArgumentException indicating that the terminal is unknown.
 */
String[] getAddresses(String terminal) throws InvalidArgumentException;
/**
 * Get a list of available terminals.
 * This may be null if the Telephony (raw) Provider does not support Terminals.
 * If the Terminal set it too large, this will throw a ResourceUnavailableException
 * <P>Since we went to lazy connecting between Addresses and Terminals, this is called so
 * we don't have to follow all Address->Terminal associations to get the full set of Terminals.
 * Creation date: (2000-02-11 12:29:00)
 * @author: Richard Deadman
 * @return An array of terminal names, media type containers.
 * @exception ResourceUnavailableException if the set it too large to be returned dynamically.
 */
TermData[] getTerminals() throws ResourceUnavailableException;
/**
 * Get all the terminals associated with an address.
 * Creation date: (2000-02-11 12:30:54)
 * @author: Richard Deadman
 * @return An array of terminal name, media type containers.
 * @param address The address number we want terminal names for.
 * @throws InvalidArgumentException indicating that the address is unknown.
 */
TermData[] getTerminals(String address) throws InvalidArgumentException;
/**
  * Release a connection to a call (Connection).
  * This should block until the connection is released. The service provider does
  * not have to send a disconnect message for this connection, since it is generated by the framework.
  *
  * @param address The address that we want to release
  * @param call The call to disconnect from
  *
  * @exception RawException Some low-level state exception occured.
  **/
void release(String address, CallId call) throws PrivilegeViolationException,
	ResourceUnavailableException, MethodNotSupportedException, RawStateException;
}
