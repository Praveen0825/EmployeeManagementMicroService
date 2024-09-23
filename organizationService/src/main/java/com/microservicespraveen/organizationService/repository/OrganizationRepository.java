package com.microservicespraveen.organizationService.repository;

import com.microservicespraveen.organizationService.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository  extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}
