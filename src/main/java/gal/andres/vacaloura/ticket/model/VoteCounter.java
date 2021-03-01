package gal.andres.vacaloura.ticket.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Provides a voting system that only allows a vote per user */
@Embeddable
public class VoteCounter {
  @ElementCollection private Set<String> voters;

  public VoteCounter() {
    this.voters = new HashSet<>();
  }

  /**
   * Adds a vote, if the user already voted, it doesn't increase the number of votes
   *
   * @param username Username of the user that is casting a vote
   */
  public void vote(String username) {
    this.voters.add(username);
  }

  /**
   * Decrease the number of votes by one if the user already voted
   *
   * @param username Username of the user that wants to delete the vote
   */
  public void removeVote(String username) {
    this.voters.remove(username);
  }

  /**
   * Gets the number of votes casted
   *
   * @return Number of votes
   */
  public int getVotes() {
    return this.voters.size();
  }

  /**
   * Gets a list with the names of the voters
   *
   * @return List with the names of the voters
   */
  public List<String> getVoters() {
    return new ArrayList<>(this.voters);
  }
}
