package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

public class Meteor implements Ultimate {
    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Від 20 до 30 шкоди кожному живому ворогу";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int damage = 0;
        for (Fighter enemy : enemies) {
            if (enemy.isAlive()) {
                damage += enemy.takeDamage(Fighter.random(20, 30));
            }
        }
        return user.getName() + " викликає «Метеорит»: " + damage + " шкоди";
    }
}
