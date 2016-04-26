package ControleAcces;

/**
 * Interface definition : Autorisation
 * 
 * @author OpenORB Compiler
 */
public abstract class AutorisationPOA extends org.omg.PortableServer.Servant
        implements AutorisationOperations, org.omg.CORBA.portable.InvokeHandler
{
    public Autorisation _this()
    {
        return AutorisationHelper.narrow(_this_object());
    }

    public Autorisation _this(org.omg.CORBA.ORB orb)
    {
        return AutorisationHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/Autorisation:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("ajoutAutorisation")) {
                return _invoke_ajoutAutorisation(_is, handler);
        } else if (opName.equals("modificationAutorisation")) {
                return _invoke_modificationAutorisation(_is, handler);
        } else if (opName.equals("supprimerAutorisation")) {
                return _invoke_supprimerAutorisation(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_ajoutAutorisation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.NomZoneHelper.read(_is);
        String arg2_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            ajoutAutorisation(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.PersonneInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PersonneInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.APIIncorrecte _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.APIIncorrecteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modificationAutorisation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.NomZoneHelper.read(_is);
        String arg2_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            modificationAutorisation(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.PersonneInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PersonneInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.APIIncorrecte _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.APIIncorrecteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_supprimerAutorisation(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.NomZoneHelper.read(_is);
        String arg2_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            supprimerAutorisation(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.PersonneInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PersonneInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.APIIncorrecte _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.APIIncorrecteHelper.write(_output,_exception);
        }
        return _output;
    }

}
