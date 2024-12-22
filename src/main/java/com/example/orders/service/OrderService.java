package com.example.orders.service;

import com.example.orders.dto.*;
import com.example.orders.exception.OrderNotFoundException;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO getOrderById(Long id) {
        // Найти заказ по ID или выбросить исключение, если не найден
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return mapToDTO(order); // Преобразуем модель в DTO
    }

    public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
        Order order = new Order();

        // Убедитесь, что 'getItems()' и 'getTotalPrice()' возвращают корректные данные
        order.setItems(createOrderDTO.getItems()); // Заполнение товаров
        order.setTotalPrice(createOrderDTO.getTotalPrice()); // Заполнение цены
        order.setOrderDate(LocalDateTime.now()); // Установка текущей даты и времени

        // Сохранение и возврат DTO
        return mapToDTO(orderRepository.save(order));
    }

    public OrderDTO updateOrder(Long id, UpdateOrderDTO updateOrderDTO) {
        // Найти заказ по ID или выбросить исключение, если не найден
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Обновление заказа новыми данными
        order.setItems(updateOrderDTO.getItems());
        order.setTotalPrice(updateOrderDTO.getTotalPrice());

        // Сохранение изменений и возврат DTO
        return mapToDTO(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        // Найти заказ по ID или выбросить исключение, если не найден
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Удаление заказа
        orderRepository.delete(order);
    }

    public List<OrderDTO> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        // Получение списка заказов по диапазону дат и преобразование их в DTO
        return orderRepository.findByOrderDateBetween(startDate, endDate)
                .stream()
                .map(this::mapToDTO) // Преобразование в DTO
                .collect(Collectors.toList());
    }

    // Преобразование модели в DTO
    private OrderDTO mapToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        // Преобразование полей
        orderDTO.setId(order.getId());
        orderDTO.setItems(order.getItems());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setOrderDate(order.getOrderDate());

        return orderDTO; // Возврат DTO
    }
}
