package ControleAcces;

/**
 * Interface definition : logs
 * 
 * @author OpenORB Compiler
 */
public abstract class logsPOA extends org.omg.PortableServer.Servant
        implements logsOperations, org.omg.CORBA.portable.InvokeHandler
{
    public logs _this()
    {
        return logsHelper.narrow(_this_object());
    }

    public logs _this(org.omg.CORBA.ORB orb)
    {
        return logsHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/logs:1.0"
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
        catch (ControleAcces.aucunLogDisponible _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.aucunLogDisponibleHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ecrireLog(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        int arg0_in = ControleAcces.IdPersonneHelper.read(_is);
        String arg1_in = ControleAcces.NomHelper.read(_is);
        String arg2_in = ControleAcces.PrenomHelper.read(_is);
        String arg3_in = ControleAcces.AccesHelper.read(_is);
        String arg4_in = ControleAcces.NomZoneHelper.read(_is);
        String arg5_in = ControleAcces.DescriptionLogHelper.read(_is);

        try
        {
            ecrireLog(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.ecritureImpossible _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.ecritureImpossibleHelper.write(_output,_exception);
        }
        catch (ControleAcces.zoneInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.zoneInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.personneInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.personneInconnueHelper.write(_output,_exception);
        }
        return _output;
    }

}
