package ControleAcces;

/**
 * Interface definition : Logs
 * 
 * @author OpenORB Compiler
 */
public abstract class LogsPOA extends org.omg.PortableServer.Servant
        implements LogsOperations, org.omg.CORBA.portable.InvokeHandler
{
    public Logs _this()
    {
        return LogsHelper.narrow(_this_object());
    }

    public Logs _this(org.omg.CORBA.ORB orb)
    {
        return LogsHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/Logs:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("demandeConsultationLog")) {
                return _invoke_demandeConsultationLog(_is, handler);
        } else if (opName.equals("ecrireLog")) {
                return _invoke_ecrireLog(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_demandeConsultationLog(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        int arg0_in = ControleAcces.DateHelper.read(_is);
        String arg1_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            String _arg_result = demandeConsultationLog(arg0_in, arg1_in);

            _output = handler.createReply();
            _output.write_string(_arg_result);

        }
        catch (ControleAcces.AucunLogDisponible _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.AucunLogDisponibleHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ecrireLog(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.TypeLogHelper.read(_is);
        String arg1_in = ControleAcces.DescriptionLogHelper.read(_is);

        try
        {
            ecrireLog(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.EcritureImpossible _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.EcritureImpossibleHelper.write(_output,_exception);
        }
        return _output;
    }

}
