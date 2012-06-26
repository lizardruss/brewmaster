package org.brewmaster.validation;

import net.sf.oval.AbstractCheck;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

public class IdRequiredCheck extends AbstractCheck {

    private static final long serialVersionUID = -6644984174410007868L;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate,
                               OValContext context, Validator validator) throws OValException {
        // TODO Auto-generated method stub
        return false;
    }

}
