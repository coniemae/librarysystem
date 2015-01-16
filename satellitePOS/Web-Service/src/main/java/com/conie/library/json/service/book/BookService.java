package com.conie.library.json.service.book;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.conie.library.businesslogic.book.BookBusinessLogic;
import com.conie.library.dto.book.BookDto;
import com.conie.library.util.Marshaller;

@Path("/book")
public class BookService {

	@POST
	@Path("/create")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String request) {
		try {
			BookDto bookDto = (BookDto) Marshaller.getInstance()
					.convertToObject(request, BookDto.class);
			BookDto book = new BookBusinessLogic().create(bookDto);
			return Response.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(book))
					.build();

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
			BookDto bookDto = new BookBusinessLogic().update((BookDto) Marshaller.getInstance()
					.convertToObject(request, BookDto.class));
			return Response.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(bookDto))
					.build();
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
			new BookBusinessLogic().delete(Integer.valueOf(id));
			return Response.status(200).build();
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
			BookDto book = new BookBusinessLogic().find(Integer.valueOf(id));
			return Response.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(book))
					.build();

		} catch (Exception e) {
			return Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
		}
	}
	@POST
	@Path("/findall")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		try {
			List<BookDto> bookList = new BookBusinessLogic().findAll();
			return Response.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(bookList))
					.build();
		} catch (Exception e) {
			Response build = Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
			return build;
		}
	}
}