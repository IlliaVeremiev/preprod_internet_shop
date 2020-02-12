package com.epam.preprod.veremiev.task11.core.adapters;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.preprod.veremiev.task11.constants.ApplicationErrors;
import com.epam.preprod.veremiev.task11.constants.SessionAttributes;
import com.epam.preprod.veremiev.task11.core.adapters.errors.ErrorsDispatcher;
import com.epam.preprod.veremiev.task11.core.timers.TimeredTask;
import com.epam.preprod.veremiev.task11.modules.cart.Cart;

/**
 * Adapter for session modification
 * 
 * @author Illia_Veremiev
 *
 */
public class SimpleSessionAdapter {

	/**
	 * Session to work
	 */
	private HttpSession session;

	/**
	 * Constructor with parameters
	 * 
	 * @param session - session to work
	 */
	public SimpleSessionAdapter(HttpSession session) {
		this.session = session;
	}

	private ErrorsDispatcher getErrorsDispatcher() {
		ErrorsDispatcher dispatcher = (ErrorsDispatcher) session.getAttribute(SessionAttributes.ERRORS);
		if (dispatcher == null) {
			dispatcher = new ErrorsDispatcher();
			session.setAttribute(SessionAttributes.ERRORS, dispatcher);
		}
		return dispatcher;
	}

	/**
	 * Adds error to session errors list
	 * 
	 * @param errorHeader - error header
	 * @param message     - error message
	 */
	public void addError(String errorHeader, String message) {
		ErrorsDispatcher dispatcher = getErrorsDispatcher();
		dispatcher.add(errorHeader, message);
	}

	/**
	 * Returns true if session contains errors
	 * 
	 * @return true if session contains errors
	 */
	public boolean isContainsError() {
		return getErrorsDispatcher().contains();
	}

	/**
	 * Move errors to request
	 * 
	 * @param request - request in which need to move
	 */
	public void migrateErrorsTo(HttpServletRequest request) {
		migrateTo(request, SessionAttributes.ERRORS);
	}

	/**
	 * Call {@link HttpSession#setAttribute(String, Object)}
	 * 
	 * @param name  - param name
	 * @param value - param
	 */
	public void setAttribute(String name, Object value) {
		session.setAttribute(name, value);
	}

	/**
	 * Move param to request
	 * 
	 * @param request   - request in which need to move
	 * @param paramName - name of param
	 */
	public void migrateTo(HttpServletRequest request, String paramName) {
		request.setAttribute(paramName, session.getAttribute(paramName));
		session.removeAttribute(paramName);
	}

	/**
	 * Returns list of {@link TimeredTask}
	 * 
	 * @return list of {@link TimeredTask}
	 */
	private Map<String, TimeredTask> getTimeredTasks() {
		Map<String, TimeredTask> tasks = (Map) session.getAttribute(SessionAttributes.TIMERED_TASKS);
		if (tasks == null) {
			tasks = new HashMap<>();
			session.setAttribute(SessionAttributes.TIMERED_TASKS, tasks);
		}
		return tasks;
	}

	/**
	 * Adds timered task
	 * 
	 * @param name - task name
	 * @param task - timered task
	 */
	public void addTimeredTask(String name, TimeredTask task) {
		getTimeredTasks().put(name, task);
	}

	/**
	 * Removes timered task
	 * 
	 * @param name - task name
	 */
	public void removeTimeredTask(String name) {
		Map<String, TimeredTask> tasks = getTimeredTasks();
		TimeredTask task = tasks.get(name);
		tasks.remove(name);
		if (task == null) {
			return;
		}
		task.tryCancel();
	}

	/**
	 * Starts timered task
	 * 
	 * @param name   - task name
	 * @param millis - delay before task
	 */
	public void startTimeredTask(String name, long millis) {
		TimeredTask task = getTimeredTasks().get(name);
		if (task == null) {
			throw new IllegalStateException(ApplicationErrors.TIMER_CANT_BE_INITIALIZED);
		}
		task.startAfter(millis);
	}

	/**
	 * Returns attribute from <b>session</b> casted to E
	 * 
	 * @param <E>   - class in which to cast
	 * @param name  - name of param
	 * @param clazz - class in which to cast
	 * @return attribute from <b>session</b> casted to E
	 */
	public <E> E get(String name, Class<E> clazz) {
		return (E) session.getAttribute(name);
	}

	/**
	 * Returns true if session contains attribute with <b>name</b>
	 * 
	 * @param name - parameter name
	 * @return true if session contains attribute with <b>name</b>
	 */
	public boolean contains(String name) {
		return session.getAttribute(name) != null;
	}

	/**
	 * Returns cart from session. If not exists - creates new
	 * 
	 * @return cart
	 */
	public Cart getCart() {
		Cart cart = (Cart) session.getAttribute(SessionAttributes.CART);
		if (cart == null) {
			session.setAttribute("cart", cart = new Cart());
		}
		return cart;
	}
}