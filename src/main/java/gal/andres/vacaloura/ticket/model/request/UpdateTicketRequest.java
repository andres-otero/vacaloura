package gal.andres.vacaloura.ticket.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import gal.andres.vacaloura.ticket.model.Priority;
import gal.andres.vacaloura.ticket.model.Status;
import gal.andres.vacaloura.ticket.model.TicketType;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateTicketRequest {
  private String name;
  private String description;
  private TicketType type;
  private Priority priority;
  private List<String> tags;
  private String version;
  private Status status;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime dueDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "UpdateTicketRequest{"
        + "name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", type="
        + type
        + ", priority="
        + priority
        + ", dueDate="
        + dueDate
        + ", tags="
        + tags
        + ", version='"
        + version
        + '\''
        + ", status="
        + status
        + '}';
  }

  public static final class Builder {
    private String name;
    private String description;
    private TicketType type;
    private Priority priority;
    private LocalDateTime dueDate;
    private List<String> tags;
    private String version;
    private Status status;

    private Builder() {}

    public static Builder builder() {
      return new Builder();
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
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

    public Builder dueDate(LocalDateTime dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public Builder status(Status status) {
      this.status = status;
      return this;
    }

    public UpdateTicketRequest build() {
      UpdateTicketRequest updateTicketRequest = new UpdateTicketRequest();
      updateTicketRequest.setName(name);
      updateTicketRequest.setDescription(description);
      updateTicketRequest.setType(type);
      updateTicketRequest.setPriority(priority);
      updateTicketRequest.setDueDate(dueDate);
      updateTicketRequest.setTags(tags);
      updateTicketRequest.setVersion(version);
      updateTicketRequest.setStatus(status);
      return updateTicketRequest;
    }
  }
}
