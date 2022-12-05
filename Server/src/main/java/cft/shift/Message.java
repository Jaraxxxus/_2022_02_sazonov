package cft.shift;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Message implements Serializable {

    private MessageType type;
    private String data;
    private String userName;

    private ZonedDateTime dateTime;
    Message(MessageType type,String data,String userName)
    {
        this.type = type;
        this.data = data;
        this.userName = userName;
        this.dateTime = null;
    }
    Message(MessageType type, String userName)
    {
        this.type = type;
        this.userName = userName;
        this.data = null;
        this.dateTime = null;
    }
    Message(MessageType type)
    {
        this.type = type;
        this.data = null;
        this.userName = null;
        this.dateTime = null;
    }



}