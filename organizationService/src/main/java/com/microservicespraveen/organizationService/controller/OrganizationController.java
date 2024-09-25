package com.microservicespraveen.organizationService.controller;

import com.microservicespraveen.organizationService.dto.OrganizationDto;
import com.microservicespraveen.organizationService.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode) throws Exception{
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
    @DeleteMapping("{code}")
    public ResponseEntity<OrganizationDto> deleteOrganization(@PathVariable("code") String organizationCode) throws Exception{
        OrganizationDto organizationDto = organizationService.deleteOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
    @PutMapping("{code}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("code") String organizationCode,@RequestBody OrganizationDto organizationDto) throws Exception{
        OrganizationDto savedOrganization = organizationService.updateOrganizationByCode(organizationCode,organizationDto);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }
}
