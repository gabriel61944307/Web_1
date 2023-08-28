 package br.ufscar.dc.dsw.validation;

 import javax.validation.ConstraintValidator;
 import javax.validation.ConstraintValidatorContext;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IPacienteDAO;
import br.ufscar.dc.dsw.domain.Paciente;

 @Component
 public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

 	@Autowired
 	private IPacienteDAO dao;

 	@Override
 	public boolean isValid(String CPF, ConstraintValidatorContext context) {
 		if (dao != null) {
 			Paciente paciente = dao.findByCPF(CPF);
 			return paciente == null;
 		} else {
 			return true;
 		}

 	}
 }