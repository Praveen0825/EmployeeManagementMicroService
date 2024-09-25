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
    public OrganizationDto getOrganizationByCode(String organizationCode) throws Exception{
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
       if (organization == null) {
           throw new Exception("Organization does not exist");
       }
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
    @Override
    public OrganizationDto deleteOrganizationByCode(String organizationCode) throws Exception{
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        if (organization == null) {
            throw new Exception("Organization does not exist");
        }
        organizationRepository.deleteByOrganizationCode(organizationCode);

        return OrganizationMapper.mapToOrganizationDto(organization);
    }
    @Override
    public OrganizationDto updateOrganizationByCode(String organizationCode,OrganizationDto organizationDto) throws Exception{
        // convert organization dto to organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization organizationDB = organizationRepository.findByOrganizationCode(organizationCode);
        if (organizationDB == null) {
            throw new Exception("organization does not exist");
        }

        organizationDB.setOrganizationName(organization.getOrganizationName());
        organizationDB.setOrganizationDescription(organization.getOrganizationDescription());

        Organization savedOrganization = organizationRepository.save(organizationDB);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }
}
