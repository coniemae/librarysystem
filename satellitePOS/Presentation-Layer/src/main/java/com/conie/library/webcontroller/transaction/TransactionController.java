package com.conie.library.webcontroller.transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;

import com.conie.library.dto.borrower.BorrowerDto;
import com.conie.library.exception.GpsSatelliteException;
import com.conie.library.util.JsonClient;
import com.conie.library.webcontroller.category.CategoryController;

@ManagedBean(name = "transactionController")
@ViewScoped
public class TransactionController implements Serializable {

	private static final long serialVersionUID = -1747390184007469622L;
	private static final Logger LOGGER = Logger
			.getLogger(CategoryController.class);
	private static final String CREATE = "http://localhost:8080/Web-Service/service/transaction/create";
	private static final String UPDATE = "http://localhost:8080/Web-Service/service/transaction/update";
	private static final String FIND_ALL = "http://localhost:8080/Web-Service/service/transaction/findAll";
	private static final String FIND = "http://localhost:8080/Web-Service/service/transaction/find";
	private static final String DELETE = "http://localhost:8080/Web-Service/service/transaction/delete";

	private BorrowerDto borrower = new BorrowerDto();
	private List<BorrowerDto> borrowerList = new ArrayList<BorrowerDto>();
	private List<BorrowerDto> filteredBorrower = new ArrayList<BorrowerDto>();
	private FacesMessage msg;
	private Action currentAction = Action.Create;
	private boolean addedOrUpdated;
	
	
	public enum Action {
		Create("Create Borrower"), Update("Update Borrower");
		private String text;

		Action(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	};
	public TransactionController() {
		init();
	}
	private void init() {
		initializeList();
		setBorrower(new BorrowerDto());
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
			borrowerList = findAllBorrower();
		} catch (GpsSatelliteException e) {
			LOGGER.error(e);
		}
	}
	@SuppressWarnings("unchecked")
	private List<BorrowerDto> findAllBorrower() throws GpsSatelliteException {
		return (List<BorrowerDto>) JsonClient.getSecureJsonPostResponseList(
				new TypeReference<List<BorrowerDto>>() {
				}, FIND_ALL, "");
	}
	
	public void initBorrowerList(){
		try {
			findAllBorrower();
			filteredBorrower.clear();
			filteredBorrower.addAll(borrowerList);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void create() {
		try {
			JsonClient.getSecureJsonPostResponse(BorrowerDto.class, CREATE,
					borrower);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
					"Borrower %s is successfully created.", borrower.getName()),
					borrower.getName()));
			initializeList();
			setAddedOrUpdated(true);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format(
					"Failed to create %s!", borrower.getName()),
					borrower.getName()));
		}
	}

	public void update() {
		try {
			JsonClient.getSecureJsonPostResponse(BorrowerDto.class, UPDATE,
					borrower);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
					"Borrower %s is successfully borrowed a book.", borrower.getName()),
					borrower.getName()));
			initializeList();
			setAddedOrUpdated(true);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
			setMsg(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(),e.getMessage()));
		}
	}	
	
	public void delete() {
		try {
			JsonClient.getSecureJsonPostResponse(DELETE, borrower.getId());
			setMsg(new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(
					"Borrower %s is successfully deleted.", borrower.getName()),
					borrower.getName()));
			initializeList();
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private BorrowerDto find(int id) {
		try {
			return (BorrowerDto) JsonClient.getSecureJsonPostResponse(
					BorrowerDto.class, FIND, id);
		} catch (GpsSatelliteException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public void prepareUpdate() {
		currentAction = Action.Update;
		borrower = find(borrower.getId());
	}

	public void prepareCreate() {
		currentAction = Action.Create;
		borrower = new BorrowerDto();
	}

	// Getter and Setter
	
	public BorrowerDto getBorrower() {
		return borrower;
	}
	public List<BorrowerDto> getFilteredBorrower() {
		return filteredBorrower;
	}
	public void setFilteredBorrower(List<BorrowerDto> filteredBorrower) {
		this.filteredBorrower = filteredBorrower;
	}
	public void setBorrower(BorrowerDto borrower) {
		this.borrower = borrower;
	}

	public List<BorrowerDto> getBorrowerList() {
		return borrowerList;
	}

	public void setBorrowerList(List<BorrowerDto> borrowerList) {
		this.borrowerList = borrowerList;
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
