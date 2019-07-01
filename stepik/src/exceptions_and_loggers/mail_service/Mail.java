package exceptions_and_loggers.mail_service;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//Сложное для понимания задание. Основная задача - использование logger и Exception

public class Mail {

    public static class UntrustworthyMailWorker implements MailService {

        private RealMailService realMailService = new RealMailService();
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] mailServices) {

            for (int i = 0; i < mailServices.length; i++) {
                this.mailServices = mailServices.clone();
            }
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable content = null;
            for (int i = 0; i < this.mailServices.length; i++) {

                if (i == 0) {
                    content = this.mailServices[0].processMail(mail);
                    continue;
                }

                content = this.mailServices[i].processMail(content);
            }
            return getRealMailService().processMail(content);
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }
    }

    public static class Spy implements MailService {

        private Logger logger;

        public Spy(Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                MailMessage message = (MailMessage) mail;
                if (message.getFrom().equals("Austin Powers"))
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"", new Object[] {message.getFrom(), message.getTo(), message.getMessage()});
                else
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object[]{message.getFrom(), message.getTo()});
            }
            return mail;
        }
    }

    public static class Thief implements MailService {

        private int price;
        private int stolenValue = 0;

        public Thief(int price) {
            this.price = price;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                int price1 = mailPackage.getContent().getPrice();
                if (price1 >= this.price) {
                    stolenValue += price1;
                    return new MailPackage(mailPackage.getFrom(), mailPackage.getTo(), new Package("stones instead of " + ((MailPackage) mail).getContent().getContent(), 0));
                }
            }
            return mail;
        }

        public int getStolenValue() {
            return stolenValue;
        }
    }

    public static class Inspector implements MailService {
        public Inspector(){}


        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                String content = mailPackage.getContent().getContent();
                if (content.contains("weapons") || content.contains("banned substance"))
                    throw new IllegalPackageException("");
                if (content.contains("stones"))
                    throw new StolenPackageException("");
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException(String message) {
            super(message);
        }

        public IllegalPackageException(String message, Throwable cause){
            super(message, cause);
        }
    }

    public static class StolenPackageException extends RuntimeException {
        public StolenPackageException(String message) {
            super(message);
        }

        public StolenPackageException(String message, Throwable cause){
            super(message, cause);
        }
    }


    //Defaul classes ---------------------------------------------------------

    public static interface Sendable {
        String getFrom();

        String getTo();
    }

    public static abstract class AbstractSendable implements Sendable {
        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            return to.equals(that.to);
        }

    }

    /*
    Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
    */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            return Objects.equals(message, that.message);
        }

    }

    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            return content.equals(that.content);
        }
    }

    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }
}


