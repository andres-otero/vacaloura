package gal.andres.vacaloura.ticket.model;

public enum Priority {
  LOW,
  MEDIUM,
  HIGH,
  VERY_HIGH;

  @Override
  public String toString() {
    return this.name();
  }
}
