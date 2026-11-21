package pixelbattle.heroes;

import pixelbattle.core.Fighter;
import pixelbattle.core.Hero;
import pixelbattle.core.Fighter;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.core.Ultimate;
import pixelbattle.engine.Sprite;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int damage = target.takeDamage(random(7, 11));
            damage += target.takeDamage(random(7, 11));
            return user.getName() + " стріляє двічі в " + target.getName()
                    + ": " + damage + " шкоди";
        }));

        setUltimate(new Ultimate() {
            private int arrowsPerEnemy = 3;

            @Override
            public String getName() {
                return "Злива стріл";
            }

            @Override
            public String getDescription() {
                return arrowsPerEnemy + " стріл по від 4 до 8 шкоди кожному живому ворогу";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int arrows = arrowsPerEnemy++;
                int damage = 0;
                for (Fighter enemy : enemies) {
                    for (int arrow = 0; arrow < arrows && enemy.isAlive(); arrow++) {
                        damage += enemy.takeDamage(random(4, 8));
                    }
                }
                return user.getName() + " застосовує «Зливу стріл» (" + arrows
                        + " стріл на ворога): " + damage + " шкоди";
            }
        });
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        return chance(25) ? 0 : damage;
    }
}
