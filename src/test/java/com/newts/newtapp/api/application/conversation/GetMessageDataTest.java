package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.MessageData;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestMessageRepository;
import com.newts.newtapp.entities.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetMessageDataTest {
    TestConversationRepository c;
    TestMessageRepository m;
    Message testMessage;
    GetMessageData g;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        m = new TestMessageRepository();
        testMessage = new Message();
        testMessage.setId(1);
        testMessage.setBody("testBody");
        testMessage.setAuthor(1);
        testMessage.setWrittenAt("testWrittenAt");
        testMessage.setLastUpdatedAt("testLastUpdatedAt");

        m.save(testMessage);

        g = new GetMessageData(c, m);
    }

    @Test(timeout=50)
    public void testGetMessagesData() throws IncorrectPassword, MessageNotFound {
        RequestModel r = new RequestModel();

        r.fill(RequestField.MESSAGE_ID, 1);

        MessageData mD = g.request(r);

        Assert.assertEquals(1, mD.id);
        Assert.assertEquals("testBody", mD.body);
        Assert.assertEquals(1, mD.author);
        Assert.assertEquals("testWrittenAt", mD.writtenAt);
        Assert.assertEquals("testLastUpdatedAt", mD.lastUpdatedAt);
    }

}
