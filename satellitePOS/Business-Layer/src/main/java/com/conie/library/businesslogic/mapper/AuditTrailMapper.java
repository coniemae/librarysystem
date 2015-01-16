package com.conie.library.businesslogic.mapper;

import org.apache.commons.lang3.StringUtils;

import com.conie.library.dto.AuditTrailDto;
import com.conie.library.entity.AuditTrail;

public class AuditTrailMapper {

	protected static void toAuditTrailDto(AuditTrailDto dto, AuditTrail entity) {
		if (StringUtils.isNotEmpty(entity.getCreatedBy())) {
			dto.setCreatedBy(entity.getCreatedBy());
		}
		dto.setDateCreated(entity.getDateCreated());
		dto.setDateModified(entity.getDateModified());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setVersion(entity.getVersion());
		if (entity.getId() != 0) {
			dto.setId(entity.getId());
		}
	}

	protected static void toAuditTrailEntity(AuditTrailDto dto,
			AuditTrail entity) {
		if (StringUtils.isNotEmpty(dto.getCreatedBy())) {
			entity.setCreatedBy(dto.getCreatedBy());
		}
		entity.setDateCreated(dto.getDateCreated());
		entity.setDateModified(dto.getDateModified());
		entity.setModifiedBy(dto.getModifiedBy());
		entity.setVersion(dto.getVersion());
		if (dto.getId() != null) {
			entity.setId(dto.getId());
		}
	}

}
