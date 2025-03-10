package br.com.zup.eduardo.proposta.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource origemMensagem;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioResponse> handle(MethodArgumentNotValidException exception) {

        List<ErroDeFormularioResponse> dto = new ArrayList<>();

        List<FieldError> errosCampos = exception.getBindingResult().getFieldErrors();
        errosCampos.forEach(e -> {
            String mensagem = origemMensagem.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioResponse erro = new ErroDeFormularioResponse(e.getField(), mensagem);
            dto.add(erro);
        });

        List<ObjectError> errors = exception.getBindingResult().getGlobalErrors();
        errors.forEach(e -> {
            String mensagem = origemMensagem.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioResponse erro = new ErroDeFormularioResponse(e.getObjectName(), mensagem);
            dto.add(erro);
        });

        return dto;
    }
}
