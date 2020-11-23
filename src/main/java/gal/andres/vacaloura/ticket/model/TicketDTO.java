package gal.andres.vacaloura.ticket.model;

import java.util.Date;
import java.util.List;

public class TicketDTO {
    private TicketType type;
    private Priority priority;
    private Date date;
    private Date dueDate;
    private List<String> tags;
    private String description;
    private Status status;
    private String version;
    private int votes;


    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }


    @Override
    public String toString() {
        return "TicketDTO{" +
                "type=" + type +
                ", priority=" + priority +
                ", date=" + date +
                ", dueDate=" + dueDate +
                ", tags=" + tags +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", version='" + version + '\'' +
                ", votes=" + votes +
                '}';
    }


    public static final class Builder {
        private TicketType type;
        private Priority priority;
        private Date date;
        private Date dueDate;
        private List<String> tags;
        private String description;
        private Status status;
        private String version;
        private int votes;

        private Builder() {
        }

        public static Builder aTicketDTO() {
            return new Builder();
        }

        public Builder type(TicketType type) {
            this.type = type;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder dueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder votes(int votes) {
            this.votes = votes;
            return this;
        }

        public TicketDTO build() {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setType(type);
            ticketDTO.setPriority(priority);
            ticketDTO.setDate(date);
            ticketDTO.setDueDate(dueDate);
            ticketDTO.setTags(tags);
            ticketDTO.setDescription(description);
            ticketDTO.setStatus(status);
            ticketDTO.setVersion(version);
            ticketDTO.setVotes(votes);
            return ticketDTO;
        }
    }
}
