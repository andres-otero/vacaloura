package gal.andres.vacaloura.ticket.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;
  @Column private TicketType type;
  @Column private Priority priority;
  @Column private LocalDateTime date;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  @Column @ElementCollection private List<String> tags;
  @Column private String description;
  @Column private Status status;
  @Column private String version;
  @Column private Integer votes;
  @Column private String stepsReproduction;

  public Ticket(
      Long id,
      String name,
      TicketType type,
      Priority priority,
      LocalDateTime date,
      LocalDateTime dueDate,
      List<String> tags,
      String description,
      Status status,
      String version,
      Integer votes,
      String stepsReproduction) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.priority = priority;
    this.date = date;
    this.dueDate = dueDate;
    this.tags = tags;
    this.description = description;
    this.status = status;
    this.version = version;
    this.votes = votes;
    this.stepsReproduction = stepsReproduction;
  }

  public Ticket() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
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

  public Integer getVotes() {
    return votes;
  }

  public void setVotes(Integer votes) {
    this.votes = votes;
  }

  public String getStepsReproduction() {
    return stepsReproduction;
  }

  public void setStepsReproduction(String stepsReproduction) {
    this.stepsReproduction = stepsReproduction;
  }

  @Override
  public String toString() {
    return "Ticket{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", type="
        + type
        + ", priority="
        + priority
        + ", date="
        + date
        + ", dueDate="
        + dueDate
        + ", tags="
        + tags
        + ", description='"
        + description
        + '\''
        + ", status="
        + status
        + ", version='"
        + version
        + '\''
        + ", votes="
        + votes
        + ", stepsReproduction='"
        + stepsReproduction
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Ticket ticket = (Ticket) o;

    if (id != null ? !id.equals(ticket.id) : ticket.id != null) return false;
    if (name != null ? !name.equals(ticket.name) : ticket.name != null) return false;
    if (type != ticket.type) return false;
    if (priority != ticket.priority) return false;
    if (date != null ? !date.equals(ticket.date) : ticket.date != null) return false;
    if (dueDate != null ? !dueDate.equals(ticket.dueDate) : ticket.dueDate != null) return false;
    if (tags != null ? !tags.equals(ticket.tags) : ticket.tags != null) return false;
    if (description != null ? !description.equals(ticket.description) : ticket.description != null)
      return false;
    if (status != ticket.status) return false;
    if (version != null ? !version.equals(ticket.version) : ticket.version != null) return false;
    if (votes != null ? !votes.equals(ticket.votes) : ticket.votes != null) return false;
    return stepsReproduction != null
        ? stepsReproduction.equals(ticket.stepsReproduction)
        : ticket.stepsReproduction == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (priority != null ? priority.hashCode() : 0);
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
    result = 31 * result + (tags != null ? tags.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (version != null ? version.hashCode() : 0);
    result = 31 * result + (votes != null ? votes.hashCode() : 0);
    result = 31 * result + (stepsReproduction != null ? stepsReproduction.hashCode() : 0);
    return result;
  }

  public static final class Builder {
    private Long id;
    private String name;
    private TicketType type;
    private Priority priority;
    private LocalDateTime date;
    private LocalDateTime dueDate;
    private List<String> tags;
    private String description;
    private Status status;
    private String version;
    private Integer votes;
    private String stepsReproduction;

    private Builder() {}

    public static Builder builder() {
      return new Builder();
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder type(TicketType type) {
      this.type = type;
      return this;
    }

    public Builder priority(Priority priority) {
      this.priority = priority;
      return this;
    }

    public Builder date(LocalDateTime date) {
      this.date = date;
      return this;
    }

    public Builder dueDate(LocalDateTime dueDate) {
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

    public Builder votes(Integer votes) {
      this.votes = votes;
      return this;
    }

    public Builder stepsReproduction(String stepsReproduction) {
      this.stepsReproduction = stepsReproduction;
      return this;
    }

    public Ticket build() {
      Ticket ticket = new Ticket();
      ticket.setId(id);
      ticket.setName(name);
      ticket.setType(type);
      ticket.setPriority(priority);
      ticket.setDate(date);
      ticket.setDueDate(dueDate);
      ticket.setTags(tags);
      ticket.setDescription(description);
      ticket.setStatus(status);
      ticket.setVersion(version);
      ticket.setVotes(votes);
      ticket.setStepsReproduction(stepsReproduction);
      return ticket;
    }
  }
}
