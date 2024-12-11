package admin_user.service;

import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsService {
    private static final String ACCOUNT_SID = "AC5d3f5196c0e2e9d1b8dc72c1c4ba3465"; // Your Twilio Account SID
    private static final String AUTH_TOKEN = "bdca15d2acaffe68cc0a20551b5305f2";  // Your Twilio Auth Token
    private static final String FROM_PHONE = "+17755045423"; // Your Twilio Phone Number

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); // Initialize Twilio
    }

    /**
     * Sends an SMS message.
     * @param toPhoneNumber The recipient's phone number.
     * @param messageBody The content of the SMS.
     */
    public void sendSms(String toPhoneNumber, String messageBody) {
        Message.creator(
                new com.twilio.type.PhoneNumber(toPhoneNumber), // To
                new com.twilio.type.PhoneNumber(FROM_PHONE),     // From
                messageBody                                     // Message body
        ).create();
    }
}
