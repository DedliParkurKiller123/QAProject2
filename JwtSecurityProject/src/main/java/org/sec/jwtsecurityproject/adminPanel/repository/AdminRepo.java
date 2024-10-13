package org.sec.jwtsecurityproject.adminPanel.repository;

import org.sec.jwtsecurityproject.adminPanel.model.AdminPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<AdminPanel, Long> { }
