package org.ticket.model;


import javax.persistence.*;

@Entity(name = "TB_TICKET")
@Table(name="TB_TICKET")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name="TICKET_ID", nullable = false)
    private long id;

    @Column(name="TICKET_TITLE", nullable = false)
    private String title;
    @Column(name="TICKET_CONTENT", nullable = false)
    private String content;
    @Column(name="TICKET_SOLVED", nullable = false)
    private int solved;

    @OneToOne
    @JoinColumn(name = "TICKET_ISSUERID", nullable = false)
    private User issuer;

    @Column(name="TICKET_PRIORITY", nullable = false)
    private int priority;

    public enum TicketPriority {
        LOW,
        MEDIUM,
        HIGH
    }

    @Transient
    private TicketPriority ticketPriority;

    protected Ticket() {}

    public Ticket(String title, String content, int solved, TicketPriority ticketPriority, User issuer) {
        this.title = title;
        this.content = content;
        this.solved = solved;
        this.ticketPriority  = ticketPriority;
        this.priority = ticketPriority.ordinal();
        this.issuer = issuer;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int b) {
        solved = b;
    }

    public User getIssuer() {
        return issuer;
    }

    public int getPriority() {
        return priority;
    }

    public String getFormattedPriority(int ordinal) {
        String r = "";

        switch (ordinal) {
            case 0:
                r = "Baixa";
                break;
            case 1:
                r = "Média";
                break;
            case 2:
                r= "Alta";
                break;
        }

        return r;
    }
}
