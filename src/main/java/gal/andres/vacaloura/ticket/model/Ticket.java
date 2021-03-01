package gal.andres.vacaloura.ticket.model;

import gal.andres.vacaloura.user.model.ApplicationUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
  @Column private VoteCounter votes;
  @Column private String stepsReproduction;
  @OneToOne private ApplicationUser assignedTo;
  @OneToOne private ApplicationUser owner;
  @OneToMany private List<ApplicationUser> followers;

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
    return votes.getVotes();
  }

  public void setVotes(VoteCounter votes) {
    this.votes = votes;
  }

  public void vote(String username) {
    this.votes.vote(username);
  }

  public void removeVote(String username) {
    this.votes.removeVote(username);
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

  public ApplicationUser getOwner() {
    return owner;
  }

  public void setOwner(ApplicationUser owner) {
    this.owner = owner;
  }

  public List<ApplicationUser> getFollowers() {
    return followers;
  }

  public void setFollowers(List<ApplicationUser> followers) {
    this.followers = followers;
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
        + votes.getVotes()
        + ", stepsReproduction='"
        + stepsReproduction
        + '\''
        + ", assignedTo="
        + assignedTo
        + ", owner="
        + owner
        + ", followers="
        + followers
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return Objects.equals(id, ticket.id)
        && Objects.equals(name, ticket.name)
        && type == ticket.type
        && priority == ticket.priority
        && Objects.equals(date, ticket.date)
        && Objects.equals(dueDate, ticket.dueDate)
        && Objects.equals(tags, ticket.tags)
        && Objects.equals(description, ticket.description)
        && status == ticket.status
        && Objects.equals(version, ticket.version)
        && Objects.equals(votes.getVoters(), ticket.votes.getVoters())
        && Objects.equals(stepsReproduction, ticket.stepsReproduction)
        && Objects.equals(assignedTo, ticket.assignedTo)
        && Objects.equals(owner, ticket.owner)
        && Objects.equals(followers, ticket.followers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        type,
        priority,
        date,
        dueDate,
        tags,
        description,
        status,
        version,
        votes.getVoters(),
        stepsReproduction,
        assignedTo,
        owner,
        followers);
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
    private String stepsReproduction;
    private ApplicationUser assignedTo;
    private ApplicationUser owner;
    private List<ApplicationUser> followers;

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

    public Builder stepsReproduction(String stepsReproduction) {
      this.stepsReproduction = stepsReproduction;
      return this;
    }

    public Builder assignedTo(ApplicationUser assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public Builder owner(ApplicationUser owner) {
      this.owner = owner;
      return this;
    }

    public Builder followers(List<ApplicationUser> followers) {
      this.followers = followers;
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
      ticket.setVotes(new VoteCounter());
      ticket.setStepsReproduction(stepsReproduction);
      ticket.setAssignedTo(assignedTo);
      ticket.setOwner(owner);
      ticket.setFollowers(followers);
      return ticket;
    }
  }
}
