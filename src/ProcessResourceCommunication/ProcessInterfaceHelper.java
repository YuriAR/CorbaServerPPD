package ProcessResourceCommunication;


/**
* ProcessResourceCommunication/ProcessInterfaceHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ProcessResourceCommunication.idl
* S�bado, 28 de Janeiro de 2017 10h57min53s GFT
*/

abstract public class ProcessInterfaceHelper
{
  private static String  _id = "IDL:ProcessResourceCommunication/ProcessInterface:1.0";

  public static void insert (org.omg.CORBA.Any a, ProcessResourceCommunication.ProcessInterface that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ProcessResourceCommunication.ProcessInterface extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ProcessResourceCommunication.ProcessInterfaceHelper.id (), "ProcessInterface");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ProcessResourceCommunication.ProcessInterface read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ProcessInterfaceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ProcessResourceCommunication.ProcessInterface value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ProcessResourceCommunication.ProcessInterface narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ProcessResourceCommunication.ProcessInterface)
      return (ProcessResourceCommunication.ProcessInterface)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ProcessResourceCommunication._ProcessInterfaceStub stub = new ProcessResourceCommunication._ProcessInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ProcessResourceCommunication.ProcessInterface unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ProcessResourceCommunication.ProcessInterface)
      return (ProcessResourceCommunication.ProcessInterface)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ProcessResourceCommunication._ProcessInterfaceStub stub = new ProcessResourceCommunication._ProcessInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
