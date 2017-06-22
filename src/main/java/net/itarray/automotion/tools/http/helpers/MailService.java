package net.itarray.automotion.tools.http.helpers;

import javax.mail.*;
import java.util.Properties;

public class MailService {

    private Session session;
    private Store store;
    private Folder folder;

    private String protocol = "imaps";
    private MailFolder folderName = MailFolder.INBOX;

    public boolean isLoggedIn() {
        return store != null && store.isConnected();
    }

    /**
     * to login to the mail host server
     */
    public void login(String host, int port, String username, String password)
            throws Exception {
        String fName = getFolderName(folderName);
        URLName url = new URLName(protocol, host, port, fName, username, password);

        if (session == null) {
            Properties props = null;
            try {
                props = System.getProperties();
            } catch (SecurityException sex) {
                props = new Properties();
            }
            session = Session.getInstance(props, null);
        }
        store = session.getStore(url);
        store.connect();
        folder = store.getFolder(url);

        folder.open(Folder.READ_WRITE);
    }

    /**
     * to logout from the mail host server
     */
    public void logout() throws MessagingException {
        folder.close(false);
        store.close();
        store = null;
        session = null;
    }

    public int getMessageCount() {
        int messageCount = 0;
        try {
            messageCount = folder.getMessageCount();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return messageCount;
    }

    public Message[] getMessages() throws MessagingException {
        return folder.getMessages();
    }

    public Message getLastMesage() throws MessagingException {
        return getMessages()[getMessageCount() - 1];
    }

    public MailService setFolder(MailFolder folder){
        if (!isLoggedIn()) {
            this.folderName = folder;
        }

        return this;
    }

    public enum MailFolder{
        INBOX,
        SPAM,
        TRASH
    }

    private String getFolderName(MailFolder folder){
        String folderName;

        switch (folder){
            case INBOX:
                folderName = "INBOX";
                break;
            case SPAM:
                folderName = "[Gmail]/Spam";
                break;
            case TRASH:
                folderName = "[Gmail]/Trash";
                break;
            default:
                folderName = "INBOX";
                break;
        }

        return folderName;
    }
}