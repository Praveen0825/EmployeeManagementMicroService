package com.microservicespraveen.organizationService.service.impl;

import com.microservicespraveen.organizationService.dto.OrganizationDto;
import com.microservicespraveen.organizationService.entity.Organization;
import com.microservicespraveen.organizationService.mapper.OrganizationMapper;
import com.microservicespraveen.organizationService.repository.OrganizationRepository;
import com.microservicespraveen.organizationService.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto){
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }
   @Override
    public OrganizationDto getOrganizationByCode(String organizationCode){
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
