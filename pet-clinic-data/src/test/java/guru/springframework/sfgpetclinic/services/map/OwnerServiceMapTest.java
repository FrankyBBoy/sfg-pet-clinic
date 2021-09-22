package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Test exemples without mockito
class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long OWNER_ID = 1L;
    final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(OWNER_ID);

        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastNameNotFound() {
        Owner foo = ownerServiceMap.findByLastName("foo");

        assertNull(foo);
    }
}