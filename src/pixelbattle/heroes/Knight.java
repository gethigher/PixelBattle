package pixelbattle.heroes;

import pixelbattle.core.Fighter;
import pixelbattle.core.Hero;
import pixelbattle.core.Fighter;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.core.Ultimate;
import pixelbattle.engine.Sprite;

/** Лицар — міцний воїн ближнього бою. */
public class Knight extends Hero {

    public Knight() {
        super("Лицар", 110, new Sprite("knight_m_idle_anim", 4, 4));

        // ПРИКЛАД: звичайна атака, ефект записаний ЛЯМБДОЮ
        addSkill(new Skill("Удар мечем", Target.ENEMY, 0, (user, target) -> {
            int dmg = target.takeDamage(random(12, 18));
            return user.getName() + " рубає " + target.getName() + ": " + dmg + " шкоди";
        }));

        addSkill(new Skill("Щит", Target.SELF, 2, (user, target) -> {
            user.setGuarding(true);
            return user.getName() + " піднімає щит";
        }));

        setUltimate(new Ultimate() {
            @Override
            public String getName() {
                return "Небесний молот";
            }

            @Override
            public String getDescription() {
                return "15 шкоди й оглушення на 1 хід усім живим ворогам";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int damage = 0;
                for (Fighter enemy : enemies) {
                    if (enemy.isAlive()) {
                        damage += enemy.takeDamage(15);
                        enemy.stun(1);
                    }
                }
                return user.getName() + " застосовує «Небесний молот»: " + damage + " шкоди";
            }
        });
    }

    @Override
    protected int modifyIncomingDamage(int damage) {
        return Math.max(0, damage - 4);
    }
}
