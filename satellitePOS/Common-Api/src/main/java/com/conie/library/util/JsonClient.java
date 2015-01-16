package com.conie.library.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;

import com.conie.library.exception.GpsSatelliteException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JsonClient {
	private static Logger	logger	= Logger.getLogger(JsonClient.class);

	@Deprecated
	public static synchronized ClientResponse getJSonGETResponse(String jsonURL) {
		Client client = Client.create();
		WebResource webResource = client.resource(jsonURL);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			response.setStatus(response.getStatus());
			logger.fatal("Failed : HTTP error code : " + response.getStatus());
		}
		return response;
	}

	@Deprecated
	public static synchronized ClientResponse getJSonPOSTResponse(String jsonURL, String objectRequestEntity) {
		Client client = Client.create();
		WebResource webResource = client.resource(jsonURL);
		ClientResponse response = webResource.post(ClientResponse.class, objectRequestEntity);
		if (response.getStatus() != 200) {
			response.setStatus(response.getStatus());
			logger.fatal("Failed : HTTP error code : " + response.getStatus());
		}
		return response;
	}

	public static synchronized String getSecureJsonPostResponse(String jsonEndpoint, String jsonObjectEntity) throws GpsSatelliteException {
		HttpClient client = HttpUtils.getNewHttpClient();
		HttpPost post = new HttpPost(jsonEndpoint);
		HttpResponse response = null;
		StringEntity input;
		String responseString = "";
		try {
			input = new StringEntity(jsonObjectEntity);
			input.setContentType(MediaType.TEXT_PLAIN);
			post.setEntity(input);
			response = client.execute(post);
			responseString = EntityUtils.toString(response.getEntity());
			if (response.getStatusLine().getStatusCode() == 500) {
				GpsSatelliteException ex=(GpsSatelliteException)Marshaller.getInstance()
										 .convertToObject(responseString,GpsSatelliteException.class);
				throw ex;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (ClientProtocolException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}catch (ClassCastException e) {
			Exception ex=(Exception)Marshaller.getInstance()
					 .convertToObject(responseString,GpsSatelliteException.class);
				throw new GpsSatelliteException(0,ex.getMessage());
		}
			if (response.getStatusLine().getStatusCode() != 200) {
				response.setStatusCode(response.getStatusLine().getStatusCode());
				logger.fatal("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				responseString = "{" + response.getStatusLine().getStatusCode() + "}";
			}

		client.getConnectionManager().closeExpiredConnections();
		client = null;
		response = null;
		input = null;
		return responseString;
	}

	public static synchronized <T> Object getSecureJsonPostResponse(Class<T> clazz, String jsonEndpoint, Object entity) throws GpsSatelliteException {
		String response = getSecureJsonPostResponse(jsonEndpoint, Marshaller.getInstance().convertToJSONString(entity));
		return Marshaller.getInstance().convertToObject(response, clazz);
	}

	public static synchronized List<?> getSecureJsonPostResponseList(TypeReference<?> typeReference, String jsonEndpoint, Object entity)
			throws GpsSatelliteException {
		String response = getSecureJsonPostResponse(jsonEndpoint, Marshaller.getInstance().convertToJSONString(entity));
		return Marshaller.getInstance().convertToObjLst(response, typeReference);
	}
	public static String getSecureJsonPostResponse(String jsonEndpoint, Object entity) throws GpsSatelliteException {
		return getSecureJsonPostResponse(jsonEndpoint, Marshaller.getInstance().convertToJSONString(entity));
	}
	public static synchronized String getSecureJsonGETResponse(String jsonEndpoint) {
		HttpClient client = HttpUtils.getNewHttpClient();
		HttpGet post = new HttpGet(jsonEndpoint);
		HttpResponse response = null;
		String responseString = "";
		try {
			response = client.execute(post);
			responseString = EntityUtils.toString(response.getEntity());
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (ClientProtocolException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		if (response.getStatusLine().getStatusCode() != 200) {
			response.setStatusCode(response.getStatusLine().getStatusCode());
			logger.fatal("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			responseString = "{" + response.getStatusLine().getStatusCode() + "}";
		}

		client.getConnectionManager().closeExpiredConnections();
		client = null;
		response = null;
		return responseString;
	}

	
}
