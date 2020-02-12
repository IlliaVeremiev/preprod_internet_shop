package com.epam.preprod.veremiev.task11.services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.preprod.veremiev.task11.database.dao.OrderItemsDAO;
import com.epam.preprod.veremiev.task11.database.dao.OrdersDAO;
import com.epam.preprod.veremiev.task11.database.mysql.TransactionManager;
import com.epam.preprod.veremiev.task11.exceptions.ServiceException;
import com.epam.preprod.veremiev.task11.model.entities.InputDevice;
import com.epam.preprod.veremiev.task11.model.entities.Order;
import com.epam.preprod.veremiev.task11.model.entities.OrderedInputDevice;
import com.epam.preprod.veremiev.task11.model.entities.User;
import com.epam.preprod.veremiev.task11.model.entities.utils.Status;
import com.epam.preprod.veremiev.task11.modules.cart.Cart;

/**
 * Orders service
 * 
 * @author Illia_Veremiev
 *
 */
public class OrdersService {

	private OrdersDAO ordersDAO;

	private OrderItemsDAO orderItemsDAO;

	public OrdersService(OrdersDAO ordersDAO, OrderItemsDAO orderItemsDAO) {
		this.ordersDAO = ordersDAO;
		this.orderItemsDAO = orderItemsDAO;
	}

	/**
	 * Creates new order
	 * 
	 * @param user          - user who makes order
	 * @param cart          - cart with items
	 * @param payType       - payment type
	 * @param payRequisites - payment requisites
	 * @return created order
	 * @throws ServiceException if errors occurs
	 */
	public Order createOrder(User user, Cart cart, String payType, String payRequisites) throws ServiceException {
		Order order = new Order();
		order.setStatus(Status.SENDED);
		order.setUserId(user.getId());
		order.setDate(new Timestamp(new Date().getTime()));
		order.setPayType(payType);
		order.setPayRequisites(payRequisites);
		try {
			return new TransactionManager().doInTransaction(con -> {
				ordersDAO.create(con, order);
				Map<InputDevice, Long> products = cart.getAll();
				for (Entry<InputDevice, Long> item : products.entrySet()) {
					orderItemsDAO.create(con, new OrderedInputDevice(item.getKey().getId(), order.getId(),
							item.getKey().getPrice(), item.getValue()));
				}
				return order;
			});
		} catch (SQLException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
