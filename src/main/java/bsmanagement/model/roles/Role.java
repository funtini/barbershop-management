package bsmanagement.model.roles;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;
import java.util.logging.Logger;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    Logger logger = Logger.getAnonymousLogger();
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;


    public Role() {

    }

    /**
     * Constructor for Role.
     * @param name
     */
    public Role(RoleName name) {
        this.name = name;
  }

  

    /**
     * Method to get ID.
     * @return ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to set ID.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to get name.
     * @return name.
     */
    public RoleName getName() {
        return name;
    }

    /**
     * Method to set name.
     * @param name
     */
    public void setName(RoleName name) {
        this.name = name;
    }

    /**
     * Method to check if RoleName is equal to ROLE_REGISTEREDUSER.
     * @return true if it is, false otherwise.
     */
    public boolean isRegisteredUser() {
        return name.equals(RoleName.ROLE_REGISTEREDUSER);
    }

    /**
     * Method to check if RoleName is equal to ROLE_DIRECTOR.
     * @return true if it is, false otherwise.
     */
    public boolean isStoreManager() {
        return name.equals(RoleName.ROLE_STOREMANAGER);
    }

    /**
     * Method to check if RoleName is equal to ROLE_ADMINISTRATOR.
     * @return true if it is, false otherwise.
     */
    public boolean isAdministrator() {
        return name.equals(RoleName.ROLE_ADMINISTRATOR);
    }

    /**
     * Method to check if RoleName is equal to ROLE_COLLABORATOR.
     * @return true if it is, false otherwise.
     */
    public boolean isUser() {
        return name.equals(RoleName.ROLE_USER);
    }

    @Override
    public String toString() {
        return name.toString().split("_")[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Role))
            return false;
        Role role = (Role) o;
        return getName() == role.getName();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName());
    }
}
