package morgan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyDataRepo extends JpaRepository<MyData, Long> {
}
