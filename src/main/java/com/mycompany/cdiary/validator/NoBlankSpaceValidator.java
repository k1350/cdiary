package com.mycompany.cdiary.validator;

import com.mycompany.cdiary.util.Messages;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class NoBlankSpaceValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value.toString().trim().isEmpty()) {
            FacesMessage msg = Messages.getMessage("myMsg", "com.mycompany.cdiary.NoBlankSpaceValidator.BLANK", new Object[1]);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
