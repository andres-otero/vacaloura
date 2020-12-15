package gal.andres.vacaloura.ticket.model;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDTO {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TicketDTO ticketDTO = (TicketDTO) o;

    if (id != null ? !id.equals(ticketDTO.id) : ticketDTO.id != null) return false;
    if (name != null ? !name.equals(ticketDTO.name) : ticketDTO.name != null) return false;
    if (type != ticketDTO.type) return false;
    if (priority != ticketDTO.priority) return false;
    if (date != null ? !date.equals(ticketDTO.date) : ticketDTO.date != null) return false;
    if (dueDate != null ? !dueDate.equals(ticketDTO.dueDate) : ticketDTO.dueDate != null)
      return false;
    if (tags != null ? !tags.equals(ticketDTO.tags) : ticketDTO.tags != null) return false;
    if (description != null
        ? !description.equals(ticketDTO.description)
        : ticketDTO.description != null) return false;
    if (status != ticketDTO.status) return false;
    if (version != null ? !version.equals(ticketDTO.version) : ticketDTO.version != null)
      return false;
    if (votes != null ? !votes.equals(ticketDTO.votes) : ticketDTO.votes != null) return false;
    return stepsReproduction != null
        ? stepsReproduction.equals(ticketDTO.stepsReproduction)
        : ticketDTO.stepsReproduction == null;
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

  @Override
  public String toString() {
    return "TicketDTO{"
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

    public TicketDTO build() {
      TicketDTO ticketDTO = new TicketDTO();
      ticketDTO.setId(id);
      ticketDTO.setName(name);
      ticketDTO.setType(type);
      ticketDTO.setPriority(priority);
      ticketDTO.setDate(date);
      ticketDTO.setDueDate(dueDate);
      ticketDTO.setTags(tags);
      ticketDTO.setDescription(description);
      ticketDTO.setStatus(status);
      ticketDTO.setVersion(version);
      ticketDTO.setVotes(votes);
      ticketDTO.setStepsReproduction(stepsReproduction);
      return ticketDTO;
    }
  }
}
