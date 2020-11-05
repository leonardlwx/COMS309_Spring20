package friendManagement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<FriendRelationEntity, Integer> {
	public FriendRelationEntity findFirstByOrderByIdDesc();

	public List<FriendRelationEntity> findByUsernameo(String username);

	public List<FriendRelationEntity> findByUsernamet(String username);
}
