package repository;

import java.util.List;

import model.Delivery;

public interface DeliveryRepository {

    void save(Delivery delivery);

    void delete(Delivery delivery);

    List<Delivery> findAll();
}