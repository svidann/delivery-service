package repository;

import model.Delivery;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDeliveryRepository
        implements DeliveryRepository {

    private final List<Delivery> deliveries =
            new ArrayList<>();

    @Override
    public void save(Delivery delivery) {
        deliveries.add(delivery);
    }

    @Override
    public void delete(Delivery delivery) {
        deliveries.remove(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return new ArrayList<>(deliveries);
    }
}