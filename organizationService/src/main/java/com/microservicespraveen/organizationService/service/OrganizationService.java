package com.microservicespraveen.organizationService.service;

import com.microservicespraveen.organizationService.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode) throws Exception;
    OrganizationDto deleteOrganizationByCode(String organizationCode) throws Exception;
    OrganizationDto updateOrganizationByCode(String organizationCode,OrganizationDto organizationDto) throws Exception;
}
