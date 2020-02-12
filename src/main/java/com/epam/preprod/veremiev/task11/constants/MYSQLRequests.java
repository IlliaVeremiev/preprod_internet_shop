package com.epam.preprod.veremiev.task11.constants;

/**
 * Contains MYSQL requests
 * 
 * @author Illia_Veremiev
 *
 */
public class MYSQLRequests {
	public static final String ORDER_DIR_MARKER = "{ORDER_DIRECTION}";
	public static final String WHERE_MARKER = "{WHERE}";
	public static final String LIMIT_MARKER = "{LIMIT}";

	public static final String INSERT_USER = "INSERT INTO `users` (`name`, `surname`, `login`, `email`, `password`, `country`, `photo`, `role`) VALUES(?,?,?,?,?,?,?,?)";
	public static final String GET_USER_BY_LOGIN = "SELECT `id`, `name`, `surname`, `login`, `email`, `password`, `country`, `photo`, `role` FROM `users` WHERE `login`=?";

	public static final String GET_MANUFACTURERS = "SELECT `name`, `id` FROM `manufacturers`";

	public static final String GET_CHILD_CATEGORIES_BY_ID = "CALL `recursiveCategoriesSelection`(?)";

	public static final String GET_PRODUCTS_BY_FILTERS = "SELECT `input_devices`.`id`, `input_devices`.`title`, `input_devices`.`price`, `input_devices`.`count`, `input_devices`.`photo`, `input_devices`.`parameters`, `categories`.`name` AS `category`, `manufacturers`.`name` AS `manufacturer` FROM `input_devices` LEFT JOIN `manufacturers` ON `input_devices`.`manufacturer` = `manufacturers`.`id` LEFT JOIN `categories` ON `input_devices`.`category` = `categories`.`id` WHERE `input_devices`.`title` LIKE ? {WHERE} ORDER BY ? {ORDER_DIRECTION} {LIMIT}";
	public static final String GET_PRODUCTS_COUNT_BY_FILTERS = "SELECT count(*) AS `count` FROM `input_devices` LEFT JOIN `manufacturers` ON `input_devices`.`manufacturer` = `manufacturers`.`id` LEFT JOIN `categories` ON `input_devices`.`category` = `categories`.`id` WHERE `input_devices`.`title` LIKE ? {WHERE}";
	public static final String GET_PRODUCT_BY_ID = "SELECT `input_devices`.`id`, `input_devices`.`title`, `input_devices`.`price`, `input_devices`.`count`, `input_devices`.`photo`, `input_devices`.`parameters`, `categories`.`name` AS `category`, `manufacturers`.`name` AS `manufacturer` FROM `input_devices` LEFT JOIN `manufacturers` ON `input_devices`.`manufacturer` = `manufacturers`.`id` LEFT JOIN `categories` ON `input_devices`.`category` = `categories`.`id` WHERE `input_devices`.`id` = ?";

	public static final String INSERT_ORDER = "INSERT INTO `orders` (`status`, `details`, `date`, `user_id`, `pay_type`, `pay_requisites`) VALUES(?,?,?,?,?,?)";

	public static final String INSERT_ORDER_ITEM = "INSERT INTO `order_items` (`order_id`, `input_device_id`, `price`, `count`) VALUES(?,?,?,?)";
}