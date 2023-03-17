package controller;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import service.MemberService;

public class TempController {
	   
	   final DefaultMessageService messageService;
	   MemberService memberService = new MemberService();
	   
	   public TempController() {
	      this.messageService = NurigoApp.INSTANCE.initialize("NCS3CDJQNKS6YNXZ", "AO8BIRTEPZLKY2CMRBZ8VCY0RDPT4ASM", "https://api.solapi.com");
	   }
	   
	   public SingleMessageSentResponse sendOne(String content) {
	       Message message = new Message();
	       // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
	       message.setFrom("01084819926");
	       message.setTo(memberService.getTelno());
	       message.setText(content);

	       SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
	       System.out.println(response);

	       return response;
	   }

	}