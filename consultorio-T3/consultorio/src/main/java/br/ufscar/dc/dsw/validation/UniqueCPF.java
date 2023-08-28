package br.ufscar.dc.dsw.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/*
 * Na classe Editora tava assim:
 * 
 * @UniqueCRM (message = "{Unique.editora.CRM}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.editora.CRM}")
	@Column(nullable = false, unique = true, length = 60)
	private String CRM;
 */

@Constraint(validatedBy = UniqueCPFValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCPF {
    String message() default "CPF is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
