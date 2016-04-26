package ControleAcces;

/**
 * Interface definition : RelaiDeZone
 * 
 * @author OpenORB Compiler
 */
public abstract class RelaiDeZonePOA extends org.omg.PortableServer.Servant
        implements RelaiDeZoneOperations, org.omg.CORBA.portable.InvokeHandler
{
    public RelaiDeZone _this()
    {
        return RelaiDeZoneHelper.narrow(_this_object());
    }

    public RelaiDeZone _this(org.omg.CORBA.ORB orb)
    {
        return RelaiDeZoneHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/RelaiDeZone:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("demandeAcces")) {
                return _invoke_demandeAcces(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_demandeAcces(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.PhotoHelper.read(_is);
        String arg1_in = ControleAcces.EmpreinteHelper.read(_is);

        try
        {
            String _arg_result = demandeAcces(arg0_in, arg1_in);

            _output = handler.createReply();
            _output.write_string(_arg_result);

        }
        catch (ControleAcces.PorteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PorteInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.AccesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.AccesRefuseHelper.write(_output,_exception);
        }
        return _output;
    }

}
