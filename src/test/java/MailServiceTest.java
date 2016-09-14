import http.helpers.MailService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;

public class MailServiceTest {

    private static final String EMAIL = "testremax01@gmail.com";
    private static final String PASSWORD = "remax2016";
    private static final String IMAP_HOST = "imap.gmail.com";
    
    private MailService mailService;

    @Test
    public void testThatEmailListCanBeFetched() throws Exception {
        mailService = new MailService();
        mailService.login(IMAP_HOST, 993, EMAIL, PASSWORD);

        Assert.assertTrue(mailService.isLoggedIn());
        Assert.assertTrue(mailService.getMessageCount() >= 0);
    }

    @Test
    public void testThatEmailListCanBeFetchedFromInbox() throws Exception {
        mailService = new MailService();
        mailService
                .setFolder(MailService.MailFolder.INBOX)
                .login(IMAP_HOST, 993, EMAIL, PASSWORD);

        Assert.assertTrue(mailService.isLoggedIn());
        Assert.assertTrue(mailService.getMessageCount() >= 0);
    }

    @Test
    public void testThatEmailListCanBeFetchedFromSpam() throws Exception {
        mailService = new MailService();
        mailService
                .setFolder(MailService.MailFolder.SPAM)
                .login(IMAP_HOST, 993, EMAIL, PASSWORD);

        Assert.assertTrue(mailService.isLoggedIn());
        Assert.assertTrue(mailService.getMessageCount() >= 0);
    }

    @Test
    public void testThatEmailListCanBeFetchedFromTrash() throws Exception {
        mailService = new MailService();
        mailService
                .setFolder(MailService.MailFolder.TRASH)
                .login(IMAP_HOST, 993, EMAIL, PASSWORD);

        Assert.assertTrue(mailService.isLoggedIn());
        Assert.assertTrue(mailService.getMessageCount() >= 0);
    }

    @After
    public void tearDown() throws MessagingException {
        mailService.logout();
    }
}
