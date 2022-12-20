package cft.shift.model;


import cft.shift.message.Message;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class MessageFormatter {

    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy - HH:mm";

    String formatChatMessage(Message response) {
        ZonedDateTime responseDateTime = response.getDateTime();
        ZonedDateTime zonedDateTime = ((responseDateTime == null) ? ZonedDateTime.now() : responseDateTime);
        String formattedDateTime = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(zonedDateTime);
        return String.format("[%s] '%s': %s\n", formattedDateTime, response.getUserName(), response.getData());
    }
}
