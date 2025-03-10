package br.com.zup.eduardo.proposta.config.anotacao;

import br.com.zup.eduardo.proposta.config.validacao.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UniqueValue {
        
	String message() default "o valor não pode ser duplicado";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {}; 
	
	String fieldName();
	
	Class<?> domainClass();
}
