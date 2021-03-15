package gal.andres.vacaloura.ticket.model;

import gal.andres.vacaloura.user.model.ApplicationUser;

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
  private ApplicationUser assignedTo;
  private String history;

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

  public ApplicationUser getAssignedTo() {
    return assignedTo;
  }

  public void setAssignedTo(ApplicationUser assignedTo) {
    this.assignedTo = assignedTo;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
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
        + ", assignedTo="
        + assignedTo
        + ", history='"
        + history
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
    private ApplicationUser assignedTo;
    private String history;

    private Builder() {}

    public static Builder aTicketDTO() {
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

    public Builder assignedTo(ApplicationUser assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public Builder history(String history) {
      this.history = history;
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
      ticketDTO.setAssignedTo(assignedTo);
      ticketDTO.setHistory(history);
      return ticketDTO;
    }
  }
}
