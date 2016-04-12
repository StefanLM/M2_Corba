package ControleAcces;

/**
 * Interface definition : biometrique
 * 
 * @author OpenORB Compiler
 */
public abstract class biometriquePOA extends org.omg.PortableServer.Servant
        implements biometriqueOperations, org.omg.CORBA.portable.InvokeHandler
{
    public biometrique _this()
    {
        return biometriqueHelper.narrow(_this_object());
    }

    public biometrique _this(org.omg.CORBA.ORB orb)
    {
        return biometriqueHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/biometrique:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        if (opName.equals("ajoutEmpreinte")) {
                return _invoke_ajoutEmpreinte(_is, handler);
        } else if (opName.equals("identificationBiometrique")) {
                return _invoke_identificationBiometrique(_is, handler);
        } else if (opName.equals("modificationEmpreinte")) {
                return _invoke_modificationEmpreinte(_is, handler);
        } else if (opName.equals("suppressionEmpreinte")) {
                return _invoke_suppressionEmpreinte(_is, handler);
        } else if (opName.equals("verificationEmpreinte")) {
                return _invoke_verificationEmpreinte(_is, handler);
        } else {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_verificationEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.IdEmpreinteHelper.read(_is);
        String arg1_in = ControleAcces.EmpreinteHelper.read(_is);
        String arg2_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            verificationEmpreinte(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.empreinteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.empreinteInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.apiIncorrect _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.apiIncorrectHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ajoutEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.EmpreinteHelper.read(_is);
        String arg1_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            String _arg_result = ajoutEmpreinte(arg0_in, arg1_in);

            _output = handler.createReply();
            ControleAcces.IdEmpreinteHelper.write(_output,_arg_result);

        }
        catch (ControleAcces.empreinteDejaExistante _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.empreinteDejaExistanteHelper.write(_output,_exception);
        }
        catch (ControleAcces.apiIncorrect _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.apiIncorrectHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_modificationEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.IdEmpreinteHelper.read(_is);
        String arg1_in = ControleAcces.EmpreinteHelper.read(_is);
        String arg2_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            modificationEmpreinte(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.empreinteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.empreinteInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.apiIncorrect _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.apiIncorrectHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_suppressionEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.EmpreinteHelper.read(_is);
        String arg1_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            suppressionEmpreinte(arg0_in, arg1_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.empreinteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.empreinteInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.apiIncorrect _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.apiIncorrectHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_identificationBiometrique(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.EmpreinteHelper.read(_is);
        String arg1_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            String _arg_result = identificationBiometrique(arg0_in, arg1_in);

            _output = handler.createReply();
            ControleAcces.IdEmpreinteHelper.write(_output,_arg_result);

        }
        catch (ControleAcces.empreinteInconnue _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.empreinteInconnueHelper.write(_output,_exception);
        }
        catch (ControleAcces.apiIncorrect _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.apiIncorrectHelper.write(_output,_exception);
        }
        return _output;
    }

}
