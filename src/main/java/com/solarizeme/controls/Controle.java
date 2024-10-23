package com.solarizeme.controls;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.solarizeme.EmailSenderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class Controle {

    @Autowired
    public EmailSenderService senderService;

    @GetMapping("/formulario")
    public String formulario() {
        return "formulario";
    }

    @PostMapping("/gerar-orcamento")
    public void gerarOrcamento(
            @RequestParam("nome") String nome,
            @RequestParam("endereco") String endereco,
            @RequestParam("telefone") String telefone,
            @RequestParam("email") String email,
            @RequestParam("tipo-imovel") String tipoImovel,
            @RequestParam("tamanho-residencia") String tamanhoResidencia,
            @RequestParam("tipo-telhado") String tipoTelhado,
            @RequestParam("telhado-condicao") String telhadoCondicao,
            @RequestParam("inclinacao-telhado") String inclinacaoTelhado,
            @RequestParam("consumo-energia") String consumoEnergia,
            HttpServletResponse response) throws IOException {

        // Defina o tipo de conteúdo para PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=orcamento.pdf");

        // Criação do ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Criação do PdfWriter
        PdfWriter writer = new PdfWriter(out);

        // Criação do PdfDocument
        PdfDocument pdfDocument = new PdfDocument(writer);

        // Criação do Document
        Document document = new Document(pdfDocument);

        // Adicionando os dados do formulário no PDF
        document.add(new Paragraph("Orçamento SolarizeMe"));
        document.add(new Paragraph("Nome: " + nome));
        document.add(new Paragraph("Endereço: " + endereco));
        document.add(new Paragraph("Telefone: " + telefone));
        document.add(new Paragraph("Email: " + email));
        document.add(new Paragraph("Tipo de Imóvel: " + tipoImovel));
        document.add(new Paragraph("Tamanho da Residência: " + tamanhoResidencia));
        document.add(new Paragraph("Tipo de Telhado: " + tipoTelhado));
        document.add(new Paragraph("Condição do Telhado: " + telhadoCondicao));
        document.add(new Paragraph("Inclinação do Telhado: " + inclinacaoTelhado));
        document.add(new Paragraph("Consumo de Energia: " + consumoEnergia));

        // Fechar o documento
        document.close();

        // Enviar o PDF gerado ao navegador para download
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();
    }




//    @PostMapping("/enviar-contato")
//    public String enviarContato(Email email) {
//        String mensagemHtml = montarMensagemHtml(email.getTitulo(), email.getEmailDestino(), email.getTexto());
//        senderService.sendEmail("andradehernandes12@gmail.com", "Solicitação de Contato", mensagemHtml);
//        return "redirect:#contato";
//    }
//
//    private String montarMensagemHtml(String nome, String emailDestino, String texto) {
//        // Criar a mensagem HTML com os dados fornecidos
//        return String.format(
//                "<!DOCTYPE html>" +
//                        "<html>" +
//                        "<head>" +
//                        "<style>" +
//                        "body { font-family: Arial, sans-serif; color: #333333; background-color: #f0f0f0; padding: 20px; margin: 0; }" +
//                        ".container { max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); }" +
//                        ".header { background-color: #007bff; color: #ffffff; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
//                        ".header h1 { margin: 0; font-size: 24px; }" +
//                        ".content { padding: 20px; }" +
//                        ".content h2 { color: #007bff; font-size: 20px; margin-top: 0; }" +
//                        ".content p { font-size: 16px; line-height: 1.5; }" +
//                        ".footer { margin-top: 20px; font-size: 14px; color: #777777; text-align: center; }" +
//                        ".footer a { color: #007bff; text-decoration: none; }" +
//                        "</style>" +
//                        "</head>" +
//                        "<body>" +
//                        "<div class='container'>" +
//                        "<div class='header'>" +
//                        "<h1>Nova Solicitação de Contato</h1>" +
//                        "</div>" +
//                        "<div class='content'>" +
//                        "<h2>Detalhes do Usuário</h2>" +
//                        "<p><strong>Nome:</strong> " + nome + "</p>" +
//                        "<p><strong>E-mail:</strong> " + emailDestino + "</p>" +
//                        "<p><strong>Mensagem:</strong><br>" + texto + "</p>" +
//                        "</div>" +
//                        "<div class='footer'>" +
//                        "<p>Este e-mail foi enviado automaticamente pela nossa plataforma. Para mais informações, visite nosso <a href='https://www.seusite.com'>site</a>.</p>" +
//                        "</div>" +
//                        "</div>" +
//                        "</body>" +
//                        "</html>"
//        );
//    }

}
