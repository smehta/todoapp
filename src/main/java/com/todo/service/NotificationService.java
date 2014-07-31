package com.todo.service;

/**
 * Created by smehta on 7/29/14.
 */
public interface NotificationService {

    /**
     * Using third party API, it sends notification to the user.
     * @param title
     * @return true, if notification is sent otherwise false.
     */
    boolean send(String title);
}
