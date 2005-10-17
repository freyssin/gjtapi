package net.sourceforge.gjtapi.raw.remote.corba;

/**
* com/uforce/jtapi/generic/raw/remote/corba/DetectEvent.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from CorbaProvider.idl
* Thursday, November 16, 2000 1:38:18 o'clock PM EST
*/

public final class DetectEvent implements org.omg.CORBA.portable.IDLEntity
{
	static final long serialVersionUID = -8445460095968525117L;
	
  public int pattern = (int)0;
  public int sigs[] = null;
  public String terminal = null;
  public net.sourceforge.gjtapi.raw.remote.corba.ResourceEvent event = null;

  public DetectEvent ()
  {
  } // ctor      
  public DetectEvent (int _pattern, int[] _sigs, String _terminal, net.sourceforge.gjtapi.raw.remote.corba.ResourceEvent _event)
  {
	pattern = _pattern;
	sigs = _sigs;
	terminal = _terminal;
	event = _event;
  } // ctor        
} // class DetectEvent
