package com.solarizeme.controls;

import com.solarizeme.EmailSenderService;
import com.solarizeme.modelo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controle {

    @Autowired
    public EmailSenderService senderService;

    @PostMapping("/enviar-contato")
    public String enviarContato(Email email) {
        String mensagemHtml = montarMensagemHtml(email.getTitulo(), email.getEmailDestino(), email.getTexto());
        senderService.sendEmail("andradehernandes12@gmail.com", "Solicitação de Contato", mensagemHtml);
        return "redirect:#contato";
    }

    private String montarMensagemHtml(String nome, String emailDestino, String texto) {
        // Criar a mensagem HTML com os dados fornecidos
        return String.format(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; color: #333333; background-color: #f0f0f0; padding: 20px; margin: 0; }" +
                        ".container { max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); }" +
                        ".header { background-color: #007bff; color: #ffffff; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                        ".header h1 { margin: 0; font-size: 24px; }" +
                        ".content { padding: 20px; }" +
                        ".content h2 { color: #007bff; font-size: 20px; margin-top: 0; }" +
                        ".content p { font-size: 16px; line-height: 1.5; }" +
                        ".footer { margin-top: 20px; font-size: 14px; color: #777777; text-align: center; }" +
                        ".footer a { color: #007bff; text-decoration: none; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'>" +
                        "<h1>Nova Solicitação de Contato</h1>" +
                        "</div>" +
                        "<div class='content'>" +
                        "<h2>Detalhes do Usuário</h2>" +
                        "<p><strong>Nome:</strong> " + nome + "</p>" +
                        "<p><strong>E-mail:</strong> " + emailDestino + "</p>" +
                        "<p><strong>Mensagem:</strong><br>" + texto + "</p>" +
                        "</div>" +
                        "<div class='footer'>" +
                        "<p>Este e-mail foi enviado automaticamente pela nossa plataforma. Para mais informações, visite nosso <a href='https://www.seusite.com'>site</a>.</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>"
        );
    }

}
