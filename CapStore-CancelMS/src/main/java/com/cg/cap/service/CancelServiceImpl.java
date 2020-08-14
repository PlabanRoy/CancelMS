package com.cg.cap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.cg.cap.beans.Orders;
import com.cg.cap.beans.ProductResponse;
import com.cg.cap.dto.CancelDTO;
import com.cg.cap.dto.OrderProductMapDTO;
import com.cg.cap.dto.ProductDTO;
import com.cg.cap.exception.CrudException;
import com.cg.cap.repository.CancelRepository;

@Service
public class CancelServiceImpl implements CancelService {

	@Autowired
	RestTemplate rest;

	@Autowired
	CancelRepository cancelRepository;

	Orders orders;
	private String orderProductURL = "http://add-to-cart-service/order";
	private String productURL = "http://product-ms/product";

	private String dataAccessException = "distributed transaction exception!";
	private String scriptException = "Not well-formed script or error SQL command exception!";
	private String transientDataAccessException = "database timeout! exception!";

	/*******************************************************************************************************
	 * - Function Name : cancelOrder <br>
	 * - Description : Cancel a order that is placed <br>
	 * @param String orderId, String userId
	 * @throws CrudException
	 *******************************************************************************************************/

	@Override
	public String cancelOrder(String orderId, String userId) throws CrudException {

		long millis = System.currentTimeMillis();
		Date currentDate = new java.util.Date(millis);

		Orders orders = rest.getForObject(orderProductURL + "/getOrders/byOrderId?orderId=" + orderId, Orders.class);
		List<OrderProductMapDTO> list = orders.getOrders();
		Iterator<OrderProductMapDTO> itr = list.iterator();
		int index = 0;

		while (itr.hasNext()) {

			CancelDTO cancelOrder = new CancelDTO(orderId, userId, list.get(index).getProductId(),
					list.get(index).getProductUIN(), currentDate, 0);
			try {
				cancelRepository.save(cancelOrder);
			} catch (RecoverableDataAccessException e) {

				throw new CrudException(dataAccessException);

			} catch (ScriptException e) {

				throw new CrudException(scriptException);

			} catch (TransientDataAccessException e) {

				throw new CrudException(transientDataAccessException);

			}

			index++;
			itr.next();
		}

		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("orderId", orderId);
		return rest.postForObject(orderProductURL + "/cancelOrder", parametersMap, String.class);

	}

	/*******************************************************************************************************
	 * - Function Name : cancelProduct <br>
	 * - Description :cancel a product of particular order that is placed <br>
	 * 
	 * @param String orderId, String userId, String productId, int quantity
	 * @throws CrudException
	 *******************************************************************************************************/

	@Override
	public String cancelProduct(String orderId, String userId, String productId, int quantity) throws CrudException {

		long millis = System.currentTimeMillis();
		Date currentDate = new java.util.Date(millis);

		Orders orders = rest.getForObject(
				orderProductURL + "/getOrders/byOrderIdProductId?orderId=" + orderId + "&productId=" + productId,
				Orders.class);
		List<OrderProductMapDTO> list = orders.getOrders();
		Iterator<OrderProductMapDTO> itr = list.iterator();
		int index = 0;

		while (itr.hasNext()) {
			CancelDTO cancelProduct = new CancelDTO(orderId, userId, list.get(index).getProductId(),
					list.get(index).getProductUIN(), currentDate, 0);
			try {
				cancelRepository.save(cancelProduct);
			} catch (RecoverableDataAccessException e) {

				throw new CrudException(dataAccessException);

			} catch (ScriptException e) {

				throw new CrudException(scriptException);

			} catch (TransientDataAccessException e) {

				throw new CrudException(transientDataAccessException);

			}

			index++;
			itr.next();
		}

		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("orderId", orderId);
		parametersMap.add("productId", productId);

		return rest.postForObject(orderProductURL + "/cancelProduct", parametersMap, String.class);
	}
	
	/*******************************************************************************************************
	 * - Function Name : getResponseProducts <br>
	 * - Description :to get the list of cancelled products <br>
	 * 
	 * @throws CrudException
	 *******************************************************************************************************/

	@Override
	public List<ProductResponse> getResponseProducts() {
		List<CancelDTO> cancelOrders = (List<CancelDTO>) cancelRepository.findAll();
		List<ProductResponse> response = new ArrayList<>();
		Iterator<CancelDTO> itr = cancelOrders.iterator();
		int index = 0;
		while (itr.hasNext()) {
			ProductDTO product = rest.getForObject(
					productURL + "/getProductById?productId=" + cancelOrders.get(index).getProductId(),
					ProductDTO.class);
			ProductResponse productResponse = new ProductResponse(cancelOrders.get(index).getUserId(),
					cancelOrders.get(index).getOrderid(), cancelOrders.get(index).getProductId(),
					product.getProductName(), product.getProductURL(), product.getPrice());
			response.add(productResponse);
			index++;
			itr.next();
		}

		return response;

	}
}
