package br.com.ada.bancobrasil.pedidocompras.service.impl;

import br.com.ada.bancobrasil.pedidocompras.dto.SendEmailDto;
import br.com.ada.bancobrasil.pedidocompras.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class SendEmailServiceImpl implements SendEmailService {

    @Override
    public void send(SendEmailDto sendEmailDto) {

        log.info("INICIO - Envio email - " + sendEmailDto);

        /*final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sendEmailDto.getPara());
        simpleMailMessage.setSubject(sendEmailDto.getAssunto());
        simpleMailMessage.setText(sendEmailDto.getMensagem());
        javaMailSender.send(simpleMailMessage);*/

        log.info("FIM - Envio email - " + sendEmailDto);

    }

}
