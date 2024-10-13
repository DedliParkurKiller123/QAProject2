package org.sec.jwtsecurityproject.user.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sec.jwtsecurityproject.config.customAnnotation.age.MinAge;
import org.sec.jwtsecurityproject.user.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_my_user_")
@Entity
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name="Seq_gener_user",
            sequenceName = "Seq_gener_user",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Seq_gener_user"
    )
    private Long userId;
    @Size(max = 500,min = 12, message = "Name should be between from 12 to 500 character")
    private String name;
    @MinAge(value = 18, message = "You must be at least 18 years old")
    private LocalDate dateOfBirth;
    @Size(min = 10,max = 10,message = "Phone number should be 10 character")
    private String phoneNumber;
    @Email(message = "Invalid email")
    @NotBlank(message = "Email can't be empty")
    private String email;
    @Size(min = 8,max = 100, message = "Password should be between from 8 to 100 character")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, LocalDate dateOfBirth, String phoneNumber, String email, String password, Role role) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
