package br.leg.rr.al.core.web.validator;

import java.util.Map;

import javax.inject.Named;

import org.primefaces.validate.ClientValidator;

@Named
public class EntityClientValidator extends EntityValidator implements ClientValidator {

	@Override
	public Map<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValidatorId() {
		return "entityClientValidator";
	}
}
