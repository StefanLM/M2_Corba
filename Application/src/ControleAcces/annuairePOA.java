package ControleAcces;

/**
 * Interface definition : Annuaire
 * 
 * @author OpenORB Compiler
 */
public abstract class AnnuairePOA extends org.omg.PortableServer.Servant
        implements AnnuaireOperations, org.omg.CORBA.portable.InvokeHandler
{
    public Annuaire _this()
    {
        return AnnuaireHelper.narrow(_this_object());
    }

    public Annuaire _this(org.omg.CORBA.ORB orb)
    {
        return AnnuaireHelper.narrow(_this_object(orb));
    }

    private static String [] _ids_list =
    {
        "IDL:ControleAcces/Annuaire:1.0"
    };

    public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte [] objectId)
    {
        return _ids_list;
    }

    private static final java.util.Map operationMap = new java.util.HashMap();

    static {
            operationMap.put("ajoutPersonne",
                    new Operation_ajoutPersonne());
            operationMap.put("ajoutPhotoPersonne",
                    new Operation_ajoutPhotoPersonne());
            operationMap.put("authentification",
                    new Operation_authentification());
            operationMap.put("consultationPersonne",
                    new Operation_consultationPersonne());
            operationMap.put("consultationPersonneBiometrique",
                    new Operation_consultationPersonneBiometrique());
            operationMap.put("demandeSuppressionEmpreinte",
                    new Operation_demandeSuppressionEmpreinte());
            operationMap.put("modificationEmpreintePersonne",
                    new Operation_modificationEmpreintePersonne());
            operationMap.put("modificationPersonne",
                    new Operation_modificationPersonne());
            operationMap.put("supprimerPersonne",
                    new Operation_supprimerPersonne());
            operationMap.put("verificationPersonne",
                    new Operation_verificationPersonne());
            operationMap.put("verificationPhoto",
                    new Operation_verificationPhoto());
    }

    public final org.omg.CORBA.portable.OutputStream _invoke(final String opName,
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler)
    {

        final AbstractOperation operation = (AbstractOperation)operationMap.get(opName);

        if (null == operation) {
            throw new org.omg.CORBA.BAD_OPERATION(opName);
        }

        return operation.invoke(this, _is, handler);
    }

    // helper methods
    private org.omg.CORBA.portable.OutputStream _invoke_verificationPhoto(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.PhotoHelper.read(_is);
        String arg1_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            String _arg_result = verificationPhoto(arg0_in, arg1_in);

            _output = handler.createReply();
            ControleAcces.IdEmpreinteHelper.write(_output,_arg_result);

        }
        catch (ControleAcces.PhotoInexistante _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PhotoInexistanteHelper.write(_output,_exception);
        }
        catch (ControleAcces.APIIncorrecte _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.APIIncorrecteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_verificationPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.NomHelper.read(_is);
        String arg1_in = ControleAcces.PrenomHelper.read(_is);
        String arg2_in = ControleAcces.PhotoHelper.read(_is);
        String arg3_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            ControleAcces.Personne _arg_result = verificationPersonne(arg0_in, arg1_in, arg2_in, arg3_in);

            _output = handler.createReply();
            ControleAcces.PersonneHelper.write(_output,_arg_result);

        }
        catch (ControleAcces.PhotoInexistante _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PhotoInexistanteHelper.write(_output,_exception);
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

    private org.omg.CORBA.portable.OutputStream _invoke_authentification(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.LoginHelper.read(_is);
        String arg1_in = ControleAcces.PasswordHelper.read(_is);
        String arg2_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            String _arg_result = authentification(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();
            ControleAcces.APIPriveHelper.write(_output,_arg_result);

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

    private org.omg.CORBA.portable.OutputStream _invoke_ajoutPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.NomHelper.read(_is);
        String arg1_in = ControleAcces.PrenomHelper.read(_is);
        String arg2_in = ControleAcces.LoginHelper.read(_is);
        String arg3_in = ControleAcces.PasswordHelper.read(_is);
        String arg4_in = ControleAcces.PhotoHelper.read(_is);
        String arg5_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            ajoutPersonne(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in);

            _output = handler.createReply();

        }
        catch (ControleAcces.PersonneDejaExistante _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.PersonneDejaExistanteHelper.write(_output,_exception);
        }
        catch (ControleAcces.APIIncorrecte _exception)
        {
            _output = handler.createExceptionReply();
            ControleAcces.APIIncorrecteHelper.write(_output,_exception);
        }
        return _output;
    }

    private org.omg.CORBA.portable.OutputStream _invoke_ajoutPhotoPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.PhotoHelper.read(_is);
        String arg2_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            ajoutPhotoPersonne(arg0_in, arg1_in, arg2_in);

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

    private org.omg.CORBA.portable.OutputStream _invoke_modificationEmpreintePersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.IdEmpreinteHelper.read(_is);
        String arg2_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            modificationEmpreintePersonne(arg0_in, arg1_in, arg2_in);

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

    private org.omg.CORBA.portable.OutputStream _invoke_modificationPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.NomHelper.read(_is);
        String arg2_in = ControleAcces.PrenomHelper.read(_is);
        String arg3_in = ControleAcces.PhotoHelper.read(_is);
        String arg4_in = ControleAcces.LoginHelper.read(_is);
        String arg5_in = ControleAcces.PasswordHelper.read(_is);
        String arg6_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            modificationPersonne(arg0_in, arg1_in, arg2_in, arg3_in, arg4_in, arg5_in, arg6_in);

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

    private org.omg.CORBA.portable.OutputStream _invoke_supprimerPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        ControleAcces.Personne arg0_in = ControleAcces.PersonneHelper.read(_is);
        String arg1_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            supprimerPersonne(arg0_in, arg1_in);

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

    private org.omg.CORBA.portable.OutputStream _invoke_consultationPersonne(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.NomHelper.read(_is);
        String arg1_in = ControleAcces.PrenomHelper.read(_is);
        String arg2_in = ControleAcces.APIPriveHelper.read(_is);

        try
        {
            ControleAcces.Personne _arg_result = consultationPersonne(arg0_in, arg1_in, arg2_in);

            _output = handler.createReply();
            ControleAcces.PersonneHelper.write(_output,_arg_result);

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

    private org.omg.CORBA.portable.OutputStream _invoke_consultationPersonneBiometrique(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.IdEmpreinteHelper.read(_is);
        String arg1_in = ControleAcces.APIPublicHelper.read(_is);

        try
        {
            ControleAcces.Personne _arg_result = consultationPersonneBiometrique(arg0_in, arg1_in);

            _output = handler.createReply();
            ControleAcces.PersonneHelper.write(_output,_arg_result);

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

    private org.omg.CORBA.portable.OutputStream _invoke_demandeSuppressionEmpreinte(
            final org.omg.CORBA.portable.InputStream _is,
            final org.omg.CORBA.portable.ResponseHandler handler) {
        org.omg.CORBA.portable.OutputStream _output;
        String arg0_in = ControleAcces.APIPublicHelper.read(_is);

        String[] _arg_result = demandeSuppressionEmpreinte(arg0_in);

        _output = handler.createReply();
        ControleAcces.SequenceIdEmpreinteHelper.write(_output,_arg_result);

        return _output;
    }

    // operation classes
    private abstract static class AbstractOperation {
        protected abstract org.omg.CORBA.portable.OutputStream invoke(
                AnnuairePOA target,
                org.omg.CORBA.portable.InputStream _is,
                org.omg.CORBA.portable.ResponseHandler handler);
    }

    private static final class Operation_verificationPhoto extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_verificationPhoto(_is, handler);
        }
    }

    private static final class Operation_verificationPersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_verificationPersonne(_is, handler);
        }
    }

    private static final class Operation_authentification extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_authentification(_is, handler);
        }
    }

    private static final class Operation_ajoutPersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_ajoutPersonne(_is, handler);
        }
    }

    private static final class Operation_ajoutPhotoPersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_ajoutPhotoPersonne(_is, handler);
        }
    }

    private static final class Operation_modificationEmpreintePersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_modificationEmpreintePersonne(_is, handler);
        }
    }

    private static final class Operation_modificationPersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_modificationPersonne(_is, handler);
        }
    }

    private static final class Operation_supprimerPersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_supprimerPersonne(_is, handler);
        }
    }

    private static final class Operation_consultationPersonne extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_consultationPersonne(_is, handler);
        }
    }

    private static final class Operation_consultationPersonneBiometrique extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_consultationPersonneBiometrique(_is, handler);
        }
    }

    private static final class Operation_demandeSuppressionEmpreinte extends AbstractOperation
    {
        protected org.omg.CORBA.portable.OutputStream invoke(
                final AnnuairePOA target,
                final org.omg.CORBA.portable.InputStream _is,
                final org.omg.CORBA.portable.ResponseHandler handler) {
            return target._invoke_demandeSuppressionEmpreinte(_is, handler);
        }
    }

}
