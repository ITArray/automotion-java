package http.helpers;

import javax.mail.*;

/**
 * @deprecated As of release 2.0, replaced by {@link net.itarray.automotion.tools.http.helpers.MailService}
 */
@Deprecated()
public class MailService {

    private final net.itarray.automotion.tools.http.helpers.MailService delegatee;
    public MailService() {
        delegatee = new net.itarray.automotion.tools.http.helpers.MailService();
    }

    public boolean isLoggedIn() {
        return delegatee.isLoggedIn();
    }

    /**
     * to login to the mail host server
     */
    public void login(String host, int port, String username, String password)
            throws Exception {
        delegatee.login(host, port, username, password);
    }

    /**
     * to logout from the mail host server
     */
    public void logout() throws MessagingException {
        delegatee.logout();
    }

    public int getMessageCount() {
        return delegatee.getMessageCount();
    }

    public Message[] getMessages() throws MessagingException {
        return delegatee.getMessages();
    }

    public Message getLastMesage() throws MessagingException {
        return delegatee.getLastMesage();
    }

    public MailService setFolder(MailFolder folder){
        switch (folder) {
            case INBOX:
                delegatee.setFolder(net.itarray.automotion.tools.http.helpers.MailService.MailFolder.INBOX);
                break;
            case SPAM:
                delegatee.setFolder(net.itarray.automotion.tools.http.helpers.MailService.MailFolder.SPAM);
                break;
            case TRASH:
                delegatee.setFolder(net.itarray.automotion.tools.http.helpers.MailService.MailFolder.TRASH);
                break;
        }
        return this;
    }

    public enum MailFolder{
        INBOX,
        SPAM,
        TRASH
    }

}