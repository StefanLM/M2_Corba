package ControleAcces;

/**
 * Interface definition : relaiDeZone
 * 
 * @author OpenORB Compiler
 */
public abstract class relaiDeZonePOA extends org.omg.PortableServer.Servant
        implements relaiDeZoneOperations, org.omg.CORBA.portable.InvokeHandler
{
    public relaiDeZone _this()
    {
        return relaiDeZoneHelper.narrow(_this_object());
    }

    public relaiDeZone _this(org.omg.CORBA.ORB orb)
    {
        return relaiDeZoneHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/relaiDeZone:1.0"
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
        int arg0_in = ControleAcces.IdPorteHelper.read(_is);
        String arg1_in = ControleAcces.PhotoHelper.read(_is);
        String arg2_in = ControleAcces.EmpreinteHelper.read(_is);

        try
        {
            demandeAcces(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.PorteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PorteInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.accesRefuse _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.accesRefuseHelper.write(_output,_exception);
        }
        return _output;
    }

}
