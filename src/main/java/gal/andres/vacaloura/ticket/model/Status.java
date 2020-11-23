package gal.andres.vacaloura.ticket.model;

public enum Status {
  NEW,
  ASSIGNED,
  FIXED,
  REJECTED,
  REOPENED,
  CLOSED;

  @Override
  public String toString() {
    return this.name();
  }
}
