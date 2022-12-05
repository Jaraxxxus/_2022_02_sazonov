package cft.shift;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;


public class JacksonTaster {

    static void pojoToJsonString(){
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
         File file = new File("fileeee.json");
         ZonedDateTime z =  ZonedDateTime.now();
         Message message = new Message(MessageType.NAME_ACCEPTED,"Name", "aaaaaaaaaaaaa", z);
        try {
            mapper.writer().writeValue(file,message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Message message1;
        try {
            message1 = mapper.readValue(file, Message.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
