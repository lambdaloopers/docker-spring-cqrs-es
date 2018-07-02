package com.app.demo.application.http.controller;

import com.app.common.application.http.exception.NotFoundException;
import com.app.common.application.http.response.CreateResponse;
import com.app.demo.application.command.message.CreateMessage;
import com.app.demo.application.http.request.message.CreateMessageRequest;
import com.app.demo.application.query.GetAllMessages;
import com.app.demo.application.query.GetMessageById;
import com.app.demo.domain.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/messages", produces = "application/json")
public class MessageController {

    @Autowired
    private GetMessageById getMessageByIdQuery;

    @Autowired
    private GetAllMessages getAllMessagesQuery;

    @Autowired
    private CreateMessage createMessageCommand;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    CreateResponse createMessage(@RequestBody @Valid CreateMessageRequest request) {

        String userId = createMessageCommand.execute(
                request.getSender(),
                request.getContent()
        );

        return new CreateResponse(userId);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    Page<Message> getAllMessages(
            @RequestParam(required = false, value = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, value = "limit", defaultValue = "10") int limit) {


        return getAllMessagesQuery.execute(offset, limit);
    }

    @RequestMapping(value = "{messageId}", method = RequestMethod.GET)
    public @ResponseBody
    Message getMessageById(
            @PathVariable("messageId") String messageId) {

        try {
            return getMessageByIdQuery.execute(messageId);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Message");
        }
    }
}
