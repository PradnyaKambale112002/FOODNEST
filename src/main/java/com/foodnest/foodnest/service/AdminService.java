package com.foodnest.foodnest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodnest.foodnest.dto.DashboardDTO;
import com.foodnest.foodnest.repository.MenuItemRepository;
import com.foodnest.foodnest.repository.OrderRepository;
import com.foodnest.foodnest.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public DashboardDTO getDashboard() {

        DashboardDTO dto = new DashboardDTO();

        dto.setTotalUsers(userRepository.count());
        dto.setTotalMenuItems(menuItemRepository.count());
        dto.setTotalOrders(orderRepository.count());
        dto.setTotalRevenue(orderRepository.getTotalRevenue());

        return dto;
    }
}