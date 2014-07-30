package com.todo.service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smehta on 7/29/14.
 */
public class TwilioNotificationServiceImpl implements NotificationService{

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String TO = System.getenv("TWILIO_TO");
    private static final String FROM = System.getenv("TWILIO_FROM");

    private static String MSG_FORMAT = "Congrats, \"%s\" task is done !!";

    private Logger log = LoggerFactory.getLogger(TwilioNotificationServiceImpl.class);

    public boolean send(String title) {

        // Create a rest client
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();

        // Send an sms (using the new messages endpoint)
        MessageFactory messageFactory = mainAccount.getMessageFactory();
        List<NameValuePair> messageParams = new ArrayList<>();
        messageParams.add(new BasicNameValuePair("To", TO));
        messageParams.add(new BasicNameValuePair("From", FROM));

        String body = String.format(MSG_FORMAT, title);
        messageParams.add(new BasicNameValuePair("Body", body));

        try {

            Message message = messageFactory.create(messageParams);

            return message.getSid() != null && message.getSid().length() > 0;

        } catch (TwilioRestException exception) {

            log.error("Unable to send SMS", exception);
        }

        return  false;
    }
}
