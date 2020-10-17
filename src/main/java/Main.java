import model.Account;
import model.AccountStatus;
import model.Developer;
import model.Skill;
import repository.AccountRepository;
import repository.DeveloperRepository;
import repository.SkillRepository;
import view.DeveloperView;
import view.SkillView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DeveloperView developerRepository = new DeveloperView();
        AccountRepository accountRepository = new AccountRepository();

        developerRepository.getAll();



    }
}
