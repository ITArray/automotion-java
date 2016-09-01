import http.helpers.MailService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;

public class MailServiceTest {

    private MailService mailService;

    @Test
    public void testThatEmailListCanBeFetched() throws Exception {
        mailService = new MailService();
        mailService.login("imap.gmail.com", 993, "testremax01@gmail.com", "remax2016");

        Assert.assertTrue(mailService.isLoggedIn());
        Assert.assertTrue(mailService.getMessageCount() >= 0);
    }

    @After
    public void tearDown() throws MessagingException {
        mailService.logout();
    }
}
