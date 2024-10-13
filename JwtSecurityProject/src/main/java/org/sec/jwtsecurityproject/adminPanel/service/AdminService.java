package org.sec.jwtsecurityproject.adminPanel.service;

import lombok.RequiredArgsConstructor;
import org.sec.jwtsecurityproject.adminPanel.model.AdminPanel;
import org.sec.jwtsecurityproject.adminPanel.repository.AdminRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo adminRepo;

    public List<AdminPanel> findAll(){
        return adminRepo.findAll();
    }

    public AdminPanel findById(Long id){
        return adminRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("Not Found"));
    }

    public String deleteById(Long id){
        adminRepo.deleteById(id);
        return adminRepo.existsById(id) ? "Deleted failed" : "Deleted successful";
    }


    public AdminPanel addNewPosition(AdminPanel adminPanel){
       return adminRepo.save(adminPanel);
    }

    @Transactional
    public AdminPanel updateById(Long id, String name, Double price, LocalDate dataAdded, String description){
        AdminPanel adminPanel = adminRepo.findById(id).orElseThrow(
                ()-> new UsernameNotFoundException("Not found with "+id)
        );
        if(isNonEmptyAndNotEquals(name, adminPanel.getName()))
            adminPanel.setName(name);
        if(isNonEmptyAndNotEquals(String.valueOf(price), String.valueOf(adminPanel.getPrice())))
            adminPanel.setPrice(price);
        if(isNonEmptyAndNotEquals(String.valueOf(dataAdded), String.valueOf(adminPanel.getDataAdded())))
            adminPanel.setDataAdded(dataAdded);
        if(isNonEmptyAndNotEquals(description, adminPanel.getDescription()))
            adminPanel.setDescription(description);
        return adminPanel;
    }

    private Boolean isNonEmptyAndNotEquals(String currentV, String newV ){
        return newV != null && !newV.isEmpty() && !Objects.equals(currentV, newV);
    }

}
