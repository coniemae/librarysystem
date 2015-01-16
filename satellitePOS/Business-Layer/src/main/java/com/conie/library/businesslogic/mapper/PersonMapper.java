package com.conie.library.businesslogic.mapper;

import com.conie.library.dto.PersonDto;
import com.conie.library.entity.Person;

public class PersonMapper extends AuditTrailMapper {
	protected static PersonDto toPersonDto(Person entity, PersonDto dto) {
		toAuditTrailDto(dto, entity);
		dto.setContactNumber(entity.getContactNumber());
		dto.setEmail(entity.getEmail());
		dto.setFirstName(entity.getFirstName());
		dto.setActive(entity.isActive());
		dto.setLastName(entity.getLastName());
		dto.setMiddleName(entity.getMiddleName());
		return dto;
	}

	protected static void toPersonEntity(PersonDto dto, Person entity) {
		toAuditTrailEntity(dto, entity);
		entity.setActive(dto.isActive());
		entity.setContactNumber(dto.getContactNumber());
		entity.setEmail(dto.getEmail());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setMiddleName(dto.getMiddleName());
	}

}
