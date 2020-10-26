package entities;

public final class Organization {
  private String name;
  private int inn;
  private long payment_account;

  public Organization(String name, int inn, int payment_account) {
    this.name = name;
    this.inn = inn;
    this.payment_account = payment_account;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getInn() {
    return inn;
  }

  public void setInn(int inn) {
    this.inn = inn;
  }

  public long getPayment_account() {
    return payment_account;
  }

  public void setPayment_account(long payment_account) {
    this.payment_account = payment_account;
  }
}
