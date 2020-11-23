package gal.andres.vacaloura.ticket.model;

public enum TicketType {
  BUG,
  ENHANCEMENT,
  FEATURE_REQUEST;

  @Override
  public String toString() {
    return this.name();
  }
}
