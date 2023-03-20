package util;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import service.MemberService;

public class MessageTemplate {
	   
	   final DefaultMessageService messageService;
	   public String phoneNumber;
	   
	   public void setPhoneNumber(String phoneNumber) {
		   this.phoneNumber = phoneNumber;
	   }
	   
	   public MessageTemplate() {
	      this.messageService = NurigoApp.INSTANCE.initialize("NCS3CDJQNKS6YNXZ", "AO8BIRTEPZLKY2CMRBZ8VCY0RDPT4ASM", "https://api.solapi.com");
	   }
	   
	   public SingleMessageSentResponse sendOne(String content) {
	       Message message = new Message();
	       // �߽Ź�ȣ �� ���Ź�ȣ�� �ݵ�� 01012345678 ���·� �ԷµǾ�� �Ѵ�.
	       message.setFrom("01084819926");
	       message.setTo(phoneNumber);
	       message.setText(content);
	       
	       SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//	       System.out.println(response);
	       System.out.println("�޽��� ���ۿϷ�");

	       return response;
	   }

	}