package com.conie.library.json.service.category;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.conie.library.businesslogic.category.CategoryBusinessLogic;
import com.conie.library.dto.category.CategoryDto;
import com.conie.library.util.Marshaller;

@Path("/category")
public class CategoryService {

	@POST
	@Path("/create")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String request) {
		try {

			CategoryDto categoryDto = (CategoryDto) Marshaller.getInstance()
					.convertToObject(request, CategoryDto.class);
			CategoryDto category = new CategoryBusinessLogic()
					.create(categoryDto);
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							category)).build();
		} catch (Exception e) {
			return Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
		}
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(String request) {
		try {
			CategoryDto categoryDto = new CategoryBusinessLogic()
					.update((CategoryDto) Marshaller.getInstance()
							.convertToObject(request, CategoryDto.class));
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							categoryDto)).build();
		} catch (Exception e) {
			return Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
		}
	}

	@POST
	@Path("/find")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(String id) {
		try {
			CategoryDto categoryDto = new CategoryBusinessLogic().find(Integer
					.valueOf(id));
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							categoryDto)).build();

		} catch (Exception e) {
			return Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
		}
	}

	@POST
	@Path("/delete")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(String id) {
		try {
			new CategoryBusinessLogic().delete(Integer.valueOf(id));
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
		}
	}

	@POST
	@Path("/findAll")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try {
			List<CategoryDto> categoryList = new CategoryBusinessLogic().findAll();
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							categoryList)).build();
		} catch (Exception e) {
			Response build = Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
			return build;
		}
	}

}
