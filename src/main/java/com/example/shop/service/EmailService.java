package com.example.shop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;


/**
 * 이메일을 보내기위한 기능을 담당하는 서비스클래스
 */
@PropertySource("classpath:application.yml")
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {


   private final JavaMailSender javaMailSender; // ** JavaMailSender emailSender;//** 의존성 주입을 통해서 필요한 객체를 가져온다.

   // 인증번호 생성

   @Value("${spring.mail.username}")
   private String id;
   
   
   //String keyword로 쓰면 여기서 임시비밀번호도 지정가능!!!

   /**
    * 메일을 만들어주는 기능을 담당하는 로직
    * @param to = 메시지를 보려고하는 사용자의 이메일
    * @param ePw = createKey메서드에서 만들어진 인증코드
    * @return = 만들어진 메시지를 리턴
    * @throws MessagingException
    * @throws UnsupportedEncodingException
    */
   public MimeMessage createMessage(String to,String ePw) throws MessagingException, UnsupportedEncodingException {

      MimeMessage message = javaMailSender.createMimeMessage();

      message.addRecipients(MimeMessage.RecipientType.TO, to); // to 보내는 대상
      message.setSubject("SHOP 회원가입 인증코드"); // 메일 제목

      // 메일 내용 메일의 subtype을 html로 지정하여 html문법 사용 가능
      String msg = "";
      msg += "<div><h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">SHOP 회원가입 인증 확인입니다 </h1>";

      msg += "<span style=\"font-size:24px; font-weight:bold;\">인증 번호 :" + ePw + "</span>";
      msg += "</div>";
      message.setText(msg, "utf-8", "html"); // 내용, charset타입, subtype
      message.setFrom(new InternetAddress("powsemail0121@gmail.com", "SHOP 인증 센터"));
      return message;
   }


   /**
    * 메시지에 보내기 위한 인증코드를 작성해주는 로직
    * @return = 랜덤하게 만들어진  8자리의 인증코드를 리턴
    */
   public String createKey() { // **public void createCode() {
      String key = "";
      Random rnd = new Random(); // * Random random = new Random();
      for (int i = 0; i < 8; i++) { // 인증코드 8자리
         key += String.valueOf(rnd.nextInt(10));
      }
      return String.valueOf(key);
   }

   // ** @S
   /*
    * 메일 발송 sendSimpleMessage의 매개변수로 들어온 to는 인증번호를 받을 메일주소 MimeMessage 객체 안에 내가 전송할
    * 메일의 내용을 담아준다. bean으로 등록해둔 javaMailSender 객체를 사용하여 이메일 send
    */
   public String sendSimpleMessage(String to) throws Exception {
      String ePw = createKey();
      MimeMessage message = createMessage(to, ePw);
      try {
         javaMailSender.send(message); // 메일 발송
      } catch (MailException es) {
         es.printStackTrace();
         throw new IllegalArgumentException();
      }
      return ePw; // 메일로 보냈던 인증 코드를 서버로 리턴
   }

}
