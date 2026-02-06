package assignment3.Entities;

public class Orders {
    private int id;
    private int customerId;
    private String status;

    public Orders(int customerId, String status) {
        this.customerId = customerId;
        this.status = status;
    }

    public Orders(int id, int customerId, String status) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private int id;
        private int customerId;
        private String status;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Orders build() {
            return new Orders(id, customerId, status);
        }
    }
}
