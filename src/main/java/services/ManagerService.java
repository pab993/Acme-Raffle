
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	//Managed Repository =============================================================================

	@Autowired
	private ManagerRepository	managerRepository;


	//SCRUDs Methods
	//===============================================================================================

	public Manager findOne(final int managerId) {
		Manager result;

		result = this.managerRepository.findOne(managerId);

		return result;
	}

	//Other Business Methods =========================================================================

	public Manager findByPrincipal() {
		Manager result;
		UserAccount userAccount;
		try {
			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);

			result = this.findByUserAccount(userAccount);
			Assert.notNull(result);
		} catch (Throwable oops) {
			result = null;
		}
		return result;
	}

	public Manager findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Manager result;

		result = this.managerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}
