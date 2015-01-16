package com.conie.library.webcontroller.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;

import com.conie.library.dto.book.BookDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;

@ManagedBean(name = "bookController")
@ViewScoped
public class BookController implements Serializable {
	private static final long serialVersionUID = 2673662694809260799L;

	private static final Logger LOGGER = Logger.getLogger(BookController.class);
	private static final String CREATE = "http://localhost:8080/Web-Service/service/book/create";
	private static final String UPDATE = "http://localhost:8080/Web-Service/service/book/update";
	private static final String FIND_ALL = "http://localhost:8080/Web-Service/service/book/findall";
	private static final String FIND = "http://localhost:8080/Web-Service/service/book/find";
	private static final String DELETE = "http://localhost:8080/Web-Service/service/book/delete";

	private BookDto book = new BookDto();
	private List<BookDto> bookList = new ArrayList<BookDto>();
/*	private List<BookDto> bookfilteredList = new ArrayList<BookDto>();*/
	private FacesMessage msg;
	private Action currentAction = Action.Create;
	private boolean addedOrUpdated = false;

	public enum Action {
		Create("Create Book"), Update("Update Book"), Delete("Delete Book");
		private String text;

		Action(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	};

	public BookController() {
		init();
	}

	private void init() {
		initializeList();
		setBook(new BookDto());
	}

	public void save() {
		if (Action.Create.equals(currentAction)) {
			create();
		} else if (Action.Update.equals(currentAction)) {
			update();
		}
		FacesContext.getCurrentInstance().addMessage(null, getMsg());
	}

	private void initializeList() {
		try {
			bookList = findAllBooks();
		} catch (GpsSatelliteException e) {
			LOGGER.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	private List<BookDto> findAllBooks() throws GpsSatelliteException {
		return (List<BookDto>) JsonClient.getSecureJsonPostResponseList(
				new TypeReference<List<BookDto>>() {
				}, FIND_ALL, "");
	}

	/*public void initBookList() {
		try {
			findAllBooks();
			bookfilteredList.clear();
			bookfilteredList.addAll(bookList);
		} catch (GpsSatelliteException e) {
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
			LOGGER.error(e);
		}

	}*/

	public void create() {
		try {
			JsonClient.getSecureJsonPostResponse(BookDto.class, CREATE, book);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
					"Book %s is successfully created.", book.getName()),
					book.getName()));
			initializeList();
			setAddedOrUpdated(true);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(
					"Failed to create %s book.", book.getName()),
					book.getName()));
		}
	}

	public void update() {
		try {
			JsonClient.getSecureJsonPostResponse(BookDto.class, UPDATE, book);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
					"Book %s is successfully updated.", book.getName()),
					book.getName()));
			initializeList();
			setAddedOrUpdated(true);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(
					"Failed to update %s book.", book.getName()),
					book.getName()));
		}
	}

	public void delete() {
		try {
			JsonClient.getSecureJsonPostResponse(DELETE, book.getId());
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
					"Book %s is successfully deleted.", book.getName()),
					book.getName()));
			initializeList();
		} catch (GpsSatelliteException e) {
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					e.getMessage(), e.getMessage()));
		}
	}

	private BookDto find(int id) {
		try {
			return (BookDto) JsonClient.getSecureJsonPostResponse(
					BookDto.class, FIND, id);
		} catch (GpsSatelliteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void prepareUpdate() {
		setAddedOrUpdated(false);
		currentAction = Action.Update;
		book = find(book.getId());
	}

	public void prepareCreate() {
		setAddedOrUpdated(false);
		currentAction = Action.Create;
		book = new BookDto();
	}
/*
	public List<BookDto> getBookfilteredList() {
		return bookfilteredList;
	}

	public void setBookfilteredList(List<BookDto> bookfilteredList) {
		this.bookfilteredList = bookfilteredList;
	}*/

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public List<BookDto> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookDto> bookList) {
		this.bookList = bookList;
	}

	public FacesMessage getMsg() {
		return msg;
	}

	public void setMsg(FacesMessage msg) {
		this.msg = msg;
	}

	public Action getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(Action currentAction) {
		this.currentAction = currentAction;
	}

	public boolean isAddedOrUpdated() {
		return addedOrUpdated;
	}

	public void setAddedOrUpdated(boolean addedOrUpdated) {
		this.addedOrUpdated = addedOrUpdated;
	}

}
