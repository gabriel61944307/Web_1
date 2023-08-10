 package br.ufscar.dc.dsw.validation;

 import javax.validation.ConstraintValidator;
 import javax.validation.ConstraintValidatorContext;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;

 import br.ufscar.dc.dsw.dao.IMedicoDAO;
 import br.ufscar.dc.dsw.domain.Medico;

 @Component
 public class UniqueCRMValidator implements ConstraintValidator<UniqueCRM, String> {

 	@Autowired
 	private IMedicoDAO dao;

 	@Override
 	public boolean isValid(String CRM, ConstraintValidatorContext context) {
 		if (dao != null) {
 			Medico editora = dao.findByCRM(CRM);
 			return editora == null;
 		} else {
 			//  Durante a execução da classe ConsultorioApplication
 			//  não há injeção de dependência
 			return true;
 		}

 	}
 }