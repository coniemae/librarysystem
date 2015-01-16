package com.conie.library.json.service.borrower;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.conie.library.businesslogic.borrower.BorrowerBusinessLogic;
import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.util.Marshaller;

@Path("/borrower")
public class BorrowerService {

	@POST
	@Path("/create")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String request) {
		try {
			BorrowerDto borrowerDto = (BorrowerDto) Marshaller.getInstance()
					.convertToObject(request, BorrowerDto.class);
			BorrowerDto borrower = new BorrowerBusinessLogic()
					.create(borrowerDto);
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							borrower)).build();
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
			BorrowerDto borrowerDto = new BorrowerBusinessLogic()
					.update((BorrowerDto) Marshaller.getInstance()
							.convertToObject(request, BorrowerDto.class));
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							borrowerDto)).build();
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
			BorrowerDto borrowerDto = new BorrowerBusinessLogic().find(Integer
					.valueOf(id));
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							borrowerDto)).build();

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
			List<BorrowerDto> borrowerList = new BorrowerBusinessLogic()
					.findAll();
			return Response
					.status(200)
					.entity(Marshaller.getInstance().convertToJSONString(
							borrowerList)).build();
		} catch (Exception e) {
			Response build = Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
			return build;
		}
	}
	@POST
	@Path("/delete")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(String id) {
		try {
			new BorrowerBusinessLogic().delete(Integer.valueOf(id));
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(500)
					.entity(Marshaller.getInstance().convertToJSONString(e))
					.build();
		}
	}

}
