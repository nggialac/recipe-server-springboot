package com.lacnguyen.recipeserver.service;

public interface IMailService {
    boolean sendEmail(String subject, String message, String to);
    boolean sendHtmlTemplate(String subject, String message, String to);
}
