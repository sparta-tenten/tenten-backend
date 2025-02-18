package com.sparta.tentenbackend.domain.order.service;

import com.sparta.tentenbackend.domain.menu.entity.Menu;
import com.sparta.tentenbackend.domain.menu.entity.MenuOption;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrder;
import com.sparta.tentenbackend.domain.menu.entity.MenuOrderOption;
import com.sparta.tentenbackend.domain.menu.repository.MenuOptionRepository;
import com.sparta.tentenbackend.domain.menu.repository.MenuRepository;
import com.sparta.tentenbackend.domain.order.dto.OrderMenuRequest;
import com.sparta.tentenbackend.domain.order.dto.OrderRequest;
import com.sparta.tentenbackend.domain.order.entity.Order;
import com.sparta.tentenbackend.domain.order.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Order> getOrderList(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Order createOrder(OrderRequest req) {
        Order order = new Order(req);

        List<UUID> menuIdList = req.getOrderMenuRequestList().stream().map(OrderMenuRequest::getId)
            .toList();
        Map<UUID, Menu> menuMap = menuRepository.findAllByIdIn(menuIdList).stream()
            .collect(Collectors.toMap(Menu::getId, menu -> menu));

        List<UUID> optionIdList = req.getOrderMenuRequestList().stream()
            .flatMap(
                orderMenuRequest -> Optional.ofNullable(orderMenuRequest.getOptionIdList()).orElse(
                    Collections.emptyList()).stream()).toList();

        Map<UUID, List<MenuOption>> optionMap = menuOptionRepository.findAllByIdIn(optionIdList)
            .stream()
            .collect(Collectors.groupingBy(op -> op.getMenu().getId()));

        List<MenuOrder> menuOrderList = new ArrayList<>();
        long totalPrice = 0L;
        for (OrderMenuRequest menuReq : req.getOrderMenuRequestList()) {
            Menu menu = menuMap.get(menuReq.getId());
            MenuOrder menuOrder = new MenuOrder(menu, order, menuReq.getQuantity());

            List<MenuOption> menuOptionList = optionMap.get(menuOrder.getMenu().getId());

            long menuPrice = menu.getPrice() * menuOrder.getQuantity();
            if (menuOptionList != null) {
                menuPrice += menuOptionList.stream().map(MenuOption::getPrice)
                    .reduce(0L, Long::sum);
                menuOrder.setMenuOrderOptionList(menuOptionList.stream()
                    .map(menuOption -> new MenuOrderOption(menuOrder, menuOption)).toList());
            }
            totalPrice += menuPrice;
            menuOrderList.add(menuOrder);
        }
        order.setMenuOrderList(menuOrderList);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }
}
